package CuaHangSach;

public class ChiTietPhieuNhap extends ChiTietPhieu {
    public ChiTietPhieuNhap() {
        super();
    }

    public ChiTietPhieuNhap(Sach sach, int soLuongNhap, double donGiaNhap) {
        this(taoMaCT("CTPN"), sach, soLuongNhap, donGiaNhap);
    }

    public ChiTietPhieuNhap(String maCT, Sach sach, int soLuongNhap, double donGiaNhap) {
        super(maCT, soLuongNhap, donGiaNhap);
        this.sach = sach;
    }

    public int getSoLuongNhap() {
        return soLuong;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        if (soLuongNhap <= 0) {
            throw new IllegalArgumentException("So luong nhap phai lon hon 0");
        }
        this.soLuong = soLuongNhap;
    }

    public double getDonGiaNhap() {
        return donGia;
    }

    public void setDonGiaNhap(double donGiaNhap) {
        if (donGiaNhap <= 0) {
            throw new IllegalArgumentException("Don gia nhap phai lon hon 0");
        }
        this.donGia = donGiaNhap;
    }

    @Override
    public double tinhThanhTien() {
        return donGia * soLuong;
    }

    public void capNhatSoLuong() {
        if (sach != null && soLuong > 0) {
            sach.tangSoLuong(soLuong);
        }
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Ma CT : " + maCT
                + "| Ten sach : " + (sach != null ? sach.getTenSach() : "(khong co)")
                + "| So luong nhap : " + soLuong
                + "| Don gia nhap : " + String.format("%,.0f", donGia)
                + "| Thanh tien : " + String.format("%,.0f", tinhThanhTien()));
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
