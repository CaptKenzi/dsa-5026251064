package lw01.unguided;

public class CarWash extends WashService {

    private static final int RATE_FIRST_3_DAYS = 35000;
    private static final int RATE_AFTER_3_DAYS = 25000;
    private static final int SETUP_FEE = 15000;
    private static final int THRESHOLD_DAYS = 3;

    public CarWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        int firstDays = Math.min(days, THRESHOLD_DAYS);
        int extraDays = Math.max(0, days - THRESHOLD_DAYS);
        return (firstDays * RATE_FIRST_3_DAYS) + (extraDays * RATE_AFTER_3_DAYS) + SETUP_FEE;
    }

    @Override
    public String label() {
        return "Car";
    }
}

