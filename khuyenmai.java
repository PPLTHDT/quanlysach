import java.time.LocalDate;

public class khuyenmai {
    private String maKM;
    private String tenKM;
    private double phanTramGiam;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    public boolean hieuLuc()
    {
        if(getNgayLap()<=ngayKetThuc)
            return 1;
        else
            return 0;
    }
    public double tinhTienGiam(double tongTien)
    {
        return tongtien*phanTramGiam;
    }
}
