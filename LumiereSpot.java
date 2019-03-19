
package TP4

import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

/**
 * La classe spot représente une lumière ponctuelle qui
 * diffuse un faisceau de lumière selon une direction
 * et un angle de diffusion (GL_SPOT_CUTOFF). Un 3eme paramètre (GL_SPOT_EXPONENT)
 * détermine l'atténuaion de l'intensité lumineuse
 * lorsque les rayons s'écartent de la direction principale (GL_SPOT_DERECTION)
 *
 * 
 *
 * @author Alexandre Bouton
 * @version 19/03/2019
 */
public class LumiereSpot extends LumiereDirectionnelle
{
    //la direction de la lumière spot (tableau de réls, dimension 3)
    float[] m_spotDirection = {0.0f,0.0f,-1.0f};
    
    //l'angle de diffusion de a lumière spot
    //(réel compris entre 0 (distribution uniforme) et 128 (focus max) )
    float m_spotCutoff = 0;
    
    //l'atténuatuin par rapport à la direction principale
    //(entre 0 et 90 -> Cône de lumière ou 180 ->distribution uniforme)
    float m_spotExponent = 90;
    
     /**
     * Constructeur par défaut de la classe LumiereSpot, il ne prend pas d'argument.
     * Il place une lumière spot au cantre du système de coordonnées local
     * orientée selon les z négatifs (droit devant)
     */
    public LumiereSpot()
    {
        super();
       
        m_spotDirection[0] = 0.0f;
        m_spotDirection[1] = 0.0f;
        m_spotDirection[3] = -1.0f;
        
        m_spotCutoff = 0.0f;
        m_spotExponent = 90.0f;
    }
    
     /**
     * Constructeur de la classe lumiereSpot. Il prend 7 arguments
     * @param _vecteurAmbiant composante ambiante de la lumière
     * @param _vecteurDiffus composante diffuse de la lumière
     * @param _vecteurSpeculaire la composante spéculaire de la lumière
     * @param _position vecteur donnant la direction des rayons émis
     * @param _spotDirection vecteur donnant la direction du faisceau du spot
     * @param _spotCutoff réel donnant l'angle d'ouverture du spot
     * @param _spotExponent réel donnant l'atténuation des rayons s'écartant de la direction principale
     */
    public LumiereSpot(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, 
                             Vecteur3D _vecteurSpeculaire, Vecteur3D _direction, Vecteur3D _spotDirection, float _cutoff, float _spotExponent)
    {
        //On appelle le constructeur de la classe parente avec ses paramètres
        super(_vecteurAmbiant, _vecteurDiffus, _vecteurSpeculaire, _position);
        
        //Avec le dernier paramètre, on initialise l'attribut m_position (il s'agit d'un tableau de réels de 
        //dimension 4)
        
        m_spotDirection[0] = _spotDirection.getX();
        m_spotDirection[1] = _spotDirection.getY();
        m_spotDirection[2] = _spotDirection.getZ();
        
        m_spotCutoff = m_spotCutoff;
        m_spotExponent = m_spotExponent;
    }
    
     /**
     * On initialise les valeurs du vecteur de direction, de cutoffet d'exponent pour cette instance de classe 
     * de lumière spot après avoir appelé la méthode d'initialisation de la 
     * classe parente
     */
    public void initialise()
    {    
        super.initialise();
        
        FloatBuffer buffDirection = BufferUtils.createFloatBuffer(4).put(m_spotDirection);
        buffPosition.position(0);

        GL11.glLight(m_currentLight, GL11.GL_AMBIENT, buffPosition);
        
    }
}
