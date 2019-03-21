package TP5;

import org.lwjgl.opengl.GL11;

/**
 * La classe de transformation permet de factoriser le code commun aux classes
 * filles Translation, Rotation et Echelle
 * Les fonctions communes à ses transformations sont principalement l'empilement
 * et le dépilement de matrices
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
        
        //ici, on execute la transformation de la classe, même si on ne sait pas encore de quelle transformation il s'agit
        transforme();
        
        //on fait quelque chose
        //on dessine le contenu de tous les élémentsde cette classe
        //plus le contenu des noeuds enfants
        //il s'agit de parcourir le contenu du Vecteur contenant les enfants
        //et d'appeller la méthode afficher() pour chaque enfant référencé dans le vecteur
        
        for(int i=0; i<m_enfants.size(); i++)
        {
            m_enfants.get(i).affiche();
        }
        
        GL11.glPopMatrix();
    }
}
