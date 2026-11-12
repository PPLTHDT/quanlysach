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
    public void hienThiThongTin(){
    System.out.println("Ma CT : "+maCT+"| Ten sach : "+(sach != null ? sach.getTenSach() : "(khong co)")+"| So luong : "+soLuong+"| Don gia : "+String.format("%,.0f", donGia)+"| Thanh tien : "+String.format("%,.0f", thanhTien));
}
public void xuat(){
    hienThiThongTin();
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
