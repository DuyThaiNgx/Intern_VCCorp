package ExerciseSix;

import java.text.SimpleDateFormat;

public class MatHang{
    private String maHangHoa;
    private String tenHangHoa;
    private String phanLoai;
    private double gia;

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

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public MatHang(String maHangHoa, String tenHangHoa, String phanLoai, double gia) {
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
