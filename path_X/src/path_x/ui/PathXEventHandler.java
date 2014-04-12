/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package path_x.ui;

import path_x.data.PathXDataModel;
import path_x.data.PathXLevel;
import path_x.file.PathXFileManager;
import static pathx.PathXConstants.*;

/**
 *
 * @author prima_000
 */
public class PathXEventHandler {
    
    //PathXGame
    private PathXGame game;
    
    /**
     * Constructor
     */
    public PathXEventHandler(PathXGame initGame) {
        game = initGame;
    }//constructor
    
    /**
     * Respond to exit request when users wishes to exit
     */
    public void respondToExitRequest() {
        if(game.getDataModel().inProgress()) {
            game.getDataModel().endGameAsLoss();
        }
        //Close it all up
        System.exit(0);
    }//respond to exit
    
    /**
     * Respond to play game
     */
    public void respondToPlayGameRequest() {
        //start audio if necessary
        //bring up the select level screen
        game.switchToLevelScreen();
        
    }//respond to play
    
    /**
     * Response to select level request
     */
    public void respondToSelectLevelRequest(String levelFile) {
        //Only if the select level screen is visible
        if(game.isCurrentScreenState(LEVEL_SCREEN_STATE)) {
            PathXDataModel data = (PathXDataModel) game.getDataModel();
            
            //update
            PathXFileManager fileManager = game.getFileManager();
            fileManager.loadLevel(levelFile);
            data.reset(game);
            //go to game
            game.switchToGameScreen();
        }
    }//Respong to select level request
    
    /**
     * Respond to display level dialog overlay
     */
    public void respondToLevelOverlay(PathXLevel level) {
        //display the level information
        game.displayLevelData(level);
    }
    
    /**
     * Respond to Settings Request
     */
    public void respondToSettingsRequest() {
        game.displaySettingsOverlay();
    }
    
    /**
     * Respond to Help Request
     */
    public void respondToHelpRequest() {
        System.out.println("boner");
        game.displayHelpOverlay();
    }
    
    //MUST DO RESPOND TO KEYPRESS STUFF BELOW HERE

    void respondToResetRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void respondToKeyPress(int keyCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
