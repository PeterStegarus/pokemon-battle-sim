package items;

import java.util.Arrays;
import java.util.List;

public class ItemFactory {
    private ItemMaker selectItemMaker(String item) {   // filter pattern
        List<ItemMaker> itemMakers = Arrays.asList(new ScutMaker(), new VestaMaker(), new SabiutaMaker());

        return itemMakers.stream().filter(itemMaker -> itemMaker.is(item)).findFirst().orElse(null);
    }

    public Item make(String item) {   // factory pattern pentru ca makerul face un item nou
        // strategy pattern pentru ca selecteaza o implementare de maker
        ItemMaker itemMaker = selectItemMaker(item);
        return itemMaker != null ? itemMaker.make() : null;
    }
}

interface ItemMaker {
    boolean is(String item);

    Item make();
}

class ScutMaker implements ItemMaker {
    public boolean is(String item) {
        return "Scut".equals(item);
    }

    public Item make() {
        return new Scut();
    }
}

class VestaMaker implements ItemMaker {
    public boolean is(String item) {
        return "Vesta".equals(item);
    }

    public Item make() {
        return new Vesta();
    }
}

class SabiutaMaker implements ItemMaker {
    public boolean is(String item) {
        return "Sabiuta".equals(item);
    }

    public Item make() {
        return new Sabiuta();
    }
}

