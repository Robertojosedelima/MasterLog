package br.com.MasterLog.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Parameters {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
          
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
           
        }
    }

    public static String getOwnerLog() {
        
        return properties.getProperty("OWNER_LOG");
    }

    public static String getTableLog() {
        
        return properties.getProperty("TABLE_LOG");
    }

}