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
        for(int i = 0; i < soLuong; i++) {
            if(dsHoaDon[i].getMaHD().equalsIgnoreCase(ma)) {
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

    public void menuTimKiem() {
        System.out.println("\n--- TIM KIEM HOA DON ---");
        System.out.println("1. Theo ma hoa don");
        System.out.println("2. Theo ngay lap (dd/MM/yyyy)");
        System.out.println("3. Theo ma hoac ten khach hang");
        System.out.println("4. Theo ma hoac ten nhan vien lap");
        System.out.println("5. Theo khoang tien phai tra");
        System.out.println("6. Loc hoa don co ap dung ma khuyen mai");
        System.out.println("0. Quay lai");
        int chon = Main.docSoNguyen("Chon cach tim: ");
        boolean thay = false;
        switch (chon) {
            case 1: {
                String ma = Main.docChuoi("Nhap ma HD: ");
                HoaDon hd = timKiem(ma);
                if (hd != null) {
                    hd.inHoaDon();
                } else {
                    System.out.println("Khong tim thay ma " + ma);
                }
                break;
            }
            case 2: {
                java.time.LocalDate ngay = Main.docNgay("Nhap ngay lap");
                for (int i = 0; i < soLuong; i++) {
                    if (dsHoaDon[i].getNgayLap().equals(ngay)) {
                        dsHoaDon[i].inHoaDon();
                        thay = true;
                    }
                }
                if (!thay) {
                    System.out.println("Khong tim thay hoa don nao trong ngay nay!");
                }
                break;
            }
            case 3: {
                String tuKhoa = Main.docChuoi("Nhap ma hoac ten khach hang: ").toLowerCase();
                for (int i = 0; i < soLuong; i++) {
                    KhachHang kh = dsHoaDon[i].getKhachHang();
                    if (kh != null && (kh.getMaKH().toLowerCase().contains(tuKhoa)
                            || kh.getHoTen().toLowerCase().contains(tuKhoa))) {
                        dsHoaDon[i].inHoaDon();
                        thay = true;
                    }
                }
                if (!thay) {
                    System.out.println("Khong tim thay hoa don cua khach hang nay!");
                }
                break;
            }
            case 4: {
                String tuKhoa = Main.docChuoi("Nhap ma hoac ten nhan vien: ").toLowerCase();
                for (int i = 0; i < soLuong; i++) {
                    NhanVien nv = dsHoaDon[i].getNhanVien();
                    if (nv != null && (nv.getMaNV().toLowerCase().contains(tuKhoa)
                            || nv.getHoTen().toLowerCase().contains(tuKhoa))) {
                        dsHoaDon[i].inHoaDon();
                        thay = true;
                    }
                }
                if (!thay) {
                    System.out.println("Khong tim thay hoa don do nhan vien nay lap!");
                }
                break;
            }
            case 5: {
                double min = Main.docSoThuc("Tong tien toi thieu: ");
                double max = Main.docSoThuc("Tong tien toi da: ");
                for (int i = 0; i < soLuong; i++) {
                    double tien = dsHoaDon[i].tinhTienPhaiTra();
                    if (tien >= min && tien <= max) {
                        dsHoaDon[i].inHoaDon();
                        thay = true;
                    }
                }
                if (!thay) {
                    System.out.println("Khong tim thay hoa don nao trong khoang tien nay!");
                }
                break;
            }
            case 6: {
                String maKM = Main.docChuoi("Nhap ma KM (Enter de xem tat ca HD co khuyen mai): ").toLowerCase();
                for (int i = 0; i < soLuong; i++) {
                    KhuyenMai km = dsHoaDon[i].getKhuyenMai();
                    if (km != null && (maKM.equals("") || km.getMaKM().toLowerCase().contains(maKM))) {
                        dsHoaDon[i].inHoaDon();
                        thay = true;
                    }
                }
                if (!thay) {
                    System.out.println("Khong tim thay hoa don nao phu hop!");
                }
                break;
            }
            case 0:
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }

    public void menu() {
        int chon;
        do {
            System.out.println();
            System.out.println("--- DanhSachHoaDon: QUAN LY HOA DON ---");
            System.out.println("1. Xem danh sach");
            System.out.println("2. Sua ngay lap hoa don");
            System.out.println("3. Xoa (da tieu chi / da thuoc tinh)");
            System.out.println("4. Tim kiem (da tieu chi / da thuoc tinh)");
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
                case 3:
                    menuXoa(); // Goi menu xoa da thuoc tinh
                    break;
                case 4:
                    menuTimKiem(); // Goi menu tim kiem da thuoc tinh
                    break;
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

    public void menuXoa() {
        System.out.println("\n--- XOA HOA DON ---");
        System.out.println("1. Xoa theo ma hoa don");
        System.out.println("2. Xoa theo ngay lap (dd/MM/yyyy)");
        System.out.println("3. Xoa theo ma khach hang");
        System.out.println("4. Xoa theo ma nhan vien lap");
        System.out.println("5. Xoa theo phuong thuc thanh toan (Tien mat / Chuyen khoan)");
        System.out.println("0. Quay lai");
        int chon = Main.docSoNguyen("Chon cach xoa: ");
        switch (chon) {
            case 1: {
                String ma = Main.docChuoi("Nhap ma HD can xoa: ");
                if (xoaTheoMa(ma)) {
                    System.out.println("Xoa thanh cong!");
                } else {
                    System.out.println("Khong tim thay ma " + ma);
                }
                break;
            }
            case 2: {
                java.time.LocalDate ngay = Main.docNgay("Nhap ngay lap can xoa");
                int dem = 0;
                for (int i = 0; i < soLuong; i++) {
                    if (dsHoaDon[i].getNgayLap().equals(ngay)) {
                        xoaTheoMa(dsHoaDon[i].getMaHD());
                        i--;
                        dem++;
                    }
                }
                System.out.println(dem > 0 ? "Da xoa " + dem + " hoa don!" : "Khong tim thay hoa don trong ngay nay!");
                break;
            }
            case 3: {
                String maKH = Main.docChuoi("Nhap ma KH can xoa hoa don: ");
                int dem = 0;
                for (int i = 0; i < soLuong; i++) {
                    if (dsHoaDon[i].getKhachHang() != null
                            && dsHoaDon[i].getKhachHang().getMaKH().equalsIgnoreCase(maKH)) {
                        xoaTheoMa(dsHoaDon[i].getMaHD());
                        i--;
                        dem++;
                    }
                }
                System.out.println(dem > 0 ? "Da xoa " + dem + " hoa don cua KH " + maKH : "Khong tim thay hoa don nao!");
                break;
            }
            case 4: {
                String maNV = Main.docChuoi("Nhap ma NV can xoa hoa don: ");
                int dem = 0;
                for (int i = 0; i < soLuong; i++) {
                    if (dsHoaDon[i].getNhanVien() != null
                            && dsHoaDon[i].getNhanVien().getMaNV().equalsIgnoreCase(maNV)) {
                        xoaTheoMa(dsHoaDon[i].getMaHD());
                        i--;
                        dem++;
                    }
                }
                System.out.println(dem > 0 ? "Da xoa " + dem + " hoa don cua NV " + maNV : "Khong tim thay hoa don nao!");
            }
        }
    }
}


