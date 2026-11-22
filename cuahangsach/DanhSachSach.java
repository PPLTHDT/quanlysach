package cuahangsach;
public class DanhSachSach{
    public static final int MAX = 100;
    private Sach[] dsSach;
    private int soLuong;
    private final DanhSachTacGia dsTG;
    private final DanhSachTheLoai dsTL;
    private final DanhSachNhaXuatBan dsNXB;
    
    public DanhSachSach(){
    dsSach = new Sach[MAX];
    soLuong = 0;
    dsTG = null;
    dsTL = null;
    dsNXB = null;
    } 
    public DanhSachSach(DanhSachTacGia dsTG, DanhSachTheLoai dsTL, DanhSachNhaXuatBan dsNXB){
        dsSach = new Sach[MAX];
        soLuong = 0;
        this.dsTG = dsTG;
        this.dsTL = dsTL;
        this.dsNXB = dsNXB;
    }

    private int nhapSoNguyen(String tb){
        while(true){
            try{
                System.out.print(tb);
                return Integer.parseInt(Main.sc.nextLine().trim());
            } catch(NumberFormatException e){
                System.out.println("Nhap lai so nguyen.");
            }
        }
    }
    private double nhapSoThapPhan(String tb){
        while(true){
            try {
                System.out.print(tb);
                return Double.parseDouble(Main.sc.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println("Nhap lai so.");
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


    public Sach timTheoMa(String ma){
        for(int i = 0; i < soLuong; i++){
            if(dsSach[i].getMaSach().equals(ma)){
                return dsSach[i];
            }
        }
        return null;
    }
    private boolean coTacGia(Sach s, String tu){
        TacGia[] tg = s.getDsTacGia();
        for(int i = 0; i < tg.length; i++){
            if(chua(tg[i].getTenTacGia(), tu)){
                return true;
            }
        }
        return false;
    }
    
    public Sach timKiem(String ma){
        return timTheoMa(ma);
    }

    public void timKiem(){
        System.out.println("--- TIM KIEM SACH ---");
        System.out.println("1. Theo ma sach (chinh xac)");
        System.out.println("2. Theo ten sach (gan dung)");
        System.out.println("3. Theo tac gia (gan dung)");
        System.out.println("4. Theo the loai (gan dung)");
        System.out.println("5. Theo nha xuat ban (gan dung)");
        System.out.println("6. Theo khoang gia");
        System.out.println("7. Theo nam xuat ban");
        System.out.println("0. Quay lai");
        int chon = nhapSoNguyen("Chon: ");
        if(chon == 0){
            return;
        }
        int dem = 0;
        if(chon == 1){
            Sach s = timTheoMa(nhapChuoi("Nhap ma sach: "));
            if(s != null){
                s.hienThi();
                dem = 1;
            }
        } else if(chon >= 2 && chon <= 7){
            String tu = "";
            double gMin = 0, gMax = 0;
            int nam = 0;
            if(chon == 2) tu = nhapChuoi("Nhap tu khoa trong ten sach: ");
            if(chon == 3) tu = nhapChuoi("Nhap tu khoa trong ten tac gia: ");
            if(chon == 4) tu = nhapChuoi("Nhap tu khoa trong ten the loai: ");
            if(chon == 5) tu = nhapChuoi("Nhap tu khoa trong ten nha xuat ban: ");
            if(chon == 6){
                gMin = nhapSoThapPhan("Gia tu: ");
                gMax = nhapSoThapPhan("Gia den: ");
            }
            if(chon == 7) nam = nhapSoNguyen("Nam xuat ban: ");
            for(int i = 0; i < soLuong; i++){
                Sach s = dsSach[i];
                boolean khop = false;
                if(chon == 2) khop = chua(s.getTenSach(), tu);
                if(chon == 3) khop = coTacGia(s, tu);
                if(chon == 4) khop = s.getTheLoai() != null && chua(s.getTheLoai().getTenTheLoai(), tu);
                if(chon == 5) khop = s.getNhaXuatBan() != null && chua(s.getNhaXuatBan().getTenNXB(), tu);
                if(chon == 6) khop = s.getGiaBan() >= gMin && s.getGiaBan() <= gMax;
                if(chon == 7) khop = s.getNamXuatBan() == nam;
                if(khop){
                    s.hienThi();
                    dem++;
                }
            }
        } else {
            System.out.println("Lua chon khong hop le.");
            return;
        }
        System.out.println("Tim thay " + dem + " ket qua.");
    }

    public boolean them(Sach s){
        if(s == null || soLuong >= MAX){
            return false;
        }
        if(timTheoMa(s.getMaSach()) != null){
            return false;
        }
        dsSach[soLuong] = s;
        soLuong++;
        return true;
    }
    public boolean suaTheoMa(String ma){
        Sach s = timTheoMa(ma);
        if(s == null){
            return false;
        }
        s.setTenSach(nhapChuoi("Ten moi cua sach: "));
        
        int nam;
        do {
            nam = nhapSoNguyen("Nam xuat ban moi: ");
        } while(nam <= 0);
        s.setNamXuatBan(nam);

        double gia;
        do {
            gia = nhapSoThapPhan("Gia ban moi: ");
        } while(gia < 0);
        s.setGiaBan(gia);

        int ton;
        do {
            ton = nhapSoNguyen("So luong ton moi: ");
        } while(ton < 0);
        s.setSoLuongTon(ton);
        return true;
    }
   
    public boolean xoaTheoMa(String ma){
        for(int i = 0; i < soLuong; i++){
            if(dsSach[i].getMaSach().equals(ma)){
                for(int j = i; j < soLuong - 1; j++){
                    dsSach[j] = dsSach[j + 1];
                }
                dsSach[soLuong - 1] = null;
                soLuong--;
                return true;
            }
        }
        return false;
    }

    
    private TheLoai layTheLoai(String ma, String ten){
        if(dsTL != null){
            TheLoai t = dsTL.timTheoMa(ma);
            if(t != null){
                return t;
            }
            t = new TheLoai(ma, ten);
            dsTL.them(t);
            return t;
        }
        return new TheLoai(ma, ten);
    }
    private NhaXuatBan layNhaXuatBan(String ma, String ten, String diaChi){
        if(dsNXB != null){
            NhaXuatBan n = dsNXB.timTheoMa(ma);
            if(n != null){
                return n;
            }
            n = new NhaXuatBan(ma, ten, diaChi);
            dsNXB.them(n);
            return n;
        }
        return new NhaXuatBan(ma, ten, diaChi);
    }
    private TacGia layTacGia(String ma, String ten){
        if(dsTG != null){
            TacGia t = dsTG.timTheoMa(ma);
            if(t != null){
                return t;
            }
            t = new TacGia(ma, ten);
            dsTG.them(t);
            return t;
        }
        return new TacGia(ma, ten);
    }

    private void nhapMot(){
        String ma = nhapChuoi("Ma sach: ");
        if(timTheoMa(ma) != null){
            System.out.println("Ma sach da ton tai.");
            return;
        }
        String ten = nhapChuoi("Ten sach: ");
        //Chan so am
        int sotg;
        do {
        sotg = nhapSoNguyen("So tac gia: ");
        } while(sotg < 0);

        TacGia[] dsTacGia = new TacGia[sotg];
        for(int j = 0; j < sotg; j++){
            String maTG = nhapChuoi("Ma tac gia " + (j + 1) + ": ");
            TacGia tg = (dsTG == null) ? null : dsTG.timTheoMa(maTG);
            if(tg == null){
                String tenTG = nhapChuoi("Ten tac gia " + (j + 1) + ": ");
                tg = layTacGia(maTG, tenTG);
            }
            dsTacGia[j] = tg;
        }
        int nam;
        do {
            nam = nhapSoNguyen("Nam xuat ban: ");
        } while(nam <= 0);
        String matl = nhapChuoi("Ma the loai: ");
        TheLoai tl = (dsTL == null) ? null : dsTL.timTheoMa(matl);
        if(tl == null){
            tl = layTheLoai(matl, nhapChuoi("Ten the loai: "));
        }
        String manxb = nhapChuoi("Ma nha xuat ban: ");
        NhaXuatBan nxb = (dsNXB == null) ? null : dsNXB.timTheoMa(manxb);
        if(nxb == null){
            String tennxb = nhapChuoi("Ten nha xuat ban: ");
            String diachi = nhapChuoi("Dia chi nha xuat ban: ");
            nxb = layNhaXuatBan(manxb, tennxb, diachi);
        }
        double gia;
        do {
            gia = nhapSoThapPhan("Gia ban: ");
        } while(gia < 0);

        int ton;
        do {
            ton = nhapSoNguyen("So luong ton: ");
        } while(ton < 0);
        if(!them(new Sach(ma, ten, dsTacGia, nam, tl, nxb, gia, ton))){
            System.out.println("Them bi loi.");
        }
    }

    public void nhap(){
        int n = nhapSoNguyen("Nhap so luong sach: ");
        for(int i = 0; i < n; i++){
            System.out.println("Nhap sach thu " + (i + 1) + ": ");
            nhapMot();
        }
    }

    public void themMot(){
        nhapMot();
    }

    public void themNhieu(){
        int k = nhapSoNguyen("Nhap so sach can them (k): ");
        for(int i = 0; i < k; i++){
            System.out.println("Them sach thu " + (i + 1) + ": ");
            nhapMot();
        }
    }
    public void xuat(){
        if(soLuong == 0){
            System.out.println("Khong co danh sach sach");
        } else {
            for(int i = 0; i < soLuong; i++){
                dsSach[i].hienThi();
            }
        }
    }


   private void thongKeTheoNhom(int loai){
    int toiDa = soLuong;
    for(int i = 0; i < soLuong; i++){
        TacGia[] tg = dsSach[i].getDsTacGia();
        if(tg != null){
            toiDa += tg.length;
        }
    }

    String[] khoa = new String[toiDa];
    int[] soDauSach = new int[toiDa];
    int[] tongTon = new int[toiDa];
    int k = 0;

    for(int i = 0; i < soLuong; i++){
        Sach s = dsSach[i];
        int ton = s.getSoLuongTon();

        String[] cacKey;
        int soKey;
        if(loai == 1){
            cacKey = new String[1];
            cacKey[0] = "(khong co)";
            if(s.getTheLoai() != null){
                cacKey[0] = s.getTheLoai().getTenTheLoai();
            }
            soKey = 1;

        } else if(loai == 2){
            cacKey = new String[1];
            cacKey[0] = "(khong co)";
            if(s.getNhaXuatBan() != null){
                cacKey[0] = s.getNhaXuatBan().getTenNXB();
            }
            soKey = 1;

        } else if(loai == 3){
            cacKey = new String[1];
            cacKey[0] = String.valueOf(s.getNamXuatBan());
            soKey = 1;

        } else {
            TacGia[] tg = s.getDsTacGia();
            if(tg == null || tg.length == 0){
                cacKey = new String[1];
                cacKey[0] = "(khong co)";
                soKey = 1;
            } else {
                cacKey = new String[tg.length];
                for(int j = 0; j < tg.length; j++){
                    cacKey[j] = "(khong co)";
                    if(tg[j] != null && tg[j].getTenTacGia() != null){
                        cacKey[j] = tg[j].getTenTacGia();
                    }
                }
                soKey = tg.length;
            }
        }

        for(int c = 0; c < soKey; c++){
            String key = cacKey[c];

            boolean datRoi = false;
            for(int p = 0; p < c; p++){
                if(cacKey[p].equalsIgnoreCase(key)){
                    datRoi = true;
                }
            }
            if(datRoi){
                continue;
            }

            int vt = -1;
            for(int j = 0; j < k; j++){
                if(khoa[j].equalsIgnoreCase(key)){
                    vt = j;
                    break;
                }
            }
            if(vt == -1){
                vt = k;
                khoa[k] = key;
                soDauSach[k] = 0;
                tongTon[k] = 0;
                k++;
            }

            soDauSach[vt]++;
            tongTon[vt] += ton;
        }
    }

    if(k == 0){
        System.out.println("Khong co du lieu.");
        return;
    }
    for(int j = 0; j < k; j++){
        System.out.println("  " + khoa[j] + ": " + soDauSach[j] + " dau sach, ton " + tongTon[j]);
    }
}

    public void thongKe(){
        System.out.println("--- THONG KE SACH ---");
        System.out.println("1. Tong quan (so loai, tong ton, gia tri kho)");
        System.out.println("2. Theo the loai");
        System.out.println("3. Theo nha xuat ban");
        System.out.println("4. Theo nam xuat ban");
        System.out.println("5. Theo tac gia");
        System.out.println("0. Quay lai");
        int chon = nhapSoNguyen("Chon: ");
        if(chon == 1){
            int tongTon = 0;
            double tongGiaTri = 0;
            for(int i = 0; i < soLuong; i++){
                tongTon = tongTon + dsSach[i].getSoLuongTon();
                tongGiaTri = tongGiaTri + dsSach[i].getGiaBan() * dsSach[i].getSoLuongTon();
            }
            System.out.println("So loai sach: " + soLuong);
            System.out.println("Tong so luong ton: " + tongTon);
            System.out.println("Tong gia tri cua kho: " + tongGiaTri);
        } else if(chon >= 2 && chon <= 5){
            thongKeTheoNhom(chon - 1);
        } else if(chon != 0){
            System.out.println("Lua chon khong hop le.");
        }
    }


    //Menu
    public void menu(){
        int chon;
        do {
            System.out.println();
            System.out.println("===== QUAN LY SACH =====");
            System.out.println("1. Nhap danh sach sach");
            System.out.println("2. Xuat danh sach sach");
            System.out.println("3. Them 1 sach");
            System.out.println("4. Them k sach");
            System.out.println("5. Sua sach theo ma");
            System.out.println("6. Xoa sach theo ma");
            System.out.println("7. Tim kiem");
            System.out.println("8. Thong ke");
            System.out.println("0. Quay lai");
            chon = nhapSoNguyen("Chon: ");
            switch(chon){
                case 1: nhap(); break;
                case 2: xuat(); break;
                case 3: themMot(); break;
                case 4: themNhieu(); break;
                case 5:
                    if(!suaTheoMa(nhapChuoi("Nhap ma sach can sua: "))){
                        System.out.println("Khong tim thay ma.");
                    }
                    break;
                case 6:
                    if(!xoaTheoMa(nhapChuoi("Nhap ma sach can xoa: "))){
                        System.out.println("Khong tim thay ma.");
                    }
                    break;
                case 7: timKiem(); break;
                case 8: thongKe(); break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le.");
            }
        } while(chon != 0);
    }

    public void napDuLieu(Sach[] ds){
        dsSach = new Sach[MAX];
        soLuong = 0;
        if(ds == null){
            return;
        }
        for(int i = 0; i < ds.length && soLuong < MAX; i++){
            dsSach[soLuong] = ds[i];
            soLuong++;
        }
    }
    public Sach[] getDs(){
        Sach[] kq = new Sach[soLuong];
        for(int i = 0; i < soLuong; i++){
            kq[i] = dsSach[i];
        }
        return kq;
    }
    public int getSoLuong(){
        return soLuong;
    }
}