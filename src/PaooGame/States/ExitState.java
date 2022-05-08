package PaooGame.States;

import PaooGame.RefLinks;
import PaooGame.Score.Score;
import PaooGame.Score.ScoreDataBase;

import java.awt.*;

public class ExitState extends State {


    /*! \fn public ExitState(RefLinks refLink)
    \brief Constructor

    \param refLink Reference to other references
 */
    public ExitState(RefLinks refLinks){
        super(refLinks);
    }

    /*! \fn public void Update()
        \brief Updates the current state of the game
     */
    @Override
    public void Update() {
     //   if(State.previousState == refLink.GetGame().getGameOverState()) {
            ScoreDataBase.getInstance().createGameEntry(Score.points);
            refLink.GetGame().db.printToFile();
            refLink.GetMap().getScore().resetPoints();

        refLink.GetGame().getGameWindow().GetWndFrame().setVisible(false);
        refLink.GetGame().getGameWindow().GetWndFrame().dispose(); //Destroy the JFrame object
        refLink.GetGame().StopGame();
  //      System.exit(0);
    }

    /*! \fn public void Draw(Graphics g)
    \brief  Draws the current state

    \param g The graphic context in which the player will be drawn.
 */
    @Override
    public void Draw(Graphics g) {

    }
}
