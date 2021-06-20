package rainclassv3.pojo;

import java.util.Date;

public class Class {
    private Long id;

    private String classname;

    private Long teacherid;

    private String starttime;

    private String code;

    private String place;

    private String notice;

    private String type;

    private Integer credit;

    private String desc;

    private Date createtime;

    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Long getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", classname=").append(classname);
        sb.append(", teacherid=").append(teacherid);
        sb.append(", starttime=").append(starttime);
        sb.append(", code=").append(code);
        sb.append(", place=").append(place);
        sb.append(", notice=").append(notice);
        sb.append(", type=").append(type);
        sb.append(", credit=").append(credit);
        sb.append(", desc=").append(desc);
        sb.append(", createtime=").append(createtime);
        sb.append(", path=").append(path);
        sb.append("]");
        return sb.toString();
    }
}