package mate.academy.service;

import mate.academy.model.ShoppingCart;
import mate.academy.model.SpectacleSession;
import mate.academy.model.User;

public interface ShoppingCartService {
    void addSession(SpectacleSession spectacleSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clearShoppingCart(ShoppingCart cart);
}
