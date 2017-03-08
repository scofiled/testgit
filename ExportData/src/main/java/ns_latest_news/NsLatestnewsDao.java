package ns_latest_news;

import java.util.List;

import org.apache.ibatis.annotations.Param;



public interface NsLatestnewsDao {
	public List<NsLatestnewsEntity> selectAll(@Param("begin")int begin,@Param("pagesize")int pagesize);
	public int countAll();
	public List<NsLatestnewsEntity> findByWebpageUrl(String webpageurl);
}	
