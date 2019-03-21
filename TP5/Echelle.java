package TP5;

import org.lwjgl.opengl.GL11;
import TP4.Vecteur3D;

/**
 * La classe de mise à l'echelle permet d'augmenter ou de réduire l'échelle d'un
 * objet dans un ou plusieurs axes grâce aux attributs _ratio
 *
 * @author Alexandre Bouton
 * @version 20/03/2019
 */
public class Echelle extends Transformation
{
    // instance variables - replace the example below with your own
    private Vecteur3D m_ratio;

    /**
     * Ce constructeur fixe le ratio de mise à l'echelle pour les 3 axes en même temps qu'une référence
     * vers l'instance de noeud parent
     * @param _parent le noeud déclaré comme parent de l'instant courante (this)
     * @param _axe axe sur lequel on applique la mise à l'echelle
     */
    public Echelle(Noeud _parent, Vecteur3D _ratio)
    {
        super(_parent);
        m_ratio = _ratio;
    }
    
        public void transforme()
    {
        GL11.glScalef(m_ratio.getX(),m_ratio.getY(),m_ratio.getZ());
    }
}