package PaooGame.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/*! \class public class SpriteSheet
    \brief Clasa retine o referinta catre o imagine formata din dale (sprite sheet)

    Metoda crop() returneaza o dala de dimensiuni fixe (o subimagine) din sprite sheet
    de la adresa (x * latimeDala, y * inaltimeDala)
 */
public class SpriteSheet
{
    private BufferedImage       spriteSheet;        /*!< Referinta catre obiectul BufferedImage ce contine sprite sheet-ul.*/
    private final int DEFAULTSIZE = 32;
    private static int    tileWidth;   /*!< Latimea unei dale din sprite sheet.*/
    private static int    tileHeight;   /*!< Inaltime unei dale din sprite sheet.*/

    /*! \fn public SpriteSheet(BufferedImage sheet)
        \brief Constructor, initializeaza spriteSheet.

        \param buffImg Un obiect BufferedImage valid.
     */
    public SpriteSheet(BufferedImage buffImg)
    {
            /// Retine referinta catre BufferedImage object.
        spriteSheet = buffImg;
        tileHeight = DEFAULTSIZE;
        tileWidth = DEFAULTSIZE;
    }


    public SpriteSheet(String file, int w, int h){
        tileWidth = w;
        tileHeight = h;
        System.out.println("Loading file "+ file);
        spriteSheet = loadSprite(file);
    }

        /*! \fn public loadSprite(String file)
        \brief Returns and image extracted from a file

        \param file     the name of the file that should be loaded
     */

    public BufferedImage loadSprite(String file){
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (Exception e) {
            System.out.println("Error: could not load file "+file);
        }
        return sprite;
    }

    public SpriteSheet(BufferedImage buffImg, int w, int h)
    {
        tileWidth = w;
        tileHeight = h;
        /// Retine referinta catre BufferedImage object.
        spriteSheet = buffImg;
    }
    /*! \fn public BufferedImage crop(int x, int y)
        \brief Returneaza un obiect BufferedImage ce contine o subimage (dala).

        Subimaginea este localizata avand ca referinta punctul din stanga sus.

        \param x numarul dalei din sprite sheet pe axa x.
        \param y numarul dalei din sprite sheet pe axa y.
     */
    public BufferedImage crop(int x, int y)
    {
            /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
            /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
            /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }

    public BufferedImage crop(int x, int y, int h,int w)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
        /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return spriteSheet.getSubimage(x * w, y *h, w, h);
    }
}
