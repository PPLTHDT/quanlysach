package cuahangsach;

public class ChiTietHoaDon extends ChiTietPhieu {
    public static final int NGUONG_CHIET_KHAU_1 = 10;
    public static final int NGUONG_CHIET_KHAU_2 = 50;
    public static final double TY_LE_CHIET_KHAU_1 = 0.05;
    public static final double TY_LE_CHIET_KHAU_2 = 0.10;

    private double thanhTien;
    private double tyLeChietKhau;

    public ChiTietHoaDon() {
        super();
    }

    public ChiTietHoaDon(String maCT, Sach sach, int soLuong) {
        this(maCT, sach, soLuong, sach != null ? sach.getGiaBan() : 0);
    }

    public ChiTietHoaDon(String maCT, Sach sach, int soLuong, double donGia) {
        super(maCT, soLuong, donGia);
        this.sach = sach;
        tinhThanhTien();
    }

    public static double tinhTyLeChietKhau(int soLuong) {
        if (soLuong >= NGUONG_CHIET_KHAU_2) {
            return TY_LE_CHIET_KHAU_2;
        }
        if (soLuong >= NGUONG_CHIET_KHAU_1) {
            return TY_LE_CHIET_KHAU_1;
        }
        return 0;
    }

    @Override
    public void setSach(Sach sach) {
        super.setSach(sach);
        tinhThanhTien();
    }

    @Override
    public void setSoLuong(int soLuong) {
        super.setSoLuong(soLuong);
        tinhThanhTien();
    }

    @Override
    public void setDonGia(double donGia) {
        super.setDonGia(donGia);
        tinhThanhTien();
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public double getTyLeChietKhau() {
        return tyLeChietKhau;
    }

    @Override
    public double tinhThanhTien() {
        double tienGoc = soLuong * donGia;
        tyLeChietKhau = tinhTyLeChietKhau(soLuong);
        thanhTien = tienGoc * (1 - tyLeChietKhau);
        return thanhTien;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Ma CT : " + maCT
                + "| Ten sach : " + (sach != null ? sach.getTenSach() : "(khong co)")
                + "| So luong : " + soLuong
                + "| Don gia : " + String.format("%,.0f", donGia)
                + "| Chiet khau : " + String.format("%.0f%%", tyLeChietKhau * 100)
                + "| Thanh tien : " + String.format("%,.0f", thanhTien));
    }

    public void xuat() {
        hienThiThongTin();
    }

    @Override
    public String dongFile() {
        return String.join("|", "CT", maCT,
                sach == null ? "" : sach.getMaSach(),
                String.valueOf(soLuong),
                String.valueOf(donGia));
    }
}
