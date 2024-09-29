import java.sql.*;
import java.util.Scanner;
public class AddLBdata {
    static String url = "jdbc:mysql://localhost:3306/library_db";
    static String user = "root";
    static String password = "Nethmi@0197";
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        int choice=0;
        do {
            System.out.println("Lets add a book!");
            String value = null;

            System.out.print("Enter title of the book: ");
            String title = scanner.nextLine();

            System.out.print("Enter Author of the book: ");
            String author = scanner.nextLine();

            System.out.print("Enter publisher: ");
            String publisher = scanner.nextLine();

            System.out.print("Enter the year published: ");
            int year_published = scanner.nextInt();

            String query = "INSERT INTO books(title, author, publisher, year_published) values (?,?,?,?)";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement add_statement = connection.prepareStatement(query);

                add_statement.setString(1, title);
                add_statement.setString(2, author);
                add_statement.setString(3, publisher);
                add_statement.setInt(4, year_published);


                int inserted_rows = add_statement.executeUpdate();
                if (inserted_rows > 0) {
                    System.out.println("details added successfully");
                }


                connection.close();
            } catch (Exception e) {
                System.out.println(e);

            }

            System.out.println("if you want to continue updating press 1");
            System.out.println("if you want to exit press 0");
            System.out.println("enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice==0){
                System.out.println("Have a great day :-) Bye Byee!!");
            }
        } while (choice != 0);
     scanner.close();
    }


    }

