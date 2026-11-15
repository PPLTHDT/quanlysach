import java.util.Arrays;
public class DanhSachKhachHang extends {
    protected String layMa(Object o) {
        return ((KhachHang) o).getMaKH();
    }

    public KhachHang timKiem(String ma) {
        return (KhachHang) timKiemMa(ma);
    }

    public KhachHang timKiemTheoSdt(String sdt) {
        for (int i = 0; i < soLuong; i++) {
            if (((KhachHang) ds[i]).getSdt().equals(sdt)) {
                return (KhachHang) ds[i];
            }
        }
        return null;
    }

    public KhachHang[] timKiemTheoTen(String ten) {
        KhachHang[] kq = new KhachHang[soLuong];
        int n = 0;
        String tk = ten.toLowerCase().trim();
        for (int i = 0; i < soLuong; i++) {
            if (((KhachHang) ds[i]).getHoTen().toLowerCase().contains(tk)) {
                kq[n] = (KhachHang) ds[i];
                n++;
            }
        }
        return Arrays.copyOf(kq, n);
    }

    public Object[] timKiemTongHop(String tuKhoa, int kieu) {
        tuKhoa = tuKhoa.trim();
        switch (kieu) {
            case 1: {
                KhachHang k = timKiem(tuKhoa);
                if (k != null) {
                    return new Object[] { k };
                }
                break;
            }
            case 2:
                return timKiemTheoTen(tuKhoa);
            case 3: {
                KhachHang k = timKiemTheoSdt(tuKhoa);
                if (k != null) {
                    return new Object[] { k };
                }
                break;
            }
        }
        return new Object[0];
    }

    public void timKiemMenu() {
        int kieu = nhapSoNguyen("Tim theo: 1. MaKH  2. Ho ten  3. SDT : ");
        String tuKhoa;
        switch (kieu) {
            case 1:
            case 2:
            case 3:
                System.out.print("Nhap tu khoa: ");
                tuKhoa = Main.sc.nextLine();
                break;
            default:
                System.out.println("Lua chon khong hop le. Khong co du lieu de tim kiem.");
                return;
        }
        Object[] kq = timKiemTongHop(tuKhoa, kieu);
        if (kq.length == 0) {
            System.out.println("Khong tim thay khach hang nao.");
        } else {
            System.out.println("Tim thay " + kq.length + " khach hang:");
            for (int i = 0; i < kq.length; i++) {
                ((KhachHang) kq[i]).hienThi();
            }
        }
    }

    public int nhapSoNguyen(String tb) {
        while (true) {
            try {
                System.out.print(tb);
                return Integer.parseInt(Main.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nhap lai so nguyen.");
            }
        }
    }

    public boolean them(KhachHang k) {
        return them((Object) k);
    }
}
