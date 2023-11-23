package ra.business.validate;

public class Validate {
    public static final String ERROR_ALERT = "Nhập không hợp lệ vui lòng thử lại!";
    public static final String ERROR_NUMBER_BIG = "Hãy nhập số lớn hơn 0!";
    public static final String ERROR_NUMBER_POSITIVE = "Hãy nhập số nguyên dương!";
    public static final String ERROR_EXITSBYNAME = "Đã tồn tại hãy nhập lại!";
    public static final String ERROR_6CHAR = "Phải lớn hơn 6 ký tự!";
    public static final String ERROR_FIND = "Không tồn tại!";
    public static final String SUCCESS_ADD = "Thêm thành công!";
    public static final String SUCCESS_UPDATE = "Sửa thành công!";

    public static boolean checkBigger(double n){
        if (n > 0) return true;
        return false;
    }

    public static boolean checkBiggerOrEquals(int n){
        if (n >= 0) return true;
        return false;
    }

    public static boolean hasLeast6Char(String str){
        if (str.trim().length() >= 6) return  true;
        return false;
    }

}
