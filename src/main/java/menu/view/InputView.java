package menu.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static menu.util.ErrorCode.NOT_NUMBER;
import static menu.util.ErrorCode.NOT_VALID_COACH_COUNT_MAX;
import static menu.util.ErrorCode.NOT_VALID_COACH_COUNT_MIN;
import static menu.util.ErrorCode.NOT_VALID_COACH_NAME;
import static menu.util.ErrorCode.NOT_VALID_INPUT;
import static menu.util.ErrorCode.NOT_VALID_UNABLE_MENU_COUNT;


public class InputView {

    private static final String MESSAGE_INPUT_COACH_MANES = "코치의 이름을 입력해 주세요. (, 로 구분)";
    private static final String MESSAGE_INPUT_UNABLE_MENUS = "\n%s(이)가 못 먹는 메뉴를 입력해 주세요.";


    //문자열리스트 입력 : 문자열 비었는지 검사하고 - 공백제거한 문자열로 반환
    public List<String> readCoachNames() {
        System.out.println(MESSAGE_INPUT_COACH_MANES);
        String input = Console.readLine();
        validateInput(input);
        List<String> coachNames = createList(input);
        validateCoach(coachNames);

        return coachNames;
    }

    public List<String> readUnableToEatMenus(String name) {
        System.out.printf(MESSAGE_INPUT_UNABLE_MENUS + "\n", name);
        String input = Console.readLine();
        List<String> unableToEatMenus = getUnableToEatMenus(input);
        validateMenu(unableToEatMenus);

        return unableToEatMenus;
    }

    public void validateMenu(List<String> unableToEatMenus) {
        if (unableToEatMenus.size() > 2) {
            throw NOT_VALID_UNABLE_MENU_COUNT.throwError();
        }
    }

    public List<String> getUnableToEatMenus(String input) {
        if (input.isEmpty() || input.isBlank()) {
            return new ArrayList<>();
        }
        return createList(input);
    }


    //문자열 입력 : 문자열 비었는지 검사하고 문자열 반환
    public String readString() {
        System.out.println();
        String input = Console.readLine();
        validateInput(input);
        return input;
    }

    //숫자 입력 : 문자열 비었는지 검사하고, 숫자인지 검사하고 문자열 반환
    public int readInt() {
        String input = Console.readLine();
        validateInput(input);
        validateNumber(input);
        return Integer.parseInt(input.trim());
    }

    public void validateCoach(List<String> coachNames) {
        validateCoachCount(coachNames);
        validateCoachName(coachNames);
    }

    public void validateCoachCount(List<String> coachNames) {
        if (coachNames.size() < 2) {
            throw NOT_VALID_COACH_COUNT_MIN.throwError();
        }
        if (coachNames.size() > 5) {
            throw NOT_VALID_COACH_COUNT_MAX.throwError();
        }
    }

    public void validateCoachName(List<String> coachNames) {
        for (String coachName : coachNames) {
            if (coachName.length() < 2 || coachName.length() > 4) {
                throw NOT_VALID_COACH_NAME.throwError();
            }
        }
    }

    public List<String> createList(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    //숫자인지 +양수인지(음수이면 오류 발생)
    public void validateNumber(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw NOT_NUMBER.throwError();
        }
    }

    public void validateInput(String input) {
        if (input == null || input.isEmpty() || input.isBlank()) {
            throw NOT_VALID_INPUT.throwError();
        }
    }
}
