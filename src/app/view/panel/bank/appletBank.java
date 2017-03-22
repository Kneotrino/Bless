/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.panel.bank;

import javax.swing.JApplet;

/**
 *
 * @author SEED
 */
public class appletBank extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
        this.add(new bankPanel());
    }

    // TODO overwrite start(), stop() and destroy() methods
}
