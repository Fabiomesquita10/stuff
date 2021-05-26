package src.edu.ufp.inf.lp2.teorico._07_nio;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileBinOutputStreamApp {
    public static void main(String[] args) {
        DataOutputStream dos = null;
        try {
            String filename = (args.length == 1 ? args[0] : "data/Test.bin");
            double[] data= {1.3, 1.6, 2.1, 3.3, 4.8, 5.6, 6.1, 7.9, 8.2, 9.9};
            // Open file and wrappers
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));

            //Write data size
            dos.writeInt(data.length);
            // Write doubles into the file
            for (int i = 0; i < data.length; i++) {
                dos.writeDouble(data[i]);
            }
        } catch (Exception e) {
            Logger.getLogger(FileBinOutputStreamApp.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(Thread.currentThread().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
