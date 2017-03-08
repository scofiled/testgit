package ns_hot_news;

import java.sql.Timestamp;

public class NsHotNewsEntity {
	private Integer innerid;
	private String title;
	private Integer rankingnum;
	private Integer commentnum;
	private Integer participatenum;
	private Integer clickingnum;
	private String rankingcycle;
	private String sourceweb;
	private String classification;
	private Timestamp crawldatetime;
	private String releasedatetime;
	private String webpageurl;
	private String uid;
	private Integer hassamenews;
	private String samenewsset;
	private Integer hassimilarnews;
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
	public void setClickingnum(Integer clickingnum) {
		this.clickingnum = clickingnum;
	}
	private String similarnewsset;
	private Integer iscrawled;
	private String keywords;
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
	public Integer getRankingnum() {
		return rankingnum;
	}
	public void setRankingnum(Integer rankingnum) {
		this.rankingnum = rankingnum;
	}
	public Integer getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(Integer commentnum) {
		this.commentnum = commentnum;
	}
	public Integer getParticipatenum() {
		return participatenum;
	}
	public void setParticipatenum(Integer participatenum) {
		this.participatenum = participatenum;
	}
	public Integer getClickingnum() {
		return clickingnum;
	}
	public void setClinkingnum(Integer clickingnum) {
		this.clickingnum = clickingnum;
	}
	public String getRankingcycle() {
		return rankingcycle;
	}
	public void setRankingcycle(String rankingcycle) {
		this.rankingcycle = rankingcycle;
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
	public Timestamp getCrawldatetime() {
		return crawldatetime;
	}
	public void setCrawldatetime(Timestamp crawldatetime) {
		this.crawldatetime = crawldatetime;
	}
	public String getReleasedatetime() {
		return releasedatetime;
	}
	public void setReleasedatetime(String releasedatetime) {
		this.releasedatetime = releasedatetime;
	}
	public String getWebpageurl() {
		return webpageurl;
	}
	public void setWebpageurl(String webpageurl) {
		this.webpageurl = webpageurl;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getHassamenews() {
		return hassamenews;
	}
	public void setHassamenews(Integer hassamenews) {
		this.hassamenews = hassamenews;
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
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
