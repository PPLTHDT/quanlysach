import java.util.Scanner;

public class DanhSachHoaDon {
    private HoaDon[] dsHoaDon;
    private int soLuong;
    private static final int MAX = 100;

    public DanhSachHoaDon() {
        dsHoaDon = new HoaDon[MAX];
        soLuong = 0;
    }

    public void nhap(Scanner sc) {
        System.out.print("Nhap so luong hoa don: ");
        soLuong = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < soLuong; i++) {
            System.out.println("Nhap thong tin hoa don thu " + (i + 1) + ":");
            HoaDon hoaDon = new HoaDon();
            hoaDon.nhap(sc);
            dsHoaDon[i] = hoaDon;
        }
    }

    public void xuat() {
        System.out.println("Danh sach hoa don:");
        for (int i = 0; i < soLuong; i++) {
            System.out.println("Hoa don thu " + (i + 1) + ":");
            dsHoaDon[i].xuat();
        }
    }

    public boolean them(HoaDon hoaDon) {
        if (soLuong < MAX) {
            dsHoaDon[soLuong] = hoaDon;
            soLuong++;
            return true;
        } else {
            System.out.println("Danh sach hoa don da day. Khong the them hoa don moi.");
            return false;
        }
    }

    public boolean xoaTheoMa(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (dsHoaDon[i].getMaHD().equals(ma)) {
                for (int j = i; j < soLuong - 1; j++) {
                    dsHoaDon[j] = dsHoaDon[j + 1];
                }
                dsHoaDon[soLuong - 1] = null;
                soLuong--;
                return true;
            }
        }
        System.out.println("Khong tim thay hoa don voi ma: " + ma);
        return false;
    }

    public boolean suaTheoMa(String ma, HoaDon hoaDonMoi) {
        for (int i = 0; i < soLuong; i++) {
            if (dsHoaDon[i].getMaHD().equals(ma)) {
                dsHoaDon[i] = hoaDonMoi;
                return true;
            }
        }
        System.out.println("Khong tim thay hoa don voi ma: " + ma);
        return false;
    }

    public HoaDon timKiem(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (dsHoaDon[i].getMaHD().equals(ma)) {
                return dsHoaDon[i];
            }
        }
        return null;
    }

    public void thongKe() {
        System.out.println("Thong ke danh sach hoa don");
        System.out.println("Tong so hoa don: " + soLuong);
        double tongDoanhThu = 0;
        for (int i = 0; i < soLuong; i++) {
            tongDoanhThu += dsHoaDon[i].getTongTien();
        }
        System.out.println("Tong doanh thu: " + tongDoanhThu);
    }

    public HoaDon[] getDsHoaDon() {
        HoaDon[] result = new HoaDon[soLuong];
        for (int i = 0; i < soLuong; i++) {
            result[i] = dsHoaDon[i];
        }
        return result;
    }

    public int getSoLuong() { return soLuong; }

    public void napDuLieu(HoaDon[] ds) {
        if (ds == null) return;
        soLuong = 0;
        for (HoaDon hd : ds) {
            if (hd != null && soLuong < MAX) {
                dsHoaDon[soLuong] = hd;
                soLuong++;
            }
        }
    }
}