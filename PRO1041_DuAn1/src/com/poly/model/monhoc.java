/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;

/**
 *
 * @author Admin
 */
public class monhoc {
    String maMH;
    String tenMH;
    String maGV;
    int hocKi;
    String maKhoa;

    public monhoc() {
    }

    public monhoc(String maMH, String tenMH, String maGV, int hocKi, String maKhoa) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.maGV = maGV;
        this.hocKi = hocKi;
        this.maKhoa = maKhoa;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public int getHocKi() {
        return hocKi;
    }

    public void setHocKi(int hocKi) {
        this.hocKi = hocKi;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }
    
    @Override
    public String toString() {
        return this.maMH ;
    }
}
