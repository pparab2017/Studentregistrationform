package com.example.studentregistrationform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class EditActivity extends AppCompatActivity {
    EditText textName;
    EditText textEmail;
    SeekBar seekBarMood;
    RadioGroup radioGroupDept;
    Student DISPLAY_STUDENT;
    LinearLayout dept;
    LinearLayout mood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        textName = (EditText)findViewById(R.id.txtName);
        textEmail = (EditText)findViewById(R.id.txtEmail);
        radioGroupDept = (RadioGroup)findViewById(R.id.radioGrpDept);
        dept = (LinearLayout)findViewById(R.id.layoutDept);
        seekBarMood = (SeekBar)findViewById(R.id.seekBar);
        mood = (LinearLayout)findViewById(R.id.layoutMood);



        if(getIntent().getExtras() != null)
        {
            if(getIntent().getExtras().containsKey(DisplayActivity.NAME_KEY)) {

                DISPLAY_STUDENT = (Student)getIntent().getExtras().getParcelable(DisplayActivity.NAME_KEY);
                textName.setText(DISPLAY_STUDENT.getName());
                textName.setVisibility(View.VISIBLE);
            }

           else if(getIntent().getExtras().containsKey(DisplayActivity.EMAIL_KEY)) {

                DISPLAY_STUDENT = (Student)getIntent().getExtras().getParcelable(DisplayActivity.EMAIL_KEY);
                textEmail.setText(DISPLAY_STUDENT.getEmailAddress());
                textEmail.setVisibility(View.VISIBLE);
            }

            else if(getIntent().getExtras().containsKey(DisplayActivity.DEPARTMENT_KEY)) {

                DISPLAY_STUDENT = (Student)getIntent().getExtras().getParcelable(DisplayActivity.DEPARTMENT_KEY);
                String deptValue = DISPLAY_STUDENT.getDepartment();
                SetSelectedRadio(deptValue);
                dept.setVisibility(View.VISIBLE);
            }

            else if(getIntent().getExtras().containsKey(DisplayActivity.MOOD_KEY)) {
                DISPLAY_STUDENT = (Student)getIntent().getExtras().getParcelable(DisplayActivity.MOOD_KEY);
                seekBarMood.setProgress((int) DISPLAY_STUDENT.getMood());
                mood.setVisibility(View.VISIBLE);
            }
        }




        Button btnSave = (Button)findViewById(R.id.btnSubmit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent toUpdate = new Intent();
                boolean setError = false;
                if(textName.getVisibility() == View.VISIBLE) {
                    if( textName.getText().toString().length() !=0) {
                        DISPLAY_STUDENT.setName(textName.getText().toString());
                        toUpdate.putExtra(DisplayActivity.NAME_KEY, DISPLAY_STUDENT);
                        setResult(RESULT_OK, toUpdate);
                    }else
                    {
                        textName.setError(getResources().getText(R.string.errFill2));
                        setError = true;
                    }

                }

                else if (textEmail.getVisibility() == View.VISIBLE)
                {
                    if( textEmail.getText().toString().length() !=0) {
                        if(MainActivity.isEmailValid(textEmail.getText().toString())) {

                            DISPLAY_STUDENT.setEmailAddress(textEmail.getText().toString());
                            toUpdate.putExtra(DisplayActivity.EMAIL_KEY, DISPLAY_STUDENT);
                            setResult(RESULT_OK, toUpdate);
                        }
                        else
                        {
                            textEmail.setError(getResources().getText(R.string.invalidEmail));
                            setError = true;
                        }
                    }
                    else
                    {
                        textEmail.setError(getResources().getText(R.string.errFill2));
                        setError = true;
                    }
                }


                else if (dept.getVisibility() == View.VISIBLE)
                {

                    DISPLAY_STUDENT.setDepartment(GetSelectedRadioText(radioGroupDept.getCheckedRadioButtonId()));

                    toUpdate.putExtra(DisplayActivity.DEPARTMENT_KEY,DISPLAY_STUDENT);
                    setResult(RESULT_OK,toUpdate);

                }


                else if (mood.getVisibility() == View.VISIBLE)
                {

                    DISPLAY_STUDENT.setMood(Double.parseDouble(String.valueOf(seekBarMood.getProgress())));

                    toUpdate.putExtra(DisplayActivity.MOOD_KEY,DISPLAY_STUDENT);
                    setResult(RESULT_OK,toUpdate);

                }
                if(!setError) {
                    finish();
                }


            }
        });

    }

    @Override
    public void onBackPressed() {
        // assign the default object
        Intent toUpdate = new Intent();
        if(textName.getVisibility() == View.VISIBLE) {
            toUpdate.putExtra(DisplayActivity.NAME_KEY, DISPLAY_STUDENT);
            setResult(RESULT_OK, toUpdate);
        }

        else if (textEmail.getVisibility() == View.VISIBLE)
        {
            toUpdate.putExtra(DisplayActivity.EMAIL_KEY,DISPLAY_STUDENT);
            setResult(RESULT_OK,toUpdate);
        }


        else if (dept.getVisibility() == View.VISIBLE)
        {
            toUpdate.putExtra(DisplayActivity.DEPARTMENT_KEY,DISPLAY_STUDENT);
            setResult(RESULT_OK,toUpdate);
        }


        else if (mood.getVisibility() == View.VISIBLE)
        {
            toUpdate.putExtra(DisplayActivity.MOOD_KEY,DISPLAY_STUDENT);
            setResult(RESULT_OK,toUpdate);

        }
        finish();
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

    private void SetSelectedRadio(String value)
    {



        switch (value)
        {
            case "SIS":
                radioGroupDept.check(R.id.radioButtonSIS);
                break;
            case "CS":
                radioGroupDept.check(R.id.radioButtonCS);
                break;
            case "BIO":
                radioGroupDept.check(R.id.radioButtonBIO);
                break;
            case "Others":
                radioGroupDept.check(R.id.radioButtonOthers);
                break;
            default:
                break;

        }
    }
}
