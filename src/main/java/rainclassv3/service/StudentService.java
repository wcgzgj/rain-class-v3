package rainclassv3.service;

import rainclassv3.req.*;
import rainclassv3.resp.PageResp;
import rainclassv3.resp.StudentMyScoreResp;

import java.util.List;

public interface StudentService {


    /**
     * 查询学生是否选择该课程
     * 需要三表联查： student表和class表，score 作为中间表
     * @param req
     * @return
     */
    boolean isChosen(StudentIsChosenReq req);


    /**
     * 获取学生已经选择的课程的信息
     * @param req
     * @return
     */
    PageResp list(StudentMyClassQueryReq req);


    /**
     * 学生退选
     * @param req
     */
    void cancelClass(StudentClassChangeReq req);

    /**
     * 学生选课
     * @param req
     */
    void selectClass(StudentClassChangeReq req);

    /**
     * 获取当前学生的成绩
     * @param req
     * @return
     */
    List<StudentMyScoreResp> getMyScore(StudentMyScoreReq req);
}
