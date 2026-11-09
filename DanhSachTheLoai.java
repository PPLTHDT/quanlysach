package quanlysach;
public class DanhSachTheLoai {
    public static final int MAX = 100;
    private TheLoai[] dsTheLoai;
    private int soLuong;
    public DanhSachTheLoai(){
        dsTheLoai = new TheLoai[MAX];
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
    public TheLoai timKiem(String ma){
        for( int i = 0; i < soLuong;i++){
            if( dsTheLoai[i].getMaTheLoai().equals(ma)){
                return dsTheLoai[i];
            }
        }
        return null;
    }
    public boolean them(TheLoai n){
        if( n == null || soLuong >= MAX){
            return false;
        }
        if( timKiem(n.getMaTheLoai()) != null ){
            return false;
        }
        dsTheLoai[soLuong] = n;
        soLuong++;
        return true;
    }
    public boolean suaTheoMa(String ma) {
        TheLoai n = timKiem(ma);
        if (n == null) {
            return false;
        }
        System.out.print("Ten the loai moi: ");
        n.setTenTheLoai(Main.sc.nextLine());
        return true;
    }
 
    public boolean xoaTheoMa(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (dsTheLoai[i].getMaTheLoai().equals(ma)) {
                for (int j = i; j < soLuong - 1; j++) {
                    dsTheLoai[j] = dsTheLoai[j + 1];
                }
                dsTheLoai[soLuong - 1] = null;
                soLuong--;
                return true;
            }
        }
        return false;
    }

    public void thongKe() {
        System.out.println("So the loai: " + soLuong);
    }
 
    public void napDuLieu(TheLoai[] ds) {
        dsTheLoai = new TheLoai[MAX];
        soLuong = 0;
        if (ds == null) {
            return;
        }

        for (int i = 0; i < ds.length && soLuong < MAX; i++) {
            dsTheLoai[soLuong] = ds[i];
            soLuong++;
        }
    }
 
    public TheLoai[] getDs() {
        TheLoai[] kq = new TheLoai[soLuong];
        for (int i = 0; i < soLuong; i++) {
            kq[i] = dsTheLoai[i];
        }
        return kq;
    }
 
    public int getSoLuong() {
        return soLuong;
    }
    public void nhap(){
        int n = nhapSoNguyen("Nhap so luong the loai: ");
        for( int i = 0; i < n; i++){
            System.out.println("Nhap the loai thu" +(i+1) +": ");
            System.out.println("Ma the loai: ");
            String ma = Main.sc.nextLine();
            System.out.println("Ten the loai: ");
            String ten = Main.sc.nextLine();

            if(!them(new TheLoai(ma,ten))) {
                System.out.println("Loi.");
            }
        }
    } 
    public void xuat(){
        if(soLuong == 0){
            System.out.println("Khong co danh sach");
        } else{
            for( int i = 0; i < soLuong; i++){
                dsTheLoai[i].hienThi();
                System.out.println();
            }
        }
    }

}
 
