/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.BusinessLogic.Services;

import CleverBank.DataAccess.Repositories.Interfaces.IRepository;
import Common.Entities.Transaction;

/**
 *
 * @author AKrot
 */
public class TransactionService extends BaseService<Transaction>{
    private final IRepository<Transaction> _TransactionRepository;
    
    public TransactionService(IRepository<Transaction> TransactionRepository){
        super(TransactionRepository);
        _TransactionRepository = TransactionRepository;
    }
}
