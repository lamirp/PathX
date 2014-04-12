
package path_x.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Collection;
import javax.swing.JPanel;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import path_x.data.PathXDataModel;
import pathx.PathX.PathXPropertyType;
import static pathx.PathX.PathXPropertyType.TEXT_PROMPT_EXIT;
import static pathx.PathXConstants.BACKGROUND_TYPE;
import static pathx.PathXConstants.DIALOG_QUIT_TYPE;
import static pathx.PathXConstants.*;
import properties_manager.PropertiesManager;

/**
 *
 * @author prima_000
 */
public class PathXPanel extends JPanel {
    //This class is the "bulky" gui stuff, the actual rendering.
    
    private MiniGame game;
    
    //DATA
    private PathXDataModel data;
    
    PathXPanel(PathXGame initGame, PathXDataModel initData) {
        game = initGame;
        data = initData;
    }
    
    //paint components
    public void paintComponent(Graphics g)
    {
        try {
            //Lock data to ourselves
            game.beginUsingData();
            
            // Clear the panel
            super.paintComponent(g);
            
            //Render the background for current screeen
            renderBackground(g);
            //render buttons
            renderGUIControls(g);
            
            //Only render while in game
            if(!data.notStarted())
            {
                // game play stuff while IN a level
                
             //Mouseover
            renderMouseOver();
            }//     
            //debugging
            //renderDebuggingText(g);
        }
        finally {
           //release lock
            game.endUsingData();
        }
    }//paintComponent
    
    //RENDERING METHODS
        //renderBackground
        //renderGUIControls
    //renderDialogs
    //renderDebuggingText
    //renderMouseOver
    
    public void renderMouseOver() {
        //trigger mouse over events for in game play
    }
    
    public void renderBackground(Graphics g) {
        Sprite bg = game.getGUIDecor().get(BACKGROUND_TYPE);
        renderSprite(g, bg);
    }
    
    public void renderGUIControls(Graphics g) {
        Collection<Sprite> decorSprites = game.getGUIDecor().values();
        for(Sprite s : decorSprites)
        {
            if(s.getSpriteType().getSpriteTypeID() != BACKGROUND_TYPE)
                renderSprite(g, s);
        }
        
        //buttons
        Collection<Sprite> buttonSprites = game.getGUIButtons().values();
        for(Sprite s : buttonSprites)
        {
            renderSprite(g, s);
        }
    }
    
    public void renderQuitDialog(Graphics g)
    {
        if(((PathXGame)game).isCurrentScreenState(GAME_SCREEN_STATE)
                && data.inProgress() || data.isPaused())
        {
            //important stuff
        }
        
        //WHEN THE QUIT DIALOG IS VISIBLE RENDER QUIT TEXT/BUTTONS TO IT
        if(game.getGUIDialogs().get(DIALOG_QUIT_TYPE).getState().equals(PathXButtonState.VISIBLE_STATE.toString()))
        {
            g.setFont(FONT_GAME_TYPE_TEXT);
            g.setColor(COLOR_TEXT_DISPLAY);
            
            //Get the Quit prompt text
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String quitPrompt = props.getProperty(PathXPropertyType.TEXT_PROMPT_EXIT);
            
            g.drawString(quitPrompt,    QUIT_TEXT_X, QUIT_TEXT_Y);

        }
    }
    
    public void renderDialogs(Graphics g)
    {
        Collection<Sprite> dialogSprites = game.getGUIDialogs().values();
        for(Sprite s : dialogSprites)
        {
            renderSprite(g, s);
        }
    }
    
    public void renderSprite(Graphics g, Sprite s)
    {
        if(!s.getState().equals(PathXButtonState.INVISIBLE_STATE.toString()))
        {
            SpriteType bgST = s.getSpriteType();
            Image img = bgST.getStateImage(s.getState());
            g.drawImage(img, (int)s.getX(), (int)s.getY(), bgST.getWidth(), bgST.getHeight(), null);
        }
    }
    
}
