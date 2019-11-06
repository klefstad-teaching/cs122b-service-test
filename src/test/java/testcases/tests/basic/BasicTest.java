package testcases.tests.basic;

import edu.uci.ics.cs122b.test.common.Result;
import edu.uci.ics.cs122b.test.common.Json;
import testcases.socket.BasicSocket;
import testcases.model.hw1.response.*;
import edu.uci.ics.cs122b.test.util.ServiceResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicTest
{
    //*********************************************
    //
    //                Hello Test
    //
    //*********************************************
    @Test
    public void hello()
    {
        Integer expectedStatus = 200;
        ServiceResponse response = BasicSocket.getHello();

        assertEquals(response.getStatus(), expectedStatus);
    }





    //*********************************************
    //
    //                Reverse Tests
    //
    //*********************************************
    @Test
    public void reverseStringLowerCase()
    {
        Result expectedResult = Result.STRING_SUCCESSFULLY_REVERSED;
        ReverseResponseModel expectedModel = new ReverseResponseModel(expectedResult, "test");

        ServiceResponse<ReverseResponseModel> response = BasicSocket.getReverseString("tset");

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void reverseStringUpperCase()
    {
        Result expectedResult = Result.STRING_SUCCESSFULLY_REVERSED;
        ReverseResponseModel expectedModel = new ReverseResponseModel(expectedResult, "TSET");

        ServiceResponse<ReverseResponseModel> response = BasicSocket.getReverseString("TEST");

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void reverseStringMix()
    {
        Result expectedResult = Result.STRING_SUCCESSFULLY_REVERSED;
        ReverseResponseModel expectedModel = new ReverseResponseModel(expectedResult, "tSeT");

        ServiceResponse<ReverseResponseModel> response = BasicSocket.getReverseString("TeSt");

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void reverseStringSpace()
    {
        Result expectedResult = Result.STRING_SUCCESSFULLY_REVERSED;
        ReverseResponseModel expectedModel = new ReverseResponseModel(expectedResult, "t s eT");

        ServiceResponse<ReverseResponseModel> response = BasicSocket.getReverseString("Te s t");

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void reverseStringNumber()
    {
        Result expectedResult = Result.STRING_SUCCESSFULLY_REVERSED;
        ReverseResponseModel expectedModel = new ReverseResponseModel(expectedResult, "4321");

        ServiceResponse<ReverseResponseModel> response = BasicSocket.getReverseString("1234");

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void reverseStringUnderScore()
    {
        Result expectedResult = Result.STRING_SUCCESSFULLY_REVERSED;
        ReverseResponseModel expectedModel = new ReverseResponseModel(expectedResult, "__b321A_");

        ServiceResponse<ReverseResponseModel> response = BasicSocket.getReverseString("_A123b__");

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void reverseStringInvalidEdge()
    {
        Result expectedResult = Result.STRING_CONTAINS_INVALID_CHARACTERS;
        ReverseResponseModel expectedModel = new ReverseResponseModel(expectedResult, null);

        ServiceResponse<ReverseResponseModel> response = BasicSocket.getReverseString("!ABC!");

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void reverseStringInvalidMid()
    {
        Result expectedResult = Result.STRING_CONTAINS_INVALID_CHARACTERS;
        ReverseResponseModel expectedModel = new ReverseResponseModel(expectedResult, null);

        ServiceResponse<ReverseResponseModel> response = BasicSocket.getReverseString("AB!CD");

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void reverseStringInvalidAll()
    {
        Result expectedResult = Result.STRING_CONTAINS_INVALID_CHARACTERS;
        ReverseResponseModel expectedModel = new ReverseResponseModel(expectedResult, null);

        ServiceResponse<ReverseResponseModel> response = BasicSocket.getReverseString("!!!!");

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }





    //*********************************************
    //
    //                Math Tests
    //
    //*********************************************
    @Test
    public void mathMapping()
    {
        Result expectedResult = Result.JSON_MAPPING_EXCEPTION;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, null);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(Json.MAPPING_EXCEP);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathParse()
    {
        Result expectedResult = Result.JSON_PARSE_EXCEPTION;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, null);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(Json.PARSE_EXCEP);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathNormal()
    {
        Result expectedResult = Result.CALCULATION_SUCCESSFUL;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, 5);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(1, 2, 3);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathEdgeX1()
    {
        Result expectedResult = Result.CALCULATION_SUCCESSFUL;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, 1);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(1, 1, 0);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathEdgeX2()
    {
        Result expectedResult = Result.CALCULATION_SUCCESSFUL;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, 99);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(99, 1, 0);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathEdgeY1()
    {
        Result expectedResult = Result.CALCULATION_SUCCESSFUL;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, 1);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(1, 1, 0);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathEdgeY2()
    {
        Result expectedResult = Result.CALCULATION_SUCCESSFUL;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, 99);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(1, 99, 0);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathEdgeZ1()
    {
        Result expectedResult = Result.CALCULATION_SUCCESSFUL;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, -9);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(1, 1, -10);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathEdgeZ2()
    {
        Result expectedResult = Result.CALCULATION_SUCCESSFUL;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, 11);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(1, 1, 10);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathPastLimitX1()
    {
        Result expectedResult = Result.DATA_CONTAINS_INVALID_INTEGERS;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, null);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(0, 1, 0);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathPastLimitX2()
    {
        Result expectedResult = Result.DATA_CONTAINS_INVALID_INTEGERS;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, null);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(100, 1, 0);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathPastLimitY1()
    {
        Result expectedResult = Result.DATA_CONTAINS_INVALID_INTEGERS;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, null);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(1, 0, 0);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathPastLimitY2()
    {
        Result expectedResult = Result.DATA_CONTAINS_INVALID_INTEGERS;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, null);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(1, 100, 0);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathPastLimitZ1()
    {
        Result expectedResult = Result.DATA_CONTAINS_INVALID_INTEGERS;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, null);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(1, 1, -11);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void mathPastLimitZ2()
    {
        Result expectedResult = Result.DATA_CONTAINS_INVALID_INTEGERS;
        MathResponseModel expectedModel = new MathResponseModel(expectedResult, null);

        ServiceResponse<MathResponseModel> response = BasicSocket.postMath(1, 1, 11);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }
}
