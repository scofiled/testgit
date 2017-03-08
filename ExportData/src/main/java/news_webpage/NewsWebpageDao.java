package news_webpage;

import java.util.List;

public interface NewsWebpageDao {
	public int insert(NewsWebpageEntity newsWebpageEntity);
	
	//��������
	public int insertBatch(List<NewsWebpageEntity> newsWebpageEntityLists);
	public NewsWebpageEntity findByWebpageUrl(String webpageurl);
	
	public int countAll();
}
