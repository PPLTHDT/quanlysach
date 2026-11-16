package cuahangsach;

public class KhachHang extends  Nguoi {
    private String maKH;
    private int diemTichLuy;
    public KhachHang()
    {
        super();
        maKH=null;
        diemTichLuy=0;

    }
    public KhachHang(String CCCD,String hoTen,String sdt,String email,String diaChi,String maKH,int diemTichLuy)
    {
        super(CCCD, hoTen, sdt, email, diaChi);
        this.maKH=maKH;
        this.diemTichLuy=diemTichLuy;
    }
    public void tangDiemTichLuy(int diem)
    {
        this.diemTichLuy+=diem;
    }
    public void giamDiemTichLuy(int diem)
    {
        this.diemTichLuy-=diem;
    }
    public void setMaKH()
    {
        this.maKH = Main.docChuoi("Nhap vao ma khach hang :");
    }
    public void setDiemTichLuy()
    {
        int diem;
        do {
            diem = Main.docSoNguyen("Nhap vao diem tich luy :");
            if (diem < 0) {
                System.out.println("Diem tich luy phai >= 0!");
            }
        } while (diem < 0);
        this.diemTichLuy = diem;
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
    @Override 
    public void hienThiThongTin(){
        GiaoDienConsole.inTieuDe("THONG TIN KHACH HANG");
        GiaoDienConsole.inMuc("CCCD", getCCCD());
        GiaoDienConsole.inMuc("Ho ten", getHoTen());
        GiaoDienConsole.inMuc("So dien thoai", getSDT());
        GiaoDienConsole.inMuc("Email", getEmail());
        GiaoDienConsole.inMuc("Dia chi", getDiaChi());
        GiaoDienConsole.inMuc("Ma khach hang", maKH);
        GiaoDienConsole.inMuc("Diem tich luy", String.valueOf(diemTichLuy));
        GiaoDienConsole.inPhanCach();
    }
    
}