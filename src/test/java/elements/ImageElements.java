package elements;

import java.util.HashMap;
import java.util.Map;

public class ImageElements {
    private final Map<String,String> listElements = new HashMap<String,String>();

    public String list(String nameElement){
        listElements.put("imageHomePage","//*[@id='nava']/img");

        return listElements.get(nameElement);
    }

    public String getElement (String elementToLookFor){
        return list(elementToLookFor);
    }
}
