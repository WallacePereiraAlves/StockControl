package services;

import java.util.List;
import java.util.Scanner;

import entities.Product;
import entities.enums.ProductCategory;

public class ProductService {

    private List<Product> productList;
    private Scanner sc;

    public ProductService(List<Product> list, Scanner sc) {
        this.productList = list;
        this.sc = sc;
    }

    public void registerProduct() {

        System.out.println();
        System.out.println("Entre com os dados do produto:");
        System.out.println();

        String name = readString("Nome: ");
        double price = readDouble("Preço: ");
        int quantity = readInt("Quantidade no estoque: ");
        ProductCategory category = readCategory();

        Product newProduct = new Product(name, price, quantity, category);
        productList.add(newProduct);

        System.out.println();
        System.out.println("Produto adicionado com sucesso!");
    }

    public void increaseQuantity() {

        System.out.println();
        int productId = readInt("Digite o ID do produto: ");

        Product p = findProductById(productId);

        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        int quantity = readInt("Digite a quantidade a ser adicionada: ");
        p.addProducts(quantity);

        System.out.println();
        System.out.println("Estoque atualizado.");
    }

    public void decreaseQuantity() {

        System.out.println();
        int productId = readInt("Digite o ID do produto: ");

        Product p = findProductById(productId);

        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        int quantity = readInt("Digite a quantidade a ser retirada: ");
        boolean success = p.removeProducts(quantity);

        if (success) {
            System.out.println();
            System.out.println("Estoque atualizado.");
        } else {
            System.out.println("Erro: Quantidade maior que o estoque disponível.");
        }
    }

    public void deleteProduct() {

        System.out.println();
        int productId = readInt("Digite o ID do produto que deseja excluir: ");

        Product p = findProductById(productId);

        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        productList.remove(p);
        System.out.println("Produto removido com sucesso!");
    }

    public void showProductList() {

        System.out.println();
        System.out.println("Lista de produtos cadastrados:");
        System.out.println();

        for (Product p : productList) {
            System.out.println(p);
        }
    }


    private Product findProductById(int id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        }
    }

    private String readString(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    private ProductCategory readCategory() {
    	
        ProductCategory category = null;

        while (category == null) {
            System.out.println();
            System.out.println("Categorias disponíveis:");
            for (ProductCategory c : ProductCategory.values()) {
                System.out.println("- " + c);
            }

            System.out.print("Digite a categoria: ");
            String categoryStr = sc.nextLine().toUpperCase();

            try {
                category = ProductCategory.valueOf(categoryStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Categoria inválida. Tente novamente.");
            }
        }

        return category;
    }
}