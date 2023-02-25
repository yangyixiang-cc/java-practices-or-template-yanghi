package com.run.arch.service;

import com.run.arch.entity.Paper;

import java.util.List;

public interface PaperService {

    List<Paper> searchPaperByKeyword(String keyword);

    int reviewPaper(String id,String flog, String reviewComments);

    List<Paper> showAllPapers();

    int declarePaper(Paper paper);

    List<Paper> showAllPaperByTeacherId(String teacherId);

    Paper getPaperById(String id);

}
