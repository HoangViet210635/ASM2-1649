package fgw.Assignment_2;

import java.util.InputMismatchException;
import java.util.Scanner;

// Computer System
public class CSystem {
    private LinkedListQueue<String> inBoxQueue;
    private LinkedListQueue<String> outBoxQueue;
    private LinkedListStack<String> processingStack;
    private String nameOfSystem;
    private CSystem destination;
    private CSystem oldDestination;
    boolean isConnect = false;

    public CSystem(){}
    public CSystem(String nameOfSystem){
        this.nameOfSystem = nameOfSystem;
        inBoxQueue = new LinkedListQueue<>();
        outBoxQueue = new LinkedListQueue<>();
        processingStack = new LinkedListStack<>();
    }

    public String getNameOfSystem() {
        return nameOfSystem;
    }

    public void setNameOfSystem(String nameOfSystem) {
        this.nameOfSystem = nameOfSystem;
    }

    public CSystem getDestination() {
        return destination;
    }

    public void setDestination(CSystem destination) {
        this.destination = destination;
    }

    public CSystem getOldDestination() {
        return oldDestination;
    }

    public void setOldDestination(CSystem oldDestination) {
        this.oldDestination = oldDestination;
    }

    public void menu(CSystem system_1, CSystem system_2){
        try {
            Scanner sc = new Scanner(System.in);
            Scanner sc_1 = new Scanner(System.in);
            Scanner sc_2 = new Scanner(System.in);
            int option, numberOfMessage;
            do {
                System.out.println("Menu of " + this.getNameOfSystem());
                System.out.println("1. Connect");
                System.out.println("2. Check connection");
                System.out.println("3. Disconnect");
                System.out.println("4. Send message");
                System.out.println("5. Receive message");
                System.out.println("6. Process message");
                System.out.println("7. Check inbox of system");
                System.out.println("8. Exit");
                System.out.print("Enter your option: ");
                option = sc.nextInt();
                switch (option){
                    case 1:
                        System.out.println("Select the system to connect");
                        System.out.println("1. " + system_1.getNameOfSystem());
                        System.out.println("2. " + system_2.getNameOfSystem());
                        System.out.println("3. Exit");
                        System.out.print("Enter your option: ");
                        int choice = sc.nextInt();
                        switch (choice){
                            case 1:
                                this.connect(system_1);
                                break;
                            case 2:
                                this.connect(system_2);
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("ERROR !!! JUST TYPE FROM 1 --> 3. TRY AGAIN");
                                break;
                        }
                        break;
                    case 2:
                        this.checkConnect();
                        break;
                    case 3:
                        this.disconnect();
                        break;
                    case 4:
                        if (isConnect) {
                            System.out.print("Number of messages you want to import: ");
                            numberOfMessage = sc_1.nextInt();
                            if (numberOfMessage <= 0) {
                                throw new IllegalArgumentException("Do not enter negative numbers, only positive numbers");
                            }
                            else {
                                for (int i = 1; i <= numberOfMessage; i++) {
                                    System.out.printf("Message %d: ", i );
                                    String message = sc_2.nextLine();
                                    this.send(message);
                                }
                            }
                        }
                        else {
                            System.out.println("This system is not currently connected to other systems for execution, " +
                                    "please connect to another system");
                        }
                        break;
                    case 5:
                        this.receive();
                        break;
                    case 6:
                        this.process();
                        break;
                    case 7:
                        this.checkMessage();
                        break;
                    case 8:
                        System.out.println("EXIT");
                        break;
                    default:
                        System.out.println("ERROR !!! JUST TYPE FROM 1 --> 8. TRY AGAIN");
                        break;
                }
            } while (option != 8);
        }
        catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
        catch (InputMismatchException e){
            System.out.println("Error: Do not enter letters, only numbers");
        }
        catch (Exception e){
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void connect(CSystem system){
        long startTime = System.nanoTime();
        try {
            if (!isConnect && !system.isConnect) {
                setDestination(system);
                setOldDestination(system);
                isConnect = true;
                system.setDestination(this);
                system.setOldDestination(this);
                system.isConnect = true;
                System.out.println(getNameOfSystem() + " has successfully connected to " + system.getNameOfSystem());
            }

            else if (getDestination() == system) {
                System.out.println(getNameOfSystem() + " is connecting to " + getDestination().getNameOfSystem());
            }

            else if (isConnect){
                System.out.println(getNameOfSystem() + " is connecting to " + getDestination().getNameOfSystem()
                        + ", it cannot connect to " + system.getNameOfSystem());
            }

            else {
                System.out.println(system.getNameOfSystem() + " is connecting to "
                        + system.getDestination().getNameOfSystem()
                        + ", " + getNameOfSystem() + " cannot interfere in this process ");
            }
        }
        catch (Exception e){
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void checkConnect(){
        long startTime = System.nanoTime();
        try {
            if (isConnect) {
                System.out.println("It's connecting to " + getDestination().getNameOfSystem());
            }
            else {
                System.out.println(getNameOfSystem() + " is not currently connected to other systems");
            }
        }
        catch (Exception e){
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void send(String message){
        long startTime = System.nanoTime();
        try {
            if (message.isEmpty()) {
                throw new NullArgumentException("Message is empty and cannot execute the program");
            } else {
                if (message.length() <= 250) {
                    System.out.println("Sending message: " + message);
                    outBoxQueue.enqueue(message);
                }
                else {
                    System.out.println("Messages over 250 characters, start truncating the message");
                    String tempMessage = "";
                    for (int i = 0; i < message.length(); i++){
                        tempMessage = tempMessage + message.charAt(i);
                        if (tempMessage.length() == 250 || i == message.length() - 1) {
                            System.out.println("Sending message: " + tempMessage);
                            outBoxQueue.enqueue(tempMessage);
                            tempMessage = "";
                        }
                    }
                }
            }
        }
        catch (NullArgumentException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void receive(){
        long startTime = System.nanoTime();
        try {
            if (isConnect){
                if (!getDestination().outBoxQueue.isEmpty()) {
                    while (!getDestination().outBoxQueue.isEmpty()){
                        inBoxQueue.enqueue(getDestination().outBoxQueue.dequeue());
                    }
                    System.out.println("Complete the process of receiving messages from "
                            + getDestination().getNameOfSystem());
                }
                else {
                    System.out.println("outQueue in " + getDestination().getNameOfSystem()
                            + " is empty, process cannot be executed");
                }
            }
            else {
                System.out.println("This system is not currently connected to other systems for execution, " +
                        "please connect to another system");
            }
        }
        catch (Exception e){
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void process(){
        long startTime = System.nanoTime();
        try {
            if (!inBoxQueue.isEmpty()){
                while (!inBoxQueue.isEmpty()){
                    System.out.println("Processing message: " + inBoxQueue.peek());
                    processingStack.push(inBoxQueue.dequeue());
                }
                System.out.println("Complete the process of processing messages of "
                        + getNameOfSystem());
            }
            else {
                System.out.println("inboxQueue is empty, process cannot be executed");
            }
        }
        catch (Exception e){
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void disconnect(){
        long startTime = System.nanoTime();
        try {
            if (isConnect) {
                System.out.println(getNameOfSystem() + " destroys the connection to "
                        + getDestination().getNameOfSystem());
                getDestination().isConnect = false;
                getDestination().setDestination(null);
                isConnect = false;
                destination = null;
            } else {
                System.out.println(getNameOfSystem() + " has disconnected");
            }
        }
        catch (Exception e){
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void checkMessage(){
        long startTime = System.nanoTime();
        int count = 0;
        try {
            if (!processingStack.isEmpty()){
                System.out.println(getOldDestination().getNameOfSystem()
                        + " just sent " + getNameOfSystem() + " a message: ");
                while (!processingStack.isEmpty()) {
                    count++;
                    System.out.printf("Part of message %d: ", count);
                    System.out.println(processingStack.pop());
                }
            }
            else if (processingStack.isEmpty()){
                System.out.println("The message has not been processed");
            }
        }
        catch (Exception e){
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        System.out.println("Time spent: " + elapsed + " nanoseconds");
    }
}
