package com.example.studentregistrationform;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayActivity extends AppCompatActivity implements View.OnClickListener{
 Student DISPLAY_STUDENT;
    final static String NAME_KEY = "NAME";
    final static int NAME_KEY_CODE = 100;

    final static String EMAIL_KEY = "EMAIL";
    final static int EMAIL_KEY_CODE = 101;

    final static String DEPARTMENT_KEY = "DEPARTMENT";
    final static int DEPARTMENT_KEY_CODE = 102;

    final static String MOOD_KEY = "MOOD";
    final static int MOOD_KEY_CODE = 103;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode)
        {
            case NAME_KEY_CODE:
                DISPLAY_STUDENT = data.getExtras().getParcelable(NAME_KEY);
                DisplayStudentInfo();
                break;
            case EMAIL_KEY_CODE:
                DISPLAY_STUDENT = data.getExtras().getParcelable(EMAIL_KEY);
                DisplayStudentInfo();
                break;
            case DEPARTMENT_KEY_CODE:
                DISPLAY_STUDENT = data.getExtras().getParcelable(DEPARTMENT_KEY);
                DisplayStudentInfo();
                break;
            case MOOD_KEY_CODE:
                DISPLAY_STUDENT = data.getExtras().getParcelable(MOOD_KEY);
                DisplayStudentInfo();
                break;
            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        if (getIntent() != null)
        {

            if(getIntent().getExtras() != null)
            {
                if(getIntent().getExtras().containsKey(MainActivity.STUDENT_KEY))
                {
                     DISPLAY_STUDENT = (Student)getIntent().getExtras().getParcelable(MainActivity.STUDENT_KEY);
                    Log.d("Demo",DISPLAY_STUDENT.toString());
                    DisplayStudentInfo();

                    findViewById(R.id.imageViewName).setOnClickListener(this);
                    findViewById(R.id.imageViewEmail).setOnClickListener(this);
                    findViewById(R.id.imageViewDepartment).setOnClickListener(this);
                    findViewById(R.id.imageViewMood).setOnClickListener(this);


                }
            }
        }
    }


    private void DisplayStudentInfo()
    {
        TextView viewName = (TextView)findViewById(R.id.textViewName);
        TextView viewEmail = (TextView)findViewById(R.id.textViewEmail);
        TextView viewDepartment = (TextView)findViewById(R.id.textViewDepartment);
        TextView viewMood= (TextView)findViewById(R.id.textViewMood);

        viewName.setText(DISPLAY_STUDENT.getName());
        viewEmail.setText(DISPLAY_STUDENT.getEmailAddress());
        viewDepartment.setText(DISPLAY_STUDENT.getDepartment());
        viewMood.setText(String.valueOf( (int) DISPLAY_STUDENT.getMood()) + " % Positive ");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.imageViewName:
                Intent forName = new Intent("com.example.studentregistrationform.intent.action.VIEW");
                forName.putExtra(NAME_KEY,DISPLAY_STUDENT);
                startActivityForResult(forName,NAME_KEY_CODE);

                break;
            case R.id.imageViewEmail:

                Intent forEmail = new Intent("com.example.studentregistrationform.intent.action.VIEW");
                forEmail.putExtra(EMAIL_KEY,DISPLAY_STUDENT);
                startActivityForResult(forEmail, EMAIL_KEY_CODE);
                break;
            case R.id.imageViewDepartment:

                Intent forDepartment = new Intent("com.example.studentregistrationform.intent.action.VIEW");
                forDepartment.putExtra(DEPARTMENT_KEY,DISPLAY_STUDENT);
                startActivityForResult(forDepartment, DEPARTMENT_KEY_CODE);
                break;
            case R.id.imageViewMood:

                Intent forMood = new Intent("com.example.studentregistrationform.intent.action.VIEW");
                forMood.putExtra(MOOD_KEY,DISPLAY_STUDENT);
                startActivityForResult(forMood, MOOD_KEY_CODE);
                break;
            default:
                break;

        }
    }
}
