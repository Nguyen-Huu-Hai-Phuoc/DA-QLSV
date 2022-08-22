/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.monhoc;
import com.poly.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MonHocDAO extends qlsvDAO<monhoc, String>{

    String INSERT_SQL = "INSERT INTO MonHoc(MaMH, TenMH, MaGV, HocKi,MaKhoa) VALUES(?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE MonHoc SET TenMH=?, MaGV=?, HocKi=?, MaKhoa=? WHERE MaMH=?";
    String DELETE_SQL = "DELETE FROM MonHoc WHERE MaMH=?";
    String SELECT_ALL_SQL = "SELECT * FROM MonHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM MonHoc WHERE MaMH=?";

    @Override
    public void insert(monhoc entity) {
        //To change body of generated methods, choose Tools | Templates.
         JdbcHelper.udate(INSERT_SQL,
                entity.getMaMH(),
                entity.getTenMH(),
                entity.getMaGV(),
                entity.getHocKi(),
                entity.getMaKhoa());
    }

    @Override
    public void update(monhoc entity) {
        //To change body of generated methods, choose Tools | Templates.
         JdbcHelper.udate(UPDATE_SQL,
                entity.getTenMH(),
                entity.getMaGV(),
                entity.getHocKi(),
                entity.getMaKhoa(),
                entity.getMaMH());
    }

    @Override
    public void delete(String key) {
        //To change body of generated methods, choose Tools | Templates.
        JdbcHelper.udate(DELETE_SQL, key);
    }

    @Override
    public List<monhoc> selectAll() {
        //To change body of generated methods, choose Tools | Templates.
         return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public monhoc selectById(String key) {
        //To change body of generated methods, choose Tools | Templates.
         List<monhoc> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<monhoc> selectBySql(String sql, Object... args) {
        //To change body of generated methods, choose Tools | Templates.
        List<monhoc> list = new ArrayList<monhoc>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                monhoc entity = new monhoc();
                entity.setMaMH(rs.getString("MaMH"));
                entity.setTenMH(rs.getString("TenMH"));
                entity.setMaGV(rs.getString("MaGV"));
                entity.setHocKi(rs.getInt("HocKi"));
                entity.setMaKhoa(rs.getString("MaKhoa"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<monhoc> selectByKhoaM(String makhoa){
        String sql = "SELECT * FROM MonHoc WHERE MaKhoa=?";
        return this.selectBySql(sql, makhoa);
    }
}
