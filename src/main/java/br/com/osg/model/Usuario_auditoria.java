package br.com.osg.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Usuario_auditoria {
	
	private Integer usuario_auditoria_id;
	
	private Usuario usuario_id;
	
	@SerializedName("datahora")
	private Date dataHora;
	
	private String ip;
	
	private String operacao;
	
	
	
	public Integer getUsuario_auditoria_id() {
		return usuario_auditoria_id;
	}
	public void setUsuario_auditoria_id(Integer usuario_auditoria_id) {
		this.usuario_auditoria_id = usuario_auditoria_id;
	}
	public Usuario getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(Usuario usuario_id) {
		this.usuario_id = usuario_id;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	

}
