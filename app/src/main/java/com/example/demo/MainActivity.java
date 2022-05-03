package com.example.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.R.layout.listview;


public class MainActivity extends AppCompatActivity {
    Button btn_add,search,viewAll,delete,call;
EditText ed_name,ed_Phone,searchBar;

ListView listView;


    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add=findViewById(R.id.btn_add);
        search=findViewById(R.id.search);
        searchBar=findViewById(R.id.searchBar);
        listView=findViewById(R.id.listview1);
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
        delete=findViewById(R.id.delete);
        call=findViewById(R.id.call);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
}


    private void ShowCustomersOnListView(dataBasehelper dataBasehelper) {
        ListAdapter CustomerArrayAdapter = new ListAdapter<customerModel>(MainActivity.this, listview, dataBasehelper.getEveryone());
        listView.setAdapter(CustomerArrayAdapter);
    }
        private void searchList(String Name, List<customerModel> returnedSearchList){
         List<customerModel> finallist=new ArrayList<>();
                for (int i=0;i<returnedSearchList.size();i++){
                    customerModel c= returnedSearchList.get(i);
                        if ((String.valueOf(c.name)).contains(Name)) {
                            finallist.add(c);
                        }
                } ListAdapter CustomerArrayAdapter = new ListAdapter<customerModel>(MainActivity.this, listview, finallist);
            listView.setAdapter(CustomerArrayAdapter);
    }

}
