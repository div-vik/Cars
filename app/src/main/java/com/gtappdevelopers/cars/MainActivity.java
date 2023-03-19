package com.gtappdevelopers.cars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;



public class MainActivity extends AppCompatActivity {

    private RecyclerView socialMediaRecyclerView,offerRecyclerView;
    RecyclerView.LayoutManager socialMediaLAyoutManger = new LinearLayoutManager(this);
    RecyclerView.LayoutManager offerLAyoutManger=new LinearLayoutManager(this);
    RecyclerViewAdapter recyclerViewAdapter;
    offerRecyclerViewAdapter offerRecyclerviewadapter;
     int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        apiData();
   }

    public void apiData(){
        String url="https://mocki.io/v1/a53f9e9d-edf0-496c-a705-33040569a7da";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("feeds");
                    InfoData(jsonArray);
                    ServiceData(jsonArray);
                    offerData(jsonArray);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }

            private void offerData(JSONArray jsonArray) {

                try {


                    JSONObject jsonObjectServiceObject = jsonArray.getJSONObject(2);
                    JSONArray jsonArraySeviceArray = jsonObjectServiceObject.getJSONArray("banners");
                    p = jsonArraySeviceArray.length();
                    String[] arr_SocialMediaName = new String[jsonArraySeviceArray.length()];
                    Bitmap[] arr_offerImage = new Bitmap[jsonArraySeviceArray.length()];
                    for (int i = 0; i < jsonArraySeviceArray.length(); i++) {
                        JSONObject jsonService = jsonArraySeviceArray.getJSONObject(i);
                        String img = jsonService.getString("image");
                        String txt = jsonService.getString("name");

                        arr_SocialMediaName[i] = txt;
                        arr_offerImage[i]=drawable_from_url(img);


                    }

                    offerRecyclerView = findViewById(R.id.offersRecyclerView);
                    offerLAyoutManger= new GridLayoutManager(MainActivity.this,1);
                    offerRecyclerView.setLayoutManager(offerLAyoutManger);
                    offerRecyclerviewadapter=new offerRecyclerViewAdapter(MainActivity.this,arr_SocialMediaName,arr_offerImage);
                    offerRecyclerView.setAdapter(offerRecyclerviewadapter);
                    offerRecyclerView.hasFixedSize();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }

            private void InfoData(JSONArray jsonArray) {
                try {
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    TextView nameText=findViewById(R.id.carName);
                    TextView numText=findViewById(R.id.carNumber);
                    TextView typeText=findViewById(R.id.carType);
                    nameText.setText(jsonObject.getString("car_name"));
                    numText.setText(jsonObject.getString("car_reg_no"));
                    typeText.setText(jsonObject.getString("car_type")+"-"+jsonObject.getString("fuel_type"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            public void ServiceData(JSONArray jsonArray)
            {
                try {


                    JSONObject jsonObjectServiceObject = jsonArray.getJSONObject(1);
                    JSONArray jsonArraySeviceArray = jsonObjectServiceObject.getJSONArray("services");
                    p=jsonArraySeviceArray.length();
                    Bitmap [] arr_image=new Bitmap[jsonArraySeviceArray.length()];
                    String [] arr_SocialMediaName=new String[jsonArraySeviceArray.length()];
                    String [] arr_redirectURL=new String[jsonArraySeviceArray.length()];
                    for (int i = 0; i < jsonArraySeviceArray.length(); i++) {
                        JSONObject jsonService = jsonArraySeviceArray.getJSONObject(i);
                        String img = jsonService.getString("image_url");
                        String redirectUrl = jsonService.getString("redirect_url");
                        String txt = jsonService.getString("name");
                        Log.e("REsponse................", "ServiceData: "+txt );
                        arr_SocialMediaName[i]=txt;
                        arr_redirectURL[i]=redirectUrl;
                        arr_image[i]=drawable_from_url(img);
                    }
                    socialMediaRecyclerView=findViewById(R.id.socialmediaRecycleView);
                    socialMediaLAyoutManger =new GridLayoutManager(MainActivity.this,3);
                    socialMediaRecyclerView.setLayoutManager(socialMediaLAyoutManger);
                    recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,arr_SocialMediaName,arr_image);
                    socialMediaRecyclerView.setAdapter(recyclerViewAdapter);
                    socialMediaRecyclerView.hasFixedSize();
                }
                catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }


            public Bitmap drawable_from_url(String url){
                try {
                    InputStream in = new java.net.URL(url).openStream();
                    Bitmap bmp = BitmapFactory.decodeStream(in);
                    
                return bmp;
            }
                catch (Exception e) {
                          e.printStackTrace();
            }
        return null;
        }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}