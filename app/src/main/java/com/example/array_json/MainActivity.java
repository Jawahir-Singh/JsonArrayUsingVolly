package com.example.array_json;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     private TextView textView;
     private RecyclerView recyclerView;
     private CustomAdapter customAdapter;
     private ArrayList<Model> mdata;
     private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       recyclerView = findViewById(R.id.recyclerView);
       progressBar = findViewById(R.id.progressbar);
       mdata = new ArrayList<>();
       getData();
       buildRecyclerView();
    }
    private void getData(){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://fakestoreapi.com/products", null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                try {
                    for (int i = 0; i < response.length(); i++) {
                        // we are getting each json object.
                        JSONObject responseObj = response.getJSONObject(i);
                        Log.d("data", "onRespose: Title is" + responseObj.getString("title")+"and description is---------"+responseObj.getString("description"));

                        String textTitle = responseObj.getString("title");
                        String textPrice = responseObj.getString("price");
                        String textDescription = responseObj.getString("description");
                        String textCategory = responseObj.getString("category");
                        String imageUrl = responseObj.getString("image");
                        mdata.add(new Model(textTitle,textPrice,textDescription,textCategory,imageUrl));
                      //  textView.setText(name1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);

    }
    private void buildRecyclerView() {

        // initializing our adapter class.
        customAdapter = new CustomAdapter(MainActivity.this,mdata);

        // adding layout manager
        // to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        recyclerView.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        recyclerView.setAdapter(customAdapter);
    }
}