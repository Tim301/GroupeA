package TP5;

import org.lwjgl.opengl.GL11;
import TP4.Vecteur3D;

/**
 * La classe translation applique une translation d�finie par l'attribut
 * m_coordonnees
 *
 * @author Alexandre Bouton
 * @version 20-03-2019
 */
public class Translation extends Transformation
{
    // instance variables - replace the example below with your own
    private Vecteur3D m_coordonnees;

    /**
     * Ce constructeur fixe les coordonnees de translation en m�me temps qu'une r�f�rence
     * vers l'instance de noeud parent
     * @param _parent le noeud d�clar� comme parent de l'instant courante (this)
     * @param _coordonnes les coordonn�es de translation � appliquer
     */
    public Translation(Noeud _parent, Vecteur3D _coordonnees)
    {
        super(_parent);
        m_coordonnees = _coordonnees;
    }
    
    /**
     * Impl�mentation de la m�thode abstraite transforme() d�clar�e initialement dans la classe abstraite transformation. Elle consiste
     * � appelerl'instruction OpenGL gltranslatef avec 3 coordonn�es (float)
     */
    public void transforme()
    {
        GL11.glTranslatef(m_coordonnees.getX(),m_coordonnees.getY(),m_coordonnees.getZ());
    }
}
