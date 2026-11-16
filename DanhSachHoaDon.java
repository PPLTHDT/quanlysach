package cuahangsach;
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
            if(dsHoaDon[i] != null && dsHoaDon[i].getMaHD() != null
                    && dsHoaDon[i].getMaHD().equalsIgnoreCase(ma)) {
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

    // Them toi da n hoa don tu mang ds, tra ve so hoa don them thanh cong
    public int themN(HoaDon[] ds, int n) {
        if (ds == null || n <= 0) {
            return 0;
        }
        int dem = 0;
        for (int i = 0; i < ds.length && i < n; i++) {
            if (them(ds[i])) { // them() da kiem tra null, day mang, trung ma
                dem++;
            }
        }
        return dem;
    }

    // Them tat ca hoa don trong mang ds
    public int themN(HoaDon[] ds) {
        return ds == null ? 0 : themN(ds, ds.length);
    }

    // Nhap N hoa don tu ban phim
    public void nhapN() {
        System.out.println("Hay dung chuc nang Lap hoa don de tao hoa don moi!");
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
        HoaDon hd = dsHoaDon[vt];
        for (ChiTietHoaDon ct : hd.getDsChiTiet()) {
            if (ct != null && ct.getSach() != null) {
                ct.getSach().tangSoLuong(ct.getSoLuong());
            }
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
        GiaoDienConsole.inTieuDe("THONG KE HOA DON");
        double doanhThu = 0;
        HoaDon lonNhat = null;
        for (int i = 0; i < soLuong; i++) {
            doanhThu = doanhThu + dsHoaDon[i].tinhTienPhaiTra();
            if (lonNhat == null || dsHoaDon[i].tinhTienPhaiTra() > lonNhat.tinhTienPhaiTra()) {
                lonNhat = dsHoaDon[i];
            }
        }
        GiaoDienConsole.inMuc("Tong so hoa don", String.valueOf(soLuong));
        GiaoDienConsole.inMuc("Tong doanh thu", Main.dinhDangTien(doanhThu));
        if (lonNhat != null) {
            GiaoDienConsole.inMuc("Hoa don gia tri cao", lonNhat.getMaHD()
                    + " (" + Main.dinhDangTien(lonNhat.tinhTienPhaiTra()) + ")");
        }
    }

    public void napDuLieu(HoaDon[] ds) {
        soLuong = 0;
        if (ds == null) {
            return;
        }
        for (int i = 0; i < ds.length && soLuong < MAX; i++) {
            if (ds[i] != null) {
                dsHoaDon[soLuong] = ds[i];
                soLuong++;
            }
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
        GiaoDienConsole.inTieuDe("TIM KIEM HOA DON");
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
                    if (dsHoaDon[i].getNgayLap() != null && dsHoaDon[i].getNgayLap().equals(ngay)) {
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
            GiaoDienConsole.inTieuDe("QUAN LY HOA DON");
            System.out.println("  1. Xem danh sach");
            System.out.println("  2. Sua ngay lap hoa don");
            System.out.println("  3. Xoa");
            System.out.println("  4. Tim kiem");
            System.out.println("  5. Thong ke");
            System.out.println("  6. Them nhieu hoa don");
            System.out.println("  0. Quay lai");
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
                case 6:
                    nhapN();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    public void menuXoa() {
        GiaoDienConsole.inTieuDe("XOA HOA DON");
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
                    if (dsHoaDon[i].getNgayLap() != null && dsHoaDon[i].getNgayLap().equals(ngay)) {
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
                break;
            }
            case 5: {
                System.out.println("1. Tien mat");
                System.out.println("2. Chuyen khoan");
                int loaiTT = Main.docSoNguyen("Chon phuong thuc: ");
                int dem = 0;
                for (int i = 0; i < soLuong; i++) {
                    ThanhToan tt = dsHoaDon[i].getThanhToan();
                    boolean khop = (loaiTT == 1 && tt instanceof ThanhToanTienMat)
                            || (loaiTT == 2 && tt instanceof ThanhToanChuyenKhoan);
                    if (khop) {
                        xoaTheoMa(dsHoaDon[i].getMaHD());
                        i--;
                        dem++;
                    }
                }
                System.out.println(dem > 0 ? "Da xoa " + dem + " hoa don!" : "Khong tim thay hoa don nao!");
                break;
            }
            case 0:
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }
}
