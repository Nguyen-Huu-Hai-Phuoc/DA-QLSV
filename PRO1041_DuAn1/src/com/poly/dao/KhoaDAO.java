/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.khoa;
import com.poly.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhoaDAO {

    ResultSet rs = null;

    public void insert(khoa model) {
        String sql = "INSERT INTO Khoa(MaKHoa, TenKhoa, NamThanhLap) VALUES (?,?,?)";
        JdbcHelper.udate(sql,
                model.getMaKhoa(),
                model.getTenKhoa(),
                model.getNam());
    }

    public void update(khoa model) {
        String sql = "UPDATE Khoa SET  TenKhoa=?, NamThanhLap=? WHERE MaKHoa=?";
        JdbcHelper.udate(sql,
                model.getTenKhoa(),
                model.getNam(),
                model.getMaKhoa());
    }

    public void delete(String tenKhoa) {
        String sql = "DELETE FROM Khoa WHERE MaKHoa=?";
        JdbcHelper.udate(sql, tenKhoa);
    }

    public List<khoa> selectAll(){
        String sql="select * from Khoa";
        return select(sql);
    }
    
    public khoa findById(String maKhoa){
        String sql = "select * from Khoa where MaKHoa=?";
        List<khoa> list = select(sql, maKhoa);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<khoa> select(String sql, Object... args) {
        List<khoa> list = new ArrayList<>();
        try {
            rs = JdbcHelper.query(sql, args);
            try {
                while (rs.next()) {
                    khoa model = new khoa();
                    model.setMaKhoa(rs.getString("MaKHoa"));
                    model.setTenKhoa(rs.getString("TenKhoa"));
                    model.setNam(rs.getInt("NamThanhLap"));
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
    
//    public List<khoa> selectMaKhoa(){
//        String sql="select Khoa.MaKhoa from Khoa";
//        return select(sql);
//    }
    
    public List<khoa> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM khoa WHERE MaKhoa LIKE ?";
        return select(sql, "%" + keyword + "%");
    }
}   

