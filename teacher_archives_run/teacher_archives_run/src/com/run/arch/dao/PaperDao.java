package com.run.arch.dao;

import com.run.arch.entity.Paper;

import java.util.List;

public interface PaperDao {
    List<Paper> getAllPaperList() throws Exception;

    int addPaper(Paper paper) throws Exception;

    int modifyPaper(Paper paper) throws Exception;

    int removePaperById(String id) throws Exception;

    Paper getPaperById(String id) throws Exception;

    List<Paper> getPaperByLikePaperName(String paperName) throws Exception;

    List<Paper> getPaperListByTeacherId(String teacherId) throws Exception;
}
