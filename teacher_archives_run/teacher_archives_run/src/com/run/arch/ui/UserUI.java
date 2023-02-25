package com.run.arch.ui;

import com.run.arch.entity.Course;
import com.run.arch.entity.Paper;
import com.run.arch.entity.Project;
import com.run.arch.entity.Teacher;
import com.run.arch.util.Md5Util;
import com.run.arch.util.NumberUtil;
import com.run.arch.service.CourseService;
import com.run.arch.service.Impl.CourseServiceImpl;
import com.run.arch.service.Impl.PaperServiceImpl;
import com.run.arch.service.Impl.ProjectServiceImpl;
import com.run.arch.service.Impl.TeacherServiceImpl;
import com.run.arch.service.PaperService;
import com.run.arch.service.ProjectService;
import com.run.arch.service.TeacherService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author 泗安
 * 书写教师操作的控制台界面
 */
public class UserUI {

    private Teacher teacher = null;

    private Scanner scan = new Scanner(System.in);

    private TeacherService teacherService = new TeacherServiceImpl();

    private CourseService courseService = new CourseServiceImpl();

    private PaperService paperService = new PaperServiceImpl();

    private ProjectService projectService = new ProjectServiceImpl();

    public void login(){
        String username = "";
        String password = "";
        System.out.println("请输入你的用户名");
        username = scan.next();
        System.out.println("请输入你的密码");
        password = scan.next();
        Teacher login = teacherService.login(username, password);
        if(!Objects.isNull(login)){
            System.out.println("欢迎你的登录，教师 "+login.getName());
            teacher = login;
            mange();
        }else{
            System.out.println("登录失败，请重新登录");
            login();
        }
    }

    //教师注册
    public void register() {
        String username = "";
        String password = "";
        System.out.println("请输入用户名");
        username = scan.next();
        while (teacherService.isExistUserName(username)){
            System.out.println("用户名已被占用，请重新输入：");
            username = scan.next();
        }
        System.out.println("请输入密码");
        password = scan.next();
        Teacher register = teacherService.register(username, password);
        if(Objects.isNull(register)){
            System.out.println("注册失败，请重新注册");
        }else{
            System.out.println("注册成功，请进行登录");
            System.out.println("注意：请登录后，通过修改个人信息功能，添加自己的详细信息");
        }
    }

    //教师操作界面
    private void mange(){
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("1.个人信息管理      2.论文管理      3.项目管理      4.查看所教课程      5.退出");
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
        System.out.print("请选择：");
        String op = scan.next();
        switch (op) {
            case "1":
                infoBar();
                break;
            case "2":
                paperBar();
                break;
            case "3":
                projectBar();
                break;
            case "4":
                coursesBar();
                break;
            case "5":
                System.out.println("欢迎下次使用！");
                System.exit(0);
                break;
            default:System.out.println("操作失败");
        }
        mange();
    }

    //个人信息菜单
    private void infoBar(){
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("1.查看个人信息    2.修改个人信息   3.修改密码      4.返回");
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
        System.out.print("请选择：");
        String op = scan.next();
        switch (op) {
            case "1":
                System.out.println(teacher);
                break;
            case "2":
                System.out.println("请输入修改后姓名：");
                String name1 = scan.next();
                System.out.println("请输入修改后性别（男/女）：");
                String gender1 = scan.next();
                System.out.println("请输入修改后工作时间：格式（2022-09-08）");
                String workTime1 = scan.next();
                System.out.println("请输入修改后从事的专业：");
                String major1 = scan.next();
                System.out.println("请输入修改后学历：");
                String education1 = scan.next();
                System.out.println("请输入修改后职称：");
                String title1 = scan.next();
                teacher.setName(name1);
                teacher.setGender(gender1);
                teacher.setWorkTime(NumberUtil.strToDate(workTime1));
                teacher.setMajor(major1);
                teacher.setEducation(education1);
                teacher.setTitle(title1);
                int i = teacherService.modifyTeacher(teacher);
                if(i==1){
                    System.out.println("修改成功！");
                }else{
                    System.out.println("修改失败！");
                }
                break;
            case "3":
                System.out.println("请输入旧密码：");
                String password = scan.next();
                try {
                    password = Md5Util.encodeByMd5(password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int i1 = 0;
                while (!teacher.getPassword().equals(password)){
                    if(i1==3){
                        System.out.println("密码输入错误超过三次，退出系统");
                        System.exit(0);
                    }
                    System.out.println("密码不一致，请重新输入");
                    password = scan.next();
                    i1++;
                }
                System.out.println("请输入新密码");
                String newPassword = scan.next();
                try {
                    newPassword = Md5Util.encodeByMd5(newPassword);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Teacher teacher1 = teacher;
                teacher1.setPassword(newPassword);
                int i2 = teacherService.modifyTeacher(teacher1);
                if(i2==1){
                    teacher.setPassword(newPassword);
                    System.out.println("修改密码成功！");
                }else{
                    System.out.println("修改密码失败！");
                }
                break;
            case "4":
                mange();
                break;
            default:System.out.println("操作失败");
        }
        infoBar();
    }

    //论文管理菜单
    private void paperBar(){
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("1.搜索论文      2.论文申报    3.查看全部论文    4.返回");
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
        System.out.print("请选择：");
        String op = scan.next();
        switch (op) {
            case "1":
                System.out.println("请输入搜索论文的关键词（id或者名称）");
                String keyword = scan.next();
                List<Paper> papers = paperService.searchPaperByKeyword(keyword);
                if(papers.isEmpty()){
                    System.out.println("无结果");
                }else{
                    for (Paper paper: papers){
                        System.out.println(paper);
                    }
                }
                break;
            case "2":
                System.out.println("请输入论文名称：");
                String paperName = scan.next();
                System.out.println("请输入论文发表时间：(2022-09-08)");
                String pubTime = scan.next();
                System.out.println("请输入论文发表所在的期刊：");
                String pubJournal = scan.next();
                System.out.println("请输入期刊级别：");
                String journalLevel = scan.next();
                System.out.println("请输入索引情况：");
                String index = scan.next();
                Paper paper = new Paper();
                paper.setPaperName(paperName);
                paper.setPubTime(NumberUtil.strToDate(pubTime));
                paper.setPubJournal(pubJournal);
                paper.setJournalLevel(journalLevel);
                paper.setIndex(index);
                paper.setTeacherId(teacher.getId());
                paper.setFlog(0);
                int i = paperService.declarePaper(paper);
                if(i == 1){
                    System.out.println("申报成功，等待管理员进行审核");
                }else{
                    System.out.println("申报失败");
                }
                break;
            case "3":
                List<Paper> papers1 = paperService.showAllPaperByTeacherId(teacher.getId());
                if(papers1.isEmpty()){
                    System.out.println("你还没有论文,请进行论文申报");
                }else{
                    for (Paper paper1: papers1){
                        System.out.println(paper1);
                    }
                }
                break;
            case "4":
                mange();
                break;
            default:System.out.println("操作失败");
        }
        paperBar();
    }

    //项目管理菜单
    private void projectBar(){
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("1.搜索项目      2.项目申报    3.查看全部项目    4.返回");
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
        System.out.print("请选择：");
        String op = scan.next();
        switch (op) {
            case "1":
                System.out.println("请输入搜索项目的关键词（id或者名称）");
                String keyword = scan.next();
                List<Project> projects = projectService.searchProjectByKeyword(keyword);
                if(projects.isEmpty()){
                    System.out.println("无结果");
                }else{
                    for (Project project: projects){
                        System.out.println(project);
                    }
                }
                break;
            case "2":
                System.out.println("请输入项目名称：");
                String projectName = scan.next();
                System.out.println("请输入项目类别：");
                String category = scan.next();
                System.out.println("请输入获奖情况：");
                String awards = scan.next();
                System.out.println("请输入项目创建时间：(2022-09-08)");
                String createTime = scan.next();
                System.out.println("请输入项目完成时间：(2022-09-08)");
                String endTime = scan.next();
                Project sProject = new Project();
                sProject.setProjectName(projectName);
                sProject.setCategory(category);
                sProject.setAwards(awards);
                sProject.setCreateTime(NumberUtil.strToDate(createTime));
                sProject.setEndTime(NumberUtil.strToDate(endTime));
                sProject.setTeacherId(teacher.getId());
                sProject.setFlog(1);
                int i = projectService.declareProject(sProject);
                if(i == 1){
                    System.out.println("申报成功，等待管理员进行审核");
                }else{
                    System.out.println("申报失败");
                }
                break;
            case "3":
                List<Project> projects1 = projectService.showAllProject();
                if (projects1.isEmpty()){
                    System.out.println("你还没有项目，请进行项目申报");
                }else{
                    for (Project project: projects1){
                        System.out.println(project);
                    }
                }
                break;
            case "4":
                mange();
                break;
            default:System.out.println("操作失败");
        }
    }

    //查看所教课程
    private void coursesBar(){
        List<Course> courses = courseService.showAllCoursesByTeacherId(teacher.getId());
        if (courses.isEmpty()){
            System.out.println("你当前还没有分配教学任务");
        }else{
            for (Course course: courses){
                System.out.println(course);
            }
        }
    }



}
