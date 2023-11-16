package ExerciseSix;

public enum CaDangKy{
    SANG, CHIEU, TOI;
    public static boolean isValid(String value) {
        try {
            // Chuyển đổi chuỗi thành enum, nếu không có ngoại lệ, giá trị là hợp lệ
            CaDangKy.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

