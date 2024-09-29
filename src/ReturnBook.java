import java.sql.*;
import java.util.Scanner;
public class ReturnBook {
    static String url = "jdbc:mysql://localhost:3306/library_db";
    static String user = "root";
    static String password = "Nethmi@0197";
    static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args){
        int choice=0;
        do {
            System.out.print("Enter the loan id that want to returned: ");
            int loan_id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Set the return date of the book: ");
            String return_date = scanner.nextLine();

            System.out.print("mark the status (returned or not returned): ");
            String status = scanner.nextLine();


            String query = "UPDATE loans SET return_date=?, status=? WHERE loan_id = ?";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, return_date);
                statement.setString(2, status);
                statement.setInt(3, loan_id);

                int inserted_rows = statement.executeUpdate();
                if (inserted_rows > 0) {
                    System.out.println("This book has returned");
                } else {
                    System.out.println("Fail");
                }

                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("if you want to continue press 1");
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
