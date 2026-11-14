public class CuaHangSach {
    private DanhSachSach danhSachSach;
    private DanhSachTheLoai danhSachTheLoai;
    private DanhSachTacGia danhSachTacGia;
    private DanhSachNhaXuatBan danhSachNhaXuatBan;
    private DanhSachKhachHang danhSachKhachHang;
    private DanhSachNhanVien danhSachNhanVien;
    private DanhSachHoaDon danhSachHoaDon;
    private DanhSachPhieuNhap danhSachPhieuNhap;
    private DanhSachKhuyenMai danhSachKhuyenMai;
    private ThongKe thongKe;
    private DocGhiFile docGhiFile;

    public CuaHangSach() {
        this.danhSachSach = new DanhSachSach();
        this.danhSachTheLoai = new DanhSachTheLoai();
        this.danhSachTacGia = new DanhSachTacGia();
        this.danhSachNhaXuatBan = new DanhSachNhaXuatBan();
        this.danhSachKhachHang = new DanhSachKhachHang();
        this.danhSachNhanVien = new DanhSachNhanVien();
        this.danhSachHoaDon = new DanhSachHoaDon();
        this.danhSachPhieuNhap = new DanhSachPhieuNhap();
        this.danhSachKhuyenMai = new DanhSachKhuyenMai();
        this.thongKe = new ThongKe();
        this.docGhiFile = new DocGhiFile();
    }

    public void khoiTao() {
        docDuLieuTuFile();
    }

    public void docDuLieuTuFile() {
        if (docGhiFile != null) {
            // Goi doc du lieu tu file khi ghep code voi lop DocGhiFile
        }
    }

    public void ghiDuLieuRaFile() {
        if (docGhiFile != null) {
            // Goi ghi du lieu ra file khi ghep code voi lop DocGhiFile
        }
    }

    public void hienThiMenu() {
        System.out.println("\n=================================================");
        System.out.println("       HE THONG QUAN LY CUA HANG SACH            ");
        System.out.println("=================================================");
        System.out.println(" 1. Quan ly Kho Sach");
        System.out.println(" 2. Quan ly The Loai");
        System.out.println(" 3. Quan ly Tac Gia");
        System.out.println(" 4. Quan ly Nha Xuat Ban");
        System.out.println(" 5. Quan ly Khach Hang");
        System.out.println(" 6. Quan ly Nhan Vien");
        System.out.println(" 7. Quan ly Hoa Don Ban Hang (Lap hoa don)");
        System.out.println(" 8. Quan ly Phieu Nhap Hang (Lap phieu nhap)");
        System.out.println(" 9. Quan ly Khuyen Mai");
        System.out.println(" 10. Thong ke bao cao");
        System.out.println(" 11. Doc / Ghi du lieu file");
        System.out.println(" 0. Thoat chuong trinh");
        System.out.println("=================================================");
        System.out.print("Moi ban chon chuc nang (0 - 11): ");
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
                    quanLyTheLoai();
                    break;
                case 3:
                    if (danhSachTacGia != null) danhSachTacGia.xuat();
                    break;
                case 4:
                    if (danhSachNhaXuatBan != null) danhSachNhaXuatBan.xuat();
                    break;
                case 5:
                    if (danhSachKhachHang != null) danhSachKhachHang.xuat();
                    break;
                case 6:
                    if (danhSachNhanVien != null) danhSachNhanVien.xuat();
                    break;
                case 7:
                    lapHoaDon();
                    break;
                case 8:
                    lapPhieuNhap();
                    break;
                case 9:
                    if (danhSachKhuyenMai != null) danhSachKhuyenMai.xuat();
                    break;
                case 10:
                    if (thongKe != null) {
                        thongKe.thongKeTonKho(danhSachSach);
                        thongKe.thongKeDoanhThu(danhSachHoaDon);
                        thongKe.thongKeKhachHang(danhSachKhachHang);
                    }
                    break;
                case 11:
                    ghiDuLieuRaFile();
                    break;
                case 0:
                    System.out.println("Tam biet quy khach!");
                    break;
                default:
                    System.out.println("Chuc nang khong hop le, vui long chon lai!");
                    break;
            }
        } while (chon != 0);
    }

    private void lapHoaDon() {
        if (danhSachHoaDon != null) {
            danhSachHoaDon.nhap();
        }
    }

    private void lapPhieuNhap() {
        if (danhSachPhieuNhap != null) {
            danhSachPhieuNhap.nhap();
        }
    }

    private void quanLyTheLoai() {
        if (danhSachTheLoai != null) {
            danhSachTheLoai.xuat();
        }
    }

    private boolean xoaTheLoai(String ma) {
        if (danhSachTheLoai != null) {
            return danhSachTheLoai.xoaTheoMa(ma);
        }
        return false;
    }

    // Getter cac danh sach theo so do lop
    public DanhSachSach getDanhSachSach() { return danhSachSach; }
    public DanhSachTheLoai getDanhSachTheLoai() { return danhSachTheLoai; }
    public DanhSachTacGia getDanhSachTacGia() { return danhSachTacGia; }
    public DanhSachNhaXuatBan getDanhSachNhaXuatBan() { return danhSachNhaXuatBan; }
    public DanhSachKhachHang getDanhSachKhachHang() { return danhSachKhachHang; }
    public DanhSachNhanVien getDanhSachNhanVien() { return danhSachNhanVien; }
    public DanhSachHoaDon getDanhSachHoaDon() { return danhSachHoaDon; }
    public DanhSachPhieuNhap getDanhSachPhieuNhap() { return danhSachPhieuNhap; }
    public DanhSachKhuyenMai getDanhSachKhuyenMai() { return danhSachKhuyenMai; }
    public ThongKe getThongKe() { return thongKe; }
    public DocGhiFile getDocGhiFile() { return docGhiFile; }
}
