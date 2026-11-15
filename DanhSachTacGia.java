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
                return Integer.parseInt(Main.sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Nhap lai so nguyen.");
            }
        }
    }
    private String nhapChuoi(String tb){
        System.out.print(tb);
        return Main.sc.nextLine();
    }
    private boolean chua(String goc, String tu){
        return goc != null && goc.toLowerCase().contains(tu.trim().toLowerCase());
    }

  

    public TacGia timTheoMa(String ma){
        for (int i = 0; i < soLuong; i++) {
            if (dsTacGia[i].getMaTacGia().equals(ma)) {
                return dsTacGia[i];
            }
        }
        return null;
    }
    

    public void timKiem(){
        System.out.println("--- TIM KIEM TAC GIA ---");
        System.out.println("1. Theo ma (chinh xac)");
        System.out.println("2. Theo ten (gan dung)");
        System.out.println("0. Quay lai");
        int chon = nhapSoNguyen("Chon: ");
        int dem = 0;
        if (chon == 1) {
            TacGia t = timTheoMa(nhapChuoi("Nhap ma tac gia: "));
            if (t != null) {
                t.hienThi();
                dem = 1;
            }
        } else if (chon == 2) {
            String tu = nhapChuoi("Nhap tu khoa trong ten: ");
            for (int i = 0; i < soLuong; i++) {
                if (chua(dsTacGia[i].getTenTacGia(), tu)) {
                    dsTacGia[i].hienThi();
                    System.out.println();
                    dem++;
                }
            }
        } else if (chon != 0) {
            System.out.println("Lua chon khong hop le.");
            return;
        } else {
            return;
        }
        System.out.println("Tim thay " + dem + " ket qua.");
    }


    public boolean them(TacGia n){
        if (n == null || soLuong >= MAX) {
            return false;
        }
        if (timTheoMa(n.getMaTacGia()) != null) {
            return false;
        }
        dsTacGia[soLuong] = n;
        soLuong++;
        return true;
    }
    public boolean suaTheoMa(String ma){
        TacGia n = timTheoMa(ma);
        if (n == null) {
            return false;
        }
        n.setTenTacGia(nhapChuoi("Ten tac gia moi: "));
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

    
    private void nhapMot(){
        String ma = nhapChuoi("Ma tac gia: ");
        String ten = nhapChuoi("Ten tac gia: ");
        if (!them(new TacGia(ma, ten))) {
            System.out.println("Them bi loi (trung ma hoac danh sach day).");
        }
    }
    
    public void nhap(){
        int n = nhapSoNguyen("Nhap so luong tac gia: ");
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap tac gia thu " + (i + 1) + ": ");
            nhapMot();
        }
    }
    
    public void themMot(){
        nhapMot();
    }
    
    public void themNhieu(){
        int k = nhapSoNguyen("Nhap so tac gia can them (k): ");
        for (int i = 0; i < k; i++) {
            System.out.println("Them tac gia thu " + (i + 1) + ": ");
            nhapMot();
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

    

    public void thongKe(){
        System.out.println("Tong so tac gia: " + soLuong);
        String[] key = new String[soLuong];
        int[] dem = new int[soLuong];
        int k = 0;
        for (int i = 0; i < soLuong; i++) {
            String ten = dsTacGia[i].getTenTacGia();
            String kh = (ten == null || ten.isEmpty()) ? "?" : ten.substring(0, 1).toUpperCase();
            int vt = -1;
            for (int j = 0; j < k; j++) {
                if (key[j].equals(kh)) {
                    vt = j;
                    break;
                }
            }
            if (vt == -1) {
                key[k] = kh;
                dem[k] = 1;
                k++;
            } else {
                dem[vt]++;
            }
        }
        System.out.println("Theo chu cai dau cua ten:");
        for (int j = 0; j < k; j++) {
            System.out.println("  " + key[j] + ": " + dem[j]);
        }
    }


    public void menu(){
        int chon;
        do {
            System.out.println();
            System.out.println("===== QUAN LY TAC GIA =====");
            System.out.println("1. Nhap danh sach tac gia");
            System.out.println("2. Xuat danh sach tac gia");
            System.out.println("3. Them 1 tac gia");
            System.out.println("4. Them k tac gia");
            System.out.println("5. Sua tac gia theo ma");
            System.out.println("6. Xoa tac gia theo ma");
            System.out.println("7. Tim kiem");
            System.out.println("8. Thong ke");
            System.out.println("0. Quay lai");
            chon = nhapSoNguyen("Chon: ");
            switch (chon) {
                case 1: nhap(); break;
                case 2: xuat(); break;
                case 3: themMot(); break;
                case 4: themNhieu(); break;
                case 5:
                    if (!suaTheoMa(nhapChuoi("Nhap ma can sua: "))) {
                        System.out.println("Khong tim thay ma.");
                    }
                    break;
                case 6:
                    if (!xoaTheoMa(nhapChuoi("Nhap ma can xoa: "))) {
                        System.out.println("Khong tim thay ma.");
                    }
                    break;
                case 7: timKiem(); break;
                case 8: thongKe(); break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le.");
            }
        } while (chon != 0);
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
}