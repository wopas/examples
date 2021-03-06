package com.bandwidth.enviroment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

/**
 * Enviromental properties are stored in this class.  they are read from a file set through the configuration method.
 */
public class Properties {


    private static final Map<String, String> PROPS = new LinkedHashMap<>();

    private Properties(){

    }

    /**
     * Takes a file path then if the file exists it reads and parses it.
     * <br/>
     * File format should be:
     * <br/>
     * property.name=property value
     * @param filePath
     */
    public static void configure(String filePath){

        File file = new File(filePath);

        if(!file.exists())
            return;


        List<String> logList = null;
        try {
            logList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            out.println(e.getMessage());
        }


        for(String line : logList){
            int index = line.indexOf('=');
            if(index == -1 || index >= line.length()) continue;

            String left = line.substring(0, index);
            String right = line.substring(index + 1, line.length() );

            PROPS.put(left, right);
        }
    }

    /**
     * Returns the poperty value for the key.  Null if it does not exist.
     * @param key
     * @return
     */
    public static String getProperty(String key){

        if(PROPS.containsKey(key)){
            return PROPS.get(key);
        } else {
            return null;
        }


    }
}
