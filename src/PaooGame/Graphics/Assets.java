package PaooGame.Graphics;

import PaooGame.Tiles.FirTreeTile;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage[] heroLeft;
    public static BufferedImage[] heroRight;
    public static BufferedImage[] idleHero;
    public static BufferedImage[] heroUp;
    public static BufferedImage[] heroDown;


    public static BufferedImage goblinLeft;
    public static BufferedImage goblinRight;
    public static BufferedImage dragon;
    public static BufferedImage rat;
    public static BufferedImage djin;


    public static BufferedImage wall;
    public static BufferedImage cutTree;
    public static BufferedImage soil;
    public static BufferedImage grass;
    public static BufferedImage mountain;
    public static BufferedImage floor;
    public static BufferedImage townSoil;
    public static BufferedImage water;
    public static BufferedImage book;
    public static BufferedImage tree;
    public static BufferedImage firTree;
    public static BufferedImage sand;
    public static BufferedImage chestClosed;
    public static BufferedImage chestOpen;

    public static BufferedImage peaches;
    public static BufferedImage dart;
    public static BufferedImage fire;
    public static BufferedImage potion;

    public static BufferedImage cat;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet djinSp = new SpriteSheet(ImageLoader.LoadImage("/Characters/djin.png"));
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/sheet.gif"));
        CharacterSheet hsheet = new CharacterSheet(ImageLoader.LoadImage("/Characters/snitzel.png"));
        SpriteSheet chestSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/chest2.png"));
        SpriteSheet itemSheet  = new SpriteSheet(ImageLoader.LoadImage("/textures/item.png"));
        SpriteSheet goblinsheet = new SpriteSheet(ImageLoader.LoadImage("/Characters/goblin2.png"));
        SpriteSheet dragonsheet = new SpriteSheet(ImageLoader.LoadImage("/Characters/dragon.png"));
        SpriteSheet animal = new SpriteSheet(ImageLoader.LoadImage("/Characters/lpccatratdog.png"));
        dart = ImageLoader.LoadImage("/textures/i-magic_darts.png");
        fire = ImageLoader.LoadImage("/textures/fire.gif");

        heroRight = new BufferedImage[8];
        idleHero = new BufferedImage[6];
        heroLeft = new BufferedImage[8];
        heroDown = new BufferedImage[8];
        heroUp   = new BufferedImage[8];
            /// Se obtin subimaginile corespunzatoare elementelor necesare.

        chestClosed = chestSheet.crop(0,0);
        chestOpen = chestSheet.crop(1,0);

        dragon = dragonsheet.crop(0,0);
        rat = animal.crop(0,0);
        cat = animal.crop(7,1);

        goblinLeft = goblinsheet.crop( 0, 1, 64, 64);
        goblinRight= goblinsheet.crop( 0, 3);

        djin = djinSp.crop(0,0,32,24);

        for(int i =0;i<6;i++)
            idleHero[i] = hsheet.crop( i, 0);
        wall = sheet.crop(3,0);
        floor = sheet.crop(2,0);
        grass = sheet.crop(1, 5);
        soil = sheet.crop(2,3 );
        water = sheet.crop(8, 5);
        townSoil = sheet.crop(8, 3);
        tree = sheet.crop(4, 4);
        for(int i =0;i<8;i++)
            heroLeft[i] = hsheet.crop( i, 6);
        for(int i =0;i<8;i++)
            heroUp[i] = hsheet.crop( i, 5);
        for(int i =0;i<8;i++)
            heroDown[i] = hsheet.crop( i, 4);
        for(int i =0;i<8;i++)
            heroRight[i] = hsheet.crop( i, 7);

        firTree = sheet.crop(7, 4);
        sand = sheet.crop(5,3);
        book = itemSheet.crop(4,0);

        peaches = itemSheet.crop(1,0);
        potion  = itemSheet.crop(1,3);
    }
}
