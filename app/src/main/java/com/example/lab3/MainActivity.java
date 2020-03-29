package com.example.lab3;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab3.DatabaseHelper;
import com.example.lab3.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button replace, btnViewData;
private Button add;
private Button change;
private Button save;
private EditText ediname;
private EditText edittime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replace = (Button) findViewById(R.id.btreplace);
        change=(Button)findViewById(R.id.btnDel);
        add=(Button)findViewById(R.id.btnadd);
        btnViewData = (Button) findViewById(R.id.btnView);
        save=(Button)findViewById(R.id.btnsave);
        ediname=(EditText)findViewById(R.id.addname);
        edittime=(EditText)findViewById(R.id.addtime);
        mDatabaseHelper = new DatabaseHelper(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ediname.setVisibility(View.VISIBLE);
                edittime.setVisibility(View.VISIBLE);
                save.setVisibility(View.VISIBLE);
            }
        });
        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if((edittime.getText().length()==0)||(ediname.getText().length()==0))
                {
                    toastMessage("cannot insert empty text");
                }else
                {
                    String name=ediname.getText().toString();
                    String time=edittime.getText().toString();
                    ediname.setText("");
                    edittime.setText(" ");
                    AddData(name,time);
                    ediname.setVisibility(View.GONE);
                    edittime.setVisibility(View.GONE);
                    save.setVisibility(View.GONE);
                }

            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change.setVisibility(View.GONE);
                DeleteData();
            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

    mDatabaseHelper.replace("Иван Иванович Иванов");

            }
        });
    }
    public void AddData(String newEntry, String newtime) {
        boolean insertData = mDatabaseHelper.addData(newEntry,newtime);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }
public void DeleteData()
{
      Random random=new Random();
     String[] Names={"Семен Семенович Семенов","Илья Ильич Ильичов","Андрей Андреев Андреевич","Федор Федоров Федорович","Максим Максимович Максимов","Матвей Матвеевич Матвеев","Данила Данилович Данилов","Григорий Григорьев Григорьевич","Давид Давидович Давидов"};
    String[]time={"12:14","13:21","10:10","15:15","23:56","14:32","12:23","14:21"};
     int i=random.nextInt(8);
   mDatabaseHelper.DeleteandAdd(0,Names[i],time[i]);
    i=random.nextInt(8);
    mDatabaseHelper.DeleteandAdd(1,Names[i],time[i]);
    i=random.nextInt(8);
    mDatabaseHelper.DeleteandAdd(1,Names[i],time[i]);
    i=random.nextInt(8);
    mDatabaseHelper.DeleteandAdd(1,Names[i],time[i]);
    i=random.nextInt(8);
    mDatabaseHelper.DeleteandAdd(1,Names[i],time[i]);
}
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
