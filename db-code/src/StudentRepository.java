import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private Student student;

    public StudentRepository(Student student) {
        this.student = student;
    }

    public static List<Student> getStudents() throws SQLException {
        Statement statement = Main.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from jdbcfirst.students");

        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            students.add(new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("score")));
        }
        return students;
    }

    public static void createStudent(Student student) throws SQLException {
        String sql = "INSERT INTO jdbcfirst.students (name, score) VALUES (?, ?)";

        PreparedStatement ps = Main.getConnection().prepareStatement(sql);
        ps.setString(1, student.getName());
        ps.setDouble(2, student.getScore());

        ps.executeUpdate();
        System.out.println("Student created successfully");
    }

    public static void updateStudent(Student student, int id) throws SQLException {
        String sql = "UPDATE jdbcfirst.students SET name = ?, score = ? WHERE id = ?";
        PreparedStatement ps = Main.getConnection().prepareStatement(sql);

        ps.setString(1, student.getName());
        ps.setDouble(2, student.getScore());
        ps.setInt(3, id);

        ps.executeUpdate();

        System.out.println("Student updated successfully");
    }

    public static void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM jdbcfirst.students WHERE id = ?";
        PreparedStatement ps = Main.getConnection().prepareStatement(sql);

        ps.setInt(1, id);

        ps.executeUpdate();

        System.out.println("Student deleted successfully");
    }

    public static void exit() throws SQLException {
        System.out.println("SQL is exiting");
        System.exit(0);
    }

    public static Student getStudent(int id) throws SQLException {
        String sql = "select * from jdbcfirst.students where id = ?";
        PreparedStatement ps = Main.getConnection().prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        Student student;
        if (resultSet.next()) {
            return new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("score")
            );
        }
        return null;
    }
}
