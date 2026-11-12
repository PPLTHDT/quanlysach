import java.time.LocalDate;
import java.util.Scanner;

public class DanhSachPhieuNhap {
    private PhieuNhap[] dsPhieuNhap;
    private int soLuong;
    public static final int MAX = 100;

    public DanhSachPhieuNhap() {
        this.dsPhieuNhap = new PhieuNhap[MAX];
        this.soLuong = 0;
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
            System.out.println("Khong tim thay phieu nhap co ma: " + ma);
            return false;
        }

        Scanner sc = Main.sc;
        System.out.println("=== SUA THONG TIN PHIEU NHAP [" + ma + "] ===");
        System.out.print("Nhap ten nha xuat ban moi (de trong de giu nguyen): ");
        String tenNXB = sc.nextLine().trim();
        if (!tenNXB.isEmpty()) {
            if (pn.getNhaXuatBan() == null) {
                pn.setNhaXuatBan(new NhaXuatBan("", tenNXB, ""));
            } else {
                pn.getNhaXuatBan().setTenNXB(tenNXB);
            }
        }
        System.out.println("Sua thong tin phieu nhap thanh cong!");
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
        Scanner sc = Main.sc;
        System.out.println("\n--- NHAP THONG TIN PHIEU NHAP MOI ---");
        System.out.print("Nhap ma phieu nhap (vd: PN01): ");
        String maPN = sc.nextLine().trim();

        if (timKiem(maPN) != null) {
            System.out.println("Loi: Ma phieu nhap nay da ton tai!");
            return;
        }

        System.out.print("Nhap ten nha xuat ban: ");
        String tenNXB = sc.nextLine().trim();
        NhaXuatBan nxb = new NhaXuatBan("NXB_" + maPN, tenNXB, "");

        PhieuNhap pn = new PhieuNhap(maPN, LocalDate.now(), nxb);

        System.out.print("Nhap so luong loai sach muon nhap: ");
        int n = 0;
        try {
            n = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            n = 0;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("\n-> Nhap chi tiet thu " + (i + 1) + ":");
            System.out.print("  Ma chi tiet (vd: CT01): ");
            String maCT = sc.nextLine().trim();

            System.out.print("  Ma sach: ");
            String maSach = sc.nextLine().trim();

            System.out.print("  Ten sach: ");
            String tenSach = sc.nextLine().trim();

            System.out.print("  So luong nhap: ");
            int sl = 0;
            try {
                sl = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                sl = 1;
            }

            System.out.print("  Don gia nhap (VND): ");
            double donGia = 0.0;
            try {
                donGia = Double.parseDouble(sc.nextLine().trim());
            } catch (Exception e) {
                donGia = 0.0;
            }

            Sach sach = new Sach(maSach, tenSach, 2024, donGia * 1.2, 0);
            ChiTietPhieuNhap ct = new ChiTietPhieuNhap(maCT, sach, sl, donGia);
            pn.themChiTiet(ct);
        }

        if (them(pn)) {
            System.out.println("Them phieu nhap thanh cong!");
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
        double maxTien = 0.0;
        String maMax = "Khong co";

        for (int i = 0; i < soLuong; i++) {
            double tien = dsPhieuNhap[i].getTongTien();
            tongChiPhi += tien;
            if (tien > maxTien) {
                maxTien = tien;
                maMax = dsPhieuNhap[i].getMaPhieuNhap();
            }
        }

        System.out.printf("Tong chi phi da chi cho nhap sach : %,15.0f VND%n", tongChiPhi);
        if (soLuong > 0) {
            System.out.printf("Phieu nhap co gia tri lon nhat     : %s (%,.0f VND)%n", maMax, maxTien);
        }
        System.out.println("========================================================");
    }

    public void napDuLieu(PhieuNhap[] ds) {
        if (ds == null) return;
        soLuong = 0;
        for (int i = 0; i < ds.length && i < MAX; i++) {
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
}
