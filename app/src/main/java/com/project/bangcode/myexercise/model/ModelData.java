package com.project.bangcode.myexercise.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bangcode on 1/6/18.
 */

public class ModelData {


    @SerializedName("nama")
    private String nama;
    @SerializedName("desc")
    private String desc;
    @SerializedName("data")
    private List<DataBean> data;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ModelData{" +
                "nama='" + nama + '\'' +
                ", desc='" + desc + '\'' +
                ", data=" + data.size() +
                '}';
    }
}
