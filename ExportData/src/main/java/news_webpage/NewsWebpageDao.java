package news_webpage;

import java.util.List;

public interface NewsWebpageDao {
	public int insert(NewsWebpageEntity newsWebpageEntity);
	
	//ÅúÁ¿²åÈë
	public int insertBatch(List<NewsWebpageEntity> newsWebpageEntityLists);
	public NewsWebpageEntity findByWebpageUrl(String webpageurl);
	
	public int countAll();
}
