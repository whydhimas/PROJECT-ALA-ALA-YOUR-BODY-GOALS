package com.example.android.yourbodygoals_apps;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HelpPage extends AppCompatActivity {
    TextView mAddrEmail;
    EditText mSubjectEmail,mMessageEmail;
    Button mBtnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GreenDark));
        }

        mAddrEmail = (TextView) findViewById(R.id.tv_email);
        mSubjectEmail = (EditText) findViewById(R.id.et_subj_email);
        mMessageEmail = (EditText) findViewById(R.id.et_text_email);
        mBtnSend = (Button) findViewById(R.id.btn_email_send);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toS = mAddrEmail.getText().toString();
                String subS = mSubjectEmail.getText().toString();
                String mesS = mMessageEmail.getText().toString();

                if (mSubjectEmail.getText().toString().length() == 0) {
                    mSubjectEmail.setError("Subject required");
                } else if (mMessageEmail.getText().toString().length() == 0) {
                    mMessageEmail.setError("Text message required");
                } else {
                    Intent intentMail = new Intent(Intent.ACTION_SEND);

                    //intentMail.putExtra(Intent.EXTRA_EMAIL, toS);
                    intentMail.putExtra(Intent.EXTRA_EMAIL, new String[]{toS});
                    intentMail.putExtra(Intent.EXTRA_SUBJECT, subS);
                    intentMail.putExtra(Intent.EXTRA_TEXT, mesS);
                    intentMail.setType("message/rfc822");

                    startActivity(Intent.createChooser(intentMail, "Choose app to send feedback. Email recommended"));
                }
            }
        });
    }
}
