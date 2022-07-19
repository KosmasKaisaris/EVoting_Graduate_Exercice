package db;
import config.Configuration;
import entities.UserEntity;
import entities.VoteEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnect
{

    private static ThreadLocal<Connection> connectionCache = new ThreadLocal<>();

    public static Connection connection()
    {
        if (connectionCache.get() != null)
        {
            Connection connection = connectionCache.get();

            try
            {
                if (!connection.isClosed() && connection.isValid(1000))
                {
                    return connection;
                }
                else
                {
                    connection.close();
                }
            }
            catch (SQLException ignored)
            {

            }
            finally {
                connectionCache.set(null);
            }
        }

        Connection connection = null;

        try
        {
            connection = DriverManager.getConnection(Configuration.DB_URL,
                    Configuration.DB_USERNAME,
                    Configuration.DB_PASSWORD);
            connectionCache.set(connection);

            return connection;
        }
        catch (SQLException ex)
        {
            return null;
        }
    }

	public static void forceCloseConnection(){
		Connection connection = connection();
		
		if(connection == null)
		{
			connectionCache.set(null);
		}
		
		try
		{
			connection.close();
		}
		catch(Exception ignored)
		{
			//Do nothing 
		}
		
		connectionCache.set(null);
	}
	
    public static List<UserEntity> findUsers()
    {
        List<UserEntity> users = new ArrayList<>();

        try
        {
            Connection connection = connection();

            if (connection == null)
                throw new NullPointerException("No JDBC connection found.");

            //Start DB transaction
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement("SELECT * from USERS");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                
                UserEntity user = new UserEntity();
                user.setId(resultSet.getLong("id"));
                user.setUserToken(resultSet.getString("user_token"));
                users.add(user);
            }

            connection.commit();

            resultSet.close();
            statement.close();

        } catch (Exception ignored) {
            users.clear();
        }

        return users;
    }

    public static UserEntity findUserByToken(String userToken)
    {
        UserEntity user = null;

        try
        {
            Connection connection = connection();

            if (connection == null)
                throw new NullPointerException("No JDBC connection found.");

            //Start DB transaction
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement("SELECT * from USERS WHERE USER_TOKEN = ?");
            statement.setString(1, userToken);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next())
                return null;

            user = new UserEntity();
            user.setId(resultSet.getLong("id"));
            user.setUserToken(resultSet.getString("user_token"));

            connection.commit();

            resultSet.close();
            statement.close();

        } catch (Exception ignored) {
            System.out.println(ignored);
            ignored.printStackTrace();
            user = null;
        }

        return user;
    }
    
    public static UserEntity findUserById(Long id)
    {
        UserEntity user = null;

        try
        {
            Connection connection = connection();

            if (connection == null)
                throw new NullPointerException("No JDBC connection found.");

            //Start DB transaction
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement("SELECT * from USERS WHERE ID = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next())
                return null;

            user = new UserEntity();
            user.setId(resultSet.getLong("id"));
            user.setUserToken(resultSet.getString("user_token"));

            connection.commit();

            resultSet.close();
            statement.close();

        } catch (Exception ignored) {
            System.out.println(ignored);
            ignored.printStackTrace();
            user = null;
        }

        return user;
    }

    public static VoteEntity findVoteById(Long voteId)
    {
        VoteEntity vote = null;

        try
        {
            Connection connection = connection();

            if (connection == null)
                throw new NullPointerException("No JDBC connection found.");

            //Start DB transaction
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement("SELECT * from VOTES WHERE ID = ?");
            statement.setLong(1, voteId);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next())
                return null;

            vote = new VoteEntity();
            vote.setId(resultSet.getLong("id"));
            vote.setUserId(resultSet.getLong("user_id"));
            vote.setVote(resultSet.getInt("vote"));

            connection.commit();

            resultSet.close();
            statement.close();

        } catch (Exception ignored) {
            vote = null;
        }

        return vote;
    }
    
    public static VoteEntity findVoteByUserId(Long userId)
    {
        VoteEntity vote = null;

        try
        {
            Connection connection = connection();

            if (connection == null)
                throw new NullPointerException("No JDBC connection found.");

            //Start DB transaction
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement("SELECT * from VOTES WHERE USER_ID = ?");
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next())
                return null;

            vote = new VoteEntity();
            vote.setId(resultSet.getLong("id"));
            vote.setUserId(resultSet.getLong("user_id"));
            vote.setVote(resultSet.getInt("vote"));

            connection.commit();

            resultSet.close();
            statement.close();

        } catch (Exception ignored) {
            vote = null;
        }

        return vote;
    }

    public static VoteEntity saveVote(VoteEntity vote)
    {
        VoteEntity saved = null;

        try
        {
            Connection connection = connection();

            if (connection == null)
                throw new NullPointerException("No JDBC connection found.");

            //Start DB transaction
            connection.setAutoCommit(false);

            
            if (vote.getId() == null)
            {
                //System.out.println("insert sto if ");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO VOTES (USER_ID, VOTE) VALUES (?,?)");
                statement.setLong(1,vote.getUserId());
                statement.setInt(2, vote.getVote());
                
                int rowsAffected = statement.executeUpdate();

                connection.commit();
                statement.close();

                if (rowsAffected == 0)
                    return null;

                saved = findVoteByUserId(vote.getUserId());
            }
            else 
            {
                //System.out.println("update sto if ");
                PreparedStatement statement = connection.prepareStatement("UPDATE VOTES SET USER_ID = ?, VOTE = ? WHERE ID = ? ");
                statement.setLong(0, vote.getUserId());
                statement.setInt(1, vote.getVote());
                statement.setLong(2, vote.getId());

                int rowsAffected = statement.executeUpdate();

                connection.commit();
                statement.close();

                if (rowsAffected == 0)
                    return null;

                saved = vote;
            }

        } catch (Exception ignored) {
            System.out.println(ignored);
            saved = null;
        }

        return saved;
    }


}