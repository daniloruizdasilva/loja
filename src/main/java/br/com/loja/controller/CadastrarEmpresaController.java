package br.com.loja.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.loja.model.Empresa;


@Named("empresas")
@ViewScoped
public class CadastrarEmpresaController implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Empresa cadEmpresa;
	
	public void salvar() {
		
		System.out.println("salvando empresa");
	}
	
	public CadastrarEmpresaController() {

	}
	
	public Empresa getEmpresa() {
		return cadEmpresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.cadEmpresa = empresa;
	}
	
	public void cancelarCadastro() {
		System.out.println("cancelando o cadastro");
	}

}
