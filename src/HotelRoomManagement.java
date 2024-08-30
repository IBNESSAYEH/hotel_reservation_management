public class HotelRoomManagement implements RoomManagementInterface {
    private static int availableRooms = 0;

    @Override
    public int gethotelMaxRooms(){
        return maxRooms;
    }

    @Override
    public int getHotelAvailableRoomsCount(){
        return availableRooms;
    }

    @Override
    public boolean setHotelAvailableRoomsCount(int guestsNumber){

        boolean possibilityOfReservation = ((availableRooms + guestsNumber) <= maxRooms);
         if (possibilityOfReservation){
             availableRooms += guestsNumber;
             return true;
         }

        return false;
    }

}
