package mattresssalesapp;

import java.util.Scanner;

public class MattressSalesApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String[] products = {
            "Cozy Nights Monarch mattress",
            "Restonic Barbados mattress",
            "Sealy Phuket mattress"
        };
        
        int[][] sales = new int[3][3];

        for (int i = 0; i < products.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter number of sales for month " + (j + 1) + " for " + products[i] + ": ");
                sales[i][j] = scanner.nextInt();
            }
            System.out.println();
        }

        Product productReport = new Product(products, sales);
        PrintProduct printer = new PrintProduct(productReport);
        printer.printReport();
        
        scanner.close();
    }
}