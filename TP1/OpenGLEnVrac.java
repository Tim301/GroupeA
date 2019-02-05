
package TP1;


import java.awt.image.BufferedImage;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

import org.lwjgl.util.glu.GLU;


import java.io.*;


/**
 * Cette classe rassemble la plupart des instructions de base
 * couramment utilisées avec la version 1.1 d'OpenGL. à savoir :
 * <ul>
 *   <li> la création de polygones,
 *   <li> l'application de texture,
 *   <li> les transformations affines dans le mode <b>MODELVIEW</b>,
 *   <li> la définition des paramètres de caméra dans le mode <b>PROJECTION<b/>,
 *   <li> les transformations affines dans le <b>MODELVIEW</b>,
 *   <li> l'empilement et le dépilement de matrices.
 * </ul>
 */

public class OpenGLEnVrac {
    private boolean done = false;
    private boolean fullscreen = false;
    private final String windowTitle = "OpenGL en vrac";
    private boolean f1 = false;
    private DisplayMode displayMode;

    private float xrot;            // X Rotation ( NEW )
    private float yrot;            // Y Rotation ( NEW )
    private float zrot;            // Z Rotation ( NEW )
    private int textureID;         // Storage For One Texture ( NEW )    

    private boolean light;         // Lighting ON/OFF    
    
    //Parametres d'eclairement
    private float[] lightAmbient = {1.0f,1.0f,1.0f,1.0f};
    //Ajout de couleurs
    private float[] lightDiffuse = {0.8f,0.0f,0.7f,0.0f}; // {R, G, B, toujours_0}
    //Declaration de la composante speculaire
    private float[] lightSpecularComponent = {1.0f, 1.0f, 1.0f, 0.0f}; // {R,G,B, toujours_0}. Ici, la composante speculaire est blanche
    //Position de a lumière
    private float[] lightPosition = {0.0f,1000.0f,0.0f,1.0f}; //Lors que le dernier composant est a 1, la lumière est ponctuelle. Si il est a 0, la lumiere est directionnelle

    private boolean filter = false;
    
    public static void main(String[] argv) {
        String OS = System.getProperty("os.name").toLowerCase();
        String path = "";
        try{
            if(OS.indexOf("win") >= 0){
                path = OpenGLEnVrac.class.getResource("../native/windows").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");
            }
            if(OS.indexOf("linux") >= 0){
                path = OpenGLEnVrac.class.getResource("../native/linux").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");                
            }
            if(OS.indexOf("mac") >= 0){
                path = OpenGLEnVrac.class.getResource("../native/macosx").getPath();
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
        
        OpenGLEnVrac monOpenGLEnVrac = new OpenGLEnVrac();
        monOpenGLEnVrac.start(fullscreen);
    }
    public void start(boolean fullscreen) {
        this.fullscreen = fullscreen;
        try {
            init();
            while (!done) {
                mainloop();
                render();
                Display.update();
            }
            cleanup();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    private void mainloop() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {       // Exit if Escape is pressed
            done = true;
        }
        if(Display.isCloseRequested()) {                     // Exit if window is closed
            done = true;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_F1) && !f1) {    // Is F1 Being Pressed?
            f1 = true;                                      // Tell Program F1 Is Being Held
            switchMode();                                   // Toggle Fullscreen / Windowed Mode
        }
        if(!Keyboard.isKeyDown(Keyboard.KEY_F1)) {          // Is F1 Being Pressed?
            f1 = false;
        }
        if(!Keyboard.isKeyDown(Keyboard.KEY_F)) {          // Is F Being Pressed?
            filter = true;
        }        
        if(!Keyboard.isKeyDown(Keyboard.KEY_D)) {          // Is F Being Pressed?
            filter = false;
        }        

    }

    private void switchMode() {
        fullscreen = !fullscreen;
        try {
            Display.setFullscreen(fullscreen);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private boolean render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);          // Clear The Screen And The Depth Buffer

        GL11.glLoadIdentity();                          // Reset The Current Modelview Matrix

        //Translation de tous les objets pour reculer les objets pour qu'ils soient visibles et centres
        GL11.glTranslatef(1.5f, 0.0f, -8.0f); // Move Into The Screen 5 Units
        
        //PushMaxtrx memorise l'etat de mon repere courant. En l'occurence, le repere courant a ce moment du code est le repere donne par le gjTranslate, soit (0,0,-8).
        //A chaque fois qu'on rempli des coordonnees, on change de repere courant
        GL11.glPushMatrix();
        GL11.glRotatef(xrot, 1.0f, 0.0f, 0.0f); // Rotate On The X Axis
        GL11.glRotatef(yrot, 0.0f, 1.0f, 0.0f); // Rotate On The Y Axis
        GL11.glRotatef(zrot, 0.0f, 0.0f, 1.0f); // Rotate On The Z Axis        
        
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID); // Select Our Texture
      
        if (filter){
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER,GL11.GL_LINEAR); // contre l'aliasage proche
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_LINEAR); // contre l'aliasage lointain
        }else{
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER,GL11.GL_NEAREST); // contre l'aliasage proche
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_NEAREST); // contre l'aliasage lointain
        }
        
        GL11.glBegin(GL11.GL_QUADS);
        // Front Face
        //GL11.glColor3f(0.5f,0.5f,0.5f);
        //Creation du cube 1
        GL11.glNormal3f( 0.0f, 0.0f, 1.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Right Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Left Of The Texture and Quad
        // Back Face
        GL11.glNormal3f( 0.0f, 0.0f, -1.0f);        
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Right Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Left Of The Texture and Quad
        // Top Face
        GL11.glNormal3f( 0.0f, 1.0f, 0.0f);        
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        // Bottom Face
        GL11.glNormal3f( 0.0f, -1.0f, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        // Right face
        GL11.glNormal3f( 1.0f, 0.0f, 0.0f);        
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Right Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Left Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        // Left Face
        GL11.glNormal3f( -1.0f, 0.0f, 0.0f);        
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glEnd();

        //Animation. On modifie la rotation a chaque fois qu'on recharge la scene, donc a chaque image
        xrot += 0.081f; // X Axis Rotation
        yrot += 0.12f; // Y Axis Rotation
        zrot += 0.02f; // Z Axis Rotation
        
        //On reommence a travailler dans le repere courant qu'on avait memorise juste avant avec PushMatrix
        GL11.glPopMatrix();
        
        //Application d'une translation au cube SUIVANT en X
        //Lorsqu'on quand un état, tout ce qui suit est change. (Se rappeler du systeme de leviers)
        GL11.glTranslatef(-3.0f, 0.0f,0.0f);
        
        //Desactivation de l'eclairage car il faut desactiver l'eclairage pour voir les fils de fer
        GL11.glDisable(GL11.GL_LIGHTING);
        
        //On decide des rotations avant l'animation
        GL11.glRotatef(xrot, 1.0f, 0.0f, 0.0f); // Rotate On The X Axis
        GL11.glRotatef(yrot, 0.0f, 1.0f, 0.0f); // Rotate On The Y Axis
        GL11.glRotatef(zrot, 0.0f, 0.0f, 1.0f); // Rotate On The Z Axis 
        
        //On trace uniquement les lignes
        GL11.glBegin(GL11.GL_LINES);
        // GL_LINES ne trace des lignes qu'entre deux points. Il va falloir rajouter des points intermediaires entre chaque paires afin de tracer tous les segments.
        
        //Creation du cube 2
        // Front Face
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); //Duplication du point d'avant pour faire la lisaison
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); //Duplication du point d'apres pour faire la liaison
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Left Of The Texture and Quad
        // Back Face
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); //Duplication du point d'avant pour faire la lisaison
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); //Duplication du point d'apres pour faire la liaison
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Left Of The Texture and Quad
        // Top Face
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); //Duplication du point d'avant pour faire la lisaison
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); //Duplication du point d'apres pour faire la liaison
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        // Bottom Face
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); //Duplication du point d'avant pour faire la lisaison
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); //Duplication du point d'apres pour faire la liaison
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        // Right face
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); //Duplication du point d'avant pour faire la lisaison
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); //Duplication du point d'apres pour faire la liaison
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        // Left Face
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); //Duplication du point d'avant pour faire la lisaison
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); //Duplication du point d'apres pour faire la liaison
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glEnd();
        
        
        //On a desactive l'eclairage pour voir les fils. On peut maintenant le remettre
        GL11.glEnable(GL11.GL_LIGHTING);
        
        return true;
    }
    private void createWindow() throws Exception {
        Display.setFullscreen(fullscreen);
        DisplayMode d[] = Display.getAvailableDisplayModes();
        for (int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640
                && d[i].getHeight() == 480
                && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;
            }
        }
        Display.setDisplayMode(displayMode);
        Display.setTitle(windowTitle);
        Display.create();
    }
    private void init() throws Exception {
        createWindow();
        TextureLoader myTextureLoader;
        BufferedImage image = TextureLoader.loadImage("/TP1/res/logo-uvhc.bmp");//The path is inside the jar file
        textureID = TextureLoader.loadTexture(image);        
        initGL();
    }

    private void initGL() {
        GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
        GL11.glShadeModel(GL11.GL_SMOOTH); // Enable Smooth Shading
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
        GL11.glClearDepth(1.0); // Depth Buffer Setup
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
        GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Testing To Do

        GL11.glEnable(GL11.GL_CULL_FACE); // Back face culling 

        
        GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
        GL11.glLoadIdentity(); // Reset The Projection Matrix

        // Calculate The Aspect Ratio Of The Window
        GLU.gluPerspective(
          45.0f,
          (float) displayMode.getWidth() / (float) displayMode.getHeight(),
          0.1f,
          100.0f);

        GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix
        
        GL11.glEnable(GL11.GL_LIGHTING);

        FloatBuffer buffAmbient = BufferUtils.createFloatBuffer(4).put(lightAmbient);
        buffAmbient.position(0);
                
        FloatBuffer buffDiffuse = BufferUtils.createFloatBuffer(4).put(lightDiffuse);
        buffDiffuse.position(0);

        FloatBuffer buffPosition = BufferUtils.createFloatBuffer(4).put(lightPosition);
        buffPosition.position(0);
        
        //Creation d'un buffer. C'est uns structure de donnee qui reagit comme un tableau dynamique
        FloatBuffer buffSpecular = BufferUtils.createFloatBuffer(4).put(lightSpecularComponent);
        buffSpecular.position(0);
                
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, buffAmbient);
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, buffDiffuse);
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, buffPosition);
        //Declaration de la composante speculaire
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, buffSpecular);
        
        
        GL11.glEnable(GL11.GL_LIGHT1);
        
        //GL11.glEnable(GL11.GL_LIGHT0);
        
        
        // Really Nice Perspective Calculations
        //GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
    }
    private static void cleanup() {
        Display.destroy();
    }    
}
