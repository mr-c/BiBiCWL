package de.unibi.cebitec.bibiworkflow.app;

import de.unibi.cebitec.bibiworkflow.bs2.Bs2Document;
import de.unibi.cebitec.bibiworkflow.bs2.IBs2Document;
import de.unibi.cebitec.bibiworkflow.gui.MainGUI;
import de.unibi.techfak.bibiserv.cms.Tfunction;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    
    
    private static String path = "/home/pol3waf/Programming/Projects/basedoc/BasespaceConverter/SampleFiles/blast_final_2.bs2";
    
    
    
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        
        // create GUI
        MainGUI mainGUI = new MainGUI();
        mainGUI.startMainGUI(args);
        
        try {
            IBs2Document bs2document = new Bs2Document(path);
            Tfunction function = bs2document.getFunctions().get(0);
            ArrayList<String> orderedArgumentList = bs2document.getCommandLineArgumentOrderAsReferences(function);
            
            for (String e : orderedArgumentList)
            {
                System.out.println(e.toString());
            }
        } catch (JAXBException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
