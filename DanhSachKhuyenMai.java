package quanlysach;
public class DanhSachKhuyenMai{
    public static final int MAX = 100;
    private KhuyenMai[] dsKhuyenMai;
    private int soLuong;
    public DanhSachKhuyenMai(){
        dsKhuyenMai = new KhuyenMai[MAX];
        soLuong = 0;
    }
    public boolean them(KhuyenMai km){
        if(soLuong >= MAX){
            return false;
        }
        if( timKiem( km.getMaKM()) != null ){
            return false;
        }
        dsKhuyenMai[soLuong] = km;
        soLuong++;
        return true;
    }

    public boolean xoaTheoMa(String ma){
        for( int i = 0; i < soLuong; i++){
            if(dsKhuyenMai[i].getMaKM().equals(ma)){
                for( int j = i; j < soLuong - 1; j++){
                    dsKhuyenMai[j] = dsKhuyenMai[j+1];
                }
                dsKhuyenMai[soLuong-1] = null;
                soLuong--;
                return true;
            }
        }
        return false;
    }
    public KhuyenMai timKiem(String ma){
        for( int i = 0; i < soLuong; i++){
            if(dsKhuyenMai[i].getMaKM().equals(ma)){
                return dsKhuyenMai[i];
            }
        }
        return null;
    }
}