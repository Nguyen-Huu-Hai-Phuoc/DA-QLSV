/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.sinhvien;
import com.poly.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SinhVienDAO {
    public void insert(sinhvien model){
        String sql = "INSERT INTO SinhVien(MaSV, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, Hinh, Email) values(?,?,?,?,?,?,?,?)";
        JdbcHelper.udate(sql, 
                model.getMaSV(),
                model.getHoTen(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getDiaChi(),
                model.getMaLop(),
                model.getHinh(),
                model.getEmail());
    }
    
    public void update(sinhvien model){
        String sql = "update SinhVien set HoTen=?, NgaySinh=?, GioiTinh=?, DiaChi=?, MaLop=?, Hinh=?, Email=? where MaSV=?";
        JdbcHelper.udate(sql, model.getHoTen(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getDiaChi(),
                model.getMaLop(),
                model.getHinh(),
                model.getEmail(),
                model.getMaSV());
    }
    
    public void delete(String maSV){
        String sql= "delete from SinhVien where MaSV=?";
        JdbcHelper.udate(sql, maSV);
    }
    
    public List<sinhvien> selectAll(){
        String sql="select * from SinhVien";
        return select(sql);
    }
    
    public sinhvien findById(String maSV){
        String sql ="select * from SinhVien where MaSV=?";
        List<sinhvien> list = select(sql, maSV);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    private List<sinhvien> select(String sql, Object... args){
        List<sinhvien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while(rs.next()){
                    sinhvien model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    } 
    
    private sinhvien readFromResultSet(ResultSet rs) throws SQLException{
        sinhvien model = new sinhvien();
        model.setMaSV(rs.getString("MaSV"));
        model.setHoTen(rs.getString("HoTen"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setDiaChi(rs.getString("DiaChi"));
        model.setMaLop(rs.getString("MaLop"));
        model.setHinh(rs.getString("Hinh"));
        model.setEmail(rs.getString("Email"));
        return model;
    }
    
    public List<sinhvien> selectMaLop(){
        String sql="select SinhVien.MaLop from SinhVien";
        return select(sql);
    }
    
    public List<sinhvien> selectByLop(String MaLop) {
        String sql = "SELECT * FROM sinhvien WHERE Malop=?";
        return select(sql, MaLop);
    }
    
}
