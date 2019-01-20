package commonUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    String propFile = "config.properties";
    Properties properties;

    public void readPropertyFile() throws IOException {

        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFile);
        if(inputStream!=null){
            properties.load(inputStream);
        }else{
            throw new FileNotFoundException(propFile + " not found");
        }

    }

    public String getPropertyValue(String key){
        try {
            readPropertyFile();
        }catch (IOException e){
            e.getMessage();
            e.printStackTrace();
        }

        String propValue =  properties.getProperty(key);
        return propValue;
    }
}
