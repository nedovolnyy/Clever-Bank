/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.DataAccess.Repositories;

import Common.Entities.Bank;
import Common.JDBC.Interfaces.IDatabaseContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author AKrot
 */
public class BankRepository extends BaseRepository<Bank>{
        private final IDatabaseContext _databaseContext;

        public BankRepository(IDatabaseContext databaseContext){
            super(databaseContext);
            _databaseContext = databaseContext;
        }

        @Override
        protected String GetParamsForInsert(Bank entity)
        {
            return "INSERT INTO bank (name) VALUES (" + entity.getName() +", " +
                   "SELECT CAST (SCOPE_IDENTITY() AS INT)";
        }

        @Override
        protected String GetParamsForUpdate(Bank entity)
        {
            return "UPDATE bank SET name = " + entity.getName() +
                   " WHERE id = " + entity.getId();
        }

        @Override
        protected String GetParamsForDelete(int id)
        {
            return "DELETE FROM bank WHERE id = " + id;
        }

        @Override
        protected String GetParamsForGetById(int id)
        {
            return "SELECT id, name FROM bank WHERE id = " + id;
        }

        @Override
        protected String GetAllCommandParameters()
        {
            return "SELECT id, name FROM bank";
        }

        /*
        /// <summary>
        /// Base Method for populate data by id.
        /// </summary>
        /// <param name="id">id.</param>
        /// <returns><see cref="Area"/>List&lt;Area&gt;.</returns>
        IEnumerable<Area> IAreaRepository.GetAllByLayoutId(int id)
        {
            var cmd = _databaseContext.Connection.CreateCommand();
            cmd.CommandText = "SELECT Id, LayoutId, Description, CoordX, CoordY FROM Area WHERE LayoutId = @LayoutId";
            cmd.CommandType = CommandType.Text;
            cmd.Parameters.AddWithValue("@LayoutId", id);
            using var reader = cmd.ExecuteReader();
            return Maps(reader);
        }
*/
        @Override
        protected Bank Map(ResultSet resultSet) throws SQLException
        {
            resultSet.next();
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Bank(id, name);
        }

        @Override
        protected List<Bank> Maps(ResultSet resultSet) throws SQLException
        {
            List<Bank> banks = new ArrayList<>();
            if (!resultSet.isClosed())
            {
                while (resultSet.next())
                {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    var bank = new Bank(id, name);
                    banks.add(bank);
                }
            }

            return banks;
        }
    }
