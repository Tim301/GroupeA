package TP3;

import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

/**
 * Cete classe Lumiere est abstraite. Elle sert a factoriser les attributs communs a toutes les lumieres :
 * la composante ambiante, la composante speculaire et la cmposante diffuse
 *
 * @author (GroupeA)
 * @version (06/02/2019)
 */
public abstract class Lumiere
{
    private float[] m_ambientComponent = {0.0f, 0.0f, 0.0f, 0.0f};
    private float[] m_diffuseComponent = {0.0f, 0.0f, 0.0f, 0.0f};
    private float[] m_specularComponent = {0.0f, 0.0f, 0.0f, 0.0f};

    protected int m_currentLight = -1;

    private static int m_nextFreeLight = GL11.GL_LIGHT0;

    /**
     * Ce constructeur assigne parmi les 8 lumieres
     * dispo en OpenDL 1.1. Le compteur m_curentlight est statique, c'est a dire que
     * toutes les instances de la classe lumiere partagent la vavleur de m_currentlight.
     * Chaque instance connait donc le nombre de lumieres deja instanciees
     */
    public Lumiere() 
    {
        m_currentLight = m_nextFreeLight;
        m_nextFreeLight ++;
    }
    
    /**
     * Constructeur de la classe Lumiere prenant en parametre trois
     * instances de Vecteur3D pour les composantes ambiante, diffuse
     * et speculaire de la lumiere
     * 
     * @param _vecteurAmbiant la composante ambiante de la lumiere (Vecteur3D)
     * @param _vecteurDiffus la composante diffuse de la lumiere (Vecteur3D)
     * @param _vecteurSpeculaire la composante speculaire de la lumiere (Vecteur3D)
     */
    public Lumiere(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, Vecteur3D _vecteurSpeculaire)
    {
        this();
        
        m_ambientComponent[0] = _vecteurAmbiant.getX();   
        m_ambientComponent[1] = _vecteurAmbiant.getY();
        m_ambientComponent[2] = _vecteurAmbiant.getZ();
        m_ambientComponent[3] = 1.0f;
        
        m_diffuseComponent[0] = _vecteurDiffus.getX();   
        m_diffuseComponent[1] = _vecteurDiffus.getY();
        m_diffuseComponent[2] = _vecteurDiffus.getZ();
        m_diffuseComponent[3] = 1.0f;
        
        m_specularComponent[0] = _vecteurSpeculaire.getX();   
        m_specularComponent[1] = _vecteurSpeculaire.getY();
        m_specularComponent[2] = _vecteurSpeculaire.getZ();
        m_specularComponent[3] = 1.0f;
    }
    
    /**
     * Initialisation de la lumiere courante
     */
    public void initialise()
    {
       FloatBuffer buffAmbient = BufferUtils.createFloatBuffer(4).put(m_ambientComponent);
        buffAmbient.position(0);
                
        FloatBuffer buffDiffuse = BufferUtils.createFloatBuffer(4).put(m_diffuseComponent);
        buffDiffuse.position(0);

        FloatBuffer buffSpecular = BufferUtils.createFloatBuffer(4).put(m_specularComponent);
        buffSpecular.position(0);
                
        GL11.glLight(m_currentLight, GL11.GL_AMBIENT, buffAmbient);
        GL11.glLight(m_currentLight, GL11.GL_DIFFUSE, buffDiffuse);
        GL11.glLight(m_currentLight, GL11.GL_SPECULAR, buffSpecular); 
    }
    
    /**
     * active la lumiere courante (on suppose que GL_LIGHTNING est egalement active)
     */
    public void allumer()
    {
        GL11.glEnable(m_currentLight);
    }
    
    /**
     * active la lumiere courante (on suppose que GL_LIGHTNING est egalement active)
     */
    public void eteindre()
    {
        GL11.glDisable(m_currentLight);
    }
    
    
    
    
    
    
}

