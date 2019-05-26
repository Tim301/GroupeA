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

/**
 * La classe epee place une epee dans le monde selon le tableau de valeur ci-dessous, dans son constructeur et lui applique les couleurs
 * decrites dans le tableau de couleur. Par defaut, l'epee est en diamant
 *
 * @author Alexandre Bouton
 * @version 2019/03/29
 */
public class Epee extends Objet
{
    
    Transformation m_transformationBase;
    
    /**
     * Constructor for objects of class carré
     */
    public Epee(Noeud _parent)
    {
        super(_parent);
        //Patern de construction pour l'epee
        int[][] sword_array = { {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                                {0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                                {0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                                {1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0} };

        Vecteur3D vecteur0 = new Vecteur3D(0.0f,-5.0f,-16.0f);                   
        m_transformationBase = new Translation(this, vecteur0);
        
        //Patern des codes couleur pour l'epee
        int[][] sword_array_color = { {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 1, 2},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 1, 3, 2},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 1, 3, 2, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 1, 3, 2, 0, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 1, 3, 2, 0, 0, 0},
                                      {0, 0, 4, 4, 0, 0, 0, 2, 3, 1, 3, 2, 0, 0, 0, 0},
                                      {0, 0, 4, 1, 4, 0, 2, 3, 1, 3, 2, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 4, 1, 4, 3, 1, 3, 2, 0, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 4, 1, 4, 1, 3, 2, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 4, 1, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 5, 6, 4, 1, 1, 4, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 0, 5, 6, 5, 0, 4, 4, 1, 4, 0, 0, 0, 0, 0, 0},
                                      {2, 2, 6, 5, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0},
                                      {2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                      {2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0} };
                           
        
        //Tableau de vecteur3D contenant les couleurs des cubes de l'epee
        Vecteur3D[] color = { new Vecteur3D(0.0f, 0.0f, 0.0f), new Vecteur3D(0.2f, 0.4f, 0.5f), new Vecteur3D(0.2f, 0.3f, 0.4f), new Vecteur3D(0.5f, 0.7f, 0.7f), 
                              new Vecteur3D(0.0f, 0.1f, 0.3f), new Vecteur3D(0.6f, 0.5f, 0.2f), new Vecteur3D(0.9f, 0.7f, 0.3f) };       
                                
        for(int i=0; i<16; i++)
        {
            for(int j=0; j<16; j++)
            {
                //On converti i et j en float pour pouvoir espacer les cubes
                float m = (float) i;
                float n = (float) j;
                if(sword_array[i][j] == 1)
                {
                    Random rand = new Random();
                    Vecteur3D vecteur = new Vecteur3D(m*2.0f-7.0f,n*2.0f-7.0f,-20.0f);
                    Transformation translation = new Translation(m_transformationBase, vecteur);
                    switch(sword_array_color[i][j]) {
                        case 1:
                            CubeCouleur cubecouleur0 = new CubeCouleur(translation, color[1]);
                            break;
                        
                        case 2:
                            CubeCouleur cubecouleur2 = new CubeCouleur(translation, color[2]);
                            break;
                            
                        case 3:
                            CubeCouleur cubecouleur3 = new CubeCouleur(translation, color[3]);
                            break;
                            
                        case 4:
                            CubeCouleur cubecouleur4 = new CubeCouleur(translation, color[4]);
                            break;
                            
                        case 5:
                            CubeCouleur cubecouleur5 = new CubeCouleur(translation, color[5]);
                            break;
                            
                        case 6:
                            CubeCouleur cubecouleur6 = new CubeCouleur(translation, color[6]);
                            break;
                    }
                }
            }
        }
    }

    /**
     * La methode dessine les 4 carres que nous avons instancie dans les constructeurs
     *
     */
    public void dessine()
    {
        //dessiner quelque chose. Le quelque chose en question est instancie une fois dans le constructeur de la classe carre
        m_transformationBase.affiche();
    }
}