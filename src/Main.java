import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main{

    public static void main(String[] args) {

        LinkedList<HotelReservation> reservationsList = new LinkedList<>();



        Scanner sc = new Scanner(System.in);
        System.out.println("Bonjour, Madame/Monsieur : \n Vous Pouvez Choisir votre Action D'aprés le Menu Suivant \n vous puouvez juste cliquer sur le numero correspondant;");
        HotelRoomManagement hotelRoomManagement = new HotelRoomManagement();

        int choix ;

        do{
            System.out.println("1/ voir le nombre des chambres libre \n" +
                    "2/ voir le nombre de chambre Maximale d'hotel \n" +
                    "3/ faire une reservation \n " +
                    "4/ modifier une reservation \n " +
                    "5/ supprimer une reservation \n " +
                    "6/ voir une reservation \n " +
                    "7/ Quitter"
            );



            choix = sc.nextInt();

            switch(choix) {
                case 1:
                    System.out.println("L'hotel à " +  hotelRoomManagement.getHotelAvailableRoomsCount() + "Chambres vide");
                    break;
                case 2:
                    System.out.println("L'hotel à " + hotelRoomManagement.gethotelMaxRooms() + "Chambres;");

                    break;
                case 3:{

                    HotelReservation newReservation = HotelReservation.CreateNewReservation( reservationsList);
                    reservationsList.add(newReservation);
                }
                break;
                case 4: {
                    HotelReservation.modifyReservation(reservationsList);
                    break;
                }

                case 5: {
                    HotelReservation.deleteReservation(reservationsList);
                    break;
                }

                case 6: {
                  HotelReservation.viewReservation(reservationsList);
                    break;
                }


                case 7:
                    System.out.println("see you soon");
                    return;


                default:
                    System.out.println("there is no actions for this choice");
            }

        }while(choix < 7);
    }



}