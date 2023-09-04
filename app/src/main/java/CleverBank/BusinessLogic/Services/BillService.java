/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.BusinessLogic.Services;

import CleverBank.DataAccess.Repositories.Interfaces.IRepository;
import Common.Entities.Bill;

/**
 *
 * @author AKrot
 */
public class BillService extends BaseService<Bill>{
    private final IRepository<Bill> _billRepository;
    
    public BillService(IRepository<Bill> billRepository){
        super(billRepository);
        _billRepository = billRepository;
    }
}
