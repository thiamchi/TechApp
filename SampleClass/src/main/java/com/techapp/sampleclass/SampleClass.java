/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techapp.sampleclass;

/**
 *
 * @author Tianzhi
 */
public class SampleClass {
    /**
     * Declarations
     */
    private boolean enabled;
    
    /**
     * Constructor
     */
    public SampleClass(){
        
    }
    
    /**
     * Is enabled?
     * 
     * @return true if enabeld, false otherwise
     */
    public boolean isEnabled(){
        return enabled;
    }
        
    /**
     * Set enable state
     * 
     * @param enabled
     * @return this SampleClass
     */
    public SampleClass setEnabled(boolean enabled){
        this.enabled = enabled;
        return this;
    }
}
