package elements;

import java.util.HashMap;
import java.util.Map;

public class TextElements {

    private final Map<String,String> listElements = new HashMap<String,String>();

    public String list(String nameElement){
        //listElements.put("WelcomeText","//*[@id='nameofuser']");
        listElements.put("Sonyvaioi5Text","//*[@id='tbodyid']/tr/td[2]");

        return listElements.get(nameElement);
    }

    public String getElement (String element){
        String elementToLookFor = manipulateElement(element);
        return list(elementToLookFor);
    }

    public String manipulateElement (String elementToManipulate){
        return elementToManipulate.replaceAll("\\s","")+"Text";
    }

}
