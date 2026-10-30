package doan;
import java.util.Scanner;
public abstract class People {
    protected String maNguoi;
    protected String hoTen;
    protected String sdt;
    protected String email;
    protected String diaChi;

    public People()
    {
        maNguoi=null;
        hoTen=null;
        sdt=null;
        email=null;
        diaChi=null;
    }
    public People(String maNguoi,String hoTen,String sdt,String email,String diaChi)
    {
        this.maNguoi=maNguoi;
        this.hoTen=hoTen;
        this.sdt=sdt;
        this.email=email;
        this.diaChi=diaChi;

    }
    public void setMaNguoi()
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("Nhap vao ma nguoi :");
        this.maNguoi=sc.nextLine();
    }
    public void setHoTen()
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("Nhap vao ho ten :");
        this.hoTen=sc.nextLine();
    }
    public void setEmail()
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("Nhap vao email :");
        this.email=sc.nextLine();
    }
    public void setDiaChi()
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("Nhap vao dia chi :");
        this.diaChi=sc.nextLine();
    }
    public void setSDT()
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("Nhap vao SDT :");
        this.sdt=sc.nextLine();
    }
    public String getHoTen()
    {
        return hoTen;
    }
    public String getSDT()
    {
        return sdt;
    }
    public String getEmail()
    {
        return email;
    }
    public String getDiaChi()
    {
        return diaChi;
    }
    public String getThongTin()
    {
        return "Ma nguoi : "+maNguoi+"| Ho ten : "+hoTen+"| SDT : "+sdt+"| Email : "+email+"| Dia chi : "+diaChi;
    }
    abstract public void hienThiThongTin();
    
}
