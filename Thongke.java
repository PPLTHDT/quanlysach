import java.time.LocalDate;
public class Thongke {
    public double tongDoanhThu(Hoadon[] ds, int n){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i]!=null){
                tong +=ds[i].getTongTien();
            }
        }
        return tong;
    }
    public double doangThuTheoNgay(Hoadon[] ds, int n, LocalDate ngay){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i] != null && ds[i].getNgayLap().equals(ngay)){
                tong += ds[i].getTongTien();
            }
        }
        return tong;
    }
    public double doanhthuTheoThang(Hoadon[] ds, int n, int thang , int nam){
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
    public double doanhThuTheoNam(Hoadon[] ds, int n , int nam){
        double tong = 0;
        for(int i=0;i<n;i++){
            if(ds[i]!= null && ds[i].getNgayLap().getYear() == nam){
                tong += ds[i].getTongTien();
            }
        }
        return tong;
    }
    public double doanhThuTheoNhanVien(Hoadon[] ds, int n, String maNV){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i] != null && ds[i].getNhanVien()!= null && ds[i].getNhanVien().getMaNV().equals(maNV)){
                tong += ds[i].getTongTien();
            }
        }
        return tong;
    }
    public double doanhThuTheoKhachHang(Hoadon[] ds, int n, String maKH){
        double tong =0;
        for(int i=0;i<n;i++){
            if(ds[i] != null &&ds[i].getKhachHang()!= null && ds[i].getKhachHang().getMaKH().equals(maKH)){
                tong += ds[i].getTongTien();
            }
        }
        return tong;
    }
    public double[] doanhThu12Thang(Hoadon[] ds, int n, int thang, int nam){
        double[] kq= new double[12];
        for(int i=1;i<=12;i++){
            kq[i-1] = doanhthuTheoThang(ds, n, i, nam);
        }
        return kq;
    }
    public int demHoaDonTheoThang(Hoadon[] ds, int n,int thang , int nam){
        int dem =0;
        for(int i=0;i<n;i++){
            if(ds[i]!=null &&ds[i].getNgayLap().getMonthValue()==thang &&ds[i].getNgayLap().getYear()==nam){
                dem++;
            }
        }
        return dem;
    }
    public Hoadon hoaDonCoGiaTriCaoNhat(Hoadon[] ds, int n){
        Hoadon max = null;
        for(int i=0;i<n;i++){
            if(ds[i]!= null && (max ==null || ds[i].getTongTien()> max.getTongTien())){
                max= ds[i];
            }
        }
        return max;
    }
    public int soLuongDaBan(Hoadon[] ds , int n, String maSach){
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
    public void inSachBanChay(Hoadon[] dsHD, int nHD, Sach[] dsSach, int nSach, int top){
        int[] daBan = new int[nSach];
        Integer[] thuTu = new Integer[nSach];
        for(int i=0;i<nSach;i++){
            daBan[i]= dsSach[i] != null ? soLuongDaBan(dsHD, nHD, dsSach[i].getMaSach()) :0;
            thuTu[i]=i;
        }
        java.util.Arrays.sort(thuTu,(a,b)->daBan[b]-daBan[a]);
        System.out.println("sach ban chay nhat la");
        int in=0;
        for(int k=0;k<nSach && in <top;k++){
            int i=thuTu[k];
            if(dsSach[i] != null && daBan[i]>0){
                System.out.println("%d. %-30s da ban: %d%n", ++in, dsSach[i].getTenSach(), daBan[i]);

            }
        }
        if(in==0){
            System.out.println(" ko co thong tin ");
        }
    }
    public void inSachSapHet(Sach[] dsSach, int nSach, int nguong) {
        System.out.println(" sach gan het");
        boolean co = false;
        for (int i = 0; i < nSach; i++) {
            if (dsSach[i] != null && dsSach[i].getSoLuongTon() <= nguong) {
                System.out.printf("%-10s %-30s con: %d%n", dsSach[i].getMaSach(),
                        dsSach[i].getTenSach(), dsSach[i].getSoLuongTon());
                co = true;
            }
        }
        if (!co) {
            System.out.println("(loi)");
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
 
    
    public void thongKeTongQuan(Hoadon[] dsHD, int nHD, Sach[] dsSach, int nSach) {
        System.out.println("thong ke doanh so");
        System.out.println("so HD          : " + nHD);
        System.out.printf("tong doanh thu      : %,.0f VND%n", tongDoanhThu(dsHD, nHD));
        System.out.println("so sach        : " + nSach);
        System.out.println("tong kho   : " + tongSoLuongTon(dsSach, nSach));
        System.out.printf("gia tri trong kho     : %,.0f VND%n", giaTriTonKho(dsSach, nSach));
        Hoadon max = hoaDonCoGiaTriCaoNhat(dsHD, nHD);
        if (max != null) {
            System.out.printf("ma HD    : %s (%,.0f VND)%n", max.getMaHD(), max.getTongTien());
        }
        inSachBanChay(dsHD, nHD, dsSach, nSach, 5);
        inSachSapHet(dsSach, nSach, 5);
    }
}
