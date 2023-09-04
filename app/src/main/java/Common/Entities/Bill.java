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
            Date dateOfOpening, Date dateOfExpiration, String iban, boolean isGetPercent){
        this.id = id;
        this.bankId = bankId;
        this.userId = userId;
        this.balance = balance;
        this.currency = currency;
        this.dateOfOpening = dateOfOpening;
        this.dateOfExpiration = dateOfExpiration;
        this.iban = iban;
        this.isGetPercent = isGetPercent;
    }
    
    int bankId;
    public int getBankId() { return this.bankId; };
    private void setBankId(int bankId) { this.bankId = bankId; };
    
    int userId;
    public int getUserId() { return this.userId; };
    private void setUserId(int userId) { this.userId = userId; };
    
    double balance;
    public double getBalance() { return this.balance; };
    private void setBalance(double balance) { this.balance = balance; };
    
    String currency;
    public String getCurrency() { return this.currency; };
    private void setCurrency(String currency) { this.currency = currency; };
    
    Date dateOfOpening;
    public Date getDateOfOpening() { return this.dateOfOpening; };
    private void setDateOfOpening(Date dateOfOpening) { this.dateOfOpening = dateOfOpening; };
    
    Date dateOfExpiration;
    public Date getDateOfExpiration() { return this.dateOfExpiration; };
    private void setDateOfExpiration(Date dateOfExpiration) { this.dateOfExpiration = dateOfExpiration; };
    
    String iban;
    public String getIban() { return this.iban; };
    private void setIban(String iban) { this.iban = iban; };
    
    boolean isGetPercent;
    public boolean getIsGetPercent() { return this.isGetPercent; };
    private void setIsGetPercent(boolean isGetPercent) { this.isGetPercent = isGetPercent; };
}
