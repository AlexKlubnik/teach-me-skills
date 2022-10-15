package homework20;

import java.sql.*;

public class JDBCHomework {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/test_db";
    public static final String USER = "  ";
    public static final String PASSWORD = "   ";
    public static final String SELECT_ALL_TMS_STUDENTS = "select id, first_name as name, age from tms_students";
    public static final String CREATE_STUDENTS_CITIES_TABLE = "create table if not exists students_cities\n" +
            "(\n" +
            "        id integer primary key auto_increment,\n" +
            "        student_id integer not null,\n" +
            "        city varchar(20) not null,\n" +
            "        foreign key (student_id) references tms_students (id)\n" +
            ")";
    public static final String ADD_CITIES = "insert into students_cities (student_id, city)\n" +
            "values (1, 'Pinsk'),\n" +
            "       (2, 'Brest'),\n" +
            "       (3, 'Baranovichi'),\n" +
            "       (4, 'Vitebsk'),\n" +
            "       (5, 'Gomel'),\n" +
            "       (6, 'Mogilev'),\n" +
            "       (7, 'Bobruysk'),\n" +
            "       (8, 'Zhabinka'),\n" +
            "       (9, 'Grodno'),\n" +
            "       (10, 'Vitebsk')";
    public static final String GET_HOMELAND_OF_STUDENTS = "select first_name, city\n" +
            "from tms_students as student\n" +
            "         join students_cities city on student.id = city.student_id";
    public static final String INSERT_STUDENT_INTO_TMS_STUDENTS = "insert into tms_students (first_name, age) values (?, ?)";
    public static final String INSERT_CITY_INTO_STUDENTS_CITIES = "insert into students_cities(student_id, city) values (?, ?)";
    public static final String DELETE_STUDENT_FROM_TMS_STUDENTS = "delete from tms_students where id = ?";
    public static final String DELETE_CITY_FROM_STUDENTS_CITIES = "delete from students_cities where student_id = ?";

    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName(JDBC_DRIVER);

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();

//            createAndFillStudentsCitiesTable(statement);
            printStudents(statement);
            System.out.println("===========================================");
            printStudentsHomeland(statement);
            System.out.println("===========================================");

//            insertStudentToTable(connection, "Ann", 27);
//            insertCityToTable(connection, 11, "Gantsevichi");
//            deleteCity(connection, 11);
//            deleteStudent(connection, 11);
            printStudents(statement);
            printStudentsHomeland(statement);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void printStudents(Statement statement) throws SQLException {

        ResultSet tmsStudents = statement.executeQuery(SELECT_ALL_TMS_STUDENTS);

        while (tmsStudents.next()) {
            System.out.println(
                    tmsStudents.getInt("id")
                            + " "
                            + tmsStudents.getString("name")
                            + " "
                            + tmsStudents.getInt("age")
            );
        }
    }

    public static void createAndFillStudentsCitiesTable(Statement statement) throws SQLException {
        statement.execute(CREATE_STUDENTS_CITIES_TABLE);
        statement.executeUpdate(ADD_CITIES);
    }

    public static void printStudentsHomeland(Statement statement) throws SQLException {

        ResultSet studentsCites = statement.executeQuery(GET_HOMELAND_OF_STUDENTS);

        while (studentsCites.next()) {
            System.out.println("Student " + studentsCites.getString("first_name")
                    + " was born in " + studentsCites.getString("city"));
        }
    }

    public static void insertStudentToTable(Connection connection, String name, int age) {
        String sql = INSERT_STUDENT_INTO_TMS_STUDENTS;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertCityToTable(Connection connection, int studentId, String city) {
        String sql = INSERT_CITY_INTO_STUDENTS_CITIES;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, city);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteStudent(Connection connection, int studentId) {
        String sql = DELETE_STUDENT_FROM_TMS_STUDENTS;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCity(Connection connection, int studentId) {
        String sql = DELETE_CITY_FROM_STUDENTS_CITIES;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
