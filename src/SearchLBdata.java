import java.sql.*;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class SearchLBdata {
    static String url = "jdbc:mysql://localhost:3306/library_db";
    static String user = "root";
    static String password = "Nethmi@0197";
    static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args) {
        int choice = 0;
        do {
            System.out.println("Enter the search type (by title, author, or year):");
            String type = scanner.nextLine();

            String query = "SELECT * FROM books WHERE ";
            String parameter = null;

            switch (type) {
                case "title":
                    System.out.println("enter the title you search for: ");
                    parameter = scanner.nextLine();
                    query += "title LIKE ? ";
                    break;

                case "author":
                    System.out.println("enter the author you search for: ");
                    parameter = scanner.nextLine();
                    query += "author LIKE ? ";
                    break;

                case "year":
                    System.out.println("enter the year you search for: ");
                    parameter = scanner.nextLine();
                    query += "year_published ? ";
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

                if ("title".equalsIgnoreCase(type) || "author".equalsIgnoreCase(type)) {
                    statement.setString(1, "%" + parameter + "%");
                } else if ("year".equalsIgnoreCase(type)) {
                    statement.setInt(1, Integer.parseInt(parameter));
                }

                ResultSet result = statement.executeQuery();

                int book_id;
                String title;
                String author;
                String publisher;
                int year_published;

                while (result.next()) {
                    book_id = result.getInt("book_id");
                    title = result.getString("title");
                    author = result.getString("author");
                    publisher = result.getString("publisher");
                    year_published = result.getInt("year_published");

                    System.out.printf("%-5d %-30s %-20s %-20s %-10d\n", book_id, title, author, publisher, year_published);
                }

                connection.close();
            } catch (Exception e) {
                System.out.println("An error occurred");
            }
            System.out.println("if you want to continue searching press 1");
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

