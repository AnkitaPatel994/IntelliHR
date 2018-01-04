package com.ankita.intellihr;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout input_layout_email, input_layout_pass;
    EditText txtEmail, txtPassword;
    TextView txtForgotPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_layout_email = (TextInputLayout)findViewById(R.id.input_layout_email);
        input_layout_pass = (TextInputLayout)findViewById(R.id.input_layout_pass);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtEmail.addTextChangedListener(new MyTextWatcher(txtEmail));
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtPassword.addTextChangedListener(new MyTextWatcher(txtPassword));
        txtForgotPass = (TextView)findViewById(R.id.txtForgotPass);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,ForgotPassActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this,EmployeeInfoActivity.class);
                startActivity(i);

                /*String Email = txtEmail.getText().toString();
                String Password = txtPassword.getText().toString();

                if(Email.equals("") && !Email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
                {
                    validateEmail();
                }
                else if(Password.equals(""))
                {
                    validatePassword();
                }
                else
                {
                    Intent i = new Intent(LoginActivity.this,EmployeeInfoActivity.class);
                    startActivity(i);
                }*/

            }
        });

    }

    private boolean validatePassword() {
        if (txtPassword.getText().toString().trim().isEmpty())
        {
            input_layout_pass.setError(getString(R.string.err_msg_password));
            requestFocus(txtPassword);
            return false;
        }
        else
        {
            input_layout_pass.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {

        if (txtEmail.getText().toString().trim().isEmpty())
        {
            input_layout_email.setError(getString(R.string.err_msg_email));
            requestFocus(txtEmail);
            return false;
        }
        else if (!txtEmail.getText().toString().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
        {
            input_layout_email.setError(getString(R.string.err_msg_email_valid));
            requestFocus(txtEmail);
            return false;
        }
        else
        {
            input_layout_email.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public class MyTextWatcher implements TextWatcher {

        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.txtEmail:
                    validateEmail();
                    break;
                case R.id.txtPassword:
                    validatePassword();
                    break;
            }
        }
    }
}
