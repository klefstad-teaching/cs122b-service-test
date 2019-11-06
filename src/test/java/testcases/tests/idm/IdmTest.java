package testcases.tests.idm;

import edu.uci.ics.cs122b.test.common.Json;
import edu.uci.ics.cs122b.test.common.Result;
import edu.uci.ics.cs122b.test.util.ServiceResponse;
import org.junit.Ignore;
import org.junit.Test;
import testcases.model.hw2.response.*;
import testcases.socket.IdmSocket;

import static org.junit.Assert.*;

public class IdmTest
{
    public static final int EXPECTED_SESSION_ID_LENGTH = 64;
    public static final String USER_SESSION     = "3c441bdac83d2e5027c6bf237121ddcebf4f6046a8db136d8026048c0b0cdabf2ac6301db8d09172bb100211f9b742f1245b921630e6fa9fa63d806f99177cbc";

    public static final String SESSION_ACTIVE   = "15b99dfdec7e615846abe541b535ab7fb5af17f263e577dfeb4ebd4618d043e9161f3f753afb972a12d775d31568010a77be008b883084b3afa663b11d1cf2cc";
    public static final String SESSION_EXPIRED  = "cdf061488306a2f2e3d97260564eaa3be5a17defc514a690a9b41b4fa7335757b66c4da6e4bdd6570cc77033dd887ab62ca53cea452247461dedca35737c126f";
    public static final String SESSION_CLOSED   = "8a5c59cceac13d6d8a4ea43e0178aedf9dbec9cb86a0d7b3b4bf7fd3d0780b501b801bab816a7edc45cc06cb0f57b6a933eace485350db9e7a4f4b5d8eda2ffb";
    public static final String SESSION_REVOKED  = "15b99dfdec7e615846abe541b535ab7fb5af17f263e577dfeb4ebd4618d043e9161f3f753afb972a12d775d31568010a77be008b883084b3afa663b11d1cf2c4";
    public static final String SESSION_NOTFOUND = "ef6fbf756c921e93bb11ec2ec4b55e5a81425addaf060554fd94dd4675fd7a8df01e27cd9479042142eaac6129aad8fe98083100de3fddfd7c052f32b0c7295a";

    //*********************************************
    //
    //                Register Tests
    //
    //*********************************************
    @Test
    public void registerSuccessfully()
    {
        Result expectedResult = Result.USER_REGISTERED_SUCCESSFULLY;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "AAAbbb111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("RegisterSuccess@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerEmailAlreadyInUse()
    {
        Result expectedResult = Result.EMAIL_ALREADY_IN_USE;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "AAAbbb111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("AlreadyInUse@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerPasswordNotMeetLengthRequirementTooShort()
    {
        Result expectedResult = Result.PASSWORD_NOT_MEET_LENGTH_REQUIREMENTS;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "Aa1";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("peter@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerPasswordNotMeetLengthRequirementTooLong()
    {
        Result expectedResult = Result.PASSWORD_NOT_MEET_LENGTH_REQUIREMENTS;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("peter@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerEmailInvalidFormatNoEmail()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "AAAAAaaaaa111";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerEmailInvalidFormatNoAT()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "AAAAAaaaaa111";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("peteruci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerEmailInvalidFormatNoDomain()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "AAAAAaaaaa111";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("peter@.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerEmailInvalidFormatNoExtension()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "AAAAAaaaaa111";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("peter@uci.", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerEmailInvalidFormatContainsNotAlphanumericChar()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "AAAAAaaaaa111";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("peter#$%^@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerEmailInvalidLengthEmptyString()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_LENGTH;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "AAAAAaaaaa111";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerEmailInvalidLengthNull()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_LENGTH;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "AAAAAaaaaa111";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister(null, passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerParse()
    {
        Result expectedResult = Result.JSON_PARSE_EXCEPTION;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister(Json.PARSE_EXCEP);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerMapping()
    {
        Result expectedResult = Result.JSON_MAPPING_EXCEPTION;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister(Json.MAPPING_EXCEP);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerPasswordInvalidLengthEmpty()
    {
        Result expectedResult = Result.PASSWORD_INVALID_LENGTH;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("peter@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerPasswordInvalidLengthNull()
    {
        Result expectedResult = Result.PASSWORD_INVALID_LENGTH;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("peter@uci.edu", null);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerPasswordNotMeetCharacterRequirement_NoUpperCaseAlpha()
    {
        Result expectedResult = Result.PASSWORD_NOT_MEET_CHARACTER_REQUIREMENTS;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "aaabbb111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("peter@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerPasswordNotMeetCharacterRequirement_NoLowerCaseAlpha()
    {
        Result expectedResult = Result.PASSWORD_NOT_MEET_CHARACTER_REQUIREMENTS;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "AAABBB111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("peter@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void registerPasswordNotMeetCharacterRequirement_NoNumeric()
    {
        Result expectedResult = Result.PASSWORD_NOT_MEET_CHARACTER_REQUIREMENTS;
        RegisterResponseModel expectedModel = new RegisterResponseModel(expectedResult);
        String password = "AAABBBcccddd";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<RegisterResponseModel> response = IdmSocket.postRegister("peter@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }


    //*********************************************
    //
    //                 Login Tests
    //
    //*********************************************
    @Test
    public void loginSuccessfully()
    {
        Result expectedResult = Result.LOGGED_IN_SUCCESSFULLY;
        String password = "AAAbbb111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin("AlreadyInUse@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void loginPasswordInvalidLength_EmptyString()
    {
        Result expectedResult = Result.PASSWORD_INVALID_LENGTH;
        String password = "";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin("AlreadyInUse@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void loginPasswordInvalidLength_Null()
    {
        Result expectedResult = Result.PASSWORD_INVALID_LENGTH;

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin("AlreadyInUse@uci.edu", null);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void loginEmailAddressInvalidLength_EmptyString()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_LENGTH;
        String password = "AAAaaa111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin("", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void loginEmailAddressInvalidLength_Null()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_LENGTH;
        String password = "AAAaaa111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin(null, passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void loginEmailAddressInvalidFormat_NoEmail()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        String password = "AAAaaa111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin("@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void loginEmailAddressInvalidFormat_NoAt()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        String password = "AAAaaa111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin("peteruci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void loginEmailAddressInvalidFormat_NoDomain()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        String password = "AAAaaa111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin("peter@.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void loginEmailAddressInvalidFormat_NoExtension()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        String password = "AAAaaa111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin("peter@uci.", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void loginParse()
    {
        Result expectedResult = Result.JSON_PARSE_EXCEPTION;

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin(Json.PARSE_EXCEP);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
        assertEquals(null, response.getEntity().getSession_id());
    }

    @Test
    public void loginMapping()
    {
        Result expectedResult = Result.JSON_MAPPING_EXCEPTION;
        LoginResponseModel expectedModel = new LoginResponseModel(expectedResult);

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin(Json.MAPPING_EXCEP);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void loginPasswordNotMatch()
    {
        Result expectedResult = Result.PASSWORD_NOT_MATCH;
        LoginResponseModel expectedModel = new LoginResponseModel(expectedResult);
        String password = "BBBbbb333444";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin("AlreadyInUse@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void loginUserNotFound()
    {
        Result expectedResult = Result.USER_NOT_FOUND;
        LoginResponseModel expectedModel = new LoginResponseModel(expectedResult);
        String password = "AAAbbb111222";
        char[] passwordArray = password.toCharArray();

        ServiceResponse<LoginResponseModel> response = IdmSocket.postLogin("LoginDoesNotExist@uci.edu", passwordArray);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }





    //*********************************************
    //
    //                 Session Tests
    //
    //*********************************************
    @Test
    public void tokenLengthTooShort()
    {
        Result expectedResult = Result.TOKEN_INVALID_LENGTH;
        String session_id = "TOOSHORT";

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("peter@uci.edu", session_id);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void tokenLengthTooLong()
    {
        Result expectedResult = Result.TOKEN_INVALID_LENGTH;
        String session_id = "TOOLONG" + USER_SESSION;

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("peter@uci.edu", session_id);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void sessionTokenInvalidLength_EmptyString()
    {
        Result expectedResult = Result.TOKEN_INVALID_LENGTH;
        String session_id = "";

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("peter@uci.edu", session_id);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    @Ignore
    public void sessionTokenInvalidLength_Null()
    {
        Result expectedResult = Result.TOKEN_INVALID_LENGTH;

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("peter@uci.edu", null);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void sessionEmailAddressInvalidFormat_NoEmail()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("@uci.edu", USER_SESSION);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void sessionEmailAddressInvalidFormat_NoAt()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("peteruci.edu", USER_SESSION);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void sessionEmailAddressInvalidFormat_NoDomain()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("peter@.edu", USER_SESSION);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void sessionEmailAddressInvalidFormat_NoExtension()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("peter@uci.", USER_SESSION);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }


    @Test
    public void sessionEmailAddressInvalidLength_EmptyString()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_LENGTH;

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("", USER_SESSION);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void sessionEmailAddressInvalidLength_Null()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_LENGTH;

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession(null, USER_SESSION);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void sessionParse()
    {
        Result expectedResult = Result.JSON_PARSE_EXCEPTION;

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession(Json.PARSE_EXCEP);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
        assertEquals(null, response.getEntity().getSession_id());
    }

    @Test
    public void sessionMapping()
    {
        Result expectedResult = Result.JSON_MAPPING_EXCEPTION;
        SessionResponseModel expectedModel = new SessionResponseModel(expectedResult);

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession(Json.MAPPING_EXCEP);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void sessionUserNotFound()
    {
        Result expectedResult = Result.USER_NOT_FOUND;

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("NotRealUser@uci.edu", USER_SESSION);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void sessionActive()
    {
        Result expectedResult = Result.SESSION_ACTIVE;

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("ActiveSession@uci.edu", SESSION_ACTIVE);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
        assertNotNull(response.getEntity().getSession_id());
    }

    @Test
    public void sessionExpired()
    {
        Result expectedResult = Result.SESSION_EXPIRED;
        SessionResponseModel expectedModel = new SessionResponseModel(expectedResult);

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("ExpiredSession@uci.edu", SESSION_EXPIRED);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void sessionClosed()
    {
        Result expectedResult = Result.SESSION_CLOSED;
        SessionResponseModel expectedModel = new SessionResponseModel(expectedResult);

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("ClosedSession@uci.edu", SESSION_CLOSED);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void sessionRevoked()
    {
        Result expectedResult = Result.SESSION_REVOKED;
        SessionResponseModel expectedModel = new SessionResponseModel(expectedResult);

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("RevokedSession@uci.edu", SESSION_REVOKED);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void sessionNotFound()
    {
        Result expectedResult = Result.SESSION_NOT_FOUND;

        ServiceResponse<SessionResponseModel> response = IdmSocket.postSession("NotFoundSession@uci.edu", SESSION_NOTFOUND);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }





    //*********************************************
    //
    //                 Privilege Tests
    //
    //*********************************************
    @Test
    public void privilegeLevelOutOfRange()
    {
        Result expectedResult = Result.PLEVEL_OUT_OF_RANGE;
        int plevel = 0;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege("peter@uci.edu", plevel);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void privilegeLevelOutOfRange2()
    {
        Result expectedResult = Result.PLEVEL_OUT_OF_RANGE;
        int plevel = 6;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege("peter@uci.edu", plevel);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void privilegeEmailAddressInvalidFormat_NoEmail()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        int plevel = 5;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege("@uci.edu", plevel);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void privilegeEmailAddressInvalidFormat_NoAt()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        int plevel = 5;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege("peteruci.edu", plevel);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void privilegeEmailAddressInvalidFormat_NoDomain()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        int plevel = 5;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege("peter@.edu", plevel);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void privilegeEmailAddressInvalidFormat_NoExtension()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_FORMAT;
        int plevel = 5;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege("peter@uci.", plevel);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }


    @Test
    public void privilegeEmailAddressInvalidLength_EmptyString()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_LENGTH;
        int plevel = 5;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege("", plevel);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void privilegeEmailAddressInvalidLength_Null()
    {
        Result expectedResult = Result.EMAIL_ADDRESS_INVALID_LENGTH;
        int plevel = 5;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege(null, plevel);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void privilegeParse()
    {
        Result expectedResult = Result.JSON_PARSE_EXCEPTION;
        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege(Json.PARSE_EXCEP);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void privilegeMapping()
    {
        Result expectedResult = Result.JSON_MAPPING_EXCEPTION;
        PrivilegeResponseModel expectedModel = new PrivilegeResponseModel(expectedResult);

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege(Json.MAPPING_EXCEP);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedModel, response.getEntity());
    }

    @Test
    public void privilegeUserNotFound()
    {
        Result expectedResult = Result.USER_NOT_FOUND;
        int plevel = 5;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege("NotRealUser@uci.edu", plevel);

        assertEquals(expectedResult.getStatus(), response.getStatus());
        assertEquals(expectedResult.getResultCode(), response.getEntity().getResultCode());
    }

    @Test
    public void privilegeUserLevel()
    {
        String userEmail = "UserLevel@uci.edu";
        Result sufficientResult = Result.SUFFICIENT_PRIVILEGE;
        Result insufficientResult = Result.INSUFFICIENT_PRIVILEGE;
        int plevel = 5;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege(userEmail, plevel);

        assertEquals(sufficientResult.getStatus(), response.getStatus());
        assertEquals(sufficientResult.getResultCode(), sufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(userEmail, 4);

        assertEquals(insufficientResult.getStatus(), response.getStatus());
        assertEquals(insufficientResult.getResultCode(), insufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(userEmail, 3);

        assertEquals(insufficientResult.getStatus(), response.getStatus());
        assertEquals(insufficientResult.getResultCode(), insufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(userEmail, 2);

        assertEquals(insufficientResult.getStatus(), response.getStatus());
        assertEquals(insufficientResult.getResultCode(), insufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(userEmail, 1);

        assertEquals(insufficientResult.getStatus(), response.getStatus());
        assertEquals(insufficientResult.getResultCode(), insufficientResult.getResultCode());
    }

    @Test
    public void privilegeServiceLevel()
    {
        String serviceEmail = "ServiceLevel@uci.edu";
        Result sufficientResult = Result.SUFFICIENT_PRIVILEGE;
        Result insufficientResult = Result.INSUFFICIENT_PRIVILEGE;
        int plevel = 4;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege(serviceEmail, plevel);

        assertEquals(sufficientResult.getStatus(), response.getStatus());
        assertEquals(sufficientResult.getResultCode(), sufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(serviceEmail, 4);

        assertEquals(sufficientResult.getStatus(), response.getStatus());
        assertEquals(sufficientResult.getResultCode(), sufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(serviceEmail, 3);

        assertEquals(insufficientResult.getStatus(), response.getStatus());
        assertEquals(insufficientResult.getResultCode(), insufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(serviceEmail, 2);

        assertEquals(insufficientResult.getStatus(), response.getStatus());
        assertEquals(insufficientResult.getResultCode(), insufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(serviceEmail, 1);

        assertEquals(insufficientResult.getStatus(), response.getStatus());
        assertEquals(insufficientResult.getResultCode(), insufficientResult.getResultCode());
    }

    @Test
    public void privilegeEmployeeLevel()
    {
        String employeeEmail = "EmployeeLevel@uci.edu";
        Result sufficientResult = Result.SUFFICIENT_PRIVILEGE;
        Result insufficientResult = Result.INSUFFICIENT_PRIVILEGE;
        int plevel = 3;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege(employeeEmail, plevel);

        assertEquals(sufficientResult.getStatus(), response.getStatus());
        assertEquals(sufficientResult.getResultCode(), sufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(employeeEmail, 4);

        assertEquals(sufficientResult.getStatus(), response.getStatus());
        assertEquals(sufficientResult.getResultCode(), sufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(employeeEmail, 3);

        assertEquals(sufficientResult.getStatus(), response.getStatus());
        assertEquals(sufficientResult.getResultCode(), sufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(employeeEmail, 2);

        assertEquals(insufficientResult.getStatus(), response.getStatus());
        assertEquals(insufficientResult.getResultCode(), insufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(employeeEmail, 1);

        assertEquals(insufficientResult.getStatus(), response.getStatus());
        assertEquals(insufficientResult.getResultCode(), insufficientResult.getResultCode());
    }

    @Test
    public void privilegeAdminLevel()
    {
        String adminEmail = "AdminLevel@uci.edu";
        Result sufficientResult = Result.SUFFICIENT_PRIVILEGE;
        Result insufficientResult = Result.INSUFFICIENT_PRIVILEGE;
        int plevel = 2;

        ServiceResponse<PrivilegeResponseModel> response = IdmSocket.postPrivilege(adminEmail, plevel);

        assertEquals(sufficientResult.getStatus(), response.getStatus());
        assertEquals(sufficientResult.getResultCode(), sufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(adminEmail, 4);

        assertEquals(sufficientResult.getStatus(), response.getStatus());
        assertEquals(sufficientResult.getResultCode(), sufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(adminEmail, 3);

        assertEquals(sufficientResult.getStatus(), response.getStatus());
        assertEquals(sufficientResult.getResultCode(), sufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(adminEmail, 2);

        assertEquals(sufficientResult.getStatus(), response.getStatus());
        assertEquals(sufficientResult.getResultCode(), sufficientResult.getResultCode());

        response = IdmSocket.postPrivilege(adminEmail, 1);

        assertEquals(insufficientResult.getStatus(), response.getStatus());
        assertEquals(insufficientResult.getResultCode(), insufficientResult.getResultCode());
    }
}
