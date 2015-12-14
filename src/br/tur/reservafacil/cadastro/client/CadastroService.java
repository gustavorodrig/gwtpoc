package br.tur.reservafacil.cadastro.client;

import br.tur.reservafacil.cadastro.server.dao.UsuarioDAO;
import br.tur.reservafacil.cadastro.shared.UsuarioDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.Date;
import java.util.HashMap;

@RemoteServiceRelativePath("cadastroService")
public interface CadastroService extends RemoteService {
    // Sample interface method of remote interface
    UsuarioDTO getUsuario(String id, String nome, String sobrenome, Date dataNascimento);

    void salva(String id, String nome, String sobrenome, Date dataNascimento);

    void remove(String id);

    HashMap<String, UsuarioDTO> lista();

    UsuarioDTO buscaPorId(String id);
}
