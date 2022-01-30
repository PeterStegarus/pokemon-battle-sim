package items.factory;

import items.Item;

public interface ItemMaker {
    boolean is(String item);

    Item make();
}
