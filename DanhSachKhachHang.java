package cuahangsach;

public class DanhSachKhachHang {
    private KhachHang[] dsKhachHang = new KhachHang[MAX];
    private int soLuong=0;
    public static final int MAX=100;

    public boolean them(KhachHang a)
    {
        if(a==null||soLuong==MAX)
        {
            return false;
        }
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i].getMaKH()!=null&&dsKhachHang[i].getMaKH().equalsIgnoreCase(a.getMaKH()))
            {
                return false;
            }
        }
        dsKhachHang[soLuong++]=a;
        return true;
    }
    public void nhapKhachHang()
    {
        KhachHang kh=new KhachHang();
        kh.setCCCD();
        kh.setHoTen();
        kh.setDiaChi();
        kh.setEmail();
        kh.setSDT();
        kh.setMaKH();
        kh.setDiemTichLuy();

        them(kh);
    }
    public void themNKhachHang(int n)
    {
        for(int i=0;i<n;i++)
        {
            nhapKhachHang();
        }
    }
    public void xuatKhachHang(KhachHang kh)
    {
        kh.hienThiThongTin();
    }

    public void xuat()
    {
        GiaoDienConsole.inTieuDe("DANH SACH KHACH HANG");
        if (soLuong == 0) {
            System.out.println("  Danh sach hien dang trong.");
            return;
        }
        for(int i=0;i<soLuong;i++)
        {
            xuatKhachHang(dsKhachHang[i]);
        }
    }

    public boolean suaTheoMa(String maKH)
    {
        for (int i=0;i<soLuong;i++) {
            if(dsKhachHang[i].getMaKH().equalsIgnoreCase(maKH))
            {
                System.out.println("1.Sua can cuoc cong dan.");
                System.out.println("2.Sua ho ten.");
                System.out.println("3.Sua sdt.");
                System.out.println("4.Sua email.");
                System.out.println("5.Sua dia chi.");
                System.out.println("6.Sua ma khach hang.");
                System.out.println("7.Sua diem tich luy.");
                int k=Main.docSoNguyen("Lua chon cua ban: ");
                switch (k) {
                    case 1:dsKhachHang[i].setCCCD();break;
                    case 2:dsKhachHang[i].setHoTen();break;
                    case 3:dsKhachHang[i].setSDT();break;
                    case 4:dsKhachHang[i].setEmail();break;
                    case 5:dsKhachHang[i].setDiaChi();break;
                    case 6:dsKhachHang[i].setMaKH();break;
                    case 7:dsKhachHang[i].setDiemTichLuy();break;
                
                    default:
                        System.out.println("Lua chon khong hop le!");
                        break;
                }
                return true;

           }   
        }  
        System.out.println("Khong tim thay ma KH nay.");
        return false;
    }
    public boolean xoaTheoMa(String maKH)
    {
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i].getMaKH().equalsIgnoreCase(maKH))
            {
                for(int j=i;j<soLuong-1;j++)
                {
                    dsKhachHang[j]=dsKhachHang[j+1];
                }
                dsKhachHang[soLuong-1]=null;
                soLuong--;
                return true;
            }

        }
        System.out.println("Khong tim thay ma KH nay");
        return false;
    }
    public KhachHang timKiemMa(String maKH)
    {
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i].getMaKH().equalsIgnoreCase(maKH))
            {
                return dsKhachHang[i];
            }
        }
        System.out.println("Khong tim thay ma kh nay.");
        return null;
    }
    public KhachHang timKiemTen(String hoten)
    {
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i].getHoTen().contains(hoten))
            {
                return dsKhachHang[i];
            }
        }
        System.out.println("Khong tim thay khach hang nay.");
        return null;
     }
    public KhachHang timKiemSDT(String sdt)
    {
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i].getSDT().contains(sdt))
                return dsKhachHang[i];
        }
        System.out.println("Khong tim thay khach hang nay.");
        return null;

    }
    public void timKiem()
    {
        int k;
        do{
            System.out.println("1.Tim kiem theo ma KH.");
            System.out.println("2.Tim kiem theo ten KH.");
            System.out.println("3.Tim kiem theo sdt KH.");
            System.out.println("  0. Quay lai");
            k=Main.docSoNguyen("Lua chon cua ban: ");

            switch (k) {
                case 1:
                    {
                        KhachHang kh=timKiemMa(Main.docChuoi("Nhap vao ma KH: "));
                        if (kh != null) {
                            kh.hienThiThongTin();
                        }
                        break;
                    }
                case 2:
                    {
                        KhachHang kh=timKiemTen(Main.docChuoi("Nhap vao ho ten KH: "));
                        if (kh != null) {
                            kh.hienThiThongTin();
                        }
                        break;
                    }
                case 3:
                    {
                        KhachHang kh=timKiemSDT(Main.docChuoi("Nhap vao SDT KH: "));
                        if (kh != null) {
                            kh.hienThiThongTin();
                        }
                        break;
                    }
                case 0:return;
                default:System.out.println("Lua chon khong hop le!");
            }
        }while(k!=0);
    }
    public void thongKeTheoMa(String maKH)
    {
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i].getMaKH().contains(maKH))
            {
                dsKhachHang[i].hienThiThongTin();
            }
        }
    }
    
    public void thongKeTheoTen(String hoTen)
    {
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i].getHoTen().contains(hoTen))
            {
                dsKhachHang[i].hienThiThongTin();
            }
        }
    }
    public void thongKeTheoSDT(String sdt)
    {
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i].getSDT().contains(sdt))
            {
                dsKhachHang[i].hienThiThongTin();
            }
        }
    }
    public void thongKe()
    {
        int p;
        do{
            GiaoDienConsole.inTieuDe("THONG KE KHACH HANG");
            System.out.println("  1. Tim theo ma khach hang");
            System.out.println("  2. Tim theo ten khach hang");
            System.out.println("  3. Tim theo so dien thoai");
            System.out.println("  0. Quay lai");
            p=Main.docSoNguyen("Lua chon cua ban: ");
            switch (p) {
                case 1:
                    thongKeTheoMa(Main.docChuoi("Nhap vao ma KH :"));
                    break;
                case 2:
                    thongKeTheoTen(Main.docChuoi("Nhap vao ten KH :"));
                    break;
                case 3:
                    thongKeTheoSDT(Main.docChuoi("Nhap vao sdt KH :"));
                    break;
                case 0:return;
                default:System.out.println("Lua chon khong hop le!");
            }
        }while(p!=0);
    }
    public void napDuLieu(KhachHang[] dsKH)
    {
        for (int i=0;i<soLuong;i++) dsKhachHang[i]=null;
            soLuong=0;
            if(dsKH==null) return;
            for (int i=0;i<dsKH.length;i++) {
                if(dsKH[i]==null)continue;
                if(soLuong==MAX)
                {
                    System.out.println("Danh sach da day,bo qua mang du.");
                    break;
                }
                dsKhachHang[soLuong++]=dsKH[i];
            }
    }
    public KhachHang[] getDs()
    {
        return dsKhachHang;
    }
    public int getSoLuong()
    {
        return soLuong;
    }



    public void menu()
    {
        int chon;
        do{
            GiaoDienConsole.inTieuDe("QUAN LY KHACH HANG");
            System.out.println("  1. Them khach hang");
            System.out.println("  2. Them nhieu khach hang");
            System.out.println("  3. Sua khach hang");
            System.out.println("  4. Xoa khach hang");
            System.out.println("  5. Tim khach hang");
            System.out.println("  6. Thong ke khach hang");
            System.out.println("  0. Quay lai");
            chon=Main.docSoNguyen("Lua chon cua ban: ");
            switch (chon) {
                case 1:nhapKhachHang();break;
                case 2:themNKhachHang(Main.docSoNguyen("Nhap vao N khach hang : "));break;
                case 3:suaTheoMa(Main.docChuoi("Nhap vao ma khach hang:"));break;
                case 4:xoaTheoMa(Main.docChuoi("Nhap vao ma khach hang:"));break;
                case 5:timKiem();break;
                case 6:thongKe();break;
                case 0:return;
                default:System.out.println("Lua chon khong hop le!");
            }
        }
        while(chon!=0);
    }


    public KhachHang timKiem(String ma) { return timKiemMa(ma); }
}