package UsingData;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateExample {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());
        cal.add(Calendar.DATE, 15);
        System.out.println(cal.getTime());
        cal.add(Calendar.MONTH, 12);
        System.out.println(cal.getTime());
        System.out.println("DAY: " + cal.get(Calendar.DATE));
        //static String[] getAvailableIDs():	Nó được sử dụng để nhận được tất cả các ID có sẵn được hỗ trợ.
//        String[] id = TimeZone.getAvailableIDs();
//        System.out.println("Danh sach id co san la: ");
//        for (int i = 0; i < id.length; i++) {
//            System.out.println(id[i]);
//        }
//        //static TimeZone getDefault(): Nó được sử dụng để lấy TimeZone mặc định cho máy chủ hiện tại.
        TimeZone time = TimeZone.getDefault();
        System.out.println(time);
        String name = time.getDisplayName();
        System.out.println(name);
        System.out.println("Giá trị ID timezone là: " +time.getID());
        LocalDate d1 = LocalDate.of(2022, 10, 22);
        LocalDate d2 = LocalDate.of(2023, 3, 22);
        Period p = d1.until(d2);
        System.out.println(d2.minus(p));


        //Date sang String
        Date currentDate = new Date();
        System.out.println("Date hien tai: " + currentDate);
        String dateToStr = DateFormat.getInstance().format(currentDate);
        System.out.println("Date Format su dung getInstance(): " + dateToStr);

        //Date sang String bằng simpleDateFormat
        Date date1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date1);
        System.out.println(strDate);
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date3 = formatter.parse("23/09/2023");
            System.out.println("Date: " + date3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //String thành DAte

}
