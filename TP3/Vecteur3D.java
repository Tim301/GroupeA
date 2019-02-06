package TP3;

/**
 * Write a description of class Vecteur3D here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Vecteur3D
{
    // attributs du Vecteur3D : 3 coordonnees
    float m_x,m_y,m_z;

    /**
     * Constructeur d'un vecteur 3D initialisant tous les attributs du vecteur a 0
     */
    public Vecteur3D()
    {
        m_x = 0.0f;
        m_y = 0.0f;
        m_z = 0.0f;
    }

    /**
     * Constructeur acceptant directement 3 valeurs pour le vecteur 3D
     * 
     * @param _x la compostante en x du veceur
     * @param _y la composante en y du vecteur
     * @param _z la composante en z du vecteur
     */
    public Vecteur3D(float _x, float _y, float _z)
    {
        m_x = _x;
        m_y = _y;
        m_z = _z;
    }
    
    /**
     * Cette methode permet de recuperer la valeur x du vecteur
     * 
     * @return m_x, la composante x du vecteur
     */
    public float getX(){
        return m_x;
    }
    
    /**
     * Cette methode permet de recuperer la valeur y du vecteur
     * 
     * @return m_y, la composante y du vecteur
     */
    public float getY(){
        return m_y;
    }
    
    /**
     * Cette methode permet de recuperer la valeur y du vecteur
     * 
     * @return m_y, la composante y du vecteur
     */
    public float getZ(){
        return m_z;
    }

}