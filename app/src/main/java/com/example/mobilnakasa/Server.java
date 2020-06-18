package com.example.mobilnakasa;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.io.OutputStreamWriter;



public class Server extends IntentService {
    private String BASEURL = "http://192.168.1.104:3000/";
    public static final String ACTION = "ACTION";
    public static final String ACTION_USER_LIST = "user/all";
    public static final String ACTION_USER_LOGIN = "user/login";
    public static final String ACTION_USER_LOGOUT = "user/logout";
    public static final String ACTION_USER_REGISTER = "user/register";
    public static final String ACTION_PRODUCT_ADD = "product/add";
    public static final String ACTION_PRODUCT_EDIT = "product/edit";
    public static final String ACTION_PRODUCT_GET_ONE = "product/find";
    public static final String ACTION_PRODUCT_GET_ALL = "product/all";
    public static final String ACTION_RECIEPT_ADD = "receipt/add";
    public static final String ACTION_RECIEPT_GET = "receipt/getReciept";
    public static final String RETURN = "Return";
    public static final String RESPONSE = "Response";
    public static final String PARAMS = "Params";
    public static final String PARAMS_RECIEPT = "Reciept";
    public static final String PRODUCT_CODE = "product";
    public static final String RECIEPT_CODE = "reciept";
    public static final int RESPONSE_SUCCESS = 200;
    public static final int RESPONSE_ERROR = 203;

    public Server() {
        super("HTTP calls handler");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            String urlstr = intent.getStringExtra(Server.ACTION);

            if(urlstr.contains(Server.ACTION_PRODUCT_GET_ONE)){
                String code = intent.getStringExtra(Server.PRODUCT_CODE);
                urlstr+="/"+code;
            }

            if(urlstr.contains(Server.ACTION_RECIEPT_GET)){
                String code = intent.getStringExtra(Server.RECIEPT_CODE);
                urlstr+="/"+code;
            }

            URL server = new URL(BASEURL+urlstr);
            HttpURLConnection myConnection = (HttpURLConnection) server.openConnection();

            switch (urlstr){
                case Server.ACTION_RECIEPT_ADD:
                    myConnection.setRequestProperty("Content-Type", "application/json");
                    myConnection.setRequestProperty("charset", "utf-8");
                case Server.ACTION_USER_LOGIN:
                case Server.ACTION_PRODUCT_ADD:
                case Server.ACTION_USER_REGISTER:
                    myConnection.setRequestMethod("POST");
                    break;
                case Server.ACTION_PRODUCT_EDIT:
                    myConnection.setRequestMethod("PATCH");
                    break;
            }

            String params = intent.getStringExtra(Server.PARAMS);
            if(params!=null) {
                myConnection.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(myConnection.getOutputStream());
                writer.write(params);
                writer.flush();
                writer.close();
            }

            String paramsReceipt = intent.getStringExtra(Server.PARAMS_RECIEPT);
            if(paramsReceipt!=null){
                myConnection.setDoOutput(true);
                JSONArray jsonArray = new JSONArray(paramsReceipt);
                JSONObject cred = new JSONObject();
                cred.put("produkty", jsonArray);
                DataOutputStream localDataOutputStream = new DataOutputStream(myConnection.getOutputStream());
                localDataOutputStream.writeBytes(cred.toString());
                localDataOutputStream.flush();
                localDataOutputStream.close();
            }

            myConnection.connect();
            String response = "";
            int responseCode = myConnection.getResponseCode();
            Log.i("ABCD", String.valueOf(responseCode));
            if (responseCode == RESPONSE_SUCCESS) {
                InputStreamReader responseBody = new InputStreamReader(myConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(responseBody);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
            }else if(responseCode == RESPONSE_ERROR){
                InputStreamReader responseBody = new InputStreamReader(myConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(responseBody);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
            }
            myConnection.disconnect();
            Intent returns = new Intent();
            returns.putExtra(Server.RESPONSE, response);
            PendingIntent reply = (PendingIntent) intent.getParcelableExtra(Server.RETURN);
            reply.send(this,responseCode,returns);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }  catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
}
