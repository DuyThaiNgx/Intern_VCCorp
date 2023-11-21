package ExerciseSix;

import java.util.Date;

public class NhanVien {

    private String maNhanVien;
    public ExerciseSix.GioiTinh gioiTinh;
    protected Date ngayLamViec;

    public NhanVien(String maNhanVien, GioiTinh gioiTinh, Date ngayLamViec) {
        this.maNhanVien = maNhanVien;
        this.gioiTinh = gioiTinh;
        this.ngayLamViec = ngayLamViec;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public ExerciseSix.GioiTinh getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(ExerciseSix.GioiTinh gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(Date ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }


}
