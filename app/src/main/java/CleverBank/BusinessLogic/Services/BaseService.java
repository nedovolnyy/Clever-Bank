/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.BusinessLogic.Services;

import CleverBank.BusinessLogic.Services.Interfaces.IService;
import CleverBank.DataAccess.Repositories.Interfaces.IRepository;
import Common.Entities.BaseEntity;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author AKrot
 */
abstract class BaseService<T extends BaseEntity> implements IService<T> {
    private final IRepository<T> _entityRepository;

    protected BaseService(IRepository<T> entityRepository) {
        _entityRepository = entityRepository;
    }

    public int Insert(T entity) throws SQLException {
        return _entityRepository.Insert(entity);
    }

    public int Update(T entity) throws SQLException {
        return _entityRepository.Update(entity);
    }

    public int Delete(int id) throws SQLException {
        return _entityRepository.Delete(id);
    }
        
    public T GetById(int id) throws SQLException {
        return _entityRepository.GetById(id);
    }
        
    public List<T> GetAll() throws SQLException {
        return _entityRepository.GetAll();
    }
}
