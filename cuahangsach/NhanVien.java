package cuahangsach;

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
        this.maNV=Main.docChuoi("Nhap vao maNV : ");
    }
    public void setChucVu()
    {
        this.chucVu=Main.docChuoi("Nhap vao chuc vu(ke toan,bao ve,kho) : ");
    }
    public void setLuongCoBan()
    {
        do{
            this.luongCoBanNV=Main.docSoThuc("Nhap vao luong co ban : ");
        }
        while(luongCoBanNV<0);
        
    }
    public void setSoGioLam()
    {
        do{
            this.soGioLam=Main.docSoNguyen("Nhap vao so gio lam : ");
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
    @Override 
    public void hienThiThongTin()
    {
        System.out.println(getThongTin());
        System.out.println("Ma NV : "+maNV+"| Chuc vu : "+chucVu+"| Luong co ban : "+luongCoBanNV+"| So gio lam :"+soGioLam+"| Luong : "+tinhLuong());
    }
    
}