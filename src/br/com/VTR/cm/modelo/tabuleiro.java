package br.com.VTR.cm.modelo;

import test.br.com.vtr.cm.modelo.CampoTeste;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class tabuleiro {

    private int linhas;
    private int colunas;
    private int minas;

    private final List<campo> campos = new ArrayList<>();

    public tabuleiro(int minas, int colunas, int linhas) {
        this.minas = minas;
        this.colunas = colunas;
        this.linhas = linhas;

        gerarCampos();
        associarOsVizinhos();
        sortearMinas();
    }

    public void abrir(int linha, int colunas){
       try{
           campos.stream()
                   .filter(c -> c.getLinha() == linha && c.getColuna() == colunas)
                   .findFirst()
                   .ifPresent(c -> c.abrir());
       } catch (Exception e){
           campos.forEach(c -> c.setAberto(true));
           throw e;
       }
    }

    public void marcar(int linha, int colunas){
        campos.stream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == colunas)
                .findFirst()
                .ifPresent(c -> c.alterarMarcador());
    }

    private void sortearMinas() {
        long minasArmadas = 0;
        Predicate<campo> minado = c->c.isMinado();
        do{
            minasArmadas = campos.stream().filter(minado).count();
            int aleatorio = (int) (Math.random() * campos.size()) ;
            campos.get(aleatorio).minar();
        } while (minasArmadas < minas);
    }

    public boolean objetivoAlcancado(){
        return campos.stream().allMatch(c -> c.objetivoAlcancado());
    }

    private void associarOsVizinhos() {
        for(campo c1: campos){
            for(campo c2: campos){
                c1.adicionarVizinho(c2);
            }
        }
    }

    private void gerarCampos() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                campos.add(new campo(i, j, false));
            }
        }
    }

    public void reiniciar(){
        campos.stream().forEach(c -> c.reiniciar());
        sortearMinas();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("");
        for (int c = 0; c < colunas ; c++) {
            sb.append(" ");
            sb.append(c);
            sb.append(" ");
        }

        sb.append("\n");

        int i =0;
        for(int l = 0; l < linhas; l++){
            sb.append(l);
            sb.append("");

            for (int c = 0; c < colunas ; c++) {
                sb.append("");
                sb.append(campos.get(i));
                sb.append("");
                i++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
