
package pathx;
import path_x.ui.PathXErrorHandler;
import path_x.ui.PathXGame;
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
            System.out.println("ever get here");
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
        FILE_GAME_PROPERTIES,
        FILE_PLAYER_RECORD,

        /* DIRECTORY PATHS FOR FILE LOADING */
        PATH_AUDIO,
        PATH_IMG,
        
        // LOADED FROM THE GAME FLAVOR PROPERTIES XML FILE
            // path_x_properties.xml
                
        /* IMAGE FILE NAMES */
        IMAGE_BACKGROUND_HOME,
        IMAGE_BACKGROUND_GAME,
        IMAGE_BACKGROUND_LEVEL_SELECT,
        IMAGE_BACKGROUND_SPLASH,
        IMAGE_BUTTON_PLAY,
        IMAGE_BUTTON_RESET,
        IMAGE_BUTTON_SETTINGS,
        IMAGE_BUTTON_HELP,
        IMAGE_BUTTON_PLAY_MOUSEOVER,
        IMAGE_BUTTON_RESET_MOUSEOVER,
        IMAGE_BUTTON_SETTINGS_MOUSEOVER,
        IMAGE_BUTTON_HELP_MOUSEOVER,
        IMAGE_BUTTON_QUIT,
        IMAGE_BUTTON_RETURN_HOME,
        IMAGE_BUTTON_SCROLL_NORTH,
        IMAGE_BUTTON_SCROLL_EAST,
        IMAGE_BUTTON_SCROLL_SOUTH,
        IMAGE_BUTTON_SCROLL_WEST,
        IMAGE_BUTTON_START_LEVEL,
        IMAGE_BUTTON_SCROLL_NORTH_MOUSEOVER,
        IMAGE_BUTTON_SCROLL_EAST_MOUSEOVER,
        IMAGE_BUTTON_SCROLL_SOUTH_MOUSEOVER,
        IMAGE_BUTTON_SCROLL_WEST_MOUSEOVER,
        //IMAGE_BUTTON_MUTE,
        //IMAGE_BUTTON_UNMUTE,
        IMAGE_BUTTON_CONFIRM,
        IMAGE_BUTTON_DENY,
        IMAGE_BUTTON_CONFIRM_MOUSEOVER,
        IMAGE_BUTTON_DENY_MOUSEOVER,
        IMAGE_LEVEL_AVAILABLE,
        IMAGE_LEVEL_UNAVAILABLE,
        IMAGE_LEVEL_COMPLETE,
       IMAGE_DIALOG_LEVEL_OVERLAY,
        IMAGE_DIALOG_QUIT,
        IMAGE_WINDOW_ICON, 
        
        /* GAME TEXT */
        TEXT_ERROR_LOADING_AUDIO,
        TEXT_ERROR_LOADING_LEVEL,
        TEXT_ERROR_LOADING_RECORD,
        TEXT_ERROR_LOADING_XML_FILE,
        TEXT_ERROR_SAVING_RECORD,
        TEXT_PROMPT_EXIT,
        TEXT_TITLE_BAR_GAME,
        TEXT_TITLE_BAR_ERROR, 


        
        /* AUDIO CUES */
        
        
        /* TILE LOADING STUFF */
        
        
    }
    
}
