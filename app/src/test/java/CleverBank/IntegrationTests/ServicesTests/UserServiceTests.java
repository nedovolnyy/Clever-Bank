/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.IntegrationTests.ServicesTests;

import CleverBank.BusinessLogic.Services.UserService;
import CleverBank.DataAccess.Repositories.UserRepository;
import Common.Entities.User;
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
public class UserServiceTests {
    private UserService _userService = new UserService(new UserRepository(databaseContext));
    
    @Container
    private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
      .withDatabaseName("TestCleverUserDB")
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
    public void Insert_WhenInsertUser_ShouldBeEqualSameUser() throws SQLException
        {
            // arrange
            var userDbSetBeforeInsert = _userService.GetAll();
            var expectedUser = new User(0, "ЮстафВикторРоманович");

            // act
            _userService.Insert(expectedUser);
            var userDbSetAfterInsert = _userService.GetAll();

            // assert
            assertThat(userDbSetBeforeInsert)
                    .extracting(User::getFullName)
                    .doesNotContain(expectedUser.getFullName());
            
            assertThat(userDbSetAfterInsert)
                    .extracting(User::getFullName)
                    .contains(expectedUser.getFullName());
        }
    
    @Test
    public void Update_WhenUpdateUser_ShouldBeEqualSameUser() throws SQLException
        {
            // arrange
            var expectedUser = new User(5, "Юстаф Виктор Романович");
            
            // act
            _userService.Update(expectedUser);
            var actualUser = _userService.GetById(expectedUser.id);

            // assert
            assertThat(tuple(actualUser.getId(), actualUser.getFullName()))
                    .isEqualTo(tuple(expectedUser.getId(), expectedUser.getFullName()));
        }
    
    @Test
    public void Delete_WhenDeleteUser_ShouldStateDeleted() throws SQLException
        {
            // arrange
            var expectedCount = _userService.GetAll().size() - 1;

            // act
            _userService.Delete(4);
            var actualCount = _userService.GetAll().size();

            // assert
            assertThat(actualCount).isEqualTo(expectedCount);
        }

    @Test
    public void GetAll_WhenHaveEntry_ShouldNotNullUsers() throws SQLException
        {
            // arrange // act
            var expectedCount = _userService.GetAll().size();

            // assert
            assertThat(expectedCount).isNotNull();
        }

    @Test
    public void GetById_WhenHaveIdEntry_ShouldEntryWithThisId() throws SQLException
        {
            // arrange
            var actualUserDbSet = _userService.GetAll();

            // act
            var expectedUser = _userService.GetById(1);

            // assert
            assertThat(actualUserDbSet)
                    .extracting(User::getId, User::getFullName)
                    .contains(tuple(expectedUser.getId(), expectedUser.getFullName()));
        }
}
