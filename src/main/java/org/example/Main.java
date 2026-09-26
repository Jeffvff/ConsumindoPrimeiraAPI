package org.example;

import org.example.dto.EnderecoDto;
import org.example.servico.ApiServico;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApiServico apiServico = new ApiServico();
        EnderecoDto enderecoDto;

        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        String CEP;

        System.out.println("Seja bem vindo ao consulta por cep");
        do {
            System.out.print("insira um CEP valido de 8 digitos: ");
            CEP = sc.next().trim();
            if (!CEP.matches("\\d{8}")) {
                System.out.println("CEP inválido!");
            } else {
                System.out.println("CEP Valido!");
            }
        } while (!CEP.matches("\\d{8}"));

        try {
            enderecoDto = apiServico.getEndereco(CEP);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Deseja ver: ");
        System.out.printf("[1] - Resumo%n[2] - Detalhado%n[3] - Completo%nSelecione: ");
        int escolha = sc.nextInt();
        sc.nextLine();
        switch (escolha) {
            case 1:
                System.out.println("Consulta resumida: ");
                System.out.print(enderecoDto.getResumido());
                System.out.print("Pressione enter para fechar: ");
                sc.nextLine();
                break;
            case 2:
                System.out.println("Consulta Detalhada: ");
                System.out.print(enderecoDto.getDetalhado());
                System.out.print("Pressione enter para fechar: ");
                sc.nextLine();
                break;
            default:
                System.out.println("Consulta Completa: ");
                System.out.print(enderecoDto);
                System.out.print("Pressione enter para fechar: ");
                sc.nextLine();
                break;
        }
    }
}
