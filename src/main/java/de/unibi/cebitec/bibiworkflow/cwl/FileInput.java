/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.unibi.cebitec.bibiworkflow.cwl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.unibi.cebitec.bibiworkflow.app.GuiControl;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author pol3waf
 */
public class FileInput extends Input
{
    
    private static final Logger LOGGER = Logger.getLogger(FileInput.class.getName());
    
    @JsonProperty
    protected String fileType;
    
    
    private final String defaultType = "File";
    private final String defaultArrayType = "File[]";
    
    
    
    
    
    
    
    
    
    
    
    public FileInput(int position, String id, String prefix, Boolean separate, String fileType)
    {
        super.type = defaultType;
        super.id = id;
        super.inputBinding = new InputBinding(prefix, separate, position);
        this.fileType = fileType;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * This method looks at several key options like isShellQuote, isOptional
     * and isArrayInput and assembles them into a coherent representation of the
     * input.
     * This is done to avoid individual cross-checking the input's state every
     * time a option is activated or deactivated.
     */
    private void assembleInputParts()
    {
        // set the atomar type (which is either "File" or "File[]"
        String atomarType;
        if (this.isArrayInput)
        {
            atomarType = this.defaultArrayType;
        }
        else
        {
            atomarType = this.defaultType;
        }
        
        // if isOptional is true, create an array of types with one type being 
        // "null" and the other being the atomar type
        if (this.isOptional)
        {
            ArrayList<String> typeArray = new ArrayList<>();
            typeArray.add("<null>");
            typeArray.add(atomarType);
            this.type = typeArray;
        }
        else
        {
            this.type = atomarType;
        }
        
    }
    
    
    
    
    
    protected void allowNull()
    {
        /*
            TODO:   the "<null>" thingy should be changed in the future. 
                    For now it is used to sort out the formatting of the 
                    "null"-string issue when converting the objects to YAML
                    by calling an extra function in the YAMLConverter:
                    
                    fixNullQuotes(String yamldocument)
        */
        this.type = new String[] {"<null>", "File"};
    }
    
    protected void doNotAllowNull()
    {
        this.type = "File";
    }
    
    
    
    
    @Override
    protected void enableOptional()
    {
        this.isOptional = true;
        this.assembleInputParts();
//        if (this.type instanceof String)
//        {
//            String oldType = (String) super.type;
//            ArrayList<String> typesList = new ArrayList<>();
//            typesList.add(oldType);
//            typesList.add("<null>");
//            this.type = typesList;
//        }
//        else if (this.type instanceof ArrayList)
//        {
//            ((ArrayList)this.type).add("<null>");
//        }
    }
    
    
    
    
    @Override
    protected void disableOptional()
    {
        this.isOptional = false;
        this.assembleInputParts();
//        if (this.type instanceof ArrayList)
//        {
//            ((ArrayList)this.type).remove("<null>");
//            
//            // if there is only one element left in the list, convert type to a simple string
//            if (((ArrayList)this.type).size() == 1)
//            {
//                this.type = ((ArrayList)this.type).get(0);
//            }
//        }
    }
    
    
    
    
    @Override
    protected void enableShellQuote()
    {
        this.inputBinding.activateShellQuote();
    }
    
    
    
    
    @Override
    protected void disableShellQuote()
    {
        this.inputBinding.deactivateShellQuote();
    }
    
    
    
    
    
    /*
        OK ... now I am adding more functions and sometimes there are dependencies.
        FIX THIS!!!!!
        --> make one "assemble input parts"-function which takes in all the options
            and fills in the member variables (or variables marked with @jsonProperty)
            and run that function every time some stuff is changed.
    */
    
    
    @Override
    protected void enableArrayInput()
    {
        this.isArrayInput = true;
        this.assembleInputParts();
//        if ( ! super.isArrayInput )
//        {
//            super.isArrayInput = true;
//            
//            if ( super.isOptional )
//            {
//                ((ArrayList)this.type).remove("File");
//                ((ArrayList)this.type).add("File[]");
//            }
//            else
//            {
//                this.type = "File[]";
//            }
//        }
    }
    
    
    
    
    @Override
    protected void disableArrayInput()
    {
        this.isArrayInput = false;
        this.assembleInputParts();
//        if ( super.isArrayInput )
//        {
//            super.isArrayInput = false;
//            
//            if ( super.isOptional )
//            {
//                ((ArrayList)this.type).remove("File[]");
//                ((ArrayList)this.type).add("File");
//            }
//            else
//            {
//                this.type = "File";
//            }
//        }
    }
}
