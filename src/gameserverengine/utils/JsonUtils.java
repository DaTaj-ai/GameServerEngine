package gameserverengine.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gameserverengine.models.GameModel;
import gameserverengine.models.LoginRequestModel;
import gameserverengine.models.LoginResponseModel;
import gameserverengine.models.RequestModel;
import gameserverengine.models.ResponseModel;
import gameserverengine.models.UserModel;
import java.util.ArrayList;
import piratesproject.models.InvitationModel;

public class JsonUtils {
    
    private static final Gson gson = new Gson();

    // Convert UserModel to JSON string
    public static String userModelToJson(UserModel userModel) {
        return gson.toJson(userModel);
    }

    // Convert JSON string to UserModel
    public static UserModel jsonToUserModel(String json) {
        return gson.fromJson(json, UserModel.class);
    }

    // Convert ResponseModel to JSON string
    public static String responseModelToJson(ResponseModel responseModel) {
        return gson.toJson(responseModel);
    }

    // Convert JSON string to ResponseModel
    public static ResponseModel jsonToResponseModel(String json) {
        return gson.fromJson(json, ResponseModel.class);
    }
    public static String requestModelToJson(RequestModel requestModel) {
        return gson.toJson(requestModel);
    }

    // Convert JSON string to ResponseModel
    public static RequestModel jsonToRequestModel(String requestModel) {
        return gson.fromJson(requestModel, RequestModel.class);
    }
    
      public static String LoginRequstModelToJson(LoginRequestModel loginRequestModel) {
        return gson.toJson(loginRequestModel);
    }

    // Convert JSON string to ResponseModel
    public static LoginRequestModel jsonToLoginRequestModel(String loginRequestModel) {
        return gson.fromJson(loginRequestModel, LoginRequestModel.class);
    }
              // Convert ResponseModel to JSON string
    public static String loginResponseModelToJson(LoginResponseModel responseModel) {
        return gson.toJson(responseModel);
    }

    // Convert JSON string to ResponseModel
    public static LoginResponseModel jsonToLoginResponseModel(String json) {
        return gson.fromJson(json, LoginResponseModel.class);
    }
        public static ArrayList<UserModel> jsonToUsersArray(String json) {
        ArrayList<UserModel> users = gson.fromJson(json, new TypeToken<ArrayList<UserModel>>(){}.getType());
        return users;
    }
        public static String usersArrayToJson(ArrayList<UserModel> users ) {
        return gson.toJson(users);
    }
        public static String invitationModelToJson(InvitationModel invitationModel){
        return gson.toJson(invitationModel); 
    }
    
    public static InvitationModel jsonToInvitationModel (String invitationJson){
        return gson.fromJson(invitationJson, InvitationModel.class);
    }
    public static String gameModelToJson(GameModel model){
        return gson.toJson(model); 
    }
    
    public static GameModel jsonToGameModel (String game){
        return gson.fromJson(game, GameModel.class);
    }
}
