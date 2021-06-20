package rainclassv3.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import rainclassv3.exception.FileException;
import rainclassv3.exception.FileExceptionCode;
import rainclassv3.mapper.ClassMapper;
import rainclassv3.mapper.TeacherMapper;
import rainclassv3.pojo.Class;
import rainclassv3.pojo.ClassExample;
import rainclassv3.pojo.Teacher;
import rainclassv3.req.ClassQueryReq;
import rainclassv3.req.ClassSaveReq;
import rainclassv3.resp.ClassQueryResp;
import rainclassv3.resp.PageResp;
import rainclassv3.resp.PicUploadResp;
import rainclassv3.service.ClassService;
import rainclassv3.util.CopyUtil;
import rainclassv3.util.FileUtil;
import rainclassv3.util.SnowFlake;
import rainclassv3.util.ValidType;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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

    private static final Logger LOG= LoggerFactory.getLogger(ClassServiceImpl.class);

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

    /**
     * 课程图片上传
     *
     * @param file
     * @return
     */
    @Override
    public PicUploadResp upload(MultipartFile file) {
        String path = "/Users/faro_z/Pictures/cover";
        String originalFilename = file.getOriginalFilename();

        LOG.info("获取的文件名为:{}",originalFilename);
        /**
         * 获取文件后缀
         */
        String hz = originalFilename.substring(originalFilename.lastIndexOf("."));

        LOG.info("获取的文件后缀为:{}",hz);


        //判断图片后缀是否合法
        boolean valid = FileUtil.isHzValid(hz, ValidType.PIC);
        if (!valid) {
            throw new FileException(FileExceptionCode.FILE_TYPE_ERROR);
        }

        String newFileName = String.valueOf(snowFlake.nextId())+hz;
        StringBuilder sb = new StringBuilder();
        sb.append(path)
                .append("/")
                .append(newFileName);
        String newPath = sb.toString();
        try {
            file.transferTo(new File(newPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Long newPicId = snowFlake.nextId();
        // Pic pic = new Pic();
        // pic.setId(newPicId);
        // LOG.info("待插入的图片主键为",pic.getId());
        // pic.setPath(newFileName);
        // picMapper.insertSelective(pic);


        /**
         * 需要将保存后的图片名称，和图片 id 返回出去
         */
        PicUploadResp picUploadResp = new PicUploadResp();
        picUploadResp.setPath(newFileName);
        picUploadResp.setShowPath("http://127.0.0.1:9000/disPic/"+newFileName);

        return picUploadResp;
    }
}
