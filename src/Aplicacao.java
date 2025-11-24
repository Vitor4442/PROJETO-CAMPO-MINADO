import br.com.VTR.cm.modelo.tabuleiro;
import br.com.VTR.cm.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {

        tabuleiro TB1 = new tabuleiro(3, 6,6);
        new TabuleiroConsole(TB1);

    }
}
