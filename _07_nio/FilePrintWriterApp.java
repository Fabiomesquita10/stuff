/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.edu.ufp.inf.lp2.teorico._07_nio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rjm
 */
public class FilePrintWriterApp {

    public static void main(String[] args) {
        FileWriter fw = null;
        PrintWriter pw = null;
        
        try {
            File f = new File("data", "Alunos.txt");
            fw = new FileWriter(f);
            System.out.println("FilePrintWriter - main(): encoding = " + fw.getEncoding());
            pw = new PrintWriter(fw);
            //pw = new PrintWriter(new FileWriter(new File("data", "Alunos.txt")));
            
            // Print into to the file
            pw.println("Nuno Alexandre, 12, 15.5");
            pw.println("Pedro Lucas, 7, 18.5");
            // Flush
            pw.flush();
            pw.println("Antonio Miguel, 3, 14.5");
            pw.println("Outro Aluno, 3, 16.5");

            //pw.flush();
            //(...)

        } catch (Exception e) {
            System.err.println("FilePrintWriterApp - main(): " + e.toString());
        } finally {
            try {
                // Flush and close
                if (pw!=null) pw.close();
                if (fw!=null) fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FilePrintWriterApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
