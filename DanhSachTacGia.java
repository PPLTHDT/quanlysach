package quanlysach;
public class DanhSachTacGia{
    public static final int MAX = 100;
    private TacGia[] dsTacGia;
    private int soLuong;
    public DanhSachTacGia(){
        dsTacGia = new TacGia[MAX];
        soLuong = 0;
    }
    private int nhapSoNguyen(String tb){
        while (true) {
            try {
                System.out.print(tb);
                return Integer.parseInt(Main.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nhap lai so nguyen.");
            }
        }
    }
      public TacGia timKiem(String ma){
        for (int i = 0; i < soLuong; i++) {
            if (dsTacGia[i].getMaTacGia().equals(ma)) {
                return dsTacGia[i];
            }
        }
        return null;
    }
    public boolean them(TacGia n){
        if (n == null || soLuong >= MAX) {
            return false;
        }
        if (timKiem(n.getMaTacGia()) != null) {
            return false;
        }
        dsTacGia[soLuong] = n;
        soLuong++;
        return true;
    }
 
    public boolean suaTheoMa(String ma){
        TacGia n = timKiem(ma);
        if (n == null) {
            return false;
        }
        System.out.print("Ten tac gia moi: ");
        n.setTenTacGia(Main.sc.nextLine());
        return true;
    }
    public boolean xoaTheoMa(String ma){
        for (int i = 0; i < soLuong; i++) {
            if (dsTacGia[i].getMaTacGia().equals(ma)) {
                for (int j = i; j < soLuong - 1; j++) {
                    dsTacGia[j] = dsTacGia[j + 1];
                }
                dsTacGia[soLuong - 1] = null;
                soLuong--;
                return true;
            }
        }
        return false;
    }
    public void thongKe(){
        System.out.println("So tac gia: " + soLuong);
    }
 
    public void napDuLieu(TacGia[] ds){
        dsTacGia = new TacGia[MAX];
        soLuong = 0;
        if (ds == null) {
            return;
        }
        for (int i = 0; i < ds.length && soLuong < MAX; i++){
            dsTacGia[soLuong] = ds[i];
            soLuong++;
        }
    }
 
    public TacGia[] getDs() {
        TacGia[] kq = new TacGia[soLuong];
        for (int i = 0; i < soLuong; i++) {
            kq[i] = dsTacGia[i];
        }
        return kq;
    }
 
    public int getSoLuong(){
        return soLuong;
    }
    public void nhap(){
        int n = nhapSoNguyen("Nhap so luong tac gia: ");
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap tac gia thu " + (i + 1) + ": ");
            System.out.print("Ma tac gia: ");
            String ma = Main.sc.nextLine();
            System.out.print("Ten tac gia: ");
            String ten = Main.sc.nextLine();
            if (!them(new TacGia(ma, ten))) {
                System.out.println("Them bi loi.");
            }
        }
    }
 
    public void xuat(){
        if (soLuong == 0) {
            System.out.println("Khong co danh sach tac gia");
        } else {
            for (int i = 0; i < soLuong; i++) {
                dsTacGia[i].hienThi();
                System.out.println();
            }
        }
    }
}