package src.edu.ufp.inf.lp2.teorico._07_nio;

import src.edu.ufp.inf.lp2.pratico._05_figgeo.Circle;
import src.edu.ufp.inf.lp2.pratico._05_figgeo.Point;
import src.edu.ufp.inf.lp2.pratico._05_figgeo.Rectangle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileObjInputStreamApp {
    public static void main(String[] args) {
        String filename = (args.length == 1 ? args[0] : "data/Date.bin");
        //ObjectOutputStream oos = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            //oos = new ObjectOutputStream(new FileOutputStream(filename));
            // Create message and Date objects
            String msg = (String) ois.readObject();
            Date today = (Date) ois.readObject();
            System.out.println(msg + " " + today);
            Point p = (Point) ois.readObject();
            System.out.println(p);
            Rectangle r = (Rectangle)  ois.readObject();
            System.out.println(r);
            Circle c = (Circle) ois.readObject();
            System.out.println(c);
        } catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(Thread.currentThread().getName()).log(Level.SEVERE, null, e);
        }
    }

}
