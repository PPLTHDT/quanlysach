import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
public class ThongKe {
    public double tongDoanhThu(HoaDon[] ds, int n){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i]!=null){
                tong +=ds[i].getTongTien();
            }
        }
        return tong;
    }
    public double doanhThuTheoNgay(HoaDon[] ds, int n, LocalDate ngay){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i] != null && ds[i].getNgayLap().equals(ngay)){
                tong += ds[i].getTongTien();
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
                tong += ds[i].getTongTien();
            }
        }
        return tong;
    }
    public double doanhThuTheoNam(HoaDon[] ds, int n , int nam){
        double tong = 0;
        for(int i=0;i<n;i++){
            if(ds[i]!= null && ds[i].getNgayLap().getYear() == nam){
                tong += ds[i].getTongTien();
            }
        }
        return tong;
    }
    public double doanhThuTheoNhanVien(HoaDon[] ds, int n, String maNV){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i] != null && ds[i].getNhanVien()!= null && ds[i].getNhanVien().getMaNV().equals(maNV)){
                tong += ds[i].getTongTien();
            }
        }
        return tong;
    }
    public double doanhThuTheoKhachHang(HoaDon[] ds, int n, String maKH){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i] != null &&ds[i].getKhachHang()!= null && ds[i].getKhachHang().getMaKH().equals(maKH)){
                tong += ds[i].getTongTien();
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
            if(ds[i]!= null && (max ==null || ds[i].getTongTien()> max.getTongTien())){
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
                if(ct.getSach()!=null && ct.getSach().getMaSach().equals(maSach)){
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
    Integer[] thuTu = new Integer[nSach];
    for(int i=0;i<nSach;i++){
        daBan[i]= dsSach[i] != null ? soLuongDaBan(dsHD, nHD, dsSach[i].getMaSach()) :0;
        thuTu[i]=i;
    }
    java.util.Arrays.sort(thuTu,(a,b)->daBan[b]-daBan[a]);
    System.out.println("Sach ban chay nhat :");
    int in=0;
    for(int k=0;k<nSach && in <top;k++){
        int i=thuTu[k];
        if(dsSach[i] != null && daBan[i]>0){
            System.out.println("Top : "+(++in)+"| Ma sach : "+dsSach[i].getMaSach()+"| Ten sach : "+dsSach[i].getTenSach()+"| Da ban : "+daBan[i]);
        }
    }
    if(in==0){
        System.out.println("Khong co thong tin");
    }
}

public void inSachSapHet(Sach[] dsSach, int nSach, int nguong) {
    System.out.println("Sach sap het :");
    boolean co = false;
    for (int i = 0; i < nSach; i++) {
        if (dsSach[i] != null && dsSach[i].getSoLuongTon() <= nguong) {
            System.out.println("Ma sach : "+dsSach[i].getMaSach()+"| Ten sach : "+dsSach[i].getTenSach()+"| Con lai : "+dsSach[i].getSoLuongTon());
            co = true;
        }
    }
    if (!co) {
        System.out.println("Khong co sach nao sap het");
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
    System.out.println("=== THONG KE TONG QUAN ===");
    System.out.println("So HD : "+nHD+"| Tong doanh thu : "+tien(tongDoanhThu(dsHD, nHD)));
    System.out.println("So sach : "+nSach+"| Tong ton kho : "+tongSoLuongTon(dsSach, nSach)+"| Gia tri ton kho : "+tien(giaTriTonKho(dsSach, nSach)));
    HoaDon max = hoaDonCoGiaTriCaoNhat(dsHD, nHD);
    if (max != null) {
        System.out.println("Hoa don cao nhat | Ma HD : "+max.getMaHD()+"| Tong tien : "+tien(max.getTongTien()));
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
    Integer[] thuTu = new Integer[nKH];
    for(int i=0;i<nKH;i++){
        chiTieu[i] = dsKH[i] != null ? doanhThuTheoKhachHang(dsHD,nHD,dsKH[i].getMaKH()) : 0;
        thuTu[i] =i;
    }
    Arrays.sort(thuTu,(a,b) -> Double.compare(chiTieu[b],chiTieu[a]));
    System.out.println("Khach hang chi tieu nhieu nhat :");
    int in=0;
    for(int k=0;k<nKH && in<top ;k++){
        int i= thuTu[k];
        if(dsKH[i] !=null && chiTieu[i]>0){
            System.out.println("Top : "+(++in)+"| Ma KH : "+dsKH[i].getMaKH()+"| Ho ten : "+dsKH[i].getHoTen()+"| Chi tieu : "+tien(chiTieu[i]));
        }
    }
    if(in==0){
        System.out.println("Du lieu trong");
    }
}
public void inTopKhachHangDiem(KhachHang[] dsKH, int nKH , int top){
    Integer[] thuTu = new Integer[nKH];
    for(int i=0;i<nKH;i++){
        thuTu[i]=i;
    }
    Arrays.sort(thuTu,(a,b) ->{
        int da = dsKH[a] != null ? dsKH[a].getDiemTichLuy() :-1;
        int db =dsKH[b] != null ? dsKH[b].getDiemTichLuy() : -1;
        return Integer.compare(db,da);
    });
    System.out.println("Khach hang co diem tich luy cao nhat :");
    int in=0;
    for(int k=0;k<nKH&& in<top;k++){
        int i = thuTu[k];
        if(dsKH[i]!=null && dsKH[i].getDiemTichLuy()>0){
            System.out.println("Top : "+(++in)+"| Ma KH : "+dsKH[i].getMaKH()+"| Ho ten : "+dsKH[i].getHoTen()+"| Diem tich luy : "+dsKH[i].getDiemTichLuy());
        }
    }
    if(in==0){
        System.out.println("Du lieu trong");
    }
}
public void thongKeChiTietKhachHang(HoaDon[] dsHD, int nHD, KhachHang[] dsKH, int nKH, String maKH) {
    KhachHang kh = null;
    for (int i = 0; i < nKH; i++) {
        if (dsKH[i] != null && dsKH[i].getMaKH().equals(maKH)) {
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
                && dsHD[i].getKhachHang().getMaKH().equals(maKH)) {
            soHD++;
            tongChi += dsHD[i].getTongTien();
            for (ChiTietHoaDon ct : dsHD[i].getDsChiTiet()) {
                soSach += ct.getSoLuong();
            }
            if (ganNhat == null || dsHD[i].getNgayLap().isAfter(ganNhat)) {
                ganNhat = dsHD[i].getNgayLap();
            }
        }
    }
    java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dong = "Ma KH : "+kh.getMaKH()+"| Ho ten : "+kh.getHoTen()+"| Diem tich luy : "+kh.getDiemTichLuy()+"| So hoa don : "+soHD+"| Tong so sach mua : "+soSach+"| Tong chi tieu : "+tien(tongChi);
    if (soHD > 0) {
        dong += "| Trung binh / HD : "+tien(tongChi / soHD)+"| Lan mua gan nhat : "+ganNhat.format(fmt);
    }
    System.out.println(dong);
}
}
 
