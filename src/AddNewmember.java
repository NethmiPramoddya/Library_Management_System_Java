import java.sql.*;
import java.util.Scanner;
public class AddNewmember {
    static String url = "jdbc:mysql://localhost:3306/library_db";
    static String user = "root";
    static String password = "Nethmi@0197";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        int choice = 0;

        do {
            System.out.print("Enter new member Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter his/her Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter contact details(phone): ");
            String phone = scanner.nextLine();

            String query = "INSERT INTO members(name, email, phone) values (?,?,?)";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, phone);

                int inserted_rows = statement.executeUpdate();
                if (inserted_rows > 0) {
                    System.out.println("member added successfully");
                }

                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("if you want to continue adding another new member press 1");
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
