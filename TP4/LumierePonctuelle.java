package TP4;

import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

/**
 * La classe Lumière ponctuele représente une souce de lumière ponctuelle.
 * Elle est définie par un point dans l'espace à partir duquel vont irradier
 * les rayons émis par cette source lumineuse ponctuelle.
 *
 * @author Alexandre Bouton
 * @version 19/03/2019
 */
public class LumierePonctuelle extends Lumiere
{
    // tableau de float de dimension 4 représentant l'origine de la source lumieuse (appelée m_position)
    // la 4eme valeur de ce vecteur vaut 1 : cela signigie que ce tableau de réels sera interprété
    // en tant que paramètre de lumière ponctuelle lors de la phase d'initialisation
    private float m_position = {0.0f,0.0f,0.0f,1.0f};
    /**
     * Constructeur de la classe lumiereDirectionelle. Il prend 4 arguments
     * @param _vecteurAmbiant composante ambiante de la lumière
     * @param _vecteurDiffus composante diffuse de la lumière
     * @param _vecteurSpeculaire la composante spéculaire de la lumière
     * @param _direction vecteur donnant la direction des rayons émis
     */
    public LumiereDirectionelle(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, 
                             Vecteur3D _vecteurSpeculaire, Vecteur3D _direction)
    {
        //On appelle le constructeur de la classe parente avec ses paramètres
        super(_vecteurAmbiant, _vecteurDiffus, _vecteurSpeculaire);
        
        //Avec le dernier paramètre, on initialise l'attrivut m_position (il s'agit d'un tableau de réels de 
        //dimension 4)
        m_position[0] = _position.getX();
        m_position[1] = _position.getY();
        m_position[2] = _position.getZ();
        m_position[3] = 1.0f;  //La derniere valeur du tableau indique si la lumière est directionnelle ou non
    }

    /**
     * Constructeur par défaut de la classe LumierePonctuelle, il ne prend pas d'argument.
     * Il place une lumière ponctuelle à l'origine du système de coordonnées
     */
    
    public LumierePonctuelle()
    {
        super();
        m_position[0] = 0.0f;
        m_position[1] = 0.0f;
        m_position[2] = 0.0f;
        m_position[3] = 1.0f;
    }
    
    /**
     * On initialise la valeur du vecteur de direction pour cette instance de classe 
     * de lumière directionelle après avoir appelé la méthode d'initialisation de la 
     * classe parente
     */
    public void initialise()
    {    
        super.initialise();
        
        FloatBuffer buffPosition = BufferUtils.createFloatBuffer(4).put(m_position);
        buffPosition.position(0);

        GL11.glLight(m_currentLight, GL11.GL_AMBIENT, buffPosition);
        
    }
   
}
