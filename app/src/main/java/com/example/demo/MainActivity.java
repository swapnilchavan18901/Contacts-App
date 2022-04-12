package com.example.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
Button btn_add;
EditText ed_name,ed_Phone;

ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add=findViewById(R.id.btn_add);

        listView=findViewById(R.id.listview);
        ed_name=findViewById(R.id.ed_name);
        ed_Phone=findViewById(R.id.ed_phone);
        dataBasehelper dataBasehelper=new dataBasehelper(MainActivity.this);
        ShowCustomersOnListView(dataBasehelper);

        //ONCLICK LISTENERS FOR BUTTONS
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerModel customerModel;
                try {
                    customerModel=new customerModel(1,ed_name.getText().toString(),ed_Phone.getText().toString());

                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error Creating Customer", Toast.LENGTH_SHORT).show();
                    customerModel=new customerModel(-1,"ERROR","0");
                }
                boolean success=dataBasehelper.ADDONE(customerModel);
                if (success==true) {
                    Toast.makeText(MainActivity.this, "succeeded in creating your contact", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this, "Failed to create your contact", Toast.LENGTH_SHORT).show();
                }
                //showdatabase without refresh
                ShowCustomersOnListView(dataBasehelper);
            }
        });



    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            customerModel clickcustomner= (customerModel) parent.getItemAtPosition(position);
            dataBasehelper.deleteOne(clickcustomner);
            ShowCustomersOnListView(dataBasehelper);
            Toast.makeText(MainActivity.this, "Deleted"+clickcustomner, Toast.LENGTH_SHORT).show();
        }
    });
    }
    private void ShowCustomersOnListView(dataBasehelper dataBasehelper) {
        ArrayAdapter CustomerArrayAdapter = new ArrayAdapter<customerModel>(MainActivity.this,android.R.layout.simple_list_item_1, dataBasehelper.getEveryone());
        listView.setAdapter(CustomerArrayAdapter);
    }
}