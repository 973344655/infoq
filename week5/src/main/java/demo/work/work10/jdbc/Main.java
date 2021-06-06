package demo.work.work10.jdbc;


import java.util.List;
import java.util.Map;

/**
 * 增删改查测试
 * @author xxl
 */
public class Main {

    public static void main(String[] args) {
//        insert();
//        update();
//        delete();
        select();
    }

    public static void select(){
        String sql = "select  * from test_user";

        List<Map<String,Object>> list = DbUtil.select(sql);

        for(Map<String,Object> map : list){
            for(Map.Entry entry : map.entrySet()){
                System.out.println(entry.getKey() + " :: " + entry.getValue());
            }
        }
    }

    public static void insert(){
        String sql = "INSERT INTO `test`.`test_user` (`name`, `desc`) VALUES ('xxl', 'desc1');";

        System.out.println(DbUtil.didSql(sql));
    }

    public static void update(){
        String sql = "UPDATE test.`test_user` set `desc` = 'desc2' where id = 3";

        System.out.println(DbUtil.didSql(sql));
    }

    public static void delete(){
        String sql = "delete from test.`test_user` where id=2";

        System.out.println(DbUtil.didSql(sql));
    }

}
