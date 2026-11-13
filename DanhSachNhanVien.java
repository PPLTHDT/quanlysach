package doan;
import java.util.Scanner;

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
        themNhanVien(nv);
    }
    public void xuatNhanVien(NhanVien nv)
    {
        nv.hienThiThongTin();
    }
    public boolean themNhanVien(NhanVien nv)
    {
        if(nv==null||soluongNV==MAX_NV)
        {
            return false;
        }
        dsNhanVien[soluongNV++]=nv;
        return true;
    }
    public boolean suaNhanVien(String maNV)
    {
        
        for (int i=0;i<soluongNV;i++) {
            if(dsNhanVien[i].getMaNV().equals(maNV))
            {
                Scanner sc=new Scanner(System.in);
                System.out.println("1.Sua ma nguoi.");
                System.out.println("2.Sua ho ten.");
                System.out.println("3.Sua sdt.");
                System.out.println("4.Sua email.");
                System.out.println("5.Sua dia chi.");
                System.out.println("6.Sua ma nhan vien.");
                System.out.println("7.Sua chuc vu.");
                System.out.println("8.Sua luong co ban.");
                System.out.println("9.Sua so gio lam.");
                System.out.println("Lua chon cua ban:");
                int k=sc.nextInt();
                switch (k) {
                    case 1:dsNhanVien[i].setCCCD();break;
                    case 2:dsNhanVien[i].setHoTen();;break;
                    case 3:dsNhanVien[i].setSDT();;break;
                    case 4:dsNhanVien[i].setEmail();;break;
                    case 5:dsNhanVien[i].setDiaChi();;break;
                    case 6:dsNhanVien[i].setMaNV();;break;
                    case 7:dsNhanVien[i].setChucVu();;break;
                    case 8:dsNhanVien[i].setLuongCoBan();;break;
                    case 9:dsNhanVien[i].setSoGioLam();;break;
                
                    default:
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
            if(dsNhanVien[i].getMaNV().equals(maNV))
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
    public NhanVien timKiemNV(String maNV)
    {
        for(int i=0;i<soluongNV;i++)

            {
                if(dsNhanVien[i].getMaNV().equals(maNV))
                    return dsNhanVien[i];
            }
        return null;
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
        ql.setPhuCap();
        themQuanLy(ql);
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
        dsQuanLy[soluongQL++]=ql;
        return true;
    }
    public boolean xoaQuanLy(String maQL)
    {
        for(int i=0;i<soluongQL;i++)
        {
            if(dsQuanLy[i].getMaQL().equals(maQL))
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
            if(dsQuanLy[i].getMaQL().equals(maQL))
            {
                Scanner sc=new Scanner(System.in);
                System.out.println("1.Sua ma nguoi.");
                System.out.println("2.Sua ho ten.");
                System.out.println("3.Sua sdt.");
                System.out.println("4.Sua email.");
                System.out.println("5.Sua dia chi.");
                System.out.println("6.Sua ma quan ly.");
                System.out.println("7.Sua phu cap.");
                System.out.println("Lua chon cua ban:");
                int k=sc.nextInt();
                switch (k) {
                    case 2:dsQuanLy[i].setHoTen();;break;
                    case 3:dsQuanLy[i].setSDT();;break;
                    case 1:dsQuanLy[i].setCCCD();break;
                    case 4:dsQuanLy[i].setEmail();;break;
                    case 5:dsQuanLy[i].setDiaChi();;break;
                    case 6:dsQuanLy[i].setMaQL();;;break;
                    case 7:dsQuanLy[i].setPhuCap();;;break;
                
                    default:
                        break;
                }
                return true;

            }
        }
        return false;
    }
    public QuanLy timKiemQuanly(String maQL)
    {
        for(int i=0;i<soluongQL;i++)
        {
            if(dsQuanLy[i].getMaQL().equals(maQL))
                return dsQuanLy[i];
        }
        return null;
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
        System.out.println("*Thong tin chung cua nhan vien : ");
        System.out.println("-So luong nhan vien : "+soluongNV);
        System.out.println("-Tong luong : "+tongLuongNV());
        System.out.println("*Thong tin chung cua quan ly : ");
        System.out.println("-So luong quan ly : "+soluongQL);
        System.out.println("-Tong luong : "+tongLuongQL());
        

        System.out.println("Phong ban ke toan.");
        System.out.println("Quan ly :");
        int qlkt=0,nvkt=0;
        for (int i=0;i<soluongQL;i++) {
            if(dsQuanLy[i].getPhongBan().equals("ke toan"))
            {
                System.out.println(dsQuanLy[i].getHoTen());
                qlkt++;
            }
        }
        System.out.println("Nhan vien : ");
        for (int i=0;i<soluongNV;i++) {
            if(dsNhanVien[i].getChucVu().equals("ke toan"))
            {
                System.out.println(dsNhanVien[i].getHoTen());
                nvkt++;
            }  
        }
        System.out.println("So luong quan ly :"+qlkt);
        System.out.println("So luong nhan vien :"+nvkt);
        System.out.println("-----------------------------------------");
        System.out.println("Phong ban bao ve.");
        System.out.println("Quan ly :");
        int qlbv=0,nvbv=0;
        for (int i=0;i<soluongQL;i++) {
            if(dsQuanLy[i].getPhongBan().equals("bao ve"))
            {
                System.out.println(dsQuanLy[i].getHoTen());
                qlbv++;
            }   
        }
        System.out.println("Nhan vien : ");
        for (int i=0;i<soluongNV;i++) {
            if(dsNhanVien[i].getChucVu().equals("bao ve"))
            {
                System.out.println(dsNhanVien[i].getHoTen());
                nvbv++;
            }
        }
        System.out.println("So luong quan ly :"+qlbv);
        System.out.println("So luong nhan vien :"+nvbv);
        System.out.println("-----------------------------------------");
        System.out.println("Phong ban kho.");
        System.out.println("Quan ly :");
        int qlk=0,nvk=0;
        for (int i=0;i<soluongQL;i++) {
            if(dsQuanLy[i].getPhongBan().equals("kho"))
            {
                System.out.println(dsQuanLy[i].getHoTen());
                qlk++;
            }
            
        }

        System.out.println("Nhan vien : ");
        for (int i=0;i<soluongNV;i++) {
            if(dsNhanVien[i].getChucVu().equals("kho"))
            {
                System.out.println(dsNhanVien[i].getHoTen());
                nvk++;
            }
            
        }
        System.out.println("So luong quan ly :"+qlk);
        System.out.println("So luong nhan vien :"+nvk);
        System.out.println("-----------------------------------------");


    }
    public void napDuLieu(NhanVien[] dsnv,QuanLy[] dsql)
{
    Scanner sc=new Scanner(System.in);
    System.out.println("1.Nap du lieu nhan vien.");
    System.out.println("2.Nap du lieu quan ly.");
    System.out.println("Lua chon cua ban :");
    int k=sc.nextInt();
    switch (k) {
        case 1:
            for (int i=0;i<soluongNV;i++) dsNhanVien[i]=null;
            soluongNV=0;
            if(dsnv==null) break;
            for (int i=0;i<dsnv.length;i++) {
                if(dsnv[i]==null)continue;
                if(soluongNV==MAX_NV)
                {
                    System.out.println("Danh sach da day,bo qua mang du.");
                    break;
                }
                dsNhanVien[soluongNV++]=dsnv[i];
            }
            break;
        case 2:
            for (int i=0;i<soluongQL;i++) dsQuanLy[i]=null;
            soluongQL=0;
            if(dsql==null) break;
            for (int i=0;i<dsql.length;i++) {
                if(dsql[i]==null)continue;
                if(soluongQL==MAX_QL)
                {
                    System.out.println("Danh sach da day,bo qua mang du.");
                    break;
                }
                dsQuanLy[soluongQL++]=dsql[i];
            }
            break;
        default:
            break;
    }
}
    public void xuatDSNhanVien()
    {
        for(int i=0;i<soluongNV;i++)
        {
            xuatNhanVien(dsNhanVien[i]);
        }
    }
    public void xuatDSQuanLy()
    {
        for(int i=0;i<soluongQL;i++)
        {
            xuatQuanLy(dsQuanLy[i]);
        }
    }
    public static void main(String[] args)
    {
        DanhSachNhanVien qlns=new DanhSachNhanVien();
        Scanner sc=new Scanner(System.in);
        int chon;
        do{
            System.out.println("1.Them nhan vien.");
            System.out.println("2.Sua nhan vien.");
            System.out.println("3.Xoa nhan vien.");
            System.out.println("4.Tim nhan vien.");
            System.out.println("5.Them quan ly.");
            System.out.println("6.Sua quan ly.");
            System.out.println("7.Xoa quan ly.");
            System.out.println("8.Tim quan ly.");
            System.out.println("9.Thong ke nhan su.");
            System.out.println("10.Xuat nhan vien.");
            System.out.println("11.Xuat quan ly.");
            System.out.println("0.OUT.");
            System.out.println("Lua chon cua ban :");
            chon=sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:qlns.nhapNhanVien();break;
                case 2:
                    System.out.println("Nhap vao ma nhan vien:");
                    String a=sc.nextLine();
                    qlns.suaNhanVien(a);
                    break;
                case 3:
                    System.out.println("Nhap vao ma nhan vien:");
                    a=sc.nextLine();
                    qlns.xoaNhanVien(a);
                    break;
                case 4:
                    System.out.println("Nhap vao ma nhan vien:");
                    a=sc.nextLine();
                    NhanVien nv = qlns.timKiemNV(a);
                    if (nv == null) System.out.println("Khong tim thay.");
                    else nv.hienThiThongTin();
                    break;
                case 5:qlns.nhapQuanLy();break; 
                case 6:
                    System.out.println("Nhap vao ma quan ly:");
                    a=sc.nextLine();
                    qlns.suaQuanLy(a);
                    break;
                case 7:
                    System.out.println("Nhap vao ma quan ly:");
                    a=sc.nextLine();
                    qlns.xoaQuanLy(a);
                    break;
                case 8:
                    System.out.println("Nhap vao ma quan ly:");
                    a=sc.nextLine();
                    QuanLy ql = qlns.timKiemQuanly(a);
                    if (ql == null) System.out.println("Khong tim thay.");
                    else ql.hienThiThongTin();  
                    break;
                case 9:
                    qlns.thongKeNhanSu();break;
                case 10:
                    qlns.xuatDSNhanVien();
                    break;
                case 11:
                    qlns.xuatDSQuanLy();    
                case 0:return;
            }
        }
        while(chon!=0);
    }
}
