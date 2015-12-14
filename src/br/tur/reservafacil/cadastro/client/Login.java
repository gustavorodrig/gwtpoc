package br.tur.reservafacil.cadastro.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

/**
 * Created by gustavo on 09/12/15.
 */
public class Login
		extends Composite {

    private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);

    /*
    * @UiTemplate is not mandatory but allows multiple XML templates
    * to be used for the same widget.
    * Default file loaded will be <class-name>.ui.xml
    */
    @UiTemplate("Login.ui.xml") interface LoginUiBinder
		    extends UiBinder<Widget, Login> {

    }

    @UiField(provided = true) final LoginResources res;

    public Login() {
	this.res = GWT.create(LoginResources.class);

	GWT.log(res.style().ensureInjected() + "CSS INJETADO");
	res.style().ensureInjected();
	initWidget(uiBinder.createAndBindUi(this));
    }

    @UiField TextBox loginBox;
    @UiField TextBox passwordBox;
    @UiField Label   mensagemLabel1;
    @UiField Label   mensagemLabel2;

    @UiHandler("buttonSubmit") void doClickSubmit(ClickEvent event) {
	GWT.log("User: " + loginBox.getValue());
	GWT.log("Senha: " + passwordBox.getValue());

	if (loginBox.getValue().equals(passwordBox.getValue())) {
	    GWT.log("Senha correta");
	    new Cadastro().onModuleLoad();
	    this.setVisible(false);
	} else {
	    Window.alert("Usuario diferente de Senha!");
	}
    }

    @UiHandler("loginBox") void handleLoginChange(ValueChangeEvent<String> event) {
	if (event.getValue().length() < 6) {
	    mensagemLabel1.setText("Usuario deve ser maior que 6 caracteres");
	} else {
	    mensagemLabel1.setText("");
	}
    }

    @UiHandler("passwordBox") void handlePasswordChange(ValueChangeEvent<String> event) {
	if (event.getValue().length() < 6) {
	    mensagemLabel2.setText("Senha deve ser maior que 6 digitos)");
	} else {
	    mensagemLabel2.setText("");
	}
    }
}