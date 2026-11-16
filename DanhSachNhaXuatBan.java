package cuahangsach;
public class DanhSachNhaXuatBan{
    public static final int MAX = 100;
    private NhaXuatBan[] dsNhaXuatBan;
    private int soLuong;
    public DanhSachNhaXuatBan(){
        dsNhaXuatBan = new NhaXuatBan[MAX];
        soLuong = 0;
    }



    private int nhapSoNguyen(String tb){
        while (true){
            try{
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


    public NhaXuatBan timTheoMa(String ma){
        for (int i = 0; i < soLuong; i++){
            if (dsNhaXuatBan[i].getMaNXB().equals(ma)){
                return dsNhaXuatBan[i];
            }
        }
        return null;
    }
    

    public void timKiem(){
        GiaoDienConsole.inTieuDe("TIM KIEM NHA XUAT BAN");
        System.out.println("1. Theo ma (chinh xac)");
        System.out.println("2. Theo ten (gan dung)");
        System.out.println("3. Theo dia chi (gan dung)");
        System.out.println("0. Quay lai");
        int chon = nhapSoNguyen("Chon: ");
        int dem = 0;
        if (chon == 1){
            NhaXuatBan n = timTheoMa(nhapChuoi("Nhap ma nha xuat ban: "));
            if (n != null){
                n.hienThi();
                dem = 1;
            }
        } else if (chon == 2){
            String tu = nhapChuoi("Nhap tu khoa trong ten: ");
            for (int i = 0; i < soLuong; i++){
                if (chua(dsNhaXuatBan[i].getTenNXB(), tu)){
                    dsNhaXuatBan[i].hienThi();
                    System.out.println();
                    dem++;
                }
            }
        } else if (chon == 3){
            String tu = nhapChuoi("Nhap tu khoa trong dia chi: ");
            for (int i = 0; i < soLuong; i++){
                if (chua(dsNhaXuatBan[i].getDiaChi(), tu)){
                    dsNhaXuatBan[i].hienThi();
                    System.out.println();
                    dem++;
                }
            }
        } else if (chon != 0){
            System.out.println("Lua chon khong hop le.");
            return;
        } else {
            return;
        }
        System.out.println("Tim thay " + dem + " ket qua.");
    }

   

    public boolean them(NhaXuatBan n){
        if (n == null || soLuong >= MAX){
            return false;
        }
        if (timTheoMa(n.getMaNXB()) != null){
            return false;
        }
        dsNhaXuatBan[soLuong] = n;
        soLuong++;
        return true;
    }
    public boolean suaTheoMa(String ma){
        NhaXuatBan n = timTheoMa(ma);
        if (n == null){
            return false;
        }
        n.setTenNXB(nhapChuoi("Ten nha xuat ban moi: "));
        n.setDiaChi(nhapChuoi("Dia chi moi: "));
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

   

    private void nhapMot(){
        String ma = nhapChuoi("Ma nha xuat ban: ");
        String ten = nhapChuoi("Ten nha xuat ban: ");
        String diaChi = nhapChuoi("Dia chi: ");
        if (!them(new NhaXuatBan(ma, ten, diaChi))){
            System.out.println("Them bi loi (trung ma hoac danh sach day).");
        }
    }
   
    public void nhap() {
        int n = nhapSoNguyen("Nhap so luong nha xuat ban: ");
        for (int i = 0; i < n; i++){
            System.out.println("Nhap nha xuat ban thu " + (i + 1) + ": ");
            nhapMot();
        }
    }
   
    public void themMot(){
        nhapMot();
    }
   
    public void themNhieu(){
        int k = nhapSoNguyen("Nhap so nha xuat ban can them (k): ");
        for (int i = 0; i < k; i++){
            System.out.println("Them nha xuat ban thu " + (i + 1) + ": ");
            nhapMot();
        }
    }
    public void xuat(){
        GiaoDienConsole.inTieuDe("DANH SACH NHA XUAT BAN");
        if (soLuong == 0){
            System.out.println("  Danh sach hien dang trong.");
        } else {
            for (int i = 0; i < soLuong; i++){
                dsNhaXuatBan[i].hienThi();
                System.out.println();
            }
        }
    }

   
    public void thongKe(){
        GiaoDienConsole.inTieuDe("THONG KE NHA XUAT BAN");
        System.out.println("So nha xuat ban: " + soLuong);
        String[] key = new String[soLuong];
        int[] dem = new int[soLuong];
        int k = 0;
        for (int i = 0; i < soLuong; i++){
            String dc = dsNhaXuatBan[i].getDiaChi();
            if (dc == null || dc.trim().isEmpty()){
                dc = "(chua co dia chi)";
            }
            int vt = -1;
            for (int j = 0; j < k; j++){
                if (key[j].equalsIgnoreCase(dc)){
                    vt = j;
                    break;
                }
            }
            if (vt == -1){
                key[k] = dc;
                dem[k] = 1;
                k++;
            } else {
                dem[vt]++;
            }
        }
        System.out.println("Theo dia chi:");
        for (int j = 0; j < k; j++){
            System.out.println("  " + key[j] + ": " + dem[j]);
        }
    }


    public void menu(){
        int chon;
        do {
            GiaoDienConsole.inTieuDe("QUAN LY NHA XUAT BAN");
            System.out.println("  1. Nhap danh sach nha xuat ban");
            System.out.println("  2. Xem danh sach nha xuat ban");
            System.out.println("  3. Them mot nha xuat ban");
            System.out.println("  4. Them nhieu nha xuat ban");
            System.out.println("  5. Sua nha xuat ban theo ma");
            System.out.println("  6. Xoa nha xuat ban theo ma");
            System.out.println("  7. Tim kiem");
            System.out.println("  8. Thong ke");
            System.out.println("  0. Quay lai");
            chon = nhapSoNguyen("Chon: ");
            switch (chon){
                case 1: nhap(); break;
                case 2: xuat(); break;
                case 3: themMot(); break;
                case 4: themNhieu(); break;
                case 5:
                    if (!suaTheoMa(nhapChuoi("Nhap ma can sua: "))){
                        System.out.println("Khong tim thay ma.");
                    }
                    break;
                case 6:
                    if (!xoaTheoMa(nhapChuoi("Nhap ma can xoa: "))){
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

 
    public void napDuLieu(NhaXuatBan[] ds){
        dsNhaXuatBan = new NhaXuatBan[MAX];
        soLuong = 0;
        if (ds == null){
            return;
        }
        for (int i = 0; i < ds.length && soLuong < MAX; i++){
            if (ds[i] != null) {
                dsNhaXuatBan[soLuong] = ds[i];
                soLuong++;
            }
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

    public NhaXuatBan timKiem(String ma) { return timTheoMa(ma); }
}