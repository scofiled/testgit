package news_content_new;

import java.sql.Timestamp;

public class NewsContentNewEntity {
	private Integer innerid;
	private String webpagecode;
	private String content;
	
	private String notagcontent;
	private String description;
	private Timestamp createdatetime;
	
	
	public Integer getInnerid() {
		return innerid;
	}
	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}
	public String getWebpagecode() {
		return webpagecode;
	}
	public void setWebpagecode(String webpagecode) {
		this.webpagecode = webpagecode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNotagcontent() {
		return notagcontent;
	}
	public void setNotagcontent(String notagcontent) {
		this.notagcontent = notagcontent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Timestamp createdatetime) {
		this.createdatetime = createdatetime;
	}

}
