package rainclassv3.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rainclassv3.exception.BusinessException;
import rainclassv3.exception.BusinessExceptionCode;
import rainclassv3.mapper.AdminMapper;
import rainclassv3.mapper.StudentMapper;
import rainclassv3.mapper.TeacherMapper;
import rainclassv3.pojo.*;
import rainclassv3.req.PasswordChangeReq;
import rainclassv3.req.UserLoginReq;
import rainclassv3.resp.AdminLoginResp;
import rainclassv3.resp.StudentLoginResp;
import rainclassv3.resp.TeacherLoginResp;
import rainclassv3.resp.UserLoginResp;
import rainclassv3.service.UserService;
import rainclassv3.util.CopyUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午4:21
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG= LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private AdminMapper adminMapper;


    /**
     * 用户登录
     * 根据传入的角色，从不同的表中，查询登录信息
     * 这里我不做 MD5 加密
     * @param req
     * @return
     */
    @Override
    public UserLoginResp login(UserLoginReq req) {
        String role = req.getRole();
        LOG.info("登录的角色为:{}",role);
        UserLoginResp loginResp = null;

        switch (role) {
            case "student":
                StudentExample studentExample = new StudentExample();
                studentExample.createCriteria()
                        .andLoginnameEqualTo(req.getLoginname());
                List<Student> students = studentMapper.selectByExample(studentExample);
                if (students==null || students.size()<=0) {
                    throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
                }
                if (!Objects.equals(req.getPassword(),students.get(0).getPassword())) {
                    throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
                }
                loginResp = CopyUtil.copy(students.get(0), StudentLoginResp.class);
                loginResp.setRole("student");
                break;
            case "teacher":
                TeacherExample teacherExample = new TeacherExample();
                teacherExample.createCriteria()
                        .andLoginnameEqualTo(req.getLoginname());
                List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
                LOG.info("查询出的教师信息为:{}",teachers);
                if (teachers==null || teachers.size()<=0) {
                    LOG.info("准备抛出异常");
                    throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
                }
                if (!Objects.equals(req.getPassword(),teachers.get(0).getPassword())) {
                    throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
                }
                loginResp = CopyUtil.copy(teachers.get(0), TeacherLoginResp.class);
                loginResp.setRole("teacher");
                break;
            case "admin":
                AdminExample adminExample = new AdminExample();
                adminExample.createCriteria()
                        .andLoginnameEqualTo(req.getLoginname());
                List<Admin> admins = adminMapper.selectByExample(adminExample);
                if (admins==null || admins.size()<=0) {
                    throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
                }
                if (!Objects.equals(req.getPassword(),admins.get(0).getPassword())) {
                    throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
                }
                loginResp = CopyUtil.copy(admins.get(0), AdminLoginResp.class);
                loginResp.setRole("admin");
                break;
        }

        return loginResp;
    }



    /**
     * 更改密码
     *
     * @param req
     */
    @Override
    public void changePassword(PasswordChangeReq req) {
        String role = req.getRole();
        switch (role) {
            case "student":
                // req.get
                break;
            case "teacher":
                break;
            case "admin":
                break;
        }
    }
}
