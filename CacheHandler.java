package echo;

public class CacheHandler extends ProxyHandler {
    private static SafeTable cache = new SafeTable();
    public CacheHandler() {
        super();
    }

    @Override
    protected String response(String msg) throws Exception {
        String outcome = cache.get(msg);
        if(outcome != null){
            System.out.println("[Cache] Cache hit for: " + msg);
            return outcome;
        }
        System.out.println("[Cache] Cache miss for: " + msg);
        outcome = super.response(msg);
        cache.put(msg, outcome);
        return outcome;
    }



}
