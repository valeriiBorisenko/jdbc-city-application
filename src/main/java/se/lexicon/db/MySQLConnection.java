package se.lexicon.db;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {

    static Properties properties = new Properties();

    private static String url;
    private static String password;
    private static String username;


    static {
        try (
                Reader in = Files.newBufferedReader(Path.of("src/main/resources/database.properties"), StandardCharsets.UTF_8)
        ) {
            properties.load(in);
            System.out.println("File Read...");

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Reading properties...");
        url = properties.getProperty("jdbc.url");
        username = properties.getProperty("jdbc.username");
        password = properties.getProperty("jdbc.password");
    }


    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, username, password);

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
