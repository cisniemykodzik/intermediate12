package pl.sda.intermediate12.users;

import lombok.Getter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class UserDAO {

    private static DataSource datasource;

    public List<User> getUserList(){
        return null;
    }

    public boolean checkIfUserExist(String eMail) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "SELECT eMail FROM users WHERE eMail = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, eMail);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (connection != null && !connection.isClosed())
                    connection.close();
            } catch (SQLException e) {
            }
        }
    }


    public void saveUser(User user) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO users (firstName, lastName, eMail, passwordHash, birthDate, pesel, phone, preferEmails)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEMail());
            ps.setString(4, user.getPasswordHash());
            ps.setString(5, user.getBirthDate());
            ps.setString(6, user.getPesel());
            ps.setString(7, user.getPhone());
            ps.setBoolean(8, user.isPreferEmails());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed())
                    connection.close();
            } catch (SQLException e) {
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        if (datasource == null) {
            String connectionString = "jdbc:mysql://127.0.0.1:3306/cisniemykodzik?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "cisniemykodzik";
            String password = "sda2018cisniemy";
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl(connectionString);
            basicDataSource.setUsername(username);
            basicDataSource.setPassword(password);
            basicDataSource.setMaxTotal(5);
            basicDataSource.setInitialSize(3);
            basicDataSource.setMaxWaitMillis(5000);

            datasource = basicDataSource;
        }
        return datasource.getConnection();
    }
}
