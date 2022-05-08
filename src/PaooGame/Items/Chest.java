package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Prizes.Prize;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Chest extends Item {
    BufferedImage image;
    public Chest(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        life = 2;
    }

    @Override
    public void Update() {

    }

    @Override
    public void die(){
        refLink.GetMap().getPrizeManager().addItem(Prize.dartItem.createNew((int) x,(int) y));
        refLink.GetMap().getPrizeManager().addItem(Prize.bookItem.createNew((int) x,(int) y+100));

    }

    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.chestClosed,(int) (x - refLink.getCamera().getxOffset()), (int) (y - refLink.getCamera().getyOffset()), width, height, null);
    }
}
