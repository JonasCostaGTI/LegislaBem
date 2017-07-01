package br.com.osg.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class SessaoVereador {

	private Integer sessao_vereador_id;
	
	@SerializedName("sessao_id")
	private Integer sessao;
	
	@SerializedName("vereador_id")
	private Integer vereador;
	
	@SerializedName("datahoraevento")
	private Date dataHoraEvento;
	
	private Character evento;
	
	private String vereador_nome;
	

	public Integer getSessao_vereador_id() {
		return sessao_vereador_id;
	}

	public void setSessao_vereador_id(Integer sessao_vereador_id) {
		this.sessao_vereador_id = sessao_vereador_id;
	}

	public Integer getSessao() {
		return sessao;
	}

	public void setSessao(Integer sessao) {
		this.sessao = sessao;
	}

	public Integer getVereador() {
		return vereador;
	}

	public void setVereador(Integer vereador) {
		this.vereador = vereador;
	}

	public Date getDataHoraEvento() {
		return dataHoraEvento;
	}

	public void setDataHoraEvento(Date dataHoraEvento) {
		this.dataHoraEvento = dataHoraEvento;
	}

	public String getVereador_nome() {
		return vereador_nome;
	}

	public void setVereador_nome(String vereador_nome) {
		this.vereador_nome = vereador_nome;
	}

	public Character getEvento() {
		return evento;
	}

	public void setEvento(Character evento) {
		this.evento = evento;
	}
	
	

	
	
	
	
}
