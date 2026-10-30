package doan;
import java.util.Scanner;

public class khachhang extends People {
    private String maKH;
    private int diemTichLuy;
    public khachhang()
    {
        super();
        maKH=null;
        diemTichLuy=0;

    }
    public khachhang(String maNguoi,String hoTen,String sdt,String email,String diaChi,String maKH,int diemTichLuy)
    {
        super(maNguoi, hoTen, sdt, email, diaChi);
        this.maKH=maKH;
        this.diemTichLuy=diemTichLuy;
    }
    public void tangDiemTichLuy()
    {
        this.diemTichLuy++;
    }
    public void giamDiemTichLuy()
    {
        this.diemTichLuy--;
    }
    public void setMaKH(String maKH)
    {
        this.maKH=maKH;
    }
    public void setDiemTichLuy(int diemTichLuy)
    {
        this.diemTichLuy=diemTichLuy;
    }
    public String getMaKH()
    {
        return maKH;
    }
    public int getDiemTichLuy()
    {
        return diemTichLuy;
    }
    public void hienThiThongTin(){
        System.out.println(getThongTin());
        System.out.println("Ma khach hang : "+maKH+"| Diem tich luy : "+diemTichLuy);
    }
    
}
