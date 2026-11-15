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
        if(vt == -1) {
            return false;
        }

        for(int i = vt; i < soLuong - 1; i++) {
            if(dsKhuyenMai[i].getMaKM().equalsIgnoreCase(ma)) {
                dsKhuyenMai[i] = dsKhuyenMai[i+1];
            }
        }

        dsKhuyenMai[vt - 1] = null;
        soLuong--;
        return true;
    }

    public void menuXoa() {
        System.out.println("\n--- XOA KHUYEN MAI ---");
        System.out.println("1. Xoa theo ma KM");
        System.out.println("2. Xoa theo ten khuyen mai (hoac gan dung)");
        System.out.println("3. Xoa theo muc phan tram giam (%)");
        System.out.println("4. Xoa tat ca khuyen mai da het han");
        System.out.println("0. Quay lai");
        int chon = Main.docSoNguyen("Chon: ");
        switch (chon) {
            case 1: {
                String ma = Main.docChuoi("Nhap ma KM can xoa: ");
                System.out.println(xoaTheoMa(ma) ? "Xoa thanh cong!" : "Khong tim thay ma " + ma);
                break;
            }
            case 2: {
                String ten = Main.docChuoi("Nhap ten khuyen mai can xoa: ").toLowerCase();
                int dem = 0;
                for (int i = 0; i < soLuong; i++) {
                    if (dsKhuyenMai[i].getTenKM().toLowerCase().contains(ten)) {
                        xoaTheoMa(dsKhuyenMai[i].getMaKM());
                        i--; // Lui chi so de tranh bo sot phan tu sau khi dich mang
                        dem++;
                    }
                }
                if (dem > 0) {
                    System.out.println("Da xoa thanh cong " + dem + " khuyen mai!");
                } else {
                    System.out.println("Khong tim thay khuyen mai nao co ten phu hop!");
                }
                break;
            }
            case 3: {
                double pt = Main.docSoThuc("Nhap muc phan tram giam (%): ");
                System.out.println("1. Xoa cac KM co % giam DUNG BANG " + pt + "%");
                System.out.println("2. Xoa cac KM co % giam THAP HON HOAC BANG " + pt + "%");
                int kieuXoa = Main.docSoNguyen("Chon tieu chi: ");
                
                int dem = 0;
                for (int i = 0; i < soLuong; i++) {
                    double phanTram = dsKhuyenMai[i].getPhanTramGiam();
                    if ((kieuXoa == 1 && phanTram == pt) || (kieuXoa == 2 && phanTram <= pt)) {
                        xoaTheoMa(dsKhuyenMai[i].getMaKM());
                        i--;
                        dem++;
                    }
                }
                if (dem > 0) {
                    System.out.println("Da xoa thanh cong " + dem + " khuyen mai!");
                } else {
                    System.out.println("Khong co khuyen mai nao thoa man dieu kien xoa!");
                }
                break;
            }
            case 4: {
                int dem = 0;
                for (int i = 0; i < soLuong; i++) {
                    if (!dsKhuyenMai[i].conHieuLuc()) {
                        xoaTheoMa(dsKhuyenMai[i].getMaKM()); 
                        i--; 
                        dem++;
                    }
                }
                System.out.println("Da don dep va xoa " + dem + " khuyen mai da het han!");
                break;
            }
            case 0: 
                break;
            default: 
                System.out.println("Lua chon khong hop le!");
        }
    }

    public void menuTimKiem() {
        System.out.println("\n--- TIM KIEM KHUYEN MAI ---");
        System.out.println("1. Theo ma khuyen mai");
        System.out.println("2. Theo ten khuyen mai (gan dung)");
        System.out.println("3. Loc cac khuyen mai dang con hieu luc");
        System.out.println("4. Theo khoang phan tram giam (%)");
        System.out.println("5. Theo khoang thoi gian dien ra");
        System.out.println("0. Quay lai");
        int chon = Main.docSoNguyen("Chon cach tim: ");
        boolean thay = false;
        switch (chon) {
            case 1: {
                String ma = Main.docChuoi("Nhap ma KM: ");
                KhuyenMai km = timKiem(ma);
                if (km != null) {
                    km.hienThi();
                } else {
                    System.out.println("Khong tim thay ma " + ma);
                }
                break;
            }
            case 2: {
                String ten = Main.docChuoi("Nhap ten khuyen mai can tim: ").toLowerCase();
                for (int i = 0; i < soLuong; i++) {
                    if (dsKhuyenMai[i].getTenKM().toLowerCase().contains(ten)) {
                        dsKhuyenMai[i].hienThi();
                        thay = true;
                    }
                }
                if (!thay) {
                    System.out.println("Khong tim thay khuyen mai nao co ten phu hop!");
                }
                break;
            }
            case 3: {
                for (int i = 0; i < soLuong; i++) {
                    if (dsKhuyenMai[i].conHieuLuc()) {
                        dsKhuyenMai[i].hienThi();
                        thay = true;
                    }
                }
                if (!thay) {
                    System.out.println("Khong co khuyen mai nào dang trong thoi gian hieu luc!");
                }
                break;
            }
            case 4: {
                double min = Main.docSoThuc("Muc giam toi thieu (%): ");
                double max = Main.docSoThuc("Muc giam toi da (%): ");
                for (int i = 0; i < soLuong; i++) {
                    double pt = dsKhuyenMai[i].getPhanTramGiam();
                    if (pt >= min && pt <= max) {
                        dsKhuyenMai[i].hienThi();
                        thay = true;
                    }
                }
                if (!thay) {
                    System.out.println("Khong tim thay khuyen mai nao trong khoang phan tram nay!");
                }
                break;
            }
            case 5: {
                System.out.println("Nhap moc thoi gian de kiem tra chuong trinh dien ra:");
                java.time.LocalDate ngayKhaoSat = Main.docNgay("Nhap ngay");
                for (int i = 0; i < soLuong; i++) {
                    KhuyenMai km = dsKhuyenMai[i];
                    // Kiem tra ngay khao sat co nam trong [ngayBatDau, ngayKetThuc] hay khong
                    if (km.getNgayBatDau() != null && km.getNgayKetThuc() != null) {
                        if (!ngayKhaoSat.isBefore(km.getNgayBatDau()) && !ngayKhaoSat.isAfter(km.getNgayKetThuc())) {
                            km.hienThi();
                            thay = true;
                        }
                    }
                }
                if (!thay) {
                    System.out.println("Khong co chuong trinh khuyen mai nao dien ra vao ngay nay!");
                }
                break;
            }
            case 0: 
                break;
            default: 
                System.out.println("Lua chon khong hop le!");
        }
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
}

