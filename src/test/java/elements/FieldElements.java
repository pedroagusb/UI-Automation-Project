package elements;

import java.util.HashMap;
import java.util.Map;

public class FieldElements {
    private final Map<String,String> listElements = new HashMap<String,String>();

    public String list(String nameElement){
        listElements.put("signUpUsernameField","//*[@id='sign-username']");
        listElements.put("signUpPasswordField","//*[@id='sign-password']");
        listElements.put("loginUsernameField","//*[@id='loginusername']");
        listElements.put("loginPasswordField","//*[@id='loginpassword']");

        return listElements.get(nameElement);
    }

    public String getElement (String element){
        String elementToLookFor = manipulateElement(element);
        return list(elementToLookFor);
    }

    public String manipulateElement (String elementToManipulate){
        String element = elementToManipulate.substring(0,1).toLowerCase()+elementToManipulate.substring(1);
        return element.replaceAll("\\s","")+"Field";
    }
}
