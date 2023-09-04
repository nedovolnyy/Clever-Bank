/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.IntegrationTests.RepositoriesTests;

import CleverBank.DataAccess.Repositories.TransactionRepository;
import Common.Entities.Transaction;
import Common.JDBC.DatabaseContext;
import Common.JDBC.Interfaces.IDatabaseContext;

import java.sql.SQLException;
import java.time.OffsetDateTime;
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
public class TransactionRepositoryTests {
    private TransactionRepository _transactionRepository = new TransactionRepository(databaseContext);
    
    @Container
    private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
      .withDatabaseName("TestCleverTransactionDB")
      .withUsername("postgres")
      .withPassword("postgrespw")
      .withInitScript("test.sql")
      .withTmpFs(singletonMap("/var/lib/postgresql/data", "rw"));
    
    private static IDatabaseContext databaseContext;

    @BeforeAll
    public static void init()
    {
        var jdbcContainer = (JdbcDatabaseContainer<?>) container;
        databaseContext = new DatabaseContext(jdbcContainer.getJdbcUrl(),jdbcContainer.getUsername(),jdbcContainer.getPassword());
    }
    
    @Test
    public void Insert_WhenInsertTransaction_ShouldBeEqualSameTransaction() throws SQLException
        {
            // arrange
            var transactionDbSetBeforeInsert = _transactionRepository.GetAll();
            var expectedTransaction = new Transaction(0, "Перевод", OffsetDateTime.parse("2016-10-26T00:00+03:00"),
                                               2, 3, 12.15, "В счёт задолженности");

            // act
            _transactionRepository.Insert(expectedTransaction);
            var transactionDbSetAfterInsert = _transactionRepository.GetAll();

            // assert
            assertThat(transactionDbSetBeforeInsert)
                    .extracting(Transaction::getTypeOfTransaction, Transaction::getTimeOfTransaction, Transaction::getSenderBillId,
                                Transaction::getReceiverBillId, Transaction::getSumm, Transaction::getDescription)
                    .doesNotContain(tuple(expectedTransaction.getTypeOfTransaction(), expectedTransaction.getTimeOfTransaction(),
                            expectedTransaction.getSenderBillId(), expectedTransaction.getReceiverBillId(),
                            expectedTransaction.getSumm(), expectedTransaction.getDescription()));
            
            assertThat(transactionDbSetAfterInsert)
                    .extracting(Transaction::getTypeOfTransaction, Transaction::getTimeOfTransaction, Transaction::getSenderBillId,
                                Transaction::getReceiverBillId, Transaction::getSumm, Transaction::getDescription)
                    .contains(tuple(expectedTransaction.getTypeOfTransaction(), expectedTransaction.getTimeOfTransaction(),
                            expectedTransaction.getSenderBillId(), expectedTransaction.getReceiverBillId(),
                            expectedTransaction.getSumm(), expectedTransaction.getDescription()));
        }
    
    @Test
    public void Update_WhenUpdateTransaction_ShouldBeEqualSameTransaction() throws SQLException
        {
            // arrange
            var expectedTransaction = new Transaction(1, "Перевод", OffsetDateTime.parse("2016-10-26T00:00+03:00"),
                                               2, 3, 12.15, "В счёт задолженности");
            
            // act
            _transactionRepository.Update(expectedTransaction);
            var actualTransaction = _transactionRepository.GetById(expectedTransaction.Id);

            // assert
            assertThat(tuple(actualTransaction.getId(), actualTransaction.getTypeOfTransaction(), actualTransaction.getTimeOfTransaction(),
                       actualTransaction.getSenderBillId(), actualTransaction.getReceiverBillId(),
                       actualTransaction.getSumm(), actualTransaction.getDescription()))
                    .isEqualTo(tuple(expectedTransaction.getId(), expectedTransaction.getTypeOfTransaction(), expectedTransaction.getTimeOfTransaction(),
                            expectedTransaction.getSenderBillId(), expectedTransaction.getReceiverBillId(),
                            expectedTransaction.getSumm(), expectedTransaction.getDescription()));
        }
    
    @Test
    public void Delete_WhenDeleteTransaction_ShouldStateDeleted() throws SQLException
        {
            // arrange
            var expectedCount = _transactionRepository.GetAll().size() - 1;

            // act
            _transactionRepository.Delete(2);
            var actualCount = _transactionRepository.GetAll().size();

            // assert
            assertThat(actualCount).isEqualTo(expectedCount);
        }

    @Test
    public void GetAll_WhenHaveEntry_ShouldNotNullTransactions() throws SQLException
        {
            // arrange // act
            var expectedCount = _transactionRepository.GetAll().size();

            // assert
            assertThat(expectedCount).isNotNull();
        }

    @Test
    public void GetById_WhenHaveIdEntry_ShouldEntryWithThisId() throws SQLException
        {
            // arrange
            var actualTransactionDbSet = _transactionRepository.GetAll();

            // act
            var expectedTransaction = _transactionRepository.GetById(1);

            // assert
            assertThat(actualTransactionDbSet)
                    .extracting(Transaction::getId, Transaction::getTypeOfTransaction, Transaction::getTimeOfTransaction, Transaction::getSenderBillId,
                                Transaction::getReceiverBillId, Transaction::getSumm, Transaction::getDescription)
                    .contains(tuple(expectedTransaction.getId(), expectedTransaction.getTypeOfTransaction(), 
                            expectedTransaction.getTimeOfTransaction(), expectedTransaction.getSenderBillId(),
                            expectedTransaction.getReceiverBillId(), expectedTransaction.getSumm(), expectedTransaction.getDescription()));
        }
}
