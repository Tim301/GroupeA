package TP5;

import org.lwjgl.opengl.GL11;
import TP4.Vecteur3D;

/**
 * La classe translation applique une translation définie par l'attribut
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
     * Ce constructeur fixe les coordonnees de translation en même temps qu'une référence
     * vers l'instance de noeud parent
     * @param _parent le noeud déclaré comme parent de l'instant courante (this)
     * @param _coordonnes les coordonnées de translation à appliquer
     */
    public Translation(Noeud _parent, Vecteur3D _coordonnees)
    {
        super(_parent);
        m_coordonnees = _coordonnees;
    }
    
    /**
     * Implémentation de la méthode abstraite transforme() déclarée initialement dans la classe abstraite transformation. Elle consiste
     * à appelerl'instruction OpenGL gltranslatef avec 3 coordonnées (float)
     */
    public void transforme()
    {
        GL11.glTranslatef(m_coordonnees.getX(),m_coordonnees.getY(),m_coordonnees.getZ());
    }
}
