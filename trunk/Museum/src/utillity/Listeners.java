/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utillity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author markh_000
 */
public class Listeners {
    private static Listeners listeners;
    private ArrayList<ActionListener> listenersList;


    private Listeners() {
        listenersList = new ArrayList<>();

    }
    
    public static Listeners getList(){
        if (listeners == null) {
            listeners = new Listeners();
        }
        return listeners;
    }

    public void addListener(ActionListener listener) {
        listenersList.add(listener);

    }

    public void notifyListeners(String event) {
        for (ActionListener actionListener : listenersList) {
            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, event));
        }

    }
}
