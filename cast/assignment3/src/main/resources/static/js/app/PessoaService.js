'use strict'

angular.module('pessoa.services', []).factory('PessoaService', ["$http", "CONSTANTS", function($http, CONSTANTS) {

    var service = {};
    
    service.listarTodasPessoas = function() {
		return $http.get(CONSTANTS.listarTodasPessoas);
	}
    
    service.buscarPessoaPorId = function(idPessoa) {
		var url = CONSTANTS.buscarPessoaPorId + idPessoa;
		return $http.get(url);
	}
    
    service.gravarPessoa = function(pessoa) {
		return $http.post(CONSTANTS.gravarPessoa, pessoa);
	}
    
    service.removerPessoaPorId = function(idPessoa) {
		var url = CONSTANTS.removerPessoaPorId + idPessoa;
		return $http.delete(url);
	}
    
    return service;
}]);