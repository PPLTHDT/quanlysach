package cuahangsach;
import java.time.LocalDate;

public class KhuyenMai {
    private String maKM;
    private String tenKM;
    private double phanTramGiam;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;

    public KhuyenMai() {
        this.maKM = "";
        this.tenKM = "";
        this.phanTramGiam = 0;
        this.ngayBatDau = LocalDate.now();
        this.ngayKetThuc = LocalDate.now();
    }

    public KhuyenMai(String maKM, String tenKM, double phanTramGiam,
                     LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.phanTramGiam = phanTramGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    // Ngay hom nay nam trong khoang [ngayBatDau, ngayKetThuc] thi con hieu luc
    public boolean conHieuLuc() {
        LocalDate homNay = LocalDate.now();
        if (ngayBatDau == null || ngayKetThuc == null) {
            return false;
        }
        return !homNay.isBefore(ngayBatDau) && !homNay.isAfter(ngayKetThuc);
    }

    public double tinhTienGiam(double tongTien) {
        return tongTien * phanTramGiam / 100;
    }

    public void hienThi() {
        String trangThai = "Het han";
        if (conHieuLuc()) {
            trangThai = "Con hieu luc";
        }
        GiaoDienConsole.inTieuDe("THONG TIN KHUYEN MAI");
        GiaoDienConsole.inMuc("Ma khuyen mai", maKM);
        GiaoDienConsole.inMuc("Ten khuyen mai", tenKM);
        GiaoDienConsole.inMuc("Muc giam", phanTramGiam + "%");
        GiaoDienConsole.inMuc("Ngay bat dau", Main.dinhDangNgay(ngayBatDau));
        GiaoDienConsole.inMuc("Ngay ket thuc", Main.dinhDangNgay(ngayKetThuc));
        GiaoDienConsole.inMuc("Trang thai", trangThai);
        GiaoDienConsole.inPhanCach();
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public double getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(double phanTramGiam) {
        if (phanTramGiam < 0) {
            phanTramGiam = 0;
        }
        if (phanTramGiam > 100) {
            phanTramGiam = 100;
        }
        this.phanTramGiam = phanTramGiam;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
