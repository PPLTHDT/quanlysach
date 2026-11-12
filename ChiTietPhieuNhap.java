public class ChiTietPhieuNhap {
    private String maCT;
    private Sach sach;
    private int soLuongNhap;
    private double donGiaNhap;

    public ChiTietPhieuNhap() {
        this.maCT = "";
        this.sach = null;
        this.soLuongNhap = 0;
        this.donGiaNhap = 0.0;
    }

    public ChiTietPhieuNhap(String maCT, Sach sach, int soLuongNhap, double donGiaNhap) {
        this.maCT = maCT;
        this.sach = sach;
        this.soLuongNhap = soLuongNhap;
        this.donGiaNhap = donGiaNhap;
    }

    public double tinhThanhTien() {
        return donGiaNhap * soLuongNhap;
    }

    public void capNhatSoLuong() {
        if (sach != null && soLuongNhap > 0) {
            sach.tangSoLuong(soLuongNhap);
        }
    }

    // Getter & Setter
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

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = (soLuongNhap >= 0) ? soLuongNhap : 0;
    }

    public double getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(double donGiaNhap) {
        this.donGiaNhap = (donGiaNhap >= 0) ? donGiaNhap : 0.0;
    }
}
