package com.test.task.rest.resource.utils;

import com.test.task.commons.api.model.model.response.ErrorResponseModel;
import com.test.task.commons.api.model.model.response.ResponseModel;
import com.test.task.commons.api.model.model.response.impl.AbstractResultResponseModel;
import org.springframework.http.ResponseEntity;

public final class ResponseEntityUtils {

    public static final String STATUS = "STATUS";

    private ResponseEntityUtils() {
        super();
    }

    /**
     * @return ResponseEntity with HttpStatus.OK but keeps the from ResultResponseModel transient status filed extracted status in header
     */
    public static <T extends ResponseModel, E extends ErrorResponseModel, R extends AbstractResultResponseModel<T, E>> ResponseEntity<R> okWithStatusInHeader(R resultResponse) {
        return ResponseEntity.ok().header(STATUS, String.valueOf(resultResponse.getHttpStatusCode())).body(resultResponse);
    }

}
