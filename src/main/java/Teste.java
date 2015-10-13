
import br.com.ufra.entidade.Estabelecimento;
import br.com.ufra.entidade.Vistoria;
import br.com.ufra.rn.EstabelecimentoRN;
import br.com.ufra.rn.VistoriaRN;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author geovane
 */
public class Teste {
    public static void main(String[] args) {
        VistoriaRN rn = new VistoriaRN();
        EstabelecimentoRN rnE = new EstabelecimentoRN();
        Estabelecimento e = rnE.obter(2);
        List<Vistoria> vistorias = rn.obterVistoriasPorEstabelecimento(e);
        vistorias.forEach((v) -> {
            System.out.println(v.getEstabelecimento().getNomeFantasia());
        });
    }
}
