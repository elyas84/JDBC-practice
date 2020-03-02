package JsonToJavaCollection;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class POSTING {
    /*
    {
  "gender": "Male",
  "name": "Honda",
  "phone": 7894256123
}
     */

    @Test
    public void postingNewSpar(){
        Map<String, Object> addingSpar = new HashMap<>();
        addingSpar.put("name","Honda");
        addingSpar.put("gender","Male");
        addingSpar.put("phone",7894256123L);

    }

}
