/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ReadConfig {

    public  ReadConfig(){ 
    }// end constructor

    public HashMap getConfigMap() {
        HashMap map =new HashMap();
        try{
            File file = new File(System.getProperty("user.dir"), "config.txt");
            //File file = new File("config.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null)
            {
                line = line.trim();
                if(line.startsWith("#")){
                    line = br.readLine();
                    continue;
                }
                int index = line.indexOf('=');
                String paramName = line.substring(0, index).trim() ;
                String paramValue = line.substring(index+1).trim() ;
                map.put(paramName, paramValue);
                line = br.readLine();
            }
            
        }catch(FileNotFoundException e){
            System.out.println("config file not found. ");
            Logger.getLogger(ReadConfig.class.getName()).log(Level.SEVERE, " Config file not found. " + e.getMessage(), e);
            JOptionPane.showMessageDialog(null, " Config file not Found. " + e.getMessage(), "Error in reading config file ", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            //e.printStackTrace();
        }catch(IOException e){
            System.out.println("IOException occured in reading config file. ");
            Logger.getLogger(ReadConfig.class.getName()).log(Level.SEVERE, " IOException occured in reading config file. " + e.getMessage(), e);
            JOptionPane.showMessageDialog(null, " Config file not Found. " + e.getMessage(), "Error in reading config file ", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }catch (Exception e) {
            Logger.getLogger(ReadConfig.class.getName()).log(Level.SEVERE, " Exception occured in reading config file. " + e.getMessage(), e);
            JOptionPane.showMessageDialog(null, " Exception occured while reading Config file. " + e.getMessage(), "Error in reading config file ", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return map ;
    }// end method


}// end class
