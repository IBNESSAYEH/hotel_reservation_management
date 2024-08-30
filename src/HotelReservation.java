public class HotelReservation implements HotelReservationInterface{
    private String principalGuestName;
    private int numberOfRoomsForReservation;
    private int numberOfPersons;
    private boolean reservationPossibility = false;


    public HotelReservation(String principalGuestName, int numberOfRoomsForReservation, int numberOfPersons) {
        this.principalGuestName = principalGuestName;
        this.numberOfRoomsForReservation = numberOfRoomsForReservation;
        this.numberOfPersons = numberOfPersons;
        HotelRoomManagement newReservation = new HotelRoomManagement();
        reservationPossibility = newReservation.setHotelAvailableRoomsCount(numberOfPersons);
    }

    @Override
    public boolean reservation() {
        if(reservationPossibility) {
            return true;
        }
        return false;
    }



    public String toString() {
        return "reservation{"
                + "reservation for MR/MS : " + principalGuestName
                + ", for: " + numberOfPersons
                + ",persons in: " + numberOfRoomsForReservation
                +  " rooms }";
    }
}
