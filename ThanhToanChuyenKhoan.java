public class ThanhToanChuyenKhoan implements ThanhToan {
    private String soTaiKhoan;
    private String nganHang;

    public ThanhToanChuyenKhoan() {
        this.soTaiKhoan = "";
        this.nganHang = "";
    }

    public ThanhToanChuyenKhoan(String soTaiKhoan, String nganHang) {
        this.soTaiKhoan = soTaiKhoan;
        this.nganHang = nganHang;
    }

    @Override
    public boolean thanhToan(double soTien) {
        System.out.println("Dang xu ly chuyen khoan " + soTien + " VND...");
        System.out.println("Tai khoan: " + soTaiKhoan + " - Ngan hang: " + nganHang);
        System.out.println("Chuyen khoan thanh cong (mo phong)!");
        return true;
    }

    public String getSoTaiKhoan() { return soTaiKhoan; }
    public void setSoTaiKhoan(String soTaiKhoan) { this.soTaiKhoan = soTaiKhoan; }

    public String getNganHang() { return nganHang; }
    public void setNganHang(String nganHang) { this.nganHang = nganHang; }
}