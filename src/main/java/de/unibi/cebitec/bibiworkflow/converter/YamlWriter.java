/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.unibi.cebitec.bibiworkflow.converter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.HashMap;

import java.util.logging.Logger;


/**
 *
 * @author pol3waf
 */
public class YamlWriter
{
    
    private static final Logger LOGGER = Logger.getLogger(YamlWriter.class.getName());
    
    private final YAMLMapper mapper = new YAMLMapper();
    
    
    
    public YamlWriter()
    {
        
    }
    
    
    public String writeObjectToYaml(Object object)
    {
        String yamlString = null;
        try
        {
            LOGGER.info("trying to write yamlString ...");
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            yamlString = mapper.writeValueAsString(object);
        }
        catch (JsonProcessingException ex) 
        {
            LOGGER.warning("Couldn't create YAML doc from Object " + object.toString());
        }
        
        return yamlString;
    }
    
    
    
    
    /**
     * Converts a set of CwlTools into a set of YAML Strings.
     * @param objects
     * @return 
     */
    public HashMap<String, String> convertMultipleObjectsToYaml(HashMap<String, ?> objects)
    {
        HashMap<String, String> yamlDocuments = new HashMap<>();
        
        objects.forEach( (name, cwlTool) ->
        {
            String yamlDocument = this.writeObjectToYaml(cwlTool);
            LOGGER.info("\nYAML output is:\n" + name + "\n" + yamlDocument + "\n\n");
            yamlDocuments.put(name, yamlDocument);
        }
        );
        
        return yamlDocuments;
    }
    
    
}

