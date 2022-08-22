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
public class TaiKhoan {
    private String tenDN ;
    private String matKhau;
    private String vaiTro;
    private String Email;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenDN, String matKhau, String vaiTro, String Email) {
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.Email = Email;
    }

    
    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public String toString(){
        return this.tenDN;
    }
}
