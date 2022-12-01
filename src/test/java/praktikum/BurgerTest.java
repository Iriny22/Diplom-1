package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;



@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {


    private IngredientType type;
    @Mock
    Bun bun;
    private Ingredient newIngredient = new Ingredient(type.FILLING,"cucumber",32);
    private Ingredient newIngredient2 = new Ingredient(type.SAUCE,"ketchup",10);
    private Burger burger = new Burger();
    @Test
    public void addIngredientTest() {
        burger.addIngredient(newIngredient);
        int sizeAfter = burger.ingredients.size();
        int sizeExpected = 1;
        List<Ingredient> listExpected = new ArrayList<>();
        listExpected.add(newIngredient);
        assertEquals(sizeExpected ,sizeAfter);
        assertEquals(listExpected, burger.ingredients);
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(newIngredient);
        burger.addIngredient(newIngredient2);
        burger.moveIngredient(1,0);
        List<Ingredient> listExpected = new ArrayList<>();
        listExpected.add(newIngredient2);
        listExpected.add(newIngredient);
        assertEquals(listExpected, burger.ingredients);
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(newIngredient);
        burger.addIngredient(newIngredient2);
        burger.removeIngredient(0);
        List<Ingredient> listExpected = new ArrayList<>();
        listExpected.add(newIngredient2);
        assertEquals(listExpected, burger.ingredients);
    }

    @Test
    public void getPriceTest() {
        burger.addIngredient(newIngredient);
        Mockito.when(bun.getPrice()).thenReturn(10.2F);
        burger.setBuns(bun);
        float expected = 52.4F;
        assertEquals(expected,burger.getPrice(),0.0f);
    }

    @Test
    public void getReceiptTest() {
        burger.addIngredient(newIngredient);
        burger.addIngredient(newIngredient2);
        Mockito.when(bun.getName()).thenReturn("White bread");
        burger.setBuns(bun);
        System.out.println(burger.getReceipt());
        String receipt = burger.getReceipt();
        String price = "42";
        assertTrue(receipt.contains("White bread")
                && receipt.contains(newIngredient.name)
                && receipt.contains(newIngredient2.name)
                && receipt.contains("Price")
                && receipt.contains(price));
    }
}
