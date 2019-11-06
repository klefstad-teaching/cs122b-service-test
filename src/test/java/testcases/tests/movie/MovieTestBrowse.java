
package testcases.tests.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uci.ics.cs122b.test.common.Result;
import edu.uci.ics.cs122b.test.util.ServiceResponse;
import org.junit.Test;
import testcases.model.hw3.request.*;

import testcases.socket.IdmSocket;
import testcases.socket.MovieSocket;

import testcases.model.hw3.response.*;


import testcases.model.hw3.submodels.*;

import javax.ws.rs.core.MultivaluedHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

//*********************************************
//
//                BrowseKeyword Tests
//
//*********************************************
public class MovieTestBrowse {
    @Test
    public void validPrivilege_BrowseKeywordDefaultQueryParams() {
        String email = UserAccounts.validEmail;
        String session_id = UserAccounts.session_id;
        String keywords = "sequel,alien invasion";
        Result expectedResult = Result.MOVIES_FOUND;

        MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.putSingle("email", email);
        headers.putSingle("session_id", session_id);

        MultivaluedHashMap<String, Object> query = new MultivaluedHashMap<>();
        ServiceResponse<BrowseKeywordResponseModel> response = MovieSocket.getBrowseByKeyword(headers, query, keywords);
        BrowseMovieModel[] browseMovies = response.getEntity().getMovies();
        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(3, browseMovies.length);
        assertEquals("tt4154796", browseMovies[0].getMovie_id());
        assertEquals(browseMovies[2].getDirector(), "Tom Green");

    }

    @Test
    public void validPrivilege_BrowseKeywordQueryParamsDesc() {
        String email = UserAccounts.validEmail;
        String session_id = UserAccounts.session_id;
        String keywords = "sequel,alien invasion";
        Result expectedResult = Result.MOVIES_FOUND;

        MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.putSingle("email", email);
        headers.putSingle("session_id", session_id);

        MultivaluedHashMap<String, Object> query = new MultivaluedHashMap<>();
        query.putSingle("direction", "desc");

        ServiceResponse<BrowseKeywordResponseModel> response = MovieSocket.getBrowseByKeyword(headers, query, keywords);
        BrowseMovieModel[] browseMovies = response.getEntity().getMovies();

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getMessage(), response.getEntity().getMessage());
        assertEquals(3, browseMovies.length);
        assertEquals("tt1935302", browseMovies[0].getMovie_id());
        assertEquals(browseMovies[1].getDirector(), "Gordon Flemyng");

    }
    @Test
    public void validBrowseKeywordQueryParamsOrderby_Year_Limit_25_Offset_25_Asc() {
        String email = UserAccounts.validEmail;
        String session_id = UserAccounts.session_id;
        String keywords = "spy";
        Result expectedResult = Result.MOVIES_FOUND;


        MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.putSingle("email", email);
        headers.putSingle("session_id", session_id);

        MultivaluedHashMap<String, Object> query = new MultivaluedHashMap<>();
        query.putSingle("orderby", "year");
        query.putSingle("direction", "asc");
        query.putSingle("limit", 25);
        query.putSingle("offset", 25);

        ServiceResponse<BrowseKeywordResponseModel> response = MovieSocket.getBrowseByKeyword(headers, query, keywords);
        BrowseMovieModel[] movies = response.getEntity().getMovies();

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(25, movies.length);
        assertEquals("tt0095532", movies[2].getMovie_id());
    }

    @Test
    public void validPrivilege_BrowseKeyword_Invalid_OrderBy() {
        String email = UserAccounts.validEmail;
        String session_id = UserAccounts.session_id;
        String keywords = "crime fighter";
        Result expectedResult = Result.MOVIES_FOUND;

        MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.putSingle("email", email);
        headers.putSingle("session_id", session_id);

        MultivaluedHashMap<String, Object> query = new MultivaluedHashMap<>();
        query.putSingle("orderby", "flabanana");

        ServiceResponse<BrowseKeywordResponseModel> response = MovieSocket.getBrowseByKeyword(headers, query, keywords);
        BrowseMovieModel[] movies = response.getEntity().getMovies();
        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(9, movies.length);
        assertEquals("tt0103060", movies[6].getMovie_id());
        assertEquals("/9myrRcegWGGp24mpVfkD4zhUfhi.jpg", movies[1].getBackdrop_path());
    }


    @Test
    public void validPrivilege_BrowseKeywordInvalidLimit() {
        String email = UserAccounts.validEmail;
        String session_id = UserAccounts.session_id;
        String keywords = "judge";
        Result expectedResult = Result.MOVIES_FOUND;


        MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.putSingle("email", email);
        headers.putSingle("session_id", session_id);

        MultivaluedHashMap<String, Object> query = new MultivaluedHashMap<>();
        query.putSingle("limit", "99");

        ServiceResponse<BrowseKeywordResponseModel> response = MovieSocket.getBrowseByKeyword(headers, query, keywords);
        BrowseMovieModel[] movies = response.getEntity().getMovies();
        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getMessage(), response.getEntity().getMessage());
        assertEquals(10, movies.length);
        assertEquals(movies[7].getMovie_id(), "tt1045772");
        assertEquals(movies[6].getDirector(), "Etan Cohen");

    }

    @Test
    public void validPrivilege_BrowseKeywordKeywordsDNE() {
        String email = UserAccounts.validEmail;
        String session_id = UserAccounts.session_id;
        String keywords = "I am lorde, yah, I am lord,south park";
        Result expectedResult = Result.MOVIES_NOT_FOUND;
        MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.putSingle("email", email);
        headers.putSingle("session_id", session_id);

        MultivaluedHashMap<String, Object> query = new MultivaluedHashMap<>();

        ServiceResponse<BrowseKeywordResponseModel> response = MovieSocket.getBrowseByKeyword(headers, query, keywords);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getMessage(), response.getEntity().getMessage());
        assertNull(response.getEntity().getMovies());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

}

