import java.util.HashMap;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        HashMap<String, HotelReservation> reservationsMap = new HashMap<String, HotelReservation>();



        Scanner sc = new Scanner(System.in);
        System.out.println("Bonjour, Madame/Monsieur : \n Vous Pouvez Choisir votre Action D'aprés le Menu Suivant \n vous puouvez juste cliquer sur le numero correspondant;");
        HotelRoomManagement hotelRoomManagement = new HotelRoomManagement();

        int choix ;

        do{
            System.out.println("1/ voir le nombre des chambres libre \n" +
                    "2/ voir le nombre de chambre Maximale d'hotel \n" +
                    "3/ faire une reservation \n " +
                    "4/ Quitter"
            );



            choix = sc.nextInt();

            switch(choix) {
                case 1:
                    System.out.println("L'hotel à " + (hotelRoomManagement.gethotelMaxRooms() - hotelRoomManagement.getHotelAvailableRoomsCount()) + "Chambres vide");
                    break;
                case 2:
                    System.out.println("L'hotel à " + hotelRoomManagement.gethotelMaxRooms() + "Chambres;");

                    break;
                case 3:{


                }


                    break;
                case 4:
                    System.out.println("see you soon");
                    return;


                default:
                    System.out.println("there is no actions for this choice");
            }

        }while(choix < 4);
    }


  /*  public void menu(){

        System.out.println("1/ voir le nombre des chambres libre \n" +
                "2/ voir le nombre de chambre Maximale d'hotel \n" +
                "3/ faire une reservation \n " +
                "4/ Quitter"
        );
    } */

    public static String[] CreateNewReservation(){
        Scanner sc = new Scanner(System.in);
        String principalGuestName ;
        int numberOfRoomsForReservation ;
        int numberOfPersons ;


        System.out.println("enter the principal guest name :");
        principalGuestName = sc.nextLine();
        System.out.println("enter the number Of Rooms For Reservation :");
        numberOfRoomsForReservation = sc.nextInt();
        System.out.println("enter the number Of Persons :");
        numberOfPersons = sc.nextInt();

        HotelReservation createNewReservation = new HotelReservation(principalGuestName , numberOfRoomsForReservation, numberOfPersons);


        if(!createNewReservation.reservation()) {

            System.out.println("the reservation is not possible caused of the the number of the rooms needed");
            return null;
        }else{

            System.out.println("the reservation is succeeded");
            String numberOfRoomsForReservationToString = Integer.toString(numberOfRoomsForReservation);
            String numberOfPersonsToString = Integer.toString(numberOfPersons);

            String[] guestData = {principalGuestName, numberOfRoomsForReservationToString,numberOfPersonsToString};
            return guestData;
        }
    }




}