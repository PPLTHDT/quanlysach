package doan;
import java.util.Scanner;

public class quanly extends People {
    private String maQL;
    private double phuCap;
    private String phongBan;
    private static final double LUONG_CO_BAN=15000000;

    public quanly()
    {
        super();
        maQL=null;
        phuCap=0;
    }
    public quanly(String maNguoi,String hoTen,String sdt,String email,String diaChi,String maQL,double phuCap)
    {
        super(maNguoi, hoTen, sdt, email, diaChi);
        this.maQL=maQL;
        this.phuCap=phuCap;
    }
    public void setMaQL()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap vao ma QL : ");
        maQL=sc.nextLine();
    }
    public void setPhuCap()
    {
        Scanner sc=new Scanner(System.in);
        do{
            System.out.println("Nhap vao phu cap : ");
            phuCap=sc.nextDouble();
            sc.nextLine();
        }while(phuCap>0);
    }
    public void setPhongBan()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap vao phong ban(ke toan,bao ve,kho) :");
        phongBan=sc.nextLine();
    }
    public String getMaQL()
    {
        return maQL;
    }
    public double getPhuCap()
    {
        return phuCap;
    }
    public String getPhongBan()
    {
        return phongBan;
    }
    public double tinhLuong()
    {
        return LUONG_CO_BAN+phuCap;
    }
    public void hienThiThongTin()
    {
        System.out.println(getThongTin());
        System.out.println("Ma QL : "+maQL+"| Phu cap : "+phuCap+"| Luong co ban : "+LUONG_CO_BAN+"| Luong : "+tinhLuong());
    }


}
