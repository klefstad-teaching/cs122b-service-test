package edu.uci.ics.cs122b.test.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.uci.ics.cs122b.test.base.ResponseModel;

import javax.ws.rs.core.Response;

public class ServiceResponse <T extends ResponseModel>
{
    private final static ObjectMapper MAPPER;
    private Integer status;
    private T entity;

    static
    {
        MAPPER = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    /**
     * Creates an instance of ServiceResponse
     *
     * @param status the status code of the Response
     * @param entity the entity in object form from the Response
     */
    private ServiceResponse(Integer status, T entity)
    {
        this.status = status;
        this.entity = entity;
    }

    /**
     * Creates an instance of ServiceResponse
     *
     * @param status the status code of the Response
     */
    private ServiceResponse(Integer status)
    {
        this.status = status;
        this.entity = null;
    }

    /**
     * Creates an instance of ServiceResponse
     *
     * @param response      an instance of {@link Response} returned from the MicroService
     * @return an instance of ServiceResponse
     */
    static ServiceResponse buildResponse(Response response)
    {

        return new ServiceResponse<>(response.getStatus());
    }

    /**
     * Creates an instance of ServiceResponse, building the entity from the given responseClass
     *
     * @param response      an instance of {@link Response} returned from the MicroService
     * @param responseClass the model class that will be used to map the entity
     * @param <T>           model class that extends {@link ResponseModel}
     * @return an instance of ServiceResponse
     */
    static <T extends ResponseModel> ServiceResponse<T> buildResponse(Response response, Class<T> responseClass)
    {
        T responseModel = response.readEntity(responseClass);

        return new ServiceResponse<>(response.getStatus(), responseModel);
    }

    /**
     *
     * @return current status
     */
    public Integer getStatus()
    {
        return status;
    }

    /**
     *
     * @return current entity
     */
    public T getEntity()
    {
        return entity;
    }
}
