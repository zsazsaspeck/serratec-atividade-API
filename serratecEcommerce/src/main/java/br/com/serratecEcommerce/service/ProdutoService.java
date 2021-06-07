package br.com.serratecEcommerce.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.serratecEcommerce.model.Produto;
import br.com.serratecEcommerce.model.exception.ResourceBadRequestException;
import br.com.serratecEcommerce.model.exception.ResourceNotFoundException;
import br.com.serratecEcommerce.repository.ProdutoRepository;


@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository _repositorioProduto;
	
	public List<Produto> obterTodos(){
		return this._repositorioProduto.findAll();
		}
	
	public Optional<Produto> obterPorId(Long id){
		return this._repositorioProduto.findById(id);
	}
	
	public ResponseEntity<Produto> adicionar(Produto produto){
		this.validarProduto(produto);
		produto.setId(null);
		produto.setDataDeCadastroDoProduto(new Date());
		var adicionado = this._repositorioProduto.save(produto);
        return new ResponseEntity<>(adicionado, HttpStatus.CREATED);
	}
	
	 public Produto atualizar(Long id,Produto produto) {
 		 _repositorioProduto.findById(id).orElseThrow( () -> new ResourceNotFoundException("Produto não encontrada pelo ID:" + id));
 		 this.validarProduto(produto);
 		 produto.setId(id);
         return this._repositorioProduto.save(produto);
	 }

	 public void deletar(Long id) {
			_repositorioProduto.findById(id).orElseThrow( () -> new ResourceNotFoundException("Produto não encontrada pelo ID:" + id));
         this._repositorioProduto.deleteById(id);
	 }
	 
	 private void validarProduto(Produto produto) {
		 if (produto.getQuantidadeEmEstoque() < 0)
			 throw new ResourceBadRequestException("A quantidade em estoque é negativa!");
		 if (produto.getPreco() < 0)
			 throw new ResourceBadRequestException("O preço do produto é negativo!");
	 }
}