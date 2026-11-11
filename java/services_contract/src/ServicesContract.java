class ServicesContract {

    private String name;
    private final int year;
    private Interval[] intervals;
    private final Interval PRESET_INTERVAL = new Interval(8.0, 12.0);
    private final double PRESET_COST_PER_HOUR = 70.0;
    private final double EXTRAORDINARY_COST_PER_HOUR = 90.0;

    public ServicesContract(String name, int year) {
        this.name = name;
        this.year = year;
        intervals = new Interval[new Date(1, 1, year).DAYS_PER_YEAR];
        for (int i = 0; i < intervals.length; i++) {
            intervals[i] = PRESET_INTERVAL.clone();
        }
    }

    public void cancel(Date date) {
        intervals[date.daysElapsedYear()] = null;
    }

    public void enlarge(Date date, double scale) {
        intervals[date.daysElapsedYear()].scale(scale);
    }

    public void shift(Date date, double shiftment) {
        intervals[date.daysElapsedYear()].shift(shiftment);
    }

    public void writeln() {
        System.out.println("Contrato de limpieza: " + name + "-" + year);
        Date date = new Date(1, 1, year);
        for (int i = 0; i < intervals.length; i++) {
            System.out.println("(" + (i + 1) + ") " + date + " - ");
            if (intervals[i] == null) {
                System.out.println("Anulado");
            } else {
                System.out.println(intervals[i].toString());
            }
            date = date.next();
        }
    }

    public double getCost() {
        double cost = 0.0;
        for (Interval interval : this.intervals) {
            if (interval != null) {
                double presetHours = 0.0;
                Interval intersection = interval.intersection(PRESET_INTERVAL);
                if (intersection != null) {
                    presetHours = intersection.length();
                    cost += presetHours * PRESET_COST_PER_HOUR;
                }
                cost += (interval.length() - presetHours)
                        * EXTRAORDINARY_COST_PER_HOUR;
            }
        }
        return cost;
    }

    public int getYear() {
        return year;
    }
}