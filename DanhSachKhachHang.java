package doan;

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
    public KhachHang timKiem(String maKH)
    {
        for(int i=0;i<soLuong;i++)
        {
            if(dsKhachHang[i].getMaKH().equals(maKH))
            {
                return dsKhachHang[i];
            }
        }
        System.out.println("Khong tim thay ma kh nay.");
        return null;
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


    public static void main(String[] args)
    {
        DanhSachKhachHang dskh=new DanhSachKhachHang();
        Scanner sc=new Scanner(System.in);
        int chon;
        do{
            System.out.println("1.Them khach hang.");
            System.out.println("2.Sua khach hang.");
            System.out.println("3.Xoa khach hang.");
            System.out.println("4.Tim khach hang.");
            System.out.println("5.Thong ke khach hang.");
            System.out.println("0.OUT.");
            System.out.println("Lua chon cua ban :");
            chon=sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:dskh.nhapKhachHang();break;
                case 2:
                    System.out.println("Nhap vao ma khach hang:");
                    String a=sc.nextLine();
                    dskh.suaTheoMa(a);
                    break;
                case 3:
                    System.out.println("Nhap vao ma khach hang:");
                    a=sc.nextLine();
                    dskh.xoaTheoMa(a);
                    break;
                case 4:
                    System.out.println("Nhap vao ma khach hang:");
                    a=sc.nextLine();
                    KhachHang kh = dskh.timKiem(a);
                    if (kh == null) System.out.println("Khong tim thay.");
                    else kh.hienThiThongTin();
                    break;
                case 5:
                    dskh.thongKe();break;   
                case 0:return;
            }
        }
        while(chon!=0);
    }

}