package com.example.doubt;

public class Subject {

    String id;
    String un;
    String ph;
    String sn;
    String u;

    public  Subject() {

    }

public Subject(String id,String un,String ph,String sn,String u){
    this.id=id;
    this.un=un;
    this.ph=ph;
    this.sn=sn;
    this.u=u;
}

    public String getId() {
        return id;
    }

    public String getUn() {
        return un;
    }

    public String getPh() {
        return ph;
    }

    public String getSn() {
        return sn;
    }

    public String getU() {
        return u;
    }
}
