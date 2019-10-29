package by.bntu.fitrschedule.domain.schedule;

import java.util.HashSet;
import java.util.Set;

public class WeekDay {
    private String name;
    private Set<Pair> pairs;

    public WeekDay() {
        pairs = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Pair> getPairs() {
        return pairs;
    }

    public void addPair(Pair pair) {
        pairs.add(pair);
    }
}
