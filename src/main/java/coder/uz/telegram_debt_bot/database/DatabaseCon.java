package coder.uz.telegram_debt_bot.database;

import coder.uz.telegram_debt_bot.model.User;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class DatabaseCon {

    private String url = "jdbc:postgresql://localhost:5432/debt_db";
    private String userName = "postgres";
    private String password = "9704";
    Connection connection = null;

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, userName, password);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }




    public String addUser(User user, String tableName) {
        String query = "INSERT INTO " + tableName + "(\"fullName\", date , \"phoneNumber\",debt)" +
                "VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            List<User> userList = getUser(tableName);
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getPhoneNumber().equals(user.getPhoneNumber())) {
                    return "bunaqa user oldin qo'shilgan";
                } else {
                    System.out.println(user);
                    preparedStatement.setString(1, user.getFullName());
                    preparedStatement.setDate(2, user.getDate());
                    preparedStatement.setString(3, user.getPhoneNumber());
                    preparedStatement.setDouble(4, user.getDebt());
                    System.out.println(preparedStatement.executeUpdate());
                    return "user added";
                }
            }
            preparedStatement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "user didn't add";

    }

    public List<User> getUser(String tableName) {
        List<User> users = new ArrayList<>();

        String query = "select * from " + tableName + ";";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setFullName(resultSet.getString("fullName"));
                user.setDebt(resultSet.getDouble("debt"));
                user.setDate(resultSet.getDate("date"));
                users.add(user);
            }
            preparedStatement.close();
            getConnection().close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public String deleteUser(Integer id, String tableName) {

        List<User> users = new ArrayList<>();
        String query = "DELETE FROM " + tableName + " WHERE username=?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            List<User> userList = getUser(tableName);
            for (int i = 0; i < userList.size(); i++) {
                if (users.get(i).getId().equals(id)) {
                    preparedStatement.setInt(1, id);
                    int i1 = preparedStatement.executeUpdate();
                    if (i1>0) {
                        return "user was deleted successfully!";
                    }
                }

            }
            preparedStatement.close();
            getConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "user id not found";
    }

    public String editUser(Long id, Double debt, String tableName) {
        List<User> userList = getUser(tableName);
        String query = "Update " + tableName + "" +
                "SET debt=?"+
                "Where id=" + id + "";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getId().equals(id)) {
                    preparedStatement.setDouble(1, debt);
                    int i1 = preparedStatement.executeUpdate();
                    if (i1 > 0) {
                        return "muvafaqiyatli o'zgartirildi";
                    }

                }
            }
            preparedStatement.close();
            getConnection().close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "id not found";
    }
}

