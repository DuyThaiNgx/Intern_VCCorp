package ExerciseSix;

import java.text.SimpleDateFormat;

public class KhachHang{
    private String maKhachHang;
    private GioiTinh gioiTinh;
    private int doTuoi;

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public GioiTinh getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(GioiTinh gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getDoTuoi() {
        return doTuoi;
    }

    public void setDoTuoi(int doTuoi) {
        this.doTuoi = doTuoi;
    }

    public KhachHang(String maKhachHang, GioiTinh gioiTinh, int doTuoi) {
        this.maKhachHang = maKhachHang;
        this.gioiTinh = gioiTinh;
        this.doTuoi = doTuoi;
    }
    @Override
    public String toString() {
        return "Ma KH: " + maKhachHang +
                ", GioiTinh: " + gioiTinh +
                ", DoTuoi: " + doTuoi;
    }
}