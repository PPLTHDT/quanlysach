package cuahangsach;

public final class PhanQuyen {

    public enum Quyen { KHONG, XEM, DAY_DU }

    // Ma chuc nang trong menu chinh
    public static final int SACH = 1;
    public static final int THE_LOAI = 2;
    public static final int TAC_GIA = 3;
    public static final int NXB = 4;
    public static final int KHACH_HANG = 5;
    public static final int NHAN_SU = 6;
    public static final int HOA_DON = 7;
    public static final int PHIEU_NHAP = 8;
    public static final int KHUYEN_MAI = 9;
    public static final int THONG_KE = 10;
    public static final int FILE = 11;
    public static final int DOI_MAT_KHAU = 12;
    public static final int QUAN_LY_TAI_KHOAN = 13;

    public static final int[] TAT_CA = {SACH, THE_LOAI, TAC_GIA, NXB, KHACH_HANG, NHAN_SU,
            HOA_DON, PHIEU_NHAP, KHUYEN_MAI, THONG_KE, FILE, DOI_MAT_KHAU, QUAN_LY_TAI_KHOAN};

    private PhanQuyen() {
    }

    public static Quyen layQuyen(VaiTro vt, int chucNang) {
        if (vt == null) return Quyen.KHONG;
        switch (vt) {
            case QUAN_LY:
                return chucNang >= SACH && chucNang <= QUAN_LY_TAI_KHOAN ? Quyen.DAY_DU : Quyen.KHONG;
            case KHO:
                switch (chucNang) {
                    case SACH: case THE_LOAI: case TAC_GIA: case PHIEU_NHAP:
                        return Quyen.DAY_DU;
                    case NXB: case THONG_KE:
                        return Quyen.XEM;
                    case DOI_MAT_KHAU:
                        return Quyen.DAY_DU;
                    default:
                        return Quyen.KHONG;
                }
            case KE_TOAN:
                switch (chucNang) {
                    case HOA_DON: case DOI_MAT_KHAU:
                        return Quyen.DAY_DU;
                    case SACH: case THE_LOAI: case TAC_GIA: case NXB: case KHACH_HANG:
                    case NHAN_SU: case PHIEU_NHAP: case KHUYEN_MAI: case THONG_KE:
                        return Quyen.XEM;
                    default:
                        return Quyen.KHONG;
                }
            default:
                return Quyen.KHONG;
        }
    }

    public static boolean coQuyen(VaiTro vt, int chucNang) {
        return layQuyen(vt, chucNang) != Quyen.KHONG;
    }

    // Cac muc con trong menu Thong ke (1-9) ma tung vai tro duoc xem
    public static boolean duocXemThongKe(VaiTro vt, int mucThongKe) {
        if (vt == null) return false;
        switch (vt) {
            case QUAN_LY:
                return true;
            case KE_TOAN: // chi cac muc lien quan doanh thu
                return mucThongKe >= 1 && mucThongKe <= 6 || mucThongKe == 9;
            case KHO: // chi sach sap het (8) va thong ke ton kho sach (10)
                return mucThongKe == 8 || mucThongKe == 10;
            default:
                return false;
        }
    }

    // Chuc vu cua nhan vien -> vai tro. Chuc vu khong duoc cap tai khoan (vd: Bao ve) tra ve null
    public static VaiTro vaiTroTuChucVu(String chucVu) {
        if (chucVu == null) return null;
        String s = java.text.Normalizer.normalize(chucVu.trim().toLowerCase(), java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").replace('đ', 'd');
        if (s.contains("kho")) return VaiTro.KHO;
        if (s.contains("ke toan")) return VaiTro.KE_TOAN;
        return null;
    }
}
