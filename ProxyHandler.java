package echo;
import echo.Correspondent;
import echo.RequestHandler;
import java.net.Socket;

public class ProxyHandler extends RequestHandler {

    protected Correspondent peer;

    public ProxyHandler(Socket s) { super(s); }
    public ProxyHandler() { super(); }

    public void initPeer(String peerHost, int peerPort) {
        peer = new Correspondent();
        peer.requestConnection(peerHost, peerPort);
    }

    protected String response(String msg) throws Exception {
        peer.send(msg);
        return peer.receive();
    }
}
