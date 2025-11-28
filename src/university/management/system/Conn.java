package university.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Connection connection;
    Statement statement;

    Conn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // REPLACE "AyushVish" with the correct MySQL root password.
            connection = DriverManager.getConnection("jdbc:mysql:///universitymanagement","root","admin");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}