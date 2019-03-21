package TP5;

import org.lwjgl.opengl.GL11;

/**
 * La classe de transformation permet de factoriser le code commun aux classes
 * filles Translation, Rotation et Echelle
 * Les fonctions communes � ses transformations sont principalement l'empilement
 * et le d�pilement de matrices
 *
 * @author Alexandre Bouton
 * @version 20-03-2019
 */
public abstract class Transformation extends Noeud
{
    public Transformation(Noeud _parent)
    {
        super(_parent);
    }
    
    public abstract void transforme();
    
    public void affiche()
    {
        //On empile la matrice de transformation sur la matrice courante
        GL11.glPushMatrix();
        
        //ici, on execute la transformation de la classe, m�me si on ne sait pas encore de quelle transformation il s'agit
        transforme();
        
        //on fait quelque chose
        //on dessine le contenu de tous les �l�mentsde cette classe
        //plus le contenu des noeuds enfants
        //il s'agit de parcourir le contenu du Vecteur contenant les enfants
        //et d'appeller la m�thode afficher() pour chaque enfant r�f�renc� dans le vecteur
        
        for(int i=0; i<m_enfants.size(); i++)
        {
            m_enfants.get(i).affiche();
        }
        
        GL11.glPopMatrix();
    }
}
