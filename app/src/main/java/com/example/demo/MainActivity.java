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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button btn_add,search,viewAll;
EditText ed_name,ed_Phone,searchBar;

ListView listView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add=findViewById(R.id.btn_add);
        search=findViewById(R.id.search);
        searchBar=findViewById(R.id.searchBar);
        listView=findViewById(R.id.listview);
        ed_name=findViewById(R.id.ed_name);
        ed_Phone=findViewById(R.id.ed_phone);
        viewAll=findViewById(R.id.viewAll);
        dataBasehelper dataBasehelper=new dataBasehelper(MainActivity.this);
        ShowCustomersOnListView(dataBasehelper);

        //ONCLICK LISTENERS FOR BUTTONS
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerModel customerModel;
                customerModel=new customerModel(1,ed_name.getText().toString(),ed_Phone.getText().toString());
               dataBasehelper.ADDONE(customerModel);
                ShowCustomersOnListView(dataBasehelper);
            }
        });

    search.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            List<customerModel> returnedSearchList=dataBasehelper.getEveryone();
            searchList(searchBar.getText().toString(),returnedSearchList);
        }
    });
    viewAll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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
        private void searchList(String Name, List<customerModel> returnedSearchList){
         List<customerModel> finallist=new ArrayList<>();
         List<customerModel> finallist2=new ArrayList<>();
                for (int i=0;i<returnedSearchList.size();i++){
                    customerModel c= returnedSearchList.get(i);
                        if (Name.equalsIgnoreCase(String.valueOf(c.name))) {
                            finallist.add(c);
                        }
                }
                    ArrayAdapter CustomerArrayAdapter = new ArrayAdapter<customerModel>(MainActivity.this,android.R.layout.simple_list_item_1,finallist);
                    listView.setAdapter(CustomerArrayAdapter);
    }

}
