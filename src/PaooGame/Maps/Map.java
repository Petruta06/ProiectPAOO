package PaooGame.Maps;

import PaooGame.Items.*;
import PaooGame.Items.Prizes.PrizeManager;
import PaooGame.Items.StaticItems.Fire;
import PaooGame.Items.StaticItems.*;
import PaooGame.Items.StaticItems.Peaches;
import PaooGame.RefLinks;
import PaooGame.Score.Score;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map implements Observer {
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/

    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private int[][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/


    int lvl = 1;

    private ItemManager iManager;
    private PrizeManager prizeManager;

    private Score score;



    /*! \fn public Map(RefLinks refLink)
            \brief Constructorul de initializare al clasei.

            \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
         */
    public Map(RefLinks refLink) {
        /// Retine referinta "shortcut".
        this.refLink = refLink;
        //incarca managerul de items.
        iManager = new ItemManager(refLink, new Hero(refLink,10,425));
        prizeManager = new PrizeManager(refLink);

        ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld(1);

        iManager.getHero().registerObserver(refLink.GetMap());
        score = new Score(refLink);
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public void Update() {
        prizeManager.Update();
        iManager.Update();
        score.Update();

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g) {
        int xStart = (int) Math.max(0, refLink.getCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (refLink.getCamera().getxOffset() + refLink.GetGame().GetWidth()) / Tile.TILE_WIDTH +1);
        int yStart = (int) Math.max(0, refLink.getCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (refLink.getCamera().getyOffset() + refLink.GetGame().GetHeight()) / Tile.TILE_HEIGHT + 1);
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                GetTile(x, y).Draw(g, (int) (x * Tile.TILE_HEIGHT - refLink.getCamera().getxOffset()), (int) (y * Tile.TILE_WIDTH - refLink.getCamera().getyOffset()));
            }
        }

        prizeManager.Draw(g);

        iManager.Draw(g);

        score.Draw(g);


    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.grassTile;
        }
        return t;
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */
    public void LoadWorld(int lvl) {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
        ///Se stabileste latimea hartii in numar de dale.
        width = 20;
        ///Se stabileste inaltimea hartii in numar de dale
        height = 16;
        ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];
        //Se incarca matricea cu coduri
        if(lvl == 1) {
            iManager.addItem(new Chest(refLink,480,300));
            iManager.addItem(new Peaches(refLink,530,575));
 //           iManager.addItem(new Fire(refLink, 50,330));
            iManager.addItem(new Goblin(refLink,615,575));
            iManager.addItem(new Dragon(refLink, 900, 400));

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    tiles[x][y] = MiddleEastMap(y, x);
                }

            }
        }
        else if(lvl == 2){
            iManager.getHero().SetX(10);
            iManager.getHero().SetY(425);
            iManager.addItem(new Chest(refLink,864,96));
            iManager.addItem(new Potion(refLink,340,150));
            iManager.addItem(new Potion(refLink,525,675));
            iManager.addItem(new Peaches(refLink,530,575));
            iManager.addItem(new Peaches(refLink,863,237));
            iManager.addItem(new Goblin(refLink,455,663));
            iManager.addItem(new Goblin(refLink,240,300));
            iManager.addItem(new Rat(refLink, 340,200));
            iManager.addItem(new Rat(refLink,380,150));
            iManager.addItem(new Rat(refLink,250,120));
            iManager.addItem(new Cat(refLink,864,35));
            iManager.addItem(new Djin(refLink,900,350));
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    tiles[x][y] = CastleMap(y, x);
                }

            }
        }

    }

    /*! \fn private int MiddleEastMap(int x ,int y)
        \brief O harta incarcata static.

        \param x linia pe care se afla codul dalei de interes.
        \param y coloana pe care se afla codul dalei de interes.
     */
    private int MiddleEastMap(int x, int y) {
        ///Definire statica a matricei de coduri de dale.
        final int map[][] = {
                {0, 0, 0 ,0, 3, 2, 2, 3, 6, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                {0, 0, 0, 0, 3, 3, 2, 3, 6, 2, 2, 4, 4, 4, 2, 2, 2, 2, 2, 2},
                {0, 0, 0, 0, 3, 2, 2, 3, 6, 0, 2, 4, 2, 2, 2, 0, 0, 0, 0, 0},
                {3, 3, 3, 3, 3, 2, 3, 3, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0},
                {2, 2, 2, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5},
                {3, 3, 3, 3, 3, 2, 0, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 5, 0, 0},
                {6, 6, 6, 6, 4, 2, 0, 2, 0, 6, 0, 5, 0, 0, 5, 0, 5, 5, 0, 0},
                {0, 0, 0, 6, 0, 2, 2, 2, 0, 6, 0, 5, 5, 5, 5, 0, 6, 0, 0, 0},
                {6, 6, 0, 6, 0, 0, 0, 2, 0, 4, 4, 4, 4, 0, 0, 0, 6, 0, 0, 0},
                {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 6, 0, 0, 0},
                {5, 5, 5, 5, 0, 0, 2, 2, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                {0, 0, 0, 5, 0, 0, 2, 2, 0, 0, 6, 6, 6, 0, 6, 6, 0, 6, 6, 6},
                {0, 0, 0, 5, 0, 0, 2, 2, 0, 0, 6, 0, 6, 0, 0, 0, 0, 0, 0, 6},
                {0, 0, 0, 5, 0, 0, 2, 2, 0, 0, 6, 0, 0, 0, 6, 6, 6, 0, 0, 6},
                {0, 0, 0, 5, 0, 0, 2, 2, 0, 0, 6, 0, 6, 6, 6, 0, 0, 0, 0, 6},
                {0, 0, 0, 5, 5, 0, 2, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
        };
        return map[x][y];

    }

    private int CastleMap(int x, int y) {
        ///Definire statica a matricei de coduri de dale.
        final int map[][] = {
                {8, 8, 8 ,8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                {8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 7, 7, 8, 7, 7, 7, 8},
                {8, 7, 8, 7, 8, 7, 8, 8, 8, 8, 8, 7, 7, 7, 7, 8, 7, 8, 7, 8},
                {8, 7, 8, 8, 8, 7, 8, 7, 7, 7, 8, 7, 8, 8, 7, 7, 7, 8, 8, 8},
                {8, 7, 7, 7, 7, 7, 8, 7, 7, 7, 8, 7, 8, 8, 8, 7, 8, 8, 8, 8},
                {8, 7, 8, 8, 8, 8, 8, 7, 8, 7, 8, 7, 8, 7, 7, 7, 7, 8, 7, 8},
                {8, 7, 8, 8, 8, 8, 8, 7, 8, 7, 8, 7, 8, 8, 8, 8, 7, 8, 7, 8},
                {8, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 8, 8, 8, 8, 7, 7, 7, 7, 7},
                {8, 8, 8, 7, 8, 8, 8, 8, 8, 7, 8, 7, 8, 7, 7, 7, 7, 8, 7, 7},
                {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 7, 8},
                {8, 8, 8, 8, 7, 8, 7, 7, 8, 8, 8, 8, 7, 8, 7, 8, 7, 7, 7, 8},
                {8, 7, 7, 8, 7, 8, 8, 7, 8, 8, 8, 8, 7, 8, 7, 8, 8, 7, 8, 8},
                {8, 7, 8, 8, 7, 7, 8, 7, 7, 7, 8, 7, 7, 8, 7, 7, 8, 7, 8, 8},
                {8, 7, 8, 8, 7, 8, 8, 7, 8, 7, 8, 8, 8, 8, 7, 7, 7, 7, 7, 8},
                {8, 7, 7, 7, 7, 7, 8, 7, 8, 7, 7, 7, 8, 8, 7, 8, 8, 8, 7, 8},
                {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
        };
        return map[x][y];
    }

    public int getHeight() {
        return height;
    }


    public int getWidth() {
        return width;
    }

    public ItemManager getItemManager() {
        return iManager;
    }

    public PrizeManager getPrizeManager() {
        return prizeManager;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setPrizeManager(PrizeManager prizeManager) {
        this.prizeManager = prizeManager;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public void updateObserver(int life) {
        iManager.getHero().setLife(life);
    }
}