package testcases.tests.apigateway;

import edu.uci.ics.cs122b.test.base.ResponseModel;
import edu.uci.ics.cs122b.test.common.Result;
import edu.uci.ics.cs122b.test.util.ServiceResponse;
import org.junit.Test;
import testcases.model.hw2.request.LoginRequestModel;
import testcases.model.hw2.response.LoginResponseModel;
import testcases.model.hw3.response.MovieSearchResponseModel;
import testcases.socket.ApiGatewaySocket;
import testcases.tests.movie.UserAccounts;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ApiGatewayTestIdm {

    @Test
    public void idmLogin(){

        String email = "apiGatewayTestLogin@uci.edu";
        String password = "aaaBBB123@";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<ResponseModel> response = ApiGatewaySocket.apiPostIdmLogin(email, passwordArray);

        int responseCount = 0;
        for (int i = 0; i < 500; ++i){
            ServiceResponse<LoginResponseModel> responseReal = ApiGatewaySocket.apiGetReport(LoginResponseModel.class, new MultivaluedHashMap<String, Object>(response.getHeaders()));
            if (responseReal.getStatus() != 204)
                responseCount++;

        }
        assertEquals(204, (int)response.getStatus());
        assertEquals(1, responseCount);

    }



}
