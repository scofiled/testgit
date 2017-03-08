package mapperrule;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import datautil.DataMapperCommon;
import datautil.SessionUtil;
import news_content_new.NewsContentNewDao;
import news_content_new.NewsContentNewEntity;
import news_webpage.NewsWebpageDao;
import news_webpage.NewsWebpageEntity;
import ns_latest_news.NsLatestnewsDao;
import ns_latest_news.NsLatestnewsEntity;
import ns_webpage.NsWebpageDao;
import ns_webpage.NsWebpageEntity;

public class NewsWebpageAndContent {

	/**
	 * ����news_webpage�����ݡ�
	 * 
	 * @return
	 */
	public NewsWebpageEntity generateNewsWebpage(NsLatestnewsEntity nsLatestnewsEntity,
			NsWebpageEntity nsWebpageEntity) {
		NewsWebpageEntity newsWebpageEntity = new NewsWebpageEntity();
		String webpageurl = nsLatestnewsEntity.getWebpageurl();

		// ����б����У���������û�У��򷵻�nullֵ��
		// NsWebpageEntity nsWebpageEntity =
		// nsWebpageDao.findByWebpageUrl(webpageurl);
		if (null == nsWebpageEntity) {
			return null;
		}

		// webpagecode
		newsWebpageEntity.setWebpagecode(this.generateWebpagecode(nsLatestnewsEntity));
		// webpageurl (�ظ�����Ҫ��¼,�ж��ظ�ԭ��)
		newsWebpageEntity.setWebpageurl(nsLatestnewsEntity.getWebpageurl());
		// tite
		newsWebpageEntity.setTitle(nsLatestnewsEntity.getTitle());
		// shorttitle (����ַ�������)
		newsWebpageEntity.setShorttitle(nsWebpageEntity.getShorttitle());
		// keywords (����ַ�������)
		newsWebpageEntity.setKeywords(nsWebpageEntity.getKeywords());
		// websummary
		newsWebpageEntity.setWebsummary(nsWebpageEntity.getSummary());
		// sourcereport (����ַ�������)
		newsWebpageEntity.setSourcereport(nsLatestnewsEntity.getSourceweb());
		// sourcecrawl (���������ֵ� ����latest_news���е�source_web�ֶ����Ĵ���)
		Integer sourcecrawl = DataMapperCommon.sourcecrawlmap.get(nsLatestnewsEntity.getSourceweb());
		newsWebpageEntity.setSourcecrawl(sourcecrawl);

		// classification (����ַ�������)
		newsWebpageEntity.setClassification(nsLatestnewsEntity.getClassification());
		// cusclassification (���¼���)
		// String cusclassification = nsLatestnewsEntity.getCusclassification();
		// String[] strs = cusclassification.split(",");

		/*
		 * region;repostscount;commentscount;participatenum;vote;
		 * weiboid;weibopid;weiborootid;against;status; ����ӳ��
		 */
		// participatenumĬ��Ϊ0
		newsWebpageEntity.setParticipatenum(0);

		// browsecount;
		newsWebpageEntity.setBrowsecount(nsWebpageEntity.getBrowsecount());
		// author;
		newsWebpageEntity.setAuthor(nsWebpageEntity.getAuthor());
		// crawldatetime;
		newsWebpageEntity.setCrawldatetime(nsLatestnewsEntity.getCrawldatetime());
		// releasedatetime;
		newsWebpageEntity.setReleasedatetime(nsLatestnewsEntity.getReleasedatetime());
		// releasedatetimestr;
		Timestamp releasedatetime = newsWebpageEntity.getReleasedatetime();
		/**
		 * ��release_datetimeֵΪ�գ� ��release_datetime��Ϊcrawl_datetime,
		 * ͬʱ��release_datetime_str��Ϊcrawl_datetime���ַ�����ʽ
		 */
		if (null == releasedatetime) {
			newsWebpageEntity.setReleasedatetime(newsWebpageEntity.getCrawldatetime());
			Timestamp crawldatetime = newsWebpageEntity.getCrawldatetime();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String crawldatetimestr = sd.format(crawldatetime);
			newsWebpageEntity.setReleasedatetimestr(crawldatetimestr);
		}

		// updatedatetime; �ɱ���û�У�Ĭ��Ϊ����ʱ�䡣
		Timestamp updatedatetime = new Timestamp(System.currentTimeMillis());
		newsWebpageEntity.setUpdatedatetime(updatedatetime);

		// iscorenews; Ĭ��Ϊ1
		Integer iscorenews = 1;
		if (null != nsLatestnewsEntity.getHassamenews())
			iscorenews = nsLatestnewsEntity.getHassamenews();
		newsWebpageEntity.setIscorenews(iscorenews);
		// corenewscode; �ݲ�����

		// similarnum;relevantnum;
		/**
		 * ת��news_analysis���ֶ�����nlp_sameArticles
		 * ת��news_analysis���ֶ�����nlp_simArticles
		 */
		// istopnews; isdeleted;isoriginal;isclue; Ĭ�϶�Ϊ0
		newsWebpageEntity.setIstopnews(0);
		newsWebpageEntity.setIsdeleted(0);
		newsWebpageEntity.setIsoriginal(0);
		newsWebpageEntity.setIsclue(0);

		// picpath;·���޸ģ�����ǰ׺·��${inewsImageServer}/image/
		String picpath = nsWebpageEntity.getPicpath();
		picpath = "${inewsImageServer}/image/" + picpath;
		newsWebpageEntity.setPicpath(picpath);

		return newsWebpageEntity;
	}

	/**
	 * ����news_content_new����
	 * 
	 * @param nsLatestnewsEntity
	 * @return
	 */
	public NewsContentNewEntity generateNewsContentNewEntity(NsWebpageEntity nsWebpageEntity,
			NewsWebpageEntity newsWebpageEntity) {
		NewsContentNewEntity newsContentNewEntity = new NewsContentNewEntity();
		// String webpageurl = nsLatestnewsEntity.getWebpageurl();
		// NsWebpageEntity nsWebpageEntity =
		// nsWebpageDao.findByWebpageUrl(webpageurl);

		// ����б�ҳ�������ݣ���������û�����ݣ��򷵻�nullֵ��
		if (null == nsWebpageEntity) {
			return null;
		}
		// webpagecode
		newsContentNewEntity.setWebpagecode(newsWebpageEntity.getWebpagecode());
		// content
		newsContentNewEntity.setContent(nsWebpageEntity.getCleaningcontent());
		// no-tag-content
		newsContentNewEntity.setNotagcontent(nsWebpageEntity.getTagcleaningcontent());
		// description����ӳ��
		// createdatatime ����ӳ�䡣
		Timestamp createdatatime = new Timestamp(System.currentTimeMillis());
		newsContentNewEntity.setCreatedatetime(createdatatime);
		return newsContentNewEntity;
	}

	/**
	 * ����webpagecode
	 * 
	 * @param nsLatestnewsEntity
	 * @return
	 */
	public String generateWebpagecode(NsLatestnewsEntity nsLatestnewsEntity) {
		String webpagecode = null;
		Timestamp crwaldatetime = nsLatestnewsEntity.getCrawldatetime();
		if (null == crwaldatetime) {
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String crawldatetimestr = sd.format(crwaldatetime);

		String datetimestr = crawldatetimestr.substring(2, 10);
		datetimestr = datetimestr.replaceAll("-", "");

		Integer sourcecrawl = DataMapperCommon.sourcecrawlmap.get(nsLatestnewsEntity.getSourceweb());
		Integer id = 1;
		if (DataMapperCommon.webpagecodemap.containsKey(datetimestr)) {
			id = DataMapperCommon.webpagecodemap.get(datetimestr);
			id++;
			DataMapperCommon.webpagecodemap.put(datetimestr, id);
		} else {
			DataMapperCommon.webpagecodemap.put(datetimestr, id);
		}

		webpagecode = datetimestr + "_" + sourcecrawl + "_" + id;
		return webpagecode;
	}

	/**
	 * ��������news_webpage��news_content_new������
	 */
	/*
	 * public static void exportdata(){ NewsWebpageAndContent dealMapperImpl =
	 * new NewsWebpageAndContent();
	 * 
	 * //����ns_latest_news���ݡ� SqlSession session_ns =
	 * SessionUtil.createSession("myBatis-config-ns.xml"); NsLatestnewsDao
	 * nsLatestnewsDao = session_ns.getMapper(NsLatestnewsDao.class);
	 * NsWebpageDao nsWebpageDao = session_ns.getMapper(NsWebpageDao.class);
	 * SqlSession session_news =
	 * SessionUtil.createSession("myBatis-config-news.xml"); NewsWebpageDao
	 * newsWebpageDao = session_news.getMapper(NewsWebpageDao.class);
	 * NewsContentNewDao newsContentNewDao =
	 * session_news.getMapper(NewsContentNewDao.class); Integer rows =
	 * nsLatestnewsDao.countAll(); System.out.println("rows="+rows); int begin =
	 * 0; int pagesize = 1000; int totalpage = 0; if(rows%pagesize == 0)
	 * totalpage = rows/pagesize; else totalpage = rows/pagesize + 1;
	 * System.out.println("totalpage"+totalpage); int currentpage = 1; int count
	 * = 1;
	 * 
	 * List<NewsWebpageEntity> newsWebpageEntityLists = new
	 * ArrayList<NewsWebpageEntity>(); List<NewsContentNewEntity>
	 * newsContentNewEntityLists = new ArrayList<NewsContentNewEntity>(); //��ҳ��ѯ
	 * while(currentpage!=totalpage){
	 * 
	 * List<NsLatestnewsEntity> nsLatestnewslists =
	 * nsLatestnewsDao.selectAll(begin,pagesize);
	 * newsWebpageEntityLists.clear(); newsContentNewEntityLists.clear();
	 * 
	 * for(int i=0;i<nsLatestnewslists.size();i++){ NsLatestnewsEntity
	 * nsLatestnewsEntity = nsLatestnewslists.get(i);
	 * //����news_webpage���ݺ�news_content_new����. NewsWebpageEntity
	 * newsWebpageEntity =
	 * dealMapperImpl.generateNewsWebpage(nsLatestnewsEntity,nsWebpageDao);
	 * NewsContentNewEntity newsContentNewEntity =
	 * dealMapperImpl.generateNewsContentNewEntity(nsLatestnewsEntity,
	 * nsWebpageDao,newsWebpageEntity);
	 * 
	 * //���newsWebpageEntityΪ�գ�˵���б��������ݣ���������û�����ݣ�����Ҫ�����±��С� if(null !=
	 * newsWebpageEntity ){ //����news_webpage���ݺ�news_content_new����. try{
	 * 
	 * newsWebpageDao.insert(newsWebpageEntity);
	 * newsContentNewDao.insert(newsContentNewEntity);
	 * System.out.println("webpage������");
	 * 
	 * //�������� newsWebpageEntityLists.add(newsWebpageEntity);
	 * newsContentNewEntityLists.add(newsContentNewEntity);
	 * }catch(PersistenceException e){
	 * 
	 * e.printStackTrace(); return; }
	 * 
	 * }else{ //System.out.println("webpageΪnull" +"  "+ count++); } }
	 * 
	 * //ÿҳ�������������� if(newsWebpageEntityLists.size() > 0){ try{
	 * newsWebpageDao.insertBatch(newsWebpageEntityLists);
	 * newsContentNewDao.insertBatch(newsContentNewEntityLists);
	 * }catch(PersistenceException e){ //�ظ��ͳ������쳣���� //�ظ�����webpage_url����¼����
	 * //д����־�ļ� e.printStackTrace(); Logger logger = null;
	 * logger=Logger.getLogger(NewsWebpageAndContent.class.getName());
	 * logger.setLevel(Level.ERROR);
	 * System.out.println("list  size"+" "+newsWebpageEntityLists.size());
	 * for(int i=0;i<newsWebpageEntityLists.size();i++){
	 * System.out.println(newsWebpageEntityLists.get(i).getWebpageurl()); }
	 * return; }
	 * 
	 * System.out.println("��������"); } //ÿһҳ����һ�¡�
	 * //session_news.flushStatements(); session_news.commit();
	 * //session_ns.flushStatements(); session_ns.commit();
	 * System.out.println("��С"+newsWebpageEntityLists.size()+" "+"currentpage" +
	 * " "+currentpage); currentpage++; begin = (currentpage-1)*pagesize; }
	 * session_news.close(); session_ns.close(); }
	 */

	/**
	 * ��ns_wenpage��Ϊ�������ת�ơ�
	 */
	public static void exportDataBasicContent(int currentpage) {
		NewsWebpageAndContent dealMapperImpl = new NewsWebpageAndContent();

		// ����ns_latest_news���ݡ�
		SqlSession session_ns = SessionUtil.createSession("mybatis-config-ns.xml");
		NsLatestnewsDao nsLatestnewsDao = session_ns.getMapper(NsLatestnewsDao.class);
		NsWebpageDao nsWebpageDao = session_ns.getMapper(NsWebpageDao.class);
		SqlSession session_news = SessionUtil.createSession("mybatis-config-news.xml");
		NewsWebpageDao newsWebpageDao = session_news.getMapper(NewsWebpageDao.class);
		NewsContentNewDao newsContentNewDao = session_news.getMapper(NewsContentNewDao.class);

		Integer rows = nsWebpageDao.countAll();

		System.out.println("rows=" + rows);
		//int begin = 0;
		int pagesize = 20;
		int totalpage = 0;
		int begin = (currentpage - 1) * pagesize;
		
		if (rows % pagesize == 0)
			totalpage = rows / pagesize;
		else
			totalpage = rows / pagesize + 1;
		System.out.println("totalpage" + totalpage);
		//int currentpage = 1;
		int count = 1;

		List<NewsWebpageEntity> newsWebpageEntityLists = new ArrayList<NewsWebpageEntity>();
		List<NewsContentNewEntity> newsContentNewEntityLists = new ArrayList<NewsContentNewEntity>();
		
		Timestamp maxtime = nsWebpageDao.getMaxtime().getCrawldatetime();
		Timestamp mintime = nsWebpageDao.getMintime().getCrawldatetime();
		
		Timestamp currenttime = mintime;
		
		
		// ��ҳ��ѯ
		while (currenttime.before(maxtime) || currenttime.equals(maxtime)) {

			//List<NsWebpageEntity> nsWebpageEntitylists = nsWebpageDao.selectAll(begin, pagesize);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(currenttime);
			calendar.add(Calendar.MINUTE,10);
			Timestamp endtime = new Timestamp(calendar.getTime().getTime());
			
			
			List<NsWebpageEntity> nsWebpageEntitylists = nsWebpageDao.selectBycrawldatetime(currenttime, endtime);
			newsWebpageEntityLists.clear();
			newsContentNewEntityLists.clear();
			
			for (int i = 0; i < nsWebpageEntitylists.size(); i++) {

				// ns_webpage
				NsWebpageEntity nsWebpageEntity = nsWebpageEntitylists.get(i);

				// ns_latest_news
				List<NsLatestnewsEntity> latestnewslists = nsLatestnewsDao
						.findByWebpageUrl(nsWebpageEntity.getWebpageurl());
				if (latestnewslists.size() > 1) {
					// �ظ���webpage_url
					System.out.println("�ظ�  webpage_url" + "  " + nsWebpageEntity.getWebpageurl());
					
					//continue;
				}else if(latestnewslists.size() == 0){
					continue;
				}
				// ����news_webpage���ݺ�news_content_new����.
				NewsWebpageEntity newsWebpageEntity = dealMapperImpl.generateNewsWebpage(latestnewslists.get(0),
						nsWebpageEntity);

				NewsContentNewEntity newsContentNewEntity = dealMapperImpl.generateNewsContentNewEntity(nsWebpageEntity,
						newsWebpageEntity);

				// ���newsWebpageEntityΪ�գ�˵���б��������ݣ���������û�����ݣ�����Ҫ�����±��С�
				if (null != newsWebpageEntity) {
					// ����news_webpage���ݺ�news_content_new����.
					try {

						/*
						 * newsWebpageDao.insert(newsWebpageEntity);
						 * newsContentNewDao.insert(newsContentNewEntity);
						 * System.out.println("webpage������");
						 */

						// ��������
						if(null != newsWebpageEntity.getWebpagecode()){
							newsWebpageEntityLists.add(newsWebpageEntity);
							newsContentNewEntityLists.add(newsContentNewEntity);
						}
					} catch (PersistenceException e) {

						e.printStackTrace();
						return;
					}

				} else {
					// System.out.println("webpageΪnull" +" "+ count++);
				}
			}

			// ÿҳ��������������
			if (newsWebpageEntityLists.size() > 0) {
				try {
					newsWebpageDao.insertBatch(newsWebpageEntityLists);
					newsContentNewDao.insertBatch(newsContentNewEntityLists);
				} catch (PersistenceException e) {
					// �ظ��ͳ������쳣����
					// �ظ�����webpage_url����¼����
					// д����־�ļ�
					
					System.out.println("�ظ�");
				}

				System.out.println("��������");
			}
			// ÿһҳ����һ�¡�
			// session_news.flushStatements();
			session_news.commit();
			// session_ns.flushStatements();
			session_ns.commit();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currenttimestr = sd.format(currenttime);
			System.out.println("��С" + newsWebpageEntityLists.size() + " " + "currenttime" + " " + currenttimestr);
			currentpage++;
			begin = (currentpage - 1) * pagesize;
			currenttime = endtime;
		}
		session_news.close();
		session_ns.close();

	}

}
