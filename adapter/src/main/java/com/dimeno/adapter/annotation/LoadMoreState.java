package com.dimeno.adapter.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * LoadMoreState
 * Created by wangzhen on 2020/6/10.
 */
@IntDef(flag = true, value = {
        LoadMoreState.READY,
        LoadMoreState.LOADING,
        LoadMoreState.NO_MORE,
        LoadMoreState.ERROR
})
@Retention(RetentionPolicy.SOURCE)
public @interface LoadMoreState {
    int READY = 0;
    int LOADING = 1;
    int NO_MORE = 2;
    int ERROR = 3;
}
