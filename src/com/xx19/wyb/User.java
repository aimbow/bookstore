package com.xx19.wyb;

public class User {

    private int uid;
    private String ucode;
    private String uname;
    private String upass;
    private int ugender;
    private int uage;
    private int utype;
    private float uscore;
    private String ulogo;

    public User() {

    }

    public User(int uid, String ucode, String uname, String upass) {
        super();
        this.uid = uid;
        this.ucode = ucode;
        this.uname = uname;
        this.upass = upass;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUcode() {
        return ucode;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public int getUgender() {
        return ugender;
    }

    public void setUgender(int ugender) {
        this.ugender = ugender;
    }

    public int getUage() {
        return uage;
    }

    public void setUage(int uage) {
        this.uage = uage;
    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }

    public float getUscore() {
        return uscore;
    }

    public void setUscore(float uscore) {
        this.uscore = uscore;
    }

    public String getUlogo() {
        return ulogo;
    }

    public void setUlogo(String ulogo) {
        this.ulogo = ulogo;
    }


}
