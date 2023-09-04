/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common.Entities;

import java.sql.Date;

/**
 *
 * @author AKrot
 */
public class Bill extends BaseEntity{
    public Bill(int id, int bankId, int userId, double balance, String currency,
            Date dateOfOpening, Date dateOfExpiration, String iban){
        Id = id;
        BankId = bankId;
        UserId = userId;
        Balance = balance;
        Currency = currency;
        DateOfOpening = dateOfOpening;
        DateOfExpiration = dateOfExpiration;
        IBAN = iban;
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
    
    String Currency;
    public String getCurrency() { return this.Currency; };
    private void setCurrency(String Currency) { this.Currency = Currency; };
    
    Date DateOfOpening;
    public Date getDateOfOpening() { return this.DateOfOpening; };
    private void setDateOfOpening(Date DateOfOpening) { this.DateOfOpening = DateOfOpening; };
    
    Date DateOfExpiration;
    public Date getDateOfExpiration() { return this.DateOfExpiration; };
    private void setDateOfExpiration(Date DateOfExpiration) { this.DateOfExpiration = DateOfExpiration; };
    
    String IBAN;
    public String getIBAN() { return this.IBAN; };
    private void setIBAN(String IBAN) { this.IBAN = IBAN; };
}
