package ns_web_comment;

import java.util.List;

import org.apache.ibatis.annotations.Param;



public interface NsWebCommentDao {
	public List<NsWebCommentEntity> selectAll(@Param("begin")int begin,@Param("end")int end);
	public int countAll();
}
