package com.run.arch.entity;

import com.run.arch.util.NumberUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author 泗安
 * 项目类
 */
public class Project {

    private String id;

    private String projectName;

    private String category;

    private String awards;

    private Date createTime;

    private Date endTime;

    private Date reviewTime;

    private Integer flog;

    private String reviewComments;

    private String teacherId;

    public Project() {
    }

    public Project(String id, String projectName, String category, String awards, Date createTime, Date endTime, Date reviewTime, Integer flog, String reviewComments, String teacherId) {
        this.id = id;
        this.projectName = projectName;
        this.category = category;
        this.awards = awards;
        this.createTime = createTime;
        this.endTime = endTime;
        this.reviewTime = reviewTime;
        this.flog = flog;
        this.reviewComments = reviewComments;
        this.teacherId = teacherId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(projectName, project.projectName) && Objects.equals(category, project.category) && Objects.equals(awards, project.awards) && Objects.equals(createTime, project.createTime) && Objects.equals(endTime, project.endTime) && Objects.equals(reviewTime, project.reviewTime) && Objects.equals(flog, project.flog) && Objects.equals(reviewComments, project.reviewComments) && Objects.equals(teacherId, project.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectName, category, awards, createTime, endTime, reviewTime, flog, reviewComments, teacherId);
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
                ", 项目名称：'" + projectName + '\'' +
                ", 项目类别：'" + category + '\'' +
                ", 获奖情况：'" + awards + '\'' +
                ", 创建时间：" + NumberUtil.dateToStr(createTime) +
                ", 结束时间：" + NumberUtil.dateToStr(endTime)+
                ", 教师ID：'" + teacherId + '\''+
                ", 审核时间：" + NumberUtil.dateToStr(reviewTime) +
                ", 审核状态：" + text +
                ", 审核意见：'" + reviewComments + '\'';
    }
}
