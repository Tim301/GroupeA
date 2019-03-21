package TP5;

import org.lwjgl.opengl.GL11;
import TP4.Vecteur3D;

/**
 * La classe de rotation va appliquer une roation à l'objet avec les attribut d'angle et d'axe
 *
 * @author Alexandre Bouton
 * @version 20/03/2019
 */
public class Rotation extends Transformation
{
    // instance variables - replace the example below with your own
    private float m_angle;
    private Vecteur3D m_axe;

    /**
     * Ce constructeur fixe l'angle et les coordonnées de rotation en même temps qu'une référence
     * vers l'instance de noeud parent
     * @param _parent le noeud déclaré comme parent de l'instant courante (this)
     * @param _angle l'ange
     * @param _coordonnes les coordonnées de translation à appliquer
     */
    public Rotation(Noeud _parent,float _angle, Vecteur3D _axe)
    {
        super(_parent);
        m_angle = _angle;
        m_axe = _axe;
    }
    
        public void transforme()
    {
        GL11.glRotatef(m_angle,m_axe.getX(),m_axe.getY(),m_axe.getZ());
    }
}
