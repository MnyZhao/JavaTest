import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Crate by E470PD on 2018/12/21
 */
public class Test {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        DecimalFormat dfs = new DecimalFormat("#.0");
        System.out.println("Test.main" + df.format(123321.33333));
        System.out.println("Test.main" + Double.parseDouble("12"));
        System.out.println("Test.main" + dfs.format(12.4343));
        String parten = "[1-9]\\d*\\.?\\d*|0\\.\\d*[1-9]";
        Pattern pa = Pattern.compile(parten);
        System.out.println("Test.main" + pa.matcher("0.121").matches());
        System.out.println("Test.main" + (int) Double.parseDouble("124.00"));

    }
}
