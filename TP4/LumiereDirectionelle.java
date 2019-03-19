package TP4;

import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

/**
 * Une lumière directionnelle représente une sourcede lumière située à l'infini :
 * tous les rayons émis par cette lumière sont parrallèles et leur direction est donnée
 * par un vecteur de dimansion 3.
 *
 * @author Alexandre Bouton
 * @version 19/03/2019
 */
public class LumiereDirectionelle extends Lumiere
{

    private float[] m_direction = {0.0f,0.0f,0.0f,0.0f};
    
    /**
     * Constructeur de la classe LumiereDirectionelle. Il prend 4 arguments
     * @param _vecteurAmbiant composante ambiante de la lumière
     * @param _vecteurDiffus composante diffuse de la lumière
     * @param _vecteurSpeculaire la composante spéculaire de la lumière
     * @param _direction vecteur donnant la direction des rayons émis
     */
    public LumiereDirectionelle(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, 
                             Vecteur3D _vecteurSpeculaire, Vecteur3D _direction)
    {
        super(_vecteurAmbiant, _vecteurDiffus, _vecteurSpeculaire);
        m_direction[0] = _direction.getX();
        m_direction[1] = _direction.getY();
        m_direction[2] = _direction.getZ();
        m_direction[3] = 0.0f;        
    }
    
     /**
     * Constructeur par défaut de la classe LumiereDirectionnelle, il ne prend pas d'argument.
     * Il place une lumière directionnelle simulant un soleil au zénith
     */
    
    public LumiereDirectionnelle()
    {
        super();
        m_direction[0] = 0.0f;
        m_direction[1] = -1.0f; //les rayons tombent verticalement
        m_direction[2] = 0.0f;
        m_direction[3] = 0.0f;
    }
    
    /**
     * On initialise la valeur du vecteur de direction pour cette instance de classe 
     * de lumière directionelle après avoir appelé la méthode d'initialisation de la 
     * classe parente
     */
    public void initialise()
    {
        super.initialise();
        
        FloatBuffer buffDirection = BufferUtils.createFloatBuffer(4).put(m_direction);
        buffDirection.position(0);

        GL11.glLight(m_currentLight, GL11.GL_AMBIENT, buffDirection);
        
    }

}
