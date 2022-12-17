package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Coach {
    private final String name;
    private List<String> unableToEatMenus;
    private String todayMenu;
    private List<String> MenuOfDays = new ArrayList<>();

    public Coach(String name) {
        this.name = name;
    }

    public void setUnableToEatMenus(List<String> unableToEatMenus) {
        this.unableToEatMenus = unableToEatMenus;
    }

    public String getName() {
        return name;
    }

    public void addMenuOfDays(List<String> menu) {
        do {
            todayMenu = Randoms.shuffle(menu).get(0);
        } while (!isPossibleMenuToAdd(todayMenu));
        MenuOfDays.add(todayMenu);
    }

    public boolean isPossibleMenuToAdd(String menu) {
        return isEatableMenu(menu) || isNotDuplicatedMenu(menu);
    }

    public boolean isEatableMenu(String menu) {
        return !unableToEatMenus.contains(menu); //못먹는 음식에 포함되어있지 않은지.
    }

    public boolean isNotDuplicatedMenu(String menu) {
        return !MenuOfDays.contains(menu);
    }
}
