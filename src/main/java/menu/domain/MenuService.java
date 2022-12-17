package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import menu.constants.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuService {
    private static final String START_CATEGORY = "카테고리";
    private final List<Coach> coaches;
    private List<Category> categoriesOfDays = new ArrayList<>();
    private Category todayCategory;


    public MenuService(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public Category getTodayCategory() {
        int todayCategorySymbol = Randoms.pickNumberInRange(1, 5);
        Category todayCategory = Category.getCategoriesBySymbol(todayCategorySymbol);
        return todayCategory;
    }

    public boolean isPossibleCategoryToAdd(Category todayCategory) {
        return Collections.frequency(categoriesOfDays, todayCategory) < 2; //1개있을때까지만 추가 가능
    }

    public void addCategory() {
        do {
            todayCategory = getTodayCategory();
        } while (!isPossibleCategoryToAdd(todayCategory)); // 2개 이상 중복이면 다시
        categoriesOfDays.add(todayCategory);
    }

    public void addTodayMenuToCoaches() {
        List<String> menu = Category.getMenusByCategory(todayCategory);
        for (Coach coach : coaches) {
            coach.addMenuOfDays(menu);
        }
    }

    public List<String> getRecommendCategories() {
        List<String> recommendCategories = new ArrayList<>();
        recommendCategories.add(START_CATEGORY);
        categoriesOfDays.stream()
                .forEach(category -> recommendCategories.add(category.getName()));
        return recommendCategories;
    }

    public List<String> getRecommendMenus() {
        List<String> recommendMenus = new ArrayList<>();
        for (Coach coach : coaches) {
            String recommendMenuOfCoach = coach.getRecommendMenuOfDays();
            recommendMenus.add(recommendMenuOfCoach);
        }
        return recommendMenus;
    }


    //    public Category addAndGetTodayCategory() {
    //        do{
    //            todayCategory = getTodayCategory();
    //        } while (!isPossibleCategoryToAdd(todayCategory)); // 2개 이상 중복이면 다시
    //        categoriesOfDays.add(todayCategory);
    //        return todayCategory;
    //    }

}
