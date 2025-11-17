package test.br.com.vtr.cm.modelo;

import br.com.VTR.cm.modelo.campo;
import org.junit.Test;
import static org.junit.Assert.*;

public class CampoTeste {

    private campo Campo = new campo(3,3, false);

    @Test
   public void TesteVizinhoDireta(){
        campo Vizinho = new campo(3, 2, false);
        boolean resultado = Campo.adicionarVizinho(Vizinho);
        assertTrue(resultado);
    }

    @Test
    public void TesteVizinhoEsquerdo(){
        campo Vizinho = new campo(3, 4, false);
        boolean resultado = Campo.adicionarVizinho(Vizinho);
        assertTrue(resultado);
    }


    @Test
    public void TesteVizinhoCima(){
        campo Vizinho = new campo(4, 3, false);
        boolean resultado = Campo.adicionarVizinho(Vizinho);
        assertTrue(resultado);
    }


    @Test
    public void TesteVizinhoBaixo(){
        campo Vizinho = new campo(2, 3, false);
        boolean resultado = Campo.adicionarVizinho(Vizinho);
        assertTrue(resultado);
    }

    @Test
    public void Diagonal(){
        campo Vizinho = new campo(2, 4, false);
        boolean resultado = Campo.adicionarVizinho(Vizinho);
        assertTrue(resultado);
    }

    @Test
    public void testeNaoVizinho(){
        campo Vizinho = new campo(10, 4, false);
        boolean resultado = Campo.adicionarVizinho(Vizinho);
       assertFalse(resultado);
    }
}
