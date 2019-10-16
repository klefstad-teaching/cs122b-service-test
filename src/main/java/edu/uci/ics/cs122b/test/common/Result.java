package edu.uci.ics.cs122b.test.common;

import javax.ws.rs.core.Response.Status;
import java.util.HashMap;
import java.util.Map;

public enum Result
{
    JSON_PARSE_EXCEPTION                (-3, "JSON parse exception", Status.BAD_REQUEST),
    JSON_MAPPING_EXCEPTION              (-2, "JSON mapping exception", Status.BAD_REQUEST),

    INTERNAL_SERVER_ERROR               (-1, "Internal server error", Status.INTERNAL_SERVER_ERROR),

    /* HW1 Result Codes*/
    STRING_SUCCESSFULLY_REVERSED        (10, "String successfully reversed"),
    COULD_NOT_REVERSE_STRING            (11, "Could not reverse string"),
    STRING_IS_EMPTY                     (12, "String is empty"),
    STRING_CONTAINS_INVALID_CHARACTERS  (13, "String contains invalid characters"),

    CALCULATION_SUCCESSFUL              (20, "Calculation successful"),
    CALCULATION_UNSUCCESSFUL            (21, "Calculation unsuccessful"),
    DATA_CONTAINS_INVALID_INTEGERS      (22, "Data contains invalid integers"),

    /*HW2 Result Codes*/
    PASSWORD_INVALID_LENGTH             (-12, "Password has invalid length", Status.BAD_REQUEST),
    EMAIL_ADDRESS_INVALID_FORMAT        (-11, "Email address has invalid format", Status.BAD_REQUEST),
    EMAIL_ADDRESS_INVALID_LENGTH        (-10, "Email address has invalid length", Status.BAD_REQUEST),
    PASSWORD_NOT_MEET_LENGTH_REQUIREMENTS(12, "Password does not meet length requirements"),
    PASSWORD_NOT_MEET_CHARACTER_REQUIREMENTS(13, "Password does not meet character requirement"),
    EMAIL_ALREADY_IN_USE                 (16, "Email already in use"),
    USER_REGISTERED_SUCCESSFULLY         (110, "User registered successfully"),

    PASSWORD_NOT_MATCH                    (11, "Passwords do not match"),
    USER_NOT_FOUND                        (14, "User not found"),
    LOGGED_IN_SUCCESSFULLY               (120, "User logged in successfully"),
    SESSION_ACTIVE                        (130, "Session is active"),
    SESSION_EXPIRED                       (131, "Session is expired"),
    SESSION_CLOSED                        (132, "Session is closed"),
    SESSION_REVOKED                       (133, "Session is revoked"),
    SESSION_NOT_FOUND                     (134, "Session not found"),
    SUFFICIENT_PRIVILEGE                  (140, "User has sufficient privilege level"),
    INSUFFICIENT_PRIVILEGE                (141, "User has insufficient privilege level");

    private static final Map<Integer, String> CODE_MAP;

    static
    {
        CODE_MAP = new HashMap<>();

        for (Result r : Result.values())
            if (CODE_MAP.put(r.resultCode, r.message) != null)
                throw new IllegalArgumentException("Result Codes must be Unique!");
    }

    private final Integer resultCode;
    private final String  message;
    private final Status  status;

    Result(int resultCode, String message)
    {
        this.resultCode = resultCode;
        this.message = message;
        this.status = Status.OK;
    }

    Result(int resultCode, String message, Status status)
    {
        this.resultCode = resultCode;
        this.message = message;
        this.status = status;
    }

    public int getResultCode()
    {
        return resultCode;
    }

    public String getMessage()
    {
        return message;
    }

    public Integer getStatus()
    {
        return status.getStatusCode();
    }
}
