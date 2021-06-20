package rainclassv3.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rainclassv3.pojo.Class;
import rainclassv3.req.ClassQueryReq;
import rainclassv3.req.ClassSaveReq;
import rainclassv3.resp.ClassQueryResp;
import rainclassv3.resp.CommonResp;
import rainclassv3.resp.PageResp;
import rainclassv3.resp.PicUploadResp;
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

    /**
     * 查询全部接口
     * @return
     */
    @GetMapping("/all")
    public List<Class> all() {
        return classService.getAll();
    }


    /**
     * 根据 id ，查询信息
     * @param id
     * @return
     */
    @GetMapping("/selectById/{id}")
    public CommonResp selectById(@PathVariable("id") String id) {
        Long classId=null;
        try {
            classId = Long.valueOf(id);
        } catch (Exception e) {
            throw new RuntimeException("课程查询错误");
        }
        ClassQueryResp classQueryResp = classService.selectById(classId);

        CommonResp commonResp = new CommonResp();
        commonResp.setContent(classQueryResp);
        return commonResp;
    }


    /**
     * 课程图片上传接口
     * @param file
     * @return
     */
    @RequestMapping("/uploadPic")
    public CommonResp uploadPic(@RequestParam MultipartFile file) {
        PicUploadResp upload = classService.upload(file);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(upload);
        return commonResp;
    }


    /**
     * 课程保存/更新接口
     */
    @PostMapping("/save")
    public CommonResp save(@RequestBody ClassSaveReq req) {
        classService.save(req);
        return new CommonResp();
    }
}
