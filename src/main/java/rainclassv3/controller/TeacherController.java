package rainclassv3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import rainclassv3.req.TeacherChangeScoreReq;
import rainclassv3.req.TeacherMyClassQueryReq;
import rainclassv3.req.TeacherMyStudentReq;
import rainclassv3.resp.CommonResp;
import rainclassv3.resp.PageResp;
import rainclassv3.resp.TeacherMyStudentResp;
import rainclassv3.service.TeacherService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TeacherController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/20 下午4:23
 * @Version 1.0
 **/
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private static final Logger LOG= LoggerFactory.getLogger(TeacherController.class);

    @Resource
    private TeacherService teacherService;

    /**
     * 获取当前教师所教授的所有课程的列表
     * @param req
     * @return
     */
    @GetMapping("/myClass")
    public CommonResp myClass(TeacherMyClassQueryReq req) {
        PageResp list = teacherService.list(req);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(list);
        return commonResp;
    }


    /**
     * 教师获取自己的课程的所有学生信息的接口
     * @param req
     * @return
     */
    @GetMapping("/myStudent")
    public CommonResp myStudent(TeacherMyStudentReq req) {
        LOG.info("传入的课程id 为:{}",req.getClassid());
        List<TeacherMyStudentResp> myStudent = teacherService.getMyStudent(req);
        LOG.info("获取的学生数据为:{}",myStudent);

        CommonResp commonResp = new CommonResp();
        commonResp.setContent(myStudent);

        return commonResp;
    }


    /**
     * 教师更改学生成绩接口
     * @param req
     * @return
     */
    @PostMapping("/changeScore")
    public CommonResp changeScore(@RequestBody TeacherChangeScoreReq req) {
        LOG.info("传入的成绩信息为:{}",req);
        teacherService.changeScore(req);
        CommonResp commonResp = new CommonResp();
        return commonResp;
    }

}
