package demo.work.work10.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtil {


    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String driverClassName = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/test?" +
                    "useUnicode=true&characterEncoding=utf-8&useSSL=false";
            String username = "root";
            String password = "123456";
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            System.out.println("获取数据库连接失败");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接
     * @param conn
     * @param stat
     * @param rs
     */
    public static void close(Connection conn, Statement stat, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行sql语句，增删改
     * @param sql
     * @param args
     * @return
     */
    public static Boolean didSql(String sql, Object...args) {
        Connection conn = null;
        PreparedStatement prestatement = null;
        try {
            conn = getConnection();
            prestatement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                prestatement.setObject(i+1, args[i]);
            }
           prestatement.executeUpdate();
            //没有异常，就默认执行成功
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close(conn, prestatement, null);
        }
    }

    /**
     * 查询，有返回值
     * @param sql
     * @param args
     * @return
     */
    public static List<Map<String, Object>> select(String sql, Object...args) {
        Connection conn = null;
        PreparedStatement prestatement = null;
        ResultSet rs = null;
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            conn = getConnection();
            prestatement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                prestatement.setObject(i+1, args[i]);
            }
            rs = prestatement.executeQuery();
            //遍历每一行属性的值
            ResultSetMetaData rsm =rs.getMetaData();
            //获得列的个数
            int col = rsm.getColumnCount();
            //获取数据
            while (rs.next()) {
                Map<String,Object> map = new HashMap<>();
                for(int i=0; i<col; i++){
                    map.put(rsm.getColumnName(i+1), rs.getString(i+1));
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
           return null;
        } finally {
            close(conn, prestatement, rs);
        }

        return list;
    }

    /**
     * 执行sql语句，增删改
     * @return
     */
    public static Boolean didSqlBatch(String sql, List<List<String>> list) {
        Connection conn = null;
        PreparedStatement prestatement = null;
        try {
            conn = getConnection();
            //关闭自动提交
            conn.setAutoCommit(false);
            prestatement = conn.prepareStatement(sql);


            //addBatch()
            for (int i = 0; i < list.size(); i++) {
                for(int j=0; j<list.get(i).size(); j++){
                    prestatement.setObject(j+1, list.get(i).get(j));
                }
                prestatement.addBatch();
            }

            //批处理
            prestatement.executeBatch();
            //执行完后，手动提交事务
            conn.commit();
            //再把自动提交打开，避免影响其他需要自动提交的操作
            conn.setAutoCommit(true);
            //没有异常，就默认执行成功
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close(conn, prestatement, null);
        }
    }


}