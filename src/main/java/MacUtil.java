
import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ermh
 */
public class MacUtil implements InvocationHandler {

    public static void init() {
        try {
            Class alclass = Class.forName("com.apple.eawt.ApplicationListener");
            Object applistener = Proxy.newProxyInstance(MacUtil.class.getClassLoader(), new Class[]{alclass}, new MacUtil());

            // Application macApplication = Application.getApplication();
            // macApplication.addApplicationListener(macAdapter);
            Class macappclass = Class.forName("com.apple.eawt.Application");
            Object macapp = macappclass.getMethod("getApplication").invoke(null);
            macappclass.getMethod("addApplicationListener", alclass).
                    invoke(macapp, applistener);


        } catch (Exception ex) {
            Logger.getLogger(MacUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Logger.getLogger(MacUtil.class.getName()).log(Level.INFO,
                    method.getName() + "(" + Arrays.asList(args) + ") : "
                    + Arrays.asList(method.getParameterTypes()));

            Class<?>[] signature = new Class<?>[args.length];
            Arrays.fill(signature, Object.class);

            Method delegate = getClass().getMethod(method.getName(), signature);
            if (delegate != null) {
                return delegate.invoke(this, args);
            }


        } catch (NoSuchMethodException ex) {
        } catch (Exception ex) {
            Logger.getLogger(MacUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        Logger.getLogger(MacUtil.class.getName()).log(Level.INFO,
                method + " not implemented");
        return null;
    }

    public void handleQuit(Object applicationEvent) {
        Circuit.ogf.destroyFrame();
    }

    public void handleOpenApplication(Object applicationEvent) {
        openFile(applicationEvent);
    }

    public void handleOpenFile(Object applicationEvent) {
        openFile(applicationEvent);
    }

    public void handleReOpenApplication(Object applicationEvent) {
        openFile(applicationEvent);
    }

    void openFile(Object applicationEvent) {
        try {
            String filename = (String) applicationEvent.getClass().getMethod("getFilename", new Class<?>[0]).invoke(applicationEvent, null);

            if (filename != null) {
                if (Circuit.ogf.firstCircuit) {
                    Logger.getLogger(MacUtil.class.getName()).log(Level.INFO, "Start Circuit: " + filename);
                    Circuit.ogf.startCircuit = filename;
                    Circuit.ogf.startLabel = new File(filename).getName();
                } else {
                    Logger.getLogger(MacUtil.class.getName()).log(Level.INFO, "Loading: " + filename);
                    Circuit.ogf.readSetupFile(new File(filename));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MacUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
