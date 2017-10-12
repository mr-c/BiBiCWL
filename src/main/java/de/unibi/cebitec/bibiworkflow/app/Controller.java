/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.unibi.cebitec.bibiworkflow.app;

import de.unibi.cebitec.bibiworkflow.converter.IConverter;
import de.unibi.cebitec.bibiworkflow.cwl.CwlTool;
import de.unibi.cebitec.bibiworkflow.gui.IMainGui;
import de.unibi.cebitec.bibiworkflow.io.ConvertBs2ToCwlEventHandler;
import de.unibi.cebitec.bibiworkflow.io.FileHandler;
import de.unibi.cebitec.bibiworkflow.io.OpenFileEventHandler;
import de.unibi.cebitec.bibiworkflow.io.YamlWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pol3waf
 */
public class Controller implements IControl {
    
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    private final IConverter converter;
    private final IMainGui mainGui;
    private final FileHandler fileHandler;
    
    
    /**
     * Constructor for the Controller with a given GUI and 
     * @param converter Implementation of a IConverter
     * @param mainGui Implementation of IMainGUI
     */
    public Controller(IConverter converter, IMainGui mainGui)
    {
        this.converter = converter;
        this.mainGui = mainGui;
        this.fileHandler = new FileHandler();
        
        this.setUpGUI();
    }
    
    private void setUpGUI()
    {
        // create all event handlers and pass them to the GUI
        OpenFileEventHandler ofeh = new OpenFileEventHandler(this.fileHandler);
        ConvertBs2ToCwlEventHandler ceh = new ConvertBs2ToCwlEventHandler(this);
        
        this.mainGui.launchGUI(ofeh, ceh);
    }
    
    
    @Override
    public void convertBs2ToCWL()
    {
        try
        {
            LOGGER.info("Start conversion of bs2 to CWL Tool ...");
            HashMap<String, CwlTool> cwlTools = converter.convertBs2(this.fileHandler.convertBs2ToRunnableItem());
            
            LOGGER.info("Start conversion to YAML ...");
            YamlWriter ym = new YamlWriter();
            
            // write each cwlTool to a separate file
            for (String key : cwlTools.keySet())
            {
                String fileName = key;
                CwlTool cwlTool = cwlTools.get(key);
                String yamlText = ym.writeObjectToYaml(cwlTool);
                LOGGER.info("\n\n" + fileName + "\n" + yamlText + "\n\n");
//                fileHandler.writeStringToFile(yamlText, fileName);
            }
        }
        catch (Exception ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
