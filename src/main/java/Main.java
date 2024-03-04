import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Sasindu Malshan
 * @project FL
 * @date 3/3/2024
 */

public class Main {
    public static void main(String[] args) {

        /*try {

            URL url = new URL("http://localhost:8080/Interleaon_war/unit");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("id", "sasindu");
            conn.setRequestProperty("supplier_code", "sasindu");
            conn.setRequestProperty("name", "sasindu");
            conn.setRequestProperty("address", "sasindu");
            conn.setRequestProperty("status", "sasindu");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

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
                Object parse1 = parse.parse(String.valueOf(informationString));
//                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

                //Get the first JSON object in the JSON array
                System.out.println();

//                JSONObject countryData = (JSONObject) dataObject.get(0);
//
//                System.out.println(countryData.get("data"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        String jsonInputString = "{\"id\": \"value1\", \"supplier_code\": \"value2\", \"name\": \"value2\", \"address\": \"value2\", \"status\": \"value2\"}";

        try {
            // URL to which you want to send the request
            URL url = new URL("http://localhost:8080/Interleaon_war/unit");

            // Create HttpURLConnection object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("POST");

            // Set headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Enable output and disable caching
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            // Create JSON request body
            String jsonRequestBody = "{\"id\": \"value1\", \"supplier_code\": \"value2\", \"name\": \"value2\", \"address\": \"value2\", \"status\": \"value2\"}";

            // Write JSON data to output stream
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonRequestBody.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            StringBuilder informationString = new StringBuilder();
            Scanner scanner = new Scanner(connection.getInputStream());

            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
            //Close the scanner
            scanner.close();

            //JSON simple library Setup with Maven is used to convert strings to JSON
            JSONParser parse = new JSONParser();
            JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));

            // Get the data object from the response
            JSONObject dataObject2 = (JSONObject) dataObject.get("data");

            // Get the name from the data object
            String name = (String) dataObject2.get("name");
            System.out.println("Name: " + name);

            // Close connection
            connection.disconnect();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}

