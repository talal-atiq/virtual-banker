public class AdminLogin {
    private static final String VALID_CARD_NUMBER = "admin";
    private static final String VALID_PIN = "1234";

    public static boolean authenticate(String cardNumber, String pin) {
        return VALID_CARD_NUMBER.equals(cardNumber) && VALID_PIN.equals(pin);
    }
}
