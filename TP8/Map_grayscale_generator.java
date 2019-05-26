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
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Le generateur de carte utilise une image sous le format pgm, qui est un type d'image non compresse en niveau de gris
 * Cette solution n'est pas encore implementee car nous n'avons pas reussi a la faire marcher. Elle pourra etre 
 * implementee par la suite
 *
 * @author Alexandre Bouton
 * @version 2019/04/18
 */


public class Map_grayscale_generator extends Objet
{
    Transformation m_transformationBase;
    
    /**
     * Le constructeur de carte. La carte doit etre dans le dossier "TP8"
     * @param _parent le noeud parent 
     * @param name, le nom du fichier
     */
    public Map_grayscale_generator(Noeud _parent, String name)
    {
        super(_parent);

        // The name of the file to open.
        URL fileURL = Monde.class.getResource(name);
        

        // This will reference one line at a time
        String line = null;
        String caracter = null;
        
        //Numero de la ligne
        int line_number = 0;
        int line_image_number = 0;
        //Largeur de l'image
        int width_image = 0;
        //Hauteur de l'image
        int height_image = 0;
        //Valeur max des pixels
        int max_value = 0;
        
        //Declaration d'un talbeau suffisament grand 
        int image_array[][] = new int[500][500];

        
       
        try 
        {
            FileReader fileReader =
                new FileReader(name);

            BufferedReader bufferedReader =
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //Code a executer lorsqu'on lit le fichier    
                 
                /**************************************************
                 * ************************************************
                 * 
                 * Detection de commentaire
                 * 
                 * 
                 * Si la ligne ne commence pas par un commentaire, 
                 * on applique les conditions
                 * ************************************************
                 **************************************************/
                if(!line.startsWith("#"))
                { 
                    /**************************************************
                     * ************************************************
                     * 
                     * Rangement des valeurs dans un tableau
                     * 
                     * ************************************************
                     **************************************************/
                    if(line_number > 2)
                    {
                        String[] ligne = line.split("[ |   |\n]");
                        for(int j = 0; j < width_image; j++)
                        {
                            System.out.println("valeur du pixel" + ligne[j]);
                            //image_array[i][j] = Integer.parseInt(size[h]);
                        }
                        //System.out.println(image_array[2][2]);
                        line_number++;
                    }
                     
                     
                    /**************************************************
                     * ************************************************
                     * 
                     * Detection de la valeur max de l'image et
                     * initialisation d'un tableau qui va contenir
                     * l'image
                     * 
                     * ************************************************
                     **************************************************/
                    else if(line_number == 2)
                    {
                        max_value = Integer.parseInt(line);
                        System.out.println(max_value);
                        
                        for(int j = 0; j < width_image; j++)
                        {
                            image_array[line_number-2][j] = 0;
                        }
                        
                        line_number++;
                    }
                    
                    
                    /**************************************************
                     * ************************************************
                     * 
                     * Detection des dimensions de l'image
                     * 
                     * Les dimesions de l'image sont censees se trouver
                     * sur la ligne utile 2
                     * ************************************************
                     **************************************************/
                    else if(line_number == 1)
                    {
                        //On retire l'espace de la chaine de caractere avec un split
                        String[] size = line.split(" ");
                        width_image = Integer.parseInt(size[0]);
                        height_image = Integer.parseInt(size[1]);
                        //On regarde si les valeurs ne depassent pas les dimensions du talbeau
                        if(width_image > 500 || height_image > 500)
                        {
                            System.out.println("Image trop grande ! Taille max : 500*500");
                        }
                        line_number++;
                    }
         
                                
                    /**************************************************
                     * ************************************************
                     * 
                     * Verification du format de fichier
                     * 
                     * ************************************************
                     **************************************************/
                    else if(line_number == 0 && line.startsWith("P2"))
                    {
                        System.out.println("Format de fichier valide !");
                        line_number++;
                    }
                    else if(line_number == 0 && !line.startsWith("P2"))
                    {
                        System.out.println("Format de fichier non valide !");
                        line_number++;
                    }
                }
                
                
            }  

            // On ferme le fichier
            bufferedReader.close();
        }
        //S'il n'y a pas de fichier
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" +
                name);
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '"
                + name);
        }
    } 
    
    public void dessine()
    {
        //dessiner quelque chose. Le quelque chose en question est instancie une fois dans le constructeur de la classe carre
        //m_transformationBase.affiche();
    }
}


