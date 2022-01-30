package items.factory;

import items.Item;

interface ItemMaker {
    boolean is(String item);

    Item make();
}
