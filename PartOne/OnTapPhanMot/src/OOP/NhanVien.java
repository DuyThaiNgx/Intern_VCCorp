package OOP;

public class NhanVien {
    public int tuoi;
    protected double luongCoBan;
    public String tenNhanVien;
//    Constructor
//    public NhanVien (String tenNhanVien, double luongCoBan, int tuoi){
//        this.tenNhanVien = tenNhanVien;
//        this.luongCoBan = luongCoBan;
//        this.tuoi = tuoi;
//    }
    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }
}

class TruongPhong extends NhanVien{
    public String diaChi = "Hà Nội";
}

class mainInheritance{
    public static void main(String[] args) {
        TruongPhong p1 = new TruongPhong();
        p1.setTuoi(25);
        p1.setLuongCoBan(1999999);
        p1.setTenNhanVien("Duy Thái");
        System.out.printf("Tên nhân viên: "+ p1.getTenNhanVien() + ", tuổi: "+p1.getTuoi()+ "\nĐịa chỉ: "+ p1.diaChi + "\nLuonng cơ bản: "+p1.getLuongCoBan());
    }

}
