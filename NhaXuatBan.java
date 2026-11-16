package cuahangsach;
public class NhaXuatBan{
    private String maNXB;
    private String tenNXB;
    private String diaChi;
    public NhaXuatBan(){

    }
    public NhaXuatBan( String maNXB, String tenNXB, String diaChi){
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
        this.diaChi = diaChi;
    }
    public String getMaNXB(){
        return maNXB;
    }
    public void setMaNXB( String maNXB){
        this.maNXB = maNXB;
    }
    public String getTenNXB(){
        return tenNXB;
    }
    public void setTenNXB( String tenNXB){
        this.tenNXB = tenNXB; 
    }
    public String getDiaChi(){
        return diaChi;
    }
    public void setDiaChi( String diaChi){
        this.diaChi = diaChi;
    }
    public void hienThi(){
        GiaoDienConsole.inTieuDe("THONG TIN NHA XUAT BAN");
        GiaoDienConsole.inMuc("Ma nha xuat ban", maNXB);
        GiaoDienConsole.inMuc("Ten nha xuat ban", tenNXB);
        GiaoDienConsole.inMuc("Dia chi", diaChi);
        GiaoDienConsole.inPhanCach();
    }
}