package zy.blue7.jsontoobj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author blue7
 * @date 2020/8/4 11:42
 **/
public class MyProperties {

    public static Properties  properties;

    public static Properties getNewInstance(String filePath) {
        Properties properties=new Properties();

        try {
            properties.load(new FileInputStream(new File(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }

}
