package echo;

import java.util.HashMap;

public class SafeTable extends HashMap<String,String> {
    public SafeTable(){
        super();
    }
    public synchronized String get(String key){
        return super.get(key);
    }
    public synchronized String put(String key, String value){
        return super.put(key, value);
    }

}
