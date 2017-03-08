package news_content_new;

import java.util.List;

import news_webpage.NewsWebpageEntity;

public interface NewsContentNewDao {
	public int insert(NewsContentNewEntity newsContentNewEntity);
	//ÅúÁ¿²åÈë
	public int insertBatch(List<NewsContentNewEntity> newsContentNewEntityLists);
}
