package cuahangsach;
public class ChiTietPhieuNhap {
    private static int demMa = 0;

    private String maCT;
    private Sach sach;
    private int soLuongNhap;
    private double donGiaNhap;

    public ChiTietPhieuNhap() {
        this.maCT = taoMaCT();
        this.sach = null;
        this.soLuongNhap = 0;
        this.donGiaNhap = 0.0;
    }

    public ChiTietPhieuNhap(Sach sach, int soLuongNhap, double donGiaNhap) {
        this(taoMaCT(), sach, soLuongNhap, donGiaNhap);
    }

    public ChiTietPhieuNhap(String maCT, Sach sach, int soLuongNhap, double donGiaNhap) {
        if (maCT == null || maCT.trim().isEmpty()) {
            this.maCT = taoMaCT();
        } else {
            this.maCT = maCT.trim();
            capNhatDemMa(this.maCT);
        }
        this.sach = sach;
        setSoLuongNhap(soLuongNhap);
        setDonGiaNhap(donGiaNhap);
    }

    private static String taoMaCT() {
        demMa++;
        return String.format("CTPN%03d", demMa);
    }

    private static void capNhatDemMa(String ma) {
        try {
            String so = ma.replaceAll("\\D", "");
            if (!so.isEmpty()) {
                int stt = Integer.parseInt(so);
                if (stt > demMa) {
                    demMa = stt;
                }
            }
        } catch (NumberFormatException ignored) {
        }
    }

    public double tinhThanhTien() {
        return donGiaNhap * soLuongNhap;
    }

    public void capNhatSoLuong() {
        if (sach != null && soLuongNhap > 0) {
            sach.tangSoLuong(soLuongNhap);
        }
    }

    public void hienThiThongTin() {
        System.out.println("Ma CT: " + maCT
                + " | Sach: " + (sach != null ? sach.getTenSach() : "(Khong co)")
                + " | SL nhap: " + soLuongNhap
                + " | Don gia nhap: " + Main.dinhDangTien(donGiaNhap)
                + " | Thanh tien: " + Main.dinhDangTien(tinhThanhTien()));
    }

    public void xuat() {
        hienThiThongTin();
    }

    public String dongFile() {
        return String.join("|", "CT",
                maCT == null ? "" : maCT,
                sach == null ? "" : sach.getMaSach(),
                String.valueOf(soLuongNhap),
                String.valueOf(donGiaNhap));
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
