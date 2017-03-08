package ns_webpage;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.User;



public interface NsWebpageDao {
	public List<NsWebpageEntity> selectAll(@Param("begin")int begin,@Param("pagesize")int pagesize);
	public NsWebpageEntity findByWebpageUrl(String webpageurl);
	
	//按照时间查询数据
	public List<NsWebpageEntity> selectBycrawldatetime(@Param("starttime") Timestamp starttime,@Param("endtime") Timestamp endtime);
	
	//查询最大时间和最小时间
	public NsWebpageEntity getMintime();
	public NsWebpageEntity getMaxtime();
	
	public int countAll();
}
