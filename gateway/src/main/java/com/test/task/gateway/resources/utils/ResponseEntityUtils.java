package com.test.task.gateway.resources.utils;

import com.test.task.commons.api.model.model.response.ErrorResponseModel;
import com.test.task.commons.api.model.model.response.ResponseModel;
import com.test.task.commons.api.model.model.response.impl.AbstractResultResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;

public final class ResponseEntityUtils {

    public static final String STATUS = "STATUS";

    private ResponseEntityUtils() {
        super();
    }

    public static <T extends ResponseModel, E extends ErrorResponseModel, R extends AbstractResultResponseModel<T, E>> int getStatusFromHeader(ResponseEntity<R> responseEntity) {
        final List<String> status = responseEntity.getHeaders().get(STATUS);
        if (!CollectionUtils.isEmpty(status)) {
            return Integer.parseInt(status.get(0));
        }
        return 200;
    }

    /**
     * @return ResponseEntity with status got from headers
     */
    public static <T extends ResponseModel, E extends ErrorResponseModel, R extends AbstractResultResponseModel<T, E>> ResponseEntity<R> withCorrectStatus(ResponseEntity<R> responseEntity) {
        return ResponseEntity.status(ResponseEntityUtils.getStatusFromHeader(responseEntity)).body(responseEntity.getBody());
    }

}
