package quanlysach;
import java.util.Scanner;
public class DanhSachSach{
    private Scanner sc = new Scanner(System.in);
    public static final int MAX = 100;
    private Sach[] dsSach;
    private int soLuong;
    public DanhSachSach(){
        dsSach = new Sach[MAX];
        soLuong = 0;
    }
public int nhapSoNguyen( String tb){
    while(true){
        try{
            System.out.print(tb);
            return Integer.parseInt(Main.sc.nextLine());
        }
        catch(NumberFormatException e){
            System.out.println("Nhap lai so nguyen.");
        }
    }
}
public double nhapSoThapPhan(String tb){
    while(true){
        try {
            System.out.print(tb);
                return Double.parseDouble(Main.sc.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("Nhap lai so.");
        }
    }
}
    public Sach timKiem( String ma){
        for( int i = 0; i < soLuong; i++){
            if(dsSach[i].getMaSach().equals(ma)){
                return dsSach[i];
            }
        }
        return null;
    }

    public boolean  them(Sach s){
        if(soLuong >= MAX){
            return false;
        }
            if(timKiem(s.getMaSach()) != null){
                return false;
            }
            dsSach[soLuong] = s;
            soLuong++;
            return true;
    }
    public boolean suaTheoMa( String ma){
        Sach s = timKiem(Ma);
        if( s == null){
            return false;
        }
        System.out.println("Ten moi cua sach: ");
        s.setTenSach(Main.sc.nextLine());
        s.setNamXuatBan(nhapSoNguyen("Nam xuat ban moi: "));
        s.setGiaBan(nhapSoThapPhan("Gia ban moi: "));
        s.setSoLuongTon(nhapSoNguyen("So luong ton moi: "));
        return true;
    }
    public boolean xoaTheoMa( String ma){
        for( int i = 0; i < soLuong; i++){
            if( dsSach[i].getMaSach().equals(ma)){
                for( int j =i; j < soLuong - 1; j++){
                    dsSach[j] = dsSach[j+1];
                }
                dsSach[soLuong - 1] = null;
                soLuong--;
                return true;
            }
        }
        return false;
    }
    public void thongKe(){
        int tongTon = 0;
        double tongGiaTri = 0;
        for( int i = 0; i < soLuong; i++){
            tongTon = tongTon + dsSach[i].getSoLuongTon();
            tongGiaTri = tongGiaTri + dsSach[i].getGiaBan()*dsSach[i].getSoLuongTon();
        }
        System.out.println("So loai sach: " + soLuong);
        System.out.println("Tong so luong ton: " + tongTon);
        System.out.println("Tong gia tri cua kho: " + tongGiaTri);
    }
    public Sach[] getDsSach(){
        Sach[] kq = new Sach[soLuong];
        for( int i = 0; i < soLuong; i++){
            kq[i] = dsSach[i];
        }
        return kq;
    }
    public int getSoLuong(){
        return soLuong;
    }


    public void nhap(){
        int n = nhapSoNguyen("Nhap so luong sach: ");
        for( int i = 0; i < n; i++){
            System.out.println("Nhap sach thu " + (i+1) + ": ");
            System.out.print("Ma sach: ");
            String ma = Main.sc.nextLine();
            System.out.print("Ten sach: ");
            String ten = Main.sc.nextLine();
            int sotg = nhapSoNguyen("So tac gia: ");
            TacGia[] dsTG = new TacGia[sotg];
            for( int j = 0; j < sotg; j++){
                System.out.print("Ma tac gia: " + (j + 1) + ": ");
                String maTG = Main.sc.nextLine();
                System.out.print("Ten tac gia: " + (j + 1) + ": ");
                String tenTG = Main.sc.nextLine();
                dsTG[j] = new TacGia(maTG, tenTG);
            }
            int nam = nhapSoNguyen("Nam xuat ban: ");
            System.out.print("Ma the loai: ");
            String matl = Main.sc.nextLine();
            System.out.print("Ten the loai: ");
            String tentl = Main.sc.nextLine();
            TheLoai tl = new TheLoai(matl,tentl);

            System.out.print("Ma nha xuat ban: ");
            String manxb = Main.sc.nextLine();
            System.out.print("Ten nha xuat ban: ");
            String tennxb = Main.sc.nextLine();
            System.out.print("Dia chi nha xuat ban: ");
            String diachi = Main.sc.nextLine();
            NhaXuatBan nxb = new NhaXuatBan(manxb, tennxb, diachi);

            double gia = nhapSoThapPhan("Gia ban: ");
            int ton = nhapSoNguyen("So luong ton: ");
            Sach s = new(ma, ten, dstg, nam, tl, nxb, gia, ton);
            if(them(s) == false){
                System.out.println("Them bi loi.");
            }
        }
    }

    public void xuat(){
        if(soLuong == 0){
            System.out.print("Khong co danh sach sach");
        } else {
            for( int i = 0; i < soLuong; i++){
                dsSach[i].hienThi();
            }
        }
    }
}