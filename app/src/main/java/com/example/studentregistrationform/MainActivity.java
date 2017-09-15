package com.example.studentregistrationform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
 final static String STUDENT_KEY = "STUDENT";
    EditText txtName;
    EditText txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 txtName = (EditText)findViewById(R.id.txtName);
                 txtEmail = (EditText)findViewById(R.id.txtEmail);

                if(txtName.getText().toString().length()==0 || txtEmail.getText().toString().length() ==0)
                {
                    Toast.makeText(MainActivity.this, getResources().getText(R.string.errFill),
                            Toast.LENGTH_LONG).show();

                }else
                {
                    if(isEmailValid(txtEmail.getText().toString())){
                Intent second = new Intent(MainActivity.this,DisplayActivity.class);

                second.putExtra(STUDENT_KEY,GetStudentInfo());
                startActivity(second);}
                    else {
                        txtEmail.setError(getResources().getText(R.string.invalidEmail));
                    }
            }}
        });
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    private String GetSelectedRadioText(int id)
    {
        RadioButton rbSis = (RadioButton)findViewById(R.id.radioButtonSIS);
        RadioButton rbCs = (RadioButton)findViewById(R.id.radioButtonCS);
        RadioButton rbBio = (RadioButton)findViewById(R.id.radioButtonBIO);
        RadioButton rbOthers = (RadioButton)findViewById(R.id.radioButtonOthers);
        switch (id)
        {
            case R.id.radioButtonSIS:
                return rbSis.getText().toString();
            case R.id.radioButtonCS:
                return rbCs.getText().toString();
            case R.id.radioButtonBIO:
                return rbBio.getText().toString();
            case R.id.radioButtonOthers:
                return rbOthers.getText().toString();
            default:
                return "";


        }
    }

    private Student GetStudentInfo()
    {

        RadioGroup radioGroupDepy = (RadioGroup)findViewById(R.id.radioGrpDept);
        String selectedRadio = GetSelectedRadioText( radioGroupDepy.getCheckedRadioButtonId());
        SeekBar seekBarMood = (SeekBar) findViewById(R.id.seekBar);

        return new Student(txtName.getText().toString(),
                txtEmail.getText().toString(),
                selectedRadio,
                "Active",
                Double.parseDouble(String.valueOf(seekBarMood.getProgress())));

    }
}
