package ExerciseSix;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
class NhanVienRepo{
    Map<String, NhanVien> map = new HashMap<>();
    public NhanVienRepo(){

    }
    public void addNhanVien(NhanVien nhanVien){
        map.put(nhanVien.getMaNhanVien(), nhanVien);
    }
    public NhanVien getNhanVien(String maNhanVien){
        return map.get(maNhanVien);
    }
}
class KhachHangRepo{
    HashMap<String, KhachHang> map = new HashMap<>();
    public KhachHangRepo(){

    }
    public void addKhachHang(KhachHang khachHang){
        map.put(khachHang.getMaKhachHang(), khachHang);
    }
    public KhachHang getKhachHang(String maKhachHang){
        return map.get(maKhachHang);
    }

}
class MatHangRepo{
    Map<String, MatHang> map = new HashMap<>();
    public MatHangRepo(){
        this.map = map;
    }
    public void addMatHang(MatHang matHang){
        map.put(matHang.getMaHangHoa(), matHang);
    }

    public MatHang getMatHang(String maHangHoa){
        return map.get(maHangHoa);
    }
}

public class Launcher {
    static NhanVienRepo nhanVienRepo=  new NhanVienRepo();
    static KhachHangRepo khachHangRepo = new KhachHangRepo();
    static MatHangRepo matHangRepo = new MatHangRepo();


    public static void main(String[] args) throws IOException, ParseException {

        String filePathNVBH = "src/ExerciseSix/NhanVienBanHang.txt";
        String filePathNVNH = "src/ExerciseSix/NhanVienNhapHang.txt";
        String filePathHoaDon = "src/ExerciseSix/HoaDon.txt";
        String filePathMatHang = "src/ExerciseSix/MatHang.txt";
        String filePathNV = "src/ExerciseSix/NhanVien.txt";
        try {
            List<NhanVien> danhSachNhanVien = docFileNV();
            // In thông tin các nhân viên
            for (NhanVien nhanVien : danhSachNhanVien) {
                System.out.println(nhanVien);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        tongTienKhach();
        doanhSoBanHang();

        //KhachHang
//        try {
//            List<KhachHang> danhSachKH = docFileKH(filePathNV);
//            // In thông tin các nhân viên
//            for (NhanVien nhanVien : danhSachNhanVien) {
//                System.out.println(nhanVien);
//            }
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }

    }
    private static List<NhanVien> docFileNV() throws IOException, ParseException {
        List<NhanVien> danhSachNhanVien = new ArrayList<>();
        String filePathNhanVien = "src/ExerciseSix/NhanVien.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathNhanVien))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "-");
                String maNV = tokenizer.nextToken();
                GioiTinh gioiTinh = GioiTinh.valueOf(tokenizer.nextToken().toUpperCase());
                Date ngayLamViec = new SimpleDateFormat("dd/MM/yyyy").parse(tokenizer.nextToken());
                String possibleCaDangKy = tokenizer.nextToken();
                if (CaDangKy.isValid(possibleCaDangKy)) {
                    CaDangKy caDangKy = CaDangKy.valueOf(possibleCaDangKy.toUpperCase());
                    // Tạo đối tượng NhanVienBanHang và thêm vào danh sách
                    NhanVienBanHang nhanVien = new NhanVienBanHang(maNV, gioiTinh, ngayLamViec, caDangKy);
                    danhSachNhanVien.add(nhanVien);
                    nhanVienRepo.addNhanVien(nhanVien);
                } else {
                    int thamNien = Integer.parseInt(possibleCaDangKy);
                    NhanVienNhapHang nhanVien = new NhanVienNhapHang(maNV, gioiTinh, ngayLamViec, thamNien);
                    danhSachNhanVien.add(nhanVien);
                    nhanVienRepo.addNhanVien(nhanVien);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return danhSachNhanVien;
    }

    private static List<KhachHang> docFileKhachHang() throws IOException, ParseException {
        List<KhachHang> danhSachKH = new ArrayList<>();
        String filePathKhachHang = "src/ExerciseSix/KhachHang.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathKhachHang))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "-");
                String maKH = tokenizer.nextToken();
                GioiTinh gioiTinh = GioiTinh.valueOf(tokenizer.nextToken().toUpperCase());
                int doTuoi = Integer.parseInt(tokenizer.nextToken());
                KhachHang khachHang = new KhachHang(maKH, gioiTinh, doTuoi);
                khachHangRepo.addKhachHang(khachHang);
                danhSachKH.add(khachHang);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return danhSachKH;
    }

    private static List<HoaDon> docFileHoaDon() throws IOException, ParseException {
        List<HoaDon> danhSachHoaDon = new ArrayList<>();
        String filePathHoaDon = "src/ExerciseSix/HoaDon.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathHoaDon))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "-");
                String maHoaDon = tokenizer.nextToken();
//                String nhanVienBH = tokenizer.nextToken();
                NhanVienBanHang nhanVienBanHang = (NhanVienBanHang) nhanVienRepo.getNhanVien(tokenizer.nextToken());
                KhachHang khachHang = khachHangRepo.getKhachHang(tokenizer.nextToken());
                String listMatHang = tokenizer.nextToken();
                List<MatHang> listMH = new ArrayList<>();
                for(String hang : listMatHang.split("-")){
                    MatHang mh = matHangRepo.getMatHang(hang);
                    listMH.add(mh);
                }
                double tongGia = Double.parseDouble(tokenizer.nextToken());
                Date ngayMua = new SimpleDateFormat("dd/MM/yyyy").parse(tokenizer.nextToken());
                HoaDon hoaDon = new HoaDon(maHoaDon,nhanVienBanHang, khachHang,listMH, tongGia, ngayMua);
                danhSachHoaDon.add(hoaDon);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return danhSachHoaDon;
    }

    private static List<MatHang> docFileMatHang() throws IOException, ParseException {
        List<MatHang> danhSachMatHang = new ArrayList<>();
        String filePathMatHang = "src/ExerciseSix/MatHang.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathMatHang))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "-");
                String maHangHoa = tokenizer.nextToken();
                String tenHangHoa = tokenizer.nextToken();
                String phanLoai = tokenizer.nextToken();
                double gia = Double.parseDouble(tokenizer.nextToken());
                MatHang matHang = new MatHang(maHangHoa, tenHangHoa, phanLoai, gia);
                matHangRepo.addMatHang(matHang);
                danhSachMatHang.add(matHang);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return danhSachMatHang;
    }

    public static void tongTienKhach() throws IOException, ParseException {
        List<HoaDon> danhSachHoaDon = docFileHoaDon();
        Map<String, Double> tongTien = new HashMap<>();
        for(HoaDon hoaDon : danhSachHoaDon){
            //Lấy mã khách hàng từ hóa đơn hiện tại.
//            String maKhachHang = hoaDon.getKhachHang();
            String maKhachHang = String.valueOf(hoaDon.getKhachHang());
            Date ngayMua = hoaDon.getNgayMua();
            Date nowTime = new Date();

            if(ngayMua.getMonth() == nowTime.getMonth()) {
                tongTien.put(maKhachHang, tongTien.getOrDefault(maKhachHang, 0.0) + hoaDon.getTongGia());
            }
        }

        System.out.println("Tong Tien: ");
        for(Map.Entry<String, Double> entry : tongTien.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue() + "VND");
        }
    }
    public static void doanhSoBanHang() throws IOException, ParseException {
        List<HoaDon> danhSachHoaDon = docFileHoaDon();
        Map<String, Double> doanhSo = new HashMap<>();
        for (HoaDon hoaDon : danhSachHoaDon) {
//            NhanVienBanHang nhanVienBanHang = hoaDon.getNhanVienBanHang();
            NhanVien nhanVienBanHang = hoaDon.getNhanVienBanHang();
//            double tongGia = hoaDon.getTongGia();
            if(nhanVienBanHang!= null){
                double tongGia = hoaDon.getTongGia();
                doanhSo.put(nhanVienBanHang.getMaNhanVien(), doanhSo.getOrDefault(nhanVienBanHang.getMaNhanVien(), 0.0) + tongGia);
            }
        }

        System.out.println("Doanh So Ban Hang: ");
        for (Map.Entry<String, Double> entry : doanhSo.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + "VND");
        }
    }

}
