package br.com.VTR.cm.modelo;
import br.com.VTR.cm.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class campo {

    private final int linha;
    private final int coluna;

    private boolean aberto =false;
    private boolean minado = false;
    private boolean marcado = false;

    private List<campo> vizinhos = new ArrayList<>();

     public campo(int linha, int coluna, boolean minado) {
        this.linha = linha;
        this.coluna = coluna;
        this.minado = minado;
    }

    public boolean adicionarVizinho(campo vizinho){
         boolean linhaDiferente = linha != vizinho.linha;
         boolean colunaDiferente = coluna != vizinho.coluna;
         boolean diagonal = linhaDiferente && colunaDiferente;

         int deltaLinha = Math.abs(linha - vizinho.linha);
         int deltaColuna = Math.abs(coluna - vizinho.coluna);
         int deltaGeral = deltaLinha + deltaColuna;

         if(deltaGeral == 1 && !diagonal){
            vizinhos.add(vizinho);
            return true;
         }
         else if (deltaGeral == 2 && diagonal){
             vizinhos.add(vizinho);
             return true;
         } else {
             return false;
         }
    }

    public void alterarMarcador(){
         if(!aberto){
             marcado = !marcado;
         }
    }

    public boolean abrir(){
         if(!aberto && !marcado){
             aberto = true;

             if (minado){
                throw new ExplosaoException();
             }

             if(vizinhacaSegura()){
                 vizinhos.forEach(v -> v.abrir());
             }

             return  true;
         }
         else {
             return false;
         }
    }

    public boolean vizinhacaSegura(){
         return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public void minar() {
            minado = true;
    }
    public boolean isMinado(){
         return minado;
    }
    public boolean isMarcado(){
         return marcado;
    }

    protected void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public boolean isAberto(){
         return aberto;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    public boolean objetivoAlcancado(){
         boolean desvendado = !minado && aberto;
         boolean protegido = minado && marcado;
         return desvendado || protegido;
    }

    public long minasNaVizinhanca(){
         return vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar() {
         aberto = false;
         minado = false;
         marcado = false;
    }

    public String toString() {
         if(marcado){
             return "x";
         } else if(aberto && minado){
             return "*";
         } else if (aberto && minasNaVizinhanca() > 0) {
             return Long.toString(minasNaVizinhanca());
         } else if(aberto){
             return "";
         } else {
             return "?";
         }
    }
}
