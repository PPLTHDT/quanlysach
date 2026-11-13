package doan;
import java.util.Scanner;
public abstract class Nguoi {
    protected String CCCD;
    protected String hoTen;
    protected String sdt;
    protected String email;
    protected String diaChi;

    public Nguoi()
    {
        CCCD=null;
        hoTen=null;
        sdt=null;
        email=null;
        diaChi=null;
    }
    public Nguoi(String CCCD,String hoTen,String sdt,String email,String diaChi)
    {
        this.CCCD=CCCD;
        this.hoTen=hoTen;
        this.sdt=sdt;
        this.email=email;
        this.diaChi=diaChi;

    }
    public void setCCCD()
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("Nhap vao CCCD :");
        this.CCCD=sc.nextLine();


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
        return "-CCCD : "+CCCD+"| Ho ten : "+hoTen+"| SDT : "+sdt+"| Email : "+email+"| Dia chi : "+diaChi;
    }
    abstract public void hienThiThongTin();
    
}
