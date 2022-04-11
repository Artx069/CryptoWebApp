package com.example.cryptowebapp.dataAccess;

import com.example.cryptowebapp.business.CryptoCurrencyList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class GetJsonData {
    //private static String API_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=" + CryptoCurrencyList.getCommaSeparatedList() +"&order=market_cap_desc&per_page=100&page=1&sparkline=false";

    public static JsonArray getJsonArray()
    {
        tryToConnectToAPI();
        try
        {
            String API_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=" + CryptoCurrencyList.getCommaSeparatedList() +"&order=market_cap_desc&per_page=100&page=1&sparkline=false";
            // Connect to the URL using java's native library
            URL url = new URL(API_URL);
            URLConnection request = url.openConnection();
            request.connect();

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
            JsonArray rootobj = root.getAsJsonArray(); //May be an array, may be an object.
            return rootobj;

        }
        catch(Exception e)
        {
            System.out.println("Something went wrong - check URL");
            return null;
        }
    }

    private static void tryToConnectToAPI()
    {
        try
        {
            while (checkAPIAvailability() != true)
            {
                System.out.println("Server not Available right now!");
                Thread.sleep(10000);
            }
            System.out.println("Connection Successful");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private static boolean checkAPIAvailability()
    {

        try
        {
            URL url = new URL("https://api.coingecko.com/api/v3/ping");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            if (code == 200)
            {
                return true;
            } else
            {
                System.out.println("Responsecode: " + code);
                return false;
            }

        }
        catch(Exception e)
        {
            System.out.println("API not Available");
            return false;
        }
    }


}
