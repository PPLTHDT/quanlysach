package cuahangsach;

public abstract class ChiTietPhieu implements ITinhTien {
    private static int soLuongDaTao = 0;

    protected String maCT;
    protected int soLuong;
    protected double donGia;
    protected Sach sach;

    public ChiTietPhieu() {
        this.maCT = taoMaCT("CT");
        this.soLuong = 0;
        this.donGia = 0;
    }

    public ChiTietPhieu(String maCT, int soLuong, double donGia) {
        if (maCT == null || maCT.trim().isEmpty()) {
            maCT = taoMaCT("CT");
        }
        capNhatMaCaoNhat(maCT);
        this.maCT = maCT;
        setSoLuong(soLuong);
        setDonGia(donGia);
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
        if (soLuong < 0) {
            throw new IllegalArgumentException("So luong khong the am");
        }
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        if (donGia < 0) {
            throw new IllegalArgumentException("Don gia khong the am");
        }
        this.donGia = donGia;
    }

    public static int getSoLuongDaTao() {
        return soLuongDaTao;
    }

    public static String taoMaCT(String tienTo) {
        soLuongDaTao++;
        return tienTo + String.format("%04d", soLuongDaTao);
    }

    public static void capNhatMaCaoNhat(String maCT) {
        if (maCT == null) {
            return;
        }
        try {
            String so = maCT.replaceAll("\\D", "");
            if (!so.isEmpty()) {
                int stt = Integer.parseInt(so);
                if (stt > soLuongDaTao) {
                    soLuongDaTao = stt;
                }
            }
        } catch (NumberFormatException ignored) {
        }
    }

    public abstract double tinhThanhTien();

    public abstract void hienThiThongTin();

    public abstract String dongFile();
}
