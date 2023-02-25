package com.nnrs.socket.entity;
/*
 * 用户阅读书籍历史
 */
public class History implements java.io.Serializable{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8216870834156391335L;
	private String novelId;
	private String novelName;
	private String readChapter;
	public History() {
		
	}
	public History(String novelId, String novelName, String readChapter) {
		super();
		this.novelId = novelId;
		this.novelName = novelName;
		this.readChapter = readChapter;
	}
	public String getNovelId() {
		return novelId;
	}
	public void setNovelId(String novelId) {
		this.novelId = novelId;
	}
	public String getNovelName() {
		return novelName;
	}
	public void setNovelName(String novelName) {
		this.novelName = novelName;
	}
	public String getReadChapter() {
		return readChapter;
	}
	public void setReadChapter(String readChapter) {
		this.readChapter = readChapter;
	}
	@Override
	public String toString() {
		return "History [novelId=" + novelId + ", novelName=" + novelName + ", readChapter=" + readChapter + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((novelId == null) ? 0 : novelId.hashCode());
		result = prime * result + ((novelName == null) ? 0 : novelName.hashCode());
		result = prime * result + ((readChapter == null) ? 0 : readChapter.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		History other = (History) obj;
		if (novelId == null) {
			if (other.novelId != null)
				return false;
		} else if (!novelId.equals(other.novelId))
			return false;
		if (novelName == null) {
			if (other.novelName != null)
				return false;
		} else if (!novelName.equals(other.novelName))
			return false;
		if (readChapter == null) {
			if (other.readChapter != null)
				return false;
		} else if (!readChapter.equals(other.readChapter))
			return false;
		return true;
	}
	
}
