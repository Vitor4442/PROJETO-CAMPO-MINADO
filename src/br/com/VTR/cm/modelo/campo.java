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
}
