package learnHystrix.data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by I303152 on 7/6/2016.
 */
public class TestDate {
    public static void main(String[] args) {
        String time = " 2016-07-06 20:09:08.846";
        String s = "=========================\n" + "GetTargetPopulation";
        String s1 = s.substring(0, s.indexOf("=\n")+2);
        String s2 = s.substring(s.indexOf("=\n")+2);
        String s3 = s1 + time + s2;
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println(timestamp.toString());

    }
}
