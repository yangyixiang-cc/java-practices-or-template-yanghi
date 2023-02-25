package com.run.arch.service;

import com.run.arch.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> searchProjectByKeyword(String keyword);

    List<Project> showAllProject();

    List<Project> showAllProjectByTeacherId(String teacherId);

    int declareProject(Project project);

    int reviewProject(String id, String flog, String reviewComments);

    Project getProjectById(String id);
}
