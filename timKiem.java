public abstract class timKiem {
    public static final int Max =100;
    protected  Object[] ds = new Object[Max];
    protected  int soLuong =0;

    protected abstract String layMa(Object o);
    protected abstract void Xuat(Object o);

    public Object timKiemMa(String ma){
        for(int i=0;i<soLuong ;i++){
            if(layMa(ds[i]).equalsIgnoreCase(ma)){
                return ds[i];
            }
        }
        return null;
    }
    public boolean them(Object x){
        if(x== null || soLuong >= Max){
            return false;
        }
        if(timKiemMa(layMa(x))!= null){
            return false;
        }
        ds[soLuong]=x;
        soLuong++;
        return true;
    }
    public int getSoLuong(){
        return soLuong;
    }
}
