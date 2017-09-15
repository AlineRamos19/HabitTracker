package br.com.udacity.habittracker;

public class HabitTracker {

    private String descriptionHabit;
    private int quantityWeek;
    private Long id;

    public HabitTracker(){}

    public HabitTracker(String descriptionHabit, int quantityWeek){
        this.descriptionHabit = descriptionHabit;
        this.quantityWeek = quantityWeek;
    }

    public String getDescriptionHabit() {
        return descriptionHabit;
    }

    public int getQuantityWeek() {
        return quantityWeek;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return getId() + " " + getDescriptionHabit() + " " + getQuantityWeek();
    }
}
