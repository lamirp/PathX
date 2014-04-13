package path_x.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import mini_game.MiniGame;
import mini_game.Viewport;
import mini_game.Sprite;
import mini_game.SpriteType;
import path_x.data.PathXDataModel;
import path_x.data.PathXLevel;
import path_x.file.PathXFileManager;
import pathx.PathX.PathXPropertyType;
import static pathx.PathXConstants.*;
import properties_manager.PropertiesManager;

/**
 *
 * @author prima_000
 */
public class PathXGame extends MiniGame {

    //handlers
    private PathXEventHandler eventHandler;

    //error handler
    private PathXErrorHandler errorHandler;

    //file manager
    private PathXFileManager fileManager;

    //current screen
    private String currentScreenState;

    /**
     * Necessary getters
     */
    public PathXFileManager getFileManager() {
        return fileManager;
    }

    public PathXErrorHandler getErrorHandler() {
        return errorHandler;
    }
    //public PathXRecord getPlayerRecord() {  return record;  }

    /**
     * Test Method to see is screen state is what it should be
     */
    public boolean isCurrentScreenState(String testScreenState) {

        return testScreenState.equals(currentScreenState);
    }

    //NEED DISPLAY UPDATE METHODS AKA VIEWPORT AS DISCUSSED IN LECTURE
    /**
     * initViewport scroll moveVieport
     *
     * displaystates savePlayerRecord switchToLevelScreen LEVEL SELECT
     * switchToSplashScreen HOME SCREEN switchToGameScreen GAMEPLAY SCREEN
     * displayLevelData displayHelpOverlay displaySettingsOverlay
     *
     *
     *
     * public void initViewport() { Viewport viewport = new Viewport(); }
     *
     * public void scrollViewport() {
     *
     * }
     *
     * public void moveViewport() {
     *
     * }
     */
    public void switchToSplashScreen() {
        guiDecor.get(BACKGROUND_TYPE).setState(MENU_SCREEN_STATE);
        // DEACTIVE EVERYTHING NOT ON SCREEN
        // ACTIVATE EVERYTHING ON SCREEN
        guiButtons.get(PLAY_GAME_BUTTON_TYPE).setState(PathXButtonState.VISIBLE_STATE.toString());
        guiButtons.get(PLAY_GAME_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(RESET_BUTTON_TYPE).setState(PathXButtonState.VISIBLE_STATE.toString());
        guiButtons.get(RESET_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(SETTINGS_BUTTON_TYPE).setState(PathXButtonState.VISIBLE_STATE.toString());
        guiButtons.get(SETTINGS_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(HELP_BUTTON_TYPE).setState(PathXButtonState.VISIBLE_STATE.toString());
        guiButtons.get(HELP_BUTTON_TYPE).setEnabled(true);

    }

    @Override
    public void initAudioContent() {
        //NO AUDIO CURRENTLY TO BE INITIALIZED
    }

    @Override
    public void initData() {
        // INIT OUR ERROR HANDLER
        errorHandler = new PathXErrorHandler(window);

        // INIT OUR FILE MANAGER
        fileManager = new PathXFileManager(this);

        // INIT OUR DATA MANAGER
        data = new PathXDataModel(this);
    }

    @Override
    public void initGUIControls() {
        BufferedImage img;
        float x, y;
        SpriteType sT;
        Sprite s;

        //ADD WINDOW ICON
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imgPath = props.getProperty(PathXPropertyType.PATH_IMG);
        String windowIconFile = props.getProperty(PathXPropertyType.IMAGE_WINDOW_ICON);
        img = loadImage(imgPath + windowIconFile);
        window.setIconImage(img);

//construct panel        
        canvas = new PathXPanel(this, (PathXDataModel) data);

        //LOAD BACKGROUNDS 'DECOR'
        currentScreenState = MENU_SCREEN_STATE;
        img = loadImage(imgPath + props.getProperty(PathXPropertyType.IMAGE_BACKGROUND_SPLASH));
        sT = new SpriteType(BACKGROUND_TYPE);
        sT.addState(MENU_SCREEN_STATE, img);
        img = loadImage(imgPath + props.getProperty(PathXPropertyType.IMAGE_BACKGROUND_LEVEL_SELECT));
        sT.addState(GAME_SCREEN_STATE, img);
        s = new Sprite(sT, 0, 0, 0, 0, MENU_SCREEN_STATE);
        guiDecor.put(BACKGROUND_TYPE, s);
        
        //LOAD BORDER DECOR
        img = loadImageWithColorKey(imgPath + props.getProperty(PathXPropertyType.IMAGE_BACKGROUND_LEVEL_SELECT_BORDER), COLOR_KEY);
        sT = new SpriteType(BORDER_TYPE);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 0, 0, 0, 0, PathXButtonState.INVISIBLE_STATE.toString());
        guiDecor.put(BORDER_TYPE, s);

        //LOAD CURSOR ****FIND SOMETHING LATER**********
        //ADD LEVEL BUTTONS
        // ArrayList<String> levels = props.getPropertyOptionsList(PathXPropertyTyp.LEVEL_OPTIONS);
        //GOING TO NEED AN ARRAY FOR THE LEVEL ICONS HERE
        //GOING TO NEED TO BE ABLE TO POSITION LEVELS SPECIFICALLY ON THE MAP
        //ADD BUTTONS
        //SCROLL CONTROLS HERE
        String northScrollButton = props.getProperty(PathXPropertyType.IMAGE_BUTTON_SCROLL_NORTH);
        sT = new SpriteType(SCROLL_BUTTON_NORTH_TYPE);
        img = loadImageWithColorKey(imgPath + northScrollButton, COLOR_KEY);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        String northScrollButtonMouseover = props.getProperty(PathXPropertyType.IMAGE_BUTTON_SCROLL_NORTH_MOUSEOVER);
        img = loadImageWithColorKey(imgPath + northScrollButtonMouseover, COLOR_KEY);
        sT.addState(PathXButtonState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, SCROLL_BUTTON_NORTH_X, SCROLL_BUTTON_NORTH_Y, 0, 0, PathXButtonState.INVISIBLE_STATE.toString());
        guiButtons.put(SCROLL_BUTTON_NORTH_TYPE, s);

        String eastScrollButton = props.getProperty(PathXPropertyType.IMAGE_BUTTON_SCROLL_EAST);
        sT = new SpriteType(SCROLL_BUTTON_EAST_TYPE);
        img = loadImageWithColorKey(imgPath + eastScrollButton, COLOR_KEY);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        String eastScrollButtonMouseover = props.getProperty(PathXPropertyType.IMAGE_BUTTON_SCROLL_EAST_MOUSEOVER);
        img = loadImageWithColorKey(imgPath + eastScrollButtonMouseover, COLOR_KEY);
        sT.addState(PathXButtonState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, SCROLL_BUTTON_EAST_X, SCROLL_BUTTON_EAST_Y, 0, 0, PathXButtonState.INVISIBLE_STATE.toString());
        guiButtons.put(SCROLL_BUTTON_EAST_TYPE, s);

        String westScrollButton = props.getProperty(PathXPropertyType.IMAGE_BUTTON_SCROLL_WEST);
        sT = new SpriteType(SCROLL_BUTTON_WEST_TYPE);
        img = loadImageWithColorKey(imgPath + westScrollButton, COLOR_KEY);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        String westScrollButtonMouseover = props.getProperty(PathXPropertyType.IMAGE_BUTTON_SCROLL_WEST_MOUSEOVER);
        img = loadImageWithColorKey(imgPath + westScrollButtonMouseover, COLOR_KEY);
        sT.addState(PathXButtonState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, SCROLL_BUTTON_WEST_X, SCROLL_BUTTON_WEST_Y, 0, 0, PathXButtonState.INVISIBLE_STATE.toString());
        guiButtons.put(SCROLL_BUTTON_WEST_TYPE, s);

        String southScrollButton = props.getProperty(PathXPropertyType.IMAGE_BUTTON_SCROLL_SOUTH);
        sT = new SpriteType(SCROLL_BUTTON_SOUTH_TYPE);
        img = loadImageWithColorKey(imgPath + southScrollButton, COLOR_KEY);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        String southScrollButtonMouseover = props.getProperty(PathXPropertyType.IMAGE_BUTTON_SCROLL_SOUTH_MOUSEOVER);
        img = loadImageWithColorKey(imgPath + southScrollButtonMouseover, COLOR_KEY);
        sT.addState(PathXButtonState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, SCROLL_BUTTON_SOUTH_X, SCROLL_BUTTON_SOUTH_Y, 0, 0, PathXButtonState.INVISIBLE_STATE.toString());
        guiButtons.put(SCROLL_BUTTON_SOUTH_TYPE, s);

        //PLAY BUTTON
        String playButton = props.getProperty(PathXPropertyType.IMAGE_BUTTON_PLAY);
        sT = new SpriteType(PLAY_GAME_BUTTON_TYPE);
        img = loadImage(imgPath + playButton);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        //mouseover
        String playButtonMouseover = props.getProperty(PathXPropertyType.IMAGE_BUTTON_PLAY_MOUSEOVER);
        img = loadImage(imgPath + playButtonMouseover);
        sT.addState(PathXButtonState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, PLAY_BUTTON_X, MENU_BUTTON_Y, 0, 0, PathXButtonState.VISIBLE_STATE.toString());
        //ADD IT
        guiButtons.put(PLAY_GAME_BUTTON_TYPE, s);

        //RESET BUTTON
        String resetButton = props.getProperty(PathXPropertyType.IMAGE_BUTTON_RESET);
        sT = new SpriteType(RESET_BUTTON_TYPE);
        img = loadImage(imgPath + resetButton);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        //mouseover
        String resetButtonMouseover = props.getProperty(PathXPropertyType.IMAGE_BUTTON_RESET_MOUSEOVER);
        img = loadImage(imgPath + resetButtonMouseover);
        sT.addState(PathXButtonState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, RESET_BUTTON_X, MENU_BUTTON_Y, 0, 0, PathXButtonState.VISIBLE_STATE.toString());
        //ADD IT
        guiButtons.put(RESET_BUTTON_TYPE, s);

        //SETTINGS BUTTON
        String settingsButton = props.getProperty(PathXPropertyType.IMAGE_BUTTON_SETTINGS);
        sT = new SpriteType(SETTINGS_BUTTON_TYPE);
        img = loadImage(imgPath + settingsButton);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        //mouseover
        String settingsButtonMouseover = props.getProperty(PathXPropertyType.IMAGE_BUTTON_SETTINGS_MOUSEOVER);
        img = loadImage(imgPath + settingsButtonMouseover);
        sT.addState(PathXButtonState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, SETTINGS_BUTTON_X, MENU_BUTTON_Y, 0, 0, PathXButtonState.VISIBLE_STATE.toString());
        //ADD IT
        guiButtons.put(SETTINGS_BUTTON_TYPE, s);

        //HELP BUTTON
        String helpButton = props.getProperty(PathXPropertyType.IMAGE_BUTTON_HELP);
        sT = new SpriteType(HELP_BUTTON_TYPE);
        img = loadImage(imgPath + helpButton);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        //mouseover
        String helpButtonMouseover = props.getProperty(PathXPropertyType.IMAGE_BUTTON_HELP_MOUSEOVER);
        img = loadImage(imgPath + helpButtonMouseover);
        sT.addState(PathXButtonState.MOUSE_OVER_STATE.toString(), img);
        s = new Sprite(sT, HELP_BUTTON_X, MENU_BUTTON_Y, 0, 0, PathXButtonState.VISIBLE_STATE.toString());
        //ADD IT
        guiButtons.put(HELP_BUTTON_TYPE, s);

        //NEED TO DO DECOR ADDITIONS, WHAT WILL BE DECOR?
        //DIALOGS
        Viewport viewport = data.getViewport();
        //LEVEL DIALOG
        String levelDialog = props.getProperty(PathXPropertyType.IMAGE_DIALOG_QUIT);
        sT = new SpriteType(DIALOG_LEVEL_TYPE);
        img = loadImageWithColorKey(imgPath + levelDialog, COLOR_KEY);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        x = (viewport.getScreenWidth() / 2) - (img.getWidth(null) / 2);
        y = (viewport.getScreenHeight() / 2) - (img.getHeight(null) / 2);
        s = new Sprite(sT, x, y, 0, 0, PathXButtonState.INVISIBLE_STATE.toString());
        guiDialogs.put(DIALOG_LEVEL_TYPE, s);

        //STATS DIALOG
        //QUIT DIALOG
        String quitDialog = props.getProperty(PathXPropertyType.IMAGE_DIALOG_QUIT);
        sT = new SpriteType(DIALOG_QUIT_TYPE);
        img = loadImageWithColorKey(imgPath + quitDialog, COLOR_KEY);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        x = (viewport.getScreenWidth() / 2) - (img.getWidth(null) / 2);
        y = (viewport.getScreenHeight() / 2) - (img.getHeight(null) / 2);
        s = new Sprite(sT, x, y, 0, 0, PathXButtonState.INVISIBLE_STATE.toString());
        guiDialogs.put(DIALOG_QUIT_TYPE, s);

        //NEED TO DO A TREE FOR DEM NAVIGATION BUTTONS RIGHT, AND PROBABLY POWERUPS
    }

    @Override
    public void initGUIHandlers() {
        eventHandler = new PathXEventHandler(this);
        //mouseHandler = new PathXMouseHandler(this);
        //powerHandler = new PathXPowerUpHandler(this);

        //custom exit response
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                eventHandler.respondToExitRequest();
            }
        });

        //LEVEL SELECTION HANDLERS
        
        //SCROLL HANDLERS
        guiButtons.get(SCROLL_BUTTON_NORTH_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToScrollNorth();
            }
        });
        
                guiButtons.get(SCROLL_BUTTON_EAST_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToScrollEast();
            }
        });
                
                        guiButtons.get(SCROLL_BUTTON_SOUTH_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToScrollSouth();
            }
        });
                        
                                guiButtons.get(SCROLL_BUTTON_WEST_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToScrollWest();
            }
        });
        //PLAY GAME EVENT HANDLER
        guiButtons.get(PLAY_GAME_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //data.getViewport().scroll(-300, -300);
                eventHandler.respondToPlayGameRequest();
            }
        });

        //RESET BUTTON EVENT HANDLER
        guiButtons.get(RESET_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToResetRequest();
            }
        });

        //SETTINGS BUTTON EVENT HANDLER
        guiButtons.get(SETTINGS_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToSettingsRequest();
            }
        });

        //HELP BUTTON EVENT HANDLER
        guiButtons.get(HELP_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToHelpRequest();
            }
        });

        //KEY LISTENER
        this.setKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                eventHandler.respondToKeyPress(ke.getKeyCode());
            }
        });

    }

    @Override
    public void reset() {
        data.reset(this);
    }

    @Override
    public void updateGUI() {
        // GO THROUGH THE VISIBLE BUTTONS TO TRIGGER MOUSE OVERS
        Iterator<Sprite> buttonsIt = guiButtons.values().iterator();
        while (buttonsIt.hasNext()) {
            Sprite button = buttonsIt.next();

            // ARE WE ENTERING A BUTTON?
            if (button.getState().equals(PathXButtonState.VISIBLE_STATE.toString())) {
                if (button.containsPoint(data.getLastMouseX(), data.getLastMouseY())) {
                    button.setState(PathXButtonState.MOUSE_OVER_STATE.toString());
                }
            } // ARE WE EXITING A BUTTON?
            else if (button.getState().equals(PathXButtonState.MOUSE_OVER_STATE.toString())) {
                if (!button.containsPoint(data.getLastMouseX(), data.getLastMouseY())) {
                    button.setState(PathXButtonState.VISIBLE_STATE.toString());
                }
            }
        }

    }

    void switchToGameScreen() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        //change background
        //THE BACKGROUND NEEDS TO BE A SMALLER VIEWPORT OF A LARGER IMAGE
        guiDecor.get(BACKGROUND_TYPE).setState(GAME_SCREEN_STATE);
        guiDecor.get(BORDER_TYPE).setState(PathXButtonState.VISIBLE_STATE.toString());

        Viewport viewport = data.getViewport();
        int width = guiDecor.get(BACKGROUND_TYPE).getSpriteType().getStateImage(GAME_SCREEN_STATE).getWidth();
        int height = guiDecor.get(BACKGROUND_TYPE).getSpriteType().getStateImage(GAME_SCREEN_STATE).getHeight();
        viewport.setGameWorldSize(width, height);
        viewport.setNorthPanelHeight(LEVEL_SELECT_NORTH_PANEL_HEIGHT);
        viewport.updateViewportBoundaries();

        //DISABLE MAIN MENU BUTTONS
        guiButtons.get(PLAY_GAME_BUTTON_TYPE).setState(PathXButtonState.INVISIBLE_STATE.toString());
        guiButtons.get(PLAY_GAME_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SETTINGS_BUTTON_TYPE).setState(PathXButtonState.INVISIBLE_STATE.toString());
        guiButtons.get(SETTINGS_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RESET_BUTTON_TYPE).setState(PathXButtonState.INVISIBLE_STATE.toString());
        guiButtons.get(RESET_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HELP_BUTTON_TYPE).setState(PathXButtonState.INVISIBLE_STATE.toString());
        guiButtons.get(HELP_BUTTON_TYPE).setEnabled(false);

        //change the current screen state
        currentScreenState = GAME_SCREEN_STATE;

        //ANYTHING ELSE NECESSARY TO DO, DO IT NOW (MAYBE SHOW THE LEVELS?)
        //Need to Activate level control buttons (scroll arrows, home button, etc)
        //which means i need to make those still
        viewport.scroll(0, LEVEL_SELECT_NORTH_PANEL_HEIGHT);
        guiButtons.get(SCROLL_BUTTON_NORTH_TYPE).setState(PathXButtonState.VISIBLE_STATE.toString());
        guiButtons.get(SCROLL_BUTTON_NORTH_TYPE).setEnabled(true);

        guiButtons.get(SCROLL_BUTTON_EAST_TYPE).setState(PathXButtonState.VISIBLE_STATE.toString());
        guiButtons.get(SCROLL_BUTTON_EAST_TYPE).setEnabled(true);

        guiButtons.get(SCROLL_BUTTON_WEST_TYPE).setState(PathXButtonState.VISIBLE_STATE.toString());
        guiButtons.get(SCROLL_BUTTON_WEST_TYPE).setEnabled(true);

        guiButtons.get(SCROLL_BUTTON_SOUTH_TYPE).setState(PathXButtonState.VISIBLE_STATE.toString());
        guiButtons.get(SCROLL_BUTTON_SOUTH_TYPE).setEnabled(true);

    }

    void displayLevelData(PathXLevel level) {

    }

    void displaySettingsOverlay() {

    }

    void displayHelpOverlay() {

    }

    void switchToLevelScreen() {

    }

}
