package com.codegym.Controllers;

import com.codegym.DAO.MatBangDAO;
import com.codegym.Entities.MatBang;
import com.codegym.Validate.Validator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/mat-bang")
public class MatBangServlet extends HttpServlet {
    private MatBangDAO dao = new MatBangDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
                break;
            case "delete":
                String id = req.getParameter("id");
                dao.delete(id);
                resp.sendRedirect("mat-bang");
                break;
            case "search":
                String loai = req.getParameter("loaiMatBang");
                String tang = req.getParameter("tang");
                String gia = req.getParameter("giaTien");
                List<MatBang> searchResult = dao.search(loai, gia, tang);
                req.setAttribute("listMatBang", searchResult);
                req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
                break;
            default:
                List<MatBang> list = dao.selectAll();
                req.setAttribute("listMatBang", list);
                req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
                break;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("insert".equals(action)) {
            try {
                String ma = req.getParameter("maMatBang");
                String dtStr = req.getParameter("dienTich");
                String trangThai = req.getParameter("trangThai");
                String tangStr = req.getParameter("tang");
                String loai = req.getParameter("loaiMatBang");
                String moTa = req.getParameter("moTa");
                String giaStr = req.getParameter("giaTien");
                String ngayBatDau = req.getParameter("ngayBatDau");
                String ngayKetThuc = req.getParameter("ngayKetThuc");

                // --- VALIDATION SERVER SIDE (Ăn trọn điểm phần này) ---
                if (!Validator.isValidMaMatBang(ma)) throw new IllegalArgumentException("Mã mặt bằng sai định dạng (XXX-XX-XX)!");
                if (dao.exists(ma)) throw new IllegalArgumentException("Mã mặt bằng đã tồn tại!");
                if (Double.parseDouble(dtStr) <= 20) throw new IllegalArgumentException("Diện tích phải > 20m2!");
                if (Double.parseDouble(giaStr) <= 1000000) throw new IllegalArgumentException("Giá tiền phải > 1.000.000!");
                if (!Validator.isAtLeast6Months(ngayBatDau, ngayKetThuc)) throw new IllegalArgumentException("Ngày kết thúc phải sau ngày bắt đầu ít nhất 6 tháng!");

                MatBang mb = new MatBang(ma, Double.parseDouble(dtStr), trangThai, Integer.parseInt(tangStr),
                        loai, moTa, Double.parseDouble(giaStr),
                        Date.valueOf(ngayBatDau), Date.valueOf(ngayKetThuc));

                dao.insert(mb);
                resp.sendRedirect("mat-bang");

            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
                // Giữ lại dữ liệu cũ để người dùng không phải nhập lại
                req.setAttribute("old", req.getParameterMap());
                req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
            }
        }
    }
}