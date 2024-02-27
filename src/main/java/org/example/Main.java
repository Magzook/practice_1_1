package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.sql.rowset.serial.SQLInputImpl;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon");
            URLConnection connection = url.openConnection();
            connection.connect();
            BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream(),
                            Charset.forName("UTF-8")
                    )
            );
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                sb.append(line);
            }
            line = sb.toString();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(line);
            System.out.println(node.get("count").asText());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}