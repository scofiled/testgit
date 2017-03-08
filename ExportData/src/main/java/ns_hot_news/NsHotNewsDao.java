package ns_hot_news;

import java.util.List;

import org.apache.ibatis.annotations.Param;



public interface NsHotNewsDao {
	public List<NsHotNewsEntity> selectAll(@Param("begin")int begin,@Param("pagesize")int pagesize);
	public int countAll();
	public List<String> findWebpageUrlByuid(String uid);
}
