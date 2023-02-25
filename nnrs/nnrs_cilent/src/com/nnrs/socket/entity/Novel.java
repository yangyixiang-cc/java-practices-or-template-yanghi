package com.nnrs.socket.entity;

public class Novel implements java.io.Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5406456459579391675L;
	private String novelID;
	private String novelName;
	private int chapterNum;
	private int visitNum;
	public Novel() {}
	public Novel(String novelID, String novelName, int chapterNum, int visitNum) {
		super();
		this.novelID = novelID;
		this.novelName = novelName;
		this.chapterNum = chapterNum;
		this.visitNum = visitNum;
	}
	public String getNovelID() {
		return novelID;
	}
	public void setNovelID(String novelID) {
		this.novelID = novelID;
	}
	public String getNovelName() {
		return novelName;
	}
	public void setNovelName(String novelName) {
		this.novelName = novelName;
	}
	public int getChapterNum() {
		return chapterNum;
	}
	public void setChapterNum(int chapterNum) {
		this.chapterNum = chapterNum;
	}
	public int getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(int visitNum) {
		this.visitNum = visitNum;
	}
	@Override
	public String toString() {
		return "Novel [novelID=" + novelID + ", novelName=" + novelName + ", chapterNum=" + chapterNum + ", visitNum="
				+ visitNum + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chapterNum;
		result = prime * result + ((novelID == null) ? 0 : novelID.hashCode());
		result = prime * result + ((novelName == null) ? 0 : novelName.hashCode());
		result = prime * result + visitNum;
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
		Novel other = (Novel) obj;
		if (chapterNum != other.chapterNum)
			return false;
		if (novelID == null) {
			if (other.novelID != null)
				return false;
		} else if (!novelID.equals(other.novelID))
			return false;
		if (novelName == null) {
			if (other.novelName != null)
				return false;
		} else if (!novelName.equals(other.novelName))
			return false;
		if (visitNum != other.visitNum)
			return false;
		return true;
	}
	
}
