package com.bawei6.usercenter.bean;

/**
 * @author fengchen
 * @date 2019/12/30.
 * @description：登录的Bean类
 */
public class LoginBean {

    /**
     * id : 3
     * usercode : 5f85db173b4549dd8b0d772044fcbd9e
     * username : l123
     * pwd : null
     * sex : 115
     * birthday : sample string 6
     * headerimg : sample string 7
     * nick : sample string 8
     * utype : 9
     * imuseraccount : sample string 10
     * signdesc : sample string 11
     * openlocation : 0
     * openmsgalert : 3
     */

    private int id;
    private String usercode;
    private String username;
    private Object pwd;
    private String sex;
    private String birthday;
    private String headerimg;
    private String nick;
    private int utype;
    private String imuseraccount;
    private String signdesc;
    private int openlocation;
    private int openmsgalert;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getPwd() {
        return pwd;
    }

    public void setPwd(Object pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeaderimg() {
        return headerimg;
    }

    public void setHeaderimg(String headerimg) {
        this.headerimg = headerimg;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }

    public String getImuseraccount() {
        return imuseraccount;
    }

    public void setImuseraccount(String imuseraccount) {
        this.imuseraccount = imuseraccount;
    }

    public String getSigndesc() {
        return signdesc;
    }

    public void setSigndesc(String signdesc) {
        this.signdesc = signdesc;
    }

    public int getOpenlocation() {
        return openlocation;
    }

    public void setOpenlocation(int openlocation) {
        this.openlocation = openlocation;
    }

    public int getOpenmsgalert() {
        return openmsgalert;
    }

    public void setOpenmsgalert(int openmsgalert) {
        this.openmsgalert = openmsgalert;
    }
}
