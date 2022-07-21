package elements;

import java.util.HashMap;
import java.util.Map;

public class DropboxElements {

    private final Map<String,String> listElements = new HashMap<String,String>();

    public String list(String nameElement){
        listElements.put("","");

        return listElements.get(nameElement);
    }

    public String getElement (String element){
        String elementToLookFor = manipulateElement(element);
        return list(elementToLookFor);
    }

    public String manipulateElement (String elementToManipulate){
        String element = elementToManipulate.substring(0,1).toLowerCase()+elementToManipulate.substring(1);
        return element.replaceAll("\\s","")+"Dropbox";
    }
}
