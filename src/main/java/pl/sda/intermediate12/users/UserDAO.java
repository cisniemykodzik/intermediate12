package pl.sda.intermediate12.users;

import lombok.Getter;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Service


public class UserDAO {
    private static DataSource datasource;

    public List<User> getUserList() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM users;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User nextUser = new User();
                int userId = rs.getInt(1);
                nextUser.setFirstName(rs.getString(2));
                nextUser.setLastName(rs.getString(3));
                nextUser.setEMail(rs.getString(4));
                nextUser.setPasswordHash((rs.getString(5)));
                nextUser.setBirthDate(rs.getString(6));
                nextUser.setPesel(rs.getString(7));
                nextUser.setPhone(rs.getString(8));
                nextUser.setPreferEmails(rs.getBoolean(9));
                nextUser.setUserAddress(getUserAddress(userId, connection));
                users.add(nextUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
        }
        return users;
    }

    private UserAddress getUserAddress(int userId, Connection connection) {
        if (connection != null) {
            try {
                String sql = "SELECT city, country, zipCode, street FROM userAddresses WHERE userId = ?;";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                UserAddress userAddress = new UserAddress();
                if (rs.next()) {
                    userAddress.setCity(rs.getString(1));
                    userAddress.setCountry(rs.getString(2));
                    userAddress.setZipCode(rs.getString(3));
                    userAddress.setStreet(rs.getString(4));
                }
                return userAddress;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    public void saveUserAddress(UserAddress userAddress, int userId, Connection connection) {
        if (connection != null) {
            try {
                String sqlInsertAddress = "INSERT INTO useraddresses (userId, city, country, zipCode, street) " +
                        "VALUES (?, ?, ?, ?, ?);";
                PreparedStatement psa = connection.prepareStatement(sqlInsertAddress);
                psa.setInt(1, userId);
                psa.setString(2, userAddress.getCity());
                psa.setString(3, userAddress.getCountry());
                psa.setString(4, userAddress.getZipCode());
                psa.setString(5, userAddress.getStreet());
                psa.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(User user) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO users (firstName, lastName, eMail, passwordHash, birthDate, pesel, phone, preferEmails) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEMail());
            ps.setString(4, user.getPasswordHash());
            ps.setString(5, user.getBirthDate());
            ps.setString(6, user.getPesel());
            ps.setString(7, user.getPhone());
            ps.setBoolean(8, user.isPreferEmails());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newUserId = generatedKeys.getInt(1);
                saveUserAddress(user.getUserAddress(), newUserId, connection);
            }
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
