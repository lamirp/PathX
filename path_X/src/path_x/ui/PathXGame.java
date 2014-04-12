package path_x.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
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
    public PathXFileManager getFileManager() {  return fileManager; }
    public PathXErrorHandler getErrorHandler() {    return errorHandler; }
    //public PathXRecord getPlayerRecord() {  return record;  }
    
    /**
     * Test Method to see is screen state is what it should be
     */
    public boolean isCurrentScreenState(String testScreenState) {
        
        return testScreenState.equals(currentScreenState);
    }
    
    //NEED DISPLAY UPDATE METHODS AKA VIEWPORT AS DISCUSSED IN LECTURE
    /**
     * initViewport
     * scroll
     * moveVieport
     * 
     * displaystates
     * savePlayerRecord
     * switchToLevelScreen  LEVEL SELECT
     * switchToSplashScreen HOME SCREEN
     * switchToGameScreen  GAMEPLAY SCREEN
     * displayLevelData
     * displayHelpOverlay
     * displaySettingsOverlay
     * 
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
        guiButtons.get(HELP_BUTTON_TYPE).setState(PathXButtonState.VISIBLE_STATE.toString());
        
        
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
                System.out.println(windowIconFile);
        img = loadImage(imgPath + windowIconFile);
        window.setIconImage(img);
        
//construct panel        
        
        //LOAD BACKGROUNDS 'DECOR'
        currentScreenState = MENU_SCREEN_STATE;
        img = loadImage(imgPath + props.getProperty(PathXPropertyType.IMAGE_BACKGROUND_SPLASH));
        sT = new SpriteType(BACKGROUND_TYPE);
        sT.addState(MENU_SCREEN_STATE, img);
        img = loadImage(imgPath + props.getProperty(PathXPropertyType.IMAGE_BACKGROUND_LEVEL_SELECT));
        sT.addState(GAME_SCREEN_STATE, img);
        s = new Sprite(sT, 0, 0, 0, 0, MENU_SCREEN_STATE);
        guiDecor.put(BACKGROUND_TYPE, s);
        
        //LOAD CURSOR ****FIND SOMETHING LATER**********
        
        //ADD LEVEL BUTTONS
       // ArrayList<String> levels = props.getPropertyOptionsList(PathXPropertyTyp.LEVEL_OPTIONS);
        //GOING TO NEED AN ARRAY FOR THE LEVEL ICONS HERE
        //GOING TO NEED TO BE ABLE TO POSITION LEVELS SPECIFICALLY ON THE MAP
        
        //ADD BUTTONS
        //PLAY BUTTON
        String playButton = props.getProperty(PathXPropertyType.IMAGE_BUTTON_PLAY);
        sT = new SpriteType(PLAY_GAME_BUTTON_TYPE);
        img = loadImage(imgPath + playButton);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        //mouseover
        //ADD IT
        guiButtons.put(PLAY_GAME_BUTTON_TYPE, s);
        
        //RESET BUTTON
        String resetButton = props.getProperty(PathXPropertyType.IMAGE_BUTTON_RESET);
        sT = new SpriteType(RESET_BUTTON_TYPE);
        img = loadImage(imgPath + resetButton);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        //mouseover
        //ADD IT
        guiButtons.put(RESET_BUTTON_TYPE, s);
        
        //SETTINGS BUTTON
        String settingsButton = props.getProperty(PathXPropertyType.IMAGE_BUTTON_SETTINGS);
        sT = new SpriteType(SETTINGS_BUTTON_TYPE);
        img = loadImage(imgPath + settingsButton);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        //mouseover
        //ADD IT
        guiButtons.put(SETTINGS_BUTTON_TYPE, s);
        
        //HELP BUTTON
        String helpButton = props.getProperty(PathXPropertyType.IMAGE_BUTTON_HELP);
        sT = new SpriteType(HELP_BUTTON_TYPE);
        img = loadImage(imgPath + settingsButton);
        sT.addState(PathXButtonState.VISIBLE_STATE.toString(), img);
        //mouseover
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
        
        //PLAY GAME EVENT HANDLER
        guiButtons.get(PLAY_GAME_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
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
        guiButtons.get(HELP_BUTTON_TYPE).setActionListener(new ActionListener(){
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
        //Mouse overs will go in here, need to mouse over for buttons.
    }

    void switchToGameScreen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void displayLevelData(PathXLevel level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void displaySettingsOverlay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void displayHelpOverlay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void switchToLevelScreen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
