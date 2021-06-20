package rainclassv3.req;

/**
 * @ClassName ClassQueryReq
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午2:14
 * @Version 1.0
 **/

public class StudentMyClassQueryReq extends PageReq{

    private String classname;

    private String type;

    private Integer credit;

    private String studentid;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    @Override
    public String toString() {
        return "ClassQueryReq{" +
                "classname='" + classname + '\'' +
                ", type='" + type + '\'' +
                ", credit=" + credit +
                ", studentid=" + studentid +
                "} " + super.toString();
    }
}
