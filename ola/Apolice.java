package src.edu.ufp.inf.lp2.ola;

import src.edu.ufp.inf.lp2.pratico._3_course.version2.Date;

public abstract class Apolice {
    private int numero;
    private Date dataInit;
    private Date dataFim;
    private float cobertura;
    private float premioBase;
    private Cliente cliente;

    public Apolice(int numero, Date dataInit, Date dataFim, float cobertura, float premioBase, Cliente cliente) {
        this.numero = numero;
        this.dataInit = dataInit;
        this.dataFim = dataFim;
        this.cobertura = cobertura;
        this.premioBase = premioBase;
        this.cliente = cliente;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDataInit() {
        return dataInit;
    }

    public void setDataInit(Date dataInit) {
        this.dataInit = dataInit;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public float getCobertura() {
        return cobertura;
    }

    public void setCobertura(float cobertura) {
        this.cobertura = cobertura;
    }

    public float getPremioBase() {
        return premioBase;
    }

    public void setPremioBase(float premioBase) {
        this.premioBase = premioBase;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float premio(){
        return premioBase;
    }
}
