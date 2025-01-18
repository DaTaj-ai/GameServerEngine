package gameserverengine.local;

import gameserverengine.interfaces.LocalDatabaseFunctions;
import gameserverengine.models.LoginResponseModel;
import gameserverengine.models.ResponseModel;
import gameserverengine.models.UserModel;
import gameserverengine.utils.Consts;
import gameserverengine.utils.JsonUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            String msg = ex.getErrorCode() == Consts.DATABASE_UNIQUE_ERROR_CODE ? Consts.REG_FAILED_USER_EXIST_MSG : ex.getMessage();
            System.err.println(ex.getErrorCode());
            response.setMessage(msg);
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

    public static ResponseModel login(String username, String password) {

        ResponseModel loginresponse = null;
        UserModel resultUser = getUser(username);

        if (resultUser != null) {
            String localUserPassword = resultUser.getPassword();
            if (password.equals(resultUser.getPassword())) {
                String json = JsonUtils.userModelToJson(resultUser);
                loginresponse = new ResponseModel(Consts.STATUS_SUCCESS, "Congratulations User loged in!", json);
            } else {
                loginresponse = new ResponseModel(Consts.STATUS_FAILED, "Check your password ");
            }
        } else {

            loginresponse = new ResponseModel(Consts.STATUS_FAILED, "Check your Username or Create Account");
        }

        return loginresponse;
    }

    public static boolean setAvilableStatus(String username, int status) {
        boolean isAvilable = false;
        try {
            String updateQuery = "UPDATE UsersTable SET isPlaying = ? WHERE userName = ?";
            PreparedStatement updateStmnt = connection.prepareStatement(updateQuery);
            updateStmnt.setInt(1, status);
            updateStmnt.setString(2, username);
            int rowsUpdated = updateStmnt.executeUpdate();
            if (rowsUpdated > 0) {
                isAvilable = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isAvilable;
    }

    public static void setOnline(String username, int status) throws SQLException {
        if (status == 1) {
            PreparedStatement stmnt = connection.prepareStatement("UPDATE UsersTable SET isOnline = 1 WHERE username = ?");
            stmnt.setString(1, username);
            stmnt.executeUpdate();
        }
        else if (status == 0){
            PreparedStatement stmnt = connection.prepareStatement("UPDATE UsersTable SET isOnline = 0 WHERE username = ?");
            stmnt.setString(1, username);
            stmnt.executeUpdate();
        }
        
    }
    public static void updateScore(String username ,Integer score) throws SQLException{
        PreparedStatement stmnt = connection.prepareStatement("UPDATE USERSTABLE SET SCORE = ? where username = ? ");
        stmnt.setInt(1, score);
        stmnt.setString(2, username);
        stmnt.executeUpdate();
        System.out.println("updated inshaalah");
        
        
    }

    public static ArrayList<UserModel> getOnlinePlayer() {
        ArrayList<UserModel> availablePlayer = new ArrayList();

        try {

            PreparedStatement stmnt = connection.prepareStatement("SELECT * from USERSTABLE WHERE isOnline = 1");
            ResultSet result = stmnt.executeQuery();

            while (result.next()) {
                UserModel player = new UserModel();
                player.setFirstName(result.getString("firstName"));
                player.setLastName(result.getString("lastName"));
                player.setIsOnline(result.getInt("isOnline"));
                player.setIsplayingnow(result.getInt("isplayingnow"));
                player.setGamesPlayed(result.getInt("GamesPlayed"));
                player.setPassword(result.getString("password"));
                player.setUserName(result.getString("userName"));
                player.setScore(result.getInt("score"));
                availablePlayer.add(player);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return availablePlayer;

    }

    public static int getOnlinePlayerCount() {
        int count = 0;
        try {

            PreparedStatement stmnt = connection.prepareStatement("SELECT * from USERSTABLE WHERE isOnline = 1");
            ResultSet result = stmnt.executeQuery();

            while (result.next()) {
                count++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static int getOfflinePlayerCount() {
        int count = 0;
        try {

            PreparedStatement stmnt = connection.prepareStatement("SELECT * From USERSTABLE WHERE isOnline != 1 ");
            ResultSet result = stmnt.executeQuery();

            while (result.next()) {
                count++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static int getAvailblePlayerCount() {
        int count = 0;
        try {

            PreparedStatement stmnt = connection.prepareStatement("SELECT * from USERSTABLE WHERE isOnline = 1 AND isplayingnow != 1 ");
            ResultSet result = stmnt.executeQuery();

            while (result.next()) {
                count++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

}
