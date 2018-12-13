package cmd.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc1 on 11/28/2018.
 */
public class ApiJsonRoot implements Serializable{
    @SerializedName(value = "api")
    List<ApiJsonType> api;

    public ApiJsonRoot() {
        api = new ArrayList<>();
    }
}
