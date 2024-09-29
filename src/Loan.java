import java.sql.*;
import java.util.Scanner;
public class Loan {
    static String url = "jdbc:mysql://localhost:3306/library_db";
    static String user = "root";
    static String password = "Nethmi@0197";
    static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args){
        int choice=0;

        do {
            System.out.print("Enter book ID: ");
            int book_id = scanner.nextInt();

            System.out.print("Enter member ID: ");
            int member_id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Loan date: ");
            String loan_date = scanner.nextLine();

            System.out.print("Enter Return date: ");
            String return_date = scanner.nextLine();

            String query = "INSERT INTO loans(book_id, member_id, loan_date, return_date) values (?,?,?,?)";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, String.valueOf(book_id));
                statement.setString(2, String.valueOf(member_id));
                statement.setString(3, loan_date);
                statement.setString(4, return_date);

                int inserted_rows = statement.executeUpdate();
                if (inserted_rows > 0) {
                    System.out.println("Loan recorded successfully!");
                }

                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }

            System.out.println("if you want to continue add another loan press 1");
            System.out.println("if you want to exit press 0");
            System.out.println("enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice==0){
                System.out.println("Have a great day :-) Bye Byee!!");
            }

        }while(choice!=0);
    }
}
