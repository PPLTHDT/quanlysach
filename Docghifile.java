import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Docghifile {
    private static final DateTimeFormatter NGAY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String duongDan;

    public Docghifile() {
        this("data");
    }

    public Docghifile(String duongDan) {
        setDuongDan(duongDan);
    }

    private File fileSach() {
        return new File(duongDan, "sach.txt");
    }

    private File fileHoaDon() {
        return new File(duongDan, "hoadon.txt");
    }

    public Sach[] docFileSach() {
        List<Sach> kq = new ArrayList<>();
        List<String> dong = docDong(fileSach());
        for (int i = 0; i < dong.size(); i++) {
            String d = dong.get(i);
            if (boQua(d)) {
                continue;
            }
            try {
                String[] p = d.split("\\|", -1);
                if (p.length < 8) {
                    throw new IllegalArgumentException("thiếu cột (cần 8)");
                }
                
                String[] dsTG = p[2].trim().isEmpty() ? new String[0] : p[2].split(";");
                TacGia[] tacGia = new TacGia[dsTG.length];
                for (int k = 0; k < dsTG.length; k++) {
                    String[] t = dsTG[k].split(":", -1);
                    tacGia[k] = taoTacGia(t[0].trim(), t.length > 1 ? t[1].trim() : "");
                }
                
                String[] tl = p[4].split(":", -1);
                TheLoai theLoai = taoTheLoai(tl[0].trim(), tl.length > 1 ? tl[1].trim() : "");
            
                String[] nx = p[5].split(":", -1);
                NhaXuatBan nxb = taoNhaXuatBan(nx[0].trim(), nx.length > 1 ? nx[1].trim() : "",
                        nx.length > 2 ? nx[2].trim() : "");

                kq.add(taoSach(p[0].trim(), p[1].trim(), tacGia, Integer.parseInt(p[3].trim()),
                        theLoai, nxb, Double.parseDouble(p[6].trim()), Integer.parseInt(p[7].trim())));
            } catch (RuntimeException e) {
                System.out.println("sach.txt dòng " + (i + 1) + " bị bỏ qua: " + e.getMessage());
            }
        }
        return kq.toArray(new Sach[0]);
    }

    public void ghiFileSach(Sach[] ds) {
        List<String> dong = new ArrayList<>();
        dong.add("# maSach|tenSach|dsTacGia|namXB|theLoai|nhaXuatBan|giaBan|soLuongTon");
        if (ds != null) {
            for (Sach s : ds) {
                if (s == null) {
                    continue;
                }
                StringBuilder tg = new StringBuilder();
                TacGia[] dsTG = s.getDsTacGia();
                for (int k = 0; dsTG != null && k < dsTG.length; k++) {
                    if (dsTG[k] == null) {
                        continue;
                    }
                    if (tg.length() > 0) {
                        tg.append(';');
                    }
                    tg.append(dsTG[k].getMaTacGia()).append(':').append(dsTG[k].getTenTacGia());
                }
                TheLoai tl = s.getTheLoai();
                NhaXuatBan nxb = s.getNhaXuatBan();
                dong.add(String.join("|",
                        s.getMaSach(), s.getTenSach(), tg.toString(),
                        String.valueOf(s.getNamXuatBan()),
                        tl != null ? tl.getMaTheLoai() + ":" + tl.getTenTheLoai() : "",
                        nxb != null ? nxb.getMaNXB() + ":" + nxb.getTenNXB() + ":" + nxb.getDiaChi() : "",
                        String.valueOf(s.getGiaBan()), String.valueOf(s.getSoLuongTon())));
            }
        }
        ghiDong(fileSach(), dong);
    }

    public Hoadon[] docFileHoaDon(KhachHang[] dsKH, int nKH, NhanVien[] dsNV, int nNV,
                                  Sach[] dsSach, int nSach) {
        List<Hoadon> kq = new ArrayList<>();
        Hoadon hienTai = null;
        List<String> dong = docDong(fileHoaDon());
        for (int i = 0; i < dong.size(); i++) {
            String d = dong.get(i);
            if (boQua(d)) {
                continue;
            }
            String[] p = d.split("\\|", -1);
            try {
                if (p[0].trim().equals("HD")) {
                    if (p.length < 6) {
                        throw new IllegalArgumentException("thiếu cột (cần 6)");
                    }
                    KhachHang kh = null;
                    if (!p[3].trim().isEmpty()) {
                        kh = timKH(dsKH, nKH, p[3].trim());
                        if (kh == null) {
                            throw new IllegalArgumentException("không có khách hàng " + p[3].trim());
                        }
                    }
                    NhanVien nv = timNV(dsNV, nNV, p[4].trim());
                    if (nv == null) {
                        throw new IllegalArgumentException("không có nhân viên " + p[4].trim());
                    }
                    hienTai = new Hoadon(p[1].trim(), LocalDate.parse(p[2].trim(), NGAY),
                            0, kh, nv, docThanhToan(p[5].trim()));
                    kq.add(hienTai);
                } else if (p[0].trim().equals("CT")) {
                    if (hienTai == null) {
                        throw new IllegalArgumentException("dòng CT đứng trước dòng HD");
                    }
                    if (p.length < 4) {
                        throw new IllegalArgumentException("thiếu cột (cần 4)");
                    }
                    Sach s = timSach(dsSach, nSach, p[2].trim());
                    if (s == null) {
                        throw new IllegalArgumentException("không có sách " + p[2].trim());
                    }
                    hienTai.themChiTiet(new ChiTietHoaDon(p[1].trim(), s, Integer.parseInt(p[3].trim())));
                } else {
                    throw new IllegalArgumentException("dòng phải bắt đầu bằng HD hoặc CT");
                }
            } catch (NumberFormatException | DateTimeParseException | IllegalArgumentException e) {
                System.out.println("hoadon.txt dòng " + (i + 1) + " bị bỏ qua: " + e.getMessage());
            }
        }
        return kq.toArray(new Hoadon[0]);
    }

    public void ghiFileHoaDon(Hoadon[] ds) {
        List<String> dong = new ArrayList<>();
        dong.add("# HD|maHD|dd/MM/yyyy|maKH|maNV|thanhToan");
        dong.add("# CT|maCT|maSach|soLuong");
        if (ds != null) {
            for (Hoadon hd : ds) {
                if (hd == null) {
                    continue;
                }
                dong.add(String.join("|", "HD", hd.getMaHD(), hd.getNgayLap().format(NGAY),
                        hd.getKhachHang() != null ? hd.getKhachHang().getMaKH() : "",
                        hd.getNhanVien().getMaNV(), ghiThanhToan(hd.getThanhToan())));
                for (ChiTietHoaDon ct : hd.getDsChiTiet()) {
                    dong.add(String.join("|", "CT", ct.getMaCT(), ct.getSach().getMaSach(),
                            String.valueOf(ct.getSoLuong())));
                }
            }
        }
        ghiDong(fileHoaDon(), dong);
    }

    private ThanhToan docThanhToan(String s) {
        if (s.isEmpty()) {
            return null;
        }
        String[] t = s.split(":", -1);
        if (t[0].equals("TM") && t.length >= 2) {
            return new ThanhToanTienMat(Double.parseDouble(t[1].trim()));
        }
        if (t[0].equals("CK") && t.length >= 4) {
            return new ThanhToanChuyenKhoan(t[1].trim(), t[2].trim(), t[3].trim());
        }
        throw new IllegalArgumentException("thanh toán sai định dạng: " + s);
    }

    private String ghiThanhToan(ThanhToan tt) {
        if (tt instanceof ThanhToanTienMat) {
            return "TM:" + ((ThanhToanTienMat) tt).getTienNhan();
        }
        if (tt instanceof ThanhToanChuyenKhoan) {
            ThanhToanChuyenKhoan ck = (ThanhToanChuyenKhoan) tt;
            return "CK:" + ck.getSoTaiKhoan() + ":" + ck.getNganHang() + ":" + ck.getMaGiaoDich();
        }
        return "";
    }

    private TacGia taoTacGia(String ma, String ten) {
        return new TacGia(ma, ten);
    }

    private TheLoai taoTheLoai(String ma, String ten) {
        return new TheLoai(ma, ten);
    }

    private NhaXuatBan taoNhaXuatBan(String ma, String ten, String diaChi) {
        return new NhaXuatBan(ma, ten, diaChi);
    }

    private Sach taoSach(String ma, String ten, TacGia[] dsTG, int namXB, TheLoai tl,
                         NhaXuatBan nxb, double giaBan, int soLuongTon) {
        return new Sach(ma, ten, dsTG, namXB, tl, nxb, giaBan, soLuongTon);
    }

    private Sach timSach(Sach[] ds, int n, String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ds[i].getMaSach().equals(ma)) {
                return ds[i];
            }
        }
        return null;
    }

    private KhachHang timKH(KhachHang[] ds, int n, String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ds[i].getMaKH().equals(ma)) {
                return ds[i];
            }
        }
        return null;
    }

    private NhanVien timNV(NhanVien[] ds, int n, String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ds[i].getMaNV().equals(ma)) {
                return ds[i];
            }
        }
        return null;
    }

    private boolean boQua(String dong) {
        String t = dong.trim();
        return t.isEmpty() || t.startsWith("#");
    }

    private List<String> docDong(File f) {
        if (!f.exists()) {
            System.out.println("Chưa có file " + f.getPath());
            return new ArrayList<>();
        }
        try {
            return Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Lỗi đọc file " + f.getPath() + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void ghiDong(File f, List<String> dong) {
        try {
            File thuMuc = f.getParentFile();
            if (thuMuc != null) {
                thuMuc.mkdirs();
            }
            Files.write(f.toPath(), dong, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Lỗi ghi file " + f.getPath() + ": " + e.getMessage());
        }
    }

    public void xuat() {
        System.out.println("Thư mục dữ liệu: " + new File(duongDan).getAbsolutePath());
        File[] dsFile = { fileSach(), fileHoaDon() };
        for (File f : dsFile) {
            System.out.println("  " + f.getName() + ": " + (f.exists() ? f.length() + " byte" : "chưa có"));
        }
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = (duongDan == null || duongDan.trim().isEmpty()) ? "data" : duongDan.trim();
    }
}
