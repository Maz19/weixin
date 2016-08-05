package com.mazheng.jingxuan;

public class NewsList {
	private String title;
	private String description;
	private String picUrl;
	private String url;
	private String ctime;
	public NewsList(String title, String description, String picUrl,
			String url, String ctime) {
		super();
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
		this.ctime = ctime;
	}
	public NewsList() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	@Override
	public String toString() {
		return "NewsList [title=" + title + ", description=" + description
				+ ", picUrl=" + picUrl + ", url=" + url + ", ctime=" + ctime
				+ "]";
	}

	
}
