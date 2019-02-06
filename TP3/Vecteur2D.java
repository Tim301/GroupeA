package TP3;


/**
 * Cette classe représente un vecteur à deux dimensions.
 * Elle est surtout utilisée pour définir les coordonnées
 *(uv,)
 * @author (GroupeA)
 * @version (0.0.0,1 alpha)
 */
public class Vecteur2D
{
    // Attrbuts de classe
    private float m_u=0.0f, m_v=0.0f;

    /**
     * Constructeur par defaut. Il initialise
     * les deux composants du vecteur a zero
     */
    public Vecteur2D()
    {
        // Initialisation des attributs
        
        //On prefxe les attributs par "m_", pour "member_"
        m_u = 0.0f;
        m_v = 0.0f;
    }
    
    /**
     * Ce constructeur prend 2 parametres:
     * les valeurs qui seront assignees aux composantes
     * m_u et m_v de l'instance de Vecteur2D.
     * 
     * @param _u est la valeur de la composante u du vecteur
     * @param _v est la valeur de la composante v du vecteur
     */
    //On prefixe les parametres par un underscore uniquement
    public Vecteur2D(float _u, float _v)
    {
        m_u = _u;
        m_v = _v;
    }

    /**
     * Cette methode ajoute au vecteur courantle vecteur passe en parallele
     * Les composantes du vecteur passe en parametre sont ajoutees aux composantes
     * du vecteur represente par l'instance courante.
     * 
     * @param _vec vecteur a ajouter a l'instance courante
     */
    public void add (Vecteur2D _vec)
    {
        //m_u = _vec.m_u;   //Pas possible ! Les attributs de _vec sont prives. Il faut creer un accesseur
        m_u += _vec.getU();
        m_v += _vec.getV();
    }
    
    /**
     * Accesseur pour la composante u
     * 
     * @return la valeur de la composante u
     */
    public float getU()
    {
        return m_u;
    }
    
    /**
     * Accesseur pour la composante v
     * 
     * @return la valeur de la composante v
     */
    public float getV()
    {
        return m_v;
    }
    
    /**
     * mutateur pour la composante u
     * 
     * @param _u la valeur de la composante u
     */
    public void setU(float _u)
    {
        this.m_u = _u;
    }
    
    /**
     * mutateur pour la composante v
     * 
     * @param _v la valeur de la composante v
     */
    public void setV(float _v)
    {
        this.m_v = _v;
    }
    
    /**
     * Cette methode retourne la norme du vecteur, c'est a dire 
     * la racine carree de la somme du carre des deux composantes du vecteur
     *
     * @return    Norme du vecteur
     */
    public float getMagnitude()
    {
        // (float) avant la fonction est un operateur de transtypage. Il permet de forcer le type de donnee
        //La methode sqrt du package Math ne retourne que des doubles. Or, la fonction doit retourner un float. Il faut donc faire la conversion
        return (float)Math.sqrt(m_u*m_u + m_v+m_v);
    }
}
