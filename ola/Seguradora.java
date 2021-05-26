package src.edu.ufp.inf.lp2.ola;

import edu.princeton.cs.algs4.ST;
import src.edu.ufp.inf.lp2.pratico._3_course.version2.Date;

import java.io.*;
import java.util.ArrayList;

public class Seguradora {
    private String nome;
    ArrayList<Apolice> apolices = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();
    ST<Integer, Apolice> st =  new ST<>();

    public Seguradora(String nome, ArrayList<Apolice> apolices, ArrayList<Cliente> clientes) {
        this.nome = nome;
        this.apolices = apolices;
        this.clientes = clientes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Apolice> getApolices() {
        return apolices;
    }

    public void setApolices(ArrayList<Apolice> apolices) {
        this.apolices = apolices;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void novoContrato(Apolice a, Cliente c){
        st.put(a.getNumero(), a);
        c.getApolices().add(a);  //lista de apolices do cliente e adiciono a nova apolice
        a.setCliente(c);         //apolice e adicionei o cliente
        apolices.add(a);
        clientes.add(c);
    }

    public Apolice procuraApolice(int num){
        if(apolices.contains(num))
            return apolices.get(num);
        //throw new NAOEXISTEAPOLICE;
        return null;
    }

    public Apolice[] procuraApolices(int nif, Date df, Date di){
        ArrayList<Apolice> temp = new ArrayList<>();
        for (Apolice a : apolices){
            if(a.getCliente().getNif() == nif && a.getDataFim().beforeDate(df) && a.getDataInit().afterDate(di))
                temp.add(a);
        }
        return (Apolice[]) temp.toArray();
    }

    public Cliente procuraCliente(int nif){
        for (Cliente c: clientes){
            if(c.getNif() == nif)
                return c;
        }
        return null;
    }

    public Cliente procuraClienteA(int apoliceNum){
        for (Apolice a: apolices){
            if(a.getNumero() == apoliceNum)
                return a.getCliente();
        }
        return null;
    }

    public Apolice[] procuraApolices(int nif, String type){
        ArrayList<Apolice> temp = new ArrayList<>();
        for (Apolice a : apolices){
            if(a.getCliente().getNif() == nif){
                if(type.equals("ApoliceCasa")){
                    if(a instanceof ApoliceCasa)
                        temp.add(a);
                }
                if(type.equals("ApoliceCarro")){
                    //if(a instanceof ApoliceCasa)
                        //temp.add(a);
                    System.out.println("ola");
                }

            }

        }
        return (Apolice[]) temp.toArray();
    }

    public void eliminaContrato(Apolice a, Cliente c){
        st.remove(a.getNumero());
        c.getApolices().remove(a);
        apolices.remove(a);
    }

    public void saveTxt(){
        String filename = "ique.txt";
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(new File(filename)));
            int tam = apolices.size();
            pw.println("APOLICES:"+tam);
            String toFile = null;
            for (Apolice a : apolices){
                if(a instanceof ApoliceCasa)
                    toFile ="ApoliceCasa" + ";" + a.getCliente().getNif() + ";" + a.getCobertura() + ";" + a.getNumero() + ";" + a.getDataInit() + ";" + a.getDataFim() + ";" + a.getPremioBase() + ";" + ((ApoliceCasa) a).getEndereco() + ";" + ((ApoliceCasa) a).getConstrucao() + ";" + ((ApoliceCasa) a).getArea();
                pw.println(toFile);
            }
            tam = clientes.size();
            pw.println("CLIENTES:"+tam);
            for (Cliente c : clientes){
                toFile = c.getNome() + ";" + c.getApolices() + ";" + c.getNif() + ";" + c.getCarta() + ";" + c.getDataCarta() + ";" + c.getNasceu();
                pw.println(toFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerTxt(){
        String filename = "ique.txt";
        try{
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            int nif, num, prem;
            float cob, area;
            String di, df, d;
            String endereco;
            String line = null;
            while ((line = br.readLine()) != null){
                String parts[] = line.split(";");
                String tipo = parts[0];
                nif = Integer.parseInt(parts[1]);
                cob = Float.parseFloat(parts[2]);
                num = Integer.parseInt(parts[3]);
                di = parts[4];
                df = parts[5];
                prem = Integer.parseInt(parts[6]);
                endereco = parts[7];
                d = parts[8];
                area = Float.parseFloat(parts[9]);
                Date d1 = new Date(di);
                Date d2 = new Date(df);
                Date d3 = new Date(d);
                if(parts[0].equals("ApoliceCasa")){
                    ApoliceCasa a = new ApoliceCasa(num, d1, d2, cob, prem, clientes.get(nif), endereco, d3, area);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
