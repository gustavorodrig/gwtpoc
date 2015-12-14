package br.tur.reservafacil.cadastro.client;

import br.tur.reservafacil.cadastro.server.dao.UsuarioDAO;
import br.tur.reservafacil.cadastro.shared.UsuarioDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;
import java.util.HashMap;

public interface CadastroServiceAsync {

    // Sample interface method of remote interface
    void getUsuario(String id, String nome, String sobrenome, Date dataNascimento, AsyncCallback<UsuarioDTO> async);

    void salva(String id, String nome, String sobrenome, Date dataNascimento, AsyncCallback<Void> async);

    void remove(String id, AsyncCallback<Void> async);

    void lista(AsyncCallback<HashMap<String, UsuarioDTO>> async);

    void buscaPorId(String id, AsyncCallback<UsuarioDTO> async);
}

