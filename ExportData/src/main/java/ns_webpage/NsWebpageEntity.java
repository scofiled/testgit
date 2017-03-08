package ns_webpage;

import java.sql.Time;
import java.sql.Timestamp;

public class NsWebpageEntity {
	private String webpageurl;
	private String title;
	private String shorttitle;
	private String keywords;
	
	private String summary;
	private String originalcontent;
	private String cleaningcontent;
	private String tagcleaningcontent;
	
	private String sourcereport;
	private String sourcecrawl;
	private String region;
	
	private String classification;
	private Integer filetype;
	private String filesize;
	private String address;
	
	private Integer browsecount;
	private Integer downloadcount;
	private String label;
	private String author;
	
	private Timestamp crawldatetime;
	private String releasedatetime;
	private String relevantnewsid;
	private String downwyid;
	private String picpath;
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getOriginalcontent() {
		return originalcontent;
	}
	public void setOriginalcontent(String originalcontent) {
		this.originalcontent = originalcontent;
	}
	public String getCleaningcontent() {
		return cleaningcontent;
	}
	public void setCleaningcontent(String cleaningcontent) {
		this.cleaningcontent = cleaningcontent;
	}
	public String getTagcleaningcontent() {
		return tagcleaningcontent;
	}
	public void setTagcleaningcontent(String tagcleaningcontent) {
		this.tagcleaningcontent = tagcleaningcontent;
	}
	public String getSourcereport() {
		return sourcereport;
	}
	public void setSourcereport(String sourcereport) {
		this.sourcereport = sourcereport;
	}
	public String getSourcecrawl() {
		return sourcecrawl;
	}
	public void setSourcecrawl(String sourcecrawl) {
		this.sourcecrawl = sourcecrawl;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public Integer getFiletype() {
		return filetype;
	}
	public void setFiletype(Integer filetype) {
		this.filetype = filetype;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getBrowsecount() {
		return browsecount;
	}
	public void setBrowsecount(Integer browsecount) {
		this.browsecount = browsecount;
	}
	public Integer getDownloadcount() {
		return downloadcount;
	}
	public void setDownloadcount(Integer downloadcount) {
		this.downloadcount = downloadcount;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
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
	public String getReleasedatetime() {
		return releasedatetime;
	}
	public void setReleasedatetime(String releasedatetime) {
		this.releasedatetime = releasedatetime;
	}
	public String getRelevantnewsid() {
		return relevantnewsid;
	}
	public void setRelevantnewsid(String relevantnewsid) {
		this.relevantnewsid = relevantnewsid;
	}
	public String getDownwyid() {
		return downwyid;
	}
	public void setDownwyid(String downwyid) {
		this.downwyid = downwyid;
	}
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

}
