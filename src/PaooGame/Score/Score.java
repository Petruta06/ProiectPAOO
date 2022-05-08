package PaooGame.Score;


import PaooGame.RefLinks;

import java.awt.*;

/*! \public class Score
    \brief Implements the score notion
 */
public class Score {

    public static int points = 0;     /*!< The points.*/
    private RefLinks refLink;    /*!< Reference to a RefLink object.*/

    protected int x;         /*!<The x coordinate.*/
    protected int y;         /*!<The y coordinate.*/

    private int highscore;


    /*! \fn public Score(RefLinks refLink)
       \brief Constructor

       \param refLink The RefLink object
    */
    public Score(RefLinks refLink){
        this.refLink = refLink;
        highscore = refLink.GetGame().db.getHighScore();

    }


    public void Update(){

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
     \brief Draws the score and the life in the window

     \param g    The graphic context in which the score will be drawn.
  */
    public void Draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.gray);
        g2d.fillRect(0,0, 170, 70);
        g2d.setColor(Color.BLUE);
        g2d.drawRect(0,0, 170, 70);
        g2d.setColor(Color.pink);
        g2d.drawString( "LIFE: " + refLink.GetMap().getItemManager().getHero().getLife(), 15, 15);
        g.setColor(Color.cyan);
        g2d.drawString("SCORE: "+points, 15,35);
        g.setColor(Color.GREEN);
        g2d.drawString("HIGHEST SCORE: "+highscore, 15, 55);

    }

    /*! \fn public void addPoints(int i)
 \brief Adds points

 \param i the amount of points
*/
    public void addPoints(int i){
        points += i;
    }

    /*! \fn public void getPoints()
\brief Gets the points

*/
    public int getPoints() {
        return points;
    }

    /*! \fn public void resetPoints()
\brief Resets the points

*/
    public void resetPoints(){
        this.points = 0;
    }
}
