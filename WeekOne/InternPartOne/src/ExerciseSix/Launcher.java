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


    public static void main(String[] args) {

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
//                int namKinhNghiem = Integer.parseInt(tokenizer.nextToken());
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
                String nhanVienBH = tokenizer.nextToken();
                NhanVien nhanVien = nhanVienRepo.getNhanVien(nhanVienBH);
                KhachHang khachHang = khachHangRepo.getKhachHang("");
                MatHang matHang = matHangRepo.
                String tongGia = tokenizer.nextToken();
                Date ngayMua = new SimpleDateFormat("dd/MM/yyyy").parse(tokenizer.nextToken());
                HoaDon hoaDon = new HoaDon(maHoaDon,nhanVienBH, khachHang,matHang, tongGia, ngayMua);
                danhSachKH.add(khachHang);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return danhSachKH;
    }

    private static List<MatHang> docFileMatHang() throws IOException, ParseException {
        List<KhachHang> danhSachKH = new ArrayList<>();
        String filePathMatHang = "src/ExerciseSix/MatHang.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathKhachHang))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "-");
                String maKH = tokenizer.nextToken();
                GioiTinh gioiTinh = GioiTinh.valueOf(tokenizer.nextToken().toUpperCase());
                int doTuoi = Integer.parseInt(tokenizer.nextToken());
                KhachHang khachHang = new KhachHang(maKH, gioiTinh, doTuoi);
                danhSachKH.add(khachHang);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return danhSachKH;
    }

//    private static List<NhanVienNhapHang> docFileNVNH(String filePath) throws IOException, ParseException {
//        List<NhanVienNhapHang> danhSachNhanVienNH = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                StringTokenizer tokenizer = new StringTokenizer(line, "-");
//                String maNV = tokenizer.nextToken();
//                GioiTinh gioiTinh = GioiTinh.valueOf(tokenizer.nextToken().toUpperCase());
//                Date ngayLamViec = new SimpleDateFormat("dd/MM/yyyy").parse(tokenizer.nextToken());
//                int namKinhNghiem = Integer.parseInt(tokenizer.nextToken());
////                CaDangKy caDangKy = CaDangKy.valueOf(tokenizer.nextToken().toUpperCase());
//
//                NhanVienNhapHang nhanVien = new NhanVienNhapHang(maNV, gioiTinh, ngayLamViec, namKinhNghiem);
//                danhSachNhanVienNH.add(nhanVien);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return danhSachNhanVienNH;
//    }
}
