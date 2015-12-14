package br.tur.reservafacil.cadastro.server.dao;

import br.tur.reservafacil.cadastro.shared.UsuarioDTO;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by gustavo on 07/12/15.
 */
public interface UsuarioI {


    public void salva(String id, String nome, String sobrenome, Date dataNascimento);
    public void atualiza();
    public void remove(String id);
    public UsuarioDTO buscaPorId(String id);
    public HashMap<String, UsuarioDTO> lista();

}
