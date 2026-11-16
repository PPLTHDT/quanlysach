package cuahangsach;

public class DanhSachNhanVien{
    private NhanVien[] dsNhanVien = new NhanVien[MAX_NV];
    private QuanLy[] dsQuanLy = new QuanLy[MAX_QL];
    private int soluongNV=0,soluongQL=0;
    public static final int MAX_NV=20;
    public static final int MAX_QL=10;
    
    public void nhapNhanVien()
    {
        NhanVien nv=new NhanVien();
        nv.setCCCD();
        nv.setHoTen();
        nv.setSDT();
        nv.setEmail();
        nv.setDiaChi();
        nv.setMaNV();
        nv.setChucVu();
        nv.setLuongCoBan();
        nv.setSoGioLam();
        if (!themNhanVien(nv)) {
            System.out.println("Them nhan vien that bai (trung ma hoac danh sach day)!");
        }
    }
    public void xuatNhanVien(NhanVien nv)
    {
        nv.hienThiThongTin();
    }
    public void themNNV(int n)
    {
        for(int i=1;i<=n;i++)
        {
            nhapNhanVien();
        }
    }
    public boolean themNhanVien(NhanVien nv)
    {
        if(nv==null||soluongNV==MAX_NV)
        {
            return false;
        }
        for(int i=0;i<soluongNV;i++)
        {
            if(dsNhanVien[i].getMaNV()!=null&&nv.getMaNV()!=null
                    &&dsNhanVien[i].getMaNV().equalsIgnoreCase(nv.getMaNV()))
            {
                return false;
            }
        }
        dsNhanVien[soluongNV++]=nv;
        return true;
    }
    public boolean suaNhanVien(String maNV)
    {
        
        for (int i=0;i<soluongNV;i++) {
            if(dsNhanVien[i].getMaNV().equalsIgnoreCase(maNV))
            {
                System.out.println("1.Sua CCCD.");
                System.out.println("2.Sua ho ten.");
                System.out.println("3.Sua sdt.");
                System.out.println("4.Sua email.");
                System.out.println("5.Sua dia chi.");
                System.out.println("6.Sua ma nhan vien.");
                System.out.println("7.Sua chuc vu.");
                System.out.println("8.Sua luong co ban.");
                System.out.println("9.Sua so gio lam.");
                int k=Main.docSoNguyen("Lua chon cua ban: ");
                switch (k) {
                    case 1:dsNhanVien[i].setCCCD();break;
                    case 2:dsNhanVien[i].setHoTen();break;
                    case 3:dsNhanVien[i].setSDT();break;
                    case 4:dsNhanVien[i].setEmail();break;
                    case 5:dsNhanVien[i].setDiaChi();break;
                    case 6:dsNhanVien[i].setMaNV();break;
                    case 7:dsNhanVien[i].setChucVu();break;
                    case 8:dsNhanVien[i].setLuongCoBan();break;
                    case 9:dsNhanVien[i].setSoGioLam();break;
                
                    default:
                        System.out.println("Lua chon khong hop le!");
                        break;
                }
                return true;

            }
            
        }
        System.out.println("Khong tim thay ma NV nay.");
        return false;
    }
    public boolean xoaNhanVien(String maNV)
    {
        for(int i=0;i<soluongNV;i++)
        {
            if(dsNhanVien[i].getMaNV().equalsIgnoreCase(maNV))
            {
                for(int j=i;j<soluongNV-1;j++)
                {
                    dsNhanVien[j]=dsNhanVien[j+1];
                }
                dsNhanVien[soluongNV-1]=null;
                soluongNV--;
                return true;
            }
        }
        return false;
    }
    public NhanVien timKiemMaNV(String maNV)
    {
        for(int i=0;i<soluongNV;i++)

            {
                if(dsNhanVien[i].getMaNV().equalsIgnoreCase(maNV))
                    return dsNhanVien[i];
            }
        return null;
    }
    public NhanVien timKiemHoTenNV(String hoTen)
    {
        for(int i=0;i<soluongNV;i++)

            {
                if(dsNhanVien[i].getHoTen().contains(hoTen))
                    return dsNhanVien[i];
            }
        return null;
    }
    public NhanVien timKiemSdtNV(String SDT)
    {
        for(int i=0;i<soluongNV;i++)

            {
                if(dsNhanVien[i].getSDT().contains(SDT))
                    return dsNhanVien[i];
            }
        return null;
    }
    public void timKiemNV()
    {
        int k;
        do{
            System.out.println("1.Tim kiem theo ma NV.");
            System.out.println("2.Tim kiem theo ten NV.");
            System.out.println("3.Tim kiem theo sdt NV.");
            System.out.println("  0. Quay lai");
            k=Main.docSoNguyen("Lua chon cua ban:");

            NhanVien nv;
            switch (k) {
                case 1:
                    {
                        nv=timKiemMaNV(Main.docChuoi("Nhap vao ma NV:"));
                        if (nv != null) {
                            nv.hienThiThongTin();
                        } else {
                            System.out.println("Khong tim thay nhan vien!");
                        }
                        break;
                    }
                case 2:
                    {
                        nv=timKiemHoTenNV(Main.docChuoi("Nhap vao ho ten NV:"));
                        if (nv != null) {
                            nv.hienThiThongTin();
                        } else {
                            System.out.println("Khong tim thay nhan vien!");
                        }
                        break;
                    }
                case 3:
                    {
                        nv=timKiemSdtNV(Main.docChuoi("Nhap vao SDT NV:"));
                        if (nv != null) {
                            nv.hienThiThongTin();
                        } else {
                            System.out.println("Khong tim thay nhan vien!");
                        }
                        break;
                    }
                case 0:return;
                default:System.out.println("Lua chon khong hop le!");
            }
        }while(k!=0);
        
        
    }
    public double tongLuongNV()
    {
        double tongluong=0;
        for(int i=0;i<soluongNV;i++)
        {
            tongluong+=dsNhanVien[i].tinhLuong();
        }
        return tongluong;
    }

    public void nhapQuanLy()
    {
        QuanLy ql=new QuanLy();
        ql.setCCCD();
        ql.setHoTen();
        ql.setSDT();
        ql.setEmail();
        ql.setDiaChi();
        ql.setMaQL();
        ql.setPhongBan();
        ql.setPhuCap();
        if (!themQuanLy(ql)) {
            System.out.println("Them quan ly that bai (trung ma hoac danh sach day)!");
        }
    }
    public void xuatQuanLy(QuanLy ql)
    {
        ql.hienThiThongTin();
    }
    public boolean themQuanLy(QuanLy ql)
    {
        if(ql==null||soluongQL==MAX_QL)
        {
            return false;
        }
        for(int i=0;i<soluongQL;i++)
        {
            if(dsQuanLy[i].getMaQL()!=null&&ql.getMaQL()!=null
                    &&dsQuanLy[i].getMaQL().equalsIgnoreCase(ql.getMaQL()))
            {
                return false;
            }
        }
        dsQuanLy[soluongQL++]=ql;
        return true;
    }
    public void themNQL(int n)
    {
        for(int i=1;i<=n;i++)
        {
            nhapQuanLy();
        }
    }
    public boolean xoaQuanLy(String maQL)
    {
        for(int i=0;i<soluongQL;i++)
        {
            if(dsQuanLy[i].getMaQL().equalsIgnoreCase(maQL))
            {
                for(int j=i;j<soluongQL-1;j++)
                {
                    dsQuanLy[j]=dsQuanLy[j+1];
                }
                dsQuanLy[soluongQL-1]=null;
                soluongQL--;
                return true;
            }
        }
        return false;
    }
    public boolean suaQuanLy(String maQL)
    {
        for (int i=0;i<soluongQL;i++) {
            if(dsQuanLy[i].getMaQL().equalsIgnoreCase(maQL))
            {
                System.out.println("1.Sua CCCD.");
                System.out.println("2.Sua ho ten.");
                System.out.println("3.Sua sdt.");
                System.out.println("4.Sua email.");
                System.out.println("5.Sua dia chi.");
                System.out.println("6.Sua ma quan ly.");
                System.out.println("7.Sua phu cap.");
                int k=Main.docSoNguyen("Lua chon cua ban: ");
                switch (k) {
                    case 2:dsQuanLy[i].setHoTen();break;
                    case 3:dsQuanLy[i].setSDT();break;
                    case 1:dsQuanLy[i].setCCCD();break;
                    case 4:dsQuanLy[i].setEmail();break;
                    case 5:dsQuanLy[i].setDiaChi();break;
                    case 6:dsQuanLy[i].setMaQL();break;
                    case 7:dsQuanLy[i].setPhuCap();break;
                
                    default:
                        System.out.println("Lua chon khong hop le!");
                        break;
                }
                return true;

            }
        }
        return false;
    }
    public QuanLy timKiemMaQL(String maQL)
    {
        for(int i=0;i<soluongQL;i++)
        {
            if(dsQuanLy[i].getMaQL().equalsIgnoreCase(maQL))
                return dsQuanLy[i];
        }
        return null;
    }
    public QuanLy timKiemHoTenQL(String hoTen)
    {
        for(int i=0;i<soluongQL;i++)
        {
            if(dsQuanLy[i].getHoTen().contains(hoTen))
                return dsQuanLy[i];
        }
        return null;
    }
    public QuanLy timKiemSdtQL(String SDT)
    {
        for(int i=0;i<soluongQL;i++)
        {
            if(dsQuanLy[i].getSDT().contains(SDT))
                return dsQuanLy[i];
        }
        return null;
    }
    
    public void timKiemQuanly()
    {
        int k;
        do{
            System.out.println("1.Tim kiem theo ma QL.");
            System.out.println("2.Tim kiem theo ten QL.");
            System.out.println("3.Tim kiem theo sdt QL.");
            System.out.println("  0. Quay lai");
            k=Main.docSoNguyen("Lua chon cua ban:");

            QuanLy ql;
            switch (k) {
                case 1:
                    {
                        ql=timKiemMaQL(Main.docChuoi("Nhap vao ma QL:"));
                        if (ql != null) {
                            ql.hienThiThongTin();
                        } else {
                            System.out.println("Khong tim thay quan ly!");
                        }
                        break;
                    }
                case 2:
                    {
                        ql=timKiemHoTenQL(Main.docChuoi("Nhap vao ho ten QL:"));
                        if (ql != null) {
                            ql.hienThiThongTin();
                        } else {
                            System.out.println("Khong tim thay quan ly!");
                        }
                        break;
                    }
                case 3:
                    {
                        ql=timKiemSdtQL(Main.docChuoi("Nhap vao SDT QL:"));
                        if (ql != null) {
                            ql.hienThiThongTin();
                        } else {
                            System.out.println("Khong tim thay quan ly!");
                        }
                        break;
                    }
                case 0:return;
                default:System.out.println("Lua chon khong hop le!");
            }
        }while(k!=0);



    }
    public double tongLuongQL()
    {
        double tongluong=0;
        for(int i=0;i<soluongQL;i++)
        {
            tongluong+=dsQuanLy[i].tinhLuong();
        }
        return tongluong;
    }
    public void thongKeNhanSu()
    {
        GiaoDienConsole.inTieuDe("THONG KE NHAN SU");
        GiaoDienConsole.inMuc("So nhan vien", String.valueOf(soluongNV));
        GiaoDienConsole.inMuc("Tong luong nhan vien", Main.dinhDangTien(tongLuongNV()));
        GiaoDienConsole.inMuc("So quan ly", String.valueOf(soluongQL));
        GiaoDienConsole.inMuc("Tong luong quan ly", Main.dinhDangTien(tongLuongQL()));
        

        GiaoDienConsole.inTieuDe("NHAN SU PHONG KE TOAN");
        System.out.println("Quan ly:");
        int qlkt=0,nvkt=0;
        for (int i=0;i<soluongQL;i++) {
            String phongBan = dsQuanLy[i].getPhongBan();
            if(phongBan != null && phongBan.toLowerCase().contains("ke toan"))
            {
                System.out.println(dsQuanLy[i].getHoTen());
                qlkt++;
            }
        }
        System.out.println("Nhan vien:");
        for (int i=0;i<soluongNV;i++) {
            String chucVu = dsNhanVien[i].getChucVu();
            if(chucVu != null && chucVu.toLowerCase().contains("ke toan"))
            {
                System.out.println(dsNhanVien[i].getHoTen());
                nvkt++;
            }  
        }
        GiaoDienConsole.inMuc("So quan ly", String.valueOf(qlkt));
        GiaoDienConsole.inMuc("So nhan vien", String.valueOf(nvkt));
        GiaoDienConsole.inTieuDe("NHAN SU PHONG BAO VE");
        System.out.println("Quan ly:");
        int qlbv=0,nvbv=0;
        for (int i=0;i<soluongQL;i++) {
            String phongBan = dsQuanLy[i].getPhongBan();
            if(phongBan != null && phongBan.toLowerCase().contains("bao ve"))
            {
                System.out.println(dsQuanLy[i].getHoTen());
                qlbv++;
            }   
        }
        System.out.println("Nhan vien:");
        for (int i=0;i<soluongNV;i++) {
            String chucVu = dsNhanVien[i].getChucVu();
            if(chucVu != null && chucVu.toLowerCase().contains("bao ve"))
            {
                System.out.println(dsNhanVien[i].getHoTen());
                nvbv++;
            }
        }
        GiaoDienConsole.inMuc("So quan ly", String.valueOf(qlbv));
        GiaoDienConsole.inMuc("So nhan vien", String.valueOf(nvbv));
        GiaoDienConsole.inTieuDe("NHAN SU PHONG KHO");
        System.out.println("Quan ly:");
        int qlk=0,nvk=0;
        for (int i=0;i<soluongQL;i++) {
            String phongBan = dsQuanLy[i].getPhongBan();
            if(phongBan != null && phongBan.toLowerCase().contains("kho"))
            {
                System.out.println(dsQuanLy[i].getHoTen());
                qlk++;
            }
            
        }

        System.out.println("Nhan vien:");
        for (int i=0;i<soluongNV;i++) {
            String chucVu = dsNhanVien[i].getChucVu();
            if(chucVu != null && chucVu.toLowerCase().contains("kho"))
            {
                System.out.println(dsNhanVien[i].getHoTen());
                nvk++;
            }
            
        }
        GiaoDienConsole.inMuc("So quan ly", String.valueOf(qlk));
        GiaoDienConsole.inMuc("So nhan vien", String.valueOf(nvk));


    }
    public void napDuLieu(NhanVien[] dsnv,QuanLy[] dsql)
    {
        for (int i=0;i<soluongNV;i++) dsNhanVien[i]=null;
        soluongNV=0;
        if(dsnv!=null)
        {
            for (int i=0;i<dsnv.length;i++) {
                if(dsnv[i]==null)continue;
                if(soluongNV==MAX_NV)
                {
                    System.out.println("Danh sach nhan vien da day,bo qua mang du.");
                    break;
                }
                dsNhanVien[soluongNV++]=dsnv[i];
            }
        }
        for (int i=0;i<soluongQL;i++) dsQuanLy[i]=null;
        soluongQL=0;
        if(dsql!=null)
        {
            for (int i=0;i<dsql.length;i++) {
                if(dsql[i]==null)continue;
                if(soluongQL==MAX_QL)
                {
                    System.out.println("Danh sach quan ly da day,bo qua mang du.");
                    break;
                }
                dsQuanLy[soluongQL++]=dsql[i];
            }
        }
    }
    public void xuat()
    {
        xuatDSNhanVien();
        xuatDSQuanLy();
    }
    public NhanVien[] getDsNhanVien()
    {
        return dsNhanVien;
    }
    public QuanLy[] getDsQuanLy()
    {
        return dsQuanLy;
    }
    public int getSoLuongNV()
    {
        return soluongNV;
    }
    public int getSoLuongQL()
    {
        return soluongQL;
    }
    public void xuatDSNhanVien()
    {
        GiaoDienConsole.inTieuDe("DANH SACH NHAN VIEN");
        if (soluongNV == 0) {
            System.out.println("  Danh sach hien dang trong.");
            return;
        }
        for(int i=0;i<soluongNV;i++)
        {
            xuatNhanVien(dsNhanVien[i]);
        }
    }
    public void xuatDSQuanLy()
    {
        GiaoDienConsole.inTieuDe("DANH SACH QUAN LY");
        if (soluongQL == 0) {
            System.out.println("  Danh sach hien dang trong.");
            return;
        }
        for(int i=0;i<soluongQL;i++)
        {
            xuatQuanLy(dsQuanLy[i]);
        }
    }
    public void menu()
    {
        int chon;
        do{
            GiaoDienConsole.inTieuDe("QUAN LY NHAN VIEN VA QUAN LY");
            System.out.println("  1. Them nhan vien");
            System.out.println("  2. Them nhieu nhan vien");
            System.out.println("  3. Sua nhan vien");
            System.out.println("  4. Xoa nhan vien");
            System.out.println("  5. Tim nhan vien");
            System.out.println("  6. Them quan ly");
            System.out.println("  7. Them nhieu quan ly");
            System.out.println("  8. Sua quan ly");
            System.out.println("  9. Xoa quan ly");
            System.out.println(" 10. Tim quan ly");
            System.out.println(" 11. Thong ke nhan su");
            System.out.println(" 12. Xem danh sach nhan vien");
            System.out.println(" 13. Xem danh sach quan ly");
            System.out.println("  0. Quay lai");
            chon=Main.docSoNguyen("Lua chon cua ban: ");
            switch (chon) {
                case 1:nhapNhanVien();break;
                case 2:themNNV(Main.docSoNguyen("Nhap vao so luong nhan vien :"));break;
                case 3:suaNhanVien(Main.docChuoi("Nhap vao ma nhan vien:"));break;
                case 4:xoaNhanVien(Main.docChuoi("Nhap vao ma nhan vien:"));break;
                case 5:timKiemNV();break;
                case 6:nhapQuanLy();break;
                case 7:themNQL(Main.docSoNguyen("Nhap vao so luong quan ly :"));break;
                case 8:suaQuanLy(Main.docChuoi("Nhap vao ma quan ly:"));break;
                case 9:xoaQuanLy(Main.docChuoi("Nhap vao ma quan ly:"));break;
                case 10:timKiemQuanly();break;
                case 11:thongKeNhanSu();break;
                case 12:xuatDSNhanVien();break;
                case 13:xuatDSQuanLy();break;
                case 0:return;
                default:System.out.println("Lua chon khong hop le!");
            }
        }
        while(chon!=0);
    }

    public NhanVien timKiem(String ma) { return timKiemMaNV(ma); }
}