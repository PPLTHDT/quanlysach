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
        this.maKH=Main.docChuoi("Nhap vao ma khach hang :");
    }
    public void setDiemTichLuy()
    {
        this.diemTichLuy=Main.docSoNguyen("Nhap vao diem tich luy :");
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
        System.out.println(getThongTin());
        System.out.println("Ma khach hang : "+maKH+"| Diem tich luy : "+diemTichLuy);
    }
    
}