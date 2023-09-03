/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.DataAccess.Repositories;

import CleverBank.DataAccess.Repositories.Interfaces.IRepository;
import Common.Entities.BaseEntity;
import Common.JDBC.Interfaces.IDatabaseContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author AKrot
 */
abstract class BaseRepository<T extends BaseEntity> implements IRepository<T>{
    private final IDatabaseContext _databaseContext;
    
    protected BaseRepository(IDatabaseContext databaseContext)
        {
            _databaseContext = databaseContext;
        }
    
    /**
     * Base method for insert data.
     * 
     * @param entity Entity
     */
    public int Insert(T entity) throws SQLException
        {
            int i;

            var statement = _databaseContext.getConnection().createStatement();
            i = statement.executeUpdate(GetParamsForInsert(entity));
            return i;
        }

    
    /**
     * Base method for update data.
     * 
     * @param entity Entity
     */
    public int Update(T entity) throws SQLException
        {
            int i;

            var statement = _databaseContext.getConnection().createStatement();
            i = statement.executeUpdate(GetParamsForUpdate(entity));
            return i;
        }

    /**
     * Base method for delete data.
     * 
     * @param id ID of Entity
     */
    public int Delete(int id) throws SQLException
        {
            int i;

            var statement = _databaseContext.getConnection().createStatement();
            i = statement.executeUpdate(GetParamsForDelete(id));
            return i;
        }

    /**
     * Base method for populate data by id.
     * 
     * @param id ID of Entity
     * @return BaseEntity
     */
    public T GetById(int id) throws SQLException
        {
            
            var resultSet = _databaseContext.getConnection().createStatement().executeQuery(GetParamsForGetById(id));
            return Map(resultSet);
        }

    /**
     * Base method for populate all data.
     * 
     * @return List of BaseEntities
     */
    public List<T> GetAll() throws SQLException
        {
            var statement = _databaseContext.getConnection().createStatement();
            var resultSet = statement.executeQuery(GetAllCommandParameters());
            return Maps(resultSet);
        }

        protected abstract String GetParamsForInsert(T entity);
        protected abstract String GetParamsForUpdate(T entity);
        protected abstract String GetParamsForDelete(int id);
        protected abstract String GetParamsForGetById(int id);
        protected abstract String GetAllCommandParameters();
        protected abstract T Map(ResultSet resultSet) throws SQLException;
        protected abstract List<T> Maps(ResultSet resultSet) throws SQLException;
}
