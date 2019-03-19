package TP3;


/**
 * Write a description of class LumierePonctuelle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LumierePonctuelle extends Lumiere
{
    // instance variables - replace the example below with your own
    private float[] m_direction = {0.0f, 0.0f, 0.0f, 0.0f};
    

    /**
     * Constructor for objects of class LumierePonctuelle
     */
    public LumierePonctuelle(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, Vecteur3D _vecteurSpeculaire)
    {
        //Appel le constructeur de la classe mere
        super(_vecteurAmbiant, _vecteurDiffus, _vecteurSpeculaire);
        m_direction[0] = _direction.getX();
        m_direction[1] = _direction.getY();
        m_direction[2] = _direction.getZ();
        m_direction[3] = 1.0f;
    }
    
    /**
     * On initialise la valeur du vecteur de direction pour cette instance de classe
     * de la lumieredirectionnelle apres avoir appele la methode d'initialisation de la classe parente
     */
    public void initialise()
    {
        super.initialise();
        
        FloatBuffer buffDirection = BufferUtils.createFloatBuffer(4).put(m_direction);
        buffDiffuse.position(0);
        
        GL11.glLight(m_currentLight, GL11.GL_AMBIENT, buffDirection);
    }
}
