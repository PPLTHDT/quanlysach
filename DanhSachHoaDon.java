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

    public int getSoLuong() {
        return soLuong;
    }
}
