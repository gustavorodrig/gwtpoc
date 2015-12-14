package br.tur.reservafacil.cadastro.client;

import br.tur.reservafacil.cadastro.server.dao.UsuarioDAO;
import br.tur.reservafacil.cadastro.shared.UsuarioDTO;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DefaultDateTimeFormatInfo;
import com.google.gwt.i18n.client.impl.cldr.DateTimeFormatInfoImpl_pt;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

import java.util.HashMap;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Cadastro
		implements EntryPoint {

    public static int contador = 0;

    private VerticalPanel mainPanel          = new VerticalPanel();
    private FlexTable     cadastrosFlexTable = new FlexTable();
    private FlexTable     adicionaFlexTable  = new FlexTable();

    private VerticalPanel addPanelV = new VerticalPanel();

    private HorizontalPanel addPanel  = new HorizontalPanel();
    private HorizontalPanel addPanel2 = new HorizontalPanel();

    private Label idLabel             = new Label("");
    private Label nomeLabel           = new Label("Nome: ");
    private Label sobrenomeLabel      = new Label("Sobrenome: ");
    private Label dataNascimentoLabel = new Label("Data de Nascimento: ");

    private TextBox   nomeTextBox           = new TextBox();
    private TextBox   sobrenomeTextBox      = new TextBox();
    private DateBox   dataNascimentoTextBox = new DateBox();
    private Button    adicionaButton        = new Button("Salvar");
    private Button    fecharButton          = new Button("Fechar");
    private Label     lastUpdatedLabel      = new Label();
    private DialogBox box                   = new DialogBox();

    private final CadastroServiceAsync cadastroService = GWT.create(CadastroService.class);

    public void onModuleLoad() {

	cadastrosFlexTable.setBorderWidth(2);

	idLabel.setVisible(false);

	fecharButton.setVisible(false);

	//cadastrosFlexTable.setText(0, 0, "Nome");
	//cadastrosFlexTable.setText(0, 1, "Sobrenome");
	//cadastrosFlexTable.setText(0, 2, "Data Nascimento");
	//cadastrosFlexTable.setText(0, 3, "Editar");
	//cadastrosFlexTable.setText(0, 3, "Editar Popup");
	//cadastrosFlexTable.setText(0, 4, "Remove");

	atualizaLista();

	adicionaFlexTable.setStyleName("boxDialog_style");
	adicionaFlexTable.setWidget(0, 0, nomeLabel);
	adicionaFlexTable.setWidget(0, 1, nomeTextBox);
	adicionaFlexTable.setWidget(1, 0, sobrenomeLabel);
	adicionaFlexTable.setWidget(1, 1, sobrenomeTextBox);
	adicionaFlexTable.setWidget(2, 0, dataNascimentoLabel);
	adicionaFlexTable.setWidget(2, 1, dataNascimentoTextBox);

	DefaultDateTimeFormatInfo formatDE = new DateTimeFormatInfoImpl_pt();
	dataNascimentoTextBox.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat(formatDE.dateFormatShort())));

	adicionaFlexTable.setWidget(3, 0, adicionaButton);
	adicionaFlexTable.setWidget(3, 1, fecharButton);

	mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	mainPanel.setStyleName("boxDialog_style");
	mainPanel.add(cadastrosFlexTable);
	mainPanel.add(idLabel);
	mainPanel.add(adicionaFlexTable);
	mainPanel.add(lastUpdatedLabel);

	RootPanel.get("cadastroList").add(mainPanel);
	nomeTextBox.setFocus(true);

	fecharButton.addClickHandler(new ClickHandler() {

	    public void onClick(ClickEvent event) {
		box.hide();
		fecharButton.setVisible(false);
		mainPanel.add(adicionaFlexTable);
		limpaFormulario();
	    }
	});

	adicionaButton.addClickHandler(new ClickHandler() {

	    public void onClick(ClickEvent event) {
		adicionaCadastro();
	    }
	});

	nomeTextBox.addKeyDownHandler(new KeyDownHandler() {

	    public void onKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		    adicionaCadastro();
		}
	    }
	});
    }

    private void adicionaCadastro() {

	nomeTextBox.setFocus(true);
	String id = "".equals(idLabel.getText()) ? String.valueOf(contador) : idLabel.getText();

	GWT.log("ID: " + id);

	AsyncCallback<Void> callbackAdiciona = new AsyncCallback<Void>() {

	    @Override public void onFailure(Throwable caught) {
		Window.alert("ERRO AO INSERIR");
	    }

	    @Override public void onSuccess(Void result) {
		atualizaLista();
	    }
	};
	cadastroService.salva(id, nomeTextBox.getText(), sobrenomeTextBox.getText(), dataNascimentoTextBox.getValue(), callbackAdiciona);
	contador += 1;

    }

    private void removeCadastro(String id) {

	AsyncCallback<Void> callbackRemove = new AsyncCallback<Void>() {

	    @Override public void onFailure(Throwable caught) {
		Window.alert("ERRO AO Deletar");
	    }

	    @Override public void onSuccess(Void result) {
		atualizaLista();
	    }
	};
	cadastroService.remove(id, callbackRemove);
	contador += 1;
    }

    private void editaCadastro(String id) {

	preencheCadastro(id);

    }

    private void preencheCadastro(String id) {

	AsyncCallback<UsuarioDTO> callbackBuscaPorId = new AsyncCallback<UsuarioDTO>() {

	    @Override public void onFailure(Throwable caught) {
		Window.alert("ERRO AO Buscar Por Id");
	    }

	    @Override public void onSuccess(UsuarioDTO result) {
		idLabel.setText(result.getId());
		nomeTextBox.setText(result.getNome());
		sobrenomeTextBox.setText(result.getSobrenome());
		dataNascimentoTextBox.setValue(result.getDataNascimento());
	    }
	};
	cadastroService.buscaPorId(id, callbackBuscaPorId);
    }

    private void editaCadastroPopup(String id) {

	preencheCadastro(id);
	fecharButton.setVisible(true);
	box.addStyleName("boxDialog_style");
	box.setAutoHideEnabled(false);
	box.setModal(true);
	box.center();
	box.setText("Editar Usuário");
	box.setAnimationEnabled(true);
	box.setGlassEnabled(true);
	box.setWidget(adicionaFlexTable);
	box.show();

    }

    private void atualizaLista() {

	cadastrosFlexTable.removeAllRows();
	int row = 0;

	cadastrosFlexTable.setText(row, 0, "Nome");
	cadastrosFlexTable.setText(row, 1, "Sobrenome");
	cadastrosFlexTable.setText(row, 2, "Data Nascimento");
	cadastrosFlexTable.setText(row, 3, "Editar");
	cadastrosFlexTable.setText(row, 4, "Editar Popup");
	cadastrosFlexTable.setText(row, 5, "Remove");

	GWT.log("Gerando List");

	AsyncCallback<HashMap<String, UsuarioDTO>> callbackLista = new AsyncCallback<HashMap<String, UsuarioDTO>>() {

	    @Override public void onFailure(Throwable caught) {
		Window.alert("ERRO AO Atualizar Lista");
	    }

	    @Override public void onSuccess(HashMap<String, UsuarioDTO> result) {

		GWT.log(result.toString());

		int row = 0;
		for (String id : result.keySet()) {

		    GWT.log("ID FOR: " + id);

		    UsuarioDTO usuarioDTO = result.get(id);
		    row += 1;
		    cadastrosFlexTable.setText(row, 0, usuarioDTO.getNome());
		    cadastrosFlexTable.setText(row, 1, usuarioDTO.getSobrenome());
		    cadastrosFlexTable.setText(row, 2, usuarioDTO.getDataNascimento().toString());

		    Button removeCadastroButton = new Button("x");
		    removeCadastroButton.getElement().setId(usuarioDTO.getId());
		    removeCadastroButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
			    removeCadastro(event.getRelativeElement().getId());
			}
		    });

		    Button editaCadastroButton = new Button(">");
		    editaCadastroButton.getElement().setId(usuarioDTO.getId());
		    editaCadastroButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
			    editaCadastro(event.getRelativeElement().getId());
			}
		    });

		    Button editaCadastroPopUpButton = new Button("Editar PopUp");
		    editaCadastroPopUpButton.getElement().setId(usuarioDTO.getId());
		    editaCadastroPopUpButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
			    editaCadastroPopup(event.getRelativeElement().getId());
			}
		    });

		    cadastrosFlexTable.setWidget(row, 3, editaCadastroButton);
		    cadastrosFlexTable.setWidget(row, 4, editaCadastroPopUpButton);
		    cadastrosFlexTable.setWidget(row, 5, removeCadastroButton);

		}
	    }
	};

	cadastroService.lista(callbackLista);
	limpaFormulario();

    }

    private void limpaFormulario() {

	idLabel.setText("");
	nomeTextBox.setText("");
	sobrenomeTextBox.setText("");
	dataNascimentoTextBox.setValue(null);

    }

}
