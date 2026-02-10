package com.codegym.Entities;

import java.sql.Date;

public class MatBang {
    private String maMatBang;
    private double dienTich;
    private String trangThai;
    private int tang;
    private String loaiMatBang;
    private String moTa;
    private double giaTien;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public MatBang() {}

    public MatBang(String maMatBang, double dienTich, String trangThai, int tang, String loaiMatBang, String moTa, double giaTien, Date ngayBatDau, Date ngayKetThuc) {
        this.maMatBang = maMatBang;
        this.dienTich = dienTich;
        this.trangThai = trangThai;
        this.tang = tang;
        this.loaiMatBang = loaiMatBang;
        this.moTa = moTa;
        this.giaTien = giaTien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMaMatBang() { return maMatBang; }
    public void setMaMatBang(String maMatBang) { this.maMatBang = maMatBang; }
    public double getDienTich() { return dienTich; }
    public void setDienTich(double dienTich) { this.dienTich = dienTich; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public int getTang() { return tang; }
    public void setTang(int tang) { this.tang = tang; }
    public String getLoaiMatBang() { return loaiMatBang; }
    public void setLoaiMatBang(String loaiMatBang) { this.loaiMatBang = loaiMatBang; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public double getGiaTien() { return giaTien; }
    public void setGiaTien(double giaTien) { this.giaTien = giaTien; }
    public Date getNgayBatDau() { return ngayBatDau; }
    public void setNgayBatDau(Date ngayBatDau) { this.ngayBatDau = ngayBatDau; }
    public Date getNgayKetThuc() { return ngayKetThuc; }
    public void setNgayKetThuc(Date ngayKetThuc) { this.ngayKetThuc = ngayKetThuc; }
}