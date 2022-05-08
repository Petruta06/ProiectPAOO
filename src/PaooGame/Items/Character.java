package PaooGame.Items;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

/*! \class public abstract class Character extends Item
    \brief Defineste notiunea abstracta de caracter/individ/fiinta din joc.

    Notiunea este definita doar de viata, viteza de deplasare si distanta cu care trebuie sa se
    miste/deplaseze in urma calculelor.
 */
public abstract class Character extends Item
{

    public static final float DEFAULT_SPEED         = 0.5f; /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH  = 40;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 40;   /*!< Inaltimea implicita a imaginii caracterului.*/


    protected float speed;  /*!< Retine viteza de deplasare caracterului.*/
    protected float xMove;  /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;  /*!< Retine noua pozitie a caracterului pe axa Y.*/

    /*! \fn public Character(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei Character

        \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
        \param x Pozitia de start pa axa X a caracterului.
        \param y Pozitia de start pa axa Y a caracterului.
        \param width Latimea imaginii caracterului.
        \param height Inaltimea imaginii caracterului.
     */
    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
            ///Apel constructor la clasei de baza
        super(refLink, x,y, width, height);
            //Seteaza pe valorile implicite pentru viata, viteza si distantele de deplasare

        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
    }

    /*! \fn public void Move()
        \brief Modifica pozitia caracterului
     */
    public void Move()
    {
            ///Modifica pozitia caracterului pe axa X.
            ///Modifica pozitia caracterului pe axa Y.
            if (!checkEntityCollisions(xMove, 0f))
                MoveX();
            if (!checkEntityCollisions(0f, yMove))
                MoveY();

    }

    /*! \fn public void MoveX()
        \brief Modifica pozitia caracterului pe axa X.
     */
    public void MoveX()
    {
            if(xMove > 0){//Se misca la dreapta.
                int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
                if(!collisionTile(tx,(int) (y + bounds.y)/Tile.TILE_HEIGHT) &&
                        !collisionTile(tx,(int) (y + bounds.y + bounds.height ) / Tile.TILE_HEIGHT)){
                   x += xMove;
                }
                else x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
            else if(xMove < 0 ){ //Se misca la stanga.
                int tx = (int) (x + xMove +bounds.x) / Tile.TILE_WIDTH;
                if(!collisionTile(tx,(int) (y+bounds.y)/Tile.TILE_HEIGHT) &&
                        !collisionTile(tx,(int) (y+ bounds.y + bounds.height ) / Tile.TILE_HEIGHT)){
                    x += xMove;
                }
                else{
                    x = tx * Tile.TILE_WIDTH + Tile.TILE_HEIGHT - bounds.x;
                }
            }
    }

    /*! \fn public void MoveY()
        \brief Modifica pozitia caracterului pe axa Y.
     */
    public void MoveY()
    {
        if(yMove > 0){//Se misca in jos.
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            if(!collisionTile((int) (x + bounds.x) / Tile.TILE_WIDTH,ty) &&
                    !collisionTile((int) (x + bounds.x + bounds.width ) / Tile.TILE_WIDTH,ty)){
                y += yMove;
            }
            else{

                y = ty*Tile.TILE_HEIGHT - bounds.y - bounds.height-1;

            }
        }
        else if(yMove < 0 ){ //Se misca in sus.
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;

            if(!collisionTile((int) (x + bounds.x)/Tile.TILE_WIDTH,ty) &&
                    !collisionTile((int) (x + bounds.x + bounds.width ) / Tile.TILE_WIDTH,ty)){
                y += yMove;
            }
            else{
                y = ty*Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }

        }
    }

    protected boolean collisionTile(int x, int y){
        return refLink.GetMap().GetTile(x,y).IsSolid();
    }
    /*! \fn public int GetLife()
        \brief Returneaza viata caracterului.
     */
    public int GetLife()
    {
        return life;
    }

    /*! \fn public int GetSpeed()
        \brief Returneaza viteza caracterului.
     */
    public float GetSpeed()
    {
        return speed;
    }

    /*! \fn public void SetLife(int life)
        \brief Seteaza viata caracterului.
     */
    public void SetLife(int life)
    {
        this.life = life;
    }

    /*! \fn public void SetSpeed(float speed)
        \brief
     */
    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    /*! \fn public float GetXMove()
        \brief Returneaza distanta in pixeli pe axa X cu care este actualizata pozitia caracterului.
     */
    public float GetXMove()
    {
        return xMove;
    }

    /*! \fn public float GetYMove()
        \brief Returneaza distanta in pixeli pe axa Y cu care este actualizata pozitia caracterului.
     */
    public float GetYMove()
    {
        return yMove;
    }

    /*! \fn public void SetXMove(float xMove)
        \brief Seteaza distanta in pixeli pe axa X cu care va fi actualizata pozitia caracterului.
     */
    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }

    /*! \fn public void SetYMove(float yMove)
        \brief Seteaza distanta in pixeli pe axa Y cu care va fi actualizata pozitia caracterului.
     */
    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }
}

