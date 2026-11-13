public class ChiTietHoaDon {
    private String maCT;
    private Sach sach;
    private int soLuong;
    private double donGia;

    public ChiTietHoaDon() {
        this.maCT = "";
        this.sach = null;
        this.soLuong = 0;
        this.donGia = 0;
    }

    // Don gia lay tu gia ban hien tai cua sach
    public ChiTietHoaDon(String maCT, Sach sach, int soLuong) {
        this.maCT = maCT;
        this.sach = sach;
        this.soLuong = soLuong;
        if (sach != null) {
            this.donGia = sach.getGiaBan();
        } else {
            this.donGia = 0;
        }
    }

    public double tinhThanhTien() {
        return soLuong * donGia;
    }

    public String getMaCT() {
        return maCT;
    }

    public void setMaCT(String maCT) {
        this.maCT = maCT;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
