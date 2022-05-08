package PaooGame.Items.StaticItems;

import PaooGame.Items.Item;
import PaooGame.RefLinks;

public abstract class StaticItem extends Item {
    public StaticItem(RefLinks refLink, float x, float y, int width, int height) {
        super(refLink, x, y, width, height);
    }
}
