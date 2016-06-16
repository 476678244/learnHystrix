package learnHystrix.testFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I303152 on 6/16/2016.
 */
public class SQLIn {
    public static void main(String[] args) {
        List<String> userIds = new ArrayList<>();
        userIds.add("zonghan");
        userIds.add("zonghan1");
        userIds.add("zonghan2");
        StringBuilder sb = new StringBuilder("delete from " + "sfuser_tree"
                + "USRGRP_MAP where users_group_id=? and users_sys_id in ( ");
        for (String userId : userIds) {
            sb.append(" ?,");
        }
        sb.deleteCharAt(sb.length() - 1).append(" )");
        String sql = sb.toString();
        System.out.println(sql);
    }
}
