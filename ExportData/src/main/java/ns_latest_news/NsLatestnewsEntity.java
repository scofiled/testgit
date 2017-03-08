package ns_latest_news;

import java.sql.Timestamp;

public class NsLatestnewsEntity {
	private Integer innerid;
	private String title;
	private Timestamp crawldatetime;
	private Timestamp releasedatetime;
	private String sourceweb;
	private String classification;
	private String cusclassification;
	private String webpageurl;
	private Integer hassamenews;
	
	private String samenewsset;
	private Integer hassimilarnews;
	private String similarnewsset;
	private Integer iscrawled;
	public Integer getInnerid() {
		return innerid;
	}
	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getCrawldatetime() {
		return crawldatetime;
	}
	public void setCrawldatetime(Timestamp crawldatetime) {
		this.crawldatetime = crawldatetime;
	}
	public Timestamp getReleasedatetime() {
		return releasedatetime;
	}
	public void setReleasedatetime(Timestamp releasedatetime) {
		this.releasedatetime = releasedatetime;
	}
	public String getSourceweb() {
		return sourceweb;
	}
	public void setSourceweb(String sourceweb) {
		this.sourceweb = sourceweb;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getCusclassification() {
		return cusclassification;
	}
	public void setCusclassification(String cusclassification) {
		this.cusclassification = cusclassification;
	}
	public String getWebpageurl() {
		return webpageurl;
	}
	public void setWebpageurl(String webpageurl) {
		this.webpageurl = webpageurl;
	}
	public Integer getHassamenews() {
		return hassamenews;
	}
	public void setHassamenews(Integer hassamenews) {
		this.hassamenews = hassamenews;
	}
	public String getSamenewsset() {
		return samenewsset;
	}
	public void setSamenewsset(String samenewsset) {
		this.samenewsset = samenewsset;
	}
	public Integer getHassimilarnews() {
		return hassimilarnews;
	}
	public void setHassimilarnews(Integer hassimilarnews) {
		this.hassimilarnews = hassimilarnews;
	}
	public String getSimilarnewsset() {
		return similarnewsset;
	}
	public void setSimilarnewsset(String similarnewsset) {
		this.similarnewsset = similarnewsset;
	}
	public Integer getIscrawled() {
		return iscrawled;
	}
	public void setIscrawled(Integer iscrawled) {
		this.iscrawled = iscrawled;
	}
	

}
