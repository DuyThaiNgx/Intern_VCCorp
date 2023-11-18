package ExerciseSix;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HoaDon{
    private String maHoaDon;
    private NhanVienBanHang nhanVienBanHang;
    private KhachHang khachHang;
    private List<MatHang> matHang;
    private double tongGia;
    private Date ngayMua;

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public NhanVienBanHang getNhanVienBanHang() {
        return nhanVienBanHang;
    }

    public void setNhanVienBanHang(NhanVienBanHang nhanVienBanHang) {
        this.nhanVienBanHang = nhanVienBanHang;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public List<MatHang> getMatHang() {
        return matHang;
    }

    public void setMatHang(ArrayList<MatHang> matHang) {
        this.matHang = matHang;
    }

    public void setMatHang(List<MatHang> matHang) {
        this.matHang = matHang;
    }

    public double getTongGia() {
        return tongGia;
    }

    public void setTongGia(double tongGia) {
        this.tongGia = tongGia;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public HoaDon(String maHoaDon, NhanVienBanHang nhanVienBanHang, KhachHang khachHang, List<MatHang> matHang, double tongGia, Date ngayMua) {
        this.maHoaDon = maHoaDon;
        this.nhanVienBanHang = nhanVienBanHang;
        this.khachHang = khachHang;
        this.matHang = matHang;
        this.tongGia = tongGia;
        this.ngayMua = ngayMua;
    }
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dsMatHang = "";
        for(MatHang mh: matHang){
            dsMatHang += mh.toString();
        }
        return "MaHoaDon: " + maHoaDon +
                ", NhanVienBH: " + nhanVienBanHang +
                ", KhachHang: " + khachHang +
                ", MatHang: " +dsMatHang+
                ", TongGia: "+tongGia+
                ", NgayMua: "+dateFormat.format(ngayMua);
    }
}
