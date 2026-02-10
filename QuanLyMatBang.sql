CREATE DATABASE QuanLyMatBang;
GO
USE QuanLyMatBang;
GO

CREATE TABLE MatBang (
    ma_mat_bang VARCHAR(20) PRIMARY KEY,
    dien_tich FLOAT CHECK (dien_tich > 20),
    trang_thai NVARCHAR(50),
    tang INT CHECK (tang >= 1 AND tang <= 15),
    loai_mat_bang NVARCHAR(50),
    mo_ta NVARCHAR(255),
    gia_tien FLOAT CHECK (gia_tien > 1000000),
    ngay_bat_dau DATE,
    ngay_ket_thuc DATE
);

INSERT INTO MatBang VALUES 
('MB001-20-02', 100, N'Trống', 2, N'Cho thuê', N'Văn phòng giá rẻ', 2000000, '2023-11-20', '2024-05-25'),
('MB002-50-05', 240, N'Đầy đủ', 5, N'Trọn gói', N'View đẹp', 5000000, '2023-04-03', '2023-10-03'),
('A01-11-11', 30, N'Hạ tầng', 10, N'Cho thuê', N'Gần thang máy', 1500000, '2024-01-01', '2024-08-01');
GO