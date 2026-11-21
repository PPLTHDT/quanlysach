package cuahangsach;

public enum VaiTro {
    KHO("Nhan vien kho"),
    KE_TOAN("Ke toan"),
    QUAN_LY("Quan ly");

    private final String ten;

    VaiTro(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }
}
