package news_hot;

import java.sql.Timestamp;

public class NewsHotEntity {
	private Integer innerid;
	private String title;
	private Integer rankingnum;
	private Integer commentnum;
	private Integer participatenum;
	private Integer clickingnum;
	private Float heatnum;
	private Integer rankingtype;
	private Integer rankingcycle;
	private Integer sourceweb;
	private String classification;
	private Integer cusclassification;
	private Timestamp crawldatetime;
	private String releasedatetimestr;
	private Timestamp releasedatetime;
	private Timestamp updatedatetime;
	private String webpagecode;
	private String webpageurl;
	private Integer isdeleted;
	private Integer repostscount;
	
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
	public void setClickingnum(Integer clickingnum) {
		this.clickingnum = clickingnum;
	}
	public Float getHeatnum() {
		return heatnum;
	}
	public void setHeatnum(Float heatnum) {
		this.heatnum = heatnum;
	}
	public Integer getRankingtype() {
		return rankingtype;
	}
	public void setRankingtype(Integer rankingtype) {
		this.rankingtype = rankingtype;
	}
	public Integer getRankingcycle() {
		return rankingcycle;
	}
	public void setRankingcycle(Integer rankingcycle) {
		this.rankingcycle = rankingcycle;
	}
	public Integer getSourceweb() {
		return sourceweb;
	}
	public void setSourceweb(Integer sourceweb) {
		this.sourceweb = sourceweb;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public Integer getCusclassification() {
		return cusclassification;
	}
	public void setCusclassification(Integer cusclassification) {
		this.cusclassification = cusclassification;
	}
	public Timestamp getCrawldatetime() {
		return crawldatetime;
	}
	public void setCrawldatetime(Timestamp crawldatetime) {
		this.crawldatetime = crawldatetime;
	}
	public String getReleasedatetimestr() {
		return releasedatetimestr;
	}
	public void setReleasedatetimestr(String releasedatetimestr) {
		this.releasedatetimestr = releasedatetimestr;
	}
	public Timestamp getReleasedatetime() {
		return releasedatetime;
	}
	public void setReleasedatetime(Timestamp releasedatetime) {
		this.releasedatetime = releasedatetime;
	}
	public Timestamp getUpdatedatetime() {
		return updatedatetime;
	}
	public void setUpdatedatetime(Timestamp updatedatetime) {
		this.updatedatetime = updatedatetime;
	}
	public String getWebpagecode() {
		return webpagecode;
	}
	public void setWebpagecode(String webpagecode) {
		this.webpagecode = webpagecode;
	}
	public String getWebpageurl() {
		return webpageurl;
	}
	public void setWebpageurl(String webpageurl) {
		this.webpageurl = webpageurl;
	}
	public Integer getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}
	public Integer getRepostscount() {
		return repostscount;
	}
	public void setRepostscount(Integer repostscount) {
		this.repostscount = repostscount;
	}

	
}
