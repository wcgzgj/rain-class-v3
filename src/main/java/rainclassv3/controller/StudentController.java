package rainclassv3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rainclassv3.req.StudentClassQueryReq;
import rainclassv3.resp.CommonResp;
import rainclassv3.service.StudentService;

import javax.annotation.Resource;

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







}
