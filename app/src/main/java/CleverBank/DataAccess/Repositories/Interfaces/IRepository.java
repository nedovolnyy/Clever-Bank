/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.DataAccess.Repositories.Interfaces;

import Common.Entities.BaseEntity;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author AKrot
 */
public interface IRepository<T extends BaseEntity>
    {
        int Insert(T entity) throws SQLException;
        int Update(T entity) throws SQLException;
        int Delete(int id) throws SQLException;
        T GetById(int id) throws SQLException;
        List<T> GetAll() throws SQLException;
    }