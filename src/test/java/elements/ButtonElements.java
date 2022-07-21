package elements;

import java.util.HashMap;
import java.util.Map;

public class ButtonElements {

    private final Map<String,String> listElements = new HashMap<String,String>();

    public String list(String nameElement){
        listElements.put("signUpButton","//*[@id='signin2']");
        listElements.put("signUpAcceptButton","//*[@id='signInModal']/div/div/div[3]/button[2]");
        listElements.put("logInButton","//*[@id='login2']");
        listElements.put("loginAcceptButton","//*[@id='logInModal']/div/div/div[3]/button[2]");
        listElements.put("addToCartButton","//*[@id='tbodyid']/div[2]/div/a");
        listElements.put("cartButton","//*[@id='navbarExample']/ul/li[4]/a");
        listElements.put("laptopSonyVaioi5Button","//*[@id='tbodyid']/div[8]/div/a");

        return listElements.get(nameElement);
    }

    public String getElement (String element){
        String elementToLookFor = manipulateElement(element);
        return list(elementToLookFor);
    }

    public String manipulateElement (String elementToManipulate){
        String element = elementToManipulate.substring(0,1).toLowerCase()+elementToManipulate.substring(1);
        return element.replaceAll("\\s","")+"Button";
    }
}
