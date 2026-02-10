<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm mới mặt bằng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-4">
<div class="card mx-auto shadow" style="max-width: 700px;">
    <div class="card-header bg-success text-white">
        <h4 class="mb-0">THÊM MỚI MẶT BẰNG</h4>
    </div>
    <div class="card-body">
        <% if(request.getAttribute("error") != null) { %>
        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
        <% } %>

        <form action="mat-bang" method="post">
            <input type="hidden" name="action" value="insert">

            <div class="mb-3">
                <label class="fw-bold">Mã mặt bằng (*)</label>
                <input type="text" name="maMatBang" class="form-control"
                       value="${param.maMatBang}"
                       pattern="[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}"
                       title="Định dạng XXX-XX-XX (X là số hoặc chữ hoa)" required>
                <small class="text-muted">VD: M01-20-02</small>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="fw-bold">Diện tích (>20m2)</label>
                    <input type="number" step="0.1" name="dienTich" class="form-control"
                           value="${param.dienTich}" min="20.1" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="fw-bold">Giá tiền (>1.000.000)</label>
                    <input type="number" name="giaTien" class="form-control"
                           value="${param.giaTien}" min="1000001" required>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="fw-bold">Trạng thái</label>
                    <select name="trangThai" class="form-select">
                        <option value="Trống" ${param.trangThai == 'Trống' ? 'selected' : ''}>Trống</option>
                        <option value="Hạ tầng" ${param.trangThai == 'Hạ tầng' ? 'selected' : ''}>Hạ tầng</option>
                        <option value="Đầy đủ" ${param.trangThai == 'Đầy đủ' ? 'selected' : ''}>Đầy đủ</option>
                    </select>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="fw-bold">Tầng (1-15)</label>
                    <select name="tang" class="form-select">
                        <% for(int i=1; i<=15; i++) { %>
                        <option value="<%=i%>" <%= String.valueOf(i).equals(request.getParameter("tang")) ? "selected" : "" %>><%=i%></option>
                        <% } %>
                    </select>
                </div>
            </div>

            <div class="mb-3">
                <label class="fw-bold">Loại văn phòng</label>
                <div>
                    <input type="radio" name="loaiMatBang" value="Trọn gói"
                    ${param.loaiMatBang == 'Trọn gói' || param.loaiMatBang == null ? 'checked' : ''}> Trọn gói
                    <input type="radio" name="loaiMatBang" value="Cho thuê" class="ms-3"
                    ${param.loaiMatBang == 'Cho thuê' ? 'checked' : ''}> Cho thuê
                </div>
            </div>

            <div class="mb-3">
                <label class="fw-bold">Mô tả chi tiết</label>
                <textarea name="moTa" class="form-control" rows="3">${param.moTa}</textarea>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="fw-bold">Ngày bắt đầu</label>
                    <input type="date" name="ngayBatDau" class="form-control"
                           value="${param.ngayBatDau}" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="fw-bold">Ngày kết thúc</label>
                    <input type="date" name="ngayKetThuc" class="form-control"
                           value="${param.ngayKetThuc}" required>
                </div>
            </div>

            <div class="text-end">
                <a href="mat-bang" class="btn btn-secondary">Hủy</a>
                <button type="submit" class="btn btn-success px-4">Lưu</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>