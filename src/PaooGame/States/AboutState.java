package PaooGame.States;

import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class AboutState extends State
    \brief Implements the about game notion
 */
public class AboutState extends State
{

    BufferedImage background;
    String i1, i2, i3, i4, i5, i6, i7, i8, i9, i10,i11;
    /*! \fn public AboutState(RefLinks refLink)
        \brief Constructor

        \param refLink Reference to other references
     */
    public AboutState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        background = ImageLoader.LoadImage("/textures/bout.jpg");
        i1 = "Use Keys \"up\", \"down\", \"right\", \"left\" to move the player in the direction you want.";
        i2 = "Use the \"A\" , \"S\", \"D\", \"W\" keys for eating, petting and attacking.";
        i3 = "There are 2 types of food in the game: ";
        i4 = "- Peaches: adds one life and 200 points";
        i5 = "- Potion: adds 150 points and speeds you up";
        i7 = "You may find something in a chest. They sometimes are guarded by black cats. Don't be scared.";
        i8 = "Killing an ogre grants 700 points, but be careful, they fight back.\n" ;
        i9 = "Getting to the dragon and slaying it means finishing the level and grants you another 1500 points.";
        i10 = "You win the game if you kill the djin. Out of respect, he will grant you a wish. Use it wisely.";
        i11 = "Press ESC to return to menu";

    }
    /*! \fn public void Update()
        \brief Updates the current state of the game
     */
    @Override
    public void Update()
    {
        if(refLink.GetKeyManager().esc){
            State.SetState(refLink.GetGame().getMenuState());
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief  Draws the current state

        \param g The graphic context in which the player will be drawn.
     */
    @Override
    public void Draw(Graphics g)
    {

        g.drawImage(background.getScaledInstance(refLink.GetWidth() ,refLink.GetHeight(),0), 0, 0, null);

  //      g.fillRect(0,0, (int)refLink.GetGame().GetWidth(), (int)refLink.GetGame().GetHeight());
        g.setColor(Color.black);
        g.drawString(i1, 100, 100);
        g.drawString(i2, 100, 125);
        g.drawString(i3, 100, 150);
        g.drawString(i4, 150, 175);
        g.drawString(i5, 150, 200);
    //    g.drawString(i6, 150, 225);
        g.drawString(i7, 100, 250);
        g.drawString(i8, 100, 275);
        g.drawString(i9,100,300);

        g.drawString(i10, 100, 350);
        g.setColor(Color.RED);
        g.drawString(i11,100,375);



    }
}
