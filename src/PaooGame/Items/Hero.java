package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import PaooGame.Graphics.Animation;
//import PaooGame.Inventory.Inventory;
import PaooGame.Maps.Observer;
import PaooGame.Maps.Subject;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Score.ScoreDataBase;
import PaooGame.States.State;

import javax.security.auth.login.AccountNotFoundException;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character implements Subject
{
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private Animation animRight;
    private Animation animIdle;
    private Animation animLeft;
    private Animation animUp;
    private Animation animDown;

    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

//    private Inventory inventory;

    ArrayList<Observer> observerList;

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y)
    {
            ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
            ///Seteaza imaginea de start a eroului


            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 14;
        normalBounds.y = 10;
        normalBounds.width = 10;
        normalBounds.height = 25;

            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 0;
        attackBounds.y = 7;
        attackBounds.width = 38;
        attackBounds.height = 38;

        animRight = new Animation(250,Assets.heroRight);
        animIdle = new Animation (500,Assets.idleHero);
        animDown = new Animation(250, Assets.heroDown);
        animUp   = new Animation(250, Assets.heroUp);
        animLeft = new Animation(250, Assets.heroLeft);

        //inventory = new Inventory(refLink);

        ///Builds the observer list
        observerList = new ArrayList<Observer>();
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {

            ///Verifica daca a fost apasata o tasta
        GetInput();
            ///Actualizeaza pozitia
        Move();
            ///Actualizeaza imaginea
        animRight.Update();
        animIdle.Update();
        animDown.Update();
        animLeft.Update();
        animUp.Update();
        if( refLink.GetKeyManager().space == true)


        if(refLink.GetKeyManager().right == true) {
         //       image = Assets.heroRight;


        //inventory.Update();

        }
        refLink.GetGame().getCamera().centeredOnItem(this);
        checkAttack();
    }

    private void checkAttack(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if(refLink.GetKeyManager().w){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        }else if(refLink.GetKeyManager().s){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }else if(refLink.GetKeyManager().a){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }else if(refLink.GetKeyManager().d){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }else{
            return;
        }

        attackTimer = 0;

        for(Item i: refLink.GetMap().getItemManager().getItems()){
            if(i.equals(this))
                continue;
            if(i.getCollisionBounds(0,0).intersects(ar)){
                i.hurt(3);
                return;
            }

        }
    }
    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
            ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
            ///Verificare apasare tasta "sus"
        if(refLink.GetKeyManager().up)
        {
            yMove = -speed;
        }
            ///Verificare apasare tasta "jos"
        if(refLink.GetKeyManager().down)
        {
            yMove = speed;
        }
            ///Verificare apasare tasta "left"
        if(refLink.GetKeyManager().left)
        {
            xMove = -speed;
        }
            ///Verificare apasare tasta "dreapta"
        if(refLink.GetKeyManager().right)
        {
            xMove = speed;
        }
    }

    public void die(){
        System.out.println("Loser");
        ///Resets points
    //    refLink.GetMap().getScore().resetPoints();
        ScoreDataBase.getInstance().createGameEntry(refLink.GetMap().getScore().getPoints());
        ///Sets the current state
        State.SetState(refLink.GetGame().getGameOverState());
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(currntAnimFrame(), (int) (x - refLink.getCamera().getxOffset()), (int) (y - refLink.getCamera().getyOffset()), width, height, null);
        //inventory.Draw(g);
            ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua lini
  //      g.setColor(Color.blue);
  //      g.fillRect((int)(x + attackBounds.x - refLink.getCamera().getxOffset()) , (int)(y + attackBounds.y - refLink.getCamera().getyOffset()), attackBounds.width, attackBounds.height);

    }

    private BufferedImage currntAnimFrame(){
        if(xMove > 0)
            return animRight.getCurrntFrame();
        else if (xMove < 0)
            return animLeft.getCurrntFrame();
        else if( yMove > 0)
            return animDown.getCurrntFrame();
        else if( yMove < 0)
            return animUp.getCurrntFrame();
        else
            return animIdle.getCurrntFrame();
    }

   // public Inventory getInventory() {
     //   return inventory;
   // }

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers() {

        for (Observer o : observerList) {
            o.updateObserver(life);
        }
    }
}
