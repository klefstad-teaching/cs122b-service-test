package testcases.socket;

import edu.uci.ics.cs122b.test.base.ResponseModel;
import edu.uci.ics.cs122b.test.util.ServiceResponse;
import edu.uci.ics.cs122b.test.util.ServiceSocket;
import edu.uci.ics.cs122b.test.util.ServiceSocketFactory;
import testcases.model.hw2.request.LoginRequestModel;
import testcases.model.hw2.response.LoginResponseModel;
import testcases.model.hw3.request.BrowseKeywordRequestModel;
import testcases.model.hw3.request.GetMovieRequestModel;
import testcases.model.hw3.request.GetPersonRequestModel;
import testcases.model.hw3.request.ThumbnailRequestModel;
import testcases.model.hw3.response.*;
import testcases.model.hw5.response.ApiResponseModel;

import javax.ws.rs.core.MultivaluedHashMap;
import java.net.URI;

public class ApiGatewaySocket {
    private final static String SCHEME;
    private final static String ADDRESS;
    private final static String PORT;
    private final static String API_PATH;

    private final static URI FULL_URI;

    private final static String API_GATEWAY_REPORT_EP = "/report";

    private final static String IDM_LOGIN_EP = "/idm/login";

    private final static String MOVIES_SEARCH_EP = "/movies/search";
    private final static String GET_MOVIE_EP = "/movies/get/";

    private final static String CART_INSERT_EP = "/billing/cart/insert";
    private final static String CART_RETRIEVE_EP = "/billing/cart/retrieve";




    private final static ServiceSocketFactory SOCKET_FACTORY;

    static {
        SCHEME = "http://";
        ADDRESS = "127.0.0.1";
        PORT = ":12345";
        API_PATH = "/api/g";

        FULL_URI = URI.create(SCHEME + ADDRESS + PORT + API_PATH);

        SOCKET_FACTORY = ServiceSocketFactory.createFactory(FULL_URI);
    }

    public static ServiceResponse<LoginResponseModel> apiGetReport(MultivaluedHashMap<String, Object> headers)
    {
        ServiceSocket serviceSocket = SOCKET_FACTORY.createSocket(API_GATEWAY_REPORT_EP);
        serviceSocket = serviceSocket.headers(headers);
        return serviceSocket.get(LoginResponseModel.class);
    }


    public static ServiceResponse<ApiResponseModel> apiPostIdmRegister(String email, char[] password)
    {
        LoginRequestModel requestModel = new LoginRequestModel(email, password);

        ServiceSocket serviceSocket = SOCKET_FACTORY.createSocket(IDM_LOGIN_EP);

        return serviceSocket.post(ApiResponseModel.class,requestModel);
    }

    public static ServiceResponse<ApiResponseModel> apiGetMovieSearch(MultivaluedHashMap<String, Object> headers, MultivaluedHashMap<String, Object> query)
    {
        ServiceSocket serviceSocket = SOCKET_FACTORY.createSocket(MOVIES_SEARCH_EP);
        serviceSocket = serviceSocket.headers(headers);
        serviceSocket = serviceSocket.queries(query);

        return serviceSocket.get(ApiResponseModel.class);

    }
//
//    public static ServiceResponse<PeopleSearchResponseModel>apietPeople(MultivaluedHashMap<String, Object> headers, MultivaluedHashMap<String, Object> query)
//    {
//        ServiceSocket serviceSocket = SOCKET_FACTORY.createSocket(PEOPLE_EP);
//        serviceSocket = serviceSocket.headers(headers);
//        serviceSocket = serviceSocket.queries(query);
//
//        return serviceSocket.get(PeopleSearchResponseModel.class);
//
//    }
//
//    public static ServiceResponse<GetMovieResponseModel> getMovieByID(MultivaluedHashMap<String, Object> headers, MultivaluedHashMap<String, Object> query, String movieID)
//    {
//        GetMovieRequestModel requestModel = new GetMovieRequestModel(movieID);
//
//        ServiceSocket serviceSocket = SOCKET_FACTORY.createSocket(GET_MOVIE_EP + movieID);
//
//        serviceSocket = serviceSocket.headers(headers);
//        serviceSocket = serviceSocket.queries(query);
//        return serviceSocket.get(GetMovieResponseModel.class);
//    }
//
//    public static ServiceResponse<GetPersonByIDResponseModel> getPersonByID(MultivaluedHashMap<String, Object> headers, MultivaluedHashMap<String, Object> query, String personID)
//    {
//        GetPersonRequestModel requestModel = new GetPersonRequestModel(personID);
//
//        ServiceSocket serviceSocket = SOCKET_FACTORY.createSocket(GET_PEOPLE_EP + personID);
//
//        serviceSocket = serviceSocket.headers(headers);
//        serviceSocket = serviceSocket.queries(query);
//        return serviceSocket.get(GetPersonByIDResponseModel.class);
//    }

}
