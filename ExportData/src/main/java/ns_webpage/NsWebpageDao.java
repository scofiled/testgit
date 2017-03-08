package ns_webpage;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.User;



public interface NsWebpageDao {
	public List<NsWebpageEntity> selectAll(@Param("begin")int begin,@Param("pagesize")int pagesize);
	public NsWebpageEntity findByWebpageUrl(String webpageurl);
	
	//����ʱ���ѯ����
	public List<NsWebpageEntity> selectBycrawldatetime(@Param("starttime") Timestamp starttime,@Param("endtime") Timestamp endtime);
	
	//��ѯ���ʱ�����Сʱ��
	public NsWebpageEntity getMintime();
	public NsWebpageEntity getMaxtime();
	
	public int countAll();
}
