package br.com.loja.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.loja.business.EmpresaBusiness;
import br.com.loja.model.Empresa;

@Named("listarEmpresas")
public class ListarEmpresasController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaBusiness business;
	
	public static List<Empresa> listaEmpresa;
	
	@PostConstruct
	public void init() {
		listaEmpresa = business.listaEmpresas();
	}

	public List<Empresa> getListaEmpresa() {
		return listaEmpresa;
	}

	public void setListEmpresa(List<Empresa> listaEmpresa) {
		ListarEmpresasController.listaEmpresa = listaEmpresa;
	}
}
