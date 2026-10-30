package doan;
import java.util.Scanner;

public class nhanvien extends People {
    private String maNV;
    private String chucVu;
    private double luongCoBan;
    private int soGioLam;

    public nhanvien()
    {
        super();
        maNV=null;
        chucVu=null;
        luongCoBan=0;
        soGioLam=0;
    }
    public nhanvien(String maNguoi,String hoTen,String sdt,String email,String diaChi,String maNV,String chucVu,double luongCoBan,int soGioLam)
    {
        super(maNguoi, hoTen, sdt, email, diaChi);
        this.maNV=maNV;
        this.chucVu=chucVu;
        this.luongCoBan=luongCoBan;
        this.soGioLam=soGioLam;
    }
    public void setMaNV()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap vao maNV : ");
        this.maNV=sc.nextLine();
    }
    public void setChucVu()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap vao chuc vu(ke toan,bao ve,kho) : ");
        this.chucVu=sc.nextLine();
    }
    public void setLuongCoBan()
    {
        Scanner sc=new Scanner(System.in);
        do{
            System.out.println("Nhap vao luong co ban : ");
            this.luongCoBan=sc.nextDouble();
            sc.nextLine();

        }
        while(luongCoBan<0);
        
    }
    public void setSoGioLam()
    {
        Scanner sc=new Scanner(System.in);
        do{
            System.out.println("Nhap vao so gio lam : ");
            this.soGioLam=sc.nextInt();
            sc.nextLine();
        }
        while(soGioLam<0);
    }

    public String getMaNV()
    {
        return maNV;
    }
    public String getChucVu()
    {
        return chucVu;
    }
    public double getLuongCoban()
    {
        return luongCoBan;
    }
    public int getSoGioLam()
    {
        return soGioLam;
    }
    public double tinhLuong()
    {
        return luongCoBan*soGioLam;
    }
    public void hienThiThongTin()
    {
        System.out.println(getThongTin());
        System.out.println("Ma NV : "+maNV+"| Chuc vu : "+chucVu+"| Luong co ban : "+luongCoBan+"| So gio lam :"+soGioLam+"| Luong : "+tinhLuong());
    }
    
}
