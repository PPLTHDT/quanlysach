package cuahangsach;

import java.util.Arrays;

public class DanhSachTaiKhoan {
    private TaiKhoan[] ds;
    private int soLuong;

    public DanhSachTaiKhoan() {
        ds = new TaiKhoan[10];
        soLuong = 0;
    }

    public TaiKhoan[] getDs() {
        return ds;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void napDuLieu(TaiKhoan[] dl) {
        ds = new TaiKhoan[Math.max(10, dl == null ? 0 : dl.length)];
        soLuong = 0;
        if (dl == null) {
            return;
        }
        for (TaiKhoan t : dl) {
            if (t != null) {
                ds[soLuong++] = t;
            }
        }
    }

    public TaiKhoan timTheoUsername(String username) {
        if (username == null) return null;
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getUsername().equalsIgnoreCase(username.trim())) {
                return ds[i];
            }
        }
        return null;
    }

    public TaiKhoan timTheoMaNguoiDung(String ma) {
        if (ma == null) return null;
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getMaNguoiDung() != null && ds[i].getMaNguoiDung().equalsIgnoreCase(ma.trim())) {
                return ds[i];
            }
        }
        return null;
    }

    public boolean coTaiKhoanVaiTro(VaiTro vt) {
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getVaiTro() == vt) return true;
        }
        return false;
    }

    public boolean them(TaiKhoan t) {
        if (t == null || timTheoUsername(t.getUsername()) != null || timTheoMaNguoiDung(t.getMaNguoiDung()) != null) {
            return false;
        }
        if (soLuong == ds.length) {
            ds = Arrays.copyOf(ds, ds.length * 2);
        }
        ds[soLuong++] = t;
        return true;
    }

    public boolean xoa(String username) {
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getUsername().equalsIgnoreCase(username.trim())) {
                for (int j = i; j < soLuong - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                ds[--soLuong] = null;
                return true;
            }
        }
        return false;
    }

    // Tra ve tai khoan neu dung username + mat khau, nguoc lai tra ve null
    public TaiKhoan dangNhap(String username, String matKhau) {
        TaiKhoan t = timTheoUsername(username);
        if (t != null && t.kiemTraMatKhau(matKhau)) {
            return t;
        }
        return null;
    }

    public void xuat() {
        System.out.println("--- DANH SACH TAI KHOAN ---");
        if (soLuong == 0) {
            System.out.println("(Chua co tai khoan)");
            return;
        }
        for (int i = 0; i < soLuong; i++) {
            System.out.println((i + 1) + ". " + ds[i].getUsername() + " | " + ds[i].getVaiTro().getTen()
                    + " | Ma: " + ds[i].getMaNguoiDung());
        }
    }
}
