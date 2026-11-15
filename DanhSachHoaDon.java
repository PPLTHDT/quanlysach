public class DanhSachHoaDon {
    public static final int MAX = 100;

    private HoaDon[] dsHoaDon;
    private int soLuong;

    public DanhSachHoaDon() {
        this.dsHoaDon = new HoaDon[MAX];
        this.soLuong = 0;
    }

    // Tim vi tri cua phan tu theo ma, khong thay tra ve -1
    private int timViTri(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (dsHoaDon[i].getMaHD().equals(ma)) {
                return i;
            }
        }
        return -1;
    }

    public boolean them(HoaDon x) {
        if (x == null || soLuong >= MAX) {
            return false;
        }
        if (timViTri(x.getMaHD()) != -1) {
            return false; // trung ma
        }
        dsHoaDon[soLuong] = x;
        soLuong++;
        return true;
    }

    public HoaDon timKiem(String ma) {
        int vt = timViTri(ma);
        if (vt == -1) {
            return null;
        }
        return dsHoaDon[vt];
    }

    public boolean xoaTheoMa(String ma) {
        int vt = timViTri(ma);
        if (vt == -1) {
            return false;
        }
        for (int i = vt; i < soLuong - 1; i++) {
            dsHoaDon[i] = dsHoaDon[i + 1];
        }
        dsHoaDon[soLuong - 1] = null;
        soLuong--;
        return true;
    }

    public boolean suaTheoMa(String ma) {
        HoaDon x = timKiem(ma);
        if (x == null) {
            return false;
        }
        x.setNgayLap(Main.docNgay("Ngay lap moi"));
        return true;
    }

    public void nhap() {
        System.out.println("Hay dung chuc nang Lap hoa don de tao hoa don moi!");
    }

    public void xuat() {
        if (soLuong == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        for (int i = 0; i < soLuong; i++) {
            dsHoaDon[i].inHoaDon();
        }
    }

    public void thongKe() {
        double doanhThu = 0;
        HoaDon lonNhat = null;
        for (int i = 0; i < soLuong; i++) {
            doanhThu = doanhThu + dsHoaDon[i].tinhTienPhaiTra();
            if (lonNhat == null || dsHoaDon[i].tinhTienPhaiTra() > lonNhat.tinhTienPhaiTra()) {
                lonNhat = dsHoaDon[i];
            }
        }
        System.out.println("Tong so hoa don: " + soLuong);
        System.out.println("Tong doanh thu: " + Main.dinhDangTien(doanhThu));
        if (lonNhat != null) {
            System.out.println("Hoa don lon nhat: " + lonNhat.getMaHD()
                    + " (" + Main.dinhDangTien(lonNhat.tinhTienPhaiTra()) + ")");
        }
    }

    public void napDuLieu(HoaDon[] ds) {
        soLuong = 0;
        for (int i = 0; i < ds.length && i < MAX; i++) {
            dsHoaDon[i] = ds[i];
            soLuong++;
        }
    }

    // Tra ve mang co do dai dung bang so phan tu hien co
    public HoaDon[] getDs() {
        HoaDon[] ketQua = new HoaDon[soLuong];
        for (int i = 0; i < soLuong; i++) {
            ketQua[i] = dsHoaDon[i];
        }
        return ketQua;
    }

    public void menu() {
        int chon;
        do {
            System.out.println();
            System.out.println("--- DanhSachHoaDon: QUAN LY HOA DON ---");
            System.out.println("1. Xem danh sach");
            System.out.println("2. Sua ngay");
            System.out.println("3. Xoa theo ma");
            System.out.println("4. Tim kiem theo ma");
            System.out.println("5. Thong ke");
            System.out.println("0. Quay lai");
            chon = Main.docSoNguyen("Chon: ");
            switch (chon) {
                case 1:
                    xuat();
                    break;
                case 2: {
                    String ma = Main.docChuoi("Nhap ma can sua: ");
                    if (suaTheoMa(ma)) {
                        System.out.println("Sua thanh cong!");
                    } else {
                        System.out.println("Khong tim thay ma " + ma);
                    }
                    break;
                }
                case 3: {
                    String ma = Main.docChuoi("Nhap ma can xoa: ");
                    if (xoaTheoMa(ma)) {
                        System.out.println("Xoa thanh cong! (Ton kho va diem tich luy khong duoc hoan lai)");
                    } else {
                        System.out.println("Khong tim thay ma " + ma);
                    }
                    break;
                }
                case 4: {
                    String ma = Main.docChuoi("Nhap ma can tim: ");
                    HoaDon kq = timKiem(ma);
                    if (kq == null) {
                        System.out.println("Khong tim thay ma " + ma);
                    } else {
                        kq.inHoaDon();
                    }
                    break;
                }
                case 5:
                    thongKe();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    public int getSoLuong() {
        return soLuong;
    }
}
