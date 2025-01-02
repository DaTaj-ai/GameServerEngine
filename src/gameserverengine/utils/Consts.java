package gameserverengine.utils;

public class Consts {
    
    final static public String DATABASE_URL = "jdbc:sqlite:./database/DB.sqlite";
    
    static public final String APP_NAME = "Mind Storm Server";
    static public final String APP_LOGO_PATH = "/gameserverengine/drawable/images/app_logo.png";
    
    
    // Database
    static public final int STATUS_FAILED = 0;
    static public final int STATUS_SUCCESS = 1;
    
    static public final String REGISTRATION_QUUERY = 
            "INSERT INTO UsersTable (firstName, lastName, userName, password ,isOnline , isplayingnow )"
            + " VALUES (?, ?, ?, ?, ?, ?)";
    
    static public final String REG_SUCCESS_MSG  = "Registration successful";
    static public final String REG_FAILED_MSG  = "Registration failed";
}