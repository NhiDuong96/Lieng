package extras.webserver.controller;

import bitzero.framework.authen.dev.DevAuthService;
import bitzero.framework.socialcontroller.bean.UserInfo;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
public class MainController {

    static Gson gson = new Gson();

    @GET
    @Path("HandRankingTool")
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return "ApiTypeTool";
    }

    @GET
    @Path("login-dev")
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@QueryParam("name") String name) {
        UserInfo userInfo = DevAuthService.getInstance().authenticate(name);
        return gson.toJson(userInfo);
    }
}


