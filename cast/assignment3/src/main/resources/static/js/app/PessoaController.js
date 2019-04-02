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
			cellPhone: null,
			phone: null
		};
		$scope.listaTodasPessoas = [];
		
		PessoaService.listarTodasPessoas().then(function(mensagemRespostaPessoa) {
			$scope.listaTodasPessoas = mensagemRespostaPessoa.data.listaTodasPessoas;
			console.log(mensagemRespostaPessoa);
		}, function(reason) {
            console.log("ocorreu um erro ao listar todas as pessoas");
        }, function(value) {
            console.log("não houve nenhum regitros");
        });
	}
]);

