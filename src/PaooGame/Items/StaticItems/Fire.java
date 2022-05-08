package PaooGame.Items.StaticItems;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Fire extends StaticItem{
    public Fire(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    }

    @Override
    protected void die() {

    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.fire, (int) (x - refLink.getCamera().getxOffset()), (int) (y - refLink.getCamera().getyOffset()), width, height, null);
    }
}
