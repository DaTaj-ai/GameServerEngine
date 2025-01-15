package gameserverengine.interfaces;

import gameserverengine.models.LoginResponseModel;
import gameserverengine.models.UserModel;
import piratesproject.models.ResponseModel;

public interface LocalDatabaseFunctions {
    
    static ResponseModel register(UserModel user){
        return null;
    }

    static UserModel getUser(String userName){
        return null;
    }

    static LoginResponseModel login(String username, String password){
        return null;
    }
}
