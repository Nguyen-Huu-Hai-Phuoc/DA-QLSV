/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.utils;

import com.poly.model.TaiKhoan;
import com.poly.model.sinhvien;

/**
 *
 * @author Admin
 */
public class Auth {

    /**
     *
     * Đối tượng chứa thông tin người sử dụng khi đăng nhập
     */
    public static TaiKhoan user = null;

    /**
     *
     * Xóa thông tin người sử dụng khi đăng xuất
     */
    public static void clear() {
        Auth.user = null;
    }

    /**
     *
     * Kiểm tra xem đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return Auth.user != null;
    }

    /**
     *
     * Kiểm tra vai trò đăng nhập
     */
    public static boolean isSinhVien(){
       return Auth.isLogin() && (user.getVaiTro().equals("Sinh Vien"));
    }
    public static boolean isGiangVien(){
       return Auth.isLogin() && (user.getVaiTro().equals("Giang Vien"));
    }
    public static boolean isCanBo(){
       return Auth.isLogin() && (user.getVaiTro().equals("Can Bo Dao Tao"));
    }
}
