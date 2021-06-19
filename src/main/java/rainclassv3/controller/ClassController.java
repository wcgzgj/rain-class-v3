package rainclassv3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rainclassv3.pojo.Class;
import rainclassv3.req.ClassQueryReq;
import rainclassv3.resp.CommonResp;
import rainclassv3.resp.PageResp;
import rainclassv3.service.ClassService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ClassController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午2:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/class")
public class ClassController {

    @Resource
    private ClassService classService;

    /**
     * 课程查询接口
     * @param req
     * @return
     */
    @GetMapping("/list")
    public CommonResp list(ClassQueryReq req) {
        PageResp pageResp = classService.list(req);

        CommonResp commonResp = new CommonResp();
        commonResp.setContent(pageResp);
        return commonResp;
    }

    @GetMapping("/all")
    public List<Class> all() {
        return classService.getAll();
    }

}
