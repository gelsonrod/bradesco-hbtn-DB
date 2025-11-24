package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdministrativoApp {

    private static Date mkDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();
        PessoaModel pessoaModel = new PessoaModel();

        // Produto
        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        // 1) Criar produto
        produtoModel.create(p1);

        // 2) Buscar todos os produtos
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        // 3) Atualizar produto
        if (!produtos.isEmpty()) {
            Produto first = produtos.get(0);
            first.setQuantidade(first.getQuantidade() + 10);
            produtoModel.update(first);
        }

        // 4) Buscar por ID
        Produto ref = new Produto();
        ref.setId(p1.getId());
        Produto buscado = produtoModel.findById(ref);
        System.out.println("Produto por ID: " + buscado);

        // Pessoa
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Jane Doe");
        pessoa.setEmail("jane@example.com");
        pessoa.setIdade(30);
        pessoa.setCpf("12345678901");
        pessoa.setDataNascimento(mkDate(1995, 5, 20));

        // 5) Criar pessoa
        pessoaModel.create(pessoa);

        // 6) Listar pessoas
        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas: " + pessoas.size());

        // 7) Atualizar pessoa
        Pessoa pRef = pessoas.get(0);
        pRef.setIdade(pRef.getIdade() + 1);
        pessoaModel.update(pRef);

        // 8) Buscar por ID
        Pessoa findOne = new Pessoa();
        findOne.setId(pRef.getId());
        System.out.println("Pessoa por ID: " + pessoaModel.findById(findOne));

        // 9) Remover produto criado (exemplo)
        produtoModel.delete(ref);

        System.out.println("CRUD demo finalizado.");
    }
}
