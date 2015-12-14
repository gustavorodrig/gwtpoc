package br.tur.reservafacil.cadastro.server.dao;

import br.tur.reservafacil.cadastro.shared.UsuarioDTO;
import com.google.gwt.core.client.GWT;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by gustavo on 07/12/15.
 */
public class UsuarioDAO implements UsuarioI, Serializable {



    @Override
    public void salva(String id, String nome, String sobrenome, Date dataNascimento) {
        System.out.println("ID" + id);
        CadastrosBD.cadastros.put(id, new UsuarioDTO(id, nome, sobrenome, dataNascimento));
        System.out.println("Cadastros: " +CadastrosBD.cadastros.values());
    }

    @Override
    public void atualiza() {

    }

    @Override public UsuarioDTO buscaPorId(String id) {
        return CadastrosBD.cadastros.get(id);
    }

    @Override
    public void remove(String id) {
        CadastrosBD.cadastros.remove(id);
    }

    @Override
    public HashMap<String, UsuarioDTO> lista() {
        return CadastrosBD.cadastros;
    }
}
