package by.iba_mobile.chronossample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.redmadrobot.chronos.gui.activity.ChronosActivity;


public class MainActivity extends ChronosActivity implements View.OnClickListener {

    private TextView textTasckCounter;
    private TextView textTasckSuccess;
    private TextView textTasckFail;
    private int taskCounter = 0;
    private int taskSuccess = 0;
    private int taskFail = 0;

    private int lastOperationId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTasckCounter = (TextView) findViewById(R.id.text_task_counter);
        textTasckSuccess = (TextView) findViewById(R.id.text_task_success);
        textTasckFail = (TextView) findViewById(R.id.text_task_fail);
        findViewById(R.id.btn_add_long_task).setOnClickListener(this);
        findViewById(R.id.btn_add_short_task).setOnClickListener(this);
        findViewById(R.id.btn_stop_last_operation).setOnClickListener(this);
        updateTaskCount();
        updateTaskFail();
        updateTaskSuccess();
    }

    private void updateTaskCount() {
        textTasckCounter.setText("Task count: " + taskCounter);
    }

    private void updateTaskSuccess() {
        textTasckSuccess.setText("Task success: " + taskSuccess);
    }

    private void updateTaskFail() {
        textTasckFail.setText("Task fail: " + taskFail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_long_task:
                taskCounter++;
                updateTaskCount();
                lastOperationId = runOperation(new LongOperation());
                break;

            case R.id.btn_add_short_task:
                taskCounter++;
                updateTaskCount();
                lastOperationId = runOperation(new ShortOperation());
                break;

            case R.id.btn_stop_last_operation:
                if (lastOperationId != -1) {
                    cancelOperation(lastOperationId);
                    lastOperationId = -1;
                    taskCounter--;
                    updateTaskCount();
                }
                break;
        }
    }

    public void onOperationFinished(final BaseOperation.Result result) {
        if (result.isSuccessful()) {
            taskSuccess++;
            updateTaskSuccess();
        } else {
            taskFail++;
            updateTaskFail();
        }
        taskCounter--;
        updateTaskCount();
    }
}
