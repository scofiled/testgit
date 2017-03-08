package news_webpage;

import java.sql.Timestamp;

public class NewsWebpageEntity {
	private Integer innerid;
	private String webpagecode;

	private String webpageurl;
	private String title;
	private String shorttitle;

	private String keywords;
	private String websummary;

	private String sourcereport;
	private Integer sourcecrawl;
	private Integer region;

	private String classification;
	private String cusclassification;

	private Integer repostscount;
	private Integer commentscount;
	private Integer participatenum;

	private Integer vote;
	private Integer against;
	private Integer browsecount;
	private Integer status;

	private String author;
	private Timestamp crawldatetime;
	private String releasedatetimestr;

	private Timestamp releasedatetime;
	private Timestamp updatedatetime;

	private Integer iscorenews;
	private String corenewscode;
	private Integer similarnum;
	private Integer relevantnum;
	private Integer istopnews;

	private String picpath;
	private Integer isdeleted;
	private Integer isoriginal;
	private Integer isclue;

	private String weiboid;
	private String weibopid;
	private String weiborootid;

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

	public String getWebpageurl() {
		return webpageurl;
	}

	public void setWebpageurl(String webpageurl) {
		this.webpageurl = webpageurl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShorttitle() {
		return shorttitle;
	}

	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getWebsummary() {
		return websummary;
	}

	public void setWebsummary(String websummary) {
		this.websummary = websummary;
	}

	public String getSourcereport() {
		return sourcereport;
	}

	public void setSourcereport(String sourcereport) {
		this.sourcereport = sourcereport;
	}

	public Integer getSourcecrawl() {
		return sourcecrawl;
	}

	public void setSourcecrawl(Integer sourcecrawl) {
		this.sourcecrawl = sourcecrawl;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
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

	public Integer getRepostscount() {
		return repostscount;
	}

	public void setRepostscount(Integer repostscount) {
		this.repostscount = repostscount;
	}

	public Integer getCommentscount() {
		return commentscount;
	}

	public void setCommentscount(Integer commentscount) {
		this.commentscount = commentscount;
	}

	public Integer getParticipatenum() {
		return participatenum;
	}

	public void setParticipatenum(Integer participatenum) {
		this.participatenum = participatenum;
	}

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

	public Integer getAgainst() {
		return against;
	}

	public void setAgainst(Integer against) {
		this.against = against;
	}

	public Integer getBrowsecount() {
		return browsecount;
	}

	public void setBrowsecount(Integer browsecount) {
		this.browsecount = browsecount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public Integer getIscorenews() {
		return iscorenews;
	}

	public void setIscorenews(Integer iscorenews) {
		this.iscorenews = iscorenews;
	}

	public String getCorenewscode() {
		return corenewscode;
	}

	public void setCorenewscode(String corenewscode) {
		this.corenewscode = corenewscode;
	}

	public Integer getSimilarnum() {
		return similarnum;
	}

	public void setSimilarnum(Integer similarnum) {
		this.similarnum = similarnum;
	}

	public Integer getRelevantnum() {
		return relevantnum;
	}

	public void setRelevantnum(Integer relevantnum) {
		this.relevantnum = relevantnum;
	}

	public Integer getIstopnews() {
		return istopnews;
	}

	public void setIstopnews(Integer istopnews) {
		this.istopnews = istopnews;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Integer getIsoriginal() {
		return isoriginal;
	}

	public void setIsoriginal(Integer isoriginal) {
		this.isoriginal = isoriginal;
	}

	public Integer getIsclue() {
		return isclue;
	}

	public void setIsclue(Integer isclue) {
		this.isclue = isclue;
	}

	public String getWeiboid() {
		return weiboid;
	}

	public void setWeiboid(String weiboid) {
		this.weiboid = weiboid;
	}

	public String getWeibopid() {
		return weibopid;
	}

	public void setWeibopid(String weibopid) {
		this.weibopid = weibopid;
	}

	public String getWeiborootid() {
		return weiborootid;
	}

	public void setWeiborootid(String weiborootid) {
		this.weiborootid = weiborootid;
	}

}
