package demo.work.work10.transation;

import demo.work.work10.jdbc.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        insertBatch();
    }

    /**
     * 批处理插入
     */
    public static void insertBatch(){
        String sql = "INSERT INTO `test`.`test_user` (`name`, `desc`) VALUES (?, ?);";

        List<List<String>> list = new ArrayList<>();
        for(int i=0; i<500; i++){
            List<String> temp = new ArrayList<>(2);
            temp.add("name" + i);
            temp.add("desc" + i);
            list.add(temp);
        }

        System.out.println(DbUtil.didSqlBatch(sql, list));
    }
}
