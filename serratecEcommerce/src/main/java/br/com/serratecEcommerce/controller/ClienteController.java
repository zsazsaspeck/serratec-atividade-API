package br.com.serratecEcommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratecEcommerce.model.Cliente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*")
@Api(value = "API REST Serratec E-Commerce - CLIENTE")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@ApiOperation(value = "Retorna uma lista com todos os clientes")
	@GetMapping
	public List<Cliente> obterTodos(){
		return obterTodos();
	}

	@ApiOperation(value = "Retorna o cliente pelo ID")
	@GetMapping("/id/{id}")
	public Optional<Cliente> obterPorId(@PathVariable(value = "id") Long id){
		return obterPorId(id);
	}
	
	@ApiOperation(value = "Retorna o cliente pelo nome")
	@GetMapping("/nome/{nome}")
	public List<Cliente> obterPorNome(@PathVariable(value = "nome") String nome){
		return obterPorNome(nome);
	}
	
	@ApiOperation(value = "Adiciona um cliente")
	@PostMapping
	public ResponseEntity<Cliente> adicionar(@RequestBody Cliente cliente){
        return adicionar(cliente);
	}
	
	@ApiOperation(value = "Atualiza os dados de cliente existente")
	@PutMapping
	 public Cliente atualizar(@PathVariable(value = "id") Long id, @RequestBody Cliente cliente) {
         return atualizar(id, cliente);
	 }

     @ApiOperation(value = "Deleta a conta de um cliente existente")
	 @DeleteMapping("/id/{id}")
	 public void deletar(@PathVariable(value = "id") Long id) {
			deletar(id);
	 }
}
