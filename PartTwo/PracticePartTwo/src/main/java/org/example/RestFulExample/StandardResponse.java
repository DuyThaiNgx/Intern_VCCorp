package org.example.RestFulExample;

import com.google.gson.JsonElement;

public class StandardResponse {

    private StatusResponse status;
    private String message;
    private JsonElement data;

    public StandardResponse(StatusResponse status) {

    }
    public StandardResponse(StatusResponse status, String message) {

    }
    public StandardResponse(StatusResponse status, JsonElement data) {

    }
    public StandardResponse(StatusResponse status,String message, JsonElement data) {

    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
    // getters and setters
}

