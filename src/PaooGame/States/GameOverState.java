package PaooGame.States;

import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;
import PaooGame.Score.Score;
import PaooGame.Score.ScoreDataBase;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class SettingsState extends State
    \brief Implements the game over notion.
 */
public class GameOverState extends State {

    private BufferedImage background;       /*!< Reference to BufferedImage object for background.*/
    private BufferedImage text;             /*!< Reference to BufferedImage object for text.*/

    /*! \fn public GameOverState(RefLinks refLink)
    \brief Constructor

    \param refLink Reference to other references
 */
    public GameOverState(RefLinks refLink){
        super(refLink);

        background = ImageLoader.LoadImage("/textures/sad.jpg");
        text = ImageLoader.LoadImage("/textures/game_over.png");

    }

    /*! \fn public void Update()
    \brief Updates the current state of the game
 */
    @Override
    public void Update() {

        if(refLink.GetKeyManager().esc){
            State.SetState(refLink.GetGame().exitState);
   //         State.SetState(refLink.GetGame().getMenuState());
        }
    }

    /*! \fn public void Draw(Graphics g)
\brief  Draws the current state

\param g The graphic context in which the player will be drawn.
*/
    @Override
    public void Draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(text, 100, 150,256, 256,  null);

        g.setColor(Color.BLUE);
        g.drawString("Score: " + refLink.GetMap().getScore().getPoints(),100, 400 );
        g.drawString("Press ESC", 100, 420);

    }
}