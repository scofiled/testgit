package mapperrule;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import datautil.DataMapperCommon;
import datautil.SessionUtil;
import news_content_new.NewsContentNewDao;
import news_content_new.NewsContentNewEntity;
import news_hot.NewsHotDao;
import news_hot.NewsHotEntity;
import news_webpage.NewsWebpageDao;
import news_webpage.NewsWebpageEntity;
import ns_hot_news.NsHotNewsDao;
import ns_hot_news.NsHotNewsEntity;
import ns_latest_news.NsLatestnewsDao;
import ns_latest_news.NsLatestnewsEntity;
import ns_webpage.NsWebpageDao;

public class NewsHotMapper {

	/**
	 * ����news_hot������ʵ�塣
	 * 
	 * @param nsHotNewsEntity
	 * @return
	 */
	public NewsHotEntity generateNewsHot(NsHotNewsEntity nsHotNewsEntity, NewsWebpageDao newsWebpageDao) {
		NewsHotEntity newsHotEntity = new NewsHotEntity();
		// title ֱ��ӳ��
		newsHotEntity.setTitle(nsHotNewsEntity.getTitle());
		newsHotEntity.setRankingnum(nsHotNewsEntity.getRankingnum());
		newsHotEntity.setCommentnum(nsHotNewsEntity.getCommentnum());
		newsHotEntity.setParticipatenum(nsHotNewsEntity.getParticipatenum());
		newsHotEntity.setClickingnum(nsHotNewsEntity.getClickingnum());
		// heat_num�������ɡ�

		// ranking_cycle ranking_type

		// source_web
		Integer sourceweb = DataMapperCommon.sourcecrawlmap.get(nsHotNewsEntity.getSourceweb());
		newsHotEntity.setSourceweb(sourceweb);

		// classification
		newsHotEntity.setClassification(nsHotNewsEntity.getClassification());
		// crawl_datetime release_datetime release_datetime_str
		newsHotEntity.setCrawldatetime(nsHotNewsEntity.getCrawldatetime());

		String releasedatetimestr = nsHotNewsEntity.getReleasedatetime();
		// SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Timestamp releasedatetime =
		// Timestamp.valueOf(sd.format(releasedatetimestr));
		Timestamp releasedatetime = null;
		if (null != releasedatetimestr)
			releasedatetime = Timestamp.valueOf(releasedatetimestr);
		newsHotEntity.setReleasedatetime(releasedatetime);

		if (null == releasedatetime) {
			newsHotEntity.setReleasedatetime(nsHotNewsEntity.getCrawldatetime());
			Timestamp crawldatetime = newsHotEntity.getCrawldatetime();
			if (null != crawldatetime) {
				SimpleDateFormat sds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String crawldatetimestr = sds.format(crawldatetime);
				newsHotEntity.setReleasedatetimestr(crawldatetimestr);
			}
		}

		Timestamp updatedatetime = new Timestamp(System.currentTimeMillis());
		newsHotEntity.setUpdatedatetime(updatedatetime);

		// webpage_code
		newsHotEntity.setWebpagecode(this.findWebpageCode(nsHotNewsEntity, newsWebpageDao));
		// webpage_url
		newsHotEntity.setWebpageurl(nsHotNewsEntity.getWebpageurl());

		newsHotEntity.setIsdeleted(0);
		return newsHotEntity;
	}

	/**
	 * ����webpagecode �Ӷ�news_webpage�в��ҡ�
	 * 
	 * @param nsHotNewsEntity
	 * @return
	 */
	public String findWebpageCode(NsHotNewsEntity nsHotNewsEntity, NewsWebpageDao newsWebpageDao) {
		String webpagecode = null;
		NewsWebpageEntity newsWebpageEntity = newsWebpageDao.findByWebpageUrl(nsHotNewsEntity.getWebpageurl());
		if (null != newsWebpageEntity)
			webpagecode = newsWebpageEntity.getWebpagecode();
		return webpagecode;
	}

	public static void exportdata() {
		NewsHotMapper dealMapperImpl = new NewsHotMapper();

		// ����ns_latest_news���ݡ�
		SqlSession session_ns = SessionUtil.createSession("mybatis-config-ns.xml");
		NsHotNewsDao nsHotnewsDao = session_ns.getMapper(NsHotNewsDao.class);

		SqlSession session_news = SessionUtil.createSession("mybatis-config-news.xml");
		NewsWebpageDao newsWebpageDao = session_news.getMapper(NewsWebpageDao.class);
		NewsHotDao newsHotDao = session_news.getMapper(NewsHotDao.class);
		Integer rows = nsHotnewsDao.countAll();
		int begin = 0;
		int pagesize = 5000;
		int totalpage = 0;
		if (rows % pagesize == 0)
			totalpage = rows / pagesize;
		else
			totalpage = rows / pagesize + 1;

		int currentpage = 1;
		List<NewsHotEntity> newsHotEntityLists = new ArrayList<NewsHotEntity>();

		// ��ҳ��ѯ
		while (currentpage <= totalpage) {
			List<NsHotNewsEntity> nsHotnewslists = nsHotnewsDao.selectAll(begin, pagesize);
			newsHotEntityLists.clear();
			for (int i = 0; i < nsHotnewslists.size(); i++) {
				NsHotNewsEntity nsHotnewsEntity = nsHotnewslists.get(i);
				// ����news_webpage���ݺ�news_content_new����.

				NewsHotEntity newsHotEntity = dealMapperImpl.generateNewsHot(nsHotnewsEntity, newsWebpageDao);
				// ���newsWebpageEntityΪ�գ�˵���б��������ݣ���������û�����ݣ�����Ҫ�����±��С�
				if (null != newsHotEntity && null != newsHotEntity.getWebpagecode()) {
					// ����news_webpage���ݺ�news_content_new����.
					try {
						// newsHotDao.insert(newsHotEntity);
						newsHotEntityLists.add(newsHotEntity);
						//System.out.println("��������");
					} catch (PersistenceException e) {
						// �ظ�����webpage_url����¼����
						// д����־�ļ�

						e.printStackTrace();
						return;
						/*
						 * System.out.println("webpage_url�ظ�");
						 * System.out.println("�ظ���¼webpage_codeΪ"+newsHotEntity.
						 * getWebpagecode());
						 */
					}

				}
			}

			// ÿҳ��������������
			if (newsHotEntityLists.size() > 0) {
				try {
					newsHotDao.insertBatch(newsHotEntityLists);
				} catch (PersistenceException e) {
					// �ظ��ͳ������쳣����
					// �ظ�����webpage_url����¼����
					// д����־�ļ�
					System.out.println("�ظ�");
				}

				System.out.println("��������");
			}

			System.out.println("��С" + newsHotEntityLists.size() + " " + "currentpage" + " " + currentpage);
			currentpage++;
			begin = (currentpage - 1) * pagesize;

			// ÿһҳ����һ�¡�
			session_news.commit();
			session_ns.commit();
		}

		session_news.close();
		session_ns.close();
	}
}
