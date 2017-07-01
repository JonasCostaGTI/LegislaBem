package br.com.osg.model;

public class Vereador {

	private Integer vereador_id;
	private String nome;
	private String partido;
	private Character status;
	private Character presente;
	
	
	public Integer getVereador_id() {
		return vereador_id;
	}
	public void setVereador_id(Integer vereador_id) {
		this.vereador_id = vereador_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPartido() {
		return partido;
	}
	public void setPartido(String partido) {
		this.partido = partido;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public Character getPresente() {
		return presente;
	}
	public void setPresente(Character presente) {
		this.presente = presente;
	}
	
	
}
