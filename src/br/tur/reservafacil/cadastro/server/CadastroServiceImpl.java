package br.tur.reservafacil.cadastro.server;

import br.tur.reservafacil.cadastro.server.dao.CadastrosBD;
import br.tur.reservafacil.cadastro.server.dao.UsuarioDAO;
import br.tur.reservafacil.cadastro.shared.UsuarioDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import br.tur.reservafacil.cadastro.client.CadastroService;

import java.util.Date;
import java.util.HashMap;

public class CadastroServiceImpl
		extends RemoteServiceServlet
		implements CadastroService {

    public UsuarioDTO getUsuario(String id, String nome, String sobrenome, Date dataNascimento) {
	return new UsuarioDTO(id, nome, sobrenome, dataNascimento);
    }

    @Override public void salva(String id, String nome, String sobrenome, Date dataNascimento) {
	new UsuarioDAO().salva(id, nome, sobrenome, dataNascimento);
    }

    @Override public void remove(String id) {
	new UsuarioDAO().remove(id);
    }

    @Override public HashMap<String, UsuarioDTO> lista() {
	return new UsuarioDAO().lista();
    }

    @Override public UsuarioDTO buscaPorId(String id) {
	return new UsuarioDAO().buscaPorId(id);
    }

}