package cuahangsach;
import java.time.LocalDate;
public class ThongKe {
    public double tongDoanhThu(HoaDon[] ds, int n){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i]!=null){
                tong +=ds[i].tinhTienPhaiTra();
            }
        }
        return tong;
    }
    public double doanhThuTheoNgay(HoaDon[] ds, int n, LocalDate ngay){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i] != null && ds[i].getNgayLap().equals(ngay)){
                tong += ds[i].tinhTienPhaiTra();
            }
        }
        return tong;
    }
    public double doanhThuTheoThang(HoaDon[] ds, int n, int thang , int nam){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i] == null){
                continue;
            }
            LocalDate d = ds[i].getNgayLap();
            if(d.getMonthValue() == thang && d.getYear() == nam){
                tong += ds[i].tinhTienPhaiTra();
            }
        }
        return tong;
    }
    public double doanhThuTheoNam(HoaDon[] ds, int n , int nam){
        double tong = 0;
        for(int i=0;i<n;i++){
            if(ds[i]!= null && ds[i].getNgayLap().getYear() == nam){
                tong += ds[i].tinhTienPhaiTra();
            }
        }
        return tong;
    }
    public double doanhThuTheoNhanVien(HoaDon[] ds, int n, String maNV){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i] != null && ds[i].getNhanVien()!= null && ds[i].getNhanVien().getMaNV().equalsIgnoreCase(maNV)){
                tong += ds[i].tinhTienPhaiTra();
            }
        }
        return tong;
    }
    public double doanhThuTheoKhachHang(HoaDon[] ds, int n, String maKH){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i] != null &&ds[i].getKhachHang()!= null && ds[i].getKhachHang().getMaKH().equalsIgnoreCase(maKH)){
                tong += ds[i].tinhTienPhaiTra();
            }
        }
        return tong;
    }
    public double[] doanhThu12Thang(HoaDon[] ds, int n, int thang, int nam){
        double[] kq= new double[12];
        for(int i=1;i<=12;i++){
            kq[i-1] = doanhThuTheoThang(ds, n, i, nam);
        }
        return kq;
    }
    public int demHoaDonTheoThang(HoaDon[] ds, int n,int thang , int nam){
        int dem =0;
        for(int i=0;i<n;i++){
            if(ds[i]!=null &&ds[i].getNgayLap().getMonthValue()==thang &&ds[i].getNgayLap().getYear()==nam){
                dem++;
            }
        }
        return dem;
    }
    public HoaDon hoaDonCoGiaTriCaoNhat(HoaDon[] ds, int n){
        HoaDon max = null;
        for(int i=0;i<n;i++){
            if(ds[i]!= null && (max ==null || ds[i].tinhTienPhaiTra()> max.tinhTienPhaiTra())){
                max= ds[i];
            }
        }
        return max;
    }
    public int soLuongDaBan(HoaDon[] ds , int n, String maSach){
        int tong=0;
        for(int i=0;i<n;i++){
            if(ds[i]==null){
                continue;
            }
            for(ChiTietHoaDon ct : ds[i].getDsChiTiet()){
                if(ct != null && ct.getSach()!=null && ct.getSach().getMaSach()!=null && ct.getSach().getMaSach().equals(maSach)){
                    tong += ct.getSoLuong();
                }
            }
        }
        return tong;
    }
    private String tien(double x) {
    return String.format("%,.0f VND", x);
}

public void inSachBanChay(HoaDon[] dsHD, int nHD, Sach[] dsSach, int nSach, int top){
    int[] daBan = new int[nSach];
    int[] thuTu = new int[nSach];
    for(int i=0;i<nSach;i++){
        daBan[i]= dsSach[i] != null ? soLuongDaBan(dsHD, nHD, dsSach[i].getMaSach()) :0;
        thuTu[i]=i;
    }
    for (int i = 0; i < nSach - 1; i++) {
        for (int j = i + 1; j < nSach; j++) {
            if (daBan[thuTu[i]] < daBan[thuTu[j]]) {
                int tam = thuTu[i];
                thuTu[i] = thuTu[j];
                thuTu[j] = tam;
            }
        }
    }
    GiaoDienConsole.inTieuDe("TOP SACH BAN CHAY");
    System.out.printf("  %-5s | %-12s | %-32s | %8s%n", "Hang", "Ma sach", "Ten sach", "Da ban");
    System.out.println("  ------------------------------------------------------------------");
    int in=0;
    for(int k=0;k<nSach && in <top;k++){
        int i=thuTu[k];
        if(dsSach[i] != null && daBan[i]>0){
            System.out.printf("  %-5d | %-12s | %-32s | %8d%n",
                    ++in, dsSach[i].getMaSach(), dsSach[i].getTenSach(), daBan[i]);
        }
    }
    if(in==0){
        System.out.println("  Chua co thong tin sach ban.");
    }
}

public void inSachSapHet(Sach[] dsSach, int nSach, int nguong) {
    GiaoDienConsole.inTieuDe("SACH SAP HET HANG");
    System.out.printf("  %-12s | %-32s | %10s%n", "Ma sach", "Ten sach", "Ton kho");
    System.out.println("  ------------------------------------------------------------");
    boolean co = false;
    for (int i = 0; i < nSach; i++) {
        if (dsSach[i] != null && dsSach[i].getSoLuongTon() <= nguong) {
            System.out.printf("  %-12s | %-32s | %10d%n",
                    dsSach[i].getMaSach(), dsSach[i].getTenSach(), dsSach[i].getSoLuongTon());
            co = true;
        }
    }
    if (!co) {
        System.out.println("  Khong co sach nao sap het.");
    }
}
    public int tongSoLuongTon(Sach[] dsSach, int nSach) {
        int tong = 0;
        for (int i = 0; i < nSach; i++) {
            if (dsSach[i] != null) {
                tong += dsSach[i].getSoLuongTon();
            }
        }
        return tong;
    }
 
   
    public double giaTriTonKho(Sach[] dsSach, int nSach) {
        double tong = 0;
        for (int i = 0; i < nSach; i++) {
            if (dsSach[i] != null) {
                tong += dsSach[i].getSoLuongTon() * dsSach[i].getGiaBan();
            }
        }
        return tong;
    }
 
    
   
public void thongKeTongQuan(HoaDon[] dsHD, int nHD, Sach[] dsSach, int nSach) {
    GiaoDienConsole.inTieuDe("THONG KE TONG QUAN");
    GiaoDienConsole.inMuc("So hoa don", String.valueOf(nHD));
    GiaoDienConsole.inMuc("Tong doanh thu", tien(tongDoanhThu(dsHD, nHD)));
    GiaoDienConsole.inMuc("So dau sach", String.valueOf(nSach));
    GiaoDienConsole.inMuc("Tong ton kho", String.valueOf(tongSoLuongTon(dsSach, nSach)));
    GiaoDienConsole.inMuc("Gia tri ton kho", tien(giaTriTonKho(dsSach, nSach)));
    HoaDon max = hoaDonCoGiaTriCaoNhat(dsHD, nHD);
    if (max != null) {
        GiaoDienConsole.inMuc("Hoa don cao nhat", max.getMaHD() + " - " + tien(max.tinhTienPhaiTra()));
    }
    inSachBanChay(dsHD, nHD, dsSach, nSach, 5);
    inSachSapHet(dsSach, nSach, 5);
}
public int demKhachHang(KhachHang[] dsHD , int nHD , KhachHang[] dsKH, int nKH){
    int dem =0;
    for(int i=0;i<nKH;i++){
        if(dsKH[i]!=null){
            dem++;
        }
    }
    return dem;
}
public KhachHang khachHangChiTieuNhieuNhat(HoaDon[] dsHD, int nHD , KhachHang[] dsKH, int nKH){
    KhachHang max= null;
    double maxTien =0;
    for(int i=0;i<nKH;i++){
        if(dsKH[i]==null){
            continue;
        }
        double chiTieu = doanhThuTheoKhachHang(dsHD,nHD,dsKH[i].getMaKH());
        if(chiTieu>maxTien){
            maxTien=chiTieu;
            max=dsKH[i];
        }
    }
    return max;
}
public void inTopKhachHangChiTieu(HoaDon[] dsHD, int nHD, KhachHang[] dsKH , int nKH , int top){
    double[] chiTieu = new double[nKH];
    int[] thuTu = new int[nKH];
    for(int i=0;i<nKH;i++){
        chiTieu[i] = dsKH[i] != null ? doanhThuTheoKhachHang(dsHD,nHD,dsKH[i].getMaKH()) : 0;
        thuTu[i] =i;
    }
    for (int i = 0; i < nKH - 1; i++) {
        for (int j = i + 1; j < nKH; j++) {
            if (chiTieu[thuTu[i]] < chiTieu[thuTu[j]]) {
                int tam = thuTu[i];
                thuTu[i] = thuTu[j];
                thuTu[j] = tam;
            }
        }
    }
    GiaoDienConsole.inTieuDe("TOP KHACH HANG CHI TIEU");
    System.out.printf("  %-5s | %-12s | %-28s | %-16s%n", "Hang", "Ma KH", "Ho ten", "Chi tieu");
    System.out.println("  --------------------------------------------------------------------");
    int in=0;
    for(int k=0;k<nKH && in<top ;k++){
        int i= thuTu[k];
        if(dsKH[i] !=null && chiTieu[i]>0){
            System.out.printf("  %-5d | %-12s | %-28s | %-16s%n",
                    ++in, dsKH[i].getMaKH(), dsKH[i].getHoTen(), tien(chiTieu[i]));
        }
    }
    if(in==0){
        System.out.println("  Chua co du lieu khach hang.");
    }
}
public void inTopKhachHangDiem(KhachHang[] dsKH, int nKH , int top){
    int[] thuTu = new int[nKH];
    for(int i=0;i<nKH;i++){
        thuTu[i]=i;
    }
    for (int i = 0; i < nKH - 1; i++) {
        for (int j = i + 1; j < nKH; j++) {
            int diemI = dsKH[thuTu[i]] != null ? dsKH[thuTu[i]].getDiemTichLuy() : -1;
            int diemJ = dsKH[thuTu[j]] != null ? dsKH[thuTu[j]].getDiemTichLuy() : -1;
            if (diemI < diemJ) {
                int tam = thuTu[i];
                thuTu[i] = thuTu[j];
                thuTu[j] = tam;
            }
        }
    }
    GiaoDienConsole.inTieuDe("TOP KHACH HANG TICH DIEM");
    System.out.printf("  %-5s | %-12s | %-28s | %10s%n", "Hang", "Ma KH", "Ho ten", "Diem");
    System.out.println("  ---------------------------------------------------------------");
    int in=0;
    for(int k=0;k<nKH&& in<top;k++){
        int i = thuTu[k];
        if(dsKH[i]!=null && dsKH[i].getDiemTichLuy()>0){
            System.out.printf("  %-5d | %-12s | %-28s | %10d%n",
                    ++in, dsKH[i].getMaKH(), dsKH[i].getHoTen(), dsKH[i].getDiemTichLuy());
        }
    }
    if(in==0){
        System.out.println("  Chua co du lieu khach hang.");
    }
}
public void thongKeChiTietKhachHang(HoaDon[] dsHD, int nHD, KhachHang[] dsKH, int nKH, String maKH) {
    KhachHang kh = null;
    for (int i = 0; i < nKH; i++) {
        if (dsKH[i] != null && dsKH[i].getMaKH().equalsIgnoreCase(maKH)) {
            kh = dsKH[i];
            break;
        }
    }
    if (kh == null) {
        System.out.println("Khong tim thay khach hang " + maKH);
        return;
    }
    int soHD = 0;
    int soSach = 0;
    double tongChi = 0;
    LocalDate ganNhat = null;
    for (int i = 0; i < nHD; i++) {
        if (dsHD[i] != null && dsHD[i].getKhachHang() != null
                && dsHD[i].getKhachHang().getMaKH().equalsIgnoreCase(maKH)) {
            soHD++;
            tongChi += dsHD[i].tinhTienPhaiTra();
            for (ChiTietHoaDon ct : dsHD[i].getDsChiTiet()) {
                if (ct != null) {
                    soSach += ct.getSoLuong();
                }
            }
            if (ganNhat == null || dsHD[i].getNgayLap().isAfter(ganNhat)) {
                ganNhat = dsHD[i].getNgayLap();
            }
        }
    }
    java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
    GiaoDienConsole.inTieuDe("CHI TIET KHACH HANG");
    GiaoDienConsole.inMuc("Ma khach hang", kh.getMaKH());
    GiaoDienConsole.inMuc("Ho ten", kh.getHoTen());
    GiaoDienConsole.inMuc("Diem tich luy", String.valueOf(kh.getDiemTichLuy()));
    GiaoDienConsole.inMuc("So hoa don", String.valueOf(soHD));
    GiaoDienConsole.inMuc("Tong sach da mua", String.valueOf(soSach));
    GiaoDienConsole.inMuc("Tong chi tieu", tien(tongChi));
    if (soHD > 0) {
    GiaoDienConsole.inMuc("Trung binh / HD", tien(tongChi / soHD));
    GiaoDienConsole.inMuc("Lan mua gan nhat", ganNhat.format(fmt));
    }
    GiaoDienConsole.inPhanCach();
}
}
 
