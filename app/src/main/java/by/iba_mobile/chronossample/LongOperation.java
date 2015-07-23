package by.iba_mobile.chronossample;

import android.support.annotation.Nullable;

/**
 * Created by Alex on 23.07.2015.
 */
public class LongOperation extends BaseOperation {

    @Nullable
    @Override
    public Integer run() {
        final Integer result = 0;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            // do nothing, thread is interrupted, which means a system wants to stop the run
        }
        return result;
    }
}
