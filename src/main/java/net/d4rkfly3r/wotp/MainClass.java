package net.d4rkfly3r.wotp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class MainClass {

    public static final boolean DEBUG = false;
    private static String localhostIP;

    public static void main(String[] args) {
        try {
            localhostIP = getIP();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(localhostIP);
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            new Game().start();
            return null; // nothing to return
        });

    }

    public static String getLocalhostIP() {
        return localhostIP;
    }

    private static String getIP() throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            return in.readLine();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
