package gameserverengine.utils;

public class Consts {
    
    final static public String DATABASE_URL = "jdbc:sqlite:./database/DB.sqlite";
    final static public String LOCAL_IP = "127.0.0.1";
    final static public int PORT = 1422;
    
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
    static public final String REG_FAILED_USER_EXIST_MSG  = "This username is already exist";
    
    static public final int DATABASE_UNIQUE_ERROR_CODE = 19;
    static  public final int ONLINE=1;
    static public final  int OFFLINE=0;
    
}