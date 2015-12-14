package br.tur.reservafacil.cadastro.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created by gustavo on 09/12/15.
 */
public class Index implements EntryPoint {

    public void onModuleLoad() {
	RootPanel.get().add(new Login());
    }

}
