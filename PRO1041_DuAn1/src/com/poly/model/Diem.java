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
public class Diem {
    String maSV;
    String hoTen;
    String maLop;
    String maMH;
    int hocKi;
    float diemThi;
    float diemTB;
    String xepLoai;
    String ghiChu;

    public Diem() {
    }

    public Diem(String maSV, String hoTen, String maLop, String maMH, int hocKi, float diemThi, float diemTB, String xepLoai, String ghiChu) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.maLop = maLop;
        this.maMH = maMH;
        this.hocKi = hocKi;
        this.diemThi = diemThi;
        this.diemTB = diemTB;
        this.xepLoai = xepLoai;
        this.ghiChu = ghiChu;
    }

    

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public int getHocKi() {
        return hocKi;
    }

    public void setHocKi(int hocKi) {
        this.hocKi = hocKi;
    }

    public float getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(float diemThi) {
        this.diemThi = diemThi;
    }

    public float getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(float diemTB) {
        this.diemTB = diemTB;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    @Override
    public String toString() {
        return this.toString();
    }
}
