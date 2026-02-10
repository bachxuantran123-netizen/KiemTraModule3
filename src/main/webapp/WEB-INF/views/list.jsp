<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách mặt bằng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-4">

<h2 class="mb-4 text-center fw-bold text-primary">DANH SÁCH MẶT BẰNG</h2>

<div class="card p-3 mb-4 bg-light">
    <form action="mat-bang" method="get" class="row g-3">
        <input type="hidden" name="action" value="search">

        <div class="col-md-3">
            <label>Loại mặt bằng:</label>
            <select name="loaiMatBang" class="form-select">
                <option value="">-- Tất cả --</option>
                <option value="Cho thuê" ${param.loaiMatBang == 'Cho thuê' ? 'selected' : ''}>Cho thuê</option>
                <option value="Trọn gói" ${param.loaiMatBang == 'Trọn gói' ? 'selected' : ''}>Trọn gói</option>
            </select>
        </div>
        <div class="col-md-3">
            <label>Giá tối đa:</label>
            <input type="number" name="giaTien" class="form-control"
                   placeholder="Nhập giá..." value="${param.giaTien}">
        </div>
        <div class="col-md-3">
            <label>Tầng:</label>
            <input type="number" name="tang" class="form-control"
                   placeholder="Nhập tầng..." value="${param.tang}">
        </div>
        <div class="col-md-3 d-flex align-items-end">
            <button type="submit" class="btn btn-primary w-100">Tìm kiếm</button>
        </div>
    </form>
</div>

<div class="mb-3">
    <a href="mat-bang?action=new" class="btn btn-success">Thêm mới mặt bằng</a>
</div>

<table class="table table-bordered table-striped table-hover">
    <thead class="table-dark">
    <tr>
        <th>Mã MB</th>
        <th>Diện tích</th>
        <th>Trạng thái</th>
        <th>Tầng</th>
        <th>Loại VP</th>
        <th>Giá thuê</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="mb" items="${listMatBang}">
        <tr>
            <td>${mb.maMatBang}</td>
            <td>${mb.dienTich} m2</td>
            <td>${mb.trangThai}</td>
            <td>${mb.tang}</td>
            <td>${mb.loaiMatBang}</td>
            <td><fmt:formatNumber value="${mb.giaTien}" type="currency" currencySymbol="VNĐ"/></td>
            <td><fmt:formatDate value="${mb.ngayBatDau}" pattern="dd/MM/yyyy"/></td>
            <td><fmt:formatDate value="${mb.ngayKetThuc}" pattern="dd/MM/yyyy"/></td>
            <td>
                <a href="mat-bang?action=delete&id=${mb.maMatBang}"
                   class="btn btn-danger btn-sm"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa mặt bằng với mã số ${mb.maMatBang} không?');">
                    Xóa
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>