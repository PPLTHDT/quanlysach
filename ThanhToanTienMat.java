public class ThanhToanTienMat implements ThanhToan {
    private double tienNhan;

    public ThanhToanTienMat() {
        this.tienNhan = 0;
    }

    public ThanhToanTienMat(double tienNhan) {
        this.tienNhan = Math.max(0, tienNhan);
    }

    public void setTienNhan(double tienNhan) {
        this.tienNhan = Math.max(0, tienNhan);
    }

    @Override
    public boolean thanhToan(double soTien) {
        return tienNhan >= soTien;
    }

    public double getTienNhan() { return tienNhan; }
    public double getTienThua(double soTien) {
        return tienNhan >= soTien ? tienNhan - soTien : 0;
    }
}