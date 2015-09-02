/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package path_x.data;

import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import path_x.ui.PathXButtonState;
import static pathx.PathXConstants.DIALOG_HELP_TYPE;

/**
 *
 * @author prima_000
 */
public class PathXDataModel extends MiniGameDataModel{
    
    //Referende mini game so can update display
    private MiniGame miniGame;
    
    //enemy storage
    
    //level
    private String currentLevel;
    
    public PathXDataModel(MiniGame initMiniGame) {
        //store it
        miniGame = initMiniGame;
        
        //initialize some gameplay stuff tiles/enemies/powerups
    }//constructor
    
    public String getCurrentLevel() {   return currentLevel;    }
    
    public void setCurrentLevel(String initCurrentLevel) {  currentLevel = initCurrentLevel;    }
    
    /**
     * Ask if game is at level select
     * @param game
     * @param x
     * @param y 
     */
    
    //init methods
    // initLevel

    @Override
    public void checkMousePressOnSprites(MiniGame game, int x, int y) {
       if(game.getGUIDialogs().get(DIALOG_HELP_TYPE).getState().equals(PathXButtonState.VISIBLE_STATE.toString()))
       {
           game.getGUIDialogs().get(DIALOG_HELP_TYPE).setState(PathXButtonState.INVISIBLE_STATE.toString());
           return;
       }
    }

    @Override
    public void reset(MiniGame game) {
        
    }

    @Override
    public void updateAll(MiniGame game) {
        
    }

    @Override
    public void updateDebugText(MiniGame game) {
        
    }
    
}
