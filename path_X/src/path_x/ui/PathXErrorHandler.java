/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package path_x.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pathx.PathX;
import pathx.PathX.PathXPropertyType;
import properties_manager.PropertiesManager;

/**
 *
 * @author prima_000
 */
public class PathXErrorHandler {
    private JFrame window;

    
    public PathXErrorHandler(JFrame initWindow)
    {
        window = initWindow;
    }
    
    
    public void processError(PathXPropertyType errorType) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String errorFeedbackText = props.getProperty(errorType);
        
        String errorTitle = props.getProperty(PathXPropertyType.TEXT_TITLE_BAR_ERROR);
        
        JOptionPane.showMessageDialog(window, errorFeedbackText, errorTitle, JOptionPane.ERROR_MESSAGE);
    }
    
}
