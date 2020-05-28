package br.com.loja.business;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.loja.model.Empresa;

@Stateless
public class EmpresaBusiness {

	@Inject
	private EntityManager em;

	@Inject
	private Logger log;

	@Inject
	private Event<Empresa> memberEventSrc;

	public void criarEmpresa(Empresa emp) {
		try {
			log.info("Criando " + emp.getRazaoSocial());
			em.persist(emp);
			memberEventSrc.fire(emp);
			log.info("Empresa " + emp.getRazaoSocial() + " persistida.");
		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}

	public void excluirEmpresa(Long id) {
		try {
		Empresa emp = em.find(Empresa.class, id);
		log.info("Excluindo " + emp.getRazaoSocial());
		em.remove(emp);
		memberEventSrc.fire(emp);
		log.info("Empresa com Id " + emp.getId() + " excluída.");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	public List<Empresa> obterEmpresas() {
        
		try {
			String sqlEmpresas = "select e from Empresa e";
			TypedQuery<Empresa> query = em.createQuery(sqlEmpresas, Empresa.class);
			List<Empresa> empresas = query.getResultList();
			log.info("Listando todas as empresas. Qtd: " + empresas.size());
			
			for (Empresa empresa : empresas) {
				log.info("Id: " + empresa.getId() + " - Nome Fantasia: " + empresa.getNomeFantasia() + " - Razão Social: " + empresa.getRazaoSocial() + " - CNPJ: " + empresa.getCnpj() + "\n");
			}
			
			return empresas;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}
	
	public Empresa obterEmpresaByCNPJ(String Cnpj) {
        
		try {
			String sqlEmpresa = "select e from Empresa e where e.cnpj = :pCNPJ";
			TypedQuery<Empresa> query = em.createQuery(sqlEmpresa, Empresa.class);
			query.setParameter("pCNPJ", Cnpj);
			Empresa empresas = query.getSingleResult();
			log.info("Buscou a empresa : " + empresas.getRazaoSocial());
			return empresas;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}

	}

	public void atualizarEmpresa(Empresa emp) {
		try {
		log.info("Atualizando " + emp.getRazaoSocial());
		em.merge(emp);
		memberEventSrc.fire(emp);
		log.info("Empresa com Id " + emp.getId() + " atualizada.");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	//Retorna lista de Empresas para Mock de Interface de Listagem de Empresas
	public List<Empresa> listaEmpresas(){
		
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		
		// Criando a Empresa 1
		Empresa emp = new Empresa();

		emp.setId(1L);
		emp.setRazaoSocial("Empresa 1");
		emp.setNomeFantasia("Empresa 1 - Nome Fantasia");
		emp.setCnpj("20921504000151");
		
		listaEmpresas.add(emp);
		
		// Criando a Empresa 2
		emp = new Empresa();
		
		emp.setId(2L);
		emp.setRazaoSocial("Empresa 2");
		emp.setNomeFantasia("Empresa 2 - Nome Fantasia");
		emp.setCnpj("20921504000152");
		
		listaEmpresas.add(emp);
		
		// Criando a Empresa 3
		emp = new Empresa();

		emp.setId(3L);
		emp.setRazaoSocial("Empresa 3");
		emp.setNomeFantasia("Empresa 3 - Nome Fantasia");
		emp.setCnpj("20921504000153");
		
		listaEmpresas.add(emp);
		
		// Criando a Empresa 4
		emp = new Empresa();

		emp.setId(4L);
		emp.setRazaoSocial("Empresa 4");
		emp.setNomeFantasia("Empresa 4 - Nome Fantasia");
		emp.setCnpj("20921504000154");
		
		// Criando a Empresa 5
		emp = new Empresa();

		emp.setId(5L);
		emp.setRazaoSocial("Empresa 5");
		emp.setNomeFantasia("Empresa 5 - Nome Fantasia");
		emp.setCnpj("20921504000155");
		
		listaEmpresas.add(emp);
		
		// Criando a Empresa 6
		emp = new Empresa();

		emp.setId(6L);
		emp.setRazaoSocial("Empresa 6");
		emp.setNomeFantasia("Empresa 6 - Nome Fantasia");
		emp.setCnpj("20921504000156");
		
		listaEmpresas.add(emp);
		
		return listaEmpresas;
	}

}
