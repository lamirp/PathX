
package pathx;
import path_x.ui.PathXMiniGame;
import path_x.ui.PathXErrorHandler;
import xml_utilities.InvalidXMLFileFormatException;
import properties_manager.PropertiesManager;
import static pathx.PathXConstants.*;

/**
 *
 * @author prima_000
 */
public class PathX {
    
    //CREATE INSTANCE OF MINIGAME
    static PathXGame miniGame = new PathXGame();
    
    public static void main(String[] args)
    {
          try
        {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
            props.loadProperties(PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            
            // THEN WE'LL LOAD THE GAME FLAVOR AS SPECIFIED BY THE PROPERTIES FILE
            String gameFlavorFile = props.getProperty(PathXPropertyType.FILE_GAME_PROPERTIES);
            props.loadProperties(gameFlavorFile, PROPERTIES_SCHEMA_FILE_NAME);

            // NOW WE CAN LOAD THE UI, WHICH WILL USE ALL THE FLAVORED CONTENT
            String appTitle = props.getProperty(PathXPropertyType.TEXT_TITLE_BAR_GAME);
            miniGame.initMiniGame(appTitle, FPS, WINDOW_WIDTH, WINDOW_HEIGHT);
            
            // GET THE PROPER WINDOW DIMENSIONS
            miniGame.startGame();
        }
        // THERE WAS A PROBLEM LOADING THE PROPERTIES FILE
        catch(InvalidXMLFileFormatException ixmlffe)
        {
            // LET THE ERROR HANDLER PROVIDE THE RESPONSE
            PathXErrorHandler errorHandler = miniGame.getErrorHandler();
            errorHandler.processError(PathXPropertyType.TEXT_ERROR_LOADING_XML_FILE);
        }
    }
    
    /**
     * Create Property Type representing all data extracted 
     * from XML properties file
     */
    public enum PathXPropertyType
    {
        //LOAD FROM PROPERTIES.XML
       /* SETUP FILE NAMES */


        /* DIRECTORY PATHS FOR FILE LOADING */
        
        
        // LOADED FROM THE GAME FLAVOR PROPERTIES XML FILE
            // path_x_properties.xml
                
        /* IMAGE FILE NAMES */
        
        
        /* GAME TEXT */
        
        
        /* AUDIO CUES */
        
        
        /* TILE LOADING STUFF */
        
        
    }
    
}
