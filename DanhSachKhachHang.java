package cuahangsach;

import java.util.Scanner;

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
        kh.hienThiThongTin();
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
        for (int i=0;i<soLuong;i++) {
            if(dsKhachHang[i].getMaKH().equals(maKH))
            {
                Scanner sc=new Scanner(System.in);
                System.out.println("1.Sua can cuoc cong dan.");
                System.out.println("2.Sua ho ten.");
                System.out.println("3.Sua sdt.");
                System.out.println("4.Sua email.");
                System.out.println("5.Sua dia chi.");
                System.out.println("6.Sua ma khach hang.");
                System.out.println("7.Sua diem tich luy.");
                System.out.println("Lua chon cua ban:");
                int k=sc.nextInt();
                switch (k) {
                    case 1:dsKhachHang[i].setCCCD();break;
                    case 2:dsKhachHang[i].setHoTen();;break;
                    case 3:dsKhachHang[i].setSDT();;break;
                    case 4:dsKhachHang[i].setEmail();;break;
                    case 5:dsKhachHang[i].setDiaChi();;break;
                    case 6:dsKhachHang[i].setMaKH();;break;
                    case 7:dsKhachHang[i].setDiemTichLuy();;break;
                
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
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i].getMaKH().equals(maKH))
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
            if(dsKhachHang[i].getMaKH().contains(maKH))
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
            System.out.println("0.OUT.");
            System.out.println("Lua chon cua ban:");
            Scanner sc=new Scanner(System.in);
            k=sc.nextInt();
            sc.nextLine();

            switch (k) {
                case 1:
                    {
                        System.out.println("Nhap vao ma KH:");
                        String ma=sc.nextLine();
                        System.out.println(timKiemMa(ma));
                        break;
                    }
                case 2:
                    {
                        System.out.println("Nhap vao ho ten KH:");
                        String ten=sc.nextLine();
                        System.out.println(timKiemTen(ten));
                        break;
                    }
                case 3:
                    {
                        System.out.println("Nhap vao SDT KH:");
                        String s=sc.nextLine();
                        System.out.println(timKiemSDT(s)); 
                        break;
                    }
                case 0:return;
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
        Scanner sc=new Scanner(System.in);
        int p;
        do{
            System.out.println("1.Thong ke KH theo ma.");
            System.out.println("2.Thong ke KH theo ten.");
            System.out.println("3.Thong ke KH theo sdt. ");
            System.out.println("0.OUT. ");
            System.out.println("Lua chon cua ban :");
            p=sc.nextInt();
            sc.nextLine();
            switch (p) {
                case 1:
                    System.out.println("Nhap vao ma KH :");
                    String ma=sc.nextLine();
                    thongKeTheoMa(ma);
                    break;
                case 2:
                    System.out.println("Nhap vao ten KH :");
                    String ten=sc.nextLine();
                    thongKeTheoTen(ten);
                    break;
                case 3:
                    System.out.println("Nhap vao sdt KH :");
                    String sdt=sc.nextLine();
                    thongKeTheoSDT(sdt);
                    break;
                case 0:return;
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
        DanhSachKhachHang dskh=new DanhSachKhachHang();
        Scanner sc=new Scanner(System.in);
        int chon;
        do{
            System.out.println("1.Them khach hang.");
            System.out.println("2.Them vao N khach hang.");
            System.out.println("3.Sua khach hang.");
            System.out.println("4.Xoa khach hang.");
            System.out.println("5.Tim khach hang.");
            System.out.println("6.Thong ke khach hang.");
            System.out.println("0.OUT.");
            System.out.println("Lua chon cua ban :");
            chon=sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:dskh.nhapKhachHang();break;
                case 2:System.out.println("Nhap vao N khach hang : ");
                int c=sc.nextInt();
                dskh.themNKhachHang(c);
                break;
                case 3:
                    System.out.println("Nhap vao ma khach hang:");
                    String a=sc.nextLine();
                    dskh.suaTheoMa(a);
                    break;
                case 4:
                    System.out.println("Nhap vao ma khach hang:");
                    a=sc.nextLine();
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

}