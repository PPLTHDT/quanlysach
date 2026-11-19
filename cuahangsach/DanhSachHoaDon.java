package cuahangsach;

import java.time.LocalDate;
import java.util.Locale;

public class DanhSachHoaDon {
    public static final int MAX = 100;

    private final HoaDon[] dsHoaDon;
    private int soLuong;

    public DanhSachHoaDon() {
        this.dsHoaDon = new HoaDon[MAX];
        this.soLuong = 0;
    }

    private int timViTriTheoMa(String ma) {
        if (ma == null || ma.trim().isEmpty()) {
            return -1;
        }
        String maCanTim = ma.trim();
        for (int i = 0; i < soLuong; i++) {
            if (dsHoaDon[i] != null && dsHoaDon[i].getMaHD() != null
                    && dsHoaDon[i].getMaHD().trim().equalsIgnoreCase(maCanTim)) {
                return i;
            }
        }
        return -1;
    }

    public boolean them(HoaDon x) {
        if (x == null || x.getMaHD() == null || soLuong >= MAX) {
            return false;
        }
        String ma = x.getMaHD().trim();
        if (ma.isEmpty() || x.getSoLuongChiTiet() <= 0 || x.getThanhToan() == null
                || timViTriTheoMa(ma) != -1) {
            return false;
        }
        x.setMaHD(ma);
        dsHoaDon[soLuong++] = x;
        return true;
    }

    // Them cac hoa don da duoc khoi tao; hoa don khong hop le hoac trung ma se bi bo qua.
    public int themN(HoaDon[] ds, int n) {
        if (ds == null || n <= 0) {
            return 0;
        }
        int dem = 0;
        for (int i = 0; i < ds.length && i < n; i++) {
            if (them(ds[i])) {
                dem++;
            }
        }
        return dem;
    }

    public int themN(HoaDon[] ds) {
        return ds == null ? 0 : themN(ds, ds.length);
    }

    public HoaDon timKiem(String ma) {
        int vt = timViTriTheoMa(ma);
        return vt == -1 ? null : dsHoaDon[vt];
    }

    public boolean xoaTheoMa(String ma) {
        int vt = timViTriTheoMa(ma);
        if (vt == -1) {
            return false;
        }
        xoaTaiViTri(vt);
        return true;
    }

    private void xoaTaiViTri(int vt) {
        for (int i = vt; i < soLuong - 1; i++) {
            dsHoaDon[i] = dsHoaDon[i + 1];
        }
        dsHoaDon[--soLuong] = null;
    }

    private int xoaTheoNgay(LocalDate ngay) {
        int dem = 0;
        for (int i = 0; i < soLuong;) {
            if (ngay.equals(dsHoaDon[i].getNgayLap())) {
                xoaTaiViTri(i);
                dem++;
            } else {
                i++;
            }
        }
        return dem;
    }

    private int xoaTheoMaKhachHang(String maKH) {
        int dem = 0;
        for (int i = 0; i < soLuong;) {
            KhachHang kh = dsHoaDon[i].getKhachHang();
            if (kh != null && kh.getMaKH() != null
                    && kh.getMaKH().trim().equalsIgnoreCase(maKH)) {
                xoaTaiViTri(i);
                dem++;
            } else {
                i++;
            }
        }
        return dem;
    }

    private int xoaTheoMaNhanVien(String maNV) {
        int dem = 0;
        for (int i = 0; i < soLuong;) {
            NhanVien nv = dsHoaDon[i].getNhanVien();
            if (nv != null && nv.getMaNV() != null
                    && nv.getMaNV().trim().equalsIgnoreCase(maNV)) {
                xoaTaiViTri(i);
                dem++;
            } else {
                i++;
            }
        }
        return dem;
    }

    private int xoaTheoPhuongThuc(String phuongThuc) {
        int dem = 0;
        for (int i = 0; i < soLuong;) {
            ThanhToan thanhToan = dsHoaDon[i].getThanhToan();
            if (thanhToan != null
                    && phuongThuc.equals(chuanHoa(thanhToan.getPhuongThuc()))) {
                xoaTaiViTri(i);
                dem++;
            } else {
                i++;
            }
        }
        return dem;
    }

    public boolean suaTheoMa(String ma) {
        HoaDon x = timKiem(ma);
        if (x == null) {
            return false;
        }
        x.setNgayLap(Main.docNgay("Ngay lap moi"));
        return true;
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
            HoaDon hd = dsHoaDon[i];
            if (hd.getThanhToan() == null) {
                continue;
            }
            double tongTien = hd.getTongTien();
            doanhThu += tongTien;
            if (lonNhat == null || tongTien > lonNhat.getTongTien()) {
                lonNhat = hd;
            }
        }
        System.out.println("Tong so hoa don: " + soLuong);
        System.out.println("Tong doanh thu: " + Main.dinhDangTien(doanhThu));
        if (lonNhat != null) {
            System.out.println("Hoa don da thanh toan lon nhat: " + lonNhat.getMaHD()
                    + " (" + Main.dinhDangTien(lonNhat.getTongTien()) + ")");
        } else {
            System.out.println("Chua co hoa don nao thanh toan.");
        }
    }

    public void napDuLieu(HoaDon[] ds) {
        for (int i = 0; i < soLuong; i++) {
            dsHoaDon[i] = null;
        }
        soLuong = 0;
        if (ds == null) {
            return;
        }

        int boQua = 0;
        for (HoaDon hd : ds) {
            if (!them(hd)) {
                boQua++;
            }
        }
        if (boQua > 0) {
            System.out.println("Bo qua " + boQua + " hoa don khong hop le, trung ma hoac vuot qua suc chua.");
        }
    }

    public HoaDon[] getDs() {
        HoaDon[] ketQua = new HoaDon[soLuong];
        System.arraycopy(dsHoaDon, 0, ketQua, 0, soLuong);
        return ketQua;
    }

    public int getSoLuong() {
        return soLuong;
    }

    private String chuanHoa(String giaTri) {
        return giaTri == null ? "" : giaTri.trim().toLowerCase(Locale.ROOT);
    }

    private boolean chuaTuKhoa(String giaTri, String tuKhoa) {
        return giaTri != null && giaTri.toLowerCase(Locale.ROOT).contains(tuKhoa);
    }

    private void inKetQua(int soKetQua, String thongBaoKhongTimThay) {
        if (soKetQua == 0) {
            System.out.println(thongBaoKhongTimThay);
        }
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
        int soKetQua = 0;

        switch (chon) {
            case 1: {
                String ma = Main.docChuoi("Nhap ma HD: ");
                HoaDon hd = timKiem(ma);
                if (hd != null) {
                    hd.inHoaDon();
                } else {
                    System.out.println("Khong tim thay ma " + ma);
                }
                return;
            }
            case 2: {
                LocalDate ngay = Main.docNgay("Nhap ngay lap");
                for (int i = 0; i < soLuong; i++) {
                    if (ngay.equals(dsHoaDon[i].getNgayLap())) {
                        dsHoaDon[i].inHoaDon();
                        soKetQua++;
                    }
                }
                inKetQua(soKetQua, "Khong tim thay hoa don nao trong ngay nay!");
                break;
            }
            case 3: {
                String tuKhoa = chuanHoa(Main.docChuoi("Nhap ma hoac ten khach hang: "));
                if (tuKhoa.isEmpty()) {
                    System.out.println("Tu khoa tim kiem khong duoc de trong!");
                    return;
                }
                for (int i = 0; i < soLuong; i++) {
                    KhachHang kh = dsHoaDon[i].getKhachHang();
                    if (kh != null && (chuaTuKhoa(kh.getMaKH(), tuKhoa)
                            || chuaTuKhoa(kh.getHoTen(), tuKhoa))) {
                        dsHoaDon[i].inHoaDon();
                        soKetQua++;
                    }
                }
                inKetQua(soKetQua, "Khong tim thay hoa don cua khach hang nay!");
                break;
            }
            case 4: {
                String tuKhoa = chuanHoa(Main.docChuoi("Nhap ma hoac ten nhan vien: "));
                if (tuKhoa.isEmpty()) {
                    System.out.println("Tu khoa tim kiem khong duoc de trong!");
                    return;
                }
                for (int i = 0; i < soLuong; i++) {
                    NhanVien nv = dsHoaDon[i].getNhanVien();
                    if (nv != null && (chuaTuKhoa(nv.getMaNV(), tuKhoa)
                            || chuaTuKhoa(nv.getHoTen(), tuKhoa))) {
                        dsHoaDon[i].inHoaDon();
                        soKetQua++;
                    }
                }
                inKetQua(soKetQua, "Khong tim thay hoa don do nhan vien nay lap!");
                break;
            }
            case 5: {
                double min = Main.docSoThuc("Tong tien toi thieu: ");
                double max = Main.docSoThuc("Tong tien toi da: ");
                if (!Double.isFinite(min) || !Double.isFinite(max) || min < 0 || max < 0 || min > max) {
                    System.out.println("Khoang tien khong hop le: vui long nhap hai so huu han, khong am va toi thieu <= toi da.");
                    return;
                }
                for (int i = 0; i < soLuong; i++) {
                    double tien = dsHoaDon[i].getTongTien();
                    if (tien >= min && tien <= max) {
                        dsHoaDon[i].inHoaDon();
                        soKetQua++;
                    }
                }
                inKetQua(soKetQua, "Khong tim thay hoa don nao trong khoang tien nay!");
                break;
            }
            case 6: {
                String maKM = chuanHoa(Main.docChuoi("Nhap ma KM (Enter de xem tat ca HD co khuyen mai): "));
                for (int i = 0; i < soLuong; i++) {
                    KhuyenMai km = dsHoaDon[i].getKhuyenMai();
                    if (km != null && (maKM.isEmpty() || chuaTuKhoa(km.getMaKM(), maKM))) {
                        dsHoaDon[i].inHoaDon();
                        soKetQua++;
                    }
                }
                inKetQua(soKetQua, "Khong tim thay hoa don nao phu hop!");
                break;
            }
            case 0:
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }

    private void menuXemVaXoaTheoTieuChi(int chon) {
        int soXoa;
        switch (chon) {
            case 2: {
                LocalDate ngay = Main.docNgay("Nhap ngay lap can xoa");
                soXoa = xoaTheoNgay(ngay);
                System.out.println(soXoa > 0 ? "Da xoa " + soXoa + " hoa don!"
                        : "Khong tim thay hoa don trong ngay nay!");
                break;
            }
            case 3: {
                String maKH = Main.docChuoi("Nhap ma KH can xoa hoa don: ").trim();
                if (maKH.isEmpty()) {
                    System.out.println("Ma khach hang khong duoc de trong!");
                    return;
                }
                soXoa = xoaTheoMaKhachHang(maKH);
                System.out.println(soXoa > 0 ? "Da xoa " + soXoa + " hoa don cua KH " + maKH
                        : "Khong tim thay hoa don nao!");
                break;
            }
            case 4: {
                String maNV = Main.docChuoi("Nhap ma NV can xoa hoa don: ").trim();
                if (maNV.isEmpty()) {
                    System.out.println("Ma nhan vien khong duoc de trong!");
                    return;
                }
                soXoa = xoaTheoMaNhanVien(maNV);
                System.out.println(soXoa > 0 ? "Da xoa " + soXoa + " hoa don cua NV " + maNV
                        : "Khong tim thay hoa don nao!");
                break;
            }
            case 5: {
                System.out.println("1. Tien mat");
                System.out.println("2. Chuyen khoan");
                int loai = Main.docSoNguyen("Chon phuong thuc thanh toan: ");
                String phuongThuc;
                if (loai == 1) {
                    phuongThuc = "tien mat";
                } else if (loai == 2) {
                    phuongThuc = "chuyen khoan";
                } else {
                    System.out.println("Lua chon khong hop le!");
                    return;
                }
                soXoa = xoaTheoPhuongThuc(phuongThuc);
                System.out.println(soXoa > 0 ? "Da xoa " + soXoa + " hoa don thanh toan " + phuongThuc + "!"
                        : "Khong tim thay hoa don nao!");
                break;
            }
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }

    public void menu() {
        int chon;
        do {
            System.out.println();
            System.out.println("--- QUAN LY HOA DON ---");
            System.out.println("1. Xem danh sach");
            System.out.println("2. Sua ngay lap hoa don");
            System.out.println("3. Xoa hoa don theo tieu chi");
            System.out.println("4. Tim kiem hoa don");
            System.out.println("5. Thong ke");
            System.out.println("0. Quay lai");
            System.out.println("(Lap hoa don moi tai muc Ban hang.)");
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
                    menuXoa();
                    break;
                case 4:
                    menuTimKiem();
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
        System.out.println("5. Xoa theo phuong thuc thanh toan");
        System.out.println("0. Quay lai");
        int chon = Main.docSoNguyen("Chon cach xoa: ");
        if (chon == 1) {
            String ma = Main.docChuoi("Nhap ma HD can xoa: ");
            System.out.println(xoaTheoMa(ma) ? "Xoa thanh cong!" : "Khong tim thay ma " + ma);
        } else if (chon >= 2 && chon <= 5) {
            menuXemVaXoaTheoTieuChi(chon);
        } else if (chon != 0) {
            System.out.println("Lua chon khong hop le!");
        }
    }
}
