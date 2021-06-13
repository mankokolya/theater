package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mate.academy.exception.AuthenticationException;
import mate.academy.lib.Injector;
import mate.academy.model.ShoppingCart;
import mate.academy.model.Spectacle;
import mate.academy.model.SpectacleSession;
import mate.academy.model.TheatreHall;
import mate.academy.model.User;
import mate.academy.security.AuthenticationService;
import mate.academy.service.OrderService;
import mate.academy.service.ShoppingCartService;
import mate.academy.service.SpectacleService;
import mate.academy.service.SpectacleSessionService;
import mate.academy.service.TheatreHallService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        SpectacleService spectacleService =
                (SpectacleService) injector.getInstance(SpectacleService.class);

        Spectacle asYouLikeIt = new Spectacle("AS YOU LIKE IT");
        asYouLikeIt.setDescription("Shakespeare’s romantic comedy.");
        spectacleService.add(asYouLikeIt);
        System.out.println(spectacleService.get(asYouLikeIt.getId()));
        spectacleService.getAll().forEach(System.out::println);

        TheatreHall firstTheatreHall = new TheatreHall();
        firstTheatreHall.setCapacity(100);
        firstTheatreHall.setDescription("first hall with capacity 100");

        TheatreHall secondTheatreHall = new TheatreHall();
        secondTheatreHall.setCapacity(200);
        secondTheatreHall.setDescription("second hall with capacity 200");

        TheatreHallService theatreHallService
                = (TheatreHallService) injector.getInstance(TheatreHallService.class);
        theatreHallService.add(firstTheatreHall);
        theatreHallService.add(secondTheatreHall);

        System.out.println(theatreHallService.getAll());
        System.out.println(theatreHallService.get(firstTheatreHall.getId()));

        SpectacleSession tomorrowSpectacleSession = new SpectacleSession();
        tomorrowSpectacleSession.setCinemaHall(firstTheatreHall);
        tomorrowSpectacleSession.setMovie(asYouLikeIt);
        tomorrowSpectacleSession.setShowTime(LocalDateTime.now().plusDays(1L));

        SpectacleSession yesterdaySpectacleSession = new SpectacleSession();
        yesterdaySpectacleSession.setCinemaHall(firstTheatreHall);
        yesterdaySpectacleSession.setMovie(asYouLikeIt);
        yesterdaySpectacleSession.setShowTime(LocalDateTime.now().minusDays(1L));

        SpectacleSessionService spectacleSessionService
                = (SpectacleSessionService) injector.getInstance(SpectacleSessionService.class);
        spectacleSessionService.add(tomorrowSpectacleSession);
        spectacleSessionService.add(yesterdaySpectacleSession);

        System.out.println(spectacleSessionService.get(yesterdaySpectacleSession.getId()));
        System.out.println(spectacleSessionService.findAvailableSessions(
                asYouLikeIt.getId(), LocalDate.now()));

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);

        User savedUser = null;
        try {
            savedUser = authenticationService.register("mankokolya@ukr.net", "1234");
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(savedUser);

        try {
            System.out.println(authenticationService.login("mankokolya@ukr.net", "1234"));
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(tomorrowSpectacleSession, savedUser);

        System.out.println("====Saved shopping card ====");
        ShoppingCart nickShoppingCart = shoppingCartService.getByUser(savedUser);
        System.out.println(nickShoppingCart);

        System.out.println("====Shopping card complete method was called====");
        OrderService orderService =
                (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(nickShoppingCart);

        System.out.println("====ShoppingCart after complete order method was called====");
        System.out.println(shoppingCartService.getByUser(savedUser));

        System.out.println("====Orders history====");
        System.out.println(orderService.getOrdersHistory(savedUser));
    }
}
