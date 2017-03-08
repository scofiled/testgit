package news_comment;

import java.util.List;



public interface NewsCommentDao {
	public int insert(NewsCommentEntity newsCommentEntity);
	//ÅúÁ¿²åÈë
	public int insertBatch(List<NewsCommentEntity> newsCommentEntityLists);
}
