import java.time.LocalDate;

public class CuaHangSach {
    private DanhSachSach danhSachSach;
    private DanhSachHoaDon danhSachHoaDon;
    private KhachHang[] khachHang;
    private DanhSachNhanVien danhSachNhanVien;
    private int soLuongKhachHang;
    private ThongKe thongKe;
    private DocGhiFile docGhiFile;
    private static final int MAX_KHACH_HANG = 100;

    public CuaHangSach() {
        this.danhSachSach = new DanhSachSach();
        this.danhSachHoaDon = new DanhSachHoaDon();
        this.khachHang = new KhachHang[MAX_KHACH_HANG];
        this.danhSachNhanVien = new DanhSachNhanVien();
        this.soLuongKhachHang = 0;
        this.thongKe = new ThongKe();
        this.docGhiFile = new DocGhiFile();
    }

    public void khoiTao() {
        docDuLieuTuFile();
    }

    public void docDuLieuTuFile() {
        if (docGhiFile != null) {
            docGhiFile.docFileSach();
            docGhiFile.docFileHoaDon();
        }
    }

    public void ghiDuLieuRaFile() {
        if (docGhiFile != null) {
            if (danhSachSach != null) {
                docGhiFile.ghiFileSach(danhSachSach.getDsSach());
            }
            if (danhSachHoaDon != null) {
                docGhiFile.ghiFileHoaDon(danhSachHoaDon.getDsHoaDon());
            }
        }
    }

    public void hienThiMenu() {
        System.out.println("\n=================================================");
        System.out.println("       HE THONG QUAN LY CUA HANG SACH            ");
        System.out.println("=================================================");
        System.out.println("1. Xem danh sach sach trong kho");
        System.out.println("2. Xem danh sach hoa don");
        System.out.println("3. Xem danh sach khach hang");
        System.out.println("4. Xem danh sach nhan vien");
        System.out.println("5. Lap hoa don ban sach truc tiep tai quay");
        System.out.println("6. Them khach hang");
        System.out.println("7. Them nhan vien");
        System.out.println("8. Thong ke");
        System.out.println("9. Ghi du lieu ra file");
        System.out.println("0. Thoat chuong trinh");
        System.out.println("=================================================");
        System.out.print("Moi ban chon chuc nang (0 - 9): ");
    }

    public void xuLy() {
        int chon = -1;
        do {
            hienThiMenu();
            try {
                chon = Integer.parseInt(Main.sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Vui long nhap so hop le!");
                continue;
            }

            switch (chon) {
                case 1:
                    if (danhSachSach != null) danhSachSach.xuat();
                    break;
                case 2:
                    if (danhSachHoaDon != null) danhSachHoaDon.xuat();
                    break;
                case 3:
                    System.out.println("\n--- DANH SACH KHACH HANG ---");
                    for (int i = 0; i < soLuongKhachHang; i++) {
                        if (khachHang[i] != null) khachHang[i].hienThiThongTin();
                    }
                    break;
                case 4:
                    if (danhSachNhanVien != null) danhSachNhanVien.xuat();
                    break;
                case 5:
                    lapHoaDon();
                    break;
                case 6:
                    System.out.print("Nhap ma KH: ");
                    String makh = Main.sc.nextLine().trim();
                    System.out.print("Nhap ho ten: ");
                    String ten = Main.sc.nextLine().trim();
                    System.out.print("Nhap SDT: ");
                    String sdt = Main.sc.nextLine().trim();
                    themKhachHang(new KhachHang(makh, ten, sdt, "", "", makh, 0));
                    System.out.println("Them khach hang thanh cong!");
                    break;
                case 7:
                    System.out.print("Nhap ma NV: ");
                    String manv = Main.sc.nextLine().trim();
                    System.out.print("Nhap ho ten: ");
                    String tennv = Main.sc.nextLine().trim();
                    System.out.print("Nhap chuc vu: ");
                    String cv = Main.sc.nextLine().trim();
                    themNhanVien(new NhanVien(manv, tennv, "", "", "", manv, cv, 5000000));
                    System.out.println("Them nhan vien thanh cong!");
                    break;
                case 8:
                    if (thongKe != null) {
                        thongKe.thongKeSach(danhSachSach);
                        thongKe.thongKeDoanhThu(danhSachHoaDon);
                        thongKe.thongKeNhanSu(danhSachNhanVien);
                    }
                    break;
                case 9:
                    ghiDuLieuRaFile();
                    break;
                case 0:
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Chuc nang khong hop le!");
                    break;
            }
        } while (chon != 0);
    }

    private void lapHoaDon() {
        System.out.println("\n--- LAP HOA DON BAN SACH TAI QUAY ---");
        if (danhSachSach == null || danhSachHoaDon == null) return;
        danhSachSach.xuat();
        System.out.print("Nhap ma sach: ");
        String ma = Main.sc.nextLine().trim();
        Sach s = danhSachSach.timKiem(ma);
        if (s == null) {
            System.out.println("Khong tim thay sach!");
            return;
        }
        System.out.print("Nhap so luong mua: ");
        int sl = 1;
        try { sl = Integer.parseInt(Main.sc.nextLine().trim()); } catch (Exception e) {}
        if (s.giamSoLuong(sl)) {
            double tong = sl * s.getGiaBan();
            String maHD = "HD" + (danhSachHoaDon.getSoLuong() + 1);
            HoaDon hd = new HoaDon(maHD, LocalDate.now(), null, null, tong);
            danhSachHoaDon.them(hd);
            System.out.println("Lap hoa don thanh cong! Da tru so luong ton kho.");
            hd.inHoaDon();
        } else {
            System.out.println("Khong du so luong trong kho!");
        }
    }

    public boolean themKhachHang(KhachHang kh) {
        if (kh == null || soLuongKhachHang >= MAX_KHACH_HANG) return false;
        khachHang[soLuongKhachHang++] = kh;
        return true;
    }

    public boolean themNhanVien(NhanVien nv) {
        if (danhSachNhanVien == null || nv == null) return false;
        return danhSachNhanVien.themNhanVien(nv);
    }

    public DanhSachSach getDanhSachSach() { return danhSachSach; }
    public DanhSachHoaDon getDanhSachHoaDon() { return danhSachHoaDon; }
    public KhachHang[] getKhachHang() { return khachHang; }
    public DanhSachNhanVien getDanhSachNhanVien() { return danhSachNhanVien; }
    public int getSoLuongKhachHang() { return soLuongKhachHang; }
    public ThongKe getThongKe() { return thongKe; }
    public DocGhiFile getDocGhiFile() { return docGhiFile; }
}
