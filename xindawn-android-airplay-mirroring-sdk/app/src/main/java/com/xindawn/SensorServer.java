import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class SensorServer extends WebSocketServer {
    private Map<String, WebSocket> devices = 
        new Map<String, WebSocket>();

    // When new device connects
    @Override 
    public void onOpen(
                    WebSocket device, 
                    ClientHandshake handshake
    ) {
        // Generate random UUID and add device to map
        String id = UUID.randomUUID().toString();
        devices.put(id, device);
    }
    
    // When a device disconnects
    @Override
    public void onClose(
                    WebSocket device, 
                    int code, 
                    String reason, 
                    boolean remote
    ) {
        // Remove device from the map
        devices.remove(device);
    }
    
    // Broadcast message to all connected devices
    public void pushData(ByteBuffer message) {
        broadcast(message.array());
    }
}