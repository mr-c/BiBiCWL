/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.unibi.cebitec.bibiworkflow.gui;

/**
 * Listener interface for the Model to use and update the GUI.
 * @author pol3waf
 */
public interface IModelListener {
    
    public void documentHasChanged(String document);
    
    public void newDocumentCreated(String document);
    
    
}
