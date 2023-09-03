/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.DataAccess.Repositories;

import Common.Entities.User;
import Common.JDBC.Interfaces.IDatabaseContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author AKrot
 */
public class UserRepository extends BaseRepository<User>{
        private final IDatabaseContext _databaseContext;

        public UserRepository(IDatabaseContext databaseContext){
            super(databaseContext);
            _databaseContext = databaseContext;
        }

        @Override
        protected String GetParamsForInsert(User entity)
        {
            return "INSERT INTO public.user (\"fullName\") VALUES (" + entity.getFullName() +", " +
                   "SELECT CAST (SCOPE_IDENTITY() AS INT)";
        }

        @Override
        protected String GetParamsForUpdate(User entity)
        {
            return "UPDATE public.user SET \"fullName\" = " + entity.getFullName() +
                   " WHERE id = " + entity.getId();
        }

        @Override
        protected String GetParamsForDelete(int id)
        {
            return "DELETE FROM public.user WHERE id = " + id;
        }

        @Override
        protected String GetParamsForGetById(int id)
        {
            return "SELECT id, \"fullName\" FROM public.user WHERE id = " + id;
        }

        @Override
        protected String GetAllCommandParameters()
        {
            return "SELECT id, \"fullName\" FROM public.user";
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
        protected User Map(ResultSet resultSet) throws SQLException
        {
            resultSet.next();
            int id = resultSet.getInt("id");
            String fullName = resultSet.getString("fullName");
            return new User(id, fullName);
        }

        @Override
        protected List<User> Maps(ResultSet resultSet) throws SQLException
        {
            List<User> users = new ArrayList<>();
            if (!resultSet.isClosed())
            {
                while (resultSet.next())
                {
                    int id = resultSet.getInt("id");
                    String fullName = resultSet.getString("fullName");
                    var user = new User(id, fullName);
                    users.add(user);
                }
            }

            return users;
        }
    }
