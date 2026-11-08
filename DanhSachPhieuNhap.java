public class DanhSachPhieuNhap {
    private PhieuNhap[] dsPhieuNhap;
    private int soLuong;
    public static final int MAX = 100;

    public DanhSachPhieuNhap() {
        this.dsPhieuNhap = new PhieuNhap[MAX];
        this.soLuong = 0;
    }

    public boolean them(PhieuNhap pn) {
        if (pn == null || soLuong >= MAX) {
            return false;
        }
        if (timKiem(pn.getMaPhieuNhap()) != null) {
            return false;
        }
        dsPhieuNhap[soLuong] = pn;
        soLuong++;
        return true;
    }

    public boolean xoaTheoMa(String ma) {
        if (ma == null || soLuong == 0) {
            return false;
        }
        int viTri = -1;
        for (int i = 0; i < soLuong; i++) {
            if (dsPhieuNhap[i].getMaPhieuNhap().equalsIgnoreCase(ma.trim())) {
                viTri = i;
                break;
            }
        }
        if (viTri == -1) {
            return false;
        }
        for (int i = viTri; i < soLuong - 1; i++) {
            dsPhieuNhap[i] = dsPhieuNhap[i + 1];
        }
        dsPhieuNhap[soLuong - 1] = null;
        soLuong--;
        return true;
    }

    public PhieuNhap timKiem(String ma) {
        if (ma == null) {
            return null;
        }
        for (int i = 0; i < soLuong; i++) {
            if (dsPhieuNhap[i].getMaPhieuNhap().equalsIgnoreCase(ma.trim())) {
                return dsPhieuNhap[i];
            }
        }
        return null;
    }

    public PhieuNhap[] getDsPhieuNhap() {
        return dsPhieuNhap;
    }

    public void setDsPhieuNhap(PhieuNhap[] dsPhieuNhap) {
        this.dsPhieuNhap = dsPhieuNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public static int getMax() {
        return MAX;
    }
}
