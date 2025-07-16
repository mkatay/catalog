package catalog;

import java.util.List;
//A Validators osztály metódusainál azért kell a static kulcsszó, mert ezek segédfüggvények (utility methods),
// és nem kötődnek egy konkrét példányhoz (objektumhoz) az osztályból.
//Ha egy metódus static, akkor azt példányosítás (new Validators()) nélkül is lehet hívni, pl.:
//Validators.isBlank("valami");
public class Validators {
    public static boolean isBlank(String text){
        return text==null || text.isBlank();
    }
    public static boolean isEmpty(List<String> contributors){
        return contributors==null || contributors.isEmpty();
    }
}
