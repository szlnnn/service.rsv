package mohi.wedding.service;

import java.util.Arrays;

class FormData {
    private String name;
    private String email;
    private boolean attending;
    private String[] guests;
    private int guestNumbers;
    private int veganMenus;
    private String message;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String[] getGuests() { return guests; }
    public void setGuests(String[] guests) { this.guests = guests; }

    @Override
    public String toString() {
        return "Name: " + name + "\nEmail: " + email + "\nAttending: " + attending + "\nGuestNumbers: " + guestNumbers +  "\nGuests: " + Arrays.toString(guests) + "\nVeganMenus: " + veganMenus + "\nComment: " + message;
    }

    public int getGuestNumbers() {
        return guestNumbers;
    }

    public void setGuestNumbers(int guestNumbers) {
        this.guestNumbers = guestNumbers;
    }

    public int getVeganMenus() {
        return veganMenus;
    }

    public void setVeganMenus(int veganMenus) {
        this.veganMenus = veganMenus;
    }

    public boolean isAttending() {
        return attending;
    }

    public void setAttending(boolean attending) {
        this.attending = attending;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

}