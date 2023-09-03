/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common.JDBC;

import Common.JDBC.Interfaces.IDatabaseContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author AKrot
 */
public class DatabaseContext implements IDatabaseContext{
    public DatabaseContext(String urlDB, String userDB, String passwordDB)
        {
            this.urlDB = urlDB;
            this.userDB = userDB;
            this.passwordDB = passwordDB;
        }
    
    private final String urlDB;
    private final String userDB;
    private final String passwordDB;
    
    Connection _connection = null;

    public Connection getConnection()
        {
            try {
		Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e)
                {
                    System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
                    e.printStackTrace();
                    return null;
                }
            
            try {
		_connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
                return _connection;
                } catch (SQLException e)
                {
                    System.out.println("Connection Failed");
                    e.printStackTrace();
		return null;
                }
        }
}
