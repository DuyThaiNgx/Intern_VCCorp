package ExerciseSix;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NhanVienBanHang extends NhanVien{
    private CaDangKy caDangKy;

    public CaDangKy getCaDangKy() {
        return caDangKy;
    }

    public void setCaDangKy(CaDangKy caDangKy) {
        this.caDangKy = caDangKy;
    }

    public NhanVienBanHang(String maNhanVien, ExerciseSix.GioiTinh gioiTinh, Date ngayLamViec, CaDangKy caDangKy) {
        super(maNhanVien, gioiTinh, ngayLamViec);
        this.caDangKy = caDangKy;
    }
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return getMaNhanVien() +"-"
                + gioiTinh + "-"
                + dateFormat.format(ngayLamViec) + "-"
                + caDangKy;
    }

}