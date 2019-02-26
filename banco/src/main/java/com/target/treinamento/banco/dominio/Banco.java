package com.target.treinamento.banco.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity //Eu sou uma persistence class
@Table(name = "BANCOS") //Quero que crie uma tabela, ou mapeie 
							  //essa tabela com este nome.
public class Banco {

	@Id
	@Column(name="ID")
	@SequenceGenerator(name="bancoSeqGen", sequenceName="bancos_id_seq", allocationSize=1)
	@GeneratedValue(generator="bancoSeqGen")
	private Long id;
	
	@Column(name="NOME")
	private String nome;

	@OneToMany(mappedBy="banco", fetch=FetchType.LAZY)	
	private List<Funcionario> funcionarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
}
