package menu.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static menu.util.ErrorCode.NOT_VALID_COACH_COUNT_MAX;
import static menu.util.ErrorCode.NOT_VALID_COACH_COUNT_MIN;
import static menu.util.ErrorCode.NOT_VALID_INPUT;
import static menu.util.ErrorCode.NOT_VALID_UNABLE_MENU_COUNT;


public class InputView {

    private static final String MESSAGE_INPUT_COACH_MANES = "코치의 이름을 입력해 주세요. (, 로 구분)";
    private static final int COACH_COUNT_MIN = 2;
    private static final int COACH_COUNT_MAX = 5;
    private static final int UNABLE_TO_EAT_COUNT_MAX = 2;

    private static final String MESSAGE_INPUT_UNABLE_MENUS = "\n%s(이)가 못 먹는 메뉴를 입력해 주세요.";

    public List<String> readCoachNames() {
        System.out.println(MESSAGE_INPUT_COACH_MANES);
        String input = Console.readLine();
        validateInput(input);
        List<String> coachNames = createList(input);
        validateCoachCount(coachNames);

        return coachNames;
    }

    public List<String> readUnableToEatMenus(String name) {
        System.out.printf(MESSAGE_INPUT_UNABLE_MENUS + "\n", name);
        String input = Console.readLine();
        List<String> unableToEatMenus = getUnableToEatMenus(input);
        validateMenu(unableToEatMenus);

        return unableToEatMenus;
    }

    public List<String> getUnableToEatMenus(String input) {
        if (input.isEmpty() || input.isBlank()) { //비었거나, 없으면
            return new ArrayList<>();
        }
        return createList(input);
    }

    //유효성 검사
    public void validateCoachCount(List<String> coachNames) {
        if (coachNames.size() < COACH_COUNT_MIN) {
            throw NOT_VALID_COACH_COUNT_MIN.throwError();
        }
        if (coachNames.size() > COACH_COUNT_MAX) {
            throw NOT_VALID_COACH_COUNT_MAX.throwError();
        }
    }

    public void validateMenu(List<String> unableToEatMenus) {
        if (unableToEatMenus.size() > UNABLE_TO_EAT_COUNT_MAX) {
            throw NOT_VALID_UNABLE_MENU_COUNT.throwError();
        }
    }

    public void validateInput(String input) {
        if (input == null || input.isEmpty() || input.isBlank()) {
            throw NOT_VALID_INPUT.throwError();
        }
    }


//    //문자열 입력 : 문자열 비었는지 검사하고 문자열 반환
//    public String readString() {
//        System.out.println();
//        String input = Console.readLine();
//        validateInput(input);
//        return input;
//    }
//
//    //숫자 입력 : 문자열 비었는지 검사하고, 숫자인지 검사하고 문자열 반환
//    public int readInt() {
//        String input = Console.readLine();
//        validateInput(input);
//        validateNumber(input);
//        return Integer.parseInt(input.trim());
//    }

//    public void validateCoach(List<String> coachNames) {
//        validateCoachCount(coachNames);
//        validateCoachName(coachNames);
//    }


    public List<String> createList(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

//    //숫자인지 +양수인지(음수이면 오류 발생)
//    public void validateNumber(String input) {
//        if (!input.chars().allMatch(Character::isDigit)) {
//            throw NOT_NUMBER.throwError();
//        }
//    }

}
