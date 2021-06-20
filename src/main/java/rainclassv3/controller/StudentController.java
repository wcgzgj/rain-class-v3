package rainclassv3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rainclassv3.req.*;
import rainclassv3.resp.CommonResp;
import rainclassv3.resp.PageResp;
import rainclassv3.resp.StudentMyScoreResp;
import rainclassv3.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName StudentController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/20 下午1:59
 * @Version 1.0
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    private static final Logger LOG= LoggerFactory.getLogger(StudentController.class);

    @Resource
    private StudentService studentService;


    /**
     * 获取学生可选的课程列表
     * 排除已经被选中的课程
     * @param req
     * @return
     */
    @GetMapping("/list")
    public CommonResp getStudentClassList(StudentClassQueryReq req) {


        CommonResp commonResp = new CommonResp();
        return commonResp;
    }


    /**
     * 查询是否已经选课接口
     * @param req
     * @return
     */
    @GetMapping("/isChosen")
    public CommonResp isChosen(StudentIsChosenReq req) {
        LOG.info("/isChosen 传入的信息为:{}"+req);
        boolean res = studentService.isChosen(req);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(res);
        return commonResp;
    }


    /**
     * 获取当前学生已经选择的课程的接口
     * @return
     */
    @GetMapping("/myClass")
    public CommonResp getMyClass(StudentMyClassQueryReq req) {
        PageResp pageResp = studentService.list(req);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(pageResp);
        return commonResp;
    }


    /**
     * 选课接口
     * @return
     */
    @RequestMapping("/selectClass")
    public CommonResp selectClass(StudentClassChangeReq req) {
        studentService.selectClass(req);
        CommonResp commonResp = new CommonResp();
        return commonResp;
    }


    /**
     * 退选接口
     * @return
     */
    @RequestMapping("/cancelClass")
    public CommonResp cancelClass(StudentClassChangeReq req) {
        studentService.cancelClass(req);
        CommonResp commonResp = new CommonResp();
        return commonResp;
    }


    /**
     * 学生 获取我的成绩接口
     * @return
     */
    @GetMapping("/myScore")
    public CommonResp myScore(StudentMyScoreReq req) {
        PageResp myScore = studentService.getMyScore(req);
        LOG.info("查询到的学生成绩信息为:{}",myScore);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(myScore);
        return commonResp;
    }






}
