public class ThanhToanTienMat implements ThanhToan {
    private double tienNhan;

    public ThanhToanTienMat(double tienNhan) {
        this.tienNhan = tienNhan;
    }

    public double tinhTienThua(double soTien) {
        if (tienNhan > soTien) {
            return tienNhan - soTien;
        }
        return 0;
    }

    @Override
    public boolean thanhToan(double soTien) {
        return tienNhan >= soTien;
    }

    @Override
    public String getPhuongThuc() {
        return "Tien mat";
    }

    public double getTienNhan() {
        return tienNhan;
    }

    public void setTienNhan(double tienNhan) {
        this.tienNhan = tienNhan;
    }
}
