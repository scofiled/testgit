package news_comment;

import java.sql.Timestamp;

public class NewsCommentEntity {
	private Integer innerid;
	private String commentid;
	private Timestamp crawldatetime;
	private String releasedatetimestr;
	private Timestamp releasedatetime;
	private Timestamp updatedatetime;
	
	private String buildlevel;
	private Integer vote;
	private Integer against;
	private String content;
	private String postid;
	private String sourcetype;
	private String userid; 
	private String userlocation;
	private String username;
	private String useridnew;
	private String productkey;
	private String sitename;
	private String ip;
	private String currenturl;
	private String webpagecode;
	private String pageno;
	private String parent;
	private String thread;
	private Integer isuseless;
	private Integer isdeleted;
	private String sentiment;
	public Integer getInnerid() {
		return innerid;
	}
	public void setInnerid(Integer innerid) {
		this.innerid = innerid;
	}
	public String getCommentid() {
		return commentid;
	}
	public void setCommentid(String commentid) {
		this.commentid = commentid;
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
	public String getBuildlevel() {
		return buildlevel;
	}
	public void setBuildlevel(String buildlevel) {
		this.buildlevel = buildlevel;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public String getSourcetype() {
		return sourcetype;
	}
	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserlocation() {
		return userlocation;
	}
	public void setUserlocation(String userlocation) {
		this.userlocation = userlocation;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseridnew() {
		return useridnew;
	}
	public void setUseridnew(String useridnew) {
		this.useridnew = useridnew;
	}
	public String getProductkey() {
		return productkey;
	}
	public void setProductkey(String productkey) {
		this.productkey = productkey;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCurrenturl() {
		return currenturl;
	}
	public void setCurrenturl(String currenturl) {
		this.currenturl = currenturl;
	}
	public String getWebpagecode() {
		return webpagecode;
	}
	public void setWebpagecode(String webpagecode) {
		this.webpagecode = webpagecode;
	}
	public String getPageno() {
		return pageno;
	}
	public void setPageno(String pageno) {
		this.pageno = pageno;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getThread() {
		return thread;
	}
	public void setThread(String thread) {
		this.thread = thread;
	}
	public Integer getIsuseless() {
		return isuseless;
	}
	public void setIsuseless(Integer isuseless) {
		this.isuseless = isuseless;
	}
	public Integer getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
}
