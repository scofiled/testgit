package exportdata;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import dao.UserDao;
import datautil.SessionUtil;
import entity.User;
import mapperrule.NewsHotMapper;
import mapperrule.NewsWebpageAndContent;
import news_webpage.NewsWebpageDao;
import news_webpage.NewsWebpageEntity;
import ns_latest_news.NsLatestnewsDao;
import ns_latest_news.NsLatestnewsEntity;
import ns_webpage.NsWebpageDao;
import ns_webpage.NsWebpageEntity;









public class TestMybties {

	@Test
	public void testInsert() {
		String resource = "mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(reader);

		SqlSession session = factory.openSession();
		UserDao userDao = session.getMapper(UserDao.class);

		User user = new User();
		user.setUserName("hongye");
		user.setPassword("123456");
		user.setComment("��ע");

		userDao.insert(user);
		System.out.println("��¼������" + userDao.countAll());

		session.commit();
		session.close();

	}

	@Test
	public void testNewsWebpage() {
		String resource = "myBatis-config-news.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(reader);

		SqlSession session = factory.openSession();
		NewsWebpageDao newsWebpageDao = session.getMapper(NewsWebpageDao.class);
		NewsWebpageEntity newsWebpageEntity = new NewsWebpageEntity();
		newsWebpageEntity.setAuthor("����");
		newsWebpageDao.insert(newsWebpageEntity);

		session.commit();
		session.close();

	}

	@Test
	public void testNsWebpage() {
		String resource = "myBatis-config-ns.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(reader);

		SqlSession session = factory.openSession();
		NsWebpageDao nsWebpageDao = session.getMapper(NsWebpageDao.class);

		List<NsWebpageEntity> nswebpages = nsWebpageDao.selectAll(0,10);
		System.out.println(nswebpages.size());

		session.commit();
		session.close();

	}

	@Test
	public void testNsLatestnews() {
		String resource = "myBatis-config-ns.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(reader);

		SqlSession session = factory.openSession();
		NsLatestnewsDao nsWebpageDao = session.getMapper(NsLatestnewsDao.class);

		List<NsLatestnewsEntity> nswebpages = nsWebpageDao.selectAll(0, 1);
		session.commit();
		session.close();
		// System.out.println(nswebpages.size());
		NsLatestnewsEntity entity = nswebpages.get(0);
		System.out.println(entity.getCrawldatetime());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String crawldatetimestr = sd.format(entity.getCrawldatetime());

		String datetimestr = crawldatetimestr.substring(2, 10);
		datetimestr = datetimestr.replaceAll("-", "");
		System.out.println(datetimestr);

	}

	/**
	 * 
	 */

	/*@Test
	 public void testdealmapper() {

		NewsWebpageAndContent dealMapperImpl = new NewsWebpageAndContent();

		// ����ns_latest_news���ݡ�
		SqlSession session_ns = SessionUtil.createSession("myBatis-config-ns.xml");
		NsLatestnewsDao nsLatestnewsDao = session_ns.getMapper(NsLatestnewsDao.class);
		NsWebpageDao nsWebpageDao = session_ns.getMapper(NsWebpageDao.class);
		SqlSession session_news = SessionUtil.createSession("myBatis-config-news.xml");
		NewsWebpageDao newsWebpageDao = session_news.getMapper(NewsWebpageDao.class);
		NewsContentNewDao newsContentNewDao = session_news.getMapper(NewsContentNewDao.class);

		List<NsLatestnewsEntity> nsLatestnewslists = nsLatestnewsDao.selectAll(1979, 2);
		for (int i = 0; i < nsLatestnewslists.size(); i++) {
			NsLatestnewsEntity nsLatestnewsEntity = nsLatestnewslists.get(i);
			// ����news_webpage���ݺ�news_content_new����.
			NewsWebpageEntity newsWebpageEntity = dealMapperImpl.generateNewsWebpage(nsLatestnewsEntity, nsWebpageDao);
			NewsContentNewEntity newsContentNewEntity = dealMapperImpl.generateNewsContentNewEntity(nsLatestnewsEntity,
					nsWebpageDao,newsWebpageEntity);
			try{
				// ���newsWebpageEntityΪ�գ�˵���б��������ݣ���������û�����ݣ�����Ҫ�����±��С�
				if (null != newsWebpageEntity) {
					String classification = newsWebpageEntity.getClassification();
					classification = classification + "һһһһһһһһһһһһһһһһһһһһһһһһһһһһһһһһһһһһ";
					//newsWebpageEntity.setClassification(classification);
					// ����news_webpage���ݺ�news_content_new����.
					newsWebpageDao.insert(newsWebpageEntity);
					newsContentNewDao.insert(newsContentNewEntity);
				}
			}
			catch(PersistenceException e){
				//�ظ�����webpage_url����¼����
				e.printStackTrace();
				
			}
		}
		// ÿһҳ����һ�¡�
		session_news.commit();
		session_news.close();
		session_ns.commit();
		session_ns.close();
	}*/

	
	//����news_webpage��news_content_new����.
	@Test
	public void testinsertwebpage(){
		NewsWebpageAndContent.exportDataBasicContent(53);
	}
	
	
	@Test
	public void testinsertHot(){
		NewsHotMapper.exportdata();
	}
	
	
	@Test
	public void testadddate(){
		SqlSession session_ns = SessionUtil.createSession("mybatis-config-ns.xml");
		NsWebpageDao nsWebpageDao = session_ns.getMapper(NsWebpageDao.class);
		Timestamp mintime = nsWebpageDao.getMintime().getCrawldatetime();
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mintimestr = sd.format(mintime);
		System.out.println(mintimestr);
		
		Timestamp currenttime = mintime;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(currenttime);
		calendar.add(Calendar.HOUR_OF_DAY,2);
		Timestamp endtime = new Timestamp(calendar.getTime().getTime());
		
		nsWebpageDao.selectBycrawldatetime(currenttime, endtime);
		
		/*
		String endtimestr = sd.format(endtime);
		System.out.println(endtimestr);
		calendar.setTime(endtime);
		calendar.add(Calendar.HOUR_OF_DAY,2);
		endtime = new Timestamp(calendar.getTime().getTime());
		endtimestr = sd.format(endtime);
		System.out.println(endtimestr);
		*/
		
	}
}
