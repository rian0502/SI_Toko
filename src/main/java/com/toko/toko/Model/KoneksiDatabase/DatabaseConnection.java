package com.toko.toko.Model.KoneksiDatabase;


import java.sql.*;

public class DatabaseConnection {
    private static final String USERNMAE = "root";
    private static final String PASSWORD = "rian";
    private static final String URL = "jdbc:mysql://localhost/toko";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    public static Connection getKoneksi(){
        Connection connection;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNMAE, PASSWORD);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return connection;
    }

}
