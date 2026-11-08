import java.time.LocalDate;

public class PhieuNhap {
    private String maPhieuNhap;
    private LocalDate ngayNhap;
    private String nhaCungCap;
    private ChiTietPhieuNhap[] dsChiTiet;
    private int soLuongChiTiet;
    private double tongTien;
    private static final int MAX_CHI_TIET = 20;

    public PhieuNhap() {
        this.maPhieuNhap = "";
        this.ngayNhap = LocalDate.now();
        this.nhaCungCap = "";
        this.dsChiTiet = new ChiTietPhieuNhap[MAX_CHI_TIET];
        this.soLuongChiTiet = 0;
        this.tongTien = 0;
    }

    public PhieuNhap(String maPhieuNhap, LocalDate ngayNhap, String nhaCungCap) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.nhaCungCap = nhaCungCap;
        this.dsChiTiet = new ChiTietPhieuNhap[MAX_CHI_TIET];
        this.soLuongChiTiet = 0;
        this.tongTien = 0;
    }

    public boolean themChiTiet(ChiTietPhieuNhap ct) {
        if (ct == null) return false;
        if (soLuongChiTiet < MAX_CHI_TIET) {
            dsChiTiet[soLuongChiTiet] = ct;
            soLuongChiTiet++;
            tinhTongTien();
            return true;
        }
        System.out.println("Da dat toi da chi tiet phieu nhap!");
        return false;
    }

    public double tinhTongTien() {
        tongTien = 0;
        for (int i = 0; i < soLuongChiTiet; i++) {
            tongTien += dsChiTiet[i].tinhThanhTien();
        }
        return tongTien;
    }

    public void inPhieuNhap() {
        System.out.println("========== PHIEU NHAP ==========");
        System.out.println("Ma phieu: " + maPhieuNhap);
        System.out.println("Ngay nhap: " + ngayNhap);
        System.out.println("Nha cung cap: " + nhaCungCap);
        System.out.println("-----------------------------");
        for (int i = 0; i < soLuongChiTiet; i++) {
            ChiTietPhieuNhap ct = dsChiTiet[i];
            System.out.println((i + 1) + ". " + ct.getSach().getTenSach()
                    + " x" + ct.getSoLuongNhap() + " @ " + ct.getDonGiaNhap() + " = " + ct.tinhThanhTien());
        }
        System.out.println("-----------------------------");
        System.out.println("Tong tien: " + tongTien);
        System.out.println("================================");
    }

    public void xacNhanNhapKho() {
        for (int i = 0; i < soLuongChiTiet; i++) {
            dsChiTiet[i].capNhatSoLuong();
        }
    }

    public String getMaPhieuNhap() { return maPhieuNhap; }
    public LocalDate getNgayNhap() { return ngayNhap; }
    public String getNhaCungCap() { return nhaCungCap; }
    public ChiTietPhieuNhap[] getDsChiTiet() {
        ChiTietPhieuNhap[] result = new ChiTietPhieuNhap[soLuongChiTiet];
        for (int i = 0; i < soLuongChiTiet; i++) {
            result[i] = dsChiTiet[i];
        }
        return result;
    }
    public int getSoLuongChiTiet() { return soLuongChiTiet; }
    public double getTongTien() { return tongTien; }

    public void setMaPhieuNhap(String maPhieuNhap) { this.maPhieuNhap = maPhieuNhap; }
    public void setNgayNhap(LocalDate ngayNhap) { this.ngayNhap = ngayNhap; }
    public void setNhaCungCap(String nhaCungCap) { this.nhaCungCap = nhaCungCap; }
}