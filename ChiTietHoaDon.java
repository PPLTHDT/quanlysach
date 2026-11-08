public class ChiTietHoaDon {
    private String maCT;
    private Sach sach;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public ChiTietHoaDon(){

    }
    public ChiTietHoaDon(String maCT, Sach sach, int soLuong){
        this(maCT,sach,soLuong,sach != null ? sach.getGiaBan():0);
    }
    public ChiTietHoaDon(String maCT, Sach sach,int soLuong , double donGia){
        this.maCT=maCT;
        this.sach=sach;
        setSoLuong(soLuong);
        this.donGia = donGia;
        tinhThanhTien();

    }
    public double tinhThanhTien(){
        thanhTien= soLuong *donGia;
        return thanhTien;
    }
    public void xuat(){
         System.out.printf("%-8s %-30s %6d %,15.0f %,15.0f%n",
                maCT,
                sach != null ? sach.getTenSach() : "(khong co)",
                soLuong, donGia, thanhTien);
    }
    public String getMaCT(){
        return maCT;
    }
    public void setMaCT(String maCT){
        this.maCT=maCT;
    }
    public Sach getSach(){
        return sach;
    }
    public void setSach(Sach sach){
        this.sach=sach;
        tinhThanhTien();
    }
    public int getSoLuong(){
        return soLuong;
    }
    public void setSoLuong(int soLuong){
        if(soLuong<0){
            throw new IllegalArgumentException(" nhap sai so luong");
        }
        this.soLuong=soLuong;
        tinhThanhTien();
    }
    public double getDonGia(){
        return donGia;
    }
    public void setDonGia(double donGia){
        if(donGia<0){
            throw new IllegalArgumentException("nhap gai loi");
        }
        this.donGia=donGia;
        tinhThanhTien();
    }
    public double getThanhTien(){
        return thanhTien;
    }
}
