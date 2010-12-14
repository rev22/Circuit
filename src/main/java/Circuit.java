// Circuit.java (c) 2005,2008 by Paul Falstad, www.falstad.com

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Circuit extends Applet implements ComponentListener {
    static CirSim ogf;
    void destroyFrame() {
	if (ogf != null)
	    ogf.dispose();
	ogf = null;
	repaint();
    }
    boolean started = false;
    public void init() {
	addComponentListener(this);
    }

    public static void main(String args[]) {
	ogf = new CirSim(null);
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    Preferences prefs = Preferences.userNodeForPackage(Circuit.class);
                    String currentFilename=ogf.currentFile.getAbsolutePath();
                    prefs.put("currentFile", currentFilename);
                    prefs.flush();
                } catch (BackingStoreException ex) {
                    ogf.showMessageDialog(ogf,ex.getMessage(),"Error");
                }
            }
        });
        
        MacUtil.init();
	ogf.init();
        
        EventQueue.invokeLater(new Runnable()  {

            public void run() {
                try {
                Preferences prefs = Preferences.userNodeForPackage(Circuit.class);
                String currentFilename = prefs.get("currentFile", null);

                if (currentFilename != null) {
                    ogf.readSetup(new File(currentFilename));
                }
                } catch (Exception ex) {
                    ogf.showMessageDialog(ogf,ex.getMessage(),"Error");
                }
            }
        });
        
        
    }
    
    void showFrame() {
	if (ogf == null) {
	    started = true;
	    ogf = new CirSim(this);
	    ogf.init();
	    repaint();
	}
    }

    public void toggleSwitch(int x) { ogf.toggleSwitch(x); }
    
    public void paint(Graphics g) {
	String s = "Applet is open in a separate window.";
	if (!started)
	    s = "Applet is starting.";
	else if (ogf == null)
	    s = "Applet is finished.";
	else if (ogf.useFrame)
	    ogf.triggerShow();
	g.drawString(s, 10, 30);
    }
    
    public void componentHidden(ComponentEvent e){}
    public void componentMoved(ComponentEvent e){}
    public void componentShown(ComponentEvent e) { showFrame(); }
    public void componentResized(ComponentEvent e) {
	if (ogf != null)
	    ogf.componentResized(e);
    }
    
    public void destroy() {
	if (ogf != null)
	    ogf.dispose();
	ogf = null;
	repaint();
    }
};

