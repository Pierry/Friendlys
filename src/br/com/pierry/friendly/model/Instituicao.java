package br.com.pierry.friendly.model;

public class Instituicao {

	private String nome;
	private String cidade;
	private double latitude;
	private double longitude;
	
	public Instituicao(String nome, String cidade, double latitude, double longitude){
		this.nome = nome;
		this.cidade = cidade;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}
