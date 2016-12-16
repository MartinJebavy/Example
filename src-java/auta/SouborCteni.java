package auta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SouborCteni {

    private String text;

    public String cteniZeSouboru(String nazevSouboru) {
        File file = new File(nazevSouboru);
        String obsahSouboru = "";

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String radek = br.readLine();
            while (radek != null) {
                radek = radek.replaceAll("\\s+",""); 
                obsahSouboru += radek + "," /*+ "\n"*/;
                radek = br.readLine();
                
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex);

        } catch (IOException ex) {
            System.err.println(ex);

        }
        //System.out.println(obsahSouboru);
        text = obsahSouboru;
        return obsahSouboru;
    }

    public String getText() {
        return text;
    }

}
