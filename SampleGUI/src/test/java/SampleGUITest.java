/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.techapp.samplegui.MainApp;
import java.util.concurrent.TimeoutException;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import static org.testfx.util.DebugUtils.informedErrorMessage;

/**
 *
 * @author Tianzhi
 */
public class SampleGUITest extends ApplicationTest{
    /**
     * Declaration
     */
    private static final FxRobot bot = new FxRobot();
    
    /**
     * Launch the MainApp.
     * @throws java.lang.Exception
     */
    @Before
    public void setUpClass() throws Exception {
        ApplicationTest.launch(MainApp.class);
    }
    
    /**
     * 
     * @param stage
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }
    
    /**
     * End of Test
     * @throws TimeoutException 
     */
    @After
    public void afterTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    /**
     * Unit test on the button to update label
     */
    @Test
    public void setlabel() {
        clickOn(".button");
        verifyThat(".label", hasText("Hello World!"), informedErrorMessage(this));
    }
}
