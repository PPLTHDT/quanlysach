package doan;
import java.util.Scanner;

public class quanlynhansu{
    private nhanvien[] dsNV=new nhanvien[MAX_NV];
    private quanly[] dsQL=new quanly[MAX_QL];
    private int soluongNV=0,soluongQL=0;
    public static final int MAX_NV=20;
    public static final int MAX_QL=10;
    
    public void nhapNhanVien()
    {
        nhanvien nv=new nhanvien();
        nv.setMaNguoi();
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
    public void xuat(nhanvien nv)
    {
        nv.hienThiThongTin();
    }
    public boolean themNhanVien(nhanvien nv)
    {
        if(nv==null||soluongNV==MAX_NV)
        {
            return false;
        }
        dsNV[soluongNV++]=nv;
        return true;
    }
    public boolean suaNhanVien(String maNV)
    {
        
        for (int i=0;i<soluongNV;i++) {
            if(dsNV[i].getMaNV().equals(maNV))
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
                    case 1:dsNV[i].setMaNguoi();break;
                    case 2:dsNV[i].setHoTen();;break;
                    case 3:dsNV[i].setSDT();;break;
                    case 4:dsNV[i].setEmail();;break;
                    case 5:dsNV[i].setDiaChi();;break;
                    case 6:dsNV[i].setMaNV();;break;
                    case 7:dsNV[i].setChucVu();;break;
                    case 8:dsNV[i].setLuongCoBan();;break;
                    case 9:dsNV[i].setSoGioLam();;break;
                
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
            if(dsNV[i].getMaNV().equals(maNV))
            {
                for(int j=i;j<soluongNV-1;j++)
                {
                    dsNV[j]=dsNV[j+1];
                }
                dsNV[soluongNV-1]=null;
                soluongNV--;
                return true;
            }
        }
        return false;
    }
    public nhanvien timKiemNV(String maNV)
    {
        for(int i=0;i<soluongNV;i++)

            {
                if(dsNV[i].getMaNV().equals(maNV))
                    return null;
            }
        return null;
    }
    public double tongLuongNV()
    {
        double tongluong=0;
        for(int i=0;i<soluongNV;i++)
        {
            tongluong+=dsNV[i].tinhLuong();
        }
        return tongluong;
    }

    public void nhapQuanLy()
    {
        quanly ql=new quanly();
        ql.setMaNguoi();
        ql.setHoTen();
        ql.setSDT();
        ql.setEmail();
        ql.setDiaChi();
        ql.setMaQL();
        ql.setPhuCap();
        themQuanLy(ql);
    }
    public void xuatQuanLy(quanly ql)
    {
        ql.hienThiThongTin();
    }
    public boolean themQuanLy(quanly ql)
    {
        if(ql==null||soluongQL==MAX_QL)
        {
            return false;
        }
        dsQL[soluongQL++]=ql;
        return true;
    }
    public boolean xoaQuanLy(String maQL)
    {
        for(int i=0;i<soluongQL;i++)
        {
            if(dsQL[i].getMaQL().equals(maQL))
            {
                for(int j=i;j<soluongQL-1;j++)
                {
                    dsQL[j]=dsQL[j+1];
                }
                dsQL[soluongQL-1]=null;
                soluongQL--;
                return true;
            }
        }
        return false;
    }
    public boolean suaQuanLy(String maQL)
    {
        for (int i=0;i<soluongQL;i++) {
            if(dsQL[i].getMaQL().equals(maQL))
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
                    case 2:dsQL[i].setHoTen();;break;
                    case 3:dsQL[i].setSDT();;break;
                    case 1:dsQL[i].setMaNguoi();break;
                    case 4:dsQL[i].setEmail();;break;
                    case 5:dsQL[i].setDiaChi();;break;
                    case 6:dsQL[i].setMaQL();;;break;
                    case 7:dsQL[i].setPhuCap();;;break;
                
                    default:
                        break;
                }
                return true;

            }
        }
        return false;
    }
    public quanly timKiemQuanly(String maQL)
    {
        for(int i=0;i<soluongQL;i++)
        {
            if(dsQL[i].getMaQL().equals(maQL))
                return dsQL[i];
        }
        return null;
    }
    public double tongLuongQL()
    {
        double tongluong=0;
        for(int i=0;i<soluongQL;i++)
        {
            tongluong+=dsQL[i].tinhLuong();
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
            if(dsQL[i].getPhongBan().equals("ke toan"))
            {
                System.out.println(dsQL[i].getHoTen());
                qlkt++;
            }
        }
        System.out.println("Nhan vien : ");
        for (int i=0;i<soluongNV;i++) {
            if(dsNV[i].getChucVu().equals("ke toan"))
            {
                System.out.println(dsNV[i].getHoTen());
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
            if(dsQL[i].getPhongBan().equals("bao ve"))
            {
                System.out.println(dsQL[i].getHoTen());
                qlbv++;
            }   
        }
        System.out.println("Nhan vien : ");
        for (int i=0;i<soluongNV;i++) {
            if(dsNV[i].getChucVu().equals("bao ve"))
            {
                System.out.println(dsNV[i].getHoTen());
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
            if(dsQL[i].getPhongBan().equals("kho"))
            {
                System.out.println(dsQL[i].getHoTen());
                qlk++;
            }
            
        }

        System.out.println("Nhan vien : ");
        for (int i=0;i<soluongNV;i++) {
            if(dsNV[i].getChucVu().equals("kho"))
            {
                System.out.println(dsNV[i].getHoTen());
                nvk++;
            }
            
        }
        System.out.println("So luong quan ly :"+qlk);
        System.out.println("So luong nhan vien :"+nvk);
        System.out.println("-----------------------------------------");


    }
    public void napDuLieu(nhanvien[] dsnv,quanly[] dsql)
{
    Scanner sc=new Scanner(System.in);
    System.out.println("1.Nap du lieu nhan vien.");
    System.out.println("2.Nap du lieu quan ly.");
    System.out.println("Lua chon cua ban :");
    int k=sc.nextInt();
    switch (k) {
        case 1:
            for (int i=0;i<soluongNV;i++) dsNV[i]=null;
            soluongNV=0;
            if(dsnv==null) break;
            for (int i=0;i<dsnv.length;i++) {
                if(dsnv[i]==null)continue;
                if(soluongNV==MAX_NV)
                {
                    System.out.println("Danh sach da day,bo qua mang du.");
                    break;
                }
                dsNV[soluongNV++]=dsnv[i];
            }
            break;
        case 2:
            for (int i=0;i<soluongQL;i++) dsQL[i]=null;
            soluongQL=0;
            if(dsql==null) break;
            for (int i=0;i<dsql.length;i++) {
                if(dsql[i]==null)continue;
                if(soluongQL==MAX_QL)
                {
                    System.out.println("Danh sach da day,bo qua mang du.");
                    break;
                }
                dsQL[soluongQL++]=dsql[i];
            }
            break;
        default:
            break;
    }
}
    public static void main(String[] args)
    {
        quanlynhansu qlns=new quanlynhansu();
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
                    System.out.println(qlns.timKiemNV(a).getThongTin());
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
                    System.out.println(qlns.timKiemNV(a).getThongTin());
                    break;
                case 9:
                    qlns.thongKeNhanSu();break;
                case 0:return;
            }
        }
        while(chon!=0);
    }
}