package ED2015;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe que identifica um caminho ou aresta com peso.
 * @author Utilizador
 */
public class Caminho {
    
    private double precoKm;
    private Hora[] horarios;
    private double distancia;
    private int duracao;

    public Caminho(double precoKm, Hora[] horarios, double distancia, int duracao) {
        this.precoKm = precoKm;
        this.horarios = horarios;
        this.distancia = distancia;
        this.duracao = duracao;
    }

    public double getPrecoKm() {
        return precoKm;
    }

    public Hora[] getHorarios() {
        return horarios;
    }

    public double getDistancia() {
        return distancia;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setPrecoKm(double precoKm) {
        this.precoKm = precoKm;
    }

    public void setHorarios(Hora[] horarios) {
        this.horarios = horarios;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
    
    
    
    
}
