/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package path_x.data;

import mini_game.MiniGame;
import mini_game.MiniGameDataModel;

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
    
    //init methods
    // initLevel

    @Override
    public void checkMousePressOnSprites(MiniGame game, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reset(MiniGame game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAll(MiniGame game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDebugText(MiniGame game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
