package cuahangsach;

public class QuanLy extends Nguoi {
    private String maQL;
    private double phuCap;
    private String phongBan;
    private double luongCoBanQL=15000000;

    public QuanLy()
    {
        super();
        maQL=null;
        phuCap=0;
        phongBan=null;
    }
    public QuanLy(String CCCD,String hoTen,String sdt,String email,String diaChi,String maQL,double phuCap,String phongBan)
    {
        super(CCCD, hoTen, sdt, email, diaChi);
        this.maQL=maQL;
        this.phuCap=phuCap;
        this.phongBan=phongBan;
    }
    public void setMaQL()
    {
        maQL = Main.docChuoi("Nhap vao ma QL : ");
    }
    public void setPhuCap()
    {
        double phuCapMoi;
        do {
            phuCapMoi = Main.docSoThuc("Nhap vao phu cap : ");
            if (phuCapMoi < 0) {
                System.out.println("Phu cap phai >= 0!");
            }
        } while (phuCapMoi < 0);
        phuCap = phuCapMoi;
    }
    public void setPhongBan()
    {
        phongBan = Main.docChuoi("Nhap vao phong ban(ke toan,bao ve,kho) :");
    }
    public void setMaQL(String maQL)
    {
        this.maQL=maQL;
    }
    public void setPhuCap(double phuCap)
    {
        this.phuCap=phuCap;
    }
    public void setPhongBan(String phongBan)
    {
        this.phongBan=phongBan;
    }
    public void setLuongCoBanQL(double luongCoBanQL)
    {
        this.luongCoBanQL=luongCoBanQL;
    }
    public double getLuongCoBanQL()
    {
        return luongCoBanQL;
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
        return luongCoBanQL+phuCap;
    }
    @Override 
    public void hienThiThongTin()
    {
        GiaoDienConsole.inTieuDe("THONG TIN QUAN LY");
        GiaoDienConsole.inMuc("CCCD", getCCCD());
        GiaoDienConsole.inMuc("Ho ten", getHoTen());
        GiaoDienConsole.inMuc("So dien thoai", getSDT());
        GiaoDienConsole.inMuc("Email", getEmail());
        GiaoDienConsole.inMuc("Dia chi", getDiaChi());
        GiaoDienConsole.inMuc("Ma quan ly", maQL);
        GiaoDienConsole.inMuc("Phong ban", phongBan);
        GiaoDienConsole.inMuc("Phu cap", Main.dinhDangTien(phuCap));
        GiaoDienConsole.inMuc("Luong co ban", Main.dinhDangTien(luongCoBanQL));
        GiaoDienConsole.inMuc("Luong", Main.dinhDangTien(tinhLuong()));
        GiaoDienConsole.inPhanCach();
    }


}