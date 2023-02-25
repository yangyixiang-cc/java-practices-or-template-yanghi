package com.run.arch.service.Impl;

import com.run.arch.dao.Impl.PaperDaoimpl;
import com.run.arch.dao.PaperDao;
import com.run.arch.entity.Paper;
import com.run.arch.service.PaperService;
import com.run.arch.util.NumberUtil;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PaperServiceImpl implements PaperService {

    private PaperDao paperDao = new PaperDaoimpl();

    @Override
    public List<Paper> searchPaperByKeyword(String keyword) {
        try {
            Paper paperById = paperDao.getPaperById(keyword);
            List<Paper> paperByLikePaperName = paperDao.getPaperByLikePaperName(keyword);
            if(!Objects.isNull(paperById)){
                paperByLikePaperName.add(paperById);
            }
            return paperByLikePaperName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int reviewPaper(String id, String flog, String reviewComments) {
        try {
            Paper paperById = paperDao.getPaperById(id);
            if(Objects.isNull(paperById)){
                return -1;
            }
            if(paperById.getFlog() != 1){
                paperById.setFlog(Integer.valueOf(flog));
                paperById.setReviewComments(reviewComments);
                paperById.setReviewTime(new Date());
                return paperDao.modifyPaper(paperById) == 1 ? 2 : 0;
            }else if(paperById.getFlog() == 1){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Paper> showAllPapers() {
        try {
            return paperDao.getAllPaperList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int declarePaper(Paper paper) {
        paper.setId(NumberUtil.getGeneratID().toString());
        paper.setReviewTime(NumberUtil.strToDate("1949-10-01"));
        int i = 0;
        try {
            i = paperDao.addPaper(paper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<Paper> showAllPaperByTeacherId(String teacherId) {
        try {
            return paperDao.getPaperListByTeacherId(teacherId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Paper getPaperById(String id) {
        try {
            return paperDao.getPaperById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
