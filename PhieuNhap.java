package cuahangsach;

import java.time.LocalDate;

public class PhieuNhap {
    private String maPhieuNhap;
    private LocalDate ngayNhap;
    private NhaXuatBan nhaXuatBan;
    private ChiTietPhieuNhap[] dsChiTiet;
    private int soLuongChiTiet;
    private double tongTien;
    public static final int MAX_CHI_TIET = 50;

    public PhieuNhap() {
        this.maPhieuNhap = "";
        this.ngayNhap = LocalDate.now();
        this.nhaXuatBan = null;
        this.dsChiTiet = new ChiTietPhieuNhap[MAX_CHI_TIET];
        this.soLuongChiTiet = 0;
        this.tongTien = 0.0;
    }

    public PhieuNhap(String maPhieuNhap, LocalDate ngayNhap, NhaXuatBan nhaXuatBan) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = (ngayNhap != null) ? ngayNhap : LocalDate.now();
        this.nhaXuatBan = nhaXuatBan;
        this.dsChiTiet = new ChiTietPhieuNhap[MAX_CHI_TIET];
        this.soLuongChiTiet = 0;
        this.tongTien = 0.0;
    }

    public PhieuNhap(PhieuNhap other) {
        this();
        if (other != null) {
            this.maPhieuNhap = other.maPhieuNhap;
            this.ngayNhap = other.ngayNhap;
            this.nhaXuatBan = other.nhaXuatBan;
            this.soLuongChiTiet = other.soLuongChiTiet;
            for (int i = 0; i < other.soLuongChiTiet; i++) {
                this.dsChiTiet[i] = (other.dsChiTiet[i] != null)
                        ? new ChiTietPhieuNhap(other.dsChiTiet[i])
                        : null;
            }
            this.tongTien = other.tongTien;
        }
    }

    public boolean themChiTiet(ChiTietPhieuNhap ct) {
        if (ct == null || ct.getSach() == null || ct.getSoLuongNhap() <= 0 || ct.getDonGiaNhap() < 0) {
            return false;
        }
        // Neu sach da co trong phieu nhap thi cong don so luong
        for (int i = 0; i < soLuongChiTiet; i++) {
            if (dsChiTiet[i].getSach() != null
                    && dsChiTiet[i].getSach().getMaSach().equalsIgnoreCase(ct.getSach().getMaSach())) {
                dsChiTiet[i].setSoLuongNhap(dsChiTiet[i].getSoLuongNhap() + ct.getSoLuongNhap());
                dsChiTiet[i].setDonGiaNhap(ct.getDonGiaNhap());
                tinhTongTien();
                return true;
            }
        }
        if (soLuongChiTiet >= MAX_CHI_TIET) {
            return false;
        }
        dsChiTiet[soLuongChiTiet++] = ct;
        tinhTongTien();
        return true;
    }

    public double tinhTongTien() {
        tongTien = 0.0;
        for (int i = 0; i < soLuongChiTiet; i++) {
            if (dsChiTiet[i] != null) {
                tongTien += dsChiTiet[i].tinhThanhTien();
            }
        }
        return tongTien;
    }

    public void nhapKho() {
        for (int i = 0; i < soLuongChiTiet; i++) {
            if (dsChiTiet[i] != null) {
                dsChiTiet[i].capNhatSoLuong();
            }
        }
    }

    public void inPhieuNhap() {
        GiaoDienConsole.inTieuDe("PHIEU NHAP HANG");
        GiaoDienConsole.inMuc("Ma phieu nhap", maPhieuNhap);
        GiaoDienConsole.inMuc("Ngay nhap", Main.dinhDangNgay(ngayNhap));
        GiaoDienConsole.inMuc("Nha xuat ban", nhaXuatBan != null ? nhaXuatBan.getTenNXB() : "(Chua co)");
        System.out.println();
        if (soLuongChiTiet == 0) {
            System.out.println("  Chua co chi tiet nao.");
        } else {
            System.out.printf("  %-4s | %-8s | %-24s | %5s | %-16s | %-16s%n",
                    "STT", "Ma CT", "Ten sach", "SL", "Don gia", "Thanh tien");
            System.out.println("  -------------------------------------------------------------------------------");
            for (int i = 0; i < soLuongChiTiet; i++) {
                ChiTietPhieuNhap ct = dsChiTiet[i];
                String tenSach = (ct.getSach() != null) ? ct.getSach().getTenSach() : "(Khong co ten)";
                System.out.printf("  %-4d | %-8s | %-24s | %5d | %-16s | %-16s%n",
                        (i + 1), ct.getMaCT(), tenSach, ct.getSoLuongNhap(),
                        Main.dinhDangTien(ct.getDonGiaNhap()),
                        Main.dinhDangTien(ct.tinhThanhTien()));
            }
        }
        GiaoDienConsole.inPhanCach();
        GiaoDienConsole.inMuc("Tong tien", Main.dinhDangTien(tinhTongTien()));
        GiaoDienConsole.inPhanCach();
    }

    // Getter & Setter
    public String getMaPhieuNhap() { return maPhieuNhap; }
    public void setMaPhieuNhap(String maPhieuNhap) { this.maPhieuNhap = maPhieuNhap; }

    public LocalDate getNgayNhap() { return ngayNhap; }
    public void setNgayNhap(LocalDate ngayNhap) { this.ngayNhap = ngayNhap; }

    public NhaXuatBan getNhaXuatBan() { return nhaXuatBan; }
    public void setNhaXuatBan(NhaXuatBan nhaXuatBan) { this.nhaXuatBan = nhaXuatBan; }

    public ChiTietPhieuNhap[] getDsChiTiet() {
        ChiTietPhieuNhap[] kq = new ChiTietPhieuNhap[soLuongChiTiet];
        for (int i = 0; i < soLuongChiTiet; i++) {
            kq[i] = dsChiTiet[i];
        }
        return kq;
    }

    public void setDsChiTiet(ChiTietPhieuNhap[] dsChiTiet) {
        this.dsChiTiet = new ChiTietPhieuNhap[MAX_CHI_TIET];
        this.soLuongChiTiet = 0;
        if (dsChiTiet != null) {
            for (int i = 0; i < dsChiTiet.length && soLuongChiTiet < MAX_CHI_TIET; i++) {
                if (dsChiTiet[i] != null) {
                    this.dsChiTiet[soLuongChiTiet++] = dsChiTiet[i];
                }
            }
        }
        tinhTongTien();
    }

    public int getSoLuongChiTiet() { return soLuongChiTiet; }
    public void setSoLuongChiTiet(int soLuongChiTiet) { this.soLuongChiTiet = soLuongChiTiet; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
}
