/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.IntegrationTests.RepositoriesTests;

import CleverBank.DataAccess.Repositories.BillRepository;
import Common.Entities.Bill;
import Common.JDBC.DatabaseContext;
import Common.JDBC.Interfaces.IDatabaseContext;
import java.sql.Date;

import java.sql.SQLException;
import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

/**
 *
 * @author AKrot
 */
@Testcontainers
public class BillRepositoryTests {
    private BillRepository _billRepository = new BillRepository(databaseContext);
    
    @Container
    private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
      .withDatabaseName("TestCleverBillDB")
      .withUsername("postgres")
      .withPassword("postgrespw")
      .withInitScript("CleverBankDB.sql")
      .withTmpFs(singletonMap("/var/lib/postgresql/data", "rw"));
    
    private static IDatabaseContext databaseContext;

    @BeforeAll
    public static void init()
    {
        var jdbcContainer = (JdbcDatabaseContainer<?>) container;
        databaseContext = new DatabaseContext(jdbcContainer.getJdbcUrl(),jdbcContainer.getUsername(),jdbcContainer.getPassword());
    }
    
    @Test
    public void Insert_WhenInsertBill_ShouldBeEqualSameBill() throws SQLException
        {
            // arrange
            var billDbSetBeforeInsert = _billRepository.GetAll();
            var expectedBill = new Bill(0, 2, 2, 55.4, "BYN", Date.valueOf("2023-09-01"), Date.valueOf("2028-09-01"), "BY44900000000000000000005578", false);

            // act
            _billRepository.Insert(expectedBill);
            var billDbSetAfterInsert = _billRepository.GetAll();

            // assert
            assertThat(billDbSetBeforeInsert)
                    .extracting(Bill::getBankId, Bill::getUserId, Bill::getBalance, Bill::getCurrency,
                                Bill::getDateOfOpening, Bill::getDateOfExpiration, Bill::getIban, Bill::getIsGetPercent)
                    .doesNotContain(tuple(expectedBill.getBankId(), expectedBill.getUserId(), expectedBill.getBalance(),
                          expectedBill.getCurrency(), expectedBill.getDateOfOpening(), expectedBill.getDateOfExpiration(),
                          expectedBill.getIban(), expectedBill.getIsGetPercent()));
            
            assertThat(billDbSetAfterInsert)
                    .extracting(Bill::getBankId, Bill::getUserId, Bill::getBalance, Bill::getCurrency,
                                Bill::getDateOfOpening, Bill::getDateOfExpiration, Bill::getIban, Bill::getIsGetPercent)
                    .contains(tuple(expectedBill.getBankId(), expectedBill.getUserId(), expectedBill.getBalance(),
                          expectedBill.getCurrency(), expectedBill.getDateOfOpening(), expectedBill.getDateOfExpiration(),
                          expectedBill.getIban(), expectedBill.getIsGetPercent()));
        }
    
    @Test
    public void Update_WhenUpdateBill_ShouldBeEqualSameBill() throws SQLException
        {
            // arrange
            var expectedBill = new Bill(1, 2, 2, 55.4F, "BYN", Date.valueOf("2023-09-01"), Date.valueOf("2028-09-01"), "BY44900000000000000000005578", false);
            
            // act
            _billRepository.Update(expectedBill);
            var actualBill = _billRepository.GetById(expectedBill.id);

            // assert
            assertThat(tuple(actualBill.getId(),actualBill.getBankId(), actualBill.getUserId(), actualBill.getBalance(),
                          actualBill.getCurrency(), actualBill.getDateOfOpening(), actualBill.getDateOfExpiration(),
                          actualBill.getIban(), actualBill.getIsGetPercent()))
                .isEqualTo(tuple(expectedBill.getId(), expectedBill.getBankId(), expectedBill.getUserId(), expectedBill.getBalance(),
                          expectedBill.getCurrency(), expectedBill.getDateOfOpening(), expectedBill.getDateOfExpiration(),
                          expectedBill.getIban(), actualBill.getIsGetPercent()));
        }
    
    @Test
    public void Delete_WhenDeleteBill_ShouldStateDeleted() throws SQLException
        {
            // arrange
            var expectedCount = _billRepository.GetAll().size() - 1;

            // act
            _billRepository.Delete(4);
            var actualCount = _billRepository.GetAll().size();

            // assert
            assertThat(actualCount).isEqualTo(expectedCount);
        }

    @Test
    public void GetAll_WhenHaveEntry_ShouldNotNullBills() throws SQLException
        {
            // arrange // act
            var expectedCount = _billRepository.GetAll().size();

            // assert
            assertThat(expectedCount).isNotNull();
        }

    @Test
    public void GetById_WhenHaveIdEntry_ShouldEntryWithThisId() throws SQLException
        {
            // arrange
            var actualBillDbSet = _billRepository.GetAll();

            // act
            var expectedBill = _billRepository.GetById(1);

            // assert
            assertThat(actualBillDbSet)
                    .extracting(Bill::getId, Bill::getBankId, Bill::getUserId, Bill::getBalance, Bill::getCurrency,
                                Bill::getDateOfOpening, Bill::getDateOfExpiration, Bill::getIban, Bill::getIsGetPercent)
                    .contains(tuple(expectedBill.getId(), expectedBill.getBankId(), expectedBill.getUserId(), expectedBill.getBalance(),
                          expectedBill.getCurrency(), expectedBill.getDateOfOpening(), expectedBill.getDateOfExpiration(),
                          expectedBill.getIban(), expectedBill.getIsGetPercent()));
        }
}
