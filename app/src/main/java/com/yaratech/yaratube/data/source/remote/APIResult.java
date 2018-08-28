package com.yaratech.yaratube.data.source.remote;

public interface APIResult<T> {
     void onSuccess(T result);
     void onFail(String errorMessage);
}
