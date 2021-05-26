package src.edu.ufp.inf.lp2.ola;

import src.edu.ufp.inf.lp2.pratico._3_course.version2.Date;

import java.util.ArrayList;

public class Cliente {
    private int nif;
    private String nome;
    private Date nasceu;
    private int carta;
    private Date dataCarta;
    private ArrayList<Apolice> apolice = new ArrayList<>();
    private Cliente proximoCliente;

    public static void main(String[] args) {
        Cliente c1 = new Cliente(1);
        Cliente c2 = new Cliente(2);
        Cliente c3 = new Cliente(3);
        Cliente c4 = new Cliente(4);
        Cliente c5 = new Cliente(5);
        c1.proximoCliente = c2;
        c2.proximoCliente = c3;
        c3.proximoCliente = c4;
        c4.proximoCliente = c5;
        c1.verClientePorOrdem();
    }

    public void verClientePorOrdem(){
        Cliente c = this;
        while (c != null){
            System.out.println(c.getNif());
            c = c.proximoCliente;
        }
    }

    public Cliente(int nif) {
        this.nif = nif;
    }

    public Cliente(int nif, String nome, Date nasceu, int carta, Date dataCarta, ArrayList<Apolice> apolices) {
        this.nif = nif;
        this.nome = nome;
        this.nasceu = nasceu;
        this.carta = carta;
        this.dataCarta = dataCarta;
        this.apolice = apolices;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNasceu() {
        return nasceu;
    }

    public void setNasceu(Date nasceu) {
        this.nasceu = nasceu;
    }

    public int getCarta() {
        return carta;
    }

    public void setCarta(int carta) {
        this.carta = carta;
    }

    public Date getDataCarta() {
        return dataCarta;
    }

    public void setDataCarta(Date dataCarta) {
        this.dataCarta = dataCarta;
    }

    public ArrayList<Apolice> getApolices() {
        return apolice;
    }

    public void setApolices(ArrayList<Apolice> apolices) {
        this.apolice = apolices;
    }

    public Apolice[] apolices(Date di, Date df){
        ArrayList<Apolice> temp = new ArrayList<>();
        for (Apolice a : apolice){
            if(a.getDataInit().afterDate(di) && a.getDataFim().beforeDate(df)) {
                temp.add(a);
            }
            /*
            if(a.getDataInit().afterDate(di) && a.getDataInit().beforeDate(df)) {
                temp.add(a);
            }
            */
        }
        return (Apolice[]) temp.toArray();
    }

    public Apolice[] apolices(String types){
        ArrayList<Apolice> temp = new ArrayList<>();
        for (Apolice a : apolice){
            if(types.equals("apoliceCasa")) {
                if(a instanceof ApoliceCasa)
                    temp.add(a);
            }else{
                System.out.println("ApoliceCarro");
            }
        }
        return (Apolice[]) temp.toArray();
    }

    public Apolice ApoliceMaisCara(){
        float valor = 0;
        int pos = 0;
        for (int i = 0; i< apolice.size(); i++){
            if(apolice.get(i).getCobertura() > valor){
                valor = apolice.get(i).getCobertura();
                pos = i;
            }
        }
        return apolice.get(pos);
    }

    public void novaApolice(Apolice a){
        if(!apolice.contains(a))
            apolice.add(a);
    }

    public void revogaApolice(Apolice a){
        if(apolice.contains(a))
            apolice.remove(a);
    }

}
