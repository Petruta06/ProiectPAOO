package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class TreeTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip tree.
 */
public class SandTile extends Tile
{
    /*! \fn public TreeTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public SandTile(int id)
    {
        super(Assets.sand, id);
    }

}
