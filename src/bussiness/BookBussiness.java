package bussiness;

import entity2.Book;
import ultil.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookBussiness {
    public static List<Book> getAllBook(){
        List<Book> bookList = null;
        // tao doi tuong connection
        Connection connection= null;
        // tao doi tuong callableStatement de goi cac thu tuc procedure
        CallableStatement callableStatement = null;

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("call proc_getAllBook()");
            // thuc hien goi procudeure
            ResultSet resultSet= callableStatement.executeQuery();
            //ResultSet: no la tap hop cac bang ghi nen phair duyet
            // duyet cac bang ghi trong ResultSet va day ra booklist
            bookList= new ArrayList<>();
            // duyet tat ca bang ghi
            while (resultSet.next()){
                Book book = new Book();
                book.setBookId(resultSet.getString("bookId"));
                book.setBookName(resultSet.getString("bookName"));
                book.setPrice(resultSet.getFloat("price"));
                book.setAuthor(resultSet.getString("author"));
                book.setStatus(resultSet.getBoolean("status"));
                // add vao list
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return bookList;
    }

    public static boolean createBook(Book book){
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement= connection.prepareCall("{call proc_insertBook(?,?,?,?,?)}");
            // set data cho cac tham so vao cau procedure
            callableStatement.setString(1, book.getBookId());
            callableStatement.setString(2, book.getBookName());
            callableStatement.setFloat(3,book.getPrice());
            callableStatement.setString(4,book.getAuthor());
            callableStatement.setBoolean(5,book.isStatus());
            // dang ky kieu data cho tham so tra ra
            // thuwc hien goi procedure
            int bookId =  callableStatement.executeUpdate();
            System.out.println("Sach vua them: " +bookId);
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }
        return result;
    }

    public static boolean updateBook(Book book){
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean resutl = false;
        try{
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call proc_updateBook(?,?,?,?,?)}");
            // set data cho cac tham so vao cua procedure
            callableStatement.setString(1, book.getBookId());
            callableStatement.setString(2,book.getBookName());
            callableStatement.setFloat(3,book.getPrice());
            callableStatement.setString(4,book.getAuthor());
            callableStatement.setBoolean(5, book.isStatus());
            // thuc hien goi procedure
            callableStatement.executeUpdate();
            resutl = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        // dong ket noi
        finally {
            ConnectionDB.closeConnection(connection,callableStatement);
        }
        return resutl;
    }

    public static Book getBookById( String bookId){
        Book book  = null;
        // tao doi tuong connection
        Connection connection= null;
        // tao doi tuong callableStatement de goi cac thu tuc procedure
        CallableStatement callableStatement = null;

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("call proc_getBookById(?)");
            //set gia tri tham so vao
            callableStatement.setString(1, bookId);
            // thuc hien goi procedure
            ResultSet resultSet= callableStatement.executeQuery();


            // duyet tat ca bang ghi
            while (resultSet.next()){
                // lay du lieu resultSet đẩy vào đối tượng book trả v
                // tao doi tuong de tra ve
                book = new Book();
                book.setBookId(resultSet.getString("bookId"));
                book.setBookName(resultSet.getString("bookName"));
                book.setPrice(resultSet.getFloat("price"));
                book.setAuthor(resultSet.getString("author"));
                book.setStatus(resultSet.getBoolean("status"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return book;
    }

    public static Boolean deleteBook( String bookId){
        // tao doi tuong connection
        Connection connection= null;
        // tao doi tuong callableStatement de goi cac thu tuc procedure
        CallableStatement callableStatement = null;
        boolean resutl = false;
        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("call proc_deleteBook(?)");
            //set gia tri tham so vao
            callableStatement.setString(1, bookId);
            // thuc hien goi procedure
            ResultSet resultSet= callableStatement.executeQuery();
            callableStatement.executeUpdate(); // vi k co tham so tra ve
            resutl=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return resutl;
    }

    public static int getBookByPrice(Float fromPrice, Float toPrice){
        int cntBook=0;
        // tao doi tuong connection
        Connection connection= null;
        // tao doi tuong callableStatement de goi cac thu tuc procedure
        CallableStatement callableStatement = null;

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("call proc_getCntBook(?,?,?)");
            //set gia tri tham so vao
            callableStatement.setFloat(1, fromPrice);
            callableStatement.setFloat(2, toPrice);
            //dang ky tham so tra ra
            callableStatement.registerOutParameter(3, Types.INTEGER);
            // thuc hien goi procedure
            callableStatement.execute(); // co tham so tra ra nen dung execute
            // lay gia tri tra ra
            cntBook = callableStatement.getInt(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return cntBook;
    }

    public static List<Book> sortPriceByPrice(){
        List<Book> bookList = new ArrayList<>();
        boolean result = false;
        // tao doi tuong connection
        Connection connection = null;
        // tao doi tuong callableStatement de goi den procedure
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            // mo ket noi den CSDL
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call proc_sortByPriceDESC()}");
            //thuc hien goi procedure
            resultSet = callableStatement.executeQuery();
            // duyet tat ca bang ghi
            while (resultSet.next()){
                // Lấy dữ liệu từ resultSet và tạo đối tượng Book
                Book book = new Book();
                book.setPrice(resultSet.getFloat("price"));
                // Thêm đối tượng Book vào danh sách
                bookList.add(book);
            }
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally { // Dong ket noi
            if (callableStatement !=null){
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet !=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection !=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return bookList;

    }

    public static float sumPrice() {
        float sum = 0.0f;
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call proc_sumBookByPrice(?)}");
            callableStatement.registerOutParameter(1, Types.FLOAT); // Đăng ký tham số đầu ra

            callableStatement.execute();

            // Lấy kết quả từ tham số đầu ra
            sum = callableStatement.getFloat(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return sum;
    }

}
