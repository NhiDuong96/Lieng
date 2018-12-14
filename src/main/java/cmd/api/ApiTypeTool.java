package cmd.api;


/**
 * Created by pc1 on 11/28/2018.
 */
public class ApiTypeTool extends ApiRender{
    public static void main(String[] args) {
        ApiRender.clear();
        new ApiTypeTool().render(true);
    }
}
