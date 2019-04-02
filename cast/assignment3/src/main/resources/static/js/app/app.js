'use strict'

var personApp = angular.module('pessoa', ['pessoa.controllers', 'pessoa.services']);

personApp.constant("CONSTANTS", {
	listarTodasPessoas: "/rest/pessoas",
	buscarPessoaPorId: "/rest/pessoa/",
	gravarPessoa: "/pessoa/save",
	removerPessoaPorId: "/pessoa/remove/"
});