package com.dimeno.adapter.meta;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * LoadMoreState
 * Created by wangzhen on 2020/6/10.
 */
@IntDef(flag = true, value = {
        LoadMoreState.LOADING,
        LoadMoreState.NO_MORE,
        LoadMoreState.ERROR
})
@Retention(RetentionPolicy.SOURCE)
public @interface LoadMoreState {
    int LOADING = 0;
    int NO_MORE = 1;
    int ERROR = 2;
}
