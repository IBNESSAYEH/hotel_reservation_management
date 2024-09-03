import java.util.LinkedList;

public class HotelRoomManagement implements RoomManagementInterface {
    protected static int availableRooms = maxRooms;



    @Override
    public int gethotelMaxRooms(){
        return maxRooms;
    }
    public int getHotelAvailableRoomsCount() {
        return availableRooms;
    }

}
