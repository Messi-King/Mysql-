import javax.sql.DataSource;
import java.sql.*;

public class TestDemo1 {
    public static void main(String[] args) {
        Connection connection = null;
        Connection connection1 = null;
        Statement statement = null;
        Statement statement1 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;

        try {
            //创建数据库连接池
            DataSource dataSource = new MysqlDataSource();
            ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chong?user=root&password=0000&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            DataSource dataSource1 = new MysqlDataSource();
            ((MysqlDataSource) dataSource1).setUrl("jdbc:mysql://localhost:3306/chong?user=root&password=0000&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            // 创建数据库连接
            connection = dataSource.getConnection();
            System.out.println(connection);
            connection1 = dataSource1.getConnection();
            System.out.println(connection1);

            //创建操作命令对象
            statement = connection.createStatement();
            statement1 = connection1.createStatement();

            //执行sql
            resultSet = statement.executeQuery("select id,  sn,  name,  qq_mail,  classes_id from student");
            resultSet1 = statement1.executeQuery("select id,  name,  `desc` from classes");

            //处理结果集
            while (resultSet.next()) { //遍历每一行数据
                int id = resultSet.getInt("id");
                int sn = resultSet.getInt("sn");
                String qq_mail = resultSet.getString("qq_mail");
                String name = resultSet.getString("name");
                int class_id = resultSet.getInt("classes_id");
                //String role = resultSet.getString("role");
                //BigDecimal salary = resultSet.getBigDecimal("salary");
                System.out.println(String.format("Student: id=%d, sn=%d name=%s , qq_mail=%s ,classes_id=%d", id, sn, name, qq_mail, class_id));
            }

            while (resultSet1.next()) { //遍历每一行数据
                int id = resultSet1.getInt("id");
                String name = resultSet1.getString("name");
                String desc = resultSet1.getString("desc");
                System.out.println(String.format("classes: id=%d, name=%s,desc=%s",id, name,desc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
                if(resultSet1 != null)
                    resultSet1.close();
                if(statement1 != null)
                    statement1.close();
                if(connection1 != null)
                    connection1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}