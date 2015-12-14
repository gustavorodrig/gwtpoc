package br.tur.reservafacil.cadastro.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gustavo on 02/12/15.
 */
public class UsuarioDTO
		implements Serializable {

    private String id;
    private String nome;
    private String sobrenome;
    private Date   dataNascimento;

    public UsuarioDTO() {

    }

    public UsuarioDTO(String id, String nome, String sobrenome, Date dataNascimento) {
	this.setId(id);
	this.setNome(nome);
	this.setSobrenome(sobrenome);
	this.setDataNascimento(dataNascimento);
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;

    }

    public String getNome() {
	return nome;

    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getSobrenome() {
	return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
	this.sobrenome = sobrenome;
    }

    public Date getDataNascimento() {
	return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
	this.dataNascimento = dataNascimento;
    }

}
