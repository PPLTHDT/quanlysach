import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DocGhiFile {

    private static final String SEP = "|";
    private static final String SEP_REGEX = "\\|";

    private static final String FILE_THE_LOAI = "theloai.txt";
    private static final String FILE_TAC_GIA = "tacgia.txt";
    private static final String FILE_NXB = "nxb.txt";
    private static final String FILE_SACH = "sach.txt";
    private static final String FILE_KHACH_HANG = "khachhang.txt";
    private static final String FILE_NHAN_VIEN = "nhanvien.txt";
    private static final String FILE_QUAN_LY = "quanly.txt";
    private static final String FILE_KHUYEN_MAI = "khuyenmai.txt";
    private static final String FILE_HOA_DON = "hoadon.txt";
    private static final String FILE_PHIEU_NHAP = "phieunhap.txt";

    private String duongDan;

    public DocGhiFile() {
        this("data");
    }

    public DocGhiFile(String duongDan) {
        this.duongDan = duongDan;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    private void ghiDong(String tenFile, List<String> dong) {
        try {
            Path thuMuc = Paths.get(duongDan == null || duongDan.trim().isEmpty() ? "." : duongDan);
            Files.createDirectories(thuMuc);
            Files.write(thuMuc.resolve(tenFile), dong, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Loi ghi file " + tenFile + ": " + e.getMessage());
        }
    }

    private List<String> docDong(String tenFile) {
        List<String> kq = new ArrayList<>();
        try {
            Path file = Paths.get(duongDan == null || duongDan.trim().isEmpty() ? "." : duongDan)
                    .resolve(tenFile);
            if (!Files.exists(file)) {
                return kq;
            }
            for (String d : Files.readAllLines(file, StandardCharsets.UTF_8)) {
                if (!d.trim().isEmpty()) {
                    kq.add(d);
                }
            }
        } catch (IOException e) {
            System.out.println("Loi doc file " + tenFile + ": " + e.getMessage());
        }
        return kq;
    }

    private String[] tach(String dong, int soTruong) {
        String[] f = dong.split(SEP_REGEX, -1);
        if (f.length < soTruong) {
            throw new IllegalArgumentException("thieu truong (can " + soTruong + ", co " + f.length + ")");
        }
        return f;
    }

    private void canhBao(String tenFile, String dong, RuntimeException e) {
        System.out.println("[" + tenFile + "] Bo qua dong loi (" + e.getMessage() + "): " + dong);
    }

    private String s(String v) {
        if (v == null) return "";
        return v.replace('|', '/').replace('\r', ' ').replace('\n', ' ');
    }

    private String ngay(LocalDate d) {
        return d == null ? "" : d.toString();
    }

    private LocalDate docNgay(String v) {
        return (v == null || v.isEmpty()) ? null : LocalDate.parse(v);
    }

    private String ghiNguoi(Nguoi n) {
        return String.join(SEP, s(n.getCCCD()), s(n.getHoTen()), s(n.getSdt()),
                s(n.getEmail()), s(n.getDiaChi()));
    }

    private void napNguoi(Nguoi n, String[] f) {
        n.setCCCD(f[0]);
        n.setHoTen(f[1]);
        n.setSdt(f[2]);
        n.setEmail(f[3]);
        n.setDiaChi(f[4]);
    }

    public void ghiFileTheLoai(TheLoai[] ds) {
        List<String> dong = new ArrayList<>();
        if (ds != null) {
            for (TheLoai x : ds) {
                if (x == null) continue;
                dong.add(String.join(SEP, s(x.getMaTheLoai()), s(x.getTenTheLoai())));
            }
        }
        ghiDong(FILE_THE_LOAI, dong);
    }

    public TheLoai[] docFileTheLoai() {
        List<TheLoai> kq = new ArrayList<>();
        for (String d : docDong(FILE_THE_LOAI)) {
            try {
                String[] f = tach(d, 2);
                TheLoai x = new TheLoai();
                x.setMaTheLoai(f[0]);
                x.setTenTheLoai(f[1]);
                kq.add(x);
            } catch (RuntimeException e) {
                canhBao(FILE_THE_LOAI, d, e);
            }
        }
        return kq.toArray(new TheLoai[0]);
    }

    public void ghiFileTacGia(TacGia[] ds) {
        List<String> dong = new ArrayList<>();
        if (ds != null) {
            for (TacGia x : ds) {
                if (x == null) continue;
                dong.add(String.join(SEP, s(x.getMaTacGia()), s(x.getTenTacGia())));
            }
        }
        ghiDong(FILE_TAC_GIA, dong);
    }

    public TacGia[] docFileTacGia() {
        List<TacGia> kq = new ArrayList<>();
        for (String d : docDong(FILE_TAC_GIA)) {
            try {
                String[] f = tach(d, 2);
                TacGia x = new TacGia();
                x.setMaTacGia(f[0]);
                x.setTenTacGia(f[1]);
                kq.add(x);
            } catch (RuntimeException e) {
                canhBao(FILE_TAC_GIA, d, e);
            }
        }
        return kq.toArray(new TacGia[0]);
    }

    public void ghiFileNhaXuatBan(NhaXuatBan[] ds) {
        List<String> dong = new ArrayList<>();
        if (ds != null) {
            for (NhaXuatBan x : ds) {
                if (x == null) continue;
                dong.add(String.join(SEP, s(x.getMaNXB()), s(x.getTenNXB()), s(x.getDiaChi())));
            }
        }
        ghiDong(FILE_NXB, dong);
    }

    public NhaXuatBan[] docFileNhaXuatBan() {
        List<NhaXuatBan> kq = new ArrayList<>();
        for (String d : docDong(FILE_NXB)) {
            try {
                String[] f = tach(d, 3);
                NhaXuatBan x = new NhaXuatBan();
                x.setMaNXB(f[0]);
                x.setTenNXB(f[1]);
                x.setDiaChi(f[2]);
                kq.add(x);
            } catch (RuntimeException e) {
                canhBao(FILE_NXB, d, e);
            }
        }
        return kq.toArray(new NhaXuatBan[0]);
    }

    public void ghiFileKhuyenMai(KhuyenMai[] ds) {
        List<String> dong = new ArrayList<>();
        if (ds != null) {
            for (KhuyenMai x : ds) {
                if (x == null) continue;
                dong.add(String.join(SEP, s(x.getMaKM()), s(x.getTenKM()),
                        String.valueOf(x.getPhanTramGiam()),
                        ngay(x.getNgayBatDau()), ngay(x.getNgayKetThuc())));
            }
        }
        ghiDong(FILE_KHUYEN_MAI, dong);
    }

    public KhuyenMai[] docFileKhuyenMai() {
        List<KhuyenMai> kq = new ArrayList<>();
        for (String d : docDong(FILE_KHUYEN_MAI)) {
            try {
                String[] f = tach(d, 5);
                KhuyenMai x = new KhuyenMai();
                x.setMaKM(f[0]);
                x.setTenKM(f[1]);
                x.setPhanTramGiam(Double.parseDouble(f[2]));
                x.setNgayBatDau(docNgay(f[3]));
                x.setNgayKetThuc(docNgay(f[4]));
                kq.add(x);
            } catch (RuntimeException e) {
                canhBao(FILE_KHUYEN_MAI, d, e);
            }
        }
        return kq.toArray(new KhuyenMai[0]);
    }

    public void ghiFileKhachHang(KhachHang[] ds) {
        List<String> dong = new ArrayList<>();
        if (ds != null) {
            for (KhachHang x : ds) {
                if (x == null) continue;
                dong.add(ghiNguoi(x) + SEP + s(x.getMaKH()) + SEP + x.getDiemTichLuy());
            }
        }
        ghiDong(FILE_KHACH_HANG, dong);
    }

    public KhachHang[] docFileKhachHang() {
        List<KhachHang> kq = new ArrayList<>();
        for (String d : docDong(FILE_KHACH_HANG)) {
            try {
                String[] f = tach(d, 7);
                KhachHang x = new KhachHang();
                napNguoi(x, f);
                x.setMaKH(f[5]);
                x.setDiemTichLuy(Integer.parseInt(f[6]));
                kq.add(x);
            } catch (RuntimeException e) {
                canhBao(FILE_KHACH_HANG, d, e);
            }
        }
        return kq.toArray(new KhachHang[0]);
    }

    public void ghiFileNhanVien(NhanVien[] ds) {
        List<String> dong = new ArrayList<>();
        if (ds != null) {
            for (NhanVien x : ds) {
                if (x == null) continue;
                dong.add(ghiNguoi(x) + SEP + s(x.getMaNV()) + SEP + s(x.getChucVu())
                        + SEP + x.getLuongCoBanNV());
            }
        }
        ghiDong(FILE_NHAN_VIEN, dong);
    }

    public NhanVien[] docFileNhanVien() {
        List<NhanVien> kq = new ArrayList<>();
        for (String d : docDong(FILE_NHAN_VIEN)) {
            try {
                String[] f = tach(d, 8);
                NhanVien x = new NhanVien();
                napNguoi(x, f);
                x.setMaNV(f[5]);
                x.setChucVu(f[6]);
                x.setLuongCoBanNV(Double.parseDouble(f[7]));
                kq.add(x);
            } catch (RuntimeException e) {
                canhBao(FILE_NHAN_VIEN, d, e);
            }
        }
        return kq.toArray(new NhanVien[0]);
    }

    public void ghiFileQuanLy(QuanLy[] ds) {
        List<String> dong = new ArrayList<>();
        if (ds != null) {
            for (QuanLy x : ds) {
                if (x == null) continue;
                dong.add(ghiNguoi(x) + SEP + s(x.getMaQL()) + SEP + x.getPhuCap()
                        + SEP + x.getLuongCoBanQL());
            }
        }
        ghiDong(FILE_QUAN_LY, dong);
    }

    public QuanLy[] docFileQuanLy() {
        List<QuanLy> kq = new ArrayList<>();
        for (String d : docDong(FILE_QUAN_LY)) {
            try {
                String[] f = tach(d, 8);
                QuanLy x = new QuanLy();
                napNguoi(x, f);
                x.setMaQL(f[5]);
                x.setPhuCap(Double.parseDouble(f[6]));
                x.setLuongCoBanQL(Double.parseDouble(f[7]));
                kq.add(x);
            } catch (RuntimeException e) {
                canhBao(FILE_QUAN_LY, d, e);
            }
        }
        return kq.toArray(new QuanLy[0]);
    }

    public void ghiFileSach(Sach[] ds) {
        List<String> dong = new ArrayList<>();
        if (ds != null) {
            for (Sach x : ds) {
                if (x == null) continue;
                StringBuilder tg = new StringBuilder();
                TacGia[] dsTG = x.getDsTacGia();
                if (dsTG != null) {
                    for (TacGia t : dsTG) {
                        if (t == null) continue;
                        if (tg.length() > 0) tg.append(',');
                        tg.append(s(t.getMaTacGia()));
                    }
                }
                dong.add(String.join(SEP, s(x.getMaSach()), s(x.getTenSach()), tg.toString(),
                        String.valueOf(x.getNamXuatBan()),
                        x.getTheLoai() == null ? "" : s(x.getTheLoai().getMaTheLoai()),
                        x.getNhaXuatBan() == null ? "" : s(x.getNhaXuatBan().getMaNXB()),
                        String.valueOf(x.getGiaBan()),
                        String.valueOf(x.getSoLuongTon())));
            }
        }
        ghiDong(FILE_SACH, dong);
    }

    public Sach[] docFileSach(DanhSachTheLoai dsTL, DanhSachTacGia dsTG, DanhSachNhaXuatBan dsNXB) {
        List<Sach> kq = new ArrayList<>();
        for (String d : docDong(FILE_SACH)) {
            try {
                String[] f = tach(d, 8);
                Sach x = new Sach();
                x.setMaSach(f[0]);
                x.setTenSach(f[1]);

                List<TacGia> tgs = new ArrayList<>();
                if (!f[2].isEmpty()) {
                    for (String ma : f[2].split(",")) {
                        TacGia t = dsTG.timKiem(ma.trim());
                        if (t != null) tgs.add(t);
                    }
                }
                x.setDsTacGia(tgs.toArray(new TacGia[0]));

                x.setNamXuatBan(Integer.parseInt(f[3]));
                x.setTheLoai(f[4].isEmpty() ? null : dsTL.timKiem(f[4]));
                x.setNhaXuatBan(f[5].isEmpty() ? null : dsNXB.timKiem(f[5]));
                x.setGiaBan(Double.parseDouble(f[6]));
                x.setSoLuongTon(Integer.parseInt(f[7]));
                kq.add(x);
            } catch (RuntimeException e) {
                canhBao(FILE_SACH, d, e);
            }
        }
        return kq.toArray(new Sach[0]);
    }

    public void ghiFileHoaDon(HoaDon[] ds) {
        List<String> dong = new ArrayList<>();
        if (ds != null) {
            for (HoaDon h : ds) {
                if (h == null) continue;

                String loai = "NONE", p1 = "", p2 = "", p3 = "";
                ThanhToan tt = h.getThanhToan();
                if (tt instanceof ThanhToanTienMat) {
                    loai = "TM";
                    p1 = String.valueOf(((ThanhToanTienMat) tt).getTienNhan());
                } else if (tt instanceof ThanhToanChuyenKhoan) {
                    ThanhToanChuyenKhoan ck = (ThanhToanChuyenKhoan) tt;
                    loai = "CK";
                    p1 = s(ck.getSoTaiKhoan());
                    p2 = s(ck.getNganHang());
                    p3 = s(ck.getMaGiaoDich());
                }

                dong.add(String.join(SEP, "HD", s(h.getMaHD()), ngay(h.getNgayLap()),
                        h.getKhachHang() == null ? "" : s(h.getKhachHang().getMaKH()),
                        h.getNhanVien() == null ? "" : s(h.getNhanVien().getMaNV()),
                        h.getKhuyenMai() == null ? "" : s(h.getKhuyenMai().getMaKM()),
                        String.valueOf(h.getTienGiam()), String.valueOf(h.getTongTien()),
                        loai, p1, p2, p3));

                for (ChiTietHoaDon ct : h.getDsChiTiet()) {
                    if (ct == null) continue;
                    dong.add(String.join(SEP, "CT", s(ct.getMaCT()),
                            ct.getSach() == null ? "" : s(ct.getSach().getMaSach()),
                            String.valueOf(ct.getSoLuong()), String.valueOf(ct.getDonGia())));
                }
            }
        }
        ghiDong(FILE_HOA_DON, dong);
    }

    public HoaDon[] docFileHoaDon(DanhSachKhachHang dsKH, DanhSachNhanVien dsNV,
                                  DanhSachKhuyenMai dsKM, DanhSachSach dsSach) {
        List<String> dong = docDong(FILE_HOA_DON);
        List<HoaDon> kq = new ArrayList<>();
        int i = 0;
        while (i < dong.size()) {
            int j = i + 1;
            while (j < dong.size() && dong.get(j).startsWith("CT" + SEP)) {
                j++;
            }
            String dongHD = dong.get(i);
            try {
                if (!dongHD.startsWith("HD" + SEP)) {
                    throw new IllegalArgumentException("dong khong bat dau bang HD");
                }
                String[] f = tach(dongHD, 12);
                KhachHang kh = f[3].isEmpty() ? null : dsKH.timKiem(f[3]);
                NhanVien nv = f[4].isEmpty() ? null : dsNV.timKiem(f[4]);
                KhuyenMai km = f[5].isEmpty() ? null : dsKM.timKiem(f[5]);

                HoaDon h = new HoaDon(f[1], docNgay(f[2]), kh, nv);
                h.apDungKhuyenMai(km);

                for (int k = i + 1; k < j; k++) {
                    try {
                        String[] c = tach(dong.get(k), 5);
                        Sach sach = dsSach.timKiem(c[2]);
                        if (sach == null) {
                            System.out.println("[hoadon.txt] Bo qua chi tiet vi khong thay sach: " + dong.get(k));
                            continue;
                        }
                        ChiTietHoaDon ct = new ChiTietHoaDon(c[1], sach, Integer.parseInt(c[3]));
                        ct.setDonGia(Double.parseDouble(c[4]));
                        h.themChiTiet(ct);
                    } catch (RuntimeException e) {
                        canhBao(FILE_HOA_DON, dong.get(k), e);
                    }
                }

                h.setTongTien(Double.parseDouble(f[7]));
                h.setTienGiam(Double.parseDouble(f[6]));

                if (f[8].equals("TM")) {
                    h.setThanhToan(new ThanhToanTienMat(Double.parseDouble(f[9])));
                } else if (f[8].equals("CK")) {
                    h.setThanhToan(new ThanhToanChuyenKhoan(f[9], f[10], f[11]));
                }
                kq.add(h);
            } catch (RuntimeException e) {
                canhBao(FILE_HOA_DON, dongHD, e);
            }
            i = j;
        }
        return kq.toArray(new HoaDon[0]);
    }

    public void ghiFilePhieuNhap(PhieuNhap[] ds) {
        List<String> dong = new ArrayList<>();
        if (ds != null) {
            for (PhieuNhap p : ds) {
                if (p == null) continue;
                dong.add(String.join(SEP, "PN", s(p.getMaPhieuNhap()), ngay(p.getNgayNhap()),
                        p.getNhaXuatBan() == null ? "" : s(p.getNhaXuatBan().getMaNXB()),
                        String.valueOf(p.getTongTien())));
                for (ChiTietPhieuNhap ct : p.getDsChiTiet()) {
                    if (ct == null) continue;
                    dong.add(String.join(SEP, "CT", s(ct.getMaCT()),
                            ct.getSach() == null ? "" : s(ct.getSach().getMaSach()),
                            String.valueOf(ct.getSoLuongNhap()), String.valueOf(ct.getDonGiaNhap())));
                }
            }
        }
        ghiDong(FILE_PHIEU_NHAP, dong);
    }

    public PhieuNhap[] docFilePhieuNhap(DanhSachNhaXuatBan dsNXB, DanhSachSach dsSach) {
        List<String> dong = docDong(FILE_PHIEU_NHAP);
        List<PhieuNhap> kq = new ArrayList<>();
        int i = 0;
        while (i < dong.size()) {
            int j = i + 1;
            while (j < dong.size() && dong.get(j).startsWith("CT" + SEP)) {
                j++;
            }
            String dongPN = dong.get(i);
            try {
                if (!dongPN.startsWith("PN" + SEP)) {
                    throw new IllegalArgumentException("dong khong bat dau bang PN");
                }
                String[] f = tach(dongPN, 5);
                NhaXuatBan nxb = f[3].isEmpty() ? null : dsNXB.timKiem(f[3]);
                PhieuNhap p = new PhieuNhap(f[1], docNgay(f[2]), nxb);

                for (int k = i + 1; k < j; k++) {
                    try {
                        String[] c = tach(dong.get(k), 5);
                        Sach sach = dsSach.timKiem(c[2]);
                        if (sach == null) {
                            System.out.println("[phieunhap.txt] Bo qua chi tiet vi khong thay sach: " + dong.get(k));
                            continue;
                        }
                        p.themChiTiet(new ChiTietPhieuNhap(c[1], sach,
                                Integer.parseInt(c[3]), Double.parseDouble(c[4])));
                    } catch (RuntimeException e) {
                        canhBao(FILE_PHIEU_NHAP, dong.get(k), e);
                    }
                }
                p.setTongTien(Double.parseDouble(f[4]));
                kq.add(p);
            } catch (RuntimeException e) {
                canhBao(FILE_PHIEU_NHAP, dongPN, e);
            }
            i = j;
        }
        return kq.toArray(new PhieuNhap[0]);
    }
}
 