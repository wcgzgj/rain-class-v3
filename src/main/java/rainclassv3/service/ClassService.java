package rainclassv3.service;

import com.github.pagehelper.PageInfo;
import rainclassv3.pojo.Class;
import rainclassv3.req.ClassQueryReq;
import rainclassv3.req.ClassSaveReq;
import rainclassv3.resp.ClassQueryResp;
import rainclassv3.resp.PageResp;

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
}
