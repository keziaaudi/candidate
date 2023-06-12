/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.candidate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author keziaaudi
 */
public class Candidate {
    private String name;
    private String gender;
    private String address;
    private int age;
    
    public Candidate(String name, String gender, String address, int age) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getInitials() {
        String[] names = name.split(" ");
        if (names.length >= 2) {
            return String.valueOf(names[0].charAt(0)) + String.valueOf(names[1].charAt(0));
        }
        return "";
    }
}

class PowerRecruitmentProgram {
    private static List<Candidate> candidates = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("POWER Recruitment Program");
            System.out.println("1. Input New Candidate");
            System.out.println("2. View Candidate's Data");
            System.out.println("3. Remove Candidate");
            System.out.println("4. Exit");

            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    inputNewCandidate(scanner);
                    break;
                case 2:
                    viewCandidateData();
                    break;
                case 3:
                    removeCandidate(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }

    private static void inputNewCandidate(Scanner scanner) {
        System.out.print("Enter candidate's name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        while (!validateName(name)) {
            System.out.println("Invalid name! Name must be 5 to 20 characters, at least 2 words, " +
                    "and the space should not be at the beginning or end.");
            System.out.print("Enter candidate's name: ");
            name = scanner.nextLine();
        }

        System.out.print("Enter candidate's gender (male/female): ");
        String gender = scanner.nextLine();

        while (!validateGender(gender)) {
            System.out.println("Invalid gender! Gender must be 'male' or 'female'.");
            System.out.print("Enter candidate's gender (male/female): ");
            gender = scanner.nextLine();
        }

        System.out.print("Enter candidate's address: ");
        String address = scanner.nextLine();

        while (!validateAddress(address)) {
            System.out.println("Invalid address! Address must end with 'street'.");
            System.out.print("Enter candidate's address: ");
            address = scanner.nextLine();
        }

        System.out.print("Enter candidate's age: ");
        int age = scanner.nextInt();

        while (!validateAge(age)) {
            System.out.println("Invalid age! Age must be between 17 and 30.");
            System.out.print("Enter candidate's age: ");
            age = scanner.nextInt();
        }

        Candidate candidate = new Candidate(name, gender, address, age);
        candidates.add(candidate);

        System.out.println("Candidate added successfully!");
        System.out.println("Initials: " + candidate.getInitials());
    }
    
    private static void viewCandidateData() {
        if (candidates.isEmpty()) {
            System.out.println("No Data!");
        } else {
            Collections.sort(candidates, Comparator.comparing(Candidate::getName));
            for (Candidate candidate : candidates) {
                System.out.println("Name: " + candidate.getName());
                System.out.println("Gender: " + candidate.getGender());
                System.out.println("Address: " + candidate.getAddress());
                System.out.println("Age: " + candidate.getAge());
                System.out.println("Initials: " + candidate.getInitials());
                System.out.println();
            }
        }
    }

    private static void removeCandidate(Scanner scanner) {
        if (candidates.isEmpty()) {
            System.out.println("No Data!");
            return;
        }

        Collections.sort(candidates, Comparator.comparing(Candidate::getName));
        System.out.println("Candidate's Data:");
        for (int i = 0; i < candidates.size(); i++) {
            Candidate candidate = candidates.get(i);
            System.out.println((i + 1) + ". " + candidate.getName());
        }

        System.out.print("Enter the candidate number to be removed (0 to cancel): ");
        int candidateNum = scanner.nextInt();

        if (candidateNum == 0) {
            System.out.println("Deleting operation canceled.");
            return;
        } else if (candidateNum < 1 || candidateNum > candidates.size()) {
            System.out.println("Invalid candidate number!");
            return;
        }

        Candidate candidateToRemove = candidates.get(candidateNum - 1);
        candidates.remove(candidateToRemove);

        System.out.println("Candidate removed successfully!");
    }

    private static boolean validateName(String name) {
        if (name.length() < 5 || name.length() > 20) {
            return false;
        }

        if (name.charAt(0) == ' ' || name.charAt(name.length() - 1) == ' ') {
            return false;
        }

        String[] words = name.split(" ");
        if (words.length < 2) {
            return false;
        }

        return true;
    }

    private static boolean validateGender(String gender) {
        return gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female");
    }

    private static boolean validateAddress(String address) {
        return address.toLowerCase().endsWith("street");
    }

    private static boolean validateAge(int age) {
        return age >= 17 && age <= 30;
    }
}
