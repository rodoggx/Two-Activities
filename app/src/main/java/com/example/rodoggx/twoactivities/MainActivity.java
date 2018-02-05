package com.example.rodoggx.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final int TEXT_REQUEST = 1;
    public static final String EXTRA_MESSAGE =
            "com.example.rodoggx.twoactivities.extra.MESSAGE";

    private Button button;
    private EditText messageEditText;
    private TextView replyMsg;
    private TextView replyHdr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button_main);
        messageEditText = (EditText) findViewById(R.id.edit_text_main);

        replyMsg = (TextView) findViewById(R.id.main_msg_reply);
        replyHdr = (TextView) findViewById(R.id.main_hdr_reply);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + "Start Second Activity");
                startSecondActivity(view);
            }
        });

        if (savedInstanceState != null) {
//            boolean isVisible = savedInstanceState.getBoolean("reply_Hdr");
//            if (isVisible) {
                replyMsg.setText(savedInstanceState.getString("reply_Msg"));
                replyMsg.setVisibility(View.VISIBLE);
//            }
            Log.d(TAG, "onCreate: savedInstanceState != null" );
            Log.d(TAG, "onCreate: savedInstanceState != null " + savedInstanceState.getString("reply_Msg"));
        }
    }

    public void startSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        String message = messageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                replyMsg.setText(reply);
                replyMsg.setVisibility(View.VISIBLE);
            }
            Log.d(TAG, "onActivityResult: " + TEXT_REQUEST);
            Log.d(TAG, "onActivityResult: " + resultCode + " ResultOK " + RESULT_OK);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//            outState.putBoolean("reply_Hdr", true);
        Log.d(TAG, "onSaveInstanceState: " + replyMsg.getText().toString());
            outState.putString("reply_Msg", replyMsg.getText().toString());
    }
}
