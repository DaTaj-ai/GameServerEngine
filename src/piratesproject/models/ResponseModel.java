package piratesproject.models;

import java.io.Serializable;

public class ResponseModel implements Serializable {

    private int status;
    private String message;
    private String Data;

    public ResponseModel() {
    }

    @Override
    public String toString() {
        return "ResponseModel{" + "status=" + status + ", message=" + message + '}';
    }
    

    public ResponseModel(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseModel(int status, String message, String Data) {
        this.status = status;
        this.message = message;
        this.Data = Data;
    }
    

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
    
    


    
    
}
