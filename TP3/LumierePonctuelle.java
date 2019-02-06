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
    private int x;

    /**
     * Constructor for objects of class LumierePonctuelle
     */
    public LumierePonctuelle(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, Vecteur3D _vecteurSpeculaire)
    {
        //Appel le constructeur de la classe mere
        super(_vecteurAmbiant, _vecteurDiffus, _vecteurSpeculaire);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
