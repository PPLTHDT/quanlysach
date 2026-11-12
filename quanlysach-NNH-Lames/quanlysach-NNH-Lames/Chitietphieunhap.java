public class Chitietphieunhap {
    private Sach sach;
    private int soLuongNhap;
    private double donGiaNhap;

    public Chitietphieunhap(){

    }
    public Chitietphieunhap(Sach sach , int soLuongNhap , double donGiaNhap){
        this.sach=sach;
        setSoLuongNhap(soLuongNhap);
        setDonGiaNhap(donGiaNhap);
    }
    public double tinhThanhTien(){
        return donGiaNhap * soLuongNhap;
    }
    public void capNhatSoLuong(){
        if(sach != null && soLuongNhap >0){
            sach.tangSoLuong(soLuongNhap);
        }
    }
    public void hienThiThongTin(){
    System.out.println("Ten sach : "+(sach != null ? sach.getTenSach() : "(khong co)")+"| So luong nhap : "+soLuongNhap+"| Don gia nhap : "+String.format("%,.0f", donGiaNhap)+"| Thanh tien : "+String.format("%,.0f", tinhThanhTien()));
}
public void xuat(){
    hienThiThongTin();
}
    public Sach getSach(){
        return sach;
    }
    public void setSach(Sach sach){
        this.sach=sach;
    }
    public int getSoLuongNhap(){
        return soLuongNhap;
    }
    public void setSoLuongNhap(int soLuongNhap){
        if(soLuongNhap <= 0){
            throw new IllegalArgumentException("nhap sai ");
        }
        this.soLuongNhap=soLuongNhap;
    }
    public double getDonGiaNhap(){
        return donGiaNhap;
    }
    public void setDonGiaNhap(double donGiaNhap){
        if(donGiaNhap<=0){
            throw new IllegalArgumentException(" nhap sai ");
        }
        this.donGiaNhap=donGiaNhap;
    }
}
