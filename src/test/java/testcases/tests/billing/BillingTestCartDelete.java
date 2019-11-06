package testcases.tests.billing;

import edu.uci.ics.cs122b.test.common.Json;
import edu.uci.ics.cs122b.test.common.Result;
import edu.uci.ics.cs122b.test.util.ServiceResponse;
import org.junit.After;
import org.junit.Test;
import testcases.model.hw4.request.CartInsertRequestModel;
import testcases.model.hw4.request.CartUpdateRequestModel;
import testcases.model.hw4.response.CartDeleteResponseModel;
import testcases.model.hw4.response.CartInsertResponseModel;
import testcases.model.hw4.response.CartRetrieveResponseModel;
import testcases.model.hw4.response.CartUpdateResponseModel;
import testcases.model.hw4.submodels.ItemModel;
import testcases.socket.BillingSocket;
import testcases.tests.movie.UserAccounts;

import javax.ws.rs.core.MultivaluedHashMap;

import static org.junit.Assert.*;
import static testcases.tests.billing.BillingTestOrderRetrieve.clearCart;

public class BillingTestCartDelete {

    @Test
    public void validDelete(){

        MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.putSingle("email", UserAccounts.validEmail );
        headers.putSingle("session_id", UserAccounts.session_id );

        String email = "validDelete@uci.edu";
        String movie_id = "tt4154796";
        Integer quantity = 1;

        //calling insert
        Result expectedResultInsert = Result.ITEM_INSERTION_SUCCESS;
        ServiceResponse<CartInsertResponseModel> responseInsert = BillingSocket.postCartInsert(headers, email, movie_id, quantity);
        assertEquals(expectedResultInsert.getStatus(), responseInsert.getStatus());

        //calling retrieve
        Result expectedResultRetrieveSuccess = Result.CART_RETRIEVE_SUCCESS;
        ServiceResponse<CartRetrieveResponseModel> responseRetrieveSuccess = BillingSocket.postCartRetrieve(headers, email);
        ItemModel[] itemModel = responseRetrieveSuccess.getEntity().getItems();

        assertEquals(expectedResultRetrieveSuccess.getStatus(), responseRetrieveSuccess.getStatus());
        assertEquals(1, itemModel.length);

        //calling delete
        Result expectedResultDelete = Result.ITEM_DELETE_SUCCESS;
        ServiceResponse<CartDeleteResponseModel> responseDelete = BillingSocket.postCartDelete(headers, email, movie_id);
        assertEquals(expectedResultDelete.getStatus(), responseDelete.getStatus());

        //calling retrieve
        Result expectedResultRetrieve = Result.ITEM_DNE;
        ServiceResponse<CartRetrieveResponseModel> responseRetrieve = BillingSocket.postCartRetrieve(headers, email);
        assertEquals(expectedResultRetrieve.getStatus(), responseRetrieve.getStatus());

    }

    @Test
    public void invalidDeleteCartItemDoesNotExist(){

        MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.putSingle("email", UserAccounts.validEmail );
        headers.putSingle("session_id", UserAccounts.session_id );

        String email = "invalidDelete@uci.edu";
        String movie_id = "tt4154796";

        //calling delete
        Result expectedResultDelete = Result.ITEM_DNE;
        ServiceResponse<CartDeleteResponseModel> responseDelete = BillingSocket.postCartDelete(headers, email, movie_id);
        assertEquals(expectedResultDelete.getStatus(), responseDelete.getStatus());

    }


}
