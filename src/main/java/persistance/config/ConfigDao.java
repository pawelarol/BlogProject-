package persistance.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigDao {
    public static final String url = "db.url";
    public static final String userName = "db.userName";
    public static final String password = "db.password";

    public static final Properties properties = new Properties();

    public static String getProperties(String name){
         if(properties.isEmpty()){
             try(InputStream is = ConfigDao.class.getClassLoader().
                     getResourceAsStream("daoProperties")){
                 properties.load(is);
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }
         }

         return properties.getProperty(name);
    }
}
