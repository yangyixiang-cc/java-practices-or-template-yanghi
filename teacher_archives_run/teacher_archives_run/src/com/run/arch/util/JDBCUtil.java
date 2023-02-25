package com.run.arch.util;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * JDBC操作工具类
 */
public class JDBCUtil {
//	final static String DRIVER="com.mysql.cj.jdbc.Driver";
//	final static String URL="jdbc:mysql://localhost:3306/wsrd?useSSL=false&serverTimezone=UTC";
private static String driver = null;
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private JDBCUtil(){}

    static {
        try {
            // 资源绑定器
            ResourceBundle bundle = ResourceBundle.getBundle("connection_information");
            driver = bundle.getString("driver");
            url = bundle.getString("url");
            user = bundle.getString("user");
            password = bundle.getString("password");
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //返回连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    // 关闭资源
    public static void close(Connection conn, Statement st, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
}
