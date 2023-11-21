package OthersProblems;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WildCardPara {

    Collection<?> coll = new ArrayList<String>();

    // Một tập hợp chỉ chứa kiểu Number hoặc kiểu con của Number
    List<? extends Number> list = new ArrayList<Long>();

    // Một đối tượng có kiểu tham số đại diện.
// (A wildcard parameterized type)
//    Pair<String,?> pair = new Pair<String,Integer>();
    /**
     * Ví dụ về lỗi
     */
    // String không phải là kiểu con của Number, vì vậy lỗi.
//    List<? extends Number> list1 = new ArrayList<String>();

    // String không phải là kiểu cha của Integer vì vậy lỗi
//    ArrayList<? super String> cmp = new ArrayList<Integer>();

}
