class ParkingSystem {
    private final int[] carSlots;

    public ParkingSystem(int big, int medium, int small) {
        carSlots = new int[] { big, medium, small };
    }

    public boolean addCar(int carType) {
        if (carSlots[carType - 1] == 0) {
            return false;
        }
        carSlots[carType - 1] -= 1;
        return true;
    }
}