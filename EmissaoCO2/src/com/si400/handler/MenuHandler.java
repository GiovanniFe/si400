package com.si400.handler;

import com.si400.model.Emissions;
import com.si400.view.MenuView;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 *
 * @author Giovanni
 */
public class MenuHandler {

    private final MenuView view;

    public MenuHandler(MenuView v) {
        view = v;
        addEvents();
        view.setVisible(true);
    }

    private void addEvents() {
        addMouseButtonEffects(view.getBtn1());
        addMouseButtonEffects(view.getBtn2());
        addMouseButtonEffects(view.getBtn3());
        addMouseButtonEffects(view.getBtn4());
        addMouseButtonEffects(view.getBtnSair());

        view.getBtnSair().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    new Emissions().load();
                } catch (IOException io) {
                }
            }
        });
    }

    private void addMouseButtonEffects(Component c) {
        c.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
                view.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                view.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
    }

}
