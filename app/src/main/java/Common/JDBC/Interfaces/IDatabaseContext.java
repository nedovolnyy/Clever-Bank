/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Common.JDBC.Interfaces;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author AKrot
 */
public interface IDatabaseContext {
    public Connection getConnection() throws SQLException;
}
