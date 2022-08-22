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
public class khoa {
    
    private String maKhoa;
    private String tenKhoa;
    private int nam;

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }
    
    @Override
    public String toString(){
        return this.maKhoa;
    }
    
//    @Override
//    public boolean equals(Object obj){
//        khoa other = (khoa) obj;
//        return other.getMaKhoa().equals(this.getMaKhoa());
//    }
}
