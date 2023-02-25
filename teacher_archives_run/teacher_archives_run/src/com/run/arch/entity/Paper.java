package com.run.arch.entity;

import com.run.arch.util.NumberUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author 泗安
 * 论文类
 */
public class Paper {

    private String id;

    private String paperName;

    private Date pubTime;

    private String pubJournal;

    private String journalLevel;

    private String index;

    private String teacherId;

    private Date reviewTime;

    private Integer flog;

    private String reviewComments;

    public Paper() {
    }

    public Paper(String id, String paperName, Date pubTime, String pubJournal, String journalLevel, String index, String teacherId, Date reviewTime, Integer flog, String reviewComments) {
        this.id = id;
        this.paperName = paperName;
        this.pubTime = pubTime;
        this.pubJournal = pubJournal;
        this.journalLevel = journalLevel;
        this.index = index;
        this.teacherId = teacherId;
        this.reviewTime = reviewTime;
        this.flog = flog;
        this.reviewComments = reviewComments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getPubJournal() {
        return pubJournal;
    }

    public void setPubJournal(String pubJournal) {
        this.pubJournal = pubJournal;
    }

    public String getJournalLevel() {
        return journalLevel;
    }

    public void setJournalLevel(String journalLevel) {
        this.journalLevel = journalLevel;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Integer getFlog() {
        return flog;
    }

    public void setFlog(Integer flog) {
        this.flog = flog;
    }

    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Paper paper = (Paper) o;
        return Objects.equals(id, paper.id) && Objects.equals(paperName, paper.paperName) && Objects.equals(pubTime, paper.pubTime) && Objects.equals(pubJournal, paper.pubJournal) && Objects.equals(journalLevel, paper.journalLevel) && Objects.equals(index, paper.index) && Objects.equals(teacherId, paper.teacherId) && Objects.equals(reviewTime, paper.reviewTime) && Objects.equals(flog, paper.flog) && Objects.equals(reviewComments, paper.reviewComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paperName, pubTime, pubJournal, journalLevel, index, teacherId, reviewTime, flog, reviewComments);
    }

    @Override
    public String toString() {
        String text = "审核中";
        if(flog == 1){
            text = "审核通过";
        }else if(flog == 2){
            text = "审核失败";
        }
        return "ID：'" + id + '\'' +
                ", 论文名称：'" + paperName + '\'' +
                ", 发表时间：" + pubTime +
                ", 发表期刊：'" + pubJournal + '\'' +
                ", 期刊级别：'" + journalLevel + '\'' +
                ", 索引情况：'" + index + '\'' +
                ", 教师ID：'" + teacherId + '\'' +
                ", 审核时间：" + NumberUtil.dateToStr(reviewTime) +
                ", 审核状态：" + text +
                ", 审核意见：'" + reviewComments + '\'';
    }
}
