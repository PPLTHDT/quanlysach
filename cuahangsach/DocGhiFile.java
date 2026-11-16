package cuahangsach;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;

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

    private int dem(Object[] ds) {
        int n = 0;
        if (ds != null) {
            for (Object x : ds) {
                if (x != null) n++;
            }
        }
        return n;
    }

    private void ghiDong(String tenFile, String[] cacDong) {
        File thuMuc = new File(duongDan == null || duongDan.trim().isEmpty() ? "." : duongDan);
        if (!thuMuc.exists()) {
            thuMuc.mkdirs();
        }
        File file = new File(thuMuc, tenFile);
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            if (cacDong != null) {
                for (String d : cacDong) {
                    if (d != null) {
                        bw.write(d);
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Loi ghi file " + tenFile + ": " + e.getMessage());
        }
    }

    private String[] docDong(String tenFile) {
        String[] kq = new String[16];
        int n = 0;
        File thuMuc = new File(duongDan == null || duongDan.trim().isEmpty() ? "." : duongDan);
        File file = new File(thuMuc, tenFile);
        if (!file.exists()) {
            return new String[0];
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String d;
            while ((d = br.readLine()) != null) {
                if (d.trim().isEmpty()) {
                    continue;
                }
                if (n == kq.length) {
                    kq = Arrays.copyOf(kq, kq.length * 2);
                }
                kq[n++] = d;
            }
        } catch (IOException e) {
            System.out.println("Loi doc file " + tenFile + ": " + e.getMessage());
        }
        return Arrays.copyOf(kq, n);
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
        return String.join(SEP, s(n.getCCCD()), s(n.getHoTen()), s(n.getSDT()),
                s(n.getEmail()), s(n.getDiaChi()));
    }

    private void napNguoi(Nguoi n, String[] f) {
        n.setCCCD(f[0]);
        n.setHoTen(f[1]);
        n.setSDT(f[2]);
        n.setEmail(f[3]);
        n.setDiaChi(f[4]);
    }

    public void ghiFileTheLoai(TheLoai[] ds) {
        String[] dong = new String[dem(ds)];
        int k = 0;
        if (ds != null) {
            for (TheLoai x : ds) {
                if (x == null) continue;
                dong[k++] = String.join(SEP, s(x.getMaTheLoai()), s(x.getTenTheLoai()));
            }
        }
        ghiDong(FILE_THE_LOAI, dong);
    }

    public TheLoai[] docFileTheLoai() {
        String[] cacDong = docDong(FILE_THE_LOAI);
        TheLoai[] kq = new TheLoai[cacDong.length];
        int n = 0;
        for (String d : cacDong) {
            try {
                String[] f = tach(d, 2);
                TheLoai x = new TheLoai();
                x.setMaTheLoai(f[0]);
                x.setTenTheLoai(f[1]);
                kq[n++] = x;
            } catch (RuntimeException e) {
                canhBao(FILE_THE_LOAI, d, e);
            }
        }
        return Arrays.copyOf(kq, n);
    }

    public void ghiFileTacGia(TacGia[] ds) {
        String[] dong = new String[dem(ds)];
        int k = 0;
        if (ds != null) {
            for (TacGia x : ds) {
                if (x == null) continue;
                dong[k++] = String.join(SEP, s(x.getMaTacGia()), s(x.getTenTacGia()));
            }
        }
        ghiDong(FILE_TAC_GIA, dong);
    }

    public TacGia[] docFileTacGia() {
        String[] cacDong = docDong(FILE_TAC_GIA);
        TacGia[] kq = new TacGia[cacDong.length];
        int n = 0;
        for (String d : cacDong) {
            try {
                String[] f = tach(d, 2);
                TacGia x = new TacGia();
                x.setMaTacGia(f[0]);
                x.setTenTacGia(f[1]);
                kq[n++] = x;
            } catch (RuntimeException e) {
                canhBao(FILE_TAC_GIA, d, e);
            }
        }
        return Arrays.copyOf(kq, n);
    }

    public void ghiFileNhaXuatBan(NhaXuatBan[] ds) {
        String[] dong = new String[dem(ds)];
        int k = 0;
        if (ds != null) {
            for (NhaXuatBan x : ds) {
                if (x == null) continue;
                dong[k++] = String.join(SEP, s(x.getMaNXB()), s(x.getTenNXB()), s(x.getDiaChi()));
            }
        }
        ghiDong(FILE_NXB, dong);
    }

    public NhaXuatBan[] docFileNhaXuatBan() {
        String[] cacDong = docDong(FILE_NXB);
        NhaXuatBan[] kq = new NhaXuatBan[cacDong.length];
        int n = 0;
        for (String d : cacDong) {
            try {
                String[] f = tach(d, 3);
                NhaXuatBan x = new NhaXuatBan();
                x.setMaNXB(f[0]);
                x.setTenNXB(f[1]);
                x.setDiaChi(f[2]);
                kq[n++] = x;
            } catch (RuntimeException e) {
                canhBao(FILE_NXB, d, e);
            }
        }
        return Arrays.copyOf(kq, n);
    }

    public void ghiFileKhuyenMai(KhuyenMai[] ds) {
        String[] dong = new String[dem(ds)];
        int k = 0;
        if (ds != null) {
            for (KhuyenMai x : ds) {
                if (x == null) continue;
                dong[k++] = String.join(SEP, s(x.getMaKM()), s(x.getTenKM()),
                        String.valueOf(x.getPhanTramGiam()),
                        ngay(x.getNgayBatDau()), ngay(x.getNgayKetThuc()));
            }
        }
        ghiDong(FILE_KHUYEN_MAI, dong);
    }

    public KhuyenMai[] docFileKhuyenMai() {
        String[] cacDong = docDong(FILE_KHUYEN_MAI);
        KhuyenMai[] kq = new KhuyenMai[cacDong.length];
        int n = 0;
        for (String d : cacDong) {
            try {
                String[] f = tach(d, 5);
                KhuyenMai x = new KhuyenMai();
                x.setMaKM(f[0]);
                x.setTenKM(f[1]);
                x.setPhanTramGiam(Double.parseDouble(f[2]));
                x.setNgayBatDau(docNgay(f[3]));
                x.setNgayKetThuc(docNgay(f[4]));
                kq[n++] = x;
            } catch (RuntimeException e) {
                canhBao(FILE_KHUYEN_MAI, d, e);
            }
        }
        return Arrays.copyOf(kq, n);
    }

    public void ghiFileKhachHang(KhachHang[] ds) {
        String[] dong = new String[dem(ds)];
        int k = 0;
        if (ds != null) {
            for (KhachHang x : ds) {
                if (x == null) continue;
                dong[k++] = ghiNguoi(x) + SEP + s(x.getMaKH()) + SEP + x.getDiemTichLuy();
            }
        }
        ghiDong(FILE_KHACH_HANG, dong);
    }

    public KhachHang[] docFileKhachHang() {
        String[] cacDong = docDong(FILE_KHACH_HANG);
        KhachHang[] kq = new KhachHang[cacDong.length];
        int n = 0;
        for (String d : cacDong) {
            try {
                String[] f = tach(d, 7);
                KhachHang x = new KhachHang();
                napNguoi(x, f);
                x.setMaKH(f[5]);
                x.setDiemTichLuy(Integer.parseInt(f[6]));
                kq[n++] = x;
            } catch (RuntimeException e) {
                canhBao(FILE_KHACH_HANG, d, e);
            }
        }
        return Arrays.copyOf(kq, n);
    }

    public void ghiFileNhanVien(NhanVien[] ds) {
        String[] dong = new String[dem(ds)];
        int k = 0;
        if (ds != null) {
            for (NhanVien x : ds) {
                if (x == null) continue;
                dong[k++] = ghiNguoi(x) + SEP + s(x.getMaNV()) + SEP + s(x.getChucVu())
                        + SEP + x.getLuongCoBanNV();
            }
        }
        ghiDong(FILE_NHAN_VIEN, dong);
    }

    public NhanVien[] docFileNhanVien() {
        String[] cacDong = docDong(FILE_NHAN_VIEN);
        NhanVien[] kq = new NhanVien[cacDong.length];
        int n = 0;
        for (String d : cacDong) {
            try {
                String[] f = tach(d, 8);
                NhanVien x = new NhanVien();
                napNguoi(x, f);
                x.setMaNV(f[5]);
                x.setChucVu(f[6]);
                x.setLuongCoBanNV(Double.parseDouble(f[7]));
                kq[n++] = x;
            } catch (RuntimeException e) {
                canhBao(FILE_NHAN_VIEN, d, e);
            }
        }
        return Arrays.copyOf(kq, n);
    }

    public void ghiFileQuanLy(QuanLy[] ds) {
        String[] dong = new String[dem(ds)];
        int k = 0;
        if (ds != null) {
            for (QuanLy x : ds) {
                if (x == null) continue;
                dong[k++] = ghiNguoi(x) + SEP + s(x.getMaQL()) + SEP + x.getPhuCap()
                        + SEP + x.getLuongCoBanQL();
            }
        }
        ghiDong(FILE_QUAN_LY, dong);
    }

    public QuanLy[] docFileQuanLy() {
        String[] cacDong = docDong(FILE_QUAN_LY);
        QuanLy[] kq = new QuanLy[cacDong.length];
        int n = 0;
        for (String d : cacDong) {
            try {
                String[] f = tach(d, 8);
                QuanLy x = new QuanLy();
                napNguoi(x, f);
                x.setMaQL(f[5]);
                x.setPhuCap(Double.parseDouble(f[6]));
                x.setLuongCoBanQL(Double.parseDouble(f[7]));
                kq[n++] = x;
            } catch (RuntimeException e) {
                canhBao(FILE_QUAN_LY, d, e);
            }
        }
        return Arrays.copyOf(kq, n);
    }

    public void ghiFileSach(Sach[] ds) {
        String[] dong = new String[dem(ds)];
        int k = 0;
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
                dong[k++] = String.join(SEP, s(x.getMaSach()), s(x.getTenSach()), tg.toString(),
                        String.valueOf(x.getNamXuatBan()),
                        x.getTheLoai() == null ? "" : s(x.getTheLoai().getMaTheLoai()),
                        x.getNhaXuatBan() == null ? "" : s(x.getNhaXuatBan().getMaNXB()),
                        String.valueOf(x.getGiaBan()),
                        String.valueOf(x.getSoLuongTon()));
            }
        }
        ghiDong(FILE_SACH, dong);
    }

    public Sach[] docFileSach(DanhSachTheLoai dsTL, DanhSachTacGia dsTG, DanhSachNhaXuatBan dsNXB) {
        String[] cacDong = docDong(FILE_SACH);
        Sach[] kq = new Sach[cacDong.length];
        int n = 0;
        for (String d : cacDong) {
            try {
                String[] f = tach(d, 8);
                Sach x = new Sach();
                x.setMaSach(f[0]);
                x.setTenSach(f[1]);

                String[] maTG = f[2].isEmpty() ? new String[0] : f[2].split(",");
                TacGia[] tgs = new TacGia[maTG.length];
                int m = 0;
                for (String ma : maTG) {
                    TacGia t = dsTG.timKiem(ma.trim());
                    if (t != null) tgs[m++] = t;
                }
                x.setDsTacGia(Arrays.copyOf(tgs, m));

                x.setNamXuatBan(Integer.parseInt(f[3]));
                x.setTheLoai(f[4].isEmpty() ? null : dsTL.timKiem(f[4]));
                x.setNhaXuatBan(f[5].isEmpty() ? null : dsNXB.timKiem(f[5]));
                x.setGiaBan(Double.parseDouble(f[6]));
                x.setSoLuongTon(Integer.parseInt(f[7]));
                kq[n++] = x;
            } catch (RuntimeException e) {
                canhBao(FILE_SACH, d, e);
            }
        }
        return Arrays.copyOf(kq, n);
    }

    public void ghiFileHoaDon(HoaDon[] ds) {
        int n = 0;
        if (ds != null) {
            for (HoaDon h : ds) {
                if (h == null) continue;
                n++;
                ChiTietHoaDon[] dsCT = h.getDsChiTiet();
                if (dsCT != null) {
                    for (ChiTietHoaDon ct : dsCT) {
                        if (ct != null) n++;
                    }
                }
            }
        }
        String[] dong = new String[n];
        int k = 0;
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

                dong[k++] = String.join(SEP, "HD", s(h.getMaHD()), ngay(h.getNgayLap()),
                        h.getKhachHang() == null ? "" : s(h.getKhachHang().getMaKH()),
                        h.getNhanVien() == null ? "" : s(h.getNhanVien().getMaNV()),
                        h.getKhuyenMai() == null ? "" : s(h.getKhuyenMai().getMaKM()),
                        String.valueOf(h.getTienGiam()), String.valueOf(h.getTongTien()),
                        loai, p1, p2, p3);

                ChiTietHoaDon[] dsCT = h.getDsChiTiet();
                if (dsCT != null) {
                    for (ChiTietHoaDon ct : dsCT) {
                        if (ct == null) continue;
                        dong[k++] = ct.dongFile();
                    }
                }
            }
        }
        ghiDong(FILE_HOA_DON, dong);
    }

    public HoaDon[] docFileHoaDon(DanhSachKhachHang dsKH, DanhSachNhanVien dsNV,
                                  DanhSachKhuyenMai dsKM, DanhSachSach dsSach) {
        String[] dong = docDong(FILE_HOA_DON);
        HoaDon[] kq = new HoaDon[dong.length];
        int soHD = 0;
        int i = 0;
        while (i < dong.length) {
            int j = i + 1;
            while (j < dong.length && dong[j].startsWith("CT" + SEP)) {
                j++;
            }
            String dongHD = dong[i];
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

                for (int c = i + 1; c < j; c++) {
                    try {
                        String[] t = tach(dong[c], 5);
                        Sach sach = dsSach.timKiem(t[2]);
                        if (sach == null) {
                            System.out.println("[hoadon.txt] Bo qua chi tiet vi khong thay sach: " + dong[c]);
                            continue;
                        }
                        ChiTietHoaDon ct = new ChiTietHoaDon(t[1], sach, Integer.parseInt(t[3]));
                        ct.setDonGia(Double.parseDouble(t[4]));
                        h.themChiTiet(ct);
                    } catch (RuntimeException e) {
                        canhBao(FILE_HOA_DON, dong[c], e);
                    }
                }

                h.setTongTien(Double.parseDouble(f[7]));
                h.setTienGiam(Double.parseDouble(f[6]));

                if (f[8].equals("TM")) {
                    h.setThanhToan(new ThanhToanTienMat(Double.parseDouble(f[9])));
                } else if (f[8].equals("CK")) {
                    h.setThanhToan(new ThanhToanChuyenKhoan(f[9], f[10], f[11]));
                }
                kq[soHD++] = h;
            } catch (RuntimeException e) {
                canhBao(FILE_HOA_DON, dongHD, e);
            }
            i = j;
        }
        return Arrays.copyOf(kq, soHD);
    }

    public void ghiFilePhieuNhap(PhieuNhap[] ds) {
        int n = 0;
        if (ds != null) {
            for (PhieuNhap p : ds) {
                if (p == null) continue;
                n++;
                ChiTietPhieuNhap[] dsCT = p.getDsChiTiet();
                if (dsCT != null) {
                    for (ChiTietPhieuNhap ct : dsCT) {
                        if (ct != null) n++;
                    }
                }
            }
        }
        String[] dong = new String[n];
        int k = 0;
        if (ds != null) {
            for (PhieuNhap p : ds) {
                if (p == null) continue;
                dong[k++] = String.join(SEP, "PN", s(p.getMaPhieuNhap()), ngay(p.getNgayNhap()),
                        p.getNhaXuatBan() == null ? "" : s(p.getNhaXuatBan().getMaNXB()),
                        String.valueOf(p.getTongTien()));
                ChiTietPhieuNhap[] dsCT = p.getDsChiTiet();
                if (dsCT != null) {
                    for (ChiTietPhieuNhap ct : dsCT) {
                        if (ct == null) continue;
                        dong[k++] = ct.dongFile();
                    }
                }
            }
        }
        ghiDong(FILE_PHIEU_NHAP, dong);
    }

    public PhieuNhap[] docFilePhieuNhap(DanhSachNhaXuatBan dsNXB, DanhSachSach dsSach) {
        String[] dong = docDong(FILE_PHIEU_NHAP);
        PhieuNhap[] kq = new PhieuNhap[dong.length];
        int soPN = 0;
        int i = 0;
        while (i < dong.length) {
            int j = i + 1;
            while (j < dong.length && dong[j].startsWith("CT" + SEP)) {
                j++;
            }
            String dongPN = dong[i];
            try {
                if (!dongPN.startsWith("PN" + SEP)) {
                    throw new IllegalArgumentException("dong khong bat dau bang PN");
                }
                String[] f = tach(dongPN, 5);
                NhaXuatBan nxb = f[3].isEmpty() ? null : dsNXB.timKiem(f[3]);
                PhieuNhap p = new PhieuNhap(f[1], docNgay(f[2]), nxb);

                for (int c = i + 1; c < j; c++) {
                    try {
                        String[] t = tach(dong[c], 5);
                        Sach sach = dsSach.timKiem(t[2]);
                        if (sach == null) {
                            System.out.println("[phieunhap.txt] Bo qua chi tiet vi khong thay sach: " + dong[c]);
                            continue;
                        }
                        p.themChiTiet(new ChiTietPhieuNhap(t[1], sach,
                                Integer.parseInt(t[3]), Double.parseDouble(t[4])));
                    } catch (RuntimeException e) {
                        canhBao(FILE_PHIEU_NHAP, dong[c], e);
                    }
                }
                p.setTongTien(Double.parseDouble(f[4]));
                kq[soPN++] = p;
            } catch (RuntimeException e) {
                canhBao(FILE_PHIEU_NHAP, dongPN, e);
            }
            i = j;
        }
        return Arrays.copyOf(kq, soPN);
    }
}
