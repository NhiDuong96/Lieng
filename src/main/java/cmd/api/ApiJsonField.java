package cmd.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by pc1 on 11/28/2018.
 */
public class ApiJsonField implements Serializable{
    @SerializedName("type")
    String type;
    @SerializedName("name")
    String name;

    public ApiJsonField(){}

    public ApiJsonField(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
