import java.sql.*;
import java.util.Scanner;
public class Test {
    static String url = "jdbc:mysql://localhost:3306/library_db";
    static String user = "root";
    static String password = "Nethmi@0197";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            System.out.println("welcome to our Library Management System!");
            System.out.println("press 1 for Add a new book");
            System.out.println("press 2 for Update Any of the book details");
            System.out.println("press 3 to Remove a book");
            System.out.println("press 4 to Search for a Book");
            System.out.println("press 5 for Add a new Member to Library");
            System.out.println("press 6 to Loan a book");
            System.out.println("press 7 to Mark a returned book");
            System.out.println("press 8 to exit/Bye!");
            System.out.println("please enter your choice: ");

            int chosen = scanner.nextInt();
            scanner.nextLine();

            switch (chosen) {
                case 1 -> add();
                case 2 -> update();
                case 3 -> delete();
                case 4 -> search();
                case 5 -> newMember();
                case 6 -> loan();
                case 7 -> bookReturn();
                case 8 -> exit();

                default -> System.out.println("invalid");
            }
            if(chosen==8){
                break;
            }
            System.out.println("thanks for interacting with us.. Have a grate day!");
        }
    }
    static void add(){
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
        System.out.println("Let's add a new book");
        System.out.print("Enter title of the book: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author of the book: ");
        String author = scanner.nextLine();

        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();

        System.out.print("Enter the year published: ");
        int year_published = scanner.nextInt();
        String query = "INSERT INTO books(title, author, publisher, year_published) values (?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, publisher);
            statement.setInt(4, year_published);
            
            int inserted_rows = statement.executeUpdate();
            if (inserted_rows > 0) {
                System.out.println("details added successfully");
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void update(){
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
        System.out.println("Let's Update book details");
        System.out.print("Enter the book id you want to update: ");
        int book_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("What do you want to update? Title, author, publisher or year published? ");
        String choice = scanner.nextLine();

        String Update_query = "UPDATE books SET ";
        String value = null;

        switch (choice) {
            case "title":
                System.out.print("Set a new title for the book: ");
                value = scanner.nextLine();
                Update_query += "title=? WHERE book_id = ? ";
                break;

            case "author":
                System.out.print("Set a new author for the book: ");
                value = scanner.nextLine();
                Update_query += "author=? WHERE book_id = ? ";
                break;

            case "publisher":
                System.out.print("Set a new publisher for the book: ");
                value = scanner.nextLine();
                Update_query += "publisher=? WHERE book_id = ? ";
                break;

            case "year published":
                System.out.print("Set a new year published for the book: ");
                value = scanner.nextLine();
                Update_query += "year_published=? WHERE book_id = ? ";
                break;

            default:
                System.out.println("invalid input");
                scanner.close();
                return;
        }

        try( Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(Update_query)){
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
    }
    static void delete() {
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
        System.out.println("To Remove a book");
        System.out.print("Enter the book id you want to delete: ");
        int ID = scanner.nextInt();

        String delete_query = "DELETE FROM books WHERE book_id = ?";

        try( Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(delete_query)){

            statement.setInt(1, ID);

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
    }
    static void search(){
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
        System.out.println("Let's Search for a Book");
        System.out.println("Enter the search type (by title, author, or year):");
        String type = scanner.nextLine();

        String search_query = "SELECT * FROM books WHERE ";
        String parameter = null;

        switch (type) {
            case "title":
                System.out.println("enter the title you search for: ");
                parameter = scanner.nextLine();
                search_query += "title LIKE ? ";
                break;

            case "author":
                System.out.println("enter the author you search for: ");
                parameter = scanner.nextLine();
                search_query += "author LIKE ? ";
                break;

            case "year":
                System.out.println("enter the year you search for: ");
                parameter = scanner.nextLine();
                search_query += "year_published ? ";
                break;

            default:
                System.out.println("invalid input");
                scanner.close();
                return;
        }

        try( Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(search_query)){

            if ("title".equalsIgnoreCase(type) || "author".equalsIgnoreCase(type)) {
                statement.setString(1, "%" + parameter + "%");
            } else if ("year".equalsIgnoreCase(type)) {
                statement.setInt(1, Integer.parseInt(parameter));
            }

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int book_id = result.getInt("book_id");
                String title = result.getString("title");
                String author = result.getString("author");
                String publisher = result.getString("publisher");
                int year_published = result.getInt("year_published");

                System.out.printf("%-5d %-30s %-20s %-20s %-10d\n", book_id, title, author, publisher, year_published);
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("An error occurred");
        }
    }
    static void  newMember(){
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;

        System.out.print("Enter new member Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter his/her Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter contact details(phone): ");
        String phone = scanner.nextLine();

        String new_mem_query = "INSERT INTO members(name, email, phone) values (?,?,?)";
        try(Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(new_mem_query)){

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

    }
    static void loan(){
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;

        System.out.println("Loan a book");
        System.out.print("Enter book ID: ");
        int Book_id = scanner.nextInt();

        System.out.print("Enter member ID: ");
        int member_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Loan date: ");
        String loan_date = scanner.nextLine();

        System.out.print("Enter Return date: ");
        String return_date = scanner.nextLine();

        String loan_query = "INSERT INTO loans(book_id, member_id, loan_date, return_date) values (?,?,?,?)";
        try( Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(loan_query)){

            statement.setString(1, String.valueOf(Book_id));
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

    }
    static void bookReturn(){
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;

        System.out.println("Let's Mark a returned book");
        System.out.print("Enter the loan id that want to returned: ");
        int loan_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Set the return date of the book: ");
        String Return_date = scanner.nextLine();

        System.out.print("mark the status (returned or not returned): ");
        String status = scanner.nextLine();

        String return_query = "UPDATE loans SET return_date=?, status=? WHERE loan_id = ?";
        try(Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(return_query)){

            statement.setString(1, Return_date);
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

    }
    static void exit(){
        System.out.println("thanks for interacting with us.. Have a grate day!");
    }
}

