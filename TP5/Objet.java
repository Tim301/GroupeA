package TP5;


/**
 * Abstract class Objet - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Objet extends Noeud
{

    public Objet(Noeud _parent)
    {
        super(_parent);
    }
    
    public abstract void dessine();
    
        public void affiche()
    {
        //ici, on dessine l'objet, même si on ne sait pas encore de quelle transformation il s'agit
        dessine();
        
        //on fait quelque chose
        //on dessine le contenu de tous les élémentsde cette classe
        //plus le contenu des noeuds enfants
        //il s'agit de parcourir le contenu du Vecteur contenant les enfants
        //et d'appeller la méthode afficher() pour chaque enfant référencé dans le vecteur
        
        for(int i=0; i<m_enfants.size(); i++)
        {
            m_enfants.get(i).affiche();
        }

    }
}
