package cn.mju.hotel.pojo;

public class SysUser {
    private Integer id;

    private String sysNum;

    private String password;

    private String trueName;

    private Integer permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSysNum() {
        return sysNum;
    }

    public void setSysNum(String sysNum) {
        this.sysNum = sysNum == null ? null : sysNum.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
}