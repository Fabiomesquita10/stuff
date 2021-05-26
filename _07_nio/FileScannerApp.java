package src.edu.ufp.inf.lp2.teorico._07_nio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileScannerApp {
    public static void main(String[] args) throws IOException {
        Scanner s=null;

        try {
            //Create scanner for txt file
            //The default delimiter is ' ' (whitespace)
            s = new Scanner(new BufferedReader(new FileReader("data/Alunos.txt")));
            s.useDelimiter("\\s*, |\\s*,|\\s*\n");
            //Walk through the file content (token by token) using ' ' as delimiter
            while (s.hasNext()) {
                /*
                String token=s.next();
                if (token.contains(",")){
                    String str = token.replace(',', ' ');
                    System.out.println("|"+str.trim()+"|");
                } else {
                    System.out.println("|"+token+"|");
                }
                */
                System.out.println(s.next());
                System.out.println(s.nextInt());
                System.out.println(s.next());
            }
        } finally {
            if (s != null) s.close();
        }
    }

}
