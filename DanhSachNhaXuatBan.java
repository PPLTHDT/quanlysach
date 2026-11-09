package quanlysach;
public class DanhSachNhaXuatBan{
    public static final int MAX = 100;
    private NhaXuatBan[] dsNhaXuatBan;
    private int soLuong;
    public DanhSachNhaXuatBan(){
        dsNhaXuatBan = new NhaXuatBan[MAX];
        soLuong= 0;
    }
    private int nhapSoNguyen(String tb){
        while (true){
            try{
                System.out.print(tb);
                return Integer.parseInt(Main.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nhap lai so nguyen.");
            }
        }
    }
    public NhaXuatBan timKiem(String ma){
        for (int i = 0; i < soLuong; i++){
            if (dsNhaXuatBan[i].getMaNXB().equals(ma)){
                return dsNhaXuatBan[i];
            }
        }
        return null;
    }
 
    public boolean them(NhaXuatBan n){
        if (n == null || soLuong >= MAX){
            return false;
        }
        if (timKiem(n.getMaNXB()) != null){
            return false;
        }
        dsNhaXuatBan[soLuong] = n;
        soLuong++;
        return true;
    }
    public boolean suaTheoMa(String ma){
        NhaXuatBan n = timKiem(ma);
        if (n == null){
            return false;
        }
        System.out.print("Ten nha xuat ban moi: ");
        n.setTenNXB(Main.sc.nextLine());
        System.out.print("Dia chi moi: ");
        n.setDiaChi(Main.sc.nextLine());
        return true;
    }
    public boolean xoaTheoMa(String ma){
        for (int i = 0; i < soLuong; i++){
            if (dsNhaXuatBan[i].getMaNXB().equals(ma)){
                for (int j = i; j < soLuong - 1; j++){
                    dsNhaXuatBan[j] = dsNhaXuatBan[j + 1];
                }
                dsNhaXuatBan[soLuong - 1] = null;
                soLuong--;
                return true;
            }
        }
        return false;
    }
    public void thongKe(){
        System.out.println("So nha xuat ban: " + soLuong);
    }
 
    public void napDuLieu(NhaXuatBan[] ds){
        dsNhaXuatBan = new NhaXuatBan[MAX];
        soLuong = 0;
        if (ds == null){
            return;
        }
        for (int i = 0; i < ds.length && soLuong < MAX; i++){
            dsNhaXuatBan[soLuong] = ds[i];
            soLuong++;
        }
    }
 
    public NhaXuatBan[] getDs(){
        NhaXuatBan[] kq = new NhaXuatBan[soLuong];
        for (int i = 0; i < soLuong; i++) {
            kq[i] = dsNhaXuatBan[i];
        }
        return kq;
    }
 
    public int getSoLuong(){
        return soLuong;
    }

    public void nhap() {
        int n = nhapSoNguyen("Nhap so luong nha xuat ban: ");
        for (int i = 0; i < n; i++){
            System.out.println("Nhap nha xuat ban thu " + (i + 1) + ": ");
            System.out.print("Ma nha xuat ban: ");
            String ma = Main.sc.nextLine();
            System.out.print("Ten nha xuat ban: ");
            String ten = Main.sc.nextLine();
            System.out.print("Dia chi: ");
            String diaChi = Main.sc.nextLine();
            if (!them(new NhaXuatBan(ma, ten, diaChi))){
                System.out.println("Them bi loi.");
            }
        }
    }
 
    public void xuat(){
        if (soLuong == 0){
            System.out.println("Khong co danh sach nha xuat ban");
        } else {
            for (int i = 0; i < soLuong; i++){
                dsNhaXuatBan[i].hienThi();
                System.out.println();
            }
        }
    }
}