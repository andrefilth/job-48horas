package br.com.cea.citas.batch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.cea.transporte.batch.model.CitaEntregaHistoricoModel;
import br.com.cea.transporte.batch.model.CitaEntregaModel;
import br.com.cea.transporte.batch.utils.DatasUtils;

public class TestaListas {
//	private static Long id = 1L;
	private static int cont = 0;

	public static void main(String[] args) {
		List<CitaEntregaModel> list = new ArrayList<>();
		List<CitaEntregaHistoricoModel> historicoModels = new ArrayList<>();
		List<CitaEntregaHistoricoModel> historicoModelsAtualiza = new ArrayList<>();
//		List<CitaEntregaHistoricoModel> historicoModelsInsere = new ArrayList<>();
		CitaEntregaModel model1 = new CitaEntregaModel();
		CitaEntregaModel model2 = new CitaEntregaModel();
		CitaEntregaModel model3 = new CitaEntregaModel();
		CitaEntregaModel model4 = new CitaEntregaModel();

		CitaEntregaHistoricoModel model5 = new CitaEntregaHistoricoModel();
		CitaEntregaHistoricoModel model6 = new CitaEntregaHistoricoModel();
		CitaEntregaHistoricoModel model7 = new CitaEntregaHistoricoModel();
		CitaEntregaHistoricoModel model8 = new CitaEntregaHistoricoModel();
		model1.setId(1l);
		model2.setId(2l);
		model3.setId(3l);
		model4.setId(4l);
		list.add(model1);
		list.add(model2);
		list.add(model3);
		list.add(model4);

		model5.setId(1l);
		model6.setId(2l);
		model7.setId(3l);
		model8.setId(8l);
		historicoModels.add(model5);
		historicoModels.add(model6);
		historicoModels.add(model7);
		historicoModels.add(model8);
		// System.out.println(historicoModels);
		// System.out.println(list);
		historicoModels.forEach(lists -> {
			list.forEach(citas -> {
				if (lists.compareTo(citas) == 0) {
					historicoModelsAtualiza.add(lists);
					cont++;
				}
				
			});
		});
//		System.out.println("Atualiza no histórico: " + historicoModels);
		System.out.println("Insere os registros no historico : " + historicoModelsAtualiza);

		System.out.println("total de dados iguais: " + cont);
		// System.out.println(list);

		CitaEntregaHistoricoModel model = new CitaEntregaHistoricoModel();
		
		Date dataSql = model.getDataCitaAtualizacao();
		System.out.println(dataSql);
		
		if(dataSql == null) {
			dataSql = DatasUtils.mostrarDataAtual();
		}
		System.out.println(dataSql);
	}

}
