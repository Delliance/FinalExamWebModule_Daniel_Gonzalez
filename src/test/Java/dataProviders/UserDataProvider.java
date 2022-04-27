package dataProviders;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class UserDataProvider {

    private static String lastName;

    private static String email;

    private static String password;

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private String randomLastNameGenerator() {
        if (lastName == null) {
            int nameLength = 7;
            Random random = new Random();
            StringBuilder builder = new StringBuilder(nameLength);
            for (int i = 0; i < nameLength; i++) {
                builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
            }
            lastName = builder.toString();
        }
        return lastName;
    }

    private String randomEmailGenerator() {
        if (email == null) {
            int nameLength = 7;
            Random random = new Random();
            StringBuilder builder = new StringBuilder(nameLength);
            for (int i = 0; i < nameLength; i++) {
                builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
            }
            email = builder +"@randomEmail.com";
        }
        return email;
    }

    private String randomPasswordGenerator() {
        if (password == null) {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
            password = RandomStringUtils.random(15, characters);
        }
        return password;
    }

    public String getFirstName() {
        return "randomName";
    } //fixed first name for an assent in the login

    public String getLastName() {
        return randomLastNameGenerator();
    }

    public String getEmail() {
        return randomEmailGenerator();
    }

    public String getPassword() {
        return randomPasswordGenerator();
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {{getEmail(), getPassword()}};
    }

}
