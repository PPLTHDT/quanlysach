package doan;
import java.util.Scanner;

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
        Scanner sc=new Scanner(System.in);
        System.out.printf("Nhap vao ma khach hang :");
        this.maKH=sc.nextLine();
    }
    public void setDiemTichLuy()
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("Nhap vao diem tich luy :");
        this.diemTichLuy=sc.nextInt();
        sc.nextLine();
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
    public void hienThiThongTin(){
        System.out.println(getThongTin());
        System.out.println("Ma khach hang : "+maKH+"| Diem tich luy : "+diemTichLuy);
    }
    
}