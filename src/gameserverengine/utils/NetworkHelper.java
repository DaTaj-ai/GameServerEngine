package gameserverengine.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetworkHelper {
    
    public static String getWiFiIPAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();

                // Check if the interface name matches Wi-Fi or WLAN
                String interfaceName = networkInterface.getName().toLowerCase();
                if (!(interfaceName.contains("wlan") || interfaceName.contains("wi-fi"))) {
                    continue;
                }

                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();

                    // Skip IPv6 and non-useful addresses
                    if (!inetAddress.isLoopbackAddress()
                            && !inetAddress.isAnyLocalAddress()
                            && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
