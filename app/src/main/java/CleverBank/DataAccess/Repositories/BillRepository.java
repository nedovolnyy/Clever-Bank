/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.DataAccess.Repositories;

import Common.Entities.Bill;
import Common.JDBC.Interfaces.IDatabaseContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author AKrot
 */
public class BillRepository extends BaseRepository<Bill>{
        private final IDatabaseContext _databaseContext;

        public BillRepository(IDatabaseContext databaseContext){
            super(databaseContext);
            _databaseContext = databaseContext;
        }

        @Override
        protected String GetParamsForInsert(Bill entity)
        {
            return "INSERT INTO bill (\"bankId\", \"userId\", balance) VALUES (" + 
                    entity.getBankId() +", " +
                    entity.getUserId() +", " +
                    entity.getBalance()+", " +
                   "SELECT CAST (SCOPE_IDENTITY() AS INT)";
        }

        @Override
        protected String GetParamsForUpdate(Bill entity)
        {
            return "UPDATE bill SET \"bankId\" = " + entity.getBankId() +
                    ", \"userId\" = " + entity.getUserId() +
                    ", \"balance\" = " + entity.getBalance()+
                   " WHERE id = " + entity.getId();
        }

        @Override
        protected String GetParamsForDelete(int id)
        {
            return "DELETE FROM bill WHERE id = " + id;
        }

        @Override
        protected String GetParamsForGetById(int id)
        {
            return "SELECT id, \"bankId\", \"userId\", balance FROM bill WHERE id = " + id;
        }

        @Override
        protected String GetAllCommandParameters()
        {
            return "SELECT id, \"bankId\", \"userId\", balance FROM bill";
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
        protected Bill Map(ResultSet resultSet) throws SQLException
        {
            resultSet.next();
            int id = resultSet.getInt("id");
            int bankId = resultSet.getInt("bankId");
            int userId = resultSet.getInt("userId");
            double balance = resultSet.getDouble("balance");
            return new Bill(id, bankId, userId, balance);
        }

        @Override
        protected List<Bill> Maps(ResultSet resultSet) throws SQLException
        {
            List<Bill> bills = new ArrayList<>();
            if (!resultSet.isClosed())
            {
                while (resultSet.next())
                {
                    int id = resultSet.getInt("id");
                    int bankId = resultSet.getInt("bankId");
                    int userId = resultSet.getInt("userId");
                    double balance = resultSet.getDouble("balance");
                    var bill = new Bill(id, bankId, userId, balance);
                    bills.add(bill);
                }
            }

            return bills;
        }
    }
