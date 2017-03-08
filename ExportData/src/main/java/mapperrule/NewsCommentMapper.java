package mapperrule;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import datautil.SessionUtil;
import news_comment.NewsCommentDao;
import news_comment.NewsCommentEntity;
import news_content_new.NewsContentNewEntity;
import news_hot.NewsHotDao;
import news_hot.NewsHotEntity;
import news_webpage.NewsWebpageDao;
import news_webpage.NewsWebpageEntity;
import ns_hot_news.NsHotNewsDao;
import ns_hot_news.NsHotNewsEntity;
import ns_web_comment.NsWebCommentDao;
import ns_web_comment.NsWebCommentEntity;

public class NewsCommentMapper {
	
	/**
	 * 生成news_comment表中的实体数据
	 * @return
	 */
	public NewsCommentEntity generateNewsComment(NsWebCommentEntity nsWebCommentEntity,NsHotNewsDao nsHotNewsDao,NewsWebpageDao newsWebpageDao){
		NewsCommentEntity newsCommentEntity = new NewsCommentEntity();
		newsCommentEntity.setCommentid(nsWebCommentEntity.getCommentid());
		newsCommentEntity.setCrawldatetime(nsWebCommentEntity.getCrawldatetime());
		newsCommentEntity.setReleasedatetime(nsWebCommentEntity.getReleasedatetime());
		//设置release_datetime_str
		Timestamp releasedatetime = newsCommentEntity.getReleasedatetime();
		if (null == releasedatetime) {
			newsCommentEntity.setReleasedatetime(newsCommentEntity.getCrawldatetime());
			Timestamp crawldatetime = newsCommentEntity.getCrawldatetime();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String crawldatetimestr = sd.format(crawldatetime);
			newsCommentEntity.setReleasedatetimestr(crawldatetimestr);
		}
		//设置update
		Timestamp updatedatetime = new Timestamp(System.currentTimeMillis());
		newsCommentEntity.setUpdatedatetime(updatedatetime);
		
		newsCommentEntity.setBuildlevel(nsWebCommentEntity.getBuildlevel());
		newsCommentEntity.setVote(nsWebCommentEntity.getVote());
		newsCommentEntity.setAgainst(nsWebCommentEntity.getAgainst());
		newsCommentEntity.setContent(nsWebCommentEntity.getContent());
		newsCommentEntity.setPostid(nsWebCommentEntity.getPostid());
		newsCommentEntity.setSourcetype(nsWebCommentEntity.getSourcetype());
		newsCommentEntity.setUserid(nsWebCommentEntity.getUserid());
		newsCommentEntity.setUserlocation(nsWebCommentEntity.getUserlocation());
		newsCommentEntity.setUsername(nsWebCommentEntity.getUsername());
		newsCommentEntity.setUseridnew(nsWebCommentEntity.getUseridnew());
		newsCommentEntity.setProductkey(nsWebCommentEntity.getProductkey());
		newsCommentEntity.setSitename(nsWebCommentEntity.getSitename());
		newsCommentEntity.setIp(nsWebCommentEntity.getIp());
		newsCommentEntity.setCurrenturl(nsWebCommentEntity.getCurrenturl());
		//webpagecode
		String webpagecode = null;
		webpagecode = this.findwebpagecode(nsWebCommentEntity.getUid(),nsHotNewsDao,newsWebpageDao);
		newsCommentEntity.setWebpagecode(webpagecode);
		newsCommentEntity.setPageno(nsWebCommentEntity.getPageno());
		newsCommentEntity.setParent(nsWebCommentEntity.getParent());
		newsCommentEntity.setThread(nsWebCommentEntity.getThread());
		newsCommentEntity.setIsuseless(0);
		newsCommentEntity.setIsdeleted(0);
		
		return newsCommentEntity;
	}
	
	/**
	 * 查找webpagecode
	 */
	public String findwebpagecode(String uid,NsHotNewsDao nsHotNewsDao,NewsWebpageDao newsWebpageDao){
		String webpagecode = null;
		List<String> webpageurls = nsHotNewsDao.findWebpageUrlByuid(uid);
		String webpageurl = null;
		if(null != webpageurls && webpageurls.size()>0){
			webpageurl = webpageurls.get(0);
			if(null != webpageurl){
				 NewsWebpageEntity entity =  newsWebpageDao.findByWebpageUrl(webpageurl); 
				 if(null != entity){
					 webpagecode  = entity.getWebpagecode();
				 }
			}
		}
		return webpagecode;
	}
	
	
	/**
	 * 导入数据
	 */
	public static  void exportBatch(){
		NewsCommentMapper dealMapperImpl = new NewsCommentMapper();
		
		//读出ns_latest_news数据。
		SqlSession session_ns = SessionUtil.createSession("mybatis-config-ns.xml");
		NsWebCommentDao nsWebCommentDao = session_ns.getMapper(NsWebCommentDao.class);
		NsHotNewsDao nsHotNewsDao = session_ns.getMapper(NsHotNewsDao.class);
		
		SqlSession session_news = SessionUtil.createSession("mybatis-config-news.xml");
		NewsWebpageDao newsWebpageDao = session_news.getMapper(NewsWebpageDao.class);
		NewsCommentDao newsCommentDao = session_news.getMapper(NewsCommentDao.class);
		Integer rows = nsWebCommentDao.countAll();
		
		int begin = 0;
		int end = 0;
		
		int pagesize = 20;
		int totalpage = 0;
		if(rows%pagesize == 0)
			totalpage = rows/pagesize;
		else
			totalpage = rows/pagesize + 1;
		
		int currentpage = 1;
		List<NewsCommentEntity> newsCommentEntityLists = new ArrayList<NewsCommentEntity>();
		//分页查询
		while(end<=rows){
			newsCommentEntityLists.clear();
			end = begin + 2000;
			List<NsWebCommentEntity> nsWebCommentlists = nsWebCommentDao.selectAll(begin,end);
			int count = 0;
			for(int i=0;i<nsWebCommentlists.size();i++){
				NsWebCommentEntity nsWebCommentEntity = nsWebCommentlists.get(i);
				NewsCommentEntity newsCommentEntity = dealMapperImpl.generateNewsComment(nsWebCommentEntity,nsHotNewsDao,newsWebpageDao);
				if(null != newsCommentEntity && null != newsCommentEntity.getWebpagecode()){
					try{
						//newsCommentDao.insert(newsCommentEntity);
						newsCommentEntityLists.add(newsCommentEntity);
						count++;
					}catch(PersistenceException e){
						//重复插入webpage_url，记录处理。
						//写入日志文件
						
						System.out.println("webpage_url重复");
						System.out.println("重复记录webpage_code为"+newsCommentEntity.getWebpagecode());
					}
					
				}
			}
			
			if (newsCommentEntityLists.size() > 0) {
				try {
					newsCommentDao.insertBatch(newsCommentEntityLists);
					System.out.println("插入数据大小"+ " " +newsCommentEntityLists.size()+"当前end值    "+end);
				} catch (PersistenceException e) {
					// 重复和超长度异常处理
					// 重复插入webpage_url，记录处理。
					// 写入日志文件
					//e.printStackTrace();
					//return;
					System.out.println("重复"+"当前end值     "+end);
				}

				//System.out.println("插入数据");
			}
			currentpage++;
			//begin = (currentpage-1)*pagesize;
			begin = end;
			
			//每一页插入一下。
			session_news.commit();	
			session_ns.commit();
		}
		
		
		session_news.close();
		session_ns.close();
	}
}
