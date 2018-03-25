package com.project.bangcode.myexercise.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bangcode on 1/6/18.
 */

class DataBean {
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("rt")
    private int rt;
    @SerializedName("rw")
    private int rw;

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public int getRw() {
        return rw;
    }

    public void setRw(int rw) {
        this.rw = rw;
    }
}
