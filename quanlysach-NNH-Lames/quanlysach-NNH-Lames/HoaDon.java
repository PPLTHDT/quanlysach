import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class HoaDon{
    private static final int SoChiTietBanDau = 10;
    private String maHD;
    private LocalDate ngayLap;
    private double tongTien;
    private double tienGiam;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private KhuyenMai khuyenMai;
    private ChiTietHoaDon[] dsChiTiet;
    private int soluongChiTiet;
    private ThanhToan thanhToan;

    public HoaDon(){
        this.ngayLap= LocalDate.now();
        this.dsChiTiet= new ChiTietHoaDon[SoChiTietBanDau];
    }
    public HoaDon(String maHD , LocalDate ngayLap , double tongTien , KhachHang khachHang, NhanVien nhanVien , ThanhToan thanhToan){
        this.maHD = maHD;
        this.ngayLap = ngayLap != null ? ngayLap : LocalDate.now();
        this.khachHang= khachHang;
        this.nhanVien= nhanVien;
        this.thanhToan= thanhToan;
        this.dsChiTiet = new ChiTietHoaDon[SoChiTietBanDau];
        this.soluongChiTiet =0;
        this.tongTien=0;
        this.tienGiam=0;
    }
    public String getMaHD(){
        return maHD;
    }
    public void setMaHD(String maHD){
        this.maHD=maHD;
    }
    public LocalDate getNgayLap(){
        return ngayLap;
    }
    public void setNgayLap(LocalDate ngayLap){
        this.ngayLap=ngayLap;
    }
    public double getTongTien(){
        return tongTien;
    }
    public void setTongTien(double tongTien){
        this.tongTien= tongTien;
    }
    public KhachHang getKhachHang(){
        return khachHang;
    }
    public void setKhachHang(KhachHang khachHang){
        this.khachHang= khachHang;
    }
    public NhanVien getNhanVien(){
        return nhanVien;
    }
    public void setNhanVien(NhanVien nhanVien){
        this.nhanVien=nhanVien;
    }
    public ChiTietHoaDon[] getDsChiTiet(){
        return Arrays.copyOf(dsChiTiet,soluongChiTiet);
    }
    public int getSoLuongChiTiet(){
        return soluongChiTiet;
    }
    public ThanhToan getThanhToan(){
        return thanhToan;
    }
    public void setThanhToan(ThanhToan thanhToan){
        this.thanhToan=thanhToan;
    }
    public double getTienGiam(){
        return tienGiam;
    }
    public void setApDungKhuyenMai(KhuyenMai km){
        if(km != null && km.conHieuLuc()){
            this.khuyenMai =km;
        }else{
            this.khuyenMai=null;
            if(km!=null){
                System.out.println(" het hieu luc");
            }
        }
        tinhTongTien();
    }
    public void themChiTiet(ChiTietHoaDon ct){
        if(ct== null){
            return;
        }
        for(int i=0;i<soluongChiTiet;i++){
            ChiTietHoaDon cu = dsChiTiet[i];
            if(cu.getSach() != null && ct.getSach() != null && cu.getSach().getMaSach().equals(ct.getSach().getMaSach())){
                cu.setSoLuong(cu.getSoLuong()+ ct.getSoLuong());
                tinhTongTien();
                return;
            }
        }
        if(soluongChiTiet == dsChiTiet.length){
            dsChiTiet = Arrays.copyOf(dsChiTiet,dsChiTiet.length * 2);
        }
        dsChiTiet[soluongChiTiet++]= ct;
        tinhTongTien();
    }
    public double tinhTongTien(){
        double tong =0;
        for(int i=0;i<soluongChiTiet;i++){
            tong += dsChiTiet[i].tinhThanhTien();
        }
        tienGiam =0;
        if(tienGiam != null && khuyenMai.conHieuLuc()){
            tienGiam = khuyenMai.tinhTienGiam(tong);
            if(tienGiam > 0){
                tienGiam=tong;
            }
        }
        tongTien=tong;
        return tongTien;
    }
    public boolean thucHienThanhToan(double tienKhachDua){
        if(thanhToan ==null || soluongChiTiet ==0){
            return false;
        }
        tinhTongTien();
        if(tienKhachDua < tongTien){
            return false;
        }
        return thanhToan.thanhToan(tongTien);
    }
    public void hienThiThongTin(){
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    System.out.println("Ma HD : "+maHD+"| Ngay lap : "+ngayLap.format(fmt)+"| Khach hang : "+(khachHang != null ? khachHang.getHoTen() : "Khach le")+"| Nhan vien : "+(nhanVien != null ? nhanVien.getHoTen() : "")+"| Tien giam : "+String.format("%,.0f", tienGiam)+"| Tong tien : "+String.format("%,.0f", tongTien)+"| Thanh toan : "+(thanhToan != null ? thanhToan.getPhuongThuc() : ""));
    for (int i = 0; i < soluongChiTiet; i++) {
        System.out.print("    ");
        dsChiTiet[i].hienThiThongTin();
    }
}
public void inHoaDon(){
    hienThiThongTin();
}
}