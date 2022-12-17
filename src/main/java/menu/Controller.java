package menu;


import menu.constants.Category;
import menu.constants.DayOfWeek;
import menu.domain.Coach;
import menu.domain.MenuService;
import menu.view.InputView;
import menu.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void generate() {
        outputView.printStart();
        //메뉴 추천 서비스 생성 with 코치들
        MenuService menuService = createMenuService();
        //메뉴 추천하기
        recommendMenu(menuService);
        //결과 출력하기
        printResult(menuService);
        outputView.printEnd();
    }
    public MenuService createMenuService() {
        List<String> coachNames = getCoachNames();
        List<Coach> coaches = getCoachList(coachNames);
        addUnableToEatMenusToCoaches(coaches);

        return new MenuService(coaches);
    }

    public void recommendMenu(MenuService menuService) {
        for(int i =0 ; i<5; i++) {
            menuService.addCategory(); //요일 추가하면 더 좋을 것 같음. map으로 요일별 카테고리
            menuService.addTodayMenuToCoaches(); // 각 코치들에게 메뉴 추천하기

        }

    }
    public void printResult(MenuService menuService) {
        // 구분 ,요일
        List<String> daysOfWeek = DayOfWeek.getDaysOfWeek();
        // 카테고리, 카테고리들
        List<String> categories = menuService.getRecommendCategories();
        // 코치 이름과 메뉴들
        List<String> menusOfCoach = menuService.getRecommendMenus();
        outputView.printResult(daysOfWeek, categories,menusOfCoach);
    }

    public List<String> getCoachNames () {
        try {
            return inputView.readCoachNames();

        }catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
        return getCoachNames ();
    }

    public List<Coach> getCoachList(List<String> coachNames) {
        List<Coach> coaches = new ArrayList<>();
        for(String coachName : coachNames) {
            coaches.add(new Coach(coachName));
        }
        return coaches;
    }

    //있는 음식인지 validate해야함
    public void addUnableToEatMenusToCoaches(List<Coach> coaches) {
        for(Coach coach : coaches) {
            List<String> unableToEatMenus = getUnableToEatMenus(coach.getName());
            coach.setUnableToEatMenus(unableToEatMenus);
        }
    }

    //메뉴가 리스트에 있는지도 확인해야함.
    public List<String> getUnableToEatMenus(String coachName) {
        try {
            List<String> unableToEatMenus = inputView.readUnableToEatMenus(coachName);
            Category.validateMenu(unableToEatMenus);
            return unableToEatMenus;

        }catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
        return getUnableToEatMenus(coachName);

    }
}
