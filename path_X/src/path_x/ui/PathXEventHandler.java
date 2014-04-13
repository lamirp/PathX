/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package path_x.ui;

import java.awt.event.KeyEvent;
import mini_game.Viewport;
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
        if (game.getDataModel().inProgress()) {
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
        game.switchToGameScreen();

    }//respond to play

    /**
     * Response to select level request
     */
    public void respondToSelectLevelRequest(String levelFile) {
        //Only if the select level screen is visible
        if (game.isCurrentScreenState(LEVEL_SCREEN_STATE)) {
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
        System.out.println("Response to settings request");
        game.displaySettingsOverlay();
    }

    /**
     * Respond to Help Request
     */
    public void respondToHelpRequest() {
        System.out.println("Response to help request");
        game.displayHelpOverlay();
    }

    //MUST DO RESPOND TO KEYPRESS STUFF BELOW HERE
    void respondToResetRequest() {
        System.out.println("Response to reset request");
    }

    void respondToKeyPress(int keyCode) {
        PathXDataModel data = (PathXDataModel) game.getDataModel();

        //UP DOWN LEFT RIGHT SCROLL KEYS
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            //CAN ONLY DO THIS IF WE'RE IN THE GAME SCREEN
            if (game.isCurrentScreenState(GAME_SCREEN_STATE)) {
                respondToScrollNorth();
            }
        }

        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            if (game.isCurrentScreenState(GAME_SCREEN_STATE)) {
                respondToScrollSouth();
            }
        }

        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_E) {
            if (game.isCurrentScreenState(GAME_SCREEN_STATE)) {
                respondToScrollEast();
            }
        }

        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            if (game.isCurrentScreenState(GAME_SCREEN_STATE)) {
                respondToScrollWest();
            }
        }

    }

    void respondToScrollSouth() {
        //FETCH VIEWPORT
        Viewport viewport = game.getDataModel().getViewport();
        //FIGURE OUT HOW MUCH ROOM WE'RE ALLOWED TO MOVE, AND MAKE IT GENERIC IN THE SENSE THAT LEVELS WILL HAVE VARYING SIZES
        int availableScrollSpace = viewport.getGameWorldHeight() - viewport.getScreenHeight();
        //SOME STUPID MATH THAT I PROBABLY OVER COMPLICATED
        if (viewport.getViewportY() * -1 + VIEWPORT_SCROLL_INC < availableScrollSpace) {
            viewport.scroll(0, -VIEWPORT_SCROLL_INC);
        } else {
            int difference = availableScrollSpace - (viewport.getViewportY() * -1);
            viewport.scroll(0, -difference);
        }
    }

    void respondToScrollNorth() {
        //FETCH VIEWPORT
        Viewport viewport = game.getDataModel().getViewport();
        //FIGURE OUT HOW MUCH ROOM WE'RE ALLOWED TO MOVE, AND MAKE IT GENERIC IN THE SENSE THAT LEVELS WILL HAVE VARYING SIZES
        int availableScrollSpace = viewport.getGameWorldHeight() - viewport.getScreenHeight();
        //SOME STUPID MATH THAT I PROBABLY OVER COMPLICATED
        if (viewport.getViewportY() + VIEWPORT_SCROLL_INC < LEVEL_SELECT_NORTH_PANEL_HEIGHT) {

            viewport.scroll(0, +VIEWPORT_SCROLL_INC);
        }
    }

    void respondToScrollWest() {
        //FETCH VIEWPORT
        Viewport viewport = game.getDataModel().getViewport();
        //FIGURE OUT HOW MUCH ROOM WE'RE ALLOWED TO MOVE, AND MAKE IT GENERIC IN THE SENSE THAT LEVELS WILL HAVE VARYING SIZES
        int availableScrollSpace = viewport.getGameWorldWidth() - viewport.getScreenWidth();
        //SOME STUPID MATH THAT I PROBABLY OVER COMPLICATED
        if (viewport.getViewportX() + VIEWPORT_SCROLL_INC < 0) {

            viewport.scroll(+VIEWPORT_SCROLL_INC, 0);
        }
    }

    void respondToScrollEast() {
        //FETCH VIEWPORT
        Viewport viewport = game.getDataModel().getViewport();
        //FIGURE OUT HOW MUCH ROOM WE'RE ALLOWED TO MOVE, AND MAKE IT GENERIC IN THE SENSE THAT LEVELS WILL HAVE VARYING SIZES
        int availableScrollSpace = viewport.getGameWorldWidth() - viewport.getScreenWidth();
        //SOME STUPID MATH THAT I PROBABLY OVER COMPLICATED
        if (viewport.getViewportX() * -1 + VIEWPORT_SCROLL_INC < availableScrollSpace) {
            viewport.scroll(-VIEWPORT_SCROLL_INC, 0);
        } else {
            int difference = availableScrollSpace - (viewport.getViewportX() * -1);
            viewport.scroll(-difference, 0);
        }
    }

}
