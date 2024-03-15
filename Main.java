import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Book> bookList = new ArrayList<>();
    static ArrayList<Student> studentList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBooks();

        while (true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option (1-3): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Student.login();
                    break;
                case 2:
                    Admin.login();
                    break;
                case 3:
                    System.out.println("Thank you. Exiting program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void initializeBooks() {
        bookList.add(new Book("388c-e691-9152", "JAVA", "author1", "Sejarah", 4));
        bookList.add(new Book("ed90-be30-5cdb", "PYTHON", "author2", "Sejarah", 0));
        bookList.add(new Book("d95e-0c4a-9523", "JAVASCRIPT", "author3", "Sejarah", 2));
    }
}

class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private int stock;

    public Book(String id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getCategory() {
        return category;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}

class Student {
    private String name;
    private String nim;
    private String programStudi;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Student(String name, String nim, String programStudi) {
        this.name = name;
        this.nim = nim;
        this.programStudi = programStudi;
    }

    public static void login() {
        System.out.print("Input NIM (input 99 to go back): ");
        String nim = Main.scanner.nextLine();
        if (nim.equals("99")) {
            return;
        }
        if (nim.length() == 15) {
            Student student = findStudentByNim(nim);
            if (student != null) {
                student.menuStudent();
            } else {
                System.out.println("Student not found.");
            }
        } else {
            System.out.println("Invalid NIM. NIM must be 15 digits.");
        }
    }
    private static Student findStudentByNim(String nim) {
        for (Student student : Main.studentList) {
            if (student.getNim().equals(nim)) {
                return student;
            }
        }
        return null;
    }

    public void menuStudent() {
        while (true) {
            System.out.println("==== Student Menu ====");
            System.out.println("1. Display Borrowed Books");
            System.out.println("2. Display Available Books and Borrow");
            System.out.println("3. Logout");
            System.out.print("Choose option (1-3): ");
            int choice = Main.scanner.nextInt();
            Main.scanner.nextLine();

            switch (choice) {
                case 1:
                    displayBorrowedBooks();
                    break;
                case 2:
                    displayAndBorrowBooks();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void displayBorrowedBooks() {
        System.out.println("=== Borrowed Books ===");
        System.out.println("ID | Title | Author | Category | Stock");
        for (Book borrowedBook : borrowedBooks) {
            for (Book book : Main.bookList) {
                if (book.getId().equals(borrowedBook.getId())) {
                    System.out.println(book.getId() + " | " + book.getTitle() + " | " + book.getAuthor() + " | " + book.getCategory() + " | " + book.getStock());
                    break;
                }
            }
        }
    }
    private void displayAndBorrowBooks() {
        System.out.println("=== Available Books ===");
        System.out.println("ID | Title | Author | Category | Stock");
        for (Book book : Main.bookList) {
            System.out.println(book.getId() + " | " + book.getTitle() + " | " + book.getAuthor() + " | " + book.getCategory() + " | " + book.getStock());
        }

        System.out.print("Enter the ID of the book you want to borrow: ");
        String bookId = Main.scanner.nextLine();
        boolean bookFound = false;
        for (Book book : Main.bookList) {
            if (book.getId().equals(bookId)) {
                if (book.getStock() > 0) {
                    System.out.println("Book borrowed successfully!");
                    borrowedBooks.add(book);
                    book.setStock(book.getStock() - 1);
                } else {
                    System.out.println("Sorry, this book is out of stock.");
                }
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            System.out.println("Book not found.");
        }
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public String getProgramStudi() {
        return programStudi;
    }
}

class Admin {
    public static void login() {
        System.out.print("Enter your username : ");
        String usn = Main.scanner.nextLine();
        System.out.print("Enter your password : ");
        String pass = Main.scanner.nextLine();

        if (usn.equals("admin") && pass.equals("admin")) {
            System.out.println("Successful login as Admin");
            menuAdmin();
        } else {
            System.out.println("Invalid Credentials for Admin");
        }
    }

    private static void menuAdmin() {
        while (true) {
            System.out.println("==== Admin Menu ====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Back");
            System.out.print("Choose option (1-3): ");
            int choice = Main.scanner.nextInt();
            Main.scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = Main.scanner.nextLine();
        System.out.print("Enter student NIM (15 digits): ");
        String nim = Main.scanner.nextLine();
        while (nim.length() != 15) {
            System.out.println("Invalid NIM. NIM must be 15 digits.");
            System.out.print("Enter student NIM (15 digits): ");
            nim = Main.scanner.nextLine();
        }
        System.out.print("Enter student program: ");
        String program = Main.scanner.nextLine();

        Student student = new Student(name, nim, program);
        Main.studentList.add(student);
        System.out.println("Student added successfully.");
    }

    private static void displayStudents() {
        System.out.println("=== Student List ===");
        for (Student student : Main.studentList) {
            System.out.println(student.getName() + " (" + student.getNim() + ") - " + student.getProgramStudi());
        }
    }
}