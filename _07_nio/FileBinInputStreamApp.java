package src.edu.ufp.inf.lp2.teorico._07_nio;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileBinInputStreamApp {
    public static void main(String[] args) {
        String filename = (args.length == 1 ? args[0] : "data/Test.bin");
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
            //Read size of array
            int size = dis.readInt();
            //Create array doubles
            double[] data= new double[size];
            //Read doubles into array
            for (int i = 0; i < size; i++) {
                data[i] = dis.readDouble();
                //dataBytes[i] = dis.readByte();
                System.out.println("d = " + data[i]);
            }
        } catch (Exception e) {
            Logger.getLogger(Thread.currentThread().getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FileBinInputStreamApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
