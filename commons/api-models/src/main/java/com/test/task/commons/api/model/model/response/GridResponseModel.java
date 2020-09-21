package com.test.task.commons.api.model.model.response;

import java.util.List;


public interface GridResponseModel<T extends ResponseModel> extends ResponseModel {

    long totalCount();

    List<T> items();
}
