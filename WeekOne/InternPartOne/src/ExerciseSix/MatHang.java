package ExerciseSix;

import java.text.SimpleDateFormat;

public class MatHang{
    private String maHangHoa;
    private String tenHangHoa;
    private String phanLoai;
    private String gia;

    public String getMaHangHoa() {
        return maHangHoa;
    }

    public void setMaHangHoa(String maHangHoa) {
        this.maHangHoa = maHangHoa;
    }

    public String getTenHangHoa() {
        return tenHangHoa;
    }

    public void setTenHangHoa(String tenHangHoa) {
        this.tenHangHoa = tenHangHoa;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public MatHang(String maHangHoa, String tenHangHoa, String phanLoai, String gia) {
        this.maHangHoa = maHangHoa;
        this.tenHangHoa = tenHangHoa;
        this.phanLoai = phanLoai;
        this.gia = gia;
    }
    @Override
    public String toString() {
        return "MaHH: " + maHangHoa +
                ", TenHH: " + tenHangHoa +
                ", PhanLoai: " + phanLoai +
                ", Gia: " + gia;
    }
}
