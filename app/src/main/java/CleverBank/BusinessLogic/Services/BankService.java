/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.BusinessLogic.Services;

import CleverBank.DataAccess.Repositories.Interfaces.IRepository;
import Common.Entities.Bank;

/**
 *
 * @author AKrot
 */
public class BankService extends BaseService<Bank>{
    private final IRepository<Bank> _bankRepository;
    
    public BankService(IRepository<Bank> bankRepository){
        super(bankRepository);
        _bankRepository = bankRepository;
    }
}
