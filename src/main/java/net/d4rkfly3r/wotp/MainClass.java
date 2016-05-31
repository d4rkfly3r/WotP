package net.d4rkfly3r.wotp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Created by Joshua on 5/31/2016.
 */
public class MainClass {

    static String localhostIP;

    public static void main(String[] args) {
        try {
            localhostIP = getIP();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(localhostIP);
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            new Game().start();
//            MainClass.frame.setResizable(false);
//            MainClass.frame.setTitle(title);
//            MainClass.frame.add(game);
//            MainClass.frame.pack();
//            MainClass.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            MainClass.frame.setLocationRelativeTo(null);
//            MainClass.frame.setVisible(true);
//            Audio.playSound(Audio.LOADUP);
//            Audio.playSound(Audio.SOUND_LOADUP);
//
//            game.start();

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
