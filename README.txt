This java application is an electronic circuit simulator.  When the application 
starts up you will see an animated schematic of a simple LRC circuit. 
The green color indicates positive voltage.  The gray color indicates ground.  
A red color indicates negative voltage.  The moving yellow dots indicate current.

To turn a switch on or off, just click on it.  If you move the mouse over any 
component of the circuit, you will see a short description of that component and 
its current state in the lower right corner of the window.  
To modify a component, move the mouse over it, click the right mouse button 
(or control-click if you have a Mac) and select “Edit”.

The application is an modification of the applet created by Paul Falstad. Added
are load and save of circuit files.

See http://www.falstad.com/circuit/ for the original.

HOWTO Build
===========

Netbeans, works out of the box

Eclipse

    install maven and run the following command
    $ mvn eclipse:eclipse
    
in the project directory
    import circuit as an existing project into eclipse
    run the class Circuit

If you are neither using Netbeans or Eclipse, you should check the documentation

Package
    mvn assembly:assembly

If you build it on a mac, it will create a mac app bundle as well.

Erik Martino <erik.martino@gmail.com>

