package net.worldticket.model;

public class SheepStatuses {
    private int numberOfHealthySheep;
    private int numberOfDeadSheep;

    public SheepStatuses() {
    }

    public SheepStatuses(int numberOfHealthySheep, int numberOfDeadSheep) {
        this.numberOfHealthySheep = numberOfHealthySheep;
        this.numberOfDeadSheep = numberOfDeadSheep;
    }

    public int getNumberOfHealthySheep() {
        return numberOfHealthySheep;
    }

    public void setNumberOfHealthySheep(int numberOfHealthySheep) {
        this.numberOfHealthySheep = numberOfHealthySheep;
    }

    public int getNumberOfDeadSheep() {
        return numberOfDeadSheep;
    }

    public void setNumberOfDeadSheep(int numberOfDeadSheep) {
        this.numberOfDeadSheep = numberOfDeadSheep;
    }

    @Override
    public String toString() {
        return "{" +
                "numberOfHealthySheep=" + numberOfHealthySheep +
                ", numberOfDeadSheep=" + numberOfDeadSheep +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheepStatuses that = (SheepStatuses) o;

        if (numberOfHealthySheep != that.numberOfHealthySheep) return false;
        return numberOfDeadSheep == that.numberOfDeadSheep;
    }

    @Override
    public int hashCode() {
        int result = numberOfHealthySheep;
        result = 31 * result + numberOfDeadSheep;
        return result;
    }
}