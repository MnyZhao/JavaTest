import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Crate by E470PD on 2018/12/13
 */
public class TestBackpressure {

    public static void main(String[] args) {
        List week = new ArrayList<>();
        week.add(new Week("周一", false, "1"));
        week.add(new Week("周二", false, "2"));
        week.add(new Week("周三", false, "3"));
        week.add(new Week("周四", false, "4"));
        week.add(new Week("周五", false, "5"));
        week.add(new Week("周六", false, "6"));
        week.add(new Week("周日", false, "7"));
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("3");
        list.add("5");
        //错误的
        /*for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < week.size(); j++) {
                Week week1 = (Week) week.get(j);
                if (list.get(i).equals(week1.getWeekNum())) {
                    week1.setCheck(true);
                    System.out.println("TestBackpressure.main->"+"list->i->"+i + "->week->J->" + j + "->isCheck->" + week1.isCheck());
                    break;
                } else {
                    week1.setCheck(false);
                    System.out.println("TestBackpressure.main->"+"list->i->"+i + "->week->J->" + j + "->isCheck->" + week1.isCheck());
                }

            }
        }*/
        //正确的
        for (int i = 0; i < week.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                Week week1 = (Week) week.get(i);
                if (list.get(j).equals(week1.getWeekNum())) {
                    week1.setCheck(true);
                    System.out.println("TestBackpressure.main->" + "week->i->" + i + "->list->J->" + j + "->isCheck->" + week1.isCheck());
                    break;
                } else {
                    week1.setCheck(false);
                    System.out.println("TestBackpressure.main->" + "week->i->" + i + "->list->J->" + j + "->isCheck->" + week1.isCheck());
                }
            }
        }
        for (int i = 0; i < week.size(); i++) {
            System.out.println("TestBackpressure.main" + ((Week) week.get(i)).isCheck());
        }
    }
}
/*for (int i = 0; i < list.size(); i++) {
        for (int j = 0; j < week.size(); j++) {
        Week week1 = (Week) week.get(j);
        if (list.get(i).equals(week1.getWeekNum())) {
        week1.setCheck(true);
        System.out.println("TestBackpressure.main->" + "J->" + j + "isCheck->" + week1.isCheck());
        break;
        } else {
        week1.setCheck(false);
        System.out.println("TestBackpressure.main->" + "J->" + j + "isCheck->" + week1.isCheck());
        }

        }
        }*/

 /*for (int i = 0; i < week.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                Week week1 = (Week) week.get(i);
                System.out.println("TestBackpressure.main i->" + i + "j->" + j);
                if (list.get(j).equals(week1.getWeekNum())) {
                    week1.setCheck(true);
                    break;
                } else {
                    week1.setCheck(false);
                }
            }
        }*/

