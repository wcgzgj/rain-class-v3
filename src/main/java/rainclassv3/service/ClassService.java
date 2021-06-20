package rainclassv3.service;

import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;
import rainclassv3.pojo.Class;
import rainclassv3.req.ClassQueryReq;
import rainclassv3.req.ClassSaveReq;
import rainclassv3.resp.ClassQueryResp;
import rainclassv3.resp.PageResp;
import rainclassv3.resp.PicUploadResp;

import java.util.List;

/**
 * @ClassName ClassService
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午2:16
 * @Version 1.0
 **/
public interface ClassService {

    /**
     * 分页、模糊查询
     * @param req
     * @return
     */
    PageResp list(ClassQueryReq req);

    /**
     * 获取全部课程信息
     * @return
     */
    List<Class> getAll();


    /**
     * 课程更新/存储
     * @param req
     * @return
     */
    int save(ClassSaveReq req);

    /**
     * 根据 id 查询课程信息
     * @param id
     * @return
     */
    ClassQueryResp selectById(Long id);

    /**
     * 课程图片上传
     * @param file
     * @return
     */
    PicUploadResp upload(MultipartFile file);

    /**
     * 根据 id 删除课程信息
     * 不但要删除 class 表的信息
     * 还要删 score 表的
     * @param id
     */
    void deleteById(Long id);
}
