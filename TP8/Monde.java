package TP8;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.lwjgl.util.glu.GLU;

import org.lwjgl.input.Keyboard;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

import TP4.*;
import TP6.*;

import SoundEffects.*;      // Import du package qui gere les classes d'effet sonor
import Weather.*;           // Import du package meteo qui permet de gerer les effets meteos

import java.io.*;

import java.util.*;

/**
 * Cette classe monde permet la creation d'une cate generee aleatoirement
 * Elle permet egalement le de placement dans cette carte avec les touches Z,Q,S,D, SPACE et LSHIFT.
 * Cette classe implémente la lecture d'une ambiance sonore ainsi qu'une modification de la meteo
 * avec des orages et une modification du fond du ciel avec un eclaire.
 *
 * @author Alexandre Bouton et Timothee Bastin
 * @version 2019/05/23
 */
public class Monde extends Noeud
{
    static final int MS_ENTRE_DEUX_AFFICHAGES = 40; // 25 affichages par secondes

    private boolean m_done = false; // Est ce que l'application doit se terminer?
    private boolean m_fullscreen = false; // Est-ce que l'application doit être plein écran?
    private final String m_windowTitle = "OpenGL et Graphe de scène"; // Titre de l'application
    private boolean m_f1 = false; // A t-on appuyé sur la touche F1?
    private DisplayMode m_displayMode; // propriétés de la fenêtre d'affichage

    private boolean m_filter = false; // Est-ce  que l'on applique le mipmapping de texture 

    TP6.Translation m_translation_avant = null;
    
    private static boolean m_calm = true;       // parametre qui sera envoye a la classe calm lors de instentiation
    private static Calm sound0 = new Calm(m_calm);  // instentiation de l'objet sound0 qui a pour parametre m_calm
    private static boolean m_storm = false;     // parametre qui sera envoye a la classe Storm lors de instentiation
    private static Storm weather0 = new Storm(m_storm); // instentiation de l'objet weather0 qui a pour parametre m_storm
    public static Vecteur3D m_colorbg;          // attribut de la classe monde qui sert a crée l'appel op gl Color background
    
    /**
     * Le constructuer de la classe Monde ne prend pas de parmètre : la classe Monde 
     * n'a pas de parent car son instance (souvent unique) est à l'origine du graphe de scène.
     */
    public Monde()
    {
        super(null);
        m_colorbg = new Vecteur3D(0.0f, 0.5f, 0.7f); // Definition de la couleur de ciel comme etant du bleu par defaut
    }
    
    public void Scene(){
        GL11.glClearColor(m_colorbg.getX(),m_colorbg.getY(),m_colorbg.getZ(),0.0f);
    }
    
    /**
     * La méthode affiche vides les buffers du contexte OpenGL puis appelle de manière 
     * récursive la méthode affiche pour l'ensemble des noeuds enfants. Le graphe de scene est ainsi parcouru.
     */
    public void affiche(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);          // Clear The Screen And The Depth Buffer

        for (int i=0; i<m_enfants.size(); i++ )
        {
            m_enfants.get(i).affiche();
        }        
    }

     /***************************************
     * 
     *  PARAMETRAGE DES TOUCHES
     *
     ***************************************/
     
    /**
     * Méthode responsable de la gestion des interactions avec l'utilisateur --
     * elle gère principalement les interruptions clavier
     */
    private void interactionManagement() {
        //Detection de l'appuie d'une touche
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {       // Exit if Escape is pressed
            m_done = true;
        }
        if(Display.isCloseRequested()) {                     // Exit if window is closed
            m_done = true;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_F1) && !m_f1) {    // Is F1 Being Pressed?
            m_f1 = true;                                      // Tell Program F1 Is Being Held
            switchMode();                                   // Toggle Fullscreen / Windowed Mode
        }
        if(!Keyboard.isKeyDown(Keyboard.KEY_F1)) {          // Is F1 Being Pressed?
            m_f1 = false;
        }
        if(!Keyboard.isKeyDown(Keyboard.KEY_F)) {          // Is F Being Pressed?
            m_filter = true;
        }        
        if(!Keyboard.isKeyDown(Keyboard.KEY_D)) {          // Is F Being Pressed?
            m_filter = false;
        }        
        if(Keyboard.isKeyDown(Keyboard.KEY_Z)) {          // Is Z Being Pressed?
            bouger_avant();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)) {          // Is S Being Pressed?
            bouger_arriere();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {          // Is Q Being Pressed?
            bouger_gauche();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)) {          // Is D Being Pressed?
            bouger_droite();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {          // Is Space Being Pressed?
            bouger_haut();
        }
                if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {          // Is left shift Being Pressed?
            bouger_bas();
        }
    }

    /**
     *  Appelé par la méthode interactionManagement() en charge de capturer les
     *  évènments clavier. Cette méthode gère l'attribut sanctionnant le mode plein
     *  écran
     */
    private void switchMode() {
        m_fullscreen = !m_fullscreen;
        try {
            Display.setFullscreen(m_fullscreen);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /***************************************
     * 
     *  CONTROLE DU MOUVEMENT
     *
     ***************************************/
     
    /**
     * Controle de mouvement en avant
     * 
     */
    private void bouger_avant()
    {
        try {
            m_translation_avant.avanceZ(0.2f);   
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Controle de mouvement en arriere
     * 
     */
    private void bouger_arriere()
    {
        try {
            m_translation_avant.reculeZ(0.2f);  
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Controle de mouvement a gauche
     * 
     */
    private void bouger_gauche()
    {
        try {
            m_translation_avant.gaucheX(0.2f);  
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Controle de mouvement a droite
     * 
     */
    private void bouger_droite()
    {
        try {
            m_translation_avant.droiteX(0.2f);  
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Controle de montee
     * 
     */
    private void bouger_haut()
    {
        try {
            m_translation_avant.monterY(0.2f);  
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Controle de descente
     * 
     */
    private void bouger_bas()
    {
        try {
            m_translation_avant.descendreY(0.2f);  
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void start(boolean fullscreen) {
        this.m_fullscreen = fullscreen;
        try {
            initGL();
            prepareScene();
            sound0.start();             // lancement de la thread qui gère la musique d'arriere plan
            weather0.start();           // lancement de la thread qui gère la meteo storm
            while (!m_done) {
                Thread.sleep(MS_ENTRE_DEUX_AFFICHAGES);
                interactionManagement();
                Scene();
                affiche();
                Display.update();
            }
            weather0.m_state=false;             // modification de l'attribut state qui gere l'execution de l'orage
            sound0.exit();                      // Stop la musique principale
            cleanup();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
        
    private void initGL() throws Exception {
        
        createWindow();
      
        if (m_filter){
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER,GL11.GL_LINEAR); // contre l'aliasage proche
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_LINEAR); // contre l'aliasage lointain
        }else{
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER,GL11.GL_NEAREST); // contre l'aliasage proche
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_NEAREST); // contre l'aliasage lointain
        }
        
        GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
        GL11.glShadeModel(GL11.GL_SMOOTH); // Enable Smooth Shading
        GL11.glClearColor(m_colorbg.getX(),m_colorbg.getY(),m_colorbg.getZ(),0.0f); // Definition de la couleur de l'arriere plan et prend comme parametre m_colorbg
        GL11.glClearDepth(1.0); // Depth Buffer Setup
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
        GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Testing To Do

        GL11.glEnable(GL11.GL_CULL_FACE); // Back face culling 
        
        GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
        GL11.glLoadIdentity(); // Reset The Projection Matrix

        // Calculate The Aspect Ratio Of The Window
        GLU.gluPerspective(
          45.0f,
          (float) m_displayMode.getWidth() / (float) m_displayMode.getHeight(),
          0.1f,
          100.0f);

        GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix
        
        GL11.glEnable(GL11.GL_LIGHTING);
        
        LumiereDirectionelle maLumiere = new LumiereDirectionelle();
        //maLumiere.allumer();
        
        //Mise en place de la lumière
        Vecteur3D vecteurAmbiant = new Vecteur3D(1.0f, 1.0f, 1.0f);
        Vecteur3D vecteurDiffus = new Vecteur3D(1.0f, 1.0f, 1.0f);
        Vecteur3D vecteurSpeculaire = new Vecteur3D(1.0f, 1.0f, 1.0f);
        Vecteur3D position = new Vecteur3D(-5.0f, 10.0f, -30.0f);
        LumierePonctuelle lumiere0 = new LumierePonctuelle(vecteurAmbiant, vecteurDiffus, 
                             vecteurSpeculaire, position);
                             
        //Penser a initialiser la lumiere
        //Creation d'une nouvelle lumiere pour eclairer le dessus de la carte
        weather0.m_state=true;
        lumiere0.initialise();      
        lumiere0.allumer();
    }

    /**
     * Préparation de la scene de base avec generation aleatoire de la carte
     */
    private void prepareScene(){         
        //Objets
        Vecteur3D vecteur0 = new Vecteur3D(-16.0f,-8.0f,-50.0f);
        Transformation translation0 = new Translation(this, vecteur0);
        
        Vecteur3D vecteur_avant = new Vecteur3D(0.0f, 0.0f, 1.0f);
        m_translation_avant = new Translation(translation0, vecteur_avant);
        
        Rotation rotation0 = new Rotation(m_translation_avant, new Vecteur3D(0.0f,1.0f,0.0f), -90.0f);
        Rotation rotation1 = new Rotation(rotation0, new Vecteur3D(1.0f, 0.0f, 0.0f), -90.0f);
        //Il y a egalement la creation d'une epee de disponible
        //Epee monEpee = new Epee(rotation0);
        Map_grayscale_generator_demerde test = new Map_grayscale_generator_demerde(rotation1);
    }
    
    private void createWindow() throws Exception {
        Display.setFullscreen(m_fullscreen);
        DisplayMode d[] = Display.getAvailableDisplayModes();
        for (int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640
                && d[i].getHeight() == 480
                && d[i].getBitsPerPixel() == 32) {
                m_displayMode = d[i];
                break;
            }
        }
        Display.setDisplayMode(m_displayMode);
        Display.setTitle(m_windowTitle);
        Display.create();
    }

    
    private static void cleanup() {
        Display.destroy();
    }    

    /**
     * Méthode statique permettant de lancer l'application depuis la ligne de commande
     */
    public static void main(String[] argv) {
        String OS = System.getProperty("os.name").toLowerCase();
        String path = "";
        try{
            if(OS.indexOf("win") >= 0){
                path = Monde.class.getResource("../native/windows").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");
            }
            if(OS.indexOf("linux") >= 0){
                path = Monde.class.getResource("../native/linux").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");                
            }
            if(OS.indexOf("mac") >= 0){
                path = Monde.class.getResource("../native/macosx").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");
            }
            System.setProperty("org.lwjgl.librarypath", path);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // il est possible de passer l'argument "fullscreen" lors de l'invocation de la fonction 
        //<b>main()</b> pour que la fenètre soit affichée en mode plein écran
        boolean fullscreen = false;
        if(argv.length>0) {
            if(argv[0].equalsIgnoreCase("fullscreen")) {
                fullscreen = true;
            }
        }
        
        Monde monMonde = new Monde();
        monMonde.start(fullscreen);
    }
    
}


