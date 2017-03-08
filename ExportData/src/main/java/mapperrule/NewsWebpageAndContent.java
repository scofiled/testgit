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
	 * 生成news_webpage表数据。
	 * 
	 * @return
	 */
	public NewsWebpageEntity generateNewsWebpage(NsLatestnewsEntity nsLatestnewsEntity,
			NsWebpageEntity nsWebpageEntity) {
		NewsWebpageEntity newsWebpageEntity = new NewsWebpageEntity();
		String webpageurl = nsLatestnewsEntity.getWebpageurl();

		// 如果列表中有，而内容中没有，则返回null值。
		// NsWebpageEntity nsWebpageEntity =
		// nsWebpageDao.findByWebpageUrl(webpageurl);
		if (null == nsWebpageEntity) {
			return null;
		}

		// webpagecode
		newsWebpageEntity.setWebpagecode(this.generateWebpagecode(nsLatestnewsEntity));
		// webpageurl (重复后需要记录,判断重复原因)
		newsWebpageEntity.setWebpageurl(nsLatestnewsEntity.getWebpageurl());
		// tite
		newsWebpageEntity.setTitle(nsLatestnewsEntity.getTitle());
		// shorttitle (检查字符串长度)
		newsWebpageEntity.setShorttitle(nsWebpageEntity.getShorttitle());
		// keywords (检查字符串长度)
		newsWebpageEntity.setKeywords(nsWebpageEntity.getKeywords());
		// websummary
		newsWebpageEntity.setWebsummary(nsWebpageEntity.getSummary());
		// sourcereport (检查字符串长度)
		newsWebpageEntity.setSourcereport(nsLatestnewsEntity.getSourceweb());
		// sourcecrawl (查找数据字典 按照latest_news表中的source_web字段做的处理)
		Integer sourcecrawl = DataMapperCommon.sourcecrawlmap.get(nsLatestnewsEntity.getSourceweb());
		newsWebpageEntity.setSourcecrawl(sourcecrawl);

		// classification (检查字符串长度)
		newsWebpageEntity.setClassification(nsLatestnewsEntity.getClassification());
		// cusclassification (重新计算)
		// String cusclassification = nsLatestnewsEntity.getCusclassification();
		// String[] strs = cusclassification.split(",");

		/*
		 * region;repostscount;commentscount;participatenum;vote;
		 * weiboid;weibopid;weiborootid;against;status; 无需映射
		 */
		// participatenum默认为0
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
		 * 若release_datetime值为空， 将release_datetime置为crawl_datetime,
		 * 同时将release_datetime_str置为crawl_datetime的字符串形式
		 */
		if (null == releasedatetime) {
			newsWebpageEntity.setReleasedatetime(newsWebpageEntity.getCrawldatetime());
			Timestamp crawldatetime = newsWebpageEntity.getCrawldatetime();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String crawldatetimestr = sd.format(crawldatetime);
			newsWebpageEntity.setReleasedatetimestr(crawldatetimestr);
		}

		// updatedatetime; 旧表中没有，默认为插入时间。
		Timestamp updatedatetime = new Timestamp(System.currentTimeMillis());
		newsWebpageEntity.setUpdatedatetime(updatedatetime);

		// iscorenews; 默认为1
		Integer iscorenews = 1;
		if (null != nsLatestnewsEntity.getHassamenews())
			iscorenews = nsLatestnewsEntity.getHassamenews();
		newsWebpageEntity.setIscorenews(iscorenews);
		// corenewscode; 暂不生成

		// similarnum;relevantnum;
		/**
		 * 转存news_analysis表，字段类型nlp_sameArticles
		 * 转存news_analysis表，字段类型nlp_simArticles
		 */
		// istopnews; isdeleted;isoriginal;isclue; 默认都为0
		newsWebpageEntity.setIstopnews(0);
		newsWebpageEntity.setIsdeleted(0);
		newsWebpageEntity.setIsoriginal(0);
		newsWebpageEntity.setIsclue(0);

		// picpath;路径修改：增加前缀路径${inewsImageServer}/image/
		String picpath = nsWebpageEntity.getPicpath();
		picpath = "${inewsImageServer}/image/" + picpath;
		newsWebpageEntity.setPicpath(picpath);

		return newsWebpageEntity;
	}

	/**
	 * 生成news_content_new内容
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

		// 如果列表页中有数据，而内容中没有数据，则返回null值。
		if (null == nsWebpageEntity) {
			return null;
		}
		// webpagecode
		newsContentNewEntity.setWebpagecode(newsWebpageEntity.getWebpagecode());
		// content
		newsContentNewEntity.setContent(nsWebpageEntity.getCleaningcontent());
		// no-tag-content
		newsContentNewEntity.setNotagcontent(nsWebpageEntity.getTagcleaningcontent());
		// description虚无映射
		// createdatatime 无需映射。
		Timestamp createdatatime = new Timestamp(System.currentTimeMillis());
		newsContentNewEntity.setCreatedatetime(createdatatime);
		return newsContentNewEntity;
	}

	/**
	 * 生成webpagecode
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
	 * 数据移入news_webpage和news_content_new的流程
	 */
	/*
	 * public static void exportdata(){ NewsWebpageAndContent dealMapperImpl =
	 * new NewsWebpageAndContent();
	 * 
	 * //读出ns_latest_news数据。 SqlSession session_ns =
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
	 * newsContentNewEntityLists = new ArrayList<NewsContentNewEntity>(); //分页查询
	 * while(currentpage!=totalpage){
	 * 
	 * List<NsLatestnewsEntity> nsLatestnewslists =
	 * nsLatestnewsDao.selectAll(begin,pagesize);
	 * newsWebpageEntityLists.clear(); newsContentNewEntityLists.clear();
	 * 
	 * for(int i=0;i<nsLatestnewslists.size();i++){ NsLatestnewsEntity
	 * nsLatestnewsEntity = nsLatestnewslists.get(i);
	 * //构造news_webpage数据和news_content_new数据. NewsWebpageEntity
	 * newsWebpageEntity =
	 * dealMapperImpl.generateNewsWebpage(nsLatestnewsEntity,nsWebpageDao);
	 * NewsContentNewEntity newsContentNewEntity =
	 * dealMapperImpl.generateNewsContentNewEntity(nsLatestnewsEntity,
	 * nsWebpageDao,newsWebpageEntity);
	 * 
	 * //如果newsWebpageEntity为空，说明列表中有数据，而内容中没有数据，不需要插入新表中。 if(null !=
	 * newsWebpageEntity ){ //插入news_webpage数据和news_content_new数据. try{
	 * 
	 * newsWebpageDao.insert(newsWebpageEntity);
	 * newsContentNewDao.insert(newsContentNewEntity);
	 * System.out.println("webpage插入中");
	 * 
	 * //批量插入 newsWebpageEntityLists.add(newsWebpageEntity);
	 * newsContentNewEntityLists.add(newsContentNewEntity);
	 * }catch(PersistenceException e){
	 * 
	 * e.printStackTrace(); return; }
	 * 
	 * }else{ //System.out.println("webpage为null" +"  "+ count++); } }
	 * 
	 * //每页都进行批量插入 if(newsWebpageEntityLists.size() > 0){ try{
	 * newsWebpageDao.insertBatch(newsWebpageEntityLists);
	 * newsContentNewDao.insertBatch(newsContentNewEntityLists);
	 * }catch(PersistenceException e){ //重复和超长度异常处理 //重复插入webpage_url，记录处理。
	 * //写入日志文件 e.printStackTrace(); Logger logger = null;
	 * logger=Logger.getLogger(NewsWebpageAndContent.class.getName());
	 * logger.setLevel(Level.ERROR);
	 * System.out.println("list  size"+" "+newsWebpageEntityLists.size());
	 * for(int i=0;i<newsWebpageEntityLists.size();i++){
	 * System.out.println(newsWebpageEntityLists.get(i).getWebpageurl()); }
	 * return; }
	 * 
	 * System.out.println("插入数据"); } //每一页插入一下。
	 * //session_news.flushStatements(); session_news.commit();
	 * //session_ns.flushStatements(); session_ns.commit();
	 * System.out.println("大小"+newsWebpageEntityLists.size()+" "+"currentpage" +
	 * " "+currentpage); currentpage++; begin = (currentpage-1)*pagesize; }
	 * session_news.close(); session_ns.close(); }
	 */

	/**
	 * 以ns_wenpage表为基表进行转移。
	 */
	public static void exportDataBasicContent(int currentpage) {
		NewsWebpageAndContent dealMapperImpl = new NewsWebpageAndContent();

		// 读出ns_latest_news数据。
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
		
		
		// 分页查询
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
					// 重复的webpage_url
					System.out.println("重复  webpage_url" + "  " + nsWebpageEntity.getWebpageurl());
					
					//continue;
				}else if(latestnewslists.size() == 0){
					continue;
				}
				// 构造news_webpage数据和news_content_new数据.
				NewsWebpageEntity newsWebpageEntity = dealMapperImpl.generateNewsWebpage(latestnewslists.get(0),
						nsWebpageEntity);

				NewsContentNewEntity newsContentNewEntity = dealMapperImpl.generateNewsContentNewEntity(nsWebpageEntity,
						newsWebpageEntity);

				// 如果newsWebpageEntity为空，说明列表中有数据，而内容中没有数据，不需要插入新表中。
				if (null != newsWebpageEntity) {
					// 插入news_webpage数据和news_content_new数据.
					try {

						/*
						 * newsWebpageDao.insert(newsWebpageEntity);
						 * newsContentNewDao.insert(newsContentNewEntity);
						 * System.out.println("webpage插入中");
						 */

						// 批量插入
						if(null != newsWebpageEntity.getWebpagecode()){
							newsWebpageEntityLists.add(newsWebpageEntity);
							newsContentNewEntityLists.add(newsContentNewEntity);
						}
					} catch (PersistenceException e) {

						e.printStackTrace();
						return;
					}

				} else {
					// System.out.println("webpage为null" +" "+ count++);
				}
			}

			// 每页都进行批量插入
			if (newsWebpageEntityLists.size() > 0) {
				try {
					newsWebpageDao.insertBatch(newsWebpageEntityLists);
					newsContentNewDao.insertBatch(newsContentNewEntityLists);
				} catch (PersistenceException e) {
					// 重复和超长度异常处理
					// 重复插入webpage_url，记录处理。
					// 写入日志文件
					
					System.out.println("重复");
				}

				System.out.println("插入数据");
			}
			// 每一页插入一下。
			// session_news.flushStatements();
			session_news.commit();
			// session_ns.flushStatements();
			session_ns.commit();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currenttimestr = sd.format(currenttime);
			System.out.println("大小" + newsWebpageEntityLists.size() + " " + "currenttime" + " " + currenttimestr);
			currentpage++;
			begin = (currentpage - 1) * pagesize;
			currenttime = endtime;
		}
		session_news.close();
		session_ns.close();

	}

}
