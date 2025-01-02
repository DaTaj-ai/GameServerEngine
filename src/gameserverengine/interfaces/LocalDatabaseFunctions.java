package gameserverengine.interfaces;

import gameserverengine.models.LoginResponseModel;
import gameserverengine.models.ResponseModel;
import gameserverengine.models.UserModel;

public interface LocalDatabaseFunctions {
    
    static ResponseModel register(UserModel user){
        return null;
    }

    static UserModel getUser(String username){
        return null;
    }

    static LoginResponseModel login(String username, String password){
        return null;
    }
}
