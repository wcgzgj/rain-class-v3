package rainclassv3.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rainclassv3.mapper.ClassMapper;
import rainclassv3.mapper.ScoreMapper;
import rainclassv3.mapper.StudentMapper;
import rainclassv3.mapper.TeacherMapper;
import rainclassv3.pojo.*;
import rainclassv3.pojo.Class;
import rainclassv3.req.StudentClassChangeReq;
import rainclassv3.req.StudentIsChosenReq;
import rainclassv3.req.StudentMyClassQueryReq;
import rainclassv3.req.StudentMyScoreReq;
import rainclassv3.resp.ClassQueryResp;
import rainclassv3.resp.PageResp;
import rainclassv3.resp.StudentMyScoreResp;
import rainclassv3.service.StudentService;
import rainclassv3.util.CopyUtil;
import rainclassv3.util.SnowFlake;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description 学生业务层代码
 * @Author FARO_Z
 * @Date 2021/6/20 下午1:59
 * @Version 1.0
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private ClassMapper classMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ScoreMapper scoreMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private SnowFlake snowFlake;

    /**
     * 查询学生是否选择该课程
     * 需要三表联查： student表和class表，score 作为中间表
     * @param req
     * @return
     */
    @Override
    public boolean isChosen(StudentIsChosenReq req) {
        long studentId = Long.parseLong(req.getStudentid());
        long classId = Long.parseLong(req.getClassid());

        /**
         * 查询出学生已经选的所有课程
         */
        ScoreExample scoreExample = new ScoreExample();
        scoreExample.createCriteria().andStudentidEqualTo(studentId);
        List<Score> scores = scoreMapper.selectByExample(scoreExample);

        for (Score score : scores) {
            if (score.getClassid()==classId) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取学生已经选择的课程的信息
     *
     * @param req
     * @return
     */
    @Override
    public PageResp list(StudentMyClassQueryReq req) {
        long studentId = Long.parseLong(req.getStudentid());
        ScoreExample scoreExample = new ScoreExample();
        scoreExample.createCriteria().andStudentidEqualTo(studentId);
        List<Score> scores = scoreMapper.selectByExample(scoreExample);

        List<Class> classes = new ArrayList<>();
        /**
         * 查询出当前学生选择的所有课程
         */
        for (Score score : scores) {
            Class myClass = classMapper.selectByPrimaryKey(score.getClassid());
            classes.add(myClass);
        }

        /**
         * 学生已经选择的课程列表中，就不设置模糊查询功能了
         */
        // ClassExample classExample = new ClassExample();
        // classExample.createCriteria().andClassnameLike("%"+req.getClassname()+"%");
        // PageHelper.startPage(req.getPageNum(),req.getPageSize());
        // List<Class> classes = classMapper.selectByExample(classExample);
        //
        // PageInfo<Class> pageInfo = new PageInfo<>(classes);

        /**
         * 查找课程的教师信息
         */
        List<ClassQueryResp> classQueryResps = CopyUtil.copyList(classes, ClassQueryResp.class);
        for (ClassQueryResp classQueryResp : classQueryResps) {
            Teacher teacher = teacherMapper.selectByPrimaryKey(classQueryResp.getTeacherid());
            classQueryResp.setTeacher(teacher);
        }

        PageResp pageResp = new PageResp();
        pageResp.setPageNum(req.getPageNum());
        pageResp.setPageSize(req.getPageSize());
        pageResp.setTotal(classQueryResps.size());
        pageResp.setList(classQueryResps);

        return pageResp;
    }

    /**
     * 学生退选
     *
     * @param req
     */
    @Override
    public void cancelClass(StudentClassChangeReq req) {
        long classId = Long.parseLong(req.getClassid());
        long studentId = Long.parseLong(req.getStudentid());

        ScoreExample scoreExample = new ScoreExample();
        scoreExample.createCriteria().andClassidEqualTo(classId)
                .andStudentidEqualTo(studentId);
        scoreMapper.deleteByExample(scoreExample);
    }

    /**
     * 学生选课
     *
     * @param req
     */
    @Override
    public void selectClass(StudentClassChangeReq req) {
        long classId = Long.parseLong(req.getClassid());
        long studentId = Long.parseLong(req.getStudentid());

        /**
         * 需要在 score 表中，添加课程与学生的信息
         */
        Score score = new Score();
        score.setClassid(classId);
        score.setStudentid(studentId);

        /**
         * 为新的数据，添加主键
         */
        long id = snowFlake.nextId();
        score.setId(id);
        
        scoreMapper.insert(score);
    }


    /**
     * 获取当前学生的所有课程的成绩
     * @param req
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public PageResp getMyScore(StudentMyScoreReq req) {
        long studentId = Long.parseLong(req.getStudentid());
        ScoreExample scoreExample = new ScoreExample();
        scoreExample.createCriteria().andStudentidEqualTo(studentId);
        List<Score> scores = scoreMapper.selectByExample(scoreExample);

        List<StudentMyScoreResp> studentMyScoreResps = new ArrayList<>();
        for (Score score : scores) {
            StudentMyScoreResp studentMyScoreResp = new StudentMyScoreResp();
            Class aClass = classMapper.selectByPrimaryKey(score.getClassid());
            studentMyScoreResp.setClassInfo(aClass);
            studentMyScoreResp.setScore(score.getScorenum());
            studentMyScoreResps.add(studentMyScoreResp);
        }

        PageResp pageResp = new PageResp();
        pageResp.setPageNum(req.getPageNum());
        pageResp.setPageSize(req.getPageSize());
        pageResp.setTotal(studentMyScoreResps.size());
        pageResp.setList(studentMyScoreResps);

        return pageResp;
    }
}
