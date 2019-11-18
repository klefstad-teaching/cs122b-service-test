package testcases.tests.apigateway;

import edu.uci.ics.cs122b.test.base.ResponseModel;
import edu.uci.ics.cs122b.test.common.Result;
import edu.uci.ics.cs122b.test.util.ServiceResponse;
import org.junit.Test;
import testcases.model.hw2.response.LoginResponseModel;
import testcases.model.hw5.response.ApiResponseModel;
import testcases.socket.ApiGatewaySocket;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ApiGatewayTest {

    @Test
    public void idmLogin(){

        String email = "anteaterTest@uci.edu";
        String password = "AAAbbb111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<ApiResponseModel> response = ApiGatewaySocket.apiPostIdmRegister(email, passwordArray);

        //System.out.println(response.getStatus());
        MultivaluedMap<String,Object> map = response.getHeaders();

        //map.forEach((k, v)->{ System.out.println("Key: " + k + " Value: " + v); });
        //System.out.println(response.getHeaders());
        //System.out.println(map.getFirst("transactionid"));

        int responseCount = 0;
        for (int i = 0; i < 500; ++i){
            ServiceResponse<LoginResponseModel> responseReal = ApiGatewaySocket.apiGetReport(new MultivaluedHashMap<String, Object>(map));
            if (responseReal.getStatus() != 204)
                responseCount++;

            else
                System.out.println(responseReal.getStatus());
        }

        assertEquals(1, responseCount);


    }

}
