/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.lop;
import com.poly.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LopDAO {

    ResultSet rs = null;
    
    public void insert(lop model){
        String sql = "INSERT INTO Lop(MaKhoa, MaLop, TenLop) VALUES (?,?,?)";
        JdbcHelper.udate(sql, 
                model.getMaKhoa(),
                model.getMaLop(),
                model.getTenLop());
    }
    
    public void update(lop model) {
        String sql = "UPDATE Lop SET  MaKhoa=?, TenLop=? WHERE MaLop=?";
        JdbcHelper.udate(sql,
                model.getMaKhoa(),
                model.getTenLop(),
                model.getMaLop());
    }
    
    public void delete(String maLop) {
        String sql = "DELETE FROM Lop WHERE MaLop=?";
        JdbcHelper.udate(sql, maLop);
    }
    
    public List<lop> selectAll(){
        String sql = "select * from Lop";
        return select(sql);
    }
    
    public lop findById(String maLop){
        String sql = "select * from Lop where MaLop=?";
        List<lop> list = select(sql, maLop);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<lop> select(String sql, Object... args){
        List<lop> list = new ArrayList<>();
        try {
            rs = JdbcHelper.query(sql, args);
            try {
                while(rs.next()){
                    lop model = new lop();
                    model.setMaKhoa(rs.getString("MaKhoa"));
                    model.setMaLop(rs.getString("MaLop"));
                    model.setTenLop(rs.getString("TenLop"));
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

    public List<lop> selectByKhoaHoc( String maLop){
        String sql = "select * from Lop where MaKhoa=?";
        return select(sql, maLop);
    }
}
