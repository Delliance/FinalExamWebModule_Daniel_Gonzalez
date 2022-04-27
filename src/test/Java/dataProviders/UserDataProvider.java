package dataProviders;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class UserDataProvider {

    private static String firstName;

    private static String lastName;

    private static String email;

    private static String password;

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

//    public UserDataProvider() {
//        this.firstName = "randomName"; //this one is fixed because I used it to check the login
//        this.lastName = randomLastNameGenerator();
//        this.email = randomEmailGenerator();
//        this.password = randomPasswordGenerator();
//    }

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
    }

    public String getLastName() {
        return randomLastNameGenerator();
    }

    public String getEmail() {
        return randomEmailGenerator();
    }

    public String getPassword() {
        return randomPasswordGenerator();
    }

//    @DataProvider(name = "registrationData")
//    public Object[][] registrationData() {
//        return new Object[][] {{this.firstName, this.lastName, this.email, this.password}};
//    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {{getEmail(), getPassword()}};
    }

}
