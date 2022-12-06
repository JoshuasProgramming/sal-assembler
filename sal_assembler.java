import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.ArrayList;

public class sal_assembler{

    // static ArrayList<String> salEncodedArray = new ArrayList<String>();
    static String salEncodedArray[];

    public static void main(String[] args) throws FileNotFoundException {
        boolean isSal = false;

        Scanner scanner = new Scanner(System.in);

        while(isSal == false){
            System.out.println("Enter sal file name: ");
            String salFileName = scanner.next();

            if(salFileName.endsWith(".sal")){
                System.out.println("sal file accepted");
                File salFile = new File(salFileName);
                isSal = true;
                readFile(salFile);

            } else {
                System.out.println("file not accepted");
            }
            
        }
    }

    public static void readFile(File salFile){
        
        try (BufferedReader reader = new BufferedReader(new FileReader(salFile))) {
            String line = reader.readLine();
            int lineNumber = 0;

            String[] lines;


            while(line != null){
                // System.out.println(line);
                encode(line);
                line = reader.readLine();

                lineNumber++;
                
            }

            encode(line);

            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void encode(String line){
        
        //declare an empty String array
        String[] arr = null;

        //convert the string
        arr = line.replaceAll("\\s", "\n").split("\\s+");

        for(int i = 0; i < arr.length; i++){

            if(arr[i].isEmpty()){
               i++;
            }  

            switch(arr[i]){
                case "LOAD": 
                if(arr[i] == "#"){
                    String rva = "01";
                }
                output("1100");
                break;

                case "SUB": 
                output("0001");
                break;

                case "OR": 
                output("0011");
                break;

                case "JMP": 
                output("0100");
                break;

                case "JGT": 
                
                output("0101");
                break;

                case "JLT": 
                output("0110");
                break;

                case "JEQ": 
                output("0111");
                break;

                case "unused": 
                output("1000");
                break;

                case "INC": 
                output("1001");
                break;

                case "DEC": 
                output("1010");
                break;

                case "NOT": 
                output("1011");
                break;

                case "STORE": 
                output("1101");
                break;

                case "ADD":
                output("0000");
                break;
            }
            
        }
        // C:/Users/joshu/Downloads/sal-examples/ex1.sal
    }

    public static void output(String instruction){
        System.out.println(instruction + "000" + "0" + "00" + "000000");
    }
}