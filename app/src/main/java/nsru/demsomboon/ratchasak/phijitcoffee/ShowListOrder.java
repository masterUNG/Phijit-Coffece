package nsru.demsomboon.ratchasak.phijitcoffee;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class ShowListOrder extends AppCompatActivity {

    //Explicit
    private String nameLoginString;
    private static final String urlJSON = "http://swiftcodingthai.com/aon/get_order_where_name.php";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_order);

        //Bind Widget
        listView = (ListView) findViewById(R.id.listview3);

        //Get Value from Intent
        nameLoginString = getIntent().getStringExtra("Name");
        Log.d("19octV1", "nameLoginString ==> " + nameLoginString);

        //Create ListView
        SynOrderTABLE synOrderTABLE = new SynOrderTABLE(this);
        synOrderTABLE.execute(urlJSON);


    }   // Main Method

    private class SynOrderTABLE extends AsyncTask<String, Void, String> {

        //Explicit
        private Context context;
        private String[] dateStrings, coffeeStrings, amountStrings, statusStrings;

        public SynOrderTABLE(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("NameAnSur", nameLoginString)
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strings[0]).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("19octV1", "e doInBack ==> " + e.toString());
                return null;
            }


        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("19octV1", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                dateStrings = new String[jsonArray.length()];
                coffeeStrings = new String[jsonArray.length()];
                amountStrings = new String[jsonArray.length()];
                statusStrings = new String[jsonArray.length()];

                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    dateStrings[i] = jsonObject.getString("Date");
                    coffeeStrings[i] = jsonObject.getString("Coffee");
                    amountStrings[i] = jsonObject.getString("Amount");

                    String strStatus = jsonObject.getString("status");
                    if (strStatus.length() == 1) {
                        //Have Log
                        statusStrings[i] = getResources().getString(R.string.finish);
                    } else {
                        statusStrings[i] = getResources().getString(R.string.non_finsh);
                    }

                }   // for

                ShowListOrderAdapter showListOrderAdapter = new ShowListOrderAdapter(context,
                        dateStrings, coffeeStrings, amountStrings, statusStrings);
                listView.setAdapter(showListOrderAdapter);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }   // onPost

    }   // SynOrderTABLE

    public void clickBackShowListOrder(View view) {
        finish();
    }

}   // Main Class
