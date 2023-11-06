package OOP;

public class toStringNapChong {
    private int tuoi;
    private String hoTen;
    private String diaChi;


    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public toStringNapChong(int tuoi, String hoTen, String diaChi) {
        this.tuoi = tuoi;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "Information:\n" +
                "Ten: " + hoTen + "Tuoi "+tuoi
                + "Dia chi: "+diaChi;
    }

    public static void main(String[] args) {
        toStringNapChong ob1 = new toStringNapChong(20, "DUy Thai" ,"Ha Noi");
        ob1.toString();
        System.out.println(ob1.toString());

    }
}
