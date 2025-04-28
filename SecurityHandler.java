package echo;

import java.util.HashMap;
import java.util.Map;

public class SecurityHandler extends ProxyHandler{

    private static Map<String,String> users = new HashMap<>();
    private boolean signedIn = false;

    public SecurityHandler(){
        super();
    }
    @Override
    protected String response(String msg) throws Exception {
        String[] operation = msg.trim().split("\\s+");

        if(!signedIn){
            if(operation.length == 3 && operation[0].equals("new")){
                String username = operation[1];
                String password = operation[2];
                if(users.containsKey(username)){
                    return "User already exists";
                }
                users.put(username, password);
                shutDown();
                return "User created. Terminating session.";
            }
            else if(operation.length == 3 && operation[0].equals("login")){
                String username = operation[1];
                String password = operation[2];
                if(password.equals(users.get(username))){
                    signedIn = true;
                    return "User logged in.";
                }else{
                    shutDown();
                    return "User not logged in. Terminating session.";
                }
            }
            else{
                shutDown();
                return "Command unknown. Terminating session.";
            }
        }else{
            return super.response(msg);
        }
    }
}
