package cuahangsach;

import java.time.LocalDate;
import java.util.Arrays;

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
    private DanhSachTaiKhoan danhSachTaiKhoan;
    private TaiKhoan taiKhoanHienTai;

    public CuaHangSach() {
        this.danhSachTaiKhoan = new DanhSachTaiKhoan();
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
        danhSachTaiKhoan.napDuLieu(docGhiFile.docFileTaiKhoan());
        taoTaiKhoanMacDinhChoQuanLy();
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
        luuTaiKhoan();
        System.out.println("Da ghi du lieu ra file thanh cong!");
    }

    // ================= DANG NHAP / DANG KY / PHAN QUYEN =================

    private static final String MAT_KHAU_MAC_DINH = "123456";

    private void taoTaiKhoanMacDinhChoQuanLy() {
        if (danhSachTaiKhoan.coTaiKhoanVaiTro(VaiTro.QUAN_LY)) {
            return;
        }
        QuanLy[] dsQL = danhSachNhanVien.getDsQuanLy();
        int n = danhSachNhanVien.getSoLuongQL();
        int tao = 0;
        for (int i = 0; i < n && i < dsQL.length; i++) {
            if (dsQL[i] != null && dsQL[i].getMaQL() != null) {
                String ma = dsQL[i].getMaQL();
                if (danhSachTaiKhoan.them(new TaiKhoan(ma.toLowerCase(), MAT_KHAU_MAC_DINH, VaiTro.QUAN_LY, ma))) {
                    tao++;
                }
            }
        }
        if (tao > 0) {
            System.out.println("Da tao " + tao + " tai khoan quan ly mac dinh (username = ma quan ly viet thuong, mat khau = "
                    + MAT_KHAU_MAC_DINH + "). Hay doi mat khau sau khi dang nhap!");
        }
    }

    private void luuTaiKhoan() {
        docGhiFile.ghiFileTaiKhoan(danhSachTaiKhoan.getDs(), danhSachTaiKhoan.getSoLuong());
    }

    private QuanLy timQuanLyTheoMa(String maQL) {
        if (maQL == null || maQL.isEmpty()) {
            return null;
        }
        QuanLy[] ds = danhSachNhanVien.getDsQuanLy();
        int n = danhSachNhanVien.getSoLuongQL();
        for (int i = 0; i < n && i < ds.length; i++) {
            if (ds[i] != null && ds[i].getMaQL() != null && ds[i].getMaQL().equalsIgnoreCase(maQL)) {
                return ds[i];
            }
        }
        return null;
    }

    private String tenNguoiDangNhap() {
        if (taiKhoanHienTai == null) {
            return "";
        }
        String ma = taiKhoanHienTai.getMaNguoiDung();
        NhanVien nv = timNhanVienTheoMa(ma);
        if (nv != null && nv.getHoTen() != null) {
            return nv.getHoTen();
        }
        QuanLy ql = timQuanLyTheoMa(ma);
        if (ql != null && ql.getHoTen() != null) {
            return ql.getHoTen();
        }
        return taiKhoanHienTai.getUsername();
    }

    // Man hinh dau tien: Dang nhap / Dang ky / Thoat
    public void chay() {
        int chon;
        do {
            System.out.println("\n=================================================");
            System.out.println("       HE THONG QUAN LY CUA HANG SACH            ");
            System.out.println("=================================================");
            System.out.println(" 1. Dang nhap");
            System.out.println(" 2. Dang ky (danh cho nhan vien kho / ke toan)");
            System.out.println(" 0. Thoat chuong trinh");
            System.out.println("=================================================");
            chon = Main.docSoNguyen("Chon: ");
            switch (chon) {
                case 1:
            if (dangNhap()) {
            xuLy();
    }
            break;
                case 2:
                    dangKy();
                    break;
                case 0:
                    System.out.println("Tam biet quy khach!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    private boolean dangNhap() {
        System.out.println("\n--- DANG NHAP ---");
        for (int lan = 1; lan <= 3; lan++) {
            String user = Main.docChuoi("Ten dang nhap: ");
            String mk = Main.docChuoi("Mat khau: ");
            TaiKhoan t = danhSachTaiKhoan.dangNhap(user, mk);
            if (t != null) {
                taiKhoanHienTai = t;
                return true;
            }
            System.out.println("Sai ten dang nhap hoac mat khau! (" + lan + "/3)");
        }
        System.out.println("Ban da nhap sai 3 lan. Quay lai man hinh dau.");
        return false;
    }

    private String docMatKhauMoi() {
        String mk = Main.docChuoi("Mat khau moi (toi thieu 6 ky tu): ");
        if (mk.length() < 6) {
            System.out.println("Mat khau qua ngan!");
            return null;
        }
        String lai = Main.docChuoi("Nhap lai mat khau: ");
        if (!mk.equals(lai)) {
            System.out.println("Hai lan nhap mat khau khong khop!");
            return null;
        }
        return mk;
    }

    private void dangKy() {
        System.out.println("\n--- DANG KY TAI KHOAN ---");
        String ma = Main.docChuoi("Nhap ma nhan vien cua ban (vd: NV02): ");
        NhanVien nv = timNhanVienTheoMa(ma);
        if (nv == null) {
            System.out.println("Khong tim thay nhan vien co ma nay! (Quan ly vui long lien he de duoc cap tai khoan)");
            return;
        }
        String cccd = Main.docChuoi("Nhap CCCD de xac minh: ");
        if (nv.getCCCD() == null || !nv.getCCCD().equals(cccd)) {
            System.out.println("CCCD khong khop voi ho so nhan vien!");
            return;
        }
        VaiTro vt = PhanQuyen.vaiTroTuChucVu(nv.getChucVu());
        if (vt == null) {
            System.out.println("Chuc vu \"" + nv.getChucVu() + "\" khong duoc cap tai khoan dang nhap.");
            return;
        }
        if (danhSachTaiKhoan.timTheoMaNguoiDung(nv.getMaNV()) != null) {
            System.out.println("Nhan vien nay da co tai khoan!");
            return;
        }
        System.out.println("Xin chao " + nv.getHoTen() + " - vai tro: " + vt.getTen());
        String user = Main.docChuoi("Ten dang nhap (3-20 ky tu: chu, so, dau . hoac _): ");
        if (!user.matches("[A-Za-z0-9._]{3,20}")) {
            System.out.println("Ten dang nhap khong hop le!");
            return;
        }
        if (danhSachTaiKhoan.timTheoUsername(user) != null) {
            System.out.println("Ten dang nhap da ton tai!");
            return;
        }
        String mk = docMatKhauMoi();
        if (mk == null) {
            return;
        }
        if (danhSachTaiKhoan.them(new TaiKhoan(user, mk, vt, nv.getMaNV()))) {
            luuTaiKhoan();
            System.out.println("Dang ky thanh cong! Hay dang nhap bang tai khoan vua tao.");
        } else {
            System.out.println("Dang ky that bai!");
        }
    }

    private void doiMatKhau() {
        System.out.println("\n--- DOI MAT KHAU ---");
        String cu = Main.docChuoi("Mat khau hien tai: ");
        if (!taiKhoanHienTai.kiemTraMatKhau(cu)) {
            System.out.println("Mat khau hien tai khong dung!");
            return;
        }
        String moi = docMatKhauMoi();
        if (moi == null) {
            return;
        }
        taiKhoanHienTai.datMatKhau(moi);
        luuTaiKhoan();
        System.out.println("Doi mat khau thanh cong!");
    }

    private void quanLyTaiKhoan() {
        int chon;
        do {
            System.out.println("\n--- QUAN LY TAI KHOAN ---");
            System.out.println("1. Xem danh sach tai khoan");
            System.out.println("2. Dat lai mat khau ");
            System.out.println("3. Xoa tai khoan");
            System.out.println("4. Tao tai khoan cho quan ly");
            System.out.println("0. Quay lai");
            chon = Main.docSoNguyen("Chon: ");
            switch (chon) {
                case 1:
                    danhSachTaiKhoan.xuat();
                    break;
                case 2: {
                    TaiKhoan t = danhSachTaiKhoan.timTheoUsername(Main.docChuoi("Nhap username: "));
                    if (t == null) {
                        System.out.println("Khong tim thay tai khoan!");
                    } else {
                        t.datMatKhau(MAT_KHAU_MAC_DINH);
                        luuTaiKhoan();
                        System.out.println("Da dat lai mat khau cho " + t.getUsername() + ".");
                    }
                    break;
                }
                case 3: {
                    String user = Main.docChuoi("Nhap username can xoa: ");
                    if (taiKhoanHienTai.getUsername().equalsIgnoreCase(user)) {
                        System.out.println("Khong the xoa tai khoan dang dang nhap!");
                    } else if (danhSachTaiKhoan.xoa(user)) {
                        luuTaiKhoan();
                        System.out.println("Da xoa tai khoan.");
                    } else {
                        System.out.println("Khong tim thay tai khoan!");
                    }
                    break;
                }
                case 4: {
                    String ma = Main.docChuoi("Nhap ma quan ly (vd: QL03): ");
                    QuanLy ql = timQuanLyTheoMa(ma);
                    if (ql == null) {
                        System.out.println("Khong tim thay quan ly co ma nay!");
                    } else if (danhSachTaiKhoan.them(new TaiKhoan(ql.getMaQL().toLowerCase(), MAT_KHAU_MAC_DINH,
                            VaiTro.QUAN_LY, ql.getMaQL()))) {
                        luuTaiKhoan();
                        System.out.println("Da tao tai khoan " + ql.getMaQL().toLowerCase()
                                + " (mat khau mac dinh " + MAT_KHAU_MAC_DINH + ").");
                    } else {
                        System.out.println("Quan ly nay da co tai khoan!");
                    }
                    break;
                }
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    // Danh sach chuc nang ma tai khoan hien tai duoc phep vao (theo thu tu hien thi)
    private int[] chucNangDuocPhep() {
        VaiTro vt = taiKhoanHienTai.getVaiTro();
        int[] tam = new int[PhanQuyen.TAT_CA.length];
        int n = 0;
        for (int cn : PhanQuyen.TAT_CA) {
            if (PhanQuyen.coQuyen(vt, cn)) {
                tam[n++] = cn;
            }
        }
        return Arrays.copyOf(tam, n);
    }

    private String tenChucNang(int cn) {
        String ten;
        switch (cn) {
            case PhanQuyen.SACH: ten = "Quan ly Kho Sach"; break;
            case PhanQuyen.THE_LOAI: ten = "Quan ly The Loai"; break;
            case PhanQuyen.TAC_GIA: ten = "Quan ly Tac Gia"; break;
            case PhanQuyen.NXB: ten = "Quan ly Nha Xuat Ban"; break;
            case PhanQuyen.KHACH_HANG: ten = "Quan ly Khach Hang"; break;
            case PhanQuyen.NHAN_SU: ten = "Quan ly Nhan Vien & Quan Ly"; break;
            case PhanQuyen.HOA_DON: ten = "Quan ly Hoa Don & Lap Hoa Don Ban Hang"; break;
            case PhanQuyen.PHIEU_NHAP: ten = "Quan ly Phieu Nhap & Lap Phieu Nhap Hang"; break;
            case PhanQuyen.KHUYEN_MAI: ten = "Quan ly Khuyen Mai"; break;
            case PhanQuyen.THONG_KE: return "Thong ke bao cao";
            case PhanQuyen.FILE: return "Doc / Ghi du lieu file";
            case PhanQuyen.DOI_MAT_KHAU: return "Doi mat khau";
            case PhanQuyen.QUAN_LY_TAI_KHOAN: return "Quan ly tai khoan";
            default: return "";
        }
        if (PhanQuyen.layQuyen(taiKhoanHienTai.getVaiTro(), cn) == PhanQuyen.Quyen.XEM) {
            ten += " (chi xem)";
        }
        return ten;
    }

    public void hienThiMenu(int[] cho) {
        System.out.println("\n=================================================");
        System.out.println("       HE THONG QUAN LY CUA HANG SACH            ");
        System.out.println(" Xin chao: " + tenNguoiDangNhap() + " - " + taiKhoanHienTai.getVaiTro().getTen());
        System.out.println("=================================================");
        for (int i = 0; i < cho.length; i++) {
            System.out.println(" " + (i + 1) + ". " + tenChucNang(cho[i]));
        }
        System.out.println(" 0. Dang xuat");
        System.out.println("=================================================");
    }

    // Menu chinh sau khi dang nhap: chi hien cac chuc nang cua vai tro hien tai
    public void xuLy() {
        int[] cho = chucNangDuocPhep();
        int chon;
        do {
            hienThiMenu(cho);
            chon = Main.docSoNguyen("Moi ban chon chuc nang (0 - " + cho.length + "): ");
            if (chon == 0) {
                ghiDuLieuRaFile();
                System.out.println("Da dang xuat.");
                taiKhoanHienTai = null;
            } else if (chon >= 1 && chon <= cho.length) {
                thucHienChucNang(cho[chon - 1]);
            } else {
                System.out.println("Chuc nang khong hop le, vui long chon lai!");
            }
        } while (chon != 0);
    }

    private void thucHienChucNang(int cn) {
        PhanQuyen.Quyen q = PhanQuyen.layQuyen(taiKhoanHienTai.getVaiTro(), cn);
        if (q == PhanQuyen.Quyen.KHONG) {
            System.out.println("Ban khong co quyen su dung chuc nang nay!");
            return;
        }
        boolean chiXem = (q == PhanQuyen.Quyen.XEM);
        switch (cn) {
            case PhanQuyen.SACH:
                if (chiXem) {
                    menuChiXem("KHO SACH", danhSachSach::xuat, danhSachSach::timKiem, danhSachSach::thongKe);
                } else {
                    danhSachSach.menu();
                }
                break;
            case PhanQuyen.THE_LOAI:
                if (chiXem) {
                    menuChiXem("THE LOAI", danhSachTheLoai::xuat, danhSachTheLoai::timKiem, danhSachTheLoai::thongKe);
                } else {
                    quanLyTheLoai();
                }
                break;
            case PhanQuyen.TAC_GIA:
                if (chiXem) {
                    menuChiXem("TAC GIA", danhSachTacGia::xuat, danhSachTacGia::timKiem, danhSachTacGia::thongKe);
                } else {
                    danhSachTacGia.menu();
                }
                break;
            case PhanQuyen.NXB:
                if (chiXem) {
                    menuChiXem("NHA XUAT BAN", danhSachNhaXuatBan::xuat, danhSachNhaXuatBan::timKiem,
                            danhSachNhaXuatBan::thongKe);
                } else {
                    danhSachNhaXuatBan.menu();
                }
                break;
            case PhanQuyen.KHACH_HANG:
                if (chiXem) {
                    menuChiXem("KHACH HANG", danhSachKhachHang::xuat, danhSachKhachHang::timKiem,
                            danhSachKhachHang::thongKe);
                } else {
                    danhSachKhachHang.menu();
                }
                break;
            case PhanQuyen.NHAN_SU:
                if (chiXem) {
                    menuChiXem("NHAN VIEN & QUAN LY", () -> {
                        danhSachNhanVien.xuatDSNhanVien();
                        danhSachNhanVien.xuatDSQuanLy();
                    }, danhSachNhanVien::timKiemNV, danhSachNhanVien::thongKeNhanSu);
                } else {
                    danhSachNhanVien.menu();
                }
                break;
            case PhanQuyen.HOA_DON:
                if (chiXem) {
                    menuChiXem("HOA DON", danhSachHoaDon::xuat, danhSachHoaDon::menuTimKiem,
                            danhSachHoaDon::thongKe);
                } else {
                    quanLyHoaDon();
                }
                break;
            case PhanQuyen.PHIEU_NHAP:
                danhSachPhieuNhap.setDanhSachLienKet(danhSachNhaXuatBan, danhSachSach);
                if (chiXem) {
                    menuChiXem("PHIEU NHAP", danhSachPhieuNhap::xuat, danhSachPhieuNhap::menuTimKiem,
                            danhSachPhieuNhap::thongKe);
                } else {
                    danhSachPhieuNhap.menu();
                }
                break;
            case PhanQuyen.KHUYEN_MAI:
                if (chiXem) {
                    menuChiXem("KHUYEN MAI", danhSachKhuyenMai::xuat, danhSachKhuyenMai::menuTimKiem,
                            danhSachKhuyenMai::thongKe);
                } else {
                    danhSachKhuyenMai.menu();
                }
                break;
            case PhanQuyen.THONG_KE:
                quanLyThongKe();
                break;
            case PhanQuyen.FILE:
                quanLyFile();
                break;
            case PhanQuyen.DOI_MAT_KHAU:
                doiMatKhau();
                break;
            case PhanQuyen.QUAN_LY_TAI_KHOAN:
                quanLyTaiKhoan();
                break;
            default:
                System.out.println("Chuc nang khong hop le!");
        }
    }

    // Menu chi cho xem / tim kiem / thong ke, khong co them - sua - xoa
    private void menuChiXem(String tieuDe, Runnable xuat, Runnable timKiem, Runnable thongKe) {
        int chon;
        do {
            System.out.println("\n--- " + tieuDe + " (CHI XEM) ---");
            System.out.println("1. Xem danh sach");
            if (timKiem != null) {
                System.out.println("2. Tim kiem");
            }
            if (thongKe != null) {
                System.out.println("3. Thong ke");
            }
            System.out.println("0. Quay lai");
            chon = Main.docSoNguyen("Chon: ");
            if (chon == 1) {
                xuat.run();
            } else if (chon == 2 && timKiem != null) {
                timKiem.run();
            } else if (chon == 3 && thongKe != null) {
                thongKe.run();
            } else if (chon != 0) {
                System.out.println("Lua chon khong hop le!");
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
        if (taiKhoanHienTai != null && taiKhoanHienTai.getVaiTro() == VaiTro.KE_TOAN) {
            // Ke toan dang dang nhap: tu dong ghi nhan la nhan vien lap hoa don
            nv = timNhanVienTheoMa(taiKhoanHienTai.getMaNguoiDung());
            if (nv != null) {
                System.out.println("Nhan vien lap: " + nv.getHoTen() + " (" + nv.getMaNV() + ")");
            }
        } else {
            String maNV = Main.docChuoi("Nhap ma nhan vien lap (Enter de bo qua): ");
            if (!maNV.isEmpty()) {
                nv = timNhanVienTheoMa(maNV);
                if (nv == null) {
                    System.out.println("Khong tim thay nhan vien co ma: " + maNV);
                    return;
                }
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
        if (soMatHang <= 0) {
            System.out.println("So loai sach mua phai lon hon 0! Huy lap hoa don.");
            return;
        }
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
            if (sach.getSoLuongTon() <= 0) {
                System.out.println("  Loi: Sach nay da het hang trong kho!");
                continue;
            }
            int soLuongMua = Main.docSoNguyen("  So luong mua: ");
            if (soLuongMua <= 0) {
                System.out.println("  Loi: So luong mua phai lon hon 0!");
                continue;
            }
            if (soLuongMua > sach.getSoLuongTon()) {
                System.out.println("  Loi: So luong mua (" + soLuongMua
                        + ") vuot qua so luong ton kho (" + sach.getSoLuongTon() + ")!");
                continue;
            }
            if (maCT.isEmpty()) {
                maCT = String.format("CTHD%02d", i + 1);
            }
            try {
                ChiTietHoaDon ct = new ChiTietHoaDon(maCT, sach, soLuongMua);
                if (!hd.themChiTiet(ct)) {
                    System.out.println("  Loi: Khong the them chi tiet (so luong mua vuot qua ton kho)!");
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
            for (ChiTietPhieu ct : hd.getDsChiTiet()) {
                if (ct != null && ct.getSach() != null) {
                    ct.getSach().tangSoLuong(ct.getSoLuong());
                }
            }
            System.out.println("Thanh toan that bai (so tien khach dua khong du hoac thong tin khong hop le)!");
            return;
        }

        if (danhSachHoaDon.them(hd)) {
            if (kh != null) {
                kh.tangDiemTichLuy((int) (canTra / 10000));
            }
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
            String[] tenMuc = {"", "Thong ke tong quan (Doanh thu, Ton kho, Ban chay, Sap het)",
                    "Doanh thu theo ngay", "Doanh thu theo thang / nam", "Doanh thu theo nam",
                    "Doanh thu theo nhan vien", "Doanh thu theo khach hang", "Top sach ban chay",
                    "Sach sap het hang", "Top khach hang chi tieu nhieu nhat", "Thong ke ton kho sach"};
            VaiTro vaiTro = taiKhoanHienTai == null ? null : taiKhoanHienTai.getVaiTro();
            for (int m = 1; m < tenMuc.length; m++) {
                if (PhanQuyen.duocXemThongKe(vaiTro, m)) {
                    System.out.println(m + ". " + tenMuc[m]);
                }
            }
            System.out.println("0. Quay lai");
            chon = Main.docSoNguyen("Chon: ");
            if (chon != 0 && !PhanQuyen.duocXemThongKe(vaiTro, chon)) {
                System.out.println("Lua chon khong hop le!");
                continue;
            }

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
                case 10:
                    danhSachSach.thongKe();
                    break;
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
