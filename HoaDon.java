import java.time.LocalDate;

public class HoaDon {
    public static final int MAX_CT = 50;

    private String maHD;
    private LocalDate ngayLap;
    private double tongTien;
    private double tienGiam;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private KhuyenMai khuyenMai;
    private ChiTietHoaDon[] dsChiTiet;
    private int soLuongChiTiet;
    private ThanhToan thanhToan;

    public HoaDon() {
        this.maHD = "";
        this.ngayLap = LocalDate.now();
        this.tongTien = 0;
        this.tienGiam = 0;
        this.khachHang = null;
        this.nhanVien = null;
        this.khuyenMai = null;
        this.dsChiTiet = new ChiTietHoaDon[MAX_CT];
        this.soLuongChiTiet = 0;
        this.thanhToan = null;
    }

    public HoaDon(String maHD, LocalDate ngayLap, KhachHang khachHang, NhanVien nhanVien) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.tongTien = 0;
        this.tienGiam = 0;
        this.khuyenMai = null;
        this.dsChiTiet = new ChiTietHoaDon[MAX_CT];
        this.soLuongChiTiet = 0;
        this.thanhToan = null;
    }

    // Them mot dong chi tiet. Tra ve false neu hoa don day hoac khong du ton kho
    public boolean themChiTiet(ChiTietHoaDon ct) {
        if (ct == null || ct.getSach() == null || soLuongChiTiet >= MAX_CT) {
            return false;
        }
        // Tinh tong so luong cua cung mot cuon sach da co trong hoa don
        int daCo = 0;
        for (int i = 0; i < soLuongChiTiet; i++) {
            if (dsChiTiet[i].getSach() == ct.getSach()) {
                daCo = daCo + dsChiTiet[i].getSoLuong();
            }
        }
        if (ct.getSoLuong() <= 0 || daCo + ct.getSoLuong() > ct.getSach().getSoLuongTon()) {
            return false;
        }
        dsChiTiet[soLuongChiTiet] = ct;
        soLuongChiTiet++;
        return true;
    }

    public double tinhTongTien() {
        double tong = 0;
        for (int i = 0; i < soLuongChiTiet; i++) {
            tong = tong + dsChiTiet[i].tinhThanhTien();
        }
        this.tongTien = tong;
        return tong;
    }

    // So tien khach phai tra sau khi giam
    public double tinhTienPhaiTra() {
        return tinhTongTien() - tienGiam;
    }

    public void apDungKhuyenMai(KhuyenMai km) {
        if (km != null && km.conHieuLuc()) {
            this.khuyenMai = km;
            this.tienGiam = km.tinhTienGiam(tinhTongTien());
        } else {
            this.khuyenMai = null;
            this.tienGiam = 0;
            System.out.println("Khuyen mai khong hop le hoac da het han!");
        }
    }

    // Thanh toan hoa don. Thanh cong thi tru kho va cong diem tich luy
    public boolean thucHienThanhToan(double tienKhachDua) {
        double canTra = tinhTienPhaiTra();
        if (soLuongChiTiet == 0) {
            return false;
        }
        // Neu chua chon phuong thuc hoac dang la tien mat thi tao lai voi so tien khach dua
        if (thanhToan == null || thanhToan instanceof ThanhToanTienMat) {
            thanhToan = new ThanhToanTienMat(tienKhachDua);
        }
        boolean thanhCong = thanhToan.thanhToan(canTra);
        if (thanhCong) {
            for (int i = 0; i < soLuongChiTiet; i++) {
                dsChiTiet[i].getSach().giamSoLuong(dsChiTiet[i].getSoLuong());
            }
            if (khachHang != null) {
                khachHang.tangDiemTichLuy((int) (canTra / 10000));
            }
        }
        return thanhCong;
    }

    public void inHoaDon() {
        double canTra = tinhTienPhaiTra();
        System.out.println("============ HOA DON ============");
        System.out.println("Ma HD: " + maHD + " | Ngay lap: " + Main.dinhDangNgay(ngayLap));
        if (nhanVien != null) {
            System.out.println("Nhan vien: " + nhanVien.getHoTen());
        }
        if (khachHang != null) {
            System.out.println("Khach hang: " + khachHang.getHoTen());
        } else {
            System.out.println("Khach hang: Khach le");
        }
        System.out.println("---------------------------------");
        for (int i = 0; i < soLuongChiTiet; i++) {
            ChiTietHoaDon ct = dsChiTiet[i];
            System.out.println((i + 1) + ". " + ct.getSach().getTenSach() + " | SL: " + ct.getSoLuong()
                    + " | Don gia: " + Main.dinhDangTien(ct.getDonGia())
                    + " | Thanh tien: " + Main.dinhDangTien(ct.tinhThanhTien()));
        }
        System.out.println("---------------------------------");
        System.out.println("Tong tien: " + Main.dinhDangTien(tongTien));
        if (khuyenMai != null) {
            System.out.println("Khuyen mai (" + khuyenMai.getTenKM() + "): -" + Main.dinhDangTien(tienGiam));
        }
        System.out.println("PHAI TRA: " + Main.dinhDangTien(canTra));
        if (thanhToan != null) {
            System.out.println("Phuong thuc: " + thanhToan.getPhuongThuc());
            if (thanhToan instanceof ThanhToanTienMat) {
                ThanhToanTienMat tm = (ThanhToanTienMat) thanhToan;
                System.out.println("Tien khach dua: " + Main.dinhDangTien(tm.getTienNhan())
                        + " | Tien thua: " + Main.dinhDangTien(tm.tinhTienThua(canTra)));
            }
        }
        System.out.println("=================================");
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getTienGiam() {
        return tienGiam;
    }

    public void setTienGiam(double tienGiam) {
        this.tienGiam = tienGiam;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public ChiTietHoaDon[] getDsChiTiet() {
        return dsChiTiet;
    }

    public void setDsChiTiet(ChiTietHoaDon[] dsChiTiet) {
        this.dsChiTiet = dsChiTiet;
    }

    public int getSoLuongChiTiet() {
        return soLuongChiTiet;
    }

    public void setSoLuongChiTiet(int soLuongChiTiet) {
        this.soLuongChiTiet = soLuongChiTiet;
    }

    public ThanhToan getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(ThanhToan thanhToan) {
        this.thanhToan = thanhToan;
    }
}
