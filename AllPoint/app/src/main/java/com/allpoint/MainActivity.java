package com.allpoint;

import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

public class MainActivity extends Activity {

    @ViewById(R.id.uname_edit_text)
    EditText email;



    EditText uname = null, upass=null;

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname= (EditText)findViewById(R.id.uname_edit_text);
        upass= (EditText)findViewById(R.id.upass_edit_text);

        login = (Button)findViewById(R.id.login_button);

       // login.setOnClickListener((View.OnClickListener) this);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Do something here

                checkValidation();

                String username,password;

                username = uname.getText().toString();
                password = upass.getText().toString();

                Toast toast=Toast.makeText(getApplicationContext(),username + " "+ password,Toast.LENGTH_LONG);
                toast.show();

            }
        });



    }

    private void checkValidation() {
        String alertMessage = "";
        if (email.getText().length() == 0 || paswd.getText().length() == 0) {
            if (email.getText().length() > 0 && paswd.getText().length() == 0) {

                alertMessage = getResources().getString(
                        R.string.alertMsg_EnterPass);
                Utils.showDialogAlert(alertMessage, LoginActivity.this);

            } else if ((email.getText().length() == 0 && paswd.getText()
                    .length() > 0)) {

                alertMessage = getResources().getString(
                        R.string.alertMsg_EnterUserName);
                Utils.showDialogAlert(alertMessage, LoginActivity.this);
            } else {
                alertMessage = getResources().getString(
                        R.string.alertMsg_EnterUNandPW);

                Utils.showDialogAlert(alertMessage, LoginActivity.this);
            }
        } else {
            // On successful login
            // make uncomment after getting proper response
            if (!Validation.isValidEmail(email.getText().toString().trim())) {
                alertMessage = getResources().getString(
                        R.string.alertMsg_EntervalidUN);
                Utils.showDialogAlert(alertMessage, LoginActivity.this);
            } /*
             * else if
             * (!Validation.passwordvalidation(paswd.getText().toString()
             * .trim())){ paswd.requestFocus(); paswd.setText(""); alertMessage
             * = getResources().getString(R.string.alertMsg_IncorrectPassword);
             * Utils.showDialogAlert(alertMessage,LoginActivity.this); }
             */else {
                Utils.hideKeyboard(LoginActivity.this);


//				alertMessage = getResources().getString(
//						R.string.en_dialogCannotConnectText);
//				Utils.showDialogAlert(alertMessage, LoginActivity.this);

                //removed flow after login if user is not active
                callLoginWebService(false);
            }
        }
    }



}
