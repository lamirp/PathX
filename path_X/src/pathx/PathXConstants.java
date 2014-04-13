package pathx;

import java.awt.Color;
import java.awt.Font;

/**
 * This class stores constants required for the game, file locations and other
 * important things.
 *
 * @author prima_000
 */
public class PathXConstants {

    //File Names
    public static String PROPERTY_TYPES_LIST = "property_types.txt";
    public static String PROPERTIES_FILE_NAME = "properties.xml";
    public static String PROPERTIES_SCHEMA_FILE_NAME = "properties_schema.xsd";
    public static String PATH_DATA = "./data/";

    //Background types
    public static final String BACKGROUND_TYPE = "BACKGROUND_TYPE";
    //Border Types
    public static final String BORDER_TYPE = "BORDER_TYPE";

    // ANIMATION SPEED
    public static final int FPS = 30;

    public static final int BUTTON_OFFSET = 40;
    // UI CONTROL SIZE AND POSITION SETTINGS
    public static final int WINDOW_WIDTH = 645;
    public static final int WINDOW_HEIGHT = 505;
    public static final int VIEWPORT_MARGIN_LEFT = 20;
    public static final int VIEWPORT_MARGIN_RIGHT = 20;
    public static final int VIEWPORT_MARGIN_TOP = 20;
    public static final int VIEWPORT_MARGIN_BOTTOM = 20;
    public static final int LEVEL_BUTTON_WIDTH = 200;
    public static final int LEVEL_BUTTON_MARGIN = 5;
    public static final int LEVEL_BUTTON_Y = 570;
    public static final int VIEWPORT_INC = 5;
    public static final int VIEWPORT_SCROLL_INC = 5;
    public static final int LEVEL_SELECT_NORTH_PANEL_HEIGHT = 90;
    public static final int QUIT_BUTTON_X = WINDOW_WIDTH - BUTTON_OFFSET;
    public static final int QUIT_BUTTON_Y = 20;
    public static final int HOME_BUTTON_X = QUIT_BUTTON_X - BUTTON_OFFSET;
    public static final int HOME_BUTTON_Y = QUIT_BUTTON_Y;
    public static final int HELP_MARGIN_LEFT = 20;
    public static final int HELP_MARGIN_TOP = 40;

    //MENU UI CONTROLS POSITIONS IN THE GAME SCREEN
    public static final String PLAY_GAME_BUTTON_TYPE = "PLAY_GAME_BUTTON_TYPE";
    public static final String RESET_BUTTON_TYPE = "RESET_BUTTON_TYPE";
    public static final String SETTINGS_BUTTON_TYPE = "SETTINGS_BUTTON_TYPE";
    public static final String HELP_BUTTON_TYPE = "HELP_BUTTON_TYPE";

    public static final int PLAY_BUTTON_X = 60;
    public static final int MENU_BUTTON_Y = 380;
    public static final int RESET_BUTTON_X = PLAY_BUTTON_X + 130;
    public static final int SETTINGS_BUTTON_X = RESET_BUTTON_X + 130;
    public static final int HELP_BUTTON_X = SETTINGS_BUTTON_X + 130;

    //SCREEN SCROLL BUTTONS
    public static final String SCROLL_BUTTON_NORTH_TYPE = "SCROLL_BUTTON_NORTH_TYPE";
    public static final String SCROLL_BUTTON_EAST_TYPE = "SCROLL_BUTTON_EAST_TYPE";
    public static final String SCROLL_BUTTON_SOUTH_TYPE = "SCROLL_BUTTON_SOUTH_TYPE";
    public static final String SCROLL_BUTTON_WEST_TYPE = "SCROLL_BUTTON_WEST_TYPE";

    //HELP SCROLL BUTTONS
    public static final String HELP_SCROLL_BUTTON_SOUTH_TYPE = "HELP_SCROLL_BUTTON_SOUTH_TYPE";
    public static final String HELP_SCROLL_BUTTON_NORTH_TYPE = "HELP_SCROLL_BUTTON_NORTH_TYPE";

    //NAVI BUTTONS
    public static final String QUIT_BUTTON_TYPE = "QUIT_BUTTON_TYPE";
    public static final String HOME_BUTTON_TYPE = "HOME_BUTTON_TYPE";
    //LEVEL NAVI BUTTONS
    public static final String LEVEL_HOME_BUTTON_TYPE = "LEVEL_HOME_BUTTON_TYPE";
    public static final String LEVEL_QUIT_BUTTON_TYPE = "LEVEL_QUIT_BUTTON_TYPE";

    //SCROLL BUTTON LOCATIONS
    public static final int SCROLL_BUTTON_OFFSET = 20;
    public static final int SCROLL_BUTTON_NORTH_X = WINDOW_WIDTH / 11;
    public static final int SCROLL_BUTTON_NORTH_Y = WINDOW_HEIGHT - (WINDOW_HEIGHT / 4);
    public static final int SCROLL_BUTTON_EAST_X = SCROLL_BUTTON_NORTH_X + SCROLL_BUTTON_OFFSET;
    public static final int SCROLL_BUTTON_EAST_Y = SCROLL_BUTTON_NORTH_Y + SCROLL_BUTTON_OFFSET;
    public static final int SCROLL_BUTTON_SOUTH_X = SCROLL_BUTTON_NORTH_X;
    public static final int SCROLL_BUTTON_SOUTH_Y = SCROLL_BUTTON_EAST_Y + SCROLL_BUTTON_OFFSET;
    public static final int SCROLL_BUTTON_WEST_X = SCROLL_BUTTON_NORTH_X - SCROLL_BUTTON_OFFSET;
    public static final int SCROLL_BUTTON_WEST_Y = SCROLL_BUTTON_EAST_Y;
    //HELP SCROLLER LOCATIONS
    public static final int HELP_SCROLL_BUTTON_NORTH_X = WINDOW_WIDTH - (WINDOW_WIDTH /11);
    public static final int HELP_SCROLL_BUTTON_NORTH_Y = WINDOW_HEIGHT - (WINDOW_HEIGHT / 4);
    public static final int HELP_SCROLL_BUTTON_SOUTH_X = HELP_SCROLL_BUTTON_NORTH_X;
    public static final int HELP_SCROLL_BUTTON_SOUTH_Y = SCROLL_BUTTON_EAST_Y + SCROLL_BUTTON_OFFSET;
    //LEVEL NAVI LOCATIONS
    public static final int LEVEL_HOME_BUTTON_X = WINDOW_WIDTH / 15;
    public static final int LEVEL_HOME_BUTTON_Y = WINDOW_HEIGHT / 5;
    public static final int LEVEL_QUIT_BUTTON_X = LEVEL_HOME_BUTTON_X + BUTTON_OFFSET;
    public static final int LEVEL_QUIT_BUTTON_Y = WINDOW_HEIGHT / 5;
    
    //Screen states
    public static final String MENU_SCREEN_STATE = "MENU_SCREEN_STATE";
    public static final String LEVEL_SCREEN_STATE = "LEVEL_SCREEN_STATE";
    public static final String SETTINGS_SCREEN_STATE = "SETTINGS_SCREEN_STATE";
    public static final String GAME_SCREEN_STATE = "GAME_SCREEN_STATE";
    public static final String HELP_SCREEN_STATE = "HELP_SCREEN_STATE";
    
    //Border states
    public static final String DEFAULT_BORDER = "DEFAULT_BORDER";
    public static final String NO_BORDER = "NO_BORDER";
    public static final String LEVEL_BORDER = "LEVEL_BORDER";
 

    // DIALOG TYPES
    public static final String DIALOG_LEVEL_TYPE = "DIALOG_LEVEL_TYPE";
    public static final String DIALOG_QUIT_TYPE = "DIALOG_QUIT_TYPE";
    public static final String DIALOG_HELP_TYPE = "DIALOG_HELP_TYPE";

    // DIALOG COORDINATES
    public static final int QUIT_TEXT_X = 400;
    public static final int QUIT_TEXT_Y = 300;

    //TEXTUAL DATA
    // COLORS USED FOR RENDERING VARIOUS THINGS, INCLUDING THE
    // COLOR KEY, WHICH REFERS TO THE COLOR TO IGNORE WHEN
    // LOADING ART.
    public static final Color COLOR_KEY = new Color(255, 174, 201);
    public static final Color COLOR_DEBUG_TEXT = Color.BLACK;
    public static final Color COLOR_TEXT_DISPLAY = new Color(10, 160, 10);
    public static final Color COLOR_DIALOG = new Color(0, 60, 0);
    public static final Color COLOR_ALGORITHM_HEADER = Color.WHITE;

    // FONTS USED DURING FOR TEXTUAL GAME DISPLAYS
    public static final Font FONT_TEXT_DISPLAY = new Font(Font.SANS_SERIF, Font.BOLD, 48);
    public static final Font FONT_DEBUG_TEXT = new Font(Font.MONOSPACED, Font.BOLD, 14);
    public static final Font FONT_DIALOG = new Font(Font.MONOSPACED, Font.BOLD, 16);
    public static final Font FONT_GAME_TYPE_TEXT = new Font(Font.SANS_SERIF, Font.BOLD, 18);
}
