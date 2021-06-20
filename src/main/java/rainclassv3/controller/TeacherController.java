package rainclassv3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rainclassv3.req.TeacherMyClassQueryReq;
import rainclassv3.resp.CommonResp;
import rainclassv3.service.TeacherService;

import javax.annotation.Resource;

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

    @Resource
    private TeacherService teacherService;

    /**
     * 获取当前教师所教授的所有课程的列表
     * @param req
     * @return
     */
    @GetMapping("/myClass")
    public CommonResp myClass(TeacherMyClassQueryReq req) {



        CommonResp commonResp = new CommonResp();
        return commonResp;
    }

}
