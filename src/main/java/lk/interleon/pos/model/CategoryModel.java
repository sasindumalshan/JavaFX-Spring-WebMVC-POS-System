package lk.interleon.pos.model;

import lk.interleon.pos.dto.CategoryDTO;
import lk.interleon.pos.dto.SupplerDTO;
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

public class CategoryModel {

    public static boolean remove(String id) {
        try {

            URL url = new URL("http://localhost:8080/Interleaon_war/category?id="+id+"");

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

    public static List<CategoryDTO> findAll() {
        try {

            URL url = new URL("http://localhost:8080/Interleaon_war/category/findAll");

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
                List<CategoryDTO> list = modelMapper.map(dataObject2, new TypeToken<List<CategoryDTO>>() {
                }.getType());
                return list;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean add(CategoryDTO categoryDTO) {
        HttpURLConnection connection = null;
        try {
            // URL to which you want to send the request
            URL url = new URL("http://localhost:8080/Interleaon_war/category");

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
            String jsonRequestBody = "{\"id\": \"" + categoryDTO.getId() + "\", \"code\": \"" + categoryDTO.getCode() + "\", \"name\": \"" + categoryDTO.getName() + "\", \"status\": \"" + categoryDTO.getStatus() + "\"}";

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

    public static boolean update(CategoryDTO categoryDTO) {
        HttpURLConnection connection = null;
        try {
            // URL to which you want to send the request
            URL url = new URL("http://localhost:8080/Interleaon_war/category/update");

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
            String jsonRequestBody = "{\"id\": \"" + categoryDTO.getId() + "\", \"code\": \"" + categoryDTO.getCode() + "\", \"name\": \"" + categoryDTO.getName() + "\", \"status\": \"" + categoryDTO.getStatus() + "\"}";

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

    public static CategoryDTO findById(String id) {
        try {

            URL url = new URL("http://localhost:8080/Interleaon_war/category?id="+id+"");

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
                CategoryDTO categoryDTO = modelMapper.map(dataObject2, CategoryDTO.class);
                return categoryDTO;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<CategoryDTO> findCategoryByText(String text) {
        try {

            URL url = new URL("http://localhost:8080/Interleaon_war/category/search?id="+text);

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
                List<CategoryDTO> list = modelMapper.map(dataObject2, new TypeToken<List<CategoryDTO>>() {
                }.getType());
                return list;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCategoryCount() {
        try {

            URL url = new URL("http://localhost:8080/Interleaon_war/category/countByAll");

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
