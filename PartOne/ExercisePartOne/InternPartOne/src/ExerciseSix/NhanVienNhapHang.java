package ExerciseSix;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NhanVienNhapHang extends NhanVien{
    private int thamNien;

    public NhanVienNhapHang(String maNhanVien, ExerciseSix.GioiTinh gioiTinh, Date ngayLamViec, int thamNien) {
        super(maNhanVien, gioiTinh, ngayLamViec);
        this.thamNien = thamNien;
    }

    public int getThamNien() {
        return thamNien;
    }

    public void setThamNien(int thamNien) {
        this.thamNien = thamNien;
    }
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return getMaNhanVien()
                + gioiTinh + "-"
                + dateFormat.format(ngayLamViec) + "-"
                + thamNien;
    }
}