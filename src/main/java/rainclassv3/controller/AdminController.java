package rainclassv3.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rainclassv3.req.ClassSaveReq;
import rainclassv3.resp.CommonResp;
import rainclassv3.service.ClassService;

import javax.annotation.Resource;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/20 下午9:16
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private ClassService classService;

    public CommonResp saveClass(ClassSaveReq req) {
        classService.save(req);
        CommonResp commonResp = new CommonResp();
        return commonResp;
    }

}
