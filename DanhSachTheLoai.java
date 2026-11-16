package cuahangsach;
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


    public TheLoai timTheoMa(String ma){
        for (int i = 0; i < soLuong; i++) {
            if (dsTheLoai[i].getMaTheLoai().equals(ma)) {
                return dsTheLoai[i];
            }
        }
        return null;
    }
    
    public void timKiem(){
        System.out.println("--- TIM KIEM THE LOAI ---");
        System.out.println("1. Theo ma (chinh xac)");
        System.out.println("2. Theo ten (gan dung)");
        System.out.println("0. Quay lai");
        int chon = nhapSoNguyen("Chon: ");
        int dem = 0;
        if (chon == 1) {
            TheLoai t = timTheoMa(nhapChuoi("Nhap ma the loai: "));
            if (t != null) {
                t.hienThi();
                dem = 1;
            }
        } else if (chon == 2) {
            String tu = nhapChuoi("Nhap tu khoa trong ten: ");
            for (int i = 0; i < soLuong; i++) {
                if (chua(dsTheLoai[i].getTenTheLoai(), tu)) {
                    dsTheLoai[i].hienThi();
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


    public boolean them(TheLoai n){
        if (n == null || soLuong >= MAX) {
            return false;
        }
        if (timTheoMa(n.getMaTheLoai()) != null) {
            return false;
        }
        dsTheLoai[soLuong] = n;
        soLuong++;
        return true;
    }
    public boolean suaTheoMa(String ma) {
        TheLoai n = timTheoMa(ma);
        if (n == null) {
            return false;
        }
        n.setTenTheLoai(nhapChuoi("Ten the loai moi: "));
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


    private void nhapMot(){
        String ma = nhapChuoi("Ma the loai: ");
        String ten = nhapChuoi("Ten the loai: ");
        if (!them(new TheLoai(ma, ten))) {
            System.out.println("Them bi loi (trung ma hoac danh sach day).");
        }
    }

    public void nhap(){
        int n = nhapSoNguyen("Nhap so luong the loai: ");
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap the loai thu " + (i + 1) + ": ");
            nhapMot();
        }
    }


    public void themMot(){
        nhapMot();
    }

    public void themNhieu(){
        int k = nhapSoNguyen("Nhap so the loai can them (k): ");
        for (int i = 0; i < k; i++) {
            System.out.println("Them the loai thu " + (i + 1) + ": ");
            nhapMot();
        }
    }
    public void xuat(){
        if (soLuong == 0) {
            System.out.println("Khong co danh sach the loai");
        } else {
            for (int i = 0; i < soLuong; i++) {
                dsTheLoai[i].hienThi();
                System.out.println();
            }
        }
    }


    public void thongKe() {
        System.out.println("Tong so the loai: " + soLuong);
        String[] key = new String[soLuong];
        int[] dem = new int[soLuong];
        int k = 0;
        for (int i = 0; i < soLuong; i++) {
            String ten = dsTheLoai[i].getTenTheLoai();
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
            System.out.println("===== QUAN LY THE LOAI =====");
            System.out.println("1. Nhap danh sach the loai");
            System.out.println("2. Xuat danh sach the loai");
            System.out.println("3. Them 1 the loai");
            System.out.println("4. Them k the loai");
            System.out.println("5. Sua the loai theo ma");
            System.out.println("6. Xoa the loai theo ma");
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

    public TheLoai timKiem(String ma) { return timTheoMa(ma); }
}