package br.com.osg.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Sessao {

	private Integer sessao_id;
	
	private Character tipo;
	
	private Integer numero;
	
	private Date dataPrevista;
	
	private Date horaPrevista;
	
	@SerializedName("datarealizada")
	private Date dataRealizada;
	
	@SerializedName("horainicio")
	private Date horaInicio;
	
	@SerializedName("horatermino")
	private Date horaTermino;
	
	private Character status;
	
	
	public Integer getSessao_id() {
		return sessao_id;
	}
	public void setSessao_id(Integer sessao_id) {
		this.sessao_id = sessao_id;
	}
	public Character getTipo() {
		return tipo;
	}
	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Date getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public Date getHoraPrevista() {
		return horaPrevista;
	}
	public void setHoraPrevista(Date horaPrevista) {
		this.horaPrevista = horaPrevista;
	}
	public Date getDataRealizada() {
		return dataRealizada;
	}
	public void setDataRealizada(Date dataRealizada) {
		this.dataRealizada = dataRealizada;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraTermino() {
		return horaTermino;
	}
	public void setHoraTermino(Date horaTermino) {
		this.horaTermino = horaTermino;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	
	
	
}
