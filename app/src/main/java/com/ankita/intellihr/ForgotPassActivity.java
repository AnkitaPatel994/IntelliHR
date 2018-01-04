package com.ankita.intellihr;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassActivity extends AppCompatActivity {

    TextInputLayout input_layout_emailFP;
    EditText txtEmailFP;
    Button btnForPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.title_activity_forgot_pass);
        setContentView(R.layout.activity_forgot_pass);

        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        input_layout_emailFP = (TextInputLayout)findViewById(R.id.input_layout_emailFP);

        txtEmailFP = (EditText)findViewById(R.id.txtEmailFP);
        txtEmailFP.addTextChangedListener(new MyTextWatcher(txtEmailFP));

        btnForPass = (Button)findViewById(R.id.btnForPass);

        btnForPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = txtEmailFP.getText().toString();

                if(Email.equals("") && !Email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
                {
                    validateEmail();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Forgot Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean validateEmail() {

        if (txtEmailFP.getText().toString().trim().isEmpty())
        {
            input_layout_emailFP.setError(getString(R.string.err_msg_email));
            requestFocus(txtEmailFP);
            return false;
        }
        else if (!txtEmailFP.getText().toString().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
        {
            input_layout_emailFP.setError(getString(R.string.err_msg_email_valid));
            requestFocus(txtEmailFP);
            return false;
        }
        else
        {
            input_layout_emailFP.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
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

                case R.id.txtEmailFP:
                    validateEmail();
                    break;
            }
        }
    }
}
