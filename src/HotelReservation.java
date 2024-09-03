import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Scanner;

public class HotelReservation extends HotelRoomManagement implements HotelReservationInterface{
    private String principalGuestName;
    private int numberOfRoomsForReservation;
    private int numberOfPersons;
    private boolean reservationPossibility = false;
    LocalDate dateOfReservation;
    private String dateOfReservationBeginning;
    private String dateOfReservationEnd;

    public HotelReservation(String principalGuestName, int numberOfRoomsForReservation, int numberOfPersons,LocalDate dateOfReservation , String dateOfReservationEnd, String dateOfReservationBeginning) {
        this.principalGuestName = principalGuestName;
        this.numberOfRoomsForReservation = numberOfRoomsForReservation;
        this.numberOfPersons = numberOfPersons;
        this.dateOfReservation = dateOfReservation;
        this.dateOfReservationBeginning = dateOfReservationBeginning;
        this.dateOfReservationEnd = dateOfReservationEnd;
        HotelRoomManagement newReservation = new HotelRoomManagement();
    }

    public String getPrincipalGuestName() {
        return principalGuestName;
    }
    public int getHotelAvailableRoomsCount() {
        return availableRooms;
    }

    public void setPrincipalGuestName(String principalGuestName) {
        this.principalGuestName = principalGuestName;
    }

    public int getNumberOfRoomsForReservation() {
        return numberOfRoomsForReservation;
    }

    public void setNumberOfRoomsForReservation(int numberOfRoomsForReservation) {
        this.numberOfRoomsForReservation = numberOfRoomsForReservation;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public boolean isReservationPossibility() {
        return reservationPossibility;
    }

    public void setReservationPossibility(boolean reservationPossibility) {
        this.reservationPossibility = reservationPossibility;
    }

    public LocalDate getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(LocalDate dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public void setDateOfReservationBeginning(String dateOfReservationBeginning) {
        this.dateOfReservationBeginning = dateOfReservationBeginning;
    }

    public void setDateOfReservationEnd(String dateOfReservationEnd) {
        this.dateOfReservationEnd = dateOfReservationEnd;
    }

    public String getDateOfReservationBeginning() {
        return dateOfReservationBeginning;
    }


    public String getDateOfReservationEnd() {
        return dateOfReservationEnd;
    }

    // cette methode sert a valider la possibiliter de la reservation true/false
    @Override
    public boolean reservation() {
        if(reservationPossibility) {
            return true;
        }

        return false;
    }

    //cette methode
    public static boolean formattingDate(String startDate, String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
             LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            LocalDate now = LocalDate.now();

            return start.isAfter(now) && start.isBefore(end);

        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format: " + e.getMessage());
            return false;
        }
    }

    // cette methode sert a clculer et stocker le nombre des chmabres disponibles

    public static boolean setHotelAvailableRoomsCount(int numberOfRoomsForReservation, LinkedList<HotelReservation> reservationsList, HotelReservation newReservation) {
        int occupiedRooms = 0;

        for (HotelReservation existingReservation : reservationsList) {
            if (existingReservation == null ||
                    existingReservation.getDateOfReservationBeginning() == null ||
                    existingReservation.getDateOfReservationEnd() == null) {
                continue;
            }

            LocalDate existingStart = LocalDate.parse(existingReservation.getDateOfReservationBeginning());
            LocalDate existingEnd = LocalDate.parse(existingReservation.getDateOfReservationEnd());
            LocalDate newStart = LocalDate.parse(newReservation.getDateOfReservationBeginning());
            LocalDate newEnd = LocalDate.parse(newReservation.getDateOfReservationEnd());

            if ((newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) ||
                    (newEnd.isBefore(existingEnd) && newStart.isAfter(existingStart))) {
                occupiedRooms += existingReservation.getNumberOfRoomsForReservation();
            }
        }

        int roomsLeft = maxRooms - occupiedRooms;
        boolean possibilityOfReservation = (roomsLeft >= numberOfRoomsForReservation);

        if (possibilityOfReservation) {
            availableRooms -= numberOfRoomsForReservation;
        }

        return possibilityOfReservation;
    }


    public String toString() {
        return "reservation{"
                + "reservation for MR/MS : " + principalGuestName
                + ", for: " + numberOfPersons
                + ",persons in: " + numberOfRoomsForReservation
                +  " rooms , it was in " + dateOfReservation
                + " start in " + dateOfReservationBeginning
                + " end in " + dateOfReservationEnd

                + "}";
    }



    public static HotelReservation CreateNewReservation(LinkedList<HotelReservation>  reservationsList){
        Scanner sc = new Scanner(System.in);
        String principalGuestName ;
        int numberOfRoomsForReservation ;
        int numberOfPersons ;
        LocalDate dateOfReservation = LocalDate.now();
        String dateOfReservationBeginning;
        String dateOfReservationEnd;
        System.out.println("enter the principal guest name :");
        principalGuestName = sc.nextLine();

        System.out.println("enter the number Of Rooms For Reservation :");
        numberOfRoomsForReservation = sc.nextInt();
        sc.nextLine();
        System.out.println("enter the number Of Persons :");
        numberOfPersons = sc.nextInt();
        sc.nextLine();

// test start<end
        boolean validateReservationDate;

        do{
            System.out.println("enter the date Of the beginning of the reservation :");
            dateOfReservationBeginning = sc.nextLine();
            System.out.println("enter the date Of the end of the reservation :");
            dateOfReservationEnd = sc.nextLine();
            validateReservationDate = HotelReservation.formattingDate(dateOfReservationBeginning, dateOfReservationEnd);
        }while(!validateReservationDate);
        HotelReservation createNewReservation = new HotelReservation(principalGuestName , numberOfRoomsForReservation, numberOfPersons, dateOfReservation, dateOfReservationEnd, dateOfReservationBeginning);

        boolean possibilityOfReservation = HotelReservation.setHotelAvailableRoomsCount( numberOfRoomsForReservation, reservationsList, createNewReservation);

        if(!possibilityOfReservation) {
            System.out.println("the reservation is not possible caused of the the number of the rooms needed");
            return null;
        }

        System.out.println("the reservation is succeeded");
            /* String numberOfRoomsForReservationToString = Integer.toString(numberOfRoomsForReservation);
            String numberOfPersonsToString = Integer.toString(numberOfPersons); */

        return createNewReservation;
    }

    public static void modifyReservation(LinkedList<HotelReservation> reservationsList) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrez le nom de l'invité pour modifier la réservation :");
        String guestName = sc.nextLine();

        for (HotelReservation reservation : reservationsList) {
            if (reservation.getPrincipalGuestName().equalsIgnoreCase(guestName)) {
                System.out.println("Réservation trouvée : " + reservation);
                System.out.println("Entrez les nouvelles informations de réservation :");

                System.out.println("Entrez le nouveau nombre de chambres :");
                int newNumberOfRooms = sc.nextInt();
                sc.nextLine();

                System.out.println("Entrez le nouveau nombre de personnes :");
                int newNumberOfPersons = sc.nextInt();
                sc.nextLine();

                int currentReservationRooms = reservation.getNumberOfRoomsForReservation();
                int totalRoomsNeeded = newNumberOfRooms - currentReservationRooms;

                boolean modificationPossible = HotelReservation.setHotelAvailableRoomsCount(totalRoomsNeeded, reservationsList, reservation);

                if (modificationPossible) {
                    reservation.setNumberOfRoomsForReservation(newNumberOfRooms);
                    reservation.setNumberOfPersons(newNumberOfPersons);
                    System.out.println("Les modifications ont été enregistrées.");
                } else {
                    System.out.println("La modification n est pas possible en raison du nombre insuffisant de chambres disponibles.");
                }
                return;
            }
        }
        System.out.println("Réservation non trouvée pour cet invité.");
    }

    public static void deleteReservation(LinkedList<HotelReservation> reservationsList) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrez le nom de l'invité pour supprimer la réservation :");
        String guestName = sc.nextLine();

        for (HotelReservation reservation : reservationsList) {
            if (reservation == null || reservation.getPrincipalGuestName() == null) {
                continue;
            }

            if (reservation.getPrincipalGuestName().equalsIgnoreCase(guestName)) {
                reservationsList.remove(reservation);
                System.out.println("La réservation a été supprimée.");
                return;
            }
        }
        System.out.println("Réservation non trouvée pour cet invité.");
    }


    public static void viewReservation(LinkedList<HotelReservation> reservationsList) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrez le nom de l'invité pour voir la réservation :");
        String guestName = sc.nextLine();

        for (HotelReservation reservation : reservationsList) {
            if (reservation.getPrincipalGuestName().equalsIgnoreCase(guestName)) {
                System.out.println("Réservation trouvée : " + reservation);
                return;
            }
        }
        System.out.println("Réservation non trouvée pour cet invité.");
    }


}
