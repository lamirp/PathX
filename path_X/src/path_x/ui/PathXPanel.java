package path_x.ui;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collection;
import javax.swing.JLabel;
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

    private PathXGame game;

    //DATA
    private PathXDataModel data;

    PathXPanel(PathXGame initGame, PathXDataModel initData) {
        game = initGame;
        data = initData;
    }

    //paint components
    public void paintComponent(Graphics g) {
        try {
            //Lock data to ourselves
            game.beginUsingData();

            // Clear the panel
            super.paintComponent(g);

                                    //RENDER LEVEL ICONS
            //Render the background for current screeen
            renderBackground(g);
            renderLevels(g);
            //render buttons
            renderGUIControls(g);
            //RENDER DIALOGS
            renderDialogs(g);
            //RENDER TEXTS

            //Only render while in game
            if (!data.notStarted()) {
                // game play stuff while IN a level

                //Mouseover
                renderMouseOver();
            }//     
            //debugging
            //renderDebuggingText(g);
        } finally {
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
        // renderSprite(g, bg);

        if (!bg.getState().equals(PathXButtonState.INVISIBLE_STATE.toString())) {
            SpriteType bgST = bg.getSpriteType();
            Image img = bgST.getStateImage(bg.getState());
            g.drawImage(img, data.getViewport().getViewportX(), data.getViewport().getViewportY(), null);
        }
        Sprite border = game.getGUIDecor().get(BORDER_TYPE);
        if (border.getState().equals(DEFAULT_BORDER)) {
            renderSprite(g, border);
        }

    }

    public void renderLevels(Graphics g) {
       // game.getGUIButtons().get(LEVEL_TYPE).setState(PathXButtonState.VISIBLE_STATE.toString());
        //  game.getGUIButtons().get(LEVEL_TYPE).setX(data.getViewport().getViewportX() + LEVEL_ZONE_A_X);
        //  game.getGUIButtons().get(LEVEL_TYPE).setY(data.getViewport().getViewportY() + LEVEL_ZONE_A_Y);
        //renderSprite(g, s);
        Collection<Sprite> levelSprites = game.getGUILevel().values();
        for (Sprite s : levelSprites) {
            if(s.getState().equals(PathXButtonState.VISIBLE_STATE.toString())){
            s.setX(data.getViewport().getViewportX() + LEVEL_ZONE_A_X);
            s.setY(data.getViewport().getViewportY() + LEVEL_ZONE_A_Y);
            renderSprite(g, s);
            }
        }

    }

    public void renderGUIControls(Graphics g) {
        Collection<Sprite> buttonSprites = game.getGUIButtons().values();
        //levels
        for(Sprite s : buttonSprites)   {
            if(s.getSpriteType().getSpriteTypeID() == LEVEL_TYPE && !s.getState().equals(PathXButtonState.INVISIBLE_STATE.toString()))
            {
                renderSprite(g, s);
            }
        }
        
        //decor shit
        Collection<Sprite> decorSprites = game.getGUIDecor().values();
        for (Sprite s : decorSprites) {
            if (s.getSpriteType().getSpriteTypeID() != BACKGROUND_TYPE) {
                renderSprite(g, s);
            }
        }
        
        //buttons
        for (Sprite s : buttonSprites) {
            if(s.getSpriteType().getSpriteTypeID() != LEVEL_TYPE) {
            renderSprite(g, s);
            }
        }

    }

    public void renderQuitDialog(Graphics g) {
        if (((PathXGame) game).isCurrentScreenState(GAME_SCREEN_STATE)
                && data.inProgress() || data.isPaused()) {
            //important stuff
        }

        //WHEN THE QUIT DIALOG IS VISIBLE RENDER QUIT TEXT/BUTTONS TO IT
        if (game.getGUIDialogs().get(DIALOG_QUIT_TYPE).getState().equals(PathXButtonState.VISIBLE_STATE.toString())) {
            g.setFont(FONT_GAME_TYPE_TEXT);
            g.setColor(COLOR_TEXT_DISPLAY);

            //Get the Quit prompt text
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String quitPrompt = props.getProperty(PathXPropertyType.TEXT_PROMPT_EXIT);

            g.drawString(quitPrompt, QUIT_TEXT_X, QUIT_TEXT_Y);

        }
    }

    public void renderDialogs(Graphics g) {
        Collection<Sprite> dialogSprites = game.getGUIDialogs().values();
        for (Sprite s : dialogSprites) {
            renderSprite(g, s);
        }
    }

    public void renderSprite(Graphics g, Sprite s) {
        if (!s.getState().equals(PathXButtonState.INVISIBLE_STATE.toString())) {
            SpriteType bgST = s.getSpriteType();
            Image img = bgST.getStateImage(s.getState());
            g.drawImage(img, (int) s.getX(), (int) s.getY(), bgST.getWidth(), bgST.getHeight(), null);
        }
    }

    public static void drawStringMultiLine(Graphics g, String text, int lineWidth, int x, int y) {
        FontMetrics m = g.getFontMetrics();
        if (m.stringWidth(text) < lineWidth) {
            g.drawString(text, x, y);
        } else {
            String[] words = text.split(" ");
            String currentLine = words[0];
            for (int i = 1; i < words.length; i++) {
                if (m.stringWidth(currentLine + words[i]) < lineWidth) {
                    currentLine += " " + words[i];
                } else {
                    g.drawString(currentLine, x, y);
                    y += m.getHeight();
                    currentLine = words[i];
                }
            }
            if (currentLine.trim().length() > 0) {
                g.drawString(currentLine, x, y);
            }
        }
    }

}
