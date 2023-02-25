package com.run.arch.dao;

import com.run.arch.entity.Project;

import java.util.List;

public interface ProjectDao {
    List<Project> getAllProjectList() throws Exception;

    int addProject(Project project) throws Exception;

    int modifyProject(Project project) throws Exception;

    int removeProjectById(String id) throws Exception;

    Project getProjectById(String id) throws Exception;

    List<Project> getProjectByLikeProjectName(String projectName) throws Exception;

    List<Project> getProjectListByTeacherId(String teacherId) throws Exception;
}
