/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.techapp.sampleclass.SampleClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tianzhi
 */
public class SampleClassTest {
    
    public SampleClassTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    /**
     * Check initial state.
     */
    @Test
    public void defaultState(){
        SampleClass sample = new SampleClass();
        assertFalse(sample.isEnabled());
    }
    
    /**
     * Test enabling and disabling state.
     */
    @Test
    public void updateEnabledState(){
        SampleClass sample = new SampleClass();
        sample.setEnabled(true);
        assertTrue(sample.isEnabled());
        sample.setEnabled(false);
        assertFalse(sample.isEnabled());
    }
    
}
