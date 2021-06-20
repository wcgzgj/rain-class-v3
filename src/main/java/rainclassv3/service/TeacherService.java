package rainclassv3.service;

import rainclassv3.req.TeacherMyClassQueryReq;
import rainclassv3.req.TeacherMyStudentReq;
import rainclassv3.resp.PageResp;
import rainclassv3.resp.TeacherMyStudentResp;

import javax.annotation.Resource;
import java.util.List;

public interface TeacherService {


    /**
     * 获取教师已经选择的课程的信息
     * @param req
     * @return
     */
    PageResp list(TeacherMyClassQueryReq req);


    /**
     * 获取当前教师，当前课程的所有学生信息
     * @param req
     * @return
     */
    List<TeacherMyStudentResp> getMyStudent(TeacherMyStudentReq req);
}
