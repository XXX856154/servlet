
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils
{
    private static final String url="jdbc:mysql://127.0.0.1:3306/java?characterEncoding=utf8&useSSL=false";
    private static final String user="root";
    private static final String password="526077798";
    private static volatile DataSource dataSource=null;

    public static void createDataSource()
    {
        if(dataSource==null)
        {
            synchronized (DBUtils.class)
            {
                if (dataSource == null)
                {
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource) dataSource).setURL(url);
                    ((MysqlDataSource) dataSource).setUser(user);
                    ((MysqlDataSource) dataSource).setPassword(password);
                }
            }
        }
    }
    public static Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }
    public static boolean save(Data data)
    {
        String sql="insert into messageWall values(?,?,?)";
        try
        {
            Connection connection=getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, data.from);
            preparedStatement.setString(2, data.to);
            preparedStatement.setString(3, data.mes);
            preparedStatement.executeUpdate();
            close(connection,preparedStatement,null);
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;

    }
    public static List<Data> load()
    {
        List<Data> list=new ArrayList<>();
        String sql="select * from messageWall";
        try
        {

            Connection connection=getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Data data=new Data();
                data.from=resultSet.getString("from");
                data.to=resultSet.getString("to");
                data.mes=resultSet.getString("message");
                list.add(data);
            }
          close(connection,preparedStatement,resultSet);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;

    }
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
    {
        try
        {
            if (connection != null)
            {
                connection.close();
            }
            if(preparedStatement!=null)
            {
                preparedStatement.close();
            }
            if(resultSet!=null)
            {
                resultSet.close();
            }

        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
