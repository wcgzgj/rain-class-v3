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
import rainclassv3.util.SnowFlake;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    private SnowFlake snowFlake;

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
        Long id = req.getId();
        int count=0;
        if (id==null) {
            //新增
            req.setId(snowFlake.nextId());
            Class copy = CopyUtil.copy(req, Class.class);
            copy.setCreatetime(new Date());
            count = classMapper.insert(copy);
        } else {
            //更新
            Class copy = CopyUtil.copy(req, Class.class);
            count = classMapper.updateByPrimaryKeySelective(copy);
        }
        return count;
    }


    /**
     * 根据 id 查询课程信息
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public ClassQueryResp selectById(Long id) {
        /**
         * 查出课程信息
         */
        Class aClass = classMapper.selectByPrimaryKey(id);
        ClassQueryResp classQueryResp = CopyUtil.copy(aClass, ClassQueryResp.class);

        /**
         * 查出该课程的教师信息
         */
        Teacher teacher = teacherMapper.selectByPrimaryKey(classQueryResp.getTeacherid());
        classQueryResp.setTeacher(teacher);
        return classQueryResp;
    }
}
