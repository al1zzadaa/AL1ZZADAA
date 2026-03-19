import java.sql.*;
import java.util.Scanner;

class Main{
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the SQL Server");

        System.out.println("Choose command you want to run:");
        System.out.println();
        System.out.println("1. Create Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Print All Students");
        System.out.println("5. Exit");

        while(true){
            switch(sc.nextInt()){
                case 1:
                    System.out.println("Enter Student information:");
                    System.out.println("Name:");
                    String name = sc.next();
                    System.out.println("Score:");
                    double score = sc.nextDouble();
                    StudentRepository.createStudent(new Student(
                            null,
                            name,
                            score));
                    break;
                    case 2:
                        System.out.println("Enter Student information to be updated:");
                        System.out.println("ID of student to be updated:");
                        int id = sc.nextInt();
                        System.out.println("New Name:");
                        String newName = sc.next();
                        System.out.println("New Score:");
                        double newScore = sc.nextDouble();
                        StudentRepository.updateStudent(new Student(
                                null,
                                newName,
                                newScore), id);
                        break;
                        case 3:
                            System.out.println("Enter Student id to be deleted:");
                            StudentRepository.deleteStudent(sc.nextInt());
                            break;
                            case 4:
                                StudentRepository.getStudents().forEach(System.out::println);
                                System.out.println("Students printed successfully");
                                break;
                                case 5:
                                    StudentRepository.exit();
                                    break;
            }
        }

        System.out.println(StudentRepository.getStudent(4));
    }



    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "sadiqqidas777";

            connection = DriverManager.getConnection(url, user, password);

            System.out.println("Connected to database successfully");
            System.out.println("-----------------------------------");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        }catch (SQLException e){
            System.out.println("SQL Error");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
