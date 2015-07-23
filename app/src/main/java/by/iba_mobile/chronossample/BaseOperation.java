package by.iba_mobile.chronossample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.redmadrobot.chronos.ChronosOperation;
import com.redmadrobot.chronos.ChronosOperationResult;

/**
 * Created by Alex on 23.07.2015.
 */
public class BaseOperation extends ChronosOperation<Integer> {

    @Nullable
    @Override
    public Integer run() {
        final Integer result = 0;
        // here you should write what you do to get the BusinessObject
        return result;
    }

    @NonNull
    @Override
    public Class<? extends ChronosOperationResult<Integer>> getResultClass() {
        return Result.class;
    }

    public final static class Result extends ChronosOperationResult<Integer> {
    }
}
