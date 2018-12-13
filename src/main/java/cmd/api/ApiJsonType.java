package cmd.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc1 on 11/28/2018.
 */

public class ApiJsonType implements Serializable{

    @SerializedName("package")
    String pack;
    @SerializedName(value = "type")
    String type;
    @SerializedName(value = "field")
    List<ApiJsonField> field;

    public ApiJsonType(){}

    public ApiJsonType(String pack, String type) {
        this.pack = pack;
        this.type = type;
        this.field = new ArrayList<>();
    }
}
