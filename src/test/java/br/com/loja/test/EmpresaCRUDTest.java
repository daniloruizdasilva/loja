package br.com.loja.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.loja.model.Empresa;
import br.com.loja.business.EmpresaBusiness;

@RunWith(Arquillian.class)
public class EmpresaCRUDTest {

	@Inject
	EmpresaBusiness empresaBusiness;

	@Inject
	Logger log;

	@Test
	public void testCriarEmpresa() throws Exception {

		// Criando a Empresa 1
		Empresa emp = new Empresa();

		emp.setRazaoSocial("Empresa 1");
		emp.setNomeFantasia("Empresa 1 - Nome Fantasia");
		emp.setCnpj("20921504000151");

		empresaBusiness.criarEmpresa(emp);
		assertNotNull(emp.getId());
		log.info(emp.getRazaoSocial() + " foi criado com o ID " + emp.getId());

	}

	
	@SuppressWarnings("null")
	@Test
	public void testObterEmpresaByCNPJ() throws Exception {
 
		// Criando a Empresa 2
		Empresa emp = new Empresa();

		emp.setRazaoSocial("Empresa 2");
		emp.setNomeFantasia("Empresa 2 - Nome Fantasia");
		emp.setCnpj("20921504000152");
		empresaBusiness.criarEmpresa(emp);

		emp = empresaBusiness.obterEmpresaByCNPJ(emp.getCnpj());

		 assertTrue(emp != null);
		log.info("A consulta obteve a empresa " + emp.getRazaoSocial());
	}
	
	@Test
	public void testObterEmpresas() throws Exception {

		// Criando a Empresa 3
		Empresa emp = new Empresa();

		emp.setRazaoSocial("Empresa 3");
		emp.setNomeFantasia("Empresa 3 - Nome Fantasia");
		emp.setCnpj("20921504000153");

		empresaBusiness.criarEmpresa(emp);

		// Criando a Empresa 4
		emp = new Empresa();

		emp.setRazaoSocial("Empresa 4");
		emp.setNomeFantasia("Empresa 4 - Nome Fantasia");
		emp.setCnpj("20921504000154");

		empresaBusiness.criarEmpresa(emp);

		List<Empresa> listaEmp = empresaBusiness.obterEmpresas();

		assertTrue(listaEmp.size() > 0);
		log.info("A consulta obteve " + listaEmp.size() + " empresa(s)");
	}

	@Test
	public void testAtualizarEmpresa() throws Exception {

		// Criando a Empresa 5
		Empresa emp = new Empresa();
		emp.setRazaoSocial("Empresa 5");
		emp.setNomeFantasia("Empresa 5 - Nome Fantasia");
		emp.setCnpj("20921504000155");
		empresaBusiness.criarEmpresa(emp);

		//Alterando a Razão Social
		emp.setRazaoSocial("Empresa 5 - ALTERADA");
		empresaBusiness.atualizarEmpresa(emp);

		//Obtendo a empresa com o CNPJ 20921504000154
		emp.setCnpj("20921504000155");
		emp = empresaBusiness.obterEmpresaByCNPJ(emp.getCnpj());

		assertTrue(emp.getRazaoSocial() == "Empresa 5 - ALTERADA");
		log.info("A nova Razão Social é " + emp.getRazaoSocial());
	}

    @Test
    public void testExcluirEmpresa() throws Exception {
    	
		// Criando a Empresa 6
		Empresa emp = new Empresa();
		emp.setRazaoSocial("Empresa 6");
		emp.setNomeFantasia("Empresa 6 - Nome Fantasia");
		emp.setCnpj("20921504000156");
		empresaBusiness.criarEmpresa(emp);
        
		//Obtendo a empresa com o CNPJ 20921504000155
        emp.setCnpj("20921504000156");
        emp = empresaBusiness.obterEmpresaByCNPJ(emp.getCnpj());
        
        //Excluindo a empresa 
        empresaBusiness.excluirEmpresa(emp.getId());

        Empresa empExcluida = new Empresa();
        empExcluida.setCnpj("20921504000156");
        empExcluida = empresaBusiness.obterEmpresaByCNPJ(emp.getCnpj());
        
        assertTrue(empExcluida == null);
        log.info("A empresa com Razão Social " + emp.getRazaoSocial() + " foi excluída com sucesso");
    }
}
