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
        else
        {
            dsKhachHang[soLuong++]=a;
            return true;
        }
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
        if (kh != null) {
            kh.hienThiThongTin();
        }
    }

    public void xuat()
    {
        for(int i=0;i<soLuong;i++)
        {
            xuatKhachHang(dsKhachHang[i]);
        }
    }

    public boolean suaTheoMa(String maKH)
    {
        if (maKH == null) {
            return false;
        }
        for (int i=0;i<soLuong;i++) {
            if(dsKhachHang[i] != null &&
            dsKhachHang[i].getMaKH() != null &&
            dsKhachHang[i].getMaKH().trim().equalsIgnoreCase(maKH.trim()))
            {
                System.out.println("1.Sua can cuoc cong dan.");
                System.out.println("2.Sua ho ten.");
                System.out.println("3.Sua sdt.");
                System.out.println("4.Sua email.");
                System.out.println("5.Sua dia chi.");
                System.out.println("6.Sua ma khach hang.");
                System.out.println("7.Sua diem tich luy.");
                int k = Main.docSoNguyen("Lua chon cua ban: ");
                switch (k) {
                    case 1:dsKhachHang[i].setCCCD();break;
                    case 2:dsKhachHang[i].setHoTen();break;
                    case 3:dsKhachHang[i].setSDT();break;
                    case 4:dsKhachHang[i].setEmail();break;
                    case 5:dsKhachHang[i].setDiaChi();break;
                    case 6:dsKhachHang[i].setMaKH();break;
                    case 7:dsKhachHang[i].setDiemTichLuy();break;
                
                    default:
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
        if (maKH == null) {
            return false;
        }
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i] != null &&
            dsKhachHang[i].getMaKH() != null &&
            dsKhachHang[i].getMaKH().trim().equalsIgnoreCase(maKH.trim()))
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
        if (maKH == null) {
            System.out.println("Khong tim thay ma kh nay.");
            return null;
        }
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i] != null &&
            dsKhachHang[i].getMaKH() != null &&
            dsKhachHang[i].getMaKH().trim().equalsIgnoreCase(maKH.trim()))
            {
                return dsKhachHang[i];
            }
        }
        System.out.println("Khong tim thay ma kh nay.");
        return null;
    }
    public KhachHang timKiemTen(String hoten)
    {
        if (hoten == null) {
            return null;
        }
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i] != null &&
            dsKhachHang[i].getHoTen() != null &&
            dsKhachHang[i].getHoTen().contains(hoten))
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
            if(dsKhachHang[i] != null &&
            dsKhachHang[i].getSDT() != null &&
            dsKhachHang[i].getSDT().equals(sdt))
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
            System.out.println("0.OUT.");
            k = Main.docSoNguyen("Lua chon cua ban: ");

            switch (k) {
                case 1:
                    {
                        String ma = Main.docChuoi("Nhap vao ma KH: ");
                        KhachHang kh = timKiemMa(ma);
                        if (kh != null) {
                        kh.hienThiThongTin();
                        }
                        break;
                    }
                case 2:
                    {
                        String ten = Main.docChuoi("Nhap vao ho ten KH: ");
                        KhachHang kh=timKiemTen(ten);
                        if (kh != null) {
                        kh.hienThiThongTin();
                        }
                        break;
                    }
                case 3:
                    {
                        String s = Main.docChuoi("Nhap vao SDT KH: ");
                        KhachHang kh=timKiemSDT(s);
                        if (kh != null) {
                        kh.hienThiThongTin();
                        }
                        break;
                    }
                case 0:return;
            }
        }while(k!=0);
    }
    public void thongKe()
    {
        for(int i=0;i<soLuong;i++)
        {
            xuatKhachHang(dsKhachHang[i]);
        }
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
        DanhSachKhachHang dskh=this;
        int chon;
        do{
            System.out.println("1.Them khach hang.");
            System.out.println("2.Them vao N khach hang.");
            System.out.println("3.Sua khach hang.");
            System.out.println("4.Xoa khach hang.");
            System.out.println("5.Tim khach hang.");
            System.out.println("6.Thong ke khach hang.");
            System.out.println("0.OUT.");
            chon = Main.docSoNguyen("Lua chon cua ban: ");
            switch (chon) {
                case 1:dskh.nhapKhachHang();break;
                case 2:
                    int c = Main.docSoNguyen("Nhap vao N khach hang: ");
                    dskh.themNKhachHang(c);
                    break;
                case 3:
                    String a = Main.docChuoi("Nhap vao ma khach hang: ");
                    dskh.suaTheoMa(a);
                    break;
                case 4:
                    a = Main.docChuoi("Nhap vao ma khach hang: ");
                    dskh.xoaTheoMa(a);
                    break;
                case 5:
                    dskh.timKiem();
                    break;
                case 6:
                    dskh.thongKe();break;   
                case 0:return;
            }
        }
        while(chon!=0);
    }
        public KhachHang timKiem(String ma) { return timKiemMa(ma); }

}
