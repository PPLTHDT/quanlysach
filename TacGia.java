package cuahangsach;
public class TacGia{
    private String maTacGia;
    private String tenTacGia;
public TacGia(){

}
public TacGia( String maTacGia, String tenTacGia ){
    this.maTacGia = maTacGia;
    this.tenTacGia = tenTacGia;
}
public String getMaTacGia(){
    return maTacGia;
}
public void setMaTacGia(String maTacGia){
    this.maTacGia = maTacGia;
}
public String getTenTacGia(){
    return tenTacGia;

}
public void setTenTacGia(String tenTacGia){
    this.tenTacGia = tenTacGia;
}
public void hienThi(){
    GiaoDienConsole.inTieuDe("THONG TIN TAC GIA");
    GiaoDienConsole.inMuc("Ma tac gia", maTacGia);
    GiaoDienConsole.inMuc("Ten tac gia", tenTacGia);
    GiaoDienConsole.inPhanCach();
}
}