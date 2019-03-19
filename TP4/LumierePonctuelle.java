package TP4;

import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

/**
 * La classe Lumi�re ponctuele repr�sente une souce de lumi�re ponctuelle.
 * Elle est d�finie par un point dans l'espace � partir duquel vont irradier
 * les rayons �mis par cette source lumineuse ponctuelle.
 *
 * @author Alexandre Bouton
 * @version 19/03/2019
 */
public class LumierePonctuelle extends Lumiere
{
    // tableau de float de dimension 4 repr�sentant l'origine de la source lumieuse (appel�e m_position)
    // la 4eme valeur de ce vecteur vaut 1 : cela signigie que ce tableau de r�els sera interpr�t�
    // en tant que param�tre de lumi�re ponctuelle lors de la phase d'initialisation
    private float m_position = {0.0f,0.0f,0.0f,1.0f};
    /**
     * Constructeur de la classe lumiereDirectionelle. Il prend 4 arguments
     * @param _vecteurAmbiant composante ambiante de la lumi�re
     * @param _vecteurDiffus composante diffuse de la lumi�re
     * @param _vecteurSpeculaire la composante sp�culaire de la lumi�re
     * @param _direction vecteur donnant la direction des rayons �mis
     */
    public LumiereDirectionelle(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, 
                             Vecteur3D _vecteurSpeculaire, Vecteur3D _direction)
    {
        //On appelle le constructeur de la classe parente avec ses param�tres
        super(_vecteurAmbiant, _vecteurDiffus, _vecteurSpeculaire);
        
        //Avec le dernier param�tre, on initialise l'attrivut m_position (il s'agit d'un tableau de r�els de 
        //dimension 4)
        m_position[0] = _position.getX();
        m_position[1] = _position.getY();
        m_position[2] = _position.getZ();
        m_position[3] = 1.0f;  //La derniere valeur du tableau indique si la lumi�re est directionnelle ou non
    }

    /**
     * Constructeur par d�faut de la classe LumierePonctuelle, il ne prend pas d'argument.
     * Il place une lumi�re ponctuelle � l'origine du syst�me de coordonn�es
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
     * de lumi�re directionelle apr�s avoir appel� la m�thode d'initialisation de la 
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
