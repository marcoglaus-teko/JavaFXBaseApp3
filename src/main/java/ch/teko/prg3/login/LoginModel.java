package ch.teko.prg3.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginModel {

    public boolean isLogin(String username, String password, Connection connection) throws Exception {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM Login where username = ? and password = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }

            return false;

        } catch (Exception e) {
            return false;
        }

        // Ein finally Block wird immer ausgeführt!
        finally {
            preparedStatement.close();
//			connection.close();
        }

    }

}

