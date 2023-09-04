/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common.Entities;

import java.time.OffsetDateTime;

/**
 *
 * @author AKrot
 */
public class Transaction extends BaseEntity{
    public Transaction(int id, String typeOfTransaction, OffsetDateTime timeOfTransaction,
            int senderBillId, int responserBillId, double summ, String description){
        this.id = id;
        this.typeOfTransaction = typeOfTransaction;
        this.timeOfTransaction = timeOfTransaction;
        this.senderBillId = senderBillId;
        this.receiverBillId = responserBillId;
        this.summ = summ;
        this.description = description;
    }
    
    String typeOfTransaction;
    public String getTypeOfTransaction() { return this.typeOfTransaction; };
    private void setTypeOfTransaction(String typeOfTransaction) { this.typeOfTransaction = typeOfTransaction; };
    
    OffsetDateTime timeOfTransaction;
    public OffsetDateTime getTimeOfTransaction() { return this.timeOfTransaction; };
    private void setTimeOfTransaction(OffsetDateTime timeOfTransaction) { this.timeOfTransaction = timeOfTransaction; };
    
    int senderBillId;
    public int getSenderBillId() { return this.senderBillId; };
    private void setSenderBillId(int senderBillId) { this.senderBillId = senderBillId; };
    
    int receiverBillId;
    public int getReceiverBillId() { return this.receiverBillId; };
    private void setReceiverBillId(int receiverBillId) { this.receiverBillId = receiverBillId; };
    
    double summ;
    public double getSumm() { return this.summ; };
    private void setSumm(double summ) { this.summ = summ; };
    
    String description;
    public String getDescription() { return this.description; };
    private void setDescription(String description) { this.description = description; };
    
}
