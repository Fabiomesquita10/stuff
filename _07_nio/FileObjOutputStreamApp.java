package src.edu.ufp.inf.lp2.teorico._07_nio;

import src.edu.ufp.inf.lp2.pratico._05_figgeo.Circle;
import src.edu.ufp.inf.lp2.pratico._05_figgeo.Point;
import src.edu.ufp.inf.lp2.pratico._05_figgeo.Rectangle;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileObjOutputStreamApp {
    public static void main(String[] args) {
        String filename = (args.length == 1 ? args[0] : "data/Date.bin");
        //ObjectOutputStream oos = null;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            //oos = new ObjectOutputStream(new FileOutputStream(filename));
            // Create message and Date objects
            String msg = "Today's Date:";
            Date today = new Date(System.currentTimeMillis());
            // Write String object followed by Date object
            oos.writeObject(msg);
            oos.writeObject(today);
            Point p = new Point(0.0, 0.0);
            oos.writeObject(p);
            Rectangle r = new Rectangle(new Point(0.0, 0.0), new Point(1.0, 1.0), Color.BLUE);
            oos.writeObject(r);
            Circle c = new Circle( Color.WHITE, new Point(0.0, 0.0),1.0);
            oos.writeObject(c);
        } catch (IOException e) {
            Logger.getLogger(Thread.currentThread().getName()).log(Level.SEVERE, null, e);
        }
    }

}
