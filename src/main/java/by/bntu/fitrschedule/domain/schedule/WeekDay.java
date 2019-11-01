package by.bntu.fitrschedule.domain.schedule;

import java.util.ArrayList;
import java.util.List;

public class WeekDay {
    private String name;
    private List<Pair> pairs;

    public WeekDay() {
        pairs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void addPair(Pair pair) {
        pairs.add(pair);
    }
}
