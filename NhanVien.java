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
        this.maNV = Main.docChuoi("Nhap vao maNV : ");
    }
    public void setChucVu()
    {
        this.chucVu = Main.docChuoi("Nhap vao chuc vu(ke toan,bao ve,kho) : ");
    }
    public void setLuongCoBan()
    {
        double luong;
        do {
            luong = Main.docSoThuc("Nhap vao luong co ban : ");
            if (luong < 0) {
                System.out.println("Luong co ban phai >= 0!");
            }
        } while (luong < 0);
        this.luongCoBanNV = luong;
    }
    public void setSoGioLam()
    {
        int gio;
        do {
            gio = Main.docSoNguyen("Nhap vao so gio lam : ");
            if (gio < 0) {
                System.out.println("So gio lam phai >= 0!");
            }
        } while (gio < 0);
        this.soGioLam = gio;
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
        GiaoDienConsole.inTieuDe("THONG TIN NHAN VIEN");
        GiaoDienConsole.inMuc("CCCD", getCCCD());
        GiaoDienConsole.inMuc("Ho ten", getHoTen());
        GiaoDienConsole.inMuc("So dien thoai", getSDT());
        GiaoDienConsole.inMuc("Email", getEmail());
        GiaoDienConsole.inMuc("Dia chi", getDiaChi());
        GiaoDienConsole.inMuc("Ma nhan vien", maNV);
        GiaoDienConsole.inMuc("Chuc vu", chucVu);
        GiaoDienConsole.inMuc("Luong co ban", Main.dinhDangTien(luongCoBanNV));
        GiaoDienConsole.inMuc("So gio lam", String.valueOf(soGioLam));
        GiaoDienConsole.inMuc("Luong", Main.dinhDangTien(tinhLuong()));
        GiaoDienConsole.inPhanCach();
    }
    
}