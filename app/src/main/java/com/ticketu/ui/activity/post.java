package com.ticketu.ui.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.ticketu.R;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class post extends AppCompatActivity{
    private TextView moviedate;
    private EditText ticketrow,nooftickets,price,additionalmessage,screenname;
    private AutoCompleteTextView theatreName;
    private Spinner city,geolocation,pickuptype;
    private Calendar cal;
    private int day, month, year;
    private String[] mname = new String[]{"Naanum Rowdy tha","Naanum rrrrr","Thanni oruvan","thani ooooo"};
    private int[] mimage= new int[]{R.drawable.naanumrowdytha,R.drawable.naanumrowdytha,R.drawable.thanioruvan,R.drawable.thanioruvan};

    private int minute, hour;
    private SimpleDateFormat mFormatter = new SimpleDateFormat("MMMM dd yyyy hh:mm aa");
    private SlideDateTimeListener listener;
    private Button post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("ABCD");
        arrayList.add("ABED");
        arrayList.add("CDED");
        arrayList.add("XZCR");
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<4;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", mname[i]);
            hm.put("flag", Integer.toString(mimage[i]));
            aList.add(hm);

        }
        Spinner city = (Spinner) findViewById(R.id.city);
        Spinner geolocation = (Spinner) findViewById(R.id.geolocation);
        Spinner pickuptype = (Spinner)findViewById(R.id.pickuptype);
        city.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList));
        geolocation.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList));
        theatreName = (CustomAutoCompleteTextView)findViewById(R.id.theatrename);
        screenname = (EditText)findViewById(R.id.screenname);
        ticketrow = (EditText)findViewById(R.id.ticketrow);
        nooftickets = (EditText)findViewById(R.id.nooftickets);
        price = (EditText)findViewById(R.id.price);
        additionalmessage = (EditText)findViewById(R.id.additionalmessage);
        moviedate  = (TextView)findViewById(R.id.moviedate);
        post = (Button)findViewById(R.id.post);
        String[] from = { "flag","txt"};

        // Ids of views in listview_layout
        int[] to = { R.id.movienameauto,R.id.movieimageauto};
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) {
                    System.out.println("hhh success");
                }
            }
        });
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.autocomplete_layout, from, to);

        // Getting a reference to CustomAutoCompleteTextView of activity_main.xml layout file

        /** Defining an itemclick event listener for the autocompletetextview */
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {

                /** Each item in the adapter is a HashMap object.
                 *  So this statement creates the currently clicked hashmap object
                 * */
                HashMap<String, String> hm = (HashMap<String, String>) arg0.getAdapter().getItem(position);

                /** Getting a reference to the TextView of the layout file activity_main to set Currency */
                TextView tvCurrency = (TextView) findViewById(R.id.movienameauto) ;
                ImageView imageView = (ImageView)findViewById(R.id.movieimageauto);
                /** Getting currency from the HashMap and setting it to the textview */
                tvCurrency.setText("Currency : " + hm.get("cur"));
            }
        };

        /** Setting the itemclick event listener */
        theatreName.setOnItemClickListener(itemClickListener);

        /** Setting the adapter to the listView */
        theatreName.setAdapter(adapter);
          listener = new SlideDateTimeListener() {

            @Override
            public void onDateTimeSet(Date date)
            {

                moviedate.setText(mFormatter.format(date));
            }

            // Optional cancel listener
            @Override
            public void onDateTimeCancel()
            {

            }
          };
/*
        theatreName.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList));
*/
        moviedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(listener)
                        .setInitialDate(new Date())
                                //.setMinDate(minDate)
                                //.setMaxDate(maxDate)
                                //.setIs24HourTime(true)
                                //.setTheme(SlideDateTimePicker.HOLO_DARK)
                                //.setIndicatorColor(Color.parseColor("#990000"))
                        .build()
                        .show();
            }
        });
    }

    private boolean isValid() {
        boolean valid;
        if(theatreName.getText().toString().equals("") || nooftickets.getText().toString().equals("")
                || moviedate.getText().toString().equals("") || moviedate.getText().toString().equals("")
                || price.getText().toString().equals("") || screenname.getText().toString().equals("")) {
            valid = false;

        }
        else {
            valid = true;System.out.println("hhh theatrename == "+theatreName.getText().toString());
        }
        return valid;
    }
}
