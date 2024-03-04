package lk.interleon.pos.model;

import lk.interleon.pos.dto.CategoryDTO;
import lk.interleon.pos.dto.SupplerDTO;
import lk.interleon.pos.dto.UnitDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

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
 * @date 3/4/2024
 */

public class UnitModel {
    //unit
    public static UnitDTO findById(String id) {
        try {

            URL url = new URL("http://localhost:8080/Interleaon_war/unit?id="+id);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));

                JSONObject dataObject2 = (JSONObject) dataObject.get("data");

                ModelMapper modelMapper = new ModelMapper();
                UnitDTO categoryDTO = modelMapper.map(dataObject2, UnitDTO.class);
                return categoryDTO;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean add(UnitDTO unitDTO) {
        HttpURLConnection connection = null;
        try {
            // URL to which you want to send the request
            URL url = new URL("http://localhost:8080/Interleaon_war/unit");

            // Create HttpURLConnection object
            connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("POST");

            // Set headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Enable output and disable caching
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            // Create JSON request body
            String jsonRequestBody = "{\"id\": \"" + unitDTO.getId() + "\", \"code\": \"" + unitDTO.getCode() + "\", \"name\": \"" + unitDTO.getName() + "\", \"status\": \"" + unitDTO.getStatus() + "\", \"supplierID\": \"" + unitDTO.getSupplierID() + "\", \"categoryID\": \"" + unitDTO.getCategoryID() + "\"}";
            System.out.println(jsonRequestBody);

            // Write JSON data to output stream
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonRequestBody.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(connection.getResponseCode());
            return connection.getResponseCode() == 200;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return false;
    }

    public static boolean update(UnitDTO unitDTO) {
        HttpURLConnection connection = null;
        try {
            // URL to which you want to send the request
            URL url = new URL("http://localhost:8080/Interleaon_war/unit/update");

            // Create HttpURLConnection object
            connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("POST");

            // Set headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Enable output and disable caching
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            // Create JSON request body
            String jsonRequestBody = "{\"id\": \"" + unitDTO.getId() + "\", \"code\": \"" + unitDTO.getCode() + "\", \"name\": \"" + unitDTO.getName() + "\", \"status\": \"" + unitDTO.getStatus() + "\", \"supplierID\": \"" + unitDTO.getSupplierID() + "\", \"categoryID\": \"" + unitDTO.getCategoryID() + "\"}";

            // Write JSON data to output stream
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonRequestBody.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection.getResponseCode() == 200;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return false;
    }

    public static List<UnitDTO> findAll() {
        try {

            URL url = new URL("http://localhost:8080/Interleaon_war/unit/findAll");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));

                JSONArray dataObject2 = (JSONArray) dataObject.get("data");

                ModelMapper modelMapper = new ModelMapper();
                List<UnitDTO> list = modelMapper.map(dataObject2, new TypeToken<List<UnitDTO>>() {
                }.getType());
                return list;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean remove(String id) {
        try {
            URL url = new URL("http://localhost:8080/Interleaon_war/unit?id="+id+"");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<UnitDTO> findUnitByText(String text) {
        try {

            URL url = new URL("http://localhost:8080/Interleaon_war/unit/search?id="+text);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));

                JSONArray dataObject2 = (JSONArray) dataObject.get("data");

                ModelMapper modelMapper = new ModelMapper();
                List<UnitDTO> list = modelMapper.map(dataObject2, new TypeToken<List<UnitDTO>>() {
                }.getType());
                return list;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCount() {
        try {

            URL url = new URL("http://localhost:8080/Interleaon_war/unit/countByAll");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));

                String count= (String) dataObject.get("data");
                System.out.println(count);

                return count;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
