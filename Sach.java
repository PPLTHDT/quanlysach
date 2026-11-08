package quanlysach;
public class Sach{
    private String maSach;
    private String tenSach;
    private TacGia[] dsTacGia;
    private int namXuatBan;
    private TheLoai theLoai;
    private NhaXuatBan nhaXuatBan;
    private double giaBan;
    private int soLuongTon;
    public Sach(){
        dsTacGia = new TacGia[0];
    }
    public Sach( String maSach, String tenSach, TacGia[] dsTacGia, int namXuatBan, TheLoai theLoai, NhaXuatBan nhaXuatBan, double giaBan, int soLuongTon){
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.dsTacGia = dsTacGia;
        this.namXuatBan = namXuatBan;
        this.theLoai = theLoai;
        this.nhaXuatBan = nhaXuatBan;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
    }
    public String getMaSach(){
        return maSach;
    }
    public void setMaSach( String maSach){
        this.maSach = maSach;
    }
    public String getTenSach(){
        return tenSach;
    }
    public void setTenSach( String tenSach){
        this.tenSach = tenSach;
    }
    public TacGia[] getDsTacGia(){
        return dsTacGia;
    }
    public void setDsTacGia( TacGia[] dsTacGia){
        this.dsTacGia = dsTacGia;
    }
    public int getNamXuatBan(){
        return namXuatBan;
    }
    public void setNamXuatBan( int namXuatBan){
        this.namXuatBan = namXuatBan;
    }
    public TheLoai getTheLoai(){
        return theLoai;
    }
    public void setTheLoai( TheLoai theLoai){
        this.theLoai = theLoai;
    }
    public NhaXuatBan getNhaXuatBan(){
        return nhaXuatBan;
    }
    public void setNhaXuatBan( NhaXuatBan nhaXuatBan){
        this.nhaXuatBan = nhaXuatBan;
    }
    public double getGiaBan(){
        return giaBan;
    }
    public void setGiaBan( double giaBan){
        this.giaBan = giaBan;
    }
    public int getSoLuongTon(){
        return soLuongTon;
    }
    public void setSoLuongTon( int soLuongTon){
        this.soLuongTon = soLuongTon;
    }
    public void tangSoLuong(int n){
        if(n>0){
            soLuongTon = soLuongTon + n;
        }
    }
    public boolean giamSoLuong( int n){
        if( n > 0 && n <= soLuongTon){
            soLuongTon = soLuongTon - n;
            return true;
        }
        return false;
    }
    public void hienThi(){
        System.out.println("Ma sach: " + maSach);
        System.out.println("Ten sach: " + tenSach);
        System.out.println("Tac gia: ");
        for( int i = 0; i < dsTacGia.length; i++){
            System.out.println( dsTacGia[i].getTenTacGia() + "; ");
        }
        System.out.println();
        System.out.println( "Nam xuat ban: " + namXuatBan);
        System.out.println("The loai: " + theLoai.getTenTheLoai());
        System.out.println("Nha xuat ban: " + nhaXuatBan.getTenNXB());
        System.out.println("So luong ton: " + soLuongTon);
        System.out.println("Gia ban: " + giaBan);
        System.out.println();

    }
}