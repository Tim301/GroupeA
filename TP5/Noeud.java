package TP5;

import java.util.Vector;

/**
 * La classe noeud est une classe abstraite en amont de la
 * hi�rarchie de classes de notre graphe de sc�ne. Sa fonction principale
 * est de porter la structure d'arbre de graphe
 * de sc�ne. Un noeud peut avoir plusieurs enfants et a un seul parent.
 * Seul un noeud d'origine de la c�ne n'a pas de parent.
 *
 * @author Alexandre Bouton
 * @version 20-03-2019
 */
public abstract class Noeud
{
    //Un noeur peut avoir plusieurs enfants
    protected Vector<Noeud> m_enfants;
    
    //Un noeud peut avoir un seul parent
    protected Noeud m_parent;
    
    /**
     * Le constructeur par d�faut de la classe noeud prend un noeud
     */
    
    public Noeud(Noeud _parent)
    {
        m_parent = _parent;
        //On ajoute au parent un enfant dans sa liste
        _parent.ajouteEnfant(this);
    }
    
    /**
     * Ajoute le Noeud _enfant pass� en param�tre � la liste d'enfant en attribut
     * @param _enfant le noeud enfant � rajouter � la liste d'enfants m_enfant
     */
    public void ajouteEnfant(Noeud _enfant)
    {
     m_enfants().add(_enfant);
    }
    
    /**
     * M�thode abstraite en charge d'appeler les instructions openGL permettant d'afficher
     * le noeud(m�me si on ne sait pas encore ce qu'il repr�sente � ce niveau l�. C'est
     * pour cela qu'elle est abstraite)
     */
    public abstract void affiche();
}
