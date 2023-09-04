/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.DataAccess.Repositories;

import Common.Entities.Transaction;
import Common.JDBC.Interfaces.IDatabaseContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author AKrot
 */
public class TransactionRepository extends BaseRepository<Transaction>{
        private final IDatabaseContext _databaseContext;

        public TransactionRepository(IDatabaseContext databaseContext){
            super(databaseContext);
            _databaseContext = databaseContext;
        }

        @Override
        protected String GetParamsForInsert(Transaction entity)
        {
            return "INSERT INTO public.transaction (\"typeOfTransaction\", \"timeOfTransaction\", \"senderBillId\", \"receiverBillId\", summ, description) VALUES ('" + 
                    entity.getTypeOfTransaction() + "', '" + entity.getTimeOfTransaction() + "', " + entity.getSenderBillId() + ", " +
                    entity.getReceiverBillId() + ", " + entity.getSumm() + ", '" + entity.getDescription() + "');";
        }

        @Override
        protected String GetParamsForUpdate(Transaction entity)
        {
            return "UPDATE public.transaction SET \"typeOfTransaction\" = '" + entity.getTypeOfTransaction() + "'" +
                    ", \"timeOfTransaction\" = '" + entity.getTimeOfTransaction() + "'" +
                    ", \"senderBillId\" = " + entity.getSenderBillId() +
                    ", \"receiverBillId\" = " + entity.getReceiverBillId() +
                    ", summ = " + entity.getSumm() +
                    ", description = '" + entity.getDescription() + "'" +
                   " WHERE id = " + entity.getId();
        }

        @Override
        protected String GetParamsForDelete(int id)
        {
            return "DELETE FROM public.transaction WHERE id = " + id;
        }

        @Override
        protected String GetParamsForGetById(int id)
        {
            return "SELECT id, \"typeOfTransaction\", \"timeOfTransaction\", \"senderBillId\", \"receiverBillId\", summ, description FROM public.transaction WHERE id = " + id;
        }

        @Override
        protected String GetAllCommandParameters()
        {
            return "SELECT id, \"typeOfTransaction\", \"timeOfTransaction\", \"senderBillId\", \"receiverBillId\", summ, description FROM public.transaction";
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
        protected Transaction Map(ResultSet resultSet) throws SQLException
        {
            resultSet.next();
            int id = resultSet.getInt("id");
            String typeOfTransaction = resultSet.getString("typeOfTransaction");
            OffsetDateTime timeOfTransaction = Instant.ofEpochMilli(resultSet.getDate("timeOfTransaction").getTime())
                    .atZone(ZoneId.systemDefault())
                    .toOffsetDateTime();
            int senderBillId = resultSet.getInt("senderBillId");
            int receiverBillId = resultSet.getInt("receiverBillId");
            double summ = resultSet.getDouble("summ");
            String description = resultSet.getString("description");
            
            return new Transaction(id, typeOfTransaction, timeOfTransaction, senderBillId, receiverBillId, summ, description);
        }

        @Override
        protected List<Transaction> Maps(ResultSet resultSet) throws SQLException
        {
            List<Transaction> transactions = new ArrayList<>();
            if (!resultSet.isClosed())
            {
                while (resultSet.next())
                {
                    int id = resultSet.getInt("id");
                    String typeOfTransaction = resultSet.getString("typeOfTransaction");
                    OffsetDateTime timeOfTransaction = Instant.ofEpochMilli(resultSet.getDate("timeOfTransaction").getTime())
                            .atZone(ZoneId.systemDefault())
                            .toOffsetDateTime();
                    int senderBillId = resultSet.getInt("senderBillId");
                    int receiverBillId = resultSet.getInt("receiverBillId");
                    double summ = resultSet.getDouble("summ");
                    String description = resultSet.getString("description");
                    
                    var transaction = new Transaction(id, typeOfTransaction, timeOfTransaction, senderBillId, receiverBillId, summ, description);
                    transactions.add(transaction);
                }
            }

            return transactions;
        }
    }
