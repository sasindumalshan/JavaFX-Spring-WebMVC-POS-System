package lk.interleon.pos.util;

import lk.interleon.pos.dto.Headers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * @author Sasindu Malshan
 * @project FL
 * @date 3/3/2024
 */

public class Connection {
    public void getRequestObject(String urlPath, String method, String jsonRequestBody, List<Headers> headers) {
        try {
            // URL to which you want to send the request
            URL url = new URL(urlPath);

            // Create HttpURLConnection object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod(method);

            // Set headers
            if (headers != null) {
                for (Headers header : headers) {
                    connection.setRequestProperty(header.getKey(),header.getValue());
                }
            }

            // Enable output and disable caching
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            // Write JSON data to output stream
            if (jsonRequestBody != null) {
                try (OutputStream outputStream = connection.getOutputStream()) {
                    byte[] input = jsonRequestBody.getBytes(StandardCharsets.UTF_8);
                    outputStream.write(input, 0, input.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            StringBuilder informationString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
            //Close the scanner
            scanner.close();

            System.out.println(informationString);

            //JSON simple library Setup with Maven is used to convert strings to JSON
            JSONParser parse = new JSONParser();
            JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));
//          JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

            //Get the first JSON object in the JSON array
            System.out.println(dataObject.toString());

//          JSONObject countryData = (JSONObject) dataObject.get(0);
//
//          System.out.println(countryData.get("data"));

            // Close connection
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
