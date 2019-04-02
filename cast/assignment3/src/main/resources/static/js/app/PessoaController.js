'use strict'

var module = angular.module('pessoa.controllers', []);
module.controller("PessoaController", ["$scope", "PessoaService",

    function($scope, PessoaService) {
		
		$scope.pessoaSelecionada = {
			id: null,
			name: null,
			street: null,
			number: null,
			neighborhood: null,
			city: null,
			state: null,
			cellphone: null,
			phone: null
		};
		$scope.listaTodasPessoas = [];
		$scope.mensagemInformativa = "";
		$scope.idPessoaBusca = null;
		
		PessoaService.listarTodasPessoas().then(function(mensagemRespostaPessoa) {
			$scope.listaTodasPessoas = mensagemRespostaPessoa.data.listaTodasPessoas;			
			tratarMensagemInformativa(mensagemRespostaPessoa);
			
		}, function(reason) {
			document.getElementById("mensagemInformativa").className = "alert alert-danger";
			$scope.mensagemInformativa = "Erro! Ocorreu um erro ao listar todas as pessoa";
        }, function(value) {
        	document.getElementById("mensagemInformativa").className = "alert alert-danger";
			$scope.mensagemInformativa = "Erro! Não houve nenhum registros";
        });
		
		$scope.buscarPessoaPorId = function() {
			PessoaService.buscarPessoaPorId($scope.idPessoaBusca).then(function(mensagemRespostaPessoa) {
				$scope.pessoaSelecionada = mensagemRespostaPessoa.data.pessoaSelecionada;
				if(mensagemRespostaPessoa.data.erro == null) {
					$scope.listaTodasPessoas = mensagemRespostaPessoa.data.listaTodasPessoas;					
				}				
				tratarMensagemInformativa(mensagemRespostaPessoa);
			}, function(reason) {
				document.getElementById("mensagemInformativa").className = "alert alert-danger";
				$scope.mensagemInformativa = "Erro! Ocorreu um erro ao buscar pessoa pelo id";
	        }, function(value) {
	        	document.getElementById("mensagemInformativa").className = "alert alert-danger";
				$scope.mensagemInformativa = "Erro! Não houve nenhum registros";
	        });
		}
		
		$scope.gravarPessoa = function() {
			PessoaService.gravarPessoa($scope.pessoaSelecionada).then(function(mensagemRespostaPessoa) {
				$scope.pessoaSelecionada = mensagemRespostaPessoa.data.pessoaSelecionada;
				if(mensagemRespostaPessoa.data.erro == null) {
					$scope.listaTodasPessoas = mensagemRespostaPessoa.data.listaTodasPessoas;					
				}	
				tratarMensagemInformativa(mensagemRespostaPessoa);
			}, function(reason) {
				document.getElementById("mensagemInformativa").className = "alert alert-danger";
				$scope.mensagemInformativa = "Erro! Ocorreu um erro ao gravar pessoa";
	        }, function(value) {
	        	document.getElementById("mensagemInformativa").className = "alert alert-danger";
				$scope.mensagemInformativa = "Erro! Não houve nenhum registros";
	        });
		}
		
		$scope.removerPessoa = function() {
			PessoaService.removerPessoaPorId($scope.pessoaSelecionada.id).then(function(mensagemRespostaPessoa) {
				$scope.pessoaSelecionada = mensagemRespostaPessoa.data.pessoaSelecionada;
				$scope.pessoaSelecionada.id = null;
				if(mensagemRespostaPessoa.data.erro == null) {
					$scope.listaTodasPessoas = mensagemRespostaPessoa.data.listaTodasPessoas;					
				}	
				tratarMensagemInformativa(mensagemRespostaPessoa);
			}, function(reason) {
				document.getElementById("mensagemInformativa").className = "alert alert-danger";
				$scope.mensagemInformativa = "Erro! Ocorreu um erro ao remover pessoa";
	        }, function(value) {
	        	document.getElementById("mensagemInformativa").className = "alert alert-danger";
				$scope.mensagemInformativa = "Erro! Não houve nenhum registros";
	        });
		}
		
		$scope.limparDados = function() {
			$scope.pessoaSelecionada = {
				id: null,
				name: null,
				street: null,
				number: null,
				neighborhood: null,
				city: null,
				state: null,
				cellphone: null,
				phone: null
			};
		}
		
		function tratarMensagemInformativa(mensagemRespostaPessoa) {			
			if(mensagemRespostaPessoa.data.erro != null) {
				document.getElementById("mensagemInformativa").className = "alert alert-danger";
				$scope.mensagemInformativa = mensagemRespostaPessoa.data.erro + "! " + mensagemRespostaPessoa.data.mensagem;
			} else {
				document.getElementById("mensagemInformativa").className = "alert alert-primary";
				$scope.mensagemInformativa = mensagemRespostaPessoa.data.mensagem;				
			}
		}
	}
]);

