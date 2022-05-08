package PaooGame.States;

import PaooGame.Graphics.Background;
import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{
    private Background bg;

    private int currentChoice = 0;
    private String[] options = {
            "Start",
            "About",
  //          "Settings",
            "Quit"
    };

    private Color titleColor;
    private Font titleFont;

    private Font font;

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);
        try {

            bg = new Background(ImageLoader.LoadImage("/textures/Background.png"));
 //           bg.setVector(-0.1, 0);

            titleColor = new Color(128, 0, 0);
            titleFont = new Font(
                    "Century Gothic",
                    Font.PLAIN,
                    45);

            font = new Font("Arial", Font.PLAIN, 28);

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    private void select() {
        if(currentChoice == 0) {
            SetState(new PlayState(refLink));

        }
        if(currentChoice == 1) {

            SetState(new AboutState(refLink));
        }
  //      if(currentChoice == 2) {
   //         SetState(new SettingsState(refLink));
    //    }
        if(currentChoice == 2){

            System.exit(0);
        }
    }
    @Override
    public void Update(){
        bg.update();
        ///Verificare apasare tasta "jos"
        if (refLink.GetKeyManager().keyJustPressed(KeyEvent.VK_UP) ){

            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        ///Verificare apasare tasta "up"
        if (refLink.GetKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }

        ///Verificare apasare tasta "ENTER"
        if(refLink.GetKeyManager().enter)
        {
            select();
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {


        // draw bg
        bg.draw(g);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Jeff's Story", refLink.GetWidth()/3, refLink.GetHeight()/3);

        // draw menu options
        g.setFont(font);

        for(int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
                g.setColor(Color.BLACK);
            }
            else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], refLink.GetWidth()/3 +60, refLink.GetHeight()/2 + i * 30);
        }



    }
}
