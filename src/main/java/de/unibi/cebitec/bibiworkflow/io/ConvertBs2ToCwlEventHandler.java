/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.unibi.cebitec.bibiworkflow.io;

import de.unibi.cebitec.bibiworkflow.app.GuiControl;
import javafx.event.ActionEvent;

/**
 *
 * @author pol3waf
 */
public class ConvertBs2ToCwlEventHandler implements javafx.event.EventHandler<ActionEvent>
{

    private final GuiControl controller;
    
    /**
     *
     * @param controller
     */
    public ConvertBs2ToCwlEventHandler(GuiControl controller)
    {
        this.controller = controller;
    }
    
    
    
    @Override
    public void handle(ActionEvent event)
    {
        controller.convertBs2ToCWL();
    }
    
}
