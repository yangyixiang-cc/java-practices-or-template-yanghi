package com.run.arch.service.Impl;

import com.run.arch.dao.Impl.ProjectDaoImpl;
import com.run.arch.dao.ProjectDao;
import com.run.arch.entity.Project;
import com.run.arch.service.ProjectService;
import com.run.arch.util.NumberUtil;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProjectServiceImpl implements ProjectService {

    private ProjectDao projectDao = new ProjectDaoImpl();

    @Override
    public List<Project> searchProjectByKeyword(String keyword) {
        try {
            Project projectById = projectDao.getProjectById(keyword);
            List<Project> projectByLikeProjectName = projectDao.getProjectByLikeProjectName(keyword);
            if(!Objects.isNull(projectById)){
                projectByLikeProjectName.add(projectById);
            }
            return projectByLikeProjectName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project> showAllProject() {
        try {
            return projectDao.getAllProjectList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project> showAllProjectByTeacherId(String teacherId) {
        try {
            return projectDao.getProjectListByTeacherId(teacherId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int declareProject(Project project) {
        project.setId(NumberUtil.getGeneratID().toString());
        project.setReviewTime(NumberUtil.strToDate("1949-10-01"));
        try {
            return projectDao.addProject(project);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int reviewProject(String id, String flog, String reviewComments) {
        try {
            Project project = projectDao.getProjectById(id);
            if(Objects.isNull(project)){
                return -1;
            }
            if(project.getFlog() != 1){
                project.setFlog(Integer.valueOf(flog));
                project.setReviewComments(reviewComments);
                project.setReviewTime(new Date());
                return projectDao.modifyProject(project) == 1 ? 2:0;
            }else if(project.getFlog() == 1){
                return 1;
            } else{
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Project getProjectById(String id) {
        try {
            return projectDao.getProjectById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
