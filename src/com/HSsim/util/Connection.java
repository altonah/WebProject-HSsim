package com.HSsim.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

    public static String getStringJSONFromRequest(String url) {
        String ret = "";
        BufferedReader reader = null;
        HttpURLConnection urlConnection;

        try {
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
            String line;
            StringBuilder sb = new StringBuilder();
            int responseCode = urlConnection.getResponseCode();
            // Read the correct stream based on the response code.
            if((responseCode == 404) || (responseCode == 500)) {
                reader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            ret = sb.toString();
        } catch (IOException e1) {
            // Some sort of connection error, let's just return some sort of error
            ret = "{\"status\":\"nok\", \"reason\":\"URL Connection Error\"}";
        }
        finally {
            if (reader != null) try {
                reader.close();
            } catch (IOException ignored) {
            }

        }
        return ret;
    }
    
}
