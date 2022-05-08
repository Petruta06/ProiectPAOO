package PaooGame.Graphics;

import PaooGame.Game;
import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

public class GameCamera {

        private RefLinks refLink;
        private float xOffset, yOffset;

        public GameCamera(RefLinks refLink, float xOffset, float yOffset){
            this.refLink = refLink ;
            this.xOffset = xOffset;
            this.yOffset = yOffset;
        }

        public void blankSpace(){
            if(xOffset<0)
                xOffset = 0;
            else if(xOffset >refLink.GetMap().getWidth()* Tile.TILE_WIDTH - refLink.GetWidth())
                xOffset = refLink.GetMap().getWidth()* Tile.TILE_WIDTH - refLink.GetWidth();
            if(yOffset <0)
                yOffset = 0;
            else if(yOffset > refLink.GetMap().getHeight()* Tile.TILE_HEIGHT - refLink.GetHeight() )
                yOffset =  refLink.GetMap().getHeight()* Tile.TILE_HEIGHT - refLink.GetHeight();




        }

        public void centeredOnItem(Item e){
            xOffset = e.GetX() - refLink.GetWidth() / 2 + e.GetWidth() / 2;
            yOffset = e.GetY() - refLink.GetHeight() / 2 + e.GetHeight() / 2;
            blankSpace();
        }

        public void move(float xAmt, float yAmt){
            xOffset += xAmt;
            yOffset += yAmt;
        }

        public float getxOffset() {
            return xOffset;
        }

        public void setxOffset(float xOffset) {
            this.xOffset = xOffset;
        }

        public float getyOffset() {
            return yOffset;
        }

        public void setyOffset(float yOffset) {
            this.yOffset = yOffset;
        }

    }
