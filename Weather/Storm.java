package Weather;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.lwjgl.util.glu.GLU;

import org.lwjgl.input.Keyboard;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;
import java.io.*;

import TP4.*;
import TP8.Monde;

import SoundEffects.*;

import java.time.*;

public class Storm extends Thread{
    long m_now1, m_now2;
    public boolean m_state;     //Attribut m_state qui authorise le while true a lancer les eclaires.
    private Vecteur3D m_whitebg;    //Vecteur3D qui donne une couleur blanche au ciel
    private Vecteur3D m_colortmp;   //Attribut de type vecteur3D pour sauvegarder la couleur du ciel

    public Storm(boolean m_tmp){
        this.m_state = m_tmp;       // recuperation du parametre m_state de la class Storm
    }

    public void run(){
        m_now1 = Instant.now().toEpochMilli();      // Recupere le temps Epoch en millisecond 
        m_now2 = Instant.now().toEpochMilli();      // Recupere le temps Epoch en millisecond
        m_whitebg = new Vecteur3D(1.0f, 1.0f, 1.0f);
        while (m_state == true){                    
            if (Instant.now().toEpochMilli() - 20000 > m_now1){
                m_colortmp = Monde.m_colorbg;       // Recuperer la couleur du ciel du monde pour backup
                //System.out.println("m_colorbg avant: " + Monde.m_colorbg.getX() + ";" + Monde.m_colorbg.getY() + ";"+ Monde.m_colorbg.getZ() );
                Monde.m_colorbg = m_whitebg;        // Remplace la valeur du vecteur3D m_colorbg de la class Mode par le vecteur3D m_whitebg                     
                //System.out.println("m_colorbg après: " + Monde.m_colorbg.getX() + ";" + Monde.m_colorbg.getY() + ";"+ Monde.m_colorbg.getZ() );
                Thunder sound0 = new Thunder(true);  // Instentie l'objet sound0 grace à la classe thunder et on envoie le parametre true pour authorisé la lecture              
                sound0.start();                      // Lancement du thread
                m_now1 = Instant.now().toEpochMilli(); // Reinitialisation de l'attribut par le nouvelle epoch 
            }
            
            if (Instant.now().toEpochMilli() - 20200 > m_now2){
                Monde.m_colorbg = m_colortmp;       // Recopie de l'ancienne valeur de colorbg
                //System.out.println("m_colorbg Reset: " + Monde.m_colorbg.getX() + ";" + Monde.m_colorbg.getY() + ";"+ Monde.m_colorbg.getZ() );
                m_now2 = m_now1;                    // Reinitialisation de l'attribut par le nouvelle epoch 
            }
        }
    }
}
