public class ThanhToanChuyenKhoan implements ThanhToan {
    private String soTaiKhoan;
    private String nganHang;
    private String maGiaoDich;

    public ThanhToanChuyenKhoan(String soTaiKhoan, String nganHang, String maGiaoDich) {
        this.soTaiKhoan = soTaiKhoan;
        this.nganHang = nganHang;
        this.maGiaoDich = maGiaoDich;
    }

    // Gia lap: co so tai khoan va so tien hop le thi coi nhu thanh cong
    @Override
    public boolean thanhToan(double soTien) {
        return soTien >= 0 && !soTaiKhoan.equals("");
    }

    @Override
    public String getPhuongThuc() {
        return "Chuyen khoan";
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }

    public String getNganHang() {
        return nganHang;
    }

    public void setNganHang(String nganHang) {
        this.nganHang = nganHang;
    }

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }
}
