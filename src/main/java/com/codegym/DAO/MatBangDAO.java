package com.codegym.DAO;

import com.codegym.Database.DatabaseConnection;
import com.codegym.Entities.MatBang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatBangDAO {

    public List<MatBang> selectAll() {
        List<MatBang> list = new ArrayList<>();
        String sql = "SELECT * FROM MatBang ORDER BY dien_tich ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void insert(MatBang mb) throws SQLException {
        String sql = "INSERT INTO MatBang (ma_mat_bang, dien_tich, trang_thai, tang, loai_mat_bang, mo_ta, gia_tien, ngay_bat_dau, ngay_ket_thuc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mb.getMaMatBang());
            ps.setDouble(2, mb.getDienTich());
            ps.setString(3, mb.getTrangThai());
            ps.setInt(4, mb.getTang());
            ps.setString(5, mb.getLoaiMatBang());
            ps.setString(6, mb.getMoTa());
            ps.setDouble(7, mb.getGiaTien());
            ps.setDate(8, mb.getNgayBatDau());
            ps.setDate(9, mb.getNgayKetThuc());
            ps.executeUpdate();
        }
    }

    public boolean delete(String maMatBang) {
        String sql = "DELETE FROM MatBang WHERE ma_mat_bang = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maMatBang);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<MatBang> search(String loaiMatBang, String giaTien, String tang) {
        List<MatBang> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM MatBang WHERE 1=1");

        if (loaiMatBang != null && !loaiMatBang.isEmpty()) {
            sql.append(" AND loai_mat_bang LIKE ?");
        }
        if (tang != null && !tang.isEmpty()) {
            sql.append(" AND tang = ?");
        }
        if (giaTien != null && !giaTien.isEmpty()) {
            sql.append(" AND gia_tien <= ?");
        }

        sql.append(" ORDER BY dien_tich ASC");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (loaiMatBang != null && !loaiMatBang.isEmpty()) {
                ps.setString(index++, "%" + loaiMatBang + "%");
            }
            if (tang != null && !tang.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(tang));
            }
            if (giaTien != null && !giaTien.isEmpty()) {
                ps.setDouble(index++, Double.parseDouble(giaTien));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean exists(String code) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM MatBang WHERE ma_mat_bang = ?")) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private MatBang mapRow(ResultSet rs) throws SQLException {
        return new MatBang(
                rs.getString("ma_mat_bang"), rs.getDouble("dien_tich"), rs.getString("trang_thai"),
                rs.getInt("tang"), rs.getString("loai_mat_bang"), rs.getString("mo_ta"),
                rs.getDouble("gia_tien"), rs.getDate("ngay_bat_dau"), rs.getDate("ngay_ket_thuc")
        );
    }
}