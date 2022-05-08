package PaooGame.Items.Prizes;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Book extends Tile
    \brief Abstractizeaza notiunea de dala de tip tree.
 */
public class Book extends Item
{
    private BufferedImage image;

    public Book(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        image = Assets.dart;
    }


    public void die(){
        refLink.GetMap().getScore().addPoints(50);
        refLink.GetMap().getItemManager().getHero().setLife(refLink.GetMap().getItemManager().getHero().getLife() +1);
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int) (x - refLink.getCamera().getxOffset()), (int) (y - refLink.getCamera().getyOffset()), width, height, null);
    }
}
