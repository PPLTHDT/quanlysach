import java.time.LocalDate;

public class DanhSachPhieuNhap {
    public static final int MAX = 100;

    private PhieuNhap[] dsPhieuNhap;
    private int soLuong;
    private DanhSachNhaXuatBan dsNXB;
    private DanhSachSach dsSach;

    public DanhSachPhieuNhap() {
        this.dsPhieuNhap = new PhieuNhap[MAX];
        this.soLuong = 0;
    }

    public DanhSachPhieuNhap(DanhSachNhaXuatBan dsNXB, DanhSachSach dsSach) {
        this();
        this.dsNXB = dsNXB;
        this.dsSach = dsSach;
    }

    public void setDanhSachLienKet(DanhSachNhaXuatBan dsNXB, DanhSachSach dsSach) {
        this.dsNXB = dsNXB;
        this.dsSach = dsSach;
    }

    public boolean them(PhieuNhap x) {
        if (x == null || soLuong >= MAX) {
            return false;
        }
        if (timKiem(x.getMaPhieuNhap()) != null) {
            return false;
        }
        dsPhieuNhap[soLuong++] = x;
        return true;
    }

    public boolean suaTheoMa(String ma) {
        PhieuNhap pn = timKiem(ma);
        if (pn == null) {
            return false;
        }
        System.out.println("=== SUA THONG TIN PHIEU NHAP [" + pn.getMaPhieuNhap() + "] ===");
        pn.setNgayNhap(Main.docNgay("Ngay nhap moi"));
        if (dsNXB != null) {
            String maCu = (pn.getNhaXuatBan() != null) ? pn.getNhaXuatBan().getMaNXB() : "";
            String maNXBMoi = Main.docChuoiGiuCu("Ma NXB moi", maCu);
            if (!maNXBMoi.isEmpty()) {
                NhaXuatBan nxb = dsNXB.timKiem(maNXBMoi);
                if (nxb != null) {
                    pn.setNhaXuatBan(nxb);
                } else {
                    System.out.println("Khong tim thay NXB co ma " + maNXBMoi + ", giu nguyen NXB cu.");
                }
            }
        }
        return true;
    }

    public boolean xoaTheoMa(String ma) {
        if (ma == null || soLuong == 0) {
            return false;
        }
        int viTri = -1;
        for (int i = 0; i < soLuong; i++) {
            if (dsPhieuNhap[i].getMaPhieuNhap().equalsIgnoreCase(ma.trim())) {
                viTri = i;
                break;
            }
        }
        if (viTri == -1) {
            return false;
        }
        for (int i = viTri; i < soLuong - 1; i++) {
            dsPhieuNhap[i] = dsPhieuNhap[i + 1];
        }
        dsPhieuNhap[soLuong - 1] = null;
        soLuong--;
        return true;
    }

    public PhieuNhap timKiem(String ma) {
        if (ma == null) {
            return null;
        }
        for (int i = 0; i < soLuong; i++) {
            if (dsPhieuNhap[i].getMaPhieuNhap().equalsIgnoreCase(ma.trim())) {
                return dsPhieuNhap[i];
            }
        }
        return null;
    }

    public void nhap() {
        if (dsNXB == null || dsSach == null) {
            System.out.println("Hay dung chuc nang Lap phieu nhap de tao phieu nhap moi!");
            return;
        }
        nhap(dsNXB, dsSach);
    }

    public void nhap(DanhSachNhaXuatBan dsNXB, DanhSachSach dsSach) {
        if (dsNXB == null || dsNXB.getSoLuong() == 0) {
            System.out.println("Danh sach Nha Xuat Ban dang trong! Vui long them Nha Xuat Ban truoc khi nhap hang.");
            return;
        }
        if (dsSach == null || dsSach.getSoLuong() == 0) {
            System.out.println("Danh sach Sach dang trong! Vui long them Sach vao danh muc truoc khi nhap kho.");
            return;
        }

        System.out.println("\n--- LAP PHIEU NHAP KHO MOI ---");
        String maPN = Main.docChuoi("Nhap ma phieu nhap (vd: PN01): ");
        if (maPN.isEmpty()) {
            System.out.println("Ma phieu nhap khong duoc de trong!");
            return;
        }
        if (timKiem(maPN) != null) {
            System.out.println("Loi: Ma phieu nhap " + maPN + " da ton tai!");
            return;
        }

        LocalDate ngayNhap = Main.docNgay("Ngay nhap");

        String maNXB = Main.docChuoi("Nhap ma nha xuat ban: ");
        NhaXuatBan nxb = dsNXB.timKiem(maNXB);
        if (nxb == null) {
            System.out.println("Khong tim thay Nha Xuat Ban co ma: " + maNXB);
            return;
        }

        PhieuNhap pn = new PhieuNhap(maPN, ngayNhap, nxb);

        int n = Main.docSoNguyen("Nhap so loai sach muon nhap: ");
        for (int i = 0; i < n; i++) {
            System.out.println("\n-> Nhap chi tiet thu " + (i + 1) + ":");
            String maCT = Main.docChuoi("  Ma chi tiet (Enter de tu sinh): ");
            String maSach = Main.docChuoi("  Ma sach: ");
            Sach sach = dsSach.timKiem(maSach);
            if (sach == null) {
                System.out.println("  Khong tim thay sach co ma: " + maSach + " trong kho!");
                continue;
            }
            System.out.println("  -> Sach: " + sach.getTenSach() + " | Ton hien tai: " + sach.getSoLuongTon());
            int sl = Main.docSoNguyen("  So luong nhap: ");
            double donGia = Main.docSoThuc("  Don gia nhap (VND): ");
            if (sl <= 0 || donGia < 0) {
                System.out.println("  So luong hoac donGia khong hop le, bo qua dong nay!");
                continue;
            }

            ChiTietPhieuNhap ct = maCT.isEmpty()
                    ? new ChiTietPhieuNhap(sach, sl, donGia)
                    : new ChiTietPhieuNhap(maCT, sach, sl, donGia);
            pn.themChiTiet(ct);
        }

        if (pn.getSoLuongChiTiet() == 0) {
            System.out.println("Phieu nhap khong co chi tiet sach hop le nao, huy luu phieu!");
            return;
        }

        if (them(pn)) {
            pn.nhapKho();
            System.out.println("Lap phieu nhap va cap nhat ton kho thanh cong!");
            pn.inPhieuNhap();
        } else {
            System.out.println("Khong the them phieu nhap vao danh sach!");
        }
    }

    public void xuat() {
        if (soLuong == 0) {
            System.out.println("\n(Danh sach phieu nhap hien dang trong)");
            return;
        }
        System.out.println("\n========================================================");
        System.out.println("              DANH SACH PHIEU NHAP HANG                 ");
        System.out.println("========================================================");
        for (int i = 0; i < soLuong; i++) {
            System.out.println("\n[Phieu nhap thu " + (i + 1) + "]:");
            dsPhieuNhap[i].inPhieuNhap();
        }
    }

    public void thongKe() {
        System.out.println("\n========================================================");
        System.out.println("           THONG KE DANH SACH PHIEU NHAP HANG           ");
        System.out.println("========================================================");
        System.out.println("Tong so luong phieu nhap: " + soLuong);
        double tongChiPhi = 0.0;
        PhieuNhap maxPN = null;

        for (int i = 0; i < soLuong; i++) {
            double tien = dsPhieuNhap[i].tinhTongTien();
            tongChiPhi += tien;
            if (maxPN == null || tien > maxPN.getTongTien()) {
                maxPN = dsPhieuNhap[i];
            }
        }

        System.out.println("Tong chi phi da chi cho nhap sach : " + Main.dinhDangTien(tongChiPhi));
        if (maxPN != null) {
            System.out.println("Phieu nhap co gia tri lon nhat     : " + maxPN.getMaPhieuNhap()
                    + " (" + Main.dinhDangTien(maxPN.getTongTien()) + ")");
        }
        System.out.println("========================================================");
    }

    public void napDuLieu(PhieuNhap[] ds) {
        soLuong = 0;
        if (ds == null) {
            return;
        }
        for (int i = 0; i < ds.length && soLuong < MAX; i++) {
            if (ds[i] != null) {
                dsPhieuNhap[soLuong++] = ds[i];
            }
        }
    }

    public PhieuNhap[] getDs() {
        PhieuNhap[] ketQua = new PhieuNhap[soLuong];
        for (int i = 0; i < soLuong; i++) {
            ketQua[i] = dsPhieuNhap[i];
        }
        return ketQua;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void menuTimKiem() {
        System.out.println("\n--- TIM KIEM PHIEU NHAP ---");
        System.out.println("1. Theo ma phieu nhap");
        System.out.println("2. Theo ngay nhap (dd/MM/yyyy)");
        System.out.println("3. Theo ma hoac ten nha xuat ban");
        System.out.println("4. Theo khoang tong tien");
        System.out.println("0. Quay lai");
        int chon = Main.docSoNguyen("Chon cach tim: ");
        boolean thay = false;
        switch (chon) {
            case 1: {
                String ma = Main.docChuoi("Nhap ma phieu nhap: ");
                PhieuNhap pn = timKiem(ma);
                if (pn != null) {
                    pn.inPhieuNhap();
                } else {
                    System.out.println("Khong tim thay ma " + ma);
                }
                break;
            }
            case 2: {
                LocalDate ngay = Main.docNgay("Nhap ngay nhap");
                for (int i = 0; i < soLuong; i++) {
                    if (dsPhieuNhap[i].getNgayNhap() != null && dsPhieuNhap[i].getNgayNhap().equals(ngay)) {
                        dsPhieuNhap[i].inPhieuNhap();
                        thay = true;
                    }
                }
                if (!thay) {
                    System.out.println("Khong tim thay phieu nhap nao trong ngay nay!");
                }
                break;
            }
            case 3: {
                String tk = Main.docChuoi("Nhap ma hoac ten NXB: ").toLowerCase();
                for (int i = 0; i < soLuong; i++) {
                    NhaXuatBan nxb = dsPhieuNhap[i].getNhaXuatBan();
                    if (nxb != null && ((nxb.getMaNXB() != null && nxb.getMaNXB().toLowerCase().contains(tk))
                            || (nxb.getTenNXB() != null && nxb.getTenNXB().toLowerCase().contains(tk)))) {
                        dsPhieuNhap[i].inPhieuNhap();
                        thay = true;
                    }
                }
                if (!thay) {
                    System.out.println("Khong tim thay phieu nhap cua NXB nay!");
                }
                break;
            }
            case 4: {
                double min = Main.docSoThuc("Tong tien toi thieu: ");
                double max = Main.docSoThuc("Tong tien toi da: ");
                for (int i = 0; i < soLuong; i++) {
                    double tien = dsPhieuNhap[i].tinhTongTien();
                    if (tien >= min && tien <= max) {
                        dsPhieuNhap[i].inPhieuNhap();
                        thay = true;
                    }
                }
                if (!thay) {
                    System.out.println("Khong tim thay phieu nhap nao trong khoang tien nay!");
                }
                break;
            }
            case 0:
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }

    public void menuXoa() {
        System.out.println("\n--- XOA PHIEU NHAP ---");
        System.out.println("1. Xoa theo ma phieu nhap");
        System.out.println("2. Xoa theo ngay nhap (dd/MM/yyyy)");
        System.out.println("3. Xoa theo ma nha xuat ban");
        System.out.println("0. Quay lai");
        int chon = Main.docSoNguyen("Chon cach xoa: ");
        switch (chon) {
            case 1: {
                String ma = Main.docChuoi("Nhap ma phieu nhap can xoa: ");
                if (xoaTheoMa(ma)) {
                    System.out.println("Xoa thanh cong!");
                } else {
                    System.out.println("Khong tim thay ma " + ma);
                }
                break;
            }
            case 2: {
                LocalDate ngay = Main.docNgay("Nhap ngay nhap can xoa");
                int dem = 0;
                for (int i = 0; i < soLuong; i++) {
                    if (dsPhieuNhap[i].getNgayNhap() != null && dsPhieuNhap[i].getNgayNhap().equals(ngay)) {
                        xoaTheoMa(dsPhieuNhap[i].getMaPhieuNhap());
                        i--;
                        dem++;
                    }
                }
                System.out.println(dem > 0 ? "Da xoa " + dem + " phieu nhap!" : "Khong tim thay phieu nhap trong ngay nay!");
                break;
            }
            case 3: {
                String maNXB = Main.docChuoi("Nhap ma NXB can xoa phieu nhap: ");
                int dem = 0;
                for (int i = 0; i < soLuong; i++) {
                    if (dsPhieuNhap[i].getNhaXuatBan() != null
                            && dsPhieuNhap[i].getNhaXuatBan().getMaNXB() != null
                            && dsPhieuNhap[i].getNhaXuatBan().getMaNXB().equalsIgnoreCase(maNXB)) {
                        xoaTheoMa(dsPhieuNhap[i].getMaPhieuNhap());
                        i--;
                        dem++;
                    }
                }
                System.out.println(dem > 0 ? "Da xoa " + dem + " phieu nhap cua NXB " + maNXB : "Khong tim thay phieu nhap nao!");
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
            System.out.println("--- DanhSachPhieuNhap: QUAN LY PHIEU NHAP ---");
            System.out.println("1. Lap phieu nhap moi");
            System.out.println("2. Xem danh sach");
            System.out.println("3. Sua theo ma");
            System.out.println("4. Xoa (da tieu chi / da thuoc tinh)");
            System.out.println("5. Tim kiem (da tieu chi / da thuoc tinh)");
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
                case 4:
                    menuXoa();
                    break;
                case 5:
                    menuTimKiem();
                    break;
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
