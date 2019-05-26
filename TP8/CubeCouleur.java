package TP8;
import org.lwjgl.opengl.GL11;
import TP6.*;
import TP4.*;

/**
 * Write a description of class CubeCouleur here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CubeCouleur extends Objet
{

    Vecteur3D m_couleur; //définit la valeur de la couleur (RGB)
    
    /**
     * Constructor for objects of class Cube
     */
    public CubeCouleur(Noeud _parent, Vecteur3D _couleur)
    {
        super(_parent);
        m_couleur = _couleur;
    }

    /**
     * Cette methode utilise une suite d'instructions OpenGL perettant d'afficher un cube.
     * Vous trouverez ces instructions dans la clase OpenGL en vrac du package TP2.
     */
    public void dessine(){

        //On change la couleur pour dessiner le cube fil de fer dans la couleur dessinée    
        GL11.glDisable(GL11.GL_LIGHTING);
        
        GL11.glColor3f(m_couleur.getX(),m_couleur.getY(),m_couleur.getZ());  
        
        GL11.glBegin(GL11.GL_QUADS); // on change la couleur de dessin avant de dessiner le cube
     
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Left Of The Texture and Quad

        // Back Face
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Left Of The Texture and Quad
        // Top Face      
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        // Bottom Face
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        // Right face      
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        // Left Face
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad

        GL11.glEnd();
        
        GL11.glColor3f(1.0f,1.0f,1.0f); // on remet la couleur de dessin à la couleur blanche une fois dessiné
        GL11.glEnable(GL11.GL_LIGHTING);
    }
}
