

import org.junit.Test;
import static org.junit.Assert.*;
import mattresssalesapp.Product;

public class ProductTest {
    
    @Test
    public void GetTotalProductsSold_WithValidData_ReturnsCorrectValue() {
        String[] products = {"Product A", "Product B", "Product C"};
        int[][] sales = {
            {20, 15, 20},
            {15, 10, 12},
            {18, 15, 20}
        }; // Total = 145
        
        Product p = new Product(products, sales);
        assertEquals(145, p.GetTotalProductsSold());
    }

    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        String[] products = {"Product A", "Product B", "Product C"};
        int[][] sales = {
            {20, 15, 20},
            {15, 10, 12},
            {18, 15, 20}
        }; // Total = 145, Avg = 145 / 3 = 48.333...
        
        Product p = new Product(products, sales);
        assertEquals(48.3333, p.GetAverageProductSold(), 0.01);
    }
}