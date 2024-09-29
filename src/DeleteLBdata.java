import java.sql.*;
import java.util.Scanner;

public class DeleteLBdata {
    static String url = "jdbc:mysql://localhost:3306/library_db";
    static String user = "root";
    static String password = "Nethmi@0197";
    static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args){
        int choice =0;
        do {
            System.out.print("Enter the book id you want to delete: ");
            int book_id = scanner.nextInt();

            String query = "DELETE FROM books WHERE book_id = ? ";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setInt(1, book_id);

                int rows_deleted = statement.executeUpdate();
                if (rows_deleted > 0) {
                    System.out.println("Deleted successfully");
                } else {
                    System.out.println("Incorrect Id");
                }

                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("if you want to continue Deleting press 1");
            System.out.println("if you want to exit press 0");
            System.out.println("enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice==0){
                System.out.println("Have a great day :-) Bye Byee!!");
            }

        }while (choice!=0);
    }

}


