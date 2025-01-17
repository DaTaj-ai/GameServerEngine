package gameserverengine.utils;

public class SharedModel {
    
    private static boolean running;

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        SharedModel.running = running;
    }
    
    
    
    
}
