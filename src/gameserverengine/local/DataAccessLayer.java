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
    public static ResponseModel register(UserModel user){
        ResponseModel response = new ResponseModel(Consts.STATUS_FAILED, Consts.REG_FAILED_MSG); ;

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

}
