package test.br.com.vtr.cm.modelo;

import br.com.VTR.cm.excecao.ExplosaoException;
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

    @Test
    public void testeValorPadraoAtributoMarcado(){
        assertFalse(Campo.isMarcado());
    }

    @Test
    public void testeAlternarMarcado(){
        Campo.alterarMarcador();
        assertTrue(Campo.isMarcado());
    }

    @Test
    public void testeAlternarMarcadoDuasChamads(){
        Campo.alterarMarcador();
        Campo.alterarMarcador();
        assertFalse(Campo.isMarcado());
    }

    @Test
    public void testeAbrirNaoMinadoNaoMarcado(){
        assertTrue(Campo.abrir());
    }

    @Test
    public void testeAbrirNaoMinadoMarcado() {
        Campo.alterarMarcador();
        assertFalse(Campo.abrir());
    }

    @Test
    public void testeAbrirMinadoMarcado() {
        Campo.alterarMarcador();
        Campo.minar();
        assertFalse(Campo.abrir());
    }

    @Test
    public void testeAbrirMinadoNaoMarcado() {
        Campo.minar();

        assertThrows(ExplosaoException.class, () -> {
            Campo.abrir();
        });
    }

    @Test
    public void testeAbrirComVizinhos1(){
        campo campo11 = new campo(2, 2, false);
        campo campo22 = new campo(2, 3, false);

        campo22.adicionarVizinho(campo11);

        Campo.adicionarVizinho(campo22);

        Campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    public void testeAbrirComVizinhos2(){
        campo campo11 = new campo(1, 1, false);
        campo campo12 = new campo(1,1,false);
        campo12.minar();
        campo campo22 = new campo(2, 2, false);

        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        Campo.adicionarVizinho(campo22);
        Campo.abrir();

        assertTrue(campo22.isAberto() && !campo11.isAberto());
    }

    @Test
    public void testeObjetivoMarcadoEMinado(){
        Campo.minar();
        Campo.isMarcado();
        assertTrue(Campo.objetivoAlcancado());
    }

    @Test
    public void testeObjetivoNaoMarcadoEAberto(){
        Campo.isAberto();
        assertTrue(Campo.objetivoAlcancado());
    }

    @Test
    public void testeObjetivoMinadoeNaoMarcado(){
        Campo.minar();
        assertFalse(Campo.objetivoAlcancado());
    }

    @Test
    public void minasNaVizinhanca(){

    }
    }
