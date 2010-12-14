
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
        if (method.getName().equals("handleQuit")) {
            Circuit.ogf.destroyFrame();
        }
        return null;
    }
}
