package ExerciseSix;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Launcher {
    private static List<KhachHang> dsKhachHang;
    private static List<NhanVien> dsNhanVien;
    private static List<MatHang> dsMatHang;
    private static List<HoaDon> dsHoaDon;



    public static void main(String[] args) throws IOException, ParseException {
        dsKhachHang = docFileKhachHang();
        dsNhanVien = docFileNV();
        dsMatHang = docFileMatHang();
        dsHoaDon = docFileHoaDon();
        tongTienKhach();
        doanhSoBanHang();
//        String filePathNVBH = "src/ExerciseSix/NhanVienBanHang.txt";
//        String filePathNVNH = "src/ExerciseSix/NhanVienNhapHang.txt";
//        String filePathHoaDon = "src/ExerciseSix/HoaDon.txt";
//        String filePathMatHang = "src/ExerciseSix/MatHang.txt";
//        String filePathNV = "src/ExerciseSix/NhanVien.txt";
//        try {
//            List<NhanVien> danhSachNhanVien = docFileNV();
//            // In thông tin các nhân viên
//            for (NhanVien nhanVien : danhSachNhanVien) {
//                System.out.println(nhanVien);
//            }
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//

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
        String filePathNhanVienBH = "src/ExerciseSix/NhanVienBanHang.txt";
        String filePathNhanVienNH = "src/ExerciseSix/NhanVienNhapHang.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathNhanVienBH))) {
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
                } else {
                    int thamNien = Integer.parseInt(possibleCaDangKy);
                    NhanVienNhapHang nhanVien = new NhanVienNhapHang(maNV, gioiTinh, ngayLamViec, thamNien);
                    danhSachNhanVien.add(nhanVien);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathNhanVienNH))) {
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
                } else {
                    int thamNien = Integer.parseInt(possibleCaDangKy);
                    NhanVienNhapHang nhanVien = new NhanVienNhapHang(maNV, gioiTinh, ngayLamViec, thamNien);
                    danhSachNhanVien.add(nhanVien);
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
                String maNhanVien = tokenizer.nextToken();

                NhanVienBanHang nhanVien = null;
                for(NhanVien nv: dsNhanVien){
                    if(nv.getMaNhanVien().equals(maNhanVien)){
                        nhanVien = (NhanVienBanHang)nv;
                        break;
                    }
                }
                KhachHang khachHang = null;
                String maKhachHang = tokenizer.nextToken();
                for (KhachHang kh: dsKhachHang){
                    if(kh.getMaKhachHang().equals(maKhachHang)){
                        khachHang = kh;
                        break;
                    }
                }
                String listMatHang = tokenizer.nextToken();
                List<MatHang> listMH = new ArrayList<>();
                for(String hang : listMatHang.split(" ")){
                    for (MatHang mh : dsMatHang){
                        if(hang.equals(mh.getMaHangHoa())){
                            listMH.add(mh);
                            break;
                        }
                    }
                }
                double tongGia = Double.parseDouble(tokenizer.nextToken());
                Date ngayMua = new SimpleDateFormat("dd/MM/yyyy").parse(tokenizer.nextToken());
                HoaDon hoaDon = new HoaDon(maHoaDon, nhanVien, khachHang, listMH, tongGia, ngayMua);
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
                danhSachMatHang.add(matHang);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return danhSachMatHang;
    }

    public static void tongTienKhach() throws IOException, ParseException {
       for(KhachHang khachHang:dsKhachHang){
            double tongTienKhach = 0;
            Date date = new Date();
            for(HoaDon hoaDon:dsHoaDon){
                if(hoaDon.getKhachHang() == khachHang && hoaDon.getNgayMua().getMonth() == date.getMonth()){
                    tongTienKhach+= hoaDon.getTongGia();
                }
            }
            System.out.println(khachHang.getMaKhachHang() + " "+ tongTienKhach);
       }
    }
    public static void doanhSoBanHang() throws IOException, ParseException {
        for(NhanVien nhanVien: dsNhanVien){
            if(nhanVien instanceof NhanVienBanHang){
                double tongTien = 0;
                for(HoaDon hoaDon: dsHoaDon){
                    if(hoaDon.getNhanVienBanHang() == nhanVien){
                        tongTien += hoaDon.getTongGia();
                    }
                }
                System.out.println(nhanVien.getMaNhanVien() + " "+ tongTien);
            }
        }
    }
}
