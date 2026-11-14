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
    public void setCCCD(String cccd)
    {
        this.CCCD=cccd;
    }
    public void setHoTen(String hoten)
    {
        this.hoTen=hoten;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public void setDiaChi(String diachi)
    {
        this.diaChi=diachi;
    }
    public void setSDT(String sdt)
    {
        this.sdt=sdt;
    }
    public String getCCCD()
    {
        return CCCD;
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
