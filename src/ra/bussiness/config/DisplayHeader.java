package ra.bussiness.config;

public class DisplayHeader {
    public static void displayHeaderCatalog() {
        System.out.println(Color.TEXT_RED +"                                .-------------------------------------------------------------------------------------------------." + Color.TEXT_RESET);
        System.out.println(Color.TEXT_RED +"                                |                                      DANH SÁCH THỂ LOẠI SÁCH                                    |" + Color.TEXT_RESET);
        System.out.println(Color.TEXT_RED +"                                |-------------------------------------------------------------------------------------------------|" + Color.TEXT_RESET);
        System.out.println(Color.TEXT_CYAN+"                                |        Mã thể loại sách      |          Tên thể loại           |         Trạng thái thể loại    |" + Color.TEXT_RESET);
        System.out.println(Color.TEXT_RED +"                                |-------------------------------------------------------------------------------------------------|" + Color.TEXT_RESET);
    }
    public static void displayHeaderBook(){
        System.out.println(Color.TEXT_RED +"                        .-------------------------------------------------------------------------------------------------------------------." + Color.TEXT_RESET);
        System.out.println(Color.TEXT_RED +"                        |                                                   DANH SÁCH  SÁCH                                                 |" + Color.TEXT_RESET);
        System.out.println(Color.TEXT_RED +"                        |-------------------------------------------------------------------------------------------------------------------|" + Color.TEXT_RESET);
        System.out.println(Color.TEXT_CYAN+"                        |   Mã sách  |   Tiêu đề sách   |   Tên tác giả   |       NXB       | Năm xuất bản |   Mô tả sách     | Mã thể loại |" + Color.TEXT_RESET);
        System.out.println(Color.TEXT_RED +"                        |-------------------------------------------------------------------------------------------------------------------|" + Color.TEXT_RESET);
    }
}
