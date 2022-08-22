/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.Diem;
import com.poly.model.monhoc;
import com.poly.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DiemDAO extends qlsvDAO<Diem, String> {

    String INSERT_SQL = "INSERT INTO KetQua(MaSV, HoTen, MaLop, MaMH, HocKi, DiemThi, DiemTB, XepLoai, GhiChu) VALUES(?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KetQua SET HoTen=?, MaLop=?, MaMH=?, HocKi=?, DiemThi=?, DiemTB=?, XepLoai=?, GhiChu=? WHERE MaSV=?";
    String DELETE_SQL = "DELETE FROM KetQua WHERE MaSV=?";
    String SELECT_ALL_SQL = "SELECT * FROM KetQua";
    String SELECT_BY_ID_SQL = "SELECT * FROM KetQua WHERE MaSV=?";

    @Override
    public void insert(Diem entity) {
        //To change body of generated methods, choose Tools | Templates.
        JdbcHelper.udate(INSERT_SQL,
                entity.getMaSV(),
                entity.getHoTen(),
                entity.getMaLop(),
                entity.getMaMH(),
                entity.getHocKi(),
                entity.getDiemThi(),
                entity.getDiemTB(),
                entity.getXepLoai(),
                entity.getGhiChu());
    }

    @Override
    public void update(Diem entity) {
        //To change body of generated methods, choose Tools | Templates.
        JdbcHelper.udate(UPDATE_SQL,
                entity.getHoTen(),
                entity.getMaLop(),
                entity.getMaMH(),
                entity.getHocKi(),
                entity.getDiemThi(),
                entity.getDiemTB(),
                entity.getXepLoai(),
                entity.getGhiChu(),
                entity.getMaSV());
    }

    @Override
    public void delete(String key) {
        //To change body of generated methods, choose Tools | Templates.
        JdbcHelper.udate(DELETE_SQL, key);
    }

    @Override
    public List<Diem> selectAll() {
        //To change body of generated methods, choose Tools | Templates.
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Diem selectById(String key) {
        //To change body of generated methods, choose Tools | Templates.
        List<Diem> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<Diem> selectBySql(String sql, Object... args) {
        //To change body of generated methods, choose Tools | Templates.
        List<Diem> list = new ArrayList<Diem>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                Diem entity = new Diem();
                entity.setMaSV(rs.getString("MaSV"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setMaLop(rs.getString("MaLop"));
                entity.setMaMH(rs.getString("MaMH"));
                entity.setHocKi(rs.getInt("HocKi"));
                entity.setDiemThi(rs.getInt("DiemThi"));
                entity.setDiemTB(rs.getInt("DiemTB"));
                entity.setXepLoai(rs.getString("XepLoai"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
