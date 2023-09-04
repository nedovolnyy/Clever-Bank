/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.IntegrationTests.ServicesTests;

import CleverBank.BusinessLogic.Services.BankService;
import CleverBank.DataAccess.Repositories.BankRepository;
import Common.Entities.Bank;
import Common.JDBC.DatabaseContext;
import Common.JDBC.Interfaces.IDatabaseContext;

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
public class BankServiceTests {
    private BankService _bankService = new BankService(new BankRepository(databaseContext));
    
    @Container
    private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
      .withDatabaseName("TestCleverBankDB")
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
    public void Insert_WhenInsertBank_ShouldBeEqualSameBank() throws SQLException
        {
            // arrange
            var bankDbSetBeforeInsert = _bankService.GetAll();
            var expectedBank = new Bank(0, "Frasabank");

            // act
            _bankService.Insert(expectedBank);
            var bankDbSetAfterInsert = _bankService.GetAll();

            // assert
            assertThat(bankDbSetBeforeInsert)
                    .extracting(Bank::getName)
                    .doesNotContain(expectedBank.getName());
            
            assertThat(bankDbSetAfterInsert)
                    .extracting(Bank::getName)
                    .contains(expectedBank.getName());
        }
    
    @Test
    public void Update_WhenUpdateBank_ShouldBeEqualSameBank() throws SQLException
        {
            // arrange
            var expectedBank = new Bank(5, "Frasabank");
            
            // act
            _bankService.Update(expectedBank);
            var actualBank = _bankService.GetById(expectedBank.id);

            // assert
            assertThat(tuple(actualBank.getId(), actualBank.getName()))
                    .isEqualTo(tuple(expectedBank.getId(), expectedBank.getName()));
        }
    
    @Test
    public void Delete_WhenDeleteBank_ShouldStateDeleted() throws SQLException
        {
            // arrange
            var expectedCount = _bankService.GetAll().size() - 1;

            // act
            _bankService.Delete(4);
            var actualCount = _bankService.GetAll().size();

            // assert
            assertThat(actualCount).isEqualTo(expectedCount);
        }

    @Test
    public void GetAll_WhenHaveEntry_ShouldNotNullBanks() throws SQLException
        {
            // arrange // act
            var expectedCount = _bankService.GetAll().size();

            // assert
            assertThat(expectedCount).isNotNull();
        }

    @Test
    public void GetById_WhenHaveIdEntry_ShouldEntryWithThisId() throws SQLException
        {
            // arrange
            var actualBankDbSet = _bankService.GetAll();

            // act
            var expectedBank = _bankService.GetById(1);

            // assert
            assertThat(actualBankDbSet)
                    .extracting(Bank::getId, Bank::getName)
                    .contains(tuple(expectedBank.getId(), expectedBank.getName()));
        }
}
