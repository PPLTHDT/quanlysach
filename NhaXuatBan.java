package quanlysach;
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
        System.out.println("Ma nha xuat ban: " + maNXB);
        System.out.println("Ten nha xuat ban: "+ tenNXB);
        System.out.println("Dia chi: "+ diaChi);
    }
}