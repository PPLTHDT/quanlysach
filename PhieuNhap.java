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

    public boolean themChiTiet(ChiTietPhieuNhap ct) {
        if (ct == null || soLuongChiTiet >= MAX_CHI_TIET) {
            return false;
        }
        dsChiTiet[soLuongChiTiet++] = ct;
        tinhTongTien();
        return true;
    }

    public double tinhTongTien() {
        tongTien = 0.0;
        for (int i = 0; i < soLuongChiTiet; i++) {
            tongTien += dsChiTiet[i].tinhThanhTien();
        }
        return tongTien;
    }

    public void nhapKho() {
        for (int i = 0; i < soLuongChiTiet; i++) {
            dsChiTiet[i].capNhatSoLuong();
        }
    }

    public void inPhieuNhap() {
        System.out.println("========== THONG TIN PHIEU NHAP ==========");
        System.out.println("Ma phieu nhap : " + maPhieuNhap);
        System.out.println("Ngay nhap     : " + Main.dinhDangNgay(ngayNhap));
        System.out.println("Nha xuat ban  : " + (nhaXuatBan != null ? nhaXuatBan.getTenNXB() : "(Chua co)"));
        System.out.println("--- Danh sach chi tiet sach nhap ---");
        if (soLuongChiTiet == 0) {
            System.out.println("  (Chua co chi tiet nao)");
        } else {
            for (int i = 0; i < soLuongChiTiet; i++) {
                ChiTietPhieuNhap ct = dsChiTiet[i];
                String tenSach = (ct.getSach() != null) ? ct.getSach().getTenSach() : "(Khong co ten)";
                System.out.printf("  %d. Ma CT: %-6s | Sach: %-22s | SL: %-3d | Don gia: %12s | Thanh tien: %14s%n",
                        (i + 1), ct.getMaCT(), tenSach, ct.getSoLuongNhap(),
                        Main.dinhDangTien(ct.getDonGiaNhap()),
                        Main.dinhDangTien(ct.tinhThanhTien()));
            }
        }
        System.out.println("Tong tien     : " + Main.dinhDangTien(tinhTongTien()));
        System.out.println("==========================================");
    }

    // Getter & Setter
    public String getMaPhieuNhap() { return maPhieuNhap; }
    public void setMaPhieuNhap(String maPhieuNhap) { this.maPhieuNhap = maPhieuNhap; }

    public LocalDate getNgayNhap() { return ngayNhap; }
    public void setNgayNhap(LocalDate ngayNhap) { this.ngayNhap = ngayNhap; }

    public NhaXuatBan getNhaXuatBan() { return nhaXuatBan; }
    public void setNhaXuatBan(NhaXuatBan nhaXuatBan) { this.nhaXuatBan = nhaXuatBan; }

    public ChiTietPhieuNhap[] getDsChiTiet() { return dsChiTiet; }
    public void setDsChiTiet(ChiTietPhieuNhap[] dsChiTiet) { this.dsChiTiet = dsChiTiet; }

    public int getSoLuongChiTiet() { return soLuongChiTiet; }
    public void setSoLuongChiTiet(int soLuongChiTiet) { this.soLuongChiTiet = soLuongChiTiet; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
}
