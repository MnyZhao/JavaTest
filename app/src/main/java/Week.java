/**
 * Crate by E470PD on 2018/12/20
 */
public class Week {
    private boolean isCheck;
    public String weekDay;//显示
    public String weekNum;//标记

    public Week() {

    }

    public Week(String weekDay, boolean isCheck, String weekNum) {
        this.weekDay = weekDay;
        this.isCheck = isCheck;
        this.weekNum = weekNum;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

}
