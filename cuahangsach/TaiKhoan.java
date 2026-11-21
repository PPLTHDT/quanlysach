package cuahangsach;
public class TaiKhoan {
    private String username;
    private String matKhau;
    private VaiTro vaiTro;
    private String maNguoiDung; // maNV hoac maQL co san trong nhanvien.txt / quanly.txt

    public TaiKhoan() {
    }

    
    public TaiKhoan(String username, String matKhau, VaiTro vaiTro, String maNguoiDung) {
        this.username = username;
        this.matKhau=matKhau;
        this.vaiTro = vaiTro;
        this.maNguoiDung = maNguoiDung;
        datMatKhau(matKhau);
    }

    public String getUsername() { return username; }
    public String getMatKhau() { return matKhau; }
    public VaiTro getVaiTro() { return vaiTro; }
    public String getMaNguoiDung() { return maNguoiDung; }

    public void datMatKhau(String matKhau) {
        this.matKhau=matKhau;
    }

    public boolean kiemTraMatKhau(String matKhau) {
    return this.matKhau != null && this.matKhau.equals(matKhau);
}


    
}
