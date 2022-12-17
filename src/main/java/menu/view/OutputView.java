package menu.view;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String MESSAGE_START = "점심 메뉴 추천을 시작합니다.\n";
    private static final String MESSAGE_RECOMMENDATION = "메뉴 추천 결과입니다.";
    private static final String MESSAGE_END = "추천을 완료했습니다.";


    public void printStart() {
        System.out.println(MESSAGE_START);

    }
    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printResult(List<String> daysOfWeek , List<String> recommendCategories, List<String> menusOfCoach) {
        String days = daysOfWeek.stream().collect(Collectors.joining(" | ", "[ "," ]"));
        String categories = recommendCategories.stream().collect(Collectors.joining(" | ", "[ "," ]"));
        System.out.println(MESSAGE_RECOMMENDATION);
        System.out.println(days);
        System.out.println(categories);
        //코치는 그냥 출력
        for(String menuOfCoach : menusOfCoach) {
            System.out.println(menuOfCoach);
        }
    }
    public void printEnd() {
        System.out.println(MESSAGE_END);
    }

    }
