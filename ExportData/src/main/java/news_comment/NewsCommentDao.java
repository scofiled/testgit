package news_comment;

import java.util.List;



public interface NewsCommentDao {
	public int insert(NewsCommentEntity newsCommentEntity);
	//��������
	public int insertBatch(List<NewsCommentEntity> newsCommentEntityLists);
}
