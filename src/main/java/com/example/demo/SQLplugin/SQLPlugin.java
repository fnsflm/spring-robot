package com.example.demo.SQLplugin;
import java.sql.*;
public class SQLPlugin {
    public static Connection conn = getConnercion();
    /*连接数据库*/
    public static Connection getConnercion(){
        Connection conn = null;
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/blhxbot?useUnicode=true&characterEncoding=utf-8";
        String username = "blhxbot";
        String password = "721217";
        try{
            conn = DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            System.out.println("数据库链接出错");
        }
        try{
            Statement statement = conn.createStatement();
            statement.execute("use blhxbot");
        }catch (SQLException e){
            System.out.println("use database 出错");
        }
        return conn;
    }
    /*数据库关闭*/
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
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

    /*打印表*/
    public static String printtable(String table,String key) {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from "+table);
            StringBuffer prtStr = new StringBuffer();
            while(rs.next()) {
                prtStr.append(rs.getString(key) + " " + rs.getString(table) + "\n");
            }
            prtStr.deleteCharAt(prtStr.length()-1);
            System.out.println(prtStr);
            return prtStr.toString();
        }catch(SQLException e){
            System.out.println("sqlerror-print");
        }
        return null;
    }
    public static String printtable(String table,String key,String constrains) {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from "+table+" where "+constrains);
            StringBuffer prtStr = new StringBuffer();
            while(rs.next()) {
                prtStr.append(rs.getString(key) + " " + rs.getString(table) + "\n");
            }
            prtStr.deleteCharAt(prtStr.length()-1);
            System.out.println(prtStr);
            return prtStr.toString();
        }catch(SQLException e){
            System.out.println("sqlerror-print");
        }
        return null;
    }

    /*查询表*/
    public static String find(String table, String keyequal){
        String value = null;
        String sqlfind = "select "+ table +" from "+ table + " where "+keyequal;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlfind);
            if(rs.next()){
                value = rs.getString(1);
            }
        }catch (SQLException e){
            System.out.println("sqlerror-find");
        }
        return value;
    }
    public static String find(String table, String keyequal,long groupId){
        String value = null;
        String sqlfind = "select "+ table +" from "+ table + " where "+keyequal+" and groupid='"+groupId+"'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlfind);
            if(rs.next()){
                value = rs.getString(1);
            }
        }catch (SQLException e){
            System.out.println("sqlerror-find");
        }
        return value;
    }
    /*插入元素*/
    public static void insert(String table, String key, String value){
        try{
            Statement stmt = conn.createStatement();
            stmt.execute("insert into " + table + " values ("+key+","+value+")");
        }catch (SQLException e){
            System.out.println("sqlerror-insert");
        }
    }
    public static void insert(String table, String key, String value1,String value2){
        try{
            Statement stmt = conn.createStatement();
            stmt.execute("insert into " + table + " values ('"+key+"','"+value1+"','"+value2+"')");
        }catch (SQLException e){
            System.out.println("sqlerror-insert");
        }
    }
    /*清空表*/
    public static void delete(String table){
        try{
            Statement stmt = conn.createStatement();
            stmt.execute("delete from "+table);
        }catch (SQLException e){
            System.out.println("sqlerror-delete");
        }
    }
    public static void delete(String table, String keyequal){
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("delete from "+table+" where "+keyequal);
        }catch (SQLException e){
            System.out.println("sqlerror-delete-by-key");
        }
    }
}
