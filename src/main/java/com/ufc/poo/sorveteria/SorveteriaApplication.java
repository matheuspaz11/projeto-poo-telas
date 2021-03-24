package com.ufc.poo.sorveteria;

import com.ufc.poo.sorveteria.model.Cliente;
import com.ufc.poo.sorveteria.model.Pedido;
import com.ufc.poo.sorveteria.model.Produto;
import com.ufc.poo.sorveteria.model.Venda;
import com.ufc.poo.sorveteria.model.enums.Tipo;
import com.ufc.poo.sorveteria.services.ClienteService;
import com.ufc.poo.sorveteria.services.PedidoService;
import com.ufc.poo.sorveteria.services.ProdutoService;
import com.ufc.poo.sorveteria.services.VendaService;
import com.ufc.poo.sorveteria.services.impl.ClienteServiceImpl;
import com.ufc.poo.sorveteria.services.impl.PedidoServiceImpl;
import com.ufc.poo.sorveteria.services.impl.ProdutoServiceImpl;
import com.ufc.poo.sorveteria.services.impl.VendaServiceImpl;

import java.util.ArrayList;
import java.util.List;

import com.ufc.poo.sorveteria.exceptions.NotFoundException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cristiano
 */
public class SorveteriaApplication {
    public static void main(String[] args) throws NotFoundException, Exception {
        ClienteService clienteService = new ClienteServiceImpl();
        ProdutoService produtoService = new ProdutoServiceImpl();
        PedidoService pedidoService = new PedidoServiceImpl();
        VendaService vendaService = new VendaServiceImpl();

        Cliente cli = new Cliente();
        cli.setId(1);
        cli.setNome("Cristiano");
        cli.setCpf("60161866000");
        cli.setTelefone("888888888");

        Cliente cli2 = new Cliente();
        cli2.setId(2);
        cli2.setNome("MAtheus");
        cli2.setCpf("07503374357");
        cli2.setTelefone("88888888");

        Produto acai = new Produto();
        acai.setId(1);
        acai.setNome("Açai");
        acai.setPreco(2.7); // 100 gramas
        acai.setQuantidadeDisponivel(1000); // gramas
        acai.setTipo(Tipo.ACAI);

        Produto sorvete = new Produto();
        sorvete.setId(2);
        sorvete.setNome("Sorvete de chocolate");
        sorvete.setPreco(3.7); // 100 gramas
        sorvete.setQuantidadeDisponivel(1000); // gramas
        sorvete.setTipo(Tipo.SORVETE);

        Pedido ped = new Pedido();
        ped.setId(1);
        ped.setProduto(acai);
        ped.setQuantidadeDesejada(1000);
        // ped.setNome(cli.getNome());

        Pedido ped2 = new Pedido();
        ped2.setId(3);
        ped2.setProduto(sorvete);
        ped2.setQuantidadeDesejada(100);
        // ped2.setNome(cli.getNome());
        // teste

        List<Pedido> peds = new ArrayList<>();
        peds.add(ped);
        peds.add(ped2);

        Venda venda = new Venda();
        venda.setCliente(cli);
        venda.setPedidos(peds);
        venda.setId(1);

        try {
            clienteService.cadastrar(cli);
            System.out.println(cli.toString());

            clienteService.cadastrar(cli2);
            // System.out.println(cli2.toString());

            // clienteService.editar(cli2);
            produtoService.cadastrar(acai);
            // System.out.println(acai.toString());

            produtoService.cadastrar(sorvete);
            // System.out.println(sorvete.toString());

            pedidoService.cadastrar(ped);
            // System.out.println(ped.toString());

            pedidoService.cadastrar(ped2);
            // System.out.println(ped2.toString());

            vendaService.cadastrar(venda);
            // System.out.println(venda.toString());

            // System.out.println(clienteService.buscar(1).getCpf());
            // System.out.println(produtoService.buscar(1).getNome());
            // System.out.println(vendaService.buscar(1).getValorTotalVenda());
            // System.out.println(produtoService.buscar(1).getQuantidadeDisponivel());

        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
