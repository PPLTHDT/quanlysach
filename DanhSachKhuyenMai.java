public class DanhSachKhuyenMai {
    public static final int MAX = 100;

    private KhuyenMai[] dsKhuyenMai;
    private int soLuong;

    public DanhSachKhuyenMai() {
        this.dsKhuyenMai = new KhuyenMai[MAX];
        this.soLuong = 0;
    }

    // Tim vi tri cua phan tu theo ma, khong thay tra ve -1
    private int timViTri(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (dsKhuyenMai[i].getMaKM().equals(ma)) {
                return i;
            }
        }
        return -1;
    }

    public boolean them(KhuyenMai x) {
        if (x == null || soLuong >= MAX) {
            return false;
        }
        if (timViTri(x.getMaKM()) != -1) {
            return false; // trung ma
        }
        dsKhuyenMai[soLuong] = x;
        soLuong++;
        return true;
    }

    public KhuyenMai timKiem(String ma) {
        int vt = timViTri(ma);
        if (vt == -1) {
            return null;
        }
        return dsKhuyenMai[vt];
    }

    public boolean xoaTheoMa(String ma) {
        int vt = timViTri(ma);
        if (vt == -1) {
            return false;
        }
        for (int i = vt; i < soLuong - 1; i++) {
            dsKhuyenMai[i] = dsKhuyenMai[i + 1];
        }
        dsKhuyenMai[soLuong - 1] = null;
        soLuong--;
        return true;
    }

    public boolean suaTheoMa(String ma) {
        KhuyenMai x = timKiem(ma);
        if (x == null) {
            return false;
        }
        x.setTenKM(Main.docChuoiGiuCu("Ten khuyen mai moi", x.getTenKM()));
        x.setPhanTramGiam(Main.docSoThuc("Phan tram giam moi: "));
        x.setNgayBatDau(Main.docNgay("Ngay bat dau moi"));
        x.setNgayKetThuc(Main.docNgay("Ngay ket thuc moi"));
        return true;
    }

    public void nhap() {
        int n = Main.docSoNguyen("Nhap so luong can them: ");
        for (int i = 0; i < n; i++) {
            System.out.println("--- Phan tu thu " + (i + 1) + " ---");
            KhuyenMai x = nhapMot();
            if (them(x)) {
                System.out.println("Them thanh cong!");
            } else {
                System.out.println("Them that bai (trung ma hoac danh sach day)!");
            }
        }
    }

    private KhuyenMai nhapMot() {
        String ma = Main.docChuoi("Ma khuyen mai: ");
        String ten = Main.docChuoi("Ten khuyen mai: ");
        double phanTram = Main.docSoThuc("Phan tram giam (0-100): ");
        java.time.LocalDate bd = Main.docNgay("Ngay bat dau");
        java.time.LocalDate kt = Main.docNgay("Ngay ket thuc");
        return new KhuyenMai(ma, ten, phanTram, bd, kt);
    }

    public void xuat() {
        if (soLuong == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        for (int i = 0; i < soLuong; i++) {
            dsKhuyenMai[i].hienThi();
        }
    }

    public void thongKe() {
        int conHieuLuc = 0;
        for (int i = 0; i < soLuong; i++) {
            if (dsKhuyenMai[i].conHieuLuc()) {
                conHieuLuc++;
            }
        }
        System.out.println("Tong so khuyen mai: " + soLuong);
        System.out.println("Dang con hieu luc: " + conHieuLuc);
        System.out.println("Da het han / chua bat dau: " + (soLuong - conHieuLuc));
    }

    public void napDuLieu(KhuyenMai[] ds) {
        soLuong = 0;
        for (int i = 0; i < ds.length && i < MAX; i++) {
            dsKhuyenMai[i] = ds[i];
            soLuong++;
        }
    }

    // Tra ve mang co do dai dung bang so phan tu hien co
    public KhuyenMai[] getDs() {
        KhuyenMai[] ketQua = new KhuyenMai[soLuong];
        for (int i = 0; i < soLuong; i++) {
            ketQua[i] = dsKhuyenMai[i];
        }
        return ketQua;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void menu() {
        int chon;
        do {
            System.out.println();
            System.out.println("--- DanhSachKhuyenMai: QUAN LY KHUYEN MAI ---");
            System.out.println("1. Them");
            System.out.println("2. Xem danh sach");
            System.out.println("3. Sua theo ma");
            System.out.println("4. Xoa theo ma");
            System.out.println("5. Tim kiem theo ma");
            System.out.println("6. Thong ke");
            System.out.println("0. Quay lai");
            chon = Main.docSoNguyen("Chon: ");
            switch (chon) {
                case 1:
                    nhap();
                    break;
                case 2:
                    xuat();
                    break;
                case 3: {
                    String ma = Main.docChuoi("Nhap ma can sua: ");
                    if (suaTheoMa(ma)) {
                        System.out.println("Sua thanh cong!");
                    } else {
                        System.out.println("Khong tim thay ma " + ma);
                    }
                    break;
                }
                case 4: {
                    String ma = Main.docChuoi("Nhap ma can xoa: ");
                    if (xoaTheoMa(ma)) {
                        System.out.println("Xoa thanh cong!");
                    } else {
                        System.out.println("Khong tim thay ma " + ma);
                    }
                    break;
                }
                case 5: {
                    String ma = Main.docChuoi("Nhap ma can tim: ");
                    KhuyenMai kq = timKiem(ma);
                    if (kq == null) {
                        System.out.println("Khong tim thay ma " + ma);
                    } else {
                        kq.hienThi();
                    }
                    break;
                }
                case 6:
                    thongKe();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }
}
