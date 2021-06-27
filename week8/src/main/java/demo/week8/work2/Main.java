package demo.week8.work2;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {

    public static void main(String[] args) {
        new Main().testInsert();
    }
    public void testInsert(){

        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into t_order\n" +
                    "            (id, customer_id, product_num)\n" +
                    "        values (\n" +
                    "                ?, ?, ?)";
            statement = conn.prepareStatement(sql);


            //表7
            statement.setInt(1, 7);
            //库0
            statement.setInt(2, 2);
            statement.setInt(3, 1);


            statement.execute();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.free(conn, statement, null);
        }

    }

}
