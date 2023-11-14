package ultil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/demo_jdbc";
    private static final String USER = "root";
    private static final String PASS = "thuit123";

    // phuong thuc de mo connection voi DB va tra ve doi tuong connection de lam viec
    public static Connection openConnection(){
        //khai bao doi tuong connection
        Connection connection = null;
        //cai dat diver manager
        try {
            //1. set driver lam viec voi CSDL de ket noi
            Class.forName(DRIVER);
            //2.khoi tao doi tuong connection tu driver managenment de lam viec voi database
            connection = DriverManager.getConnection(URL,USER,PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    return connection;
    }

    // dong ket noi
    public static void closeConnection(Connection connection , CallableStatement callableStatement) {
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(callableStatement !=null){
            try {
                callableStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

   // test xong bo nha!!!!
//    public static void main(String[] args) {
//        Connection connection = openConnection();
//        if(connection!=null){
//            System.out.println("ket noi thanh cong");
//        }else {
//            System.out.println("ket noi that bai!");
//        }
//    }
}
