package shopOfItems.logic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import shopOfItems.dbManagers.DBManagementSystem;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class TestShopLogic {
    InterfaceShopOfItems shopOfItems;
    DBManagementSystem mockDB;

    @Before
    public void init() {mockDB = mock(DBManagementSystem.class);
        shopOfItems = new ShopOfItems(mockDB);

        shopOfItems.setManagementSystem(mockDB);
    }

    @Test
    public void correctAddItemMockTest() {
        assertTrue(shopOfItems.addItem("Duck", "2", "20"));
    }

    @Test
    public void noCorrectAddItemMockTest() {
        assertFalse(shopOfItems.addItem("", "2", "20"));
    }

    @Test
    public void correctAddCustomerMockTest() {
        assertTrue(shopOfItems.addCustomer("Kolya", "Pepin", "18", 1, "Marsianina st, 8", "(067)456-78-90"));
    }

    @Test
    public void noCorrectAddCustomerMockTest() {
        assertFalse(shopOfItems.addCustomer("", "", "", 3, "", ""));
    }
}
