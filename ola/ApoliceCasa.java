package src.edu.ufp.inf.lp2.ola;

import src.edu.ufp.inf.lp2.pratico._3_course.version2.Date;

public class ApoliceCasa extends Apolice{
    private String endereco;
    private Date construcao;
    private float area;

    public ApoliceCasa(int numero, Date dataInit, Date dataFim, float cobertura, float premioBase, Cliente cliente, String endereco, Date construcao, float area) {
        super(numero, dataInit, dataFim, cobertura, premioBase, cliente);
        this.endereco = endereco;
        this.construcao = construcao;
        this.area = area;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getConstrucao() {
        return construcao;
    }

    public void setConstrucao(Date construcao) {
        this.construcao = construcao;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float premio(){
        Date hoje = new Date();
        int dif = hoje.differenceYears(construcao);
        float premio = super.premio();
        return premio + (float)((0.005 * area) + (0.01 * dif));
    }
}
