/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common.Entities;

/**
 *
 * @author AKrot
 */
public class Bill extends BaseEntity{
    public Bill(int id, int bankId, int userId, double balance){
        Id = id;
        BankId = bankId;
        UserId = userId;
        Balance = balance;
    }
    
    int BankId;
    public int getBankId() { return this.BankId; };
    private void setBankId(int BankId) { this.BankId = BankId; };
    
    int UserId;
    public int getUserId() { return this.UserId; };
    private void setUserId(int UserId) { this.UserId = UserId; };
    
    double Balance;
    public double getBalance() { return this.Balance; };
    private void setBalance(double Balance) { this.Balance = Balance; };
}
