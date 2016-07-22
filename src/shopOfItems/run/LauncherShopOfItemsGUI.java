package shopOfItems.run;

import shopOfItems.dbManagers.DBManagementSystem;
import shopOfItems.dbManagers.Derby;
import shopOfItems.dbManagers.MySQL;
import shopOfItems.shopGUI.ShopOfItemsGUI;

public class LauncherShopOfItemsGUI {
    public static void main(String[] args) throws Exception{

        DBManagementSystem managementSystem;

        if(args.length > 0 && "mysql".equals(args[0].toLowerCase())) {
            managementSystem = new MySQL();
        } else {
            managementSystem = new Derby();
        }

        new ShopOfItemsGUI(managementSystem);
    }
}
