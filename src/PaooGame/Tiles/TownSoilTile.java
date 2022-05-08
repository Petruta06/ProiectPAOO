package PaooGame.Tiles;

import PaooGame.Graphics.Assets;
/*! \class public class TownSoilTile extends Tile
       \brief Abstractizeaza notiunea de dala de tip sol/pamant din sat.
    */
public class TownSoilTile extends Tile {

        /*! \fn public TownSoilTile(int id)
            \brief Constructorul de initializare al clasei

            \param id Id-ul dalei util in desenarea hartii.
         */
        public TownSoilTile(int id)
        {
            super(Assets.townSoil, id);
        }

    }
