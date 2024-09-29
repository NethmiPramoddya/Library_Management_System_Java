import java.sql.*;
import java.util.Scanner;

public class UpdateLBdata {
    static String url = "jdbc:mysql://localhost:3306/library_db";
    static String user = "root";
    static String password = "Nethmi@0197";
    static Scanner scanner = new Scanner(System.in);
    public static void main (String[] args){
        int choice2 = 0;
        do{
            System.out.print("Enter the book id you want to update: ");
            int book_id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("What do you want to update? Title, author, publisher or year published? ");
            String choice = scanner.nextLine();

            String query = "UPDATE books SET ";
            String value = null;

            switch (choice) {
                case "title":
                    System.out.print("Set a new title for the book: ");
                    value = scanner.nextLine();
                    query += "title=? WHERE book_id = ? ";
                    break;

                case "author":
                    System.out.print("Set a new author for the book: ");
                    value = scanner.nextLine();
                    query += "author=? WHERE book_id = ? ";
                    break;

                case "publisher":
                    System.out.print("Set a new publisher for the book: ");
                    value = scanner.nextLine();
                    query += "publisher=? WHERE book_id = ? ";
                    break;

                case "year published":
                    System.out.print("Set a new year published for the book: ");
                    value = scanner.nextLine();
                    query += "year_published=? WHERE book_id = ? ";
                    break;

                default:
                    System.out.println("invalid input");
                    scanner.close();
                    return;
            }

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = connection.prepareStatement(query);

                if ("title".equalsIgnoreCase(choice) || "author".equalsIgnoreCase(choice) || "publisher".equalsIgnoreCase(choice) || "year published".equalsIgnoreCase(choice)) {
                    statement.setString(1, value);
                } else if ("year published".equalsIgnoreCase(choice)) {
                    statement.setInt(1, Integer.parseInt(value));
                }
                statement.setInt(2, book_id);

                int inserted_rows = statement.executeUpdate();
                if (inserted_rows > 0) {
                    System.out.println("details updated successfully");
                } else {
                    System.out.println("incorrect book ID");
                }

                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("if you want to continue press 1");
            System.out.println("if you want to exit press 0");
            System.out.println("enter your choice: ");
            choice2 = scanner.nextInt();
            scanner.nextLine();
            if(choice2==0){
                System.out.println("Have a great day :-) Bye Byee!!");
            }
        }while (choice2!=0);

    }
}
