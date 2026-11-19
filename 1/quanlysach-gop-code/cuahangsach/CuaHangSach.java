package cuahangsach;

import java.time.LocalDate;

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
        this.danhSachTheLoai = new DanhSachTheLoai();
        this.danhSachTacGia = new DanhSachTacGia();
        this.danhSachNhaXuatBan = new DanhSachNhaXuatBan();
        this.danhSachSach = new DanhSachSach(this.danhSachTacGia, this.danhSachTheLoai, this.danhSachNhaXuatBan);
        this.danhSachKhachHang = new DanhSachKhachHang();
        this.danhSachNhanVien = new DanhSachNhanVien();
        this.danhSachHoaDon = new DanhSachHoaDon();
        this.danhSachPhieuNhap = new DanhSachPhieuNhap(this.danhSachNhaXuatBan, this.danhSachSach);
        this.danhSachKhuyenMai = new DanhSachKhuyenMai();
        this.thongKe = new ThongKe();
        this.docGhiFile = new DocGhiFile();
    }

    public void khoiTao() {
        docDuLieuTuFile();
    }

    public void docDuLieuTuFile() {
        if (docGhiFile == null) {
            return;
        }
        // Nap dung thu tu phu thuoc: The loai -> Tac gia -> NXB -> Sach -> KH / NV / KM -> Hoa don -> Phieu nhap
        danhSachTheLoai.napDuLieu(docGhiFile.docFileTheLoai());
        danhSachTacGia.napDuLieu(docGhiFile.docFileTacGia());
        danhSachNhaXuatBan.napDuLieu(docGhiFile.docFileNhaXuatBan());
        danhSachSach.napDuLieu(docGhiFile.docFileSach(danhSachTheLoai, danhSachTacGia, danhSachNhaXuatBan));
        danhSachKhachHang.napDuLieu(docGhiFile.docFileKhachHang());
        danhSachNhanVien.napDuLieu(docGhiFile.docFileNhanVien(), docGhiFile.docFileQuanLy());
        danhSachKhuyenMai.napDuLieu(docGhiFile.docFileKhuyenMai());
        danhSachHoaDon.napDuLieu(docGhiFile.docFileHoaDon(danhSachKhachHang, danhSachNhanVien, danhSachKhuyenMai, danhSachSach));
        danhSachPhieuNhap.napDuLieu(docGhiFile.docFilePhieuNhap(danhSachNhaXuatBan, danhSachSach));
        System.out.println("Da doc du lieu tu file thanh cong!");
    }

    public void ghiDuLieuRaFile() {
        if (docGhiFile == null) {
            return;
        }
        docGhiFile.ghiFileTheLoai(danhSachTheLoai.getDs());
        docGhiFile.ghiFileTacGia(danhSachTacGia.getDs());
        docGhiFile.ghiFileNhaXuatBan(danhSachNhaXuatBan.getDs());
        docGhiFile.ghiFileSach(danhSachSach.getDs());
        docGhiFile.ghiFileKhachHang(danhSachKhachHang.getDs());
        docGhiFile.ghiFileNhanVien(danhSachNhanVien.getDsNhanVien());
        docGhiFile.ghiFileQuanLy(danhSachNhanVien.getDsQuanLy());
        docGhiFile.ghiFileKhuyenMai(danhSachKhuyenMai.getDs());
        docGhiFile.ghiFileHoaDon(danhSachHoaDon.getDs());
        docGhiFile.ghiFilePhieuNhap(danhSachPhieuNhap.getDs());
        System.out.println("Da ghi du lieu ra file thanh cong!");
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
        System.out.println(" 6. Quan ly Nhan Vien & Quan Ly");
        System.out.println(" 7. Quan ly Hoa Don & Lap Hoa Don Ban Hang");
        System.out.println(" 8. Quan ly Phieu Nhap & Lap Phieu Nhap Hang");
        System.out.println(" 9. Quan ly Khuyen Mai");
        System.out.println(" 10. Thong ke bao cao");
        System.out.println(" 11. Doc / Ghi du lieu file");
        System.out.println(" 0. Thoat chuong trinh");
        System.out.println("=================================================");
    }

    public void xuLy() {
        int chon;
        do {
            hienThiMenu();
            chon = Main.docSoNguyen("Moi ban chon chuc nang (0 - 11): ");

            switch (chon) {
                case 1:
            if (danhSachSach != null) {
                danhSachSach.menu();
            }
            break;
                case 2:
                    quanLyTheLoai();
                    break;
                case 3:
                    if (danhSachTacGia != null) {
                        danhSachTacGia.menu();
                    }
                    break;
                case 4:
                    if (danhSachNhaXuatBan != null) {
                        danhSachNhaXuatBan.menu();
                    }
                    break;
                case 5:
                    if (danhSachKhachHang != null) {
                        danhSachKhachHang.menu();
                    }
                    break;
                case 6:
                    if (danhSachNhanVien != null) {
                        danhSachNhanVien.menu();
                    }
                    break;
                case 7:
                    quanLyHoaDon();
                    break;
                case 8:
                    if (danhSachPhieuNhap != null) {
                        danhSachPhieuNhap.setDanhSachLienKet(danhSachNhaXuatBan, danhSachSach);
                        danhSachPhieuNhap.menu();
                    }
                    break;
                case 9:
                    if (danhSachKhuyenMai != null) {
                        danhSachKhuyenMai.menu();
                    }
                    break;
                case 10:
                    quanLyThongKe();
                    break;
                case 11:
                    quanLyFile();
                    break;
                case 0:
                    ghiDuLieuRaFile();
                    System.out.println("Tam biet quy khach!");
                    break;
                default:
                    System.out.println("Chuc nang khong hop le, vui long chon lai!");
                    break;
            }
        } while (chon != 0);
    }

    private KhachHang timKhachHangTheoMa(String maKH) {
        if (danhSachKhachHang == null || maKH == null || maKH.isEmpty()) {
            return null;
        }
        KhachHang[] ds = danhSachKhachHang.getDs();
        int n = danhSachKhachHang.getSoLuong();
        for (int i = 0; i < n && i < ds.length; i++) {
            if (ds[i] != null && ds[i].getMaKH() != null && ds[i].getMaKH().equalsIgnoreCase(maKH)) {
                return ds[i];
            }
        }
        return null;
    }

    private NhanVien timNhanVienTheoMa(String maNV) {
        if (danhSachNhanVien == null || maNV == null || maNV.isEmpty()) {
            return null;
        }
        NhanVien[] ds = danhSachNhanVien.getDsNhanVien();
        int n = danhSachNhanVien.getSoLuongNV();
        for (int i = 0; i < n && i < ds.length; i++) {
            if (ds[i] != null && ds[i].getMaNV() != null && ds[i].getMaNV().equalsIgnoreCase(maNV)) {
                return ds[i];
            }
        }
        return null;
    }

    private void lapHoaDon() {
        if (danhSachSach == null || danhSachSach.getSoLuong() == 0) {
            System.out.println("Kho sach hien dang trong, khong the lap hoa don!");
            return;
        }

        System.out.println("\n--- LAP HOA DON BAN HANG MOI ---");
        String maHD = Main.docChuoi("Nhap ma hoa don (vd: HD01): ");
        if (maHD.isEmpty()) {
            System.out.println("Ma hoa don khong duoc de trong!");
            return;
        }
        if (danhSachHoaDon.timKiem(maHD) != null) {
            System.out.println("Loi: Ma hoa don " + maHD + " da ton tai!");
            return;
        }

        LocalDate ngayLap = Main.docNgay("Ngay lap hoa don");

        NhanVien nv = null;
        String maNV = Main.docChuoi("Nhap ma nhan vien lap (Enter de bo qua): ");
        if (!maNV.isEmpty()) {
            nv = timNhanVienTheoMa(maNV);
            if (nv == null) {
                System.out.println("Khong tim thay nhan vien co ma: " + maNV);
                return;
            }
        }

        KhachHang kh = null;
        String maKH = Main.docChuoi("Nhap ma khach hang (Enter neu la khach le): ");
        if (!maKH.isEmpty()) {
            kh = timKhachHangTheoMa(maKH);
            if (kh == null) {
                System.out.println("Khong tim thay khach hang co ma " + maKH + ", tinh la khach le.");
            }
        }

        HoaDon hd = new HoaDon(maHD, ngayLap, kh, nv);

        int soMatHang = Main.docSoNguyen("Nhap so loai sach khach mua: ");
        for (int i = 0; i < soMatHang; i++) {
            System.out.println("\n-> Nhap chi tiet thu " + (i + 1) + ":");
            String maCT = Main.docChuoi("  Ma chi tiet (vd: CTHD01): ");
            String maSach = Main.docChuoi("  Ma sach: ");
            Sach sach = danhSachSach.timTheoMa(maSach);
            if (sach == null) {
                System.out.println("  Khong tim thay sach co ma: " + maSach);
                continue;
            }
            System.out.println("  -> Sach: " + sach.getTenSach()
                    + " | Gia ban: " + Main.dinhDangTien(sach.getGiaBan())
                    + " | Ton kho: " + sach.getSoLuongTon());
            int soLuongMua = Main.docSoNguyen("  So luong mua: ");
            if (maCT.isEmpty()) {
                maCT = String.format("CTHD%02d", i + 1);
            }
            try {
                ChiTietHoaDon ct = new ChiTietHoaDon(maCT, sach, soLuongMua);
                if (!hd.themChiTiet(ct)) {
                    System.out.println("  Khong the them chi tiet (so luong khong hop le hoac vuot qua ton kho)!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("  Loi: " + e.getMessage());
            }
        }

        if (hd.getSoLuongChiTiet() == 0) {
            System.out.println("Hoa don khong co san pham hop le nao, huy lap hoa don!");
            return;
        }

        hd.tinhTongTien();
        System.out.println("Tam tinh: " + Main.dinhDangTien(hd.getTongTien()));

        String maKM = Main.docChuoi("Nhap ma khuyen mai (Enter de bo qua): ");
        if (!maKM.isEmpty() && danhSachKhuyenMai != null) {
            KhuyenMai km = danhSachKhuyenMai.timKiem(maKM);
            if (km != null) {
                hd.apDungKhuyenMai(km);
            } else {
                System.out.println("Khong tim thay ma khuyen mai: " + maKM);
            }
        }

        double canTra = hd.tinhTienPhaiTra();
        System.out.println("Tong tien khach phai tra: " + Main.dinhDangTien(canTra));

        System.out.println("Chon phuong thuc thanh toan: 1. Tien mat | 2. Chuyen khoan");
        int loaiTT = Main.docSoNguyen("Chon (1-2): ");
        boolean thanhCong;
        if (loaiTT == 2) {
            String soTK = Main.docChuoi("Nhap so tai khoan: ");
            String nganHang = Main.docChuoi("Nhap ten ngan hang: ");
            String maGD = Main.docChuoi("Nhap ma giao dich: ");
            hd.setThanhToan(new ThanhToanChuyenKhoan(soTK, nganHang, maGD));
            thanhCong = hd.thucHienThanhToan(canTra);
        } else {
            double tienKhachDua = Main.docSoThuc("Nhap so tien khach dua: ");
            hd.setThanhToan(new ThanhToanTienMat(tienKhachDua));
            thanhCong = hd.thucHienThanhToan(tienKhachDua);
        }

        if (!thanhCong) {
            System.out.println("Thanh toan that bai (so tien khach dua khong du hoac thong tin khong hop le)!");
            return;
        }

        if (danhSachHoaDon.them(hd)) {
            System.out.println("Lap hoa don thanh cong!");
            hd.inHoaDon();
        } else {
            System.out.println("Loi: Khong the luu hoa don vao danh sach!");
        }
    }

    private void lapPhieuNhap() {
        if (danhSachPhieuNhap != null) {
            danhSachPhieuNhap.setDanhSachLienKet(danhSachNhaXuatBan, danhSachSach);
            danhSachPhieuNhap.nhap(danhSachNhaXuatBan, danhSachSach);
        }
    }

    private void quanLyHoaDon() {
        int chon;
        do {
            System.out.println("\n--- QUAN LY HOA DON & BAN HANG ---");
            System.out.println("1. Lap hoa don ban hang moi");
            System.out.println("2. Menu quan ly danh sach hoa don (Xem / Sua / Xoa / Tim kiem / Thong ke)");
            System.out.println("0. Quay lai");
            chon = Main.docSoNguyen("Chon: ");
            switch (chon) {
                case 1:
                    lapHoaDon();
                    break;
                case 2:
                    if (danhSachHoaDon != null) {
                        danhSachHoaDon.menu();
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    private void quanLyTheLoai() {
        if (danhSachTheLoai != null) {
            danhSachTheLoai.menu();
        }
    }

    private boolean xoaTheLoai(String ma) {
        if (danhSachTheLoai == null || ma == null) {
            return false;
        }
        if (danhSachSach != null) {
            Sach[] ds = danhSachSach.getDs();
            for (int i = 0; i < danhSachSach.getSoLuong() && i < ds.length; i++) {
                if (ds[i] != null && ds[i].getTheLoai() != null
                        && ma.equalsIgnoreCase(ds[i].getTheLoai().getMaTheLoai())) {
                    System.out.println("Khong the xoa! The loai nay dang duoc su dung boi sach: " + ds[i].getTenSach());
                    return false;
                }
            }
        }
        return danhSachTheLoai.xoaTheoMa(ma);
    }

    private void quanLyThongKe() {
        if (thongKe == null) return;
        int chon;
        do {
            System.out.println("\n--- THONG KE BAO CAO CUA HANG ---");
            System.out.println("1. Thong ke tong quan (Doanh thu, Ton kho, Ban chay, Sap het)");
            System.out.println("2. Doanh thu theo ngay");
            System.out.println("3. Doanh thu theo thang / nam");
            System.out.println("4. Doanh thu theo nam");
            System.out.println("5. Doanh thu theo nhan vien");
            System.out.println("6. Doanh thu theo khach hang");
            System.out.println("7. Top sach ban chay");
            System.out.println("8. Sach sap het hang");
            System.out.println("9. Top khach hang chi tieu nhieu nhat");
            System.out.println("0. Quay lai");
            chon = Main.docSoNguyen("Chon: ");

            HoaDon[] dsHD = danhSachHoaDon.getDs();
            int nHD = danhSachHoaDon.getSoLuong();
            Sach[] dsS = danhSachSach.getDs();
            int nS = danhSachSach.getSoLuong();
            KhachHang[] dsKH = danhSachKhachHang.getDs();
            int nKH = danhSachKhachHang.getSoLuong();

            switch (chon) {
                case 1:
                    thongKe.thongKeTongQuan(dsHD, nHD, dsS, nS);
                    break;
                case 2: {
                    LocalDate ngay = Main.docNgay("Nhap ngay can xem doanh thu");
                    System.out.println("Doanh thu ngay " + Main.dinhDangNgay(ngay) + ": "
                            + Main.dinhDangTien(thongKe.doanhThuTheoNgay(dsHD, nHD, ngay)));
                    break;
                }
                case 3: {
                    int thang = Main.docSoNguyen("Nhap thang (1-12): ");
                    int nam = Main.docSoNguyen("Nhap nam: ");
                    System.out.println("Doanh thu thang " + thang + "/" + nam + ": "
                            + Main.dinhDangTien(thongKe.doanhThuTheoThang(dsHD, nHD, thang, nam)));
                    break;
                }
                case 4: {
                    int nam = Main.docSoNguyen("Nhap nam: ");
                    System.out.println("Doanh thu nam " + nam + ": "
                            + Main.dinhDangTien(thongKe.doanhThuTheoNam(dsHD, nHD, nam)));
                    break;
                }
                case 5: {
                    String maNV = Main.docChuoi("Nhap ma nhan vien: ");
                    System.out.println("Doanh thu cua NV " + maNV + ": "
                            + Main.dinhDangTien(thongKe.doanhThuTheoNhanVien(dsHD, nHD, maNV)));
                    break;
                }
                case 6: {
                    String maKH = Main.docChuoi("Nhap ma khach hang: ");
                    System.out.println("Doanh thu tu KH " + maKH + ": "
                            + Main.dinhDangTien(thongKe.doanhThuTheoKhachHang(dsHD, nHD, maKH)));
                    break;
                }
                case 7: {
                    int top = Main.docSoNguyen("Nhap so luong top sach ban chay: ");
                    thongKe.inSachBanChay(dsHD, nHD, dsS, nS, top);
                    break;
                }
                case 8: {
                    int nguong = Main.docSoNguyen("Nhap nguong ton kho sap het: ");
                    thongKe.inSachSapHet(dsS, nS, nguong);
                    break;
                }
                case 9: {
                    int top = Main.docSoNguyen("Nhap so luong top khach hang: ");
                    thongKe.inTopKhachHangChiTieu(dsHD, nHD, dsKH, nKH, top);
                    break;
                }
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    private void quanLyFile() {
        System.out.println("\n--- DOC / GHI DU LIEU FILE ---");
        System.out.println("1. Doc du lieu tu file");
        System.out.println("2. Ghi du lieu ra file");
        System.out.println("0. Quay lai");
        int chon = Main.docSoNguyen("Chon: ");
        switch (chon) {
            case 1:
                docDuLieuTuFile();
                break;
            case 2:
                ghiDuLieuRaFile();
                break;
            case 0:
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
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
