package echo;

import java.net.Socket;

public class MathHandler extends RequestHandler {
    public MathHandler(Socket socket) {
        super(socket);
    }
    public MathHandler(){
        super();
    }
    @Override
    protected String response(String msg){
        String[] operation = msg.trim().split("\\s+");
        if(operation.length < 2){
            return "not valid command";
        }

        String operator = operation[0];
        double outcome = Double.parseDouble(operation[1]);

        for (int i = 2; i < operation.length; i++){
            double num = Double.parseDouble(operation[i]);
            switch(operator){
                case "add":
                    outcome += num;
                    break;
                case "sub":
                    outcome -= num;
                    break;
                case "mul":
                    outcome *= num;
                    break;
                case "div":
                    if (num == 0){
                        return "Error: You divided by zero";
                    }
                    outcome /= num;
                    break;
                default:
                    return "Unknown operator: " + operator;
            }
        }
        return ""+ outcome;
    }



}
