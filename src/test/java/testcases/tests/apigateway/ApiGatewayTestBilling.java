package testcases.tests.apigateway;

import edu.uci.ics.cs122b.test.base.ResponseModel;
import edu.uci.ics.cs122b.test.util.ServiceResponse;
import org.junit.Test;
import testcases.model.hw3.response.GetMovieResponseModel;
import testcases.model.hw3.response.MovieSearchResponseModel;
import testcases.model.hw4.response.CartInsertResponseModel;
import testcases.socket.ApiGatewaySocket;

import javax.ws.rs.core.MultivaluedHashMap;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ApiGatewayTestBilling {

    @Test
    public void cartInsert(){

        MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();

        headers.putSingle("email", "apiGatewayTest@uci.edu" );
        headers.putSingle("session_id", "5eb88e2dee72c28ed24c7239815d921a6e17e48c212f705b01f9140192b61347d30186a86085e72f2ea7bc71ee7715f3405ffbad746ebb520feda762da3e569a" );

        String email = "apiGatewayTest@uci.edu";
        String movie_id = "tt0308514";
        Integer quantity = 2;


        ServiceResponse<ResponseModel> response = ApiGatewaySocket.apiPostBillingCartInsert(headers, email, movie_id, quantity);

        //response.getHeaders().forEach((k, v)->{ System.out.println("Key: " + k + " Value: " + v); });


        int count = ApiGatewaySocket.ReportLoop(CartInsertResponseModel.class, new MultivaluedHashMap<String, Object>(response.getHeaders()) );
        /*
        int responseCount = 0;


        for (int i = 0; i < 20; ++i){
            ServiceResponse<CartInsertResponseModel> responseReal = ApiGatewaySocket.apiGetReport(CartInsertResponseModel.class, new MultivaluedHashMap<String, Object>(response.getHeaders()));
            if (responseReal.getStatus() != 204)
                responseCount++;
            else
                System.out.println(responseReal.getStatus());

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Sleep Interrupted");
                //e.printStackTrace();

            }

        }*/
        assertEquals(204, (int)response.getStatus());
        assertEquals(1, count);

    }


}
