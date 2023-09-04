/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package CleverBank;

import CleverBank.BusinessLogic.Services.BankService;
import CleverBank.BusinessLogic.Services.BillService;
import CleverBank.BusinessLogic.Services.UserService;
import CleverBank.DataAccess.Repositories.BankRepository;
import CleverBank.DataAccess.Repositories.BillRepository;
import CleverBank.DataAccess.Repositories.UserRepository;
import Common.Entities.Bill;
import Common.Entities.Comparators.BillsComparator;
import Common.JDBC.DatabaseContext;
import Common.JDBC.Interfaces.IDatabaseContext;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class App {    
    private static IDatabaseContext databaseContext;
    private static float percentOfDeposit;
    private static boolean isNeedDoDeposit;
    
    private static void initializeConfigurationFile() throws IOException{
        YamlReader reader = new YamlReader();
        Configuration read = reader.read("src/main/resources/Configuration.yml", Configuration.class);
        
        databaseContext = new DatabaseContext(read.postgresql.get("db.url"),read.postgresql.get("db.user"),read.postgresql.get("db.password"));
        percentOfDeposit = Float.parseFloat(read.cleverBank.get("percentOfDeposit"));
    }
    
    public static void checkTimerForDepositPerccentAsync(long timeout) {
        final Thread t = new Thread();
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                if (t.isAlive()) {
                    t.interrupt();
                }
                
        if (LocalDate.now().getDayOfMonth() == YearMonth.now().atEndOfMonth().getDayOfMonth()){
            isNeedDoDeposit = !isNeedDoDeposit;
        }
        else
        {
            if (isNeedDoDeposit)
                {
                    isNeedDoDeposit = !isNeedDoDeposit;
                    try {
                        addedDepositePercent();
                    } catch (SQLException ex) {
                    }
                }
        }
        
        checkTimerForDepositPerccentAsync(timeout);
            }
        }, timeout * 1000);
        t.start();
    }
    
    private static void addedDepositePercent() throws SQLException{
        var billService = new BillService( new BillRepository(databaseContext));
        var bills = billService.GetAll();
        for ( var bill : bills){
            var temporaryBill = new Bill(
                bill.getId(), 
                bill.getBankId(), 
                bill.getUserId(),
                bill.getBalance() * (1 + percentOfDeposit / 100), 
                bill.getCurrency(), 
                bill.getDateOfOpening(), 
                bill.getDateOfExpiration(), 
                bill.getIban(),
                !bill.getIsGetPercent());
            billService.Update(temporaryBill);
            }
        
    }
    
    private static void changeBalance() throws SQLException {
        var in = new Scanner(System.in);

        var bills = new BillService( new BillRepository(databaseContext)).GetAll();
        
        Collections.sort(bills, new BillsComparator());
        
        System.out.println("Choice bill for +/- balance:\n");
        for (var bill : bills)
            System.out.println(bill.getId() + " | " +
                    new UserService(new UserRepository(databaseContext)).GetById(bill.getUserId()).getFullName() + " | " +
                    new BankService(new BankRepository(databaseContext)).GetById(bill.getBankId()).getName() + " | " +
                    bill.getBalance() + " | " +
                    bill.getCurrency() + " | " +
                    bill.getDateOfOpening() + " | " +
                    bill.getDateOfExpiration() + " | " +
                    bill.getIban() + " | " +
                    bill.getIsGetPercent());
        
        System.out.print("Input a Bill's identifictor: ");
        int billId = in.nextInt() - 1;
        System.out.println(bills.get(billId).getId() + " | " +
                    new UserService(new UserRepository(databaseContext)).GetById(bills.get(billId).getUserId()).getFullName() + " | " +
                    new BankService(new BankRepository(databaseContext)).GetById(bills.get(billId).getBankId()).getName() + " | " +
                    bills.get(billId).getBalance());
        
        System.out.printf("Correnct balance: ");
        double correctBalance = in.nextDouble();
        var updatedBill = new Bill(
                bills.get(billId).getId(), 
                bills.get(billId).getBankId(), 
                bills.get(billId).getUserId(),
                bills.get(billId).getBalance() + correctBalance, 
                bills.get(billId).getCurrency(), 
                bills.get(billId).getDateOfOpening(), 
                bills.get(billId).getDateOfExpiration(), 
                bills.get(billId).getIban(),
                bills.get(billId).getIsGetPercent());
        
        new BillService(new BillRepository(databaseContext)).Update(updatedBill);
        
        System.out.printf("Correnct balance: %f\n", new BillService(new BillRepository(databaseContext)).GetById(billId + 1).getBalance());
        
        in.close();
    }
    
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        initializeConfigurationFile();
        checkTimerForDepositPerccentAsync(30);

        var in = new Scanner(System.in);        
        
        System.out.println("\tWelcome to Bill's Management!\n");
        System.out.println("1 - Change balance");
        System.out.println("2 - ...(Sent balance)");
        System.out.println("3 - ...");
        System.out.println("0 - return 0");
        
        int choice=in.nextInt();
        
        switch(choice){
            case 1 -> changeBalance();
            case 2 -> {
                return;
            }
            case 3 -> {
                return;
            }
            case 0 -> {
                return;
            }
        }
        
        in.close();        
    }
}
