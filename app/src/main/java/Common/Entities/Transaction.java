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
        Id = id;
        TypeOfTransaction = typeOfTransaction;
        TimeOfTransaction = timeOfTransaction;
        SenderBillId = senderBillId;
        ReceiverBillId = responserBillId;
        Summ = summ;
        Description = description;
    }
    
    String TypeOfTransaction;
    public String getTypeOfTransaction() { return this.TypeOfTransaction; };
    private void setTypeOfTransaction(String TypeOfTransaction) { this.TypeOfTransaction = TypeOfTransaction; };
    
    OffsetDateTime TimeOfTransaction;
    public OffsetDateTime getTimeOfTransaction() { return this.TimeOfTransaction; };
    private void setTimeOfTransaction(OffsetDateTime TimeOfTransaction) { this.TimeOfTransaction = TimeOfTransaction; };
    
    int SenderBillId;
    public int getSenderBillId() { return this.SenderBillId; };
    private void setSenderBillId(int SenderBillId) { this.SenderBillId = SenderBillId; };
    
    int ReceiverBillId;
    public int getReceiverBillId() { return this.ReceiverBillId; };
    private void setReceiverBillId(int ReceiverBillId) { this.ReceiverBillId = ReceiverBillId; };
    
    double Summ;
    public double getSumm() { return this.Summ; };
    private void setSumm(double Summ) { this.Summ = Summ; };
    
    String Description;
    public String getDescription() { return this.Description; };
    private void setDescription(String Description) { this.Description = Description; };
    
}
