package gameserverengine.local;

import gameserverengine.interfaces.LocalDatabaseFunctions;
import gameserverengine.models.LoginResponseModel;
import gameserverengine.models.ResponseModel;
import gameserverengine.models.UserModel;
import gameserverengine.utils.Consts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAccessLayer implements LocalDatabaseFunctions {

    private static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(Consts.DATABASE_URL);

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }

    }

    public static ResponseModel register(UserModel user) {
        ResponseModel response = new ResponseModel(Consts.STATUS_FAILED, Consts.REG_FAILED_MSG);;

        try {
            PreparedStatement pst = connection.prepareStatement(Consts.REGISTRATION_QUUERY);
            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setString(3, user.getUserName());
            pst.setString(4, user.getPassword());
            pst.setBoolean(5, true);
            pst.setBoolean(6, true);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                response.setStatus(Consts.STATUS_SUCCESS);
                response.setMessage(Consts.REG_SUCCESS_MSG);

            }
            pst.close();
        } catch (SQLException ex) {
            response = new ResponseModel(Consts.STATUS_FAILED, ex.getMessage());
            response.setMessage("An error occurred: " + ex.getMessage());
        }

        return response;
    }

    public static UserModel getUser(String userName) {
        UserModel resultUser = null;

        try {
            String myStatment
                    = "SELECT * FROM UsersTable " + "WHERE userName = ? ";

            PreparedStatement pst = connection.prepareStatement(myStatment);

            pst.setString(1, userName);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String userFirstName = rs.getString("firstName");
                String userLastName = rs.getString("lastName");
                String resultUserName = rs.getString("userName");
                String userPasswor = rs.getString("password");
                resultUser = new UserModel(userFirstName, userLastName, resultUserName, userPasswor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultUser;
    }

    public static LoginResponseModel login(String username, String password) {

        LoginResponseModel loginresponse = null;
        UserModel resultUser = getUser(username);

        if (resultUser != null) {
            String localUserPassword = resultUser.getPassword();
            if (password.equals(resultUser.getPassword())) {
                loginresponse = new LoginResponseModel(Consts.STATUS_SUCCESS, "Congratulations User loged in!", resultUser);
            } else {
                loginresponse = new LoginResponseModel(Consts.STATUS_FAILED, "Check your password ", resultUser);
            }
        } else {
            loginresponse = new LoginResponseModel(Consts.STATUS_FAILED, "Check your Username or Create Account", resultUser);
        }

        return loginresponse;
    }

}
