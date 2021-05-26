package src.edu.ufp.inf.lp2.teorico._07_nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;


public class UrlInputApp {
    public static void main(String[] args) throws MalformedURLException {
        URL url=new URL("http://homepage.ufp.pt/rmoreira/LP2/data.txt");
        //readTxtFileFromUrl(url);
        downloadFileFromUrl(url);
    }

    public static void readTxtFileFromUrl(URL url){
        try (InputStream is =url.openStream();
             BufferedReader in=new BufferedReader(new InputStreamReader(is))) {
            int lineCount = 0;
            String line;
            while ((line=in.readLine()) != null) {
                System.out.println("InputFromUrlApp - readTxtFileFromUrl(): ["+ ++lineCount +"]: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadFileFromUrl(URL url) {
        BufferedWriter bw = null;

        try (InputStream is = url.openStream()){
            // Read data into byte array buffer
            byte[] buffer= new byte[20];
            //int bytecount = is.read(buffer, 0, buffer.length);
            int bytecount = 0;
            // Print and save data to local file system
            String urlStrPath = url.getPath();
            String filename = urlStrPath.substring(urlStrPath.lastIndexOf("/") + 1);
            System.out.println("InputFromUrlApp - downloadFileFromUrl(): filename = " + filename);
            File f = new File("data", filename);
            bw = new BufferedWriter(new FileWriter(f));
            while ((bytecount = is.read(buffer, 0, buffer.length)) != -1) {
                System.out.println("\nInputFromUrlApp - downloadFileFromUrl(): bytecount = " + bytecount);
                String s = new String(buffer);
                System.out.println("\nInputFromUrlApp - downloadFileFromUrl(): s = " + s);
                bw.write(s);
                //Clean buffer...
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = '\u0000';
                }
            }
            System.out.println("\nInputFromUrlApp - downloadFileFromUrl(): final bytecount = " + bytecount);
            System.out.println("\nInputFromUrlApp - downloadFileFromUrl(): finished reading from url + "+url);

        } catch (IOException ioe) {
            //ioe.printStackTrace();
            Logger.getLogger(Thread.currentThread().getName()).log(Level.SEVERE, null, ioe);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Thread.currentThread().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
