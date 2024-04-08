import java.sql.*;

public class Main {
    public static void main(String[] args) {

        Connection myConn = null;
        Statement myStmt = null;
        PreparedStatement myStmtPrepared = null;
        ResultSet myRs = null;
        int rowsAffected = 0;
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3307/project", "root", "123456");
            System.out.println("Connection successful");

            String sql = "insert into employees (first_name, pa_surname,ma_surname, email, salary) values (?,?,?,?,?)";

            myStmtPrepared = myConn.prepareStatement(sql);
            myStmtPrepared.setString(1, "Oscar");
            myStmtPrepared.setString(2, "Rodriguez");
            myStmtPrepared.setString(3, "Marin");
            myStmtPrepared.setString(4, "oscar@mail.com");
            myStmtPrepared.setInt(5, 10000);

            rowsAffected = myStmtPrepared.executeUpdate();
            if(rowsAffected >0) {
                System.out.println("Se ha creado un nuevo empleado");
            }

            myStmt = myConn.createStatement();
            sql = "UPDATE employees SET salary = 20000 WHERE first_name = 'Oscar'";
            rowsAffected= myStmt.executeUpdate(sql);

            while (myRs.next()) {
                System.out.println(myRs.getString("first_name") );
            }




            rowsAffected = myStmt.executeUpdate(sql);
            if(rowsAffected >0) {
                System.out.println("Se ha creado un nuevo empleado");
            }



            //
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from employees");

            //
            while (myRs.next()) {
                System.out.println(myRs.getString("first_name") );
            }

        } catch (Exception exc) {
            exc.printStackTrace();
            System.out.println("Connection failed");
        }

      }

    }
