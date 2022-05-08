package PaooGame.States;

import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WinningState extends State {
    private BufferedImage background;       /*!< Reference to BufferedImage object for background.*/
    private BufferedImage text;             /*!< Reference to BufferedImage object for text.*/

    /*! \fn public GameOverState(RefLinks refLink)
    \brief Constructor

    \param refLink Reference to other references
 */
    public WinningState(RefLinks refLink){
        super(refLink);

        background = ImageLoader.LoadImage("/textures/about.jpg");
//        text = ImageLoader.LoadImage("/textures/game_over.png");

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
        g.drawImage(background.getScaledInstance(refLink.GetWidth() ,refLink.GetHeight(),0), 0, 0, null);
 //       g.drawImage(text, 100, 150,256, 256,  null);

        g.setColor(Color.BLUE);
        g.setFont(new Font("default", Font.BOLD,16));
        g.drawString("Louise: Look guys! He's back! Did you find my bracelet?",100,90);
        g.drawString("Score: " + refLink.GetMap().getScore().getPoints(),100, 400 );
        g.drawString("Press ESC", 100, 420);

    }
}
