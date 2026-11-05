import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Hoadon{
    private static final int SoChiTietBanDau = 10;
    private String maHD;
    private LocalDate ngayLap;
    private double tongTien;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private ChiTietHoaDon[] dsChiTiet;
    private int soluongChiTiet;
    private ThanhToan thanhToan;

    public Hoadon(){
        this.ngayLap= LocalDate.now();
        this.dsChiTiet= new ChiTietHoaDon[SoChiTietBanDau];
    }
    public Hoadon(String maHD , LocalDate ngayLap , double tongTien , KhachHang khachHang, NhanVien nhanVien , ThanhToan thanhToan){
        this.maHD = maHD;
        this.ngayLap = ngayLap != null ? ngayLap : LocalDate.now();
        this.khachHang= khachHang;
        this.nhanVien= nhanVien;
        this.thanhToan= thanhToan;
        this.dsChiTiet = new ChiTietHoaDon[SoChiTietBanDau];
        this.soluongChiTiet =0;
        this.tongTien=0;
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
    public void inHoaDon(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("==================== HÓA ĐƠN ====================");
        System.out.println("Mã hóa đơn : " + maHD);
        System.out.println("Ngày lập   : " + ngayLap.format(fmt));
        System.out.println("Khách hàng : " + (khachHang != null ? khachHang.getHoTen() : "Khách lẻ"));
        System.out.println("Nhân viên  : " + (nhanVien != null ? nhanVien.getHoTen() : ""));
        System.out.println("-------------------------------------------------");
        System.out.printf("%-8s %-30s %6s %15s %15s%n", "Mã CT", "Tên sách", "SL", "Đơn giá", "Thành tiền");
        for (int i = 0; i < soluongChiTiet; i++) {
            dsChiTiet[i].xuat();
        }
        System.out.println("-------------------------------------------------");
        System.out.printf("TỔNG TIỀN: %,.0f VND%n", tongTien);
        if (thanhToan != null) {
            System.out.println("Phương thức: " + thanhToan.getPhuongThuc());
        }
        System.out.println("=================================================");
    }
}