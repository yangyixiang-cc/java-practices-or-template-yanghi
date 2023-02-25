package com.run.arch.ui;

import com.run.arch.entity.Course;
import com.run.arch.entity.Paper;
import com.run.arch.entity.Project;
import com.run.arch.entity.Teacher;
import com.run.arch.service.*;
import com.run.arch.util.NumberUtil;
import com.run.arch.service.Impl.*;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author 泗安
 * 书写管理员操作的控制台界面
 */
public class AdminUI {


    private Scanner scan = new Scanner(System.in);

    private AdminService adminService = new AdminServiceImpl();

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
        if(adminService.login(username, password)){
            System.out.println("欢迎你的登录，管理员 "+username);
            mange();
        }else{
            System.out.println("登录失败，请重新登录");
            login();
        }
    }

    //管理员管理界面
    private void mange(){
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("1.教师管理      2.课程管理      3.论文管理      4.项目管理      5.退出");
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
        System.out.print("请选择：");
        String op = scan.next();
        switch (op) {
            case "1":
                teacherBar();
                break;
            case "2":
                courseBar();
                break;
            case "3":
                paperBar();
                break;
            case "4":
                projectBar();
                break;
            case "5":
                System.out.println("欢迎下次使用！");
                System.exit(0);
                break;
            default:System.out.println("操作失败");
        }
        mange();
    }

    //教师管理菜单
    private void teacherBar(){
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("1.搜索教师      2.添加教师      3.删除教师     4.修改教师信息  5.查看全部教师   6.返回");
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
        System.out.print("请选择：");
        String op = scan.next();
        switch (op) {
            case "1":
                System.out.println("请输入搜索教师的关键词（id或者名称）");
                String keyword = scan.next();
                List<Teacher> teachers = teacherService.searchTeacherByKeyword(keyword);
                if(teachers.isEmpty()){
                    System.out.println("无结果");
                }else{
                    for (Teacher teacher: teachers){
                        System.out.println(teacher);
                    }
                }
                break;
            case "2":
                Teacher teacher = new Teacher();
                System.out.println("请输入用户登录昵称：（英文）");
                String username = scan.next();
                while (teacherService.isExistUserName(username)){
                    System.out.println("用户名已被占用，请重新输入：");
                    username = scan.next();
                }
                System.out.println("请输入教师姓名：");
                String name = scan.next();
                System.out.println("请输入教师性别（男/女）：");
                String gender = scan.next();
                System.out.println("请输入参加工作时间：格式（2022-09-08）");
                String workTime = scan.next();
                System.out.println("请输入从事的专业：");
                String major = scan.next();
                System.out.println("请输入教师学历：");
                String education = scan.next();
                System.out.println("请输入职称：");
                String title = scan.next();
                teacher.setName(name);
                teacher.setGender(gender);
                teacher.setWorkTime(NumberUtil.strToDate(workTime));
                teacher.setMajor(major);
                teacher.setEducation(education);
                teacher.setTitle(title);
                teacher.setUsername(username);
                int i = teacherService.addTeacher(teacher);
                if(i == 1){
                    System.out.println("添加成功！");
                    System.out.println("请牢记用户名："+username+"密码为默认密码：123456");
                }else{
                    System.out.println("添加失败！");
                }
                break;
            case "3":
                System.out.println("请输入教师ID：");
                String id = scan.next();
                int i1 = teacherService.removeTeacherById(id);
                if(i1==1){
                    System.out.println("删除成功！");
                }else {
                    System.out.println("删除失败！");
                }
                break;
            case "4":
                System.out.println("请输入教师ID：");
                String id1 = scan.next();
                Teacher teacherById = teacherService.getTeacherById(id1);
                if (Objects.isNull(teacherById)){
                    System.out.println("获取不到该教师信息，请重新输入教师ID");
                    break;
                }
                System.out.println("请输入修改后的教师姓名：");
                String name1 = scan.next();
                System.out.println("请输入修改后教师性别（男/女）：");
                String gender1 = scan.next();
                System.out.println("请输入修改后参加工作时间：格式（2022-09-08）");
                String workTime1 = scan.next();
                System.out.println("请输入修改后从事的专业：");
                String major1 = scan.next();
                System.out.println("请输入修改后教师学历：");
                String education1 = scan.next();
                System.out.println("请输入修改后职称：");
                String title1 = scan.next();
                teacherById.setId(id1);
                teacherById.setName(name1);
                teacherById.setGender(gender1);
                teacherById.setWorkTime(NumberUtil.strToDate(workTime1));
                teacherById.setMajor(major1);
                teacherById.setEducation(education1);
                teacherById.setTitle(title1);
                int i2 = teacherService.modifyTeacher(teacherById);
                if(i2==1){
                    System.out.println("修改成功！");
                }else{
                    System.out.println("修改失败！");
                }
                break;
            case "5":
                List<Teacher> teachers1 = teacherService.showTeachers();
                for (Teacher teacher2: teachers1){
                    System.out.println(teacher2);
                }
                break;
            case "6":
                mange();
                break;
            default:System.out.println("操作失败");
        }
        teacherBar();
    }

    //课程管理菜单
    private void courseBar(){
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("1.搜索课程      2.添加课程      3.删除课程     4.修改课程信息   5.查看全部课程    6.返回");
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("◀▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▶");
        System.out.print("请选择：");
        String op = scan.next();
        switch (op) {
            case "1":
                System.out.println("请输入搜索课程的关键词（id或者名称）");
                String keyword = scan.next();
                List<Course> courses = courseService.searchCourseByCourseKeyword(keyword);
                if(courses.isEmpty()){
                    System.out.println("无结果");
                }else{
                    for (Course course: courses){
                        System.out.println(course);
                    }
                }
                break;
            case "2":
                Course course = new Course();
                System.out.println("请输入课程名称：");
                String courseName = scan.next();
                System.out.println("请输入学时：(数字)");
                String classHours = scan.next();
                System.out.println("请输入创建时间：（2022-09-08）");
                String createTime = scan.next();
                System.out.println("请输入课程人数：（数字）");
                String propleNum = scan.next();
                System.out.println("请输入关联的教师ID");
                String teacherId = scan.next();
                course.setCourseName(courseName);
                course.setClassHours(Integer.valueOf(classHours));
                course.setCreateTime(NumberUtil.strToDate(createTime));
                course.setPeopleNum(Integer.valueOf(propleNum));
                course.setTeacherId(teacherId);
                int i = courseService.addCourse(course);
                if(i == 1){
                    System.out.println("添加成功！");
                }else{
                    System.out.println("添加失败！");
                }
                break;
            case "3":
                System.out.println("请输入课程ID：");
                String id = scan.next();
                int i1 = courseService.removeCourseById(id);
                if(i1==1){
                    System.out.println("删除成功！");
                }else {
                    System.out.println("删除失败！");
                }
                break;
            case "4":
                Course course1 = new Course();
                System.out.println("请输入课程ID：");
                String id1 = scan.next();
                Course courseById = courseService.getCourseById(id1);
                if (Objects.isNull(courseById)){
                    System.out.println("获取不到该课程信息，请重新输入课程ID");
                    break;
                }
                System.out.println("请输入修改后课程名称：");
                String courseName1 = scan.next();
                System.out.println("请输入修改后学时：(数字)");
                String classHours1 = scan.next();
                System.out.println("请输入修改后创建时间：（2022-09-08）");
                String createTime1 = scan.next();
                System.out.println("请输入修改后课程人数：（数字）");
                String propleNum1 = scan.next();
                courseById.setCourseName(courseName1);
                courseById.setClassHours(Integer.valueOf(classHours1));
                courseById.setCreateTime(NumberUtil.strToDate(createTime1));
                courseById.setPeopleNum(Integer.valueOf(propleNum1));
                int i2 = courseService.modifyCourse(courseById);
                if(i2==1){
                    System.out.println("修改成功！");
                }else{
                    System.out.println("修改失败！");
                }
                break;
            case "5":
                List<Course> courses1 = courseService.showAllCourses();
                for (Course course2: courses1){
                    System.out.println(course2);
                }
                break;
            case "6":
                mange();
                break;
            default:System.out.println("操作失败");
        }
        courseBar();
    }

    //论文管理菜单
    private void paperBar(){
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("1.搜索论文      2.论文审核    3.查看全部论文    4.返回");
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
                System.out.println("请输入论文ID：");
                String id = scan.next();
                System.out.println("请输入审核标志：1 通过 2 不通过");
                String flog = scan.next();
                System.out.println("请输入审核意见：");
                String text = scan.next();
                int i = paperService.reviewPaper(id, flog, text);
                if(i == -1){
                    System.out.println("无该论文，请检查论文ID");
                }else if(i == 1){
                    System.out.println("论文已审核，无需审核");
                }else if(i == 2){
                    System.out.println("审核成功");
                }else{
                    System.out.println("审核失败");
                }
                break;
            case "3":
                List<Paper> papers1 = paperService.showAllPapers();
                if(papers1.isEmpty()){
                    System.out.println("无结果");
                }else{
                    for (Paper paper: papers1){
                        System.out.println(paper);
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
        System.out.println("1.搜索项目      2.项目审核    3.查看全部项目    4.返回");
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
                System.out.println("请输入项目ID：");
                String id = scan.next();
                System.out.println("请输入审核标志：1 通过 2 不通过");
                String flog = scan.next();
                System.out.println("请输入审核意见：");
                String text = scan.next();
                int i = projectService.reviewProject(id, flog, text);
                if(i == -1){
                    System.out.println("无该项目，请检查项目ID");
                }else if(i == 1){
                    System.out.println("项目已审核，无需审核");
                }else if(i == 2){
                    System.out.println("审核成功");
                }else{
                    System.out.println("审核失败");
                }
                break;
            case "3":
                List<Project> projects1 = projectService.showAllProject();
                if (projects1.isEmpty()){
                    System.out.println("无结果");
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
        projectBar();
    }
}
