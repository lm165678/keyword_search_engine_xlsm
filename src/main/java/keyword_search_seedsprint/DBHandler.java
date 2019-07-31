package keyword_search_seedsprint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class DBHandler 
{
    private Connection conn;

    public DBHandler()
    {
        conn = null;
    }

    public void init() 
    {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", 
                "root", "root")) 
        {
            if (conn != null) 
            {
                System.out.println("[SUCCESS] Connected to the database");
            }
            else 
            {
                System.out.println("[FAIL] Failed to connect to the database"); 
            }
        }
        catch (SQLException e) 
        {
            System.err.format("[FAIL] SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    // public void add_query(String sql_query)
    // {
    //     try (PreparedStatement preparedStatement = conn.preparedStatement(sql_query)) {
    //         ResultSet resultSet = preparedStatement.executeQuery();
    //     }
    //     catch (SQLException e)
    //     {
    //         System.err.format("[FAIL] SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    //     } 
    //     catch (Exception e) 
    //     {
    //         e.printStackTrace();
    //     }
    // }
}