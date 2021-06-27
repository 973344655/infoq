package demo.week8.work2;

import java.sql.*;

public  class JdbcUtils {

    /**
     * 数据库连接地址
     */
    private static String url ;
    /**
     * 用户名
     */
    private static String userName ;
    /**
     * 密码
     */
    private static String password;

    private static String driver;

    /**
     * 装载驱动
     */
    static {

        url="jdbc:mysql://127.0.0.1:3307/sharding_db";
        userName="root";
        password="123456";
        driver="com.mysql.jdbc.Driver";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 建立数据库连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(url, userName, password);
        return conn;
    }

    /**
     * 释放连接
     * @param conn
     */
    private static void freeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 释放statement
     * @param statement
     */
    private static void freeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 释放resultset
     * @param rs
     */
    private static void freeResultSet(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     *
     * @param conn
     * @param statement
     * @param rs
     */
    public static void free(Connection conn, Statement statement, ResultSet rs) {
        if (rs != null) {
            freeResultSet(rs);
        }
        if (statement != null) {
            freeStatement(statement);
        }
        if (conn != null) {
            freeConnection(conn);
        }
    }

}