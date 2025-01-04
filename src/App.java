import java.util.Scanner;

public class App {
    
    // ANSI - for color
    public static final String RESET = "\u001B[0m"; // Note : Eto ung mga special characters
    public static final String CYAN = "\u001B[36m"; // Kapag na print siya sa terminal
    public static final String GREEN = "\u001B[32m"; // magkaka kulay ung mga letters

    // Colored Binary
    public static final String BIN = "["+GREEN+"BINARY"+RESET+"]"; // Note : Line 11-28
    public static final String BING = GREEN+"[B]inary"+RESET; // Mga Variable lang na string nilalagyan ng kulay
    public static final String BINC = CYAN+"[B]inary"+RESET; // para di na kailangan ulit ulitin isulat sa print statement
    
    // Colored Decimal
    public static final String DEC = "["+GREEN+"DECIMAL"+RESET+"]";
    public static final String DECG = GREEN+"[D]ecimal"+RESET;
    public static final String DECC = CYAN+"[D]ecimal"+RESET;

    // Colored Octal
    public static final String OCT = "["+GREEN+"OCTAL"+RESET+"]";
    public static final String OCTG = GREEN+"[O]ctal"+RESET;
    public static final String OCTC = CYAN+"[O]ctal"+RESET;

    // VOID METHODS FOR PRINTING
    public static void clearScreen() {
        System.out.printf("\u001B[2J\u001B[H"); // Note : Eto naman para ma clear ung output
    }                                                  // Para hindi pa ulit ulit code
        
    public static void printTitle() { // Method para i print yung title na converter
        clearScreen();
        System.out.println("\u001B[1m━━━━━━━━━━━━━━━━━━━ Converter ━━━━━━━━━━━━━━━━━━━\u001B[0m");
    }

    public static String console() { // Method para mag display ng >>> bago mag type
        Scanner sc = new Scanner(System.in);
        System.out.print("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"+CYAN+"\n>>> "+RESET);
        return sc.nextLine();
    }

    public static boolean resetPrompt() { // Pang reset ng prompt para bumalik sa simula
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\nReset? (Y/N)");
            console();
            String choice = sc.nextLine();
    
            switch (choice.toUpperCase().trim()) {
                case "Y":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Invalid Choice");
                    continue;
            }
        }
    }

    // Computation Methods - Binary
    
    public static int binToDec(String binary) {
        // First is to convert the string into an array of char
        char[] array = binary.toCharArray();
        // Store the value and the power
        int value = 0;
        int power = 1;
        // Look at the least significant bit or the last char and then check
        // if that bit is 1, if yes then we add the power to the value.
        for (int i = array.length-1; i >=0; i--) {
            if (array[i] == '1') {
                value = value + power;
            } else if (array[i] != '1' && array[i] != '0') {
                clearScreen();
                System.out.println("Invalid Binary");
                return -1;
            }
            power = power + power;
        }    
        return value;
    }
    public static int binToOct(String binary) {
        char[] array = binary.toCharArray();
        int return_value = 0;
        int current_value = 0;
        int counter = 0;
        int power_dec = 1;
        int power_oct = 1;
        // Counter to check every 3 digits
        // and power to multiply for each digit

        for (int i=array.length-1;i>=0;i--) {
            counter++;
            if (array[i] == '1') {
                current_value = current_value + power_dec;
            } else if (array[i] != '1' && array[i] != '0') {
                clearScreen();
                System.out.println("Invalid Binary");
                return -1;
            }
            power_dec *= 2;
            if (counter % 3 == 0 || i == 0) {
                return_value += current_value * power_oct;
                power_oct = power_oct * 10;
                power_dec = 1;
                current_value = 0;
            }
            
        }

        return return_value;
    }

    // Computation Methods Decimal
    public static String decToBin(int decimal) {
        int bits = 0;
        int temp = decimal;
        while (temp > 0) {
            bits++;
            temp /= 2;
        }
    
        char[] array = new char[bits];
        int bit_counter = array.length;
        for (int i = decimal; i > 0; i /= 2) {
            if (i % 2 == 1) {
                array[bit_counter - 1] = '1';
            } else {
                array[bit_counter - 1] = '0';
            }
            bit_counter--;
        }
    
        return new String(array);
    }
    // Computation Methods Octal

    public static String octToBin(int octal) { // very messy

        // Convert int to str first and to array
        String octStr = ""+octal;
        char[] octArray = octStr.toCharArray();
        // Get its length
        int digits = octStr.length()*3;
        char[] binArray = new char[digits];
        
        int octDigit = 0;

        for (char digit : octArray) {
            // Subtract the character '0' to convert it to int - ???? idk it works
            int dig = digit - '0';

            char[] bin = decToBin(dig).toCharArray();
            char[] tempArray = new char[3];

            for (int i=2;i>=0;i--) {
                if (2 - i < bin.length) {
                    tempArray[i] = bin[bin.length - 1 - (2 - i)];    
                } else {
                    tempArray[i] = '0';
                }
            }

            for (char octbin : tempArray) {
                binArray[octDigit] = octbin;
                octDigit++;
            }
        }
        return new String(binArray);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean condition = true; // Main logic loop condition
        boolean error = false; // Displays error right before console()
        boolean validate = false; // Validation Checker if input is allowed

        while (condition == true) { // Main Logic
            printTitle();
            System.out.println("\nSelect the number system you want to convert from\n");
            System.out.printf("\n%-4s %-4s %-4s\t\t\u001b[31m  [E]xit\u001b[0m\n",BING,OCTG,DECG);
            if (error == true) {
                System.out.print("\u001B[31mInvalid Choice, Try Again\u001b[0m");
            }
            console();
            
            String choice = sc.nextLine();

            if (choice.toUpperCase().trim().equals("B")) { // If choice is Binary
                printTitle();
                System.out.println("\nSelect the number system you want to convert to\n");
                System.out.printf("\n%-4s %-4s\n",OCTG,DECG); // Display oct and dec choices

                console();
                
                String choice_binary = sc.nextLine();
                switch (choice_binary.toUpperCase().trim()) {
                    // Binary -> Octal
                    case "O":
                        validate = false; // Start case with validate and 
                        error = false; // error false to ensure logic will follow.
                        while (validate == false) {
                            printTitle();
                            System.out.printf("\n\n\t%s%s\t→\t%s%s\n",BIN,CYAN,RESET,OCT);
                            System.out.print("\nInput Binary\n");
                            if (error == true) {
                                System.out.print("\u001B[31mPlease input a binary value\u001b[0m");
                            }
                            console();
                            String binaryO = sc.nextLine();
                            char[] valBinaryArray = binaryO.toCharArray();
                            for (char valBin : valBinaryArray) {
                                int intValBin = valBin - '0';
                                if (intValBin == 1 || intValBin == 0) {
                                    validate = true;
                                } else {
                                    validate = false;
                                    error = true;
                                }
                            }
                            printTitle();
                            System.out.printf("\n\t\t%s = %s%s%s\n\t\t     to\n\t\t %s = %s%s%s\n",BIN,CYAN,binaryO,RESET,OCT,CYAN,binToOct(binaryO),RESET);    
                        }

                        if (resetPrompt() == false) {
                            condition = false;
                        } else {
                            error = false;
                            continue;
                        }
                        break;
                    // Binary -> Decimal
                    case "D":
                        validate = false;
                        error = false;
                        while (validate == false) {
                            printTitle();
                            System.out.printf("\n\n\t%s%s\t→\t%s%s\n",BIN,CYAN,RESET,DEC);
                            System.out.print("\nInput Binary\n");
                            if (error == true) {
                                System.out.print("\u001B[31mPlease input a binary value\u001b[0m");
                            }
                            console();
                            String binaryD = sc.nextLine();
                            char[] valBinaryArray = binaryD.toCharArray();
                            for (char valBin : valBinaryArray) {
                                int intValBin = valBin - '0';
                                if (intValBin == 1 || intValBin == 0) {
                                    validate = true;
                                } else {
                                    validate = false;
                                    error = true;
                                }
                            }
                            printTitle();
                            System.out.printf("\n\t\t %s = %s%s%s\n\t\t      to\n\t\t%s = %s%s%s\n",BIN,CYAN,binaryD,RESET,DEC,CYAN,binToDec(binaryD),RESET);
                        }
                        if (resetPrompt() == false) {
                            condition = false;
                        } else {
                            error = false;
                            continue;
                        }
                        break;
                    default:
                        error = true;
                }
            } else if (choice.toUpperCase().trim().equals("O")) {
                printTitle();
                System.out.println("\nSelect the number system you want to convert to\n");
                System.out.printf("\n%-4s %-4s\n",BING,DECG);

                console();
                
                choice = sc.nextLine();
                switch (choice.toUpperCase().trim()) {
                    // Binary
                    case "B":
                        printTitle();
                        System.out.printf("\n\n\t%s%s\t\t→\t%s%s\n",OCT,CYAN,RESET,BIN);
                        System.out.print("\nInput Octal\n");
                        console();
                        int decimal = sc.nextInt();
                        sc.nextLine();

                        printTitle();
                        System.out.printf("\n\t\t %s = %s%d%s\n\t\t     to\n\t\t%s = %s%s%s\n",OCT,CYAN,decimal,RESET,BIN,CYAN,octToBin(decimal),RESET);

                        if (resetPrompt() == false) {
                            condition = false;
                        } else {
                            error = false;
                            continue;
                        }
                        break;
                }
            } else if (choice.toUpperCase().trim().equals("D")) {
                printTitle();
                System.out.println("\nSelect the number system you want to convert to\n");
                System.out.printf("\n%-4s %-4s\n",BING,OCTG);

                console();
                
                choice = sc.nextLine();
                switch (choice.toUpperCase().trim()) {
                    // Decimal to [B]inary
                    case "B":
                        printTitle();
                        System.out.printf("\n\n\t%s%s\t→\t%s%s\n",DEC,CYAN,RESET,BIN);
                        System.out.print("\nInput Decimal\n");
                        console();
                        int decimal = sc.nextInt();
                        sc.nextLine();

                        printTitle();
                        System.out.printf("\n\t\t%s = %s%d%s\n\t\t     to\n\t\t %s = %s%s%s\n",DEC,CYAN,decimal,RESET,BIN,CYAN,decToBin(decimal),RESET);

                        if (resetPrompt() == false) {
                            condition = false;
                        } else {
                            error = false;
                            continue;
                        }
                        break;
                }
            } else if (choice.toUpperCase().trim().equals("E")) {
                condition = false;
                break;
            } 
            else {
                error = true;
            }              
        }
    }
}
