package doan;
import java.util.Scanner;

public class NhanVien extends Nguoi {
    private String maNV;
    private String chucVu;
    private double luongCoBanNV;
    private int soGioLam;

    public NhanVien()
    {
        super();
        maNV=null;
        chucVu=null;
        luongCoBanNV=0;
        soGioLam=0;
    }
    public NhanVien(String CCCD,String hoTen,String sdt,String email,String diaChi,String maNV,String chucVu,double luongCoBan,int soGioLam)
    {
        super(CCCD, hoTen, sdt, email, diaChi);
        this.maNV=maNV;
        this.chucVu=chucVu;
        this.luongCoBanNV=luongCoBan;
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
            this.luongCoBanNV=sc.nextDouble();
            sc.nextLine();

        }
        while(luongCoBanNV<0);
        
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
    public void setMaNV(String maNV)
    {
        this.maNV=maNV;
    }
    public void setChucVu(String chucVu)
    {
        this.chucVu=chucVu;
    }
    public void setLuongCoBanNV(double luongCoBan)
    {
        this.luongCoBanNV=luongCoBan;
    }
    public void setSoGioLam(int soGioLam)
    {
        this.soGioLam=soGioLam;
    }

    public String getMaNV()
    {
        return maNV;
    }
    public String getChucVu()
    {
        return chucVu;
    }
    public double getLuongCoBanNV()
    {
        return luongCoBanNV;
    }
    public int getSoGioLam()
    {
        return soGioLam;
    }
    public double tinhLuong()
    {
        return luongCoBanNV*soGioLam;
    }
    public void hienThiThongTin()
    {
        System.out.println(getThongTin());
        System.out.println("Ma NV : "+maNV+"| Chuc vu : "+chucVu+"| Luong co ban : "+luongCoBanNV+"| So gio lam :"+soGioLam+"| Luong : "+tinhLuong());
    }
    
}