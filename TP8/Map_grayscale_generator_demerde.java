package TP8;

import TP6.*;
import TP4.Vecteur3D;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.Keyboard;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;
import java.io.*;
import java.util.*;
import java.net.URL;


/**
 * Generation aleatoire de carte du monde 
 *
 * @Alexandre Bouton
 * @2019/05/22
 */
public class Map_grayscale_generator_demerde extends Objet
{
    Transformation m_transformationBase =null;
    
    /**
     * Le constructeur de carte. La carte doit etre dans le dossier "TP8"
     * @param _parent le noeud parent 
     * @param name, le nom du fichier
     */
    public Map_grayscale_generator_demerde(Noeud _parent)
    {
        super(_parent);
        Vecteur3D vecteur0 = new Vecteur3D(0.0f,0.0f,0.0f);
        m_transformationBase = new Translation(_parent, vecteur0);
        
        //Cubes de couleurs
        Vecteur3D color = new Vecteur3D(0.0f, 0.5f, 0.0f);
                
        //Longeur et largeur de la carte
        int longeur = 20;
        int largeur= 20;
        //Tableau de taille longeur*largeur
        int[][] image = new int[longeur][largeur];
        
        //Remplissage du tableau par des valeurs aleatoires comprises entre min et max
        int Min = 0;
        int Max = 2;
        for(int i=0; i<longeur; i++)
        {
            for(int j=0; j<largeur; j++)
            {
                image[i][j] = Min + (int)(Math.random() * ((Max - Min) + 1));
            }
        }

        //Tableau en dur, pour du test
        /*int[][] image = { {1, 0, 1, 1, 2, 3, 3, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                          {0, 1, 0, 0, 0, 3, 3, 2, 2, 0, 0, 0, 1, 1, 1, 1},
                          {1, 1, 0, 6, 0, 2, 2, 2, 1, 1, 1, 0, 1, 1, 2, 1},
                          {0, 1, 3, 0, 1, 2, 2, 2, 1, 1, 1, 0, 1, 1, 3, 1},
                          {0, 1, 3, 3, 2, 2, 2, 1, 1, 1, 1, 0, 1, 1, 2, 1},
                          {0, 2, 2, 2, 2, 3, 2, 1, 0, 0, 0, 0, 0, 1, 1, 1} };*/
                   
                  
        //Tableau 2D de CubeTexture
        CubeTexture map[][] = new CubeTexture[longeur][largeur];
        
        for(int i=0; i<longeur; i++)
        {
            for(int j=0; j<largeur; j++)
            {
                //On converti i et j en float pour pouvoir espacer les cubes
                float m = (float) i;
                float n = (float) j;
                
                //Remplissage 
                if(image[i][j] > 0)
                {
                    float hauteur = (float) image[i][j];
                    for(float k=0; k<hauteur; k++)
                    {
                        Vecteur3D vecteur = new Vecteur3D(m*2.0f, n*2.0f, k*2.0f);
                        Translation translation = new Translation(m_transformationBase, vecteur);
                        map[i][j] = new CubeTexture(translation);
                    }
                }
                

                Vecteur3D vecteur = new Vecteur3D(m*2.0f,n*2.0f - 5.0f,image[i][j]*2.0f);
                Translation translation = new Translation(m_transformationBase, vecteur);
                //map[i][j] = new CubeTexture(translation);
                map[i][j] = new CubeTexture(translation);
            }
        }
    }
         
     public void dessine()
    {
        //dessiner quelque chose. Le quelque chose en question est instancie une fois dans le constructeur de la classe carre
        m_transformationBase.affiche();
    }
}