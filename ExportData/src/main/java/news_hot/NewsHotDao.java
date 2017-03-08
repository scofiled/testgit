package news_hot;

import java.util.List;

import news_content_new.NewsContentNewEntity;

public interface NewsHotDao {
	public int insert(NewsHotEntity newsHotEntity);
	//≈˙¡ø≤Â»Î
	public int insertBatch(List<NewsHotEntity> newsHotEntityLists);
}
