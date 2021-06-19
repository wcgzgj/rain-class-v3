package rainclassv3.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rainclassv3.mapper.ClassMapper;
import rainclassv3.mapper.TeacherMapper;
import rainclassv3.pojo.Class;
import rainclassv3.pojo.ClassExample;
import rainclassv3.pojo.Teacher;
import rainclassv3.req.ClassQueryReq;
import rainclassv3.req.ClassSaveReq;
import rainclassv3.resp.ClassQueryResp;
import rainclassv3.resp.PageResp;
import rainclassv3.service.ClassService;
import rainclassv3.util.CopyUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ClassServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午2:17
 * @Version 1.0
 **/
@Service
public class ClassServiceImpl implements ClassService {

    @Resource
    private ClassMapper classMapper;

    @Resource
    private TeacherMapper teacherMapper;

    /**
     * 分页、模糊查询
     * @param req
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public PageResp list(ClassQueryReq req) {
        ClassExample classExample = new ClassExample();
        classExample.createCriteria().andClassnameLike("%"+req.getClassname()+"%");
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<Class> classes = classMapper.selectByExample(classExample);

        PageInfo<Class> pageInfo = new PageInfo<>(classes);

        List<ClassQueryResp> classQueryResps = CopyUtil.copyList(classes, ClassQueryResp.class);
        for (ClassQueryResp classQueryResp : classQueryResps) {
            Teacher teacher = teacherMapper.selectByPrimaryKey(classQueryResp.getTeacherid());
            classQueryResp.setTeacher(teacher);
        }

        PageResp pageResp = new PageResp();
        pageResp.setPageNum(pageInfo.getPageNum());
        pageResp.setPageSize(pageInfo.getPageSize());
        pageResp.setTotal((int) pageInfo.getTotal());
        pageResp.setList(classQueryResps);

        return pageResp;
    }

    /**
     * 获取全部课程信息
     *
     * @return
     */
    @Override
    public List<Class> getAll() {
        List<Class> classes = classMapper.selectByExample(null);
        return classes;
    }


    /**
     * 课程更新/存储
     * @param req
     * @return
     */
    @Override
    public int save(ClassSaveReq req) {
        return -1;
    }
}
