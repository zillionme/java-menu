package menu.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DayOfWeek {
    MON(0, "월요일"),
    TUE(1, "화요일"),
    WED(2, "수요일"),
    THU(3, "목요일"),
    FRI(4, "금요일");
    private static final String START = "구분";
    private final int symbol;
    private final String name;

    DayOfWeek(int symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public static List<String> getDaysOfWeek() {
        List<String> daysOfWeek = new ArrayList<>();
        daysOfWeek.add(START);
        Arrays.stream(values())
                .forEach(day -> daysOfWeek.add(day.name));
        return daysOfWeek;
    }
}
