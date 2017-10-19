package me.vepu.restclient1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    public void sendMessage(View view){
        final String uri = "http://192.168.1.102:8080/api/groceries/paine";
        RESTTask restTask = new RESTTask();
        restTask.execute(uri);
        try{
            ResponseEntity<Item> responseEntity = restTask.get(123, TimeUnit.SECONDS);
            final TextView afield  = (TextView) findViewById(R.id.afield);
            afield.setText(responseEntity.getBody().getName());
        }
        catch (Exception e){
            String message = e.getMessage();
        }
    }

    class RESTTask extends AsyncTask<String,Void,ResponseEntity<Item>>{

        protected ResponseEntity<Item> doInBackground(String... uri){
            String url = uri[0];
            RestTemplate restTemplate = new RestTemplate();
            try{
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("APIKey","12345");

                HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
                ResponseEntity<Item> responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity,Item.class);

                return responseEntity;

            }catch (Exception e){
                String message = e.getMessage();
                return null;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        final TextView afield  = (TextView) findViewById(R.id.afield);

        afield.setText(""+0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
