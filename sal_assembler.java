import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.ArrayList;

public class sal_assembler{

    // static ArrayList<String> salEncodedArray = new ArrayList<String>();
    static String salEncodedArray[];

    static ArrayList<String> outputTxtItems = new ArrayList<String>();

    static String salName = "";

    // static FileWriter outputTextFile = new FileWriter("output.txt");
   
    public static void main(String[] args) throws FileNotFoundException {

        // try{
        //     FileWriter outputTextFile  = new FileWriter("C:/Users/joshu/Documents/output.txt");
            
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }


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

        // System.out.println(outputTxtItems);
    }

    public static void readFile(File salFile){
        
        try (BufferedReader reader = new BufferedReader(new FileReader(salFile))) {
            String line = reader.readLine();

            while(line != null){
                encode(line);
                line = reader.readLine();
            }
            encode(line);

            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void encode(String line){

        String rva = null;
        String rva01 = null;
        final ArrayList<String> rva_arr = new ArrayList<String>();
        final ArrayList<String> size_arr = new ArrayList<String>();
        


        try (FileWriter outputTextFile = new FileWriter("output.txt")) {
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //declare an empty String array
        String[] arr = null;

        String arr2 = null;

        //convert the string
        if(line != null){
            arr = line.replaceAll("\\s", "\n").split("\\s+");
        }

        arr2 = line;
        
        boolean rvaOn = false;

        if(arr != null){

            for(int i = 0; i < arr.length; i++){

                if(arr[i].isEmpty()){
                   i++;
                }  

                // System.out.println(arr2.replaceAll("\\s+", "\n"));

                if(arr2.replaceAll("\\s+", "\n").contains("#")){
                    rva_arr.add("01");
                } else {
                    rva = "00";
                }

                Matcher m = Pattern.compile("[-+]?\\d+").matcher(arr2.replaceAll("\\s+", "\n"));

                if(m.find()){
                    int number = Integer.parseInt(m.group());
                    if(number > 63){
                        size_arr.add("1");
                    } else {
                        size_arr.add("0");
                    }
                }
                
                System.out.println(size_arr);

                // char[] ch = new char[arr[i].length()];

                // for(int k = 0; k < arr[i].length(); k++){
                //     ch[k] = arr[i].charAt(k);

                //     if(ch[k] == '#'){
                //         rva01 = "01";
                //         System.out.println(ch[k] + " " + 1);
                //         // System.out.println(rva01);
                //         rva_arr.add("01");
                //         System.out.println(rva_arr + " " + 1);
                //         rvaOn = true;

                //     } else {
                //         rva = "00";
                //         // System.out.println(rva);
                //         System.out.println(ch[k]);
                        
                //     }
                // }

                // System.out.println(rva_arr.contains("01") + " " + rva_arr + " " + rvaOn);
                

    
                switch(arr[i]){

                    case "LOAD": 

                    if(rva_arr.contains("01")){
                        output("1100", "01");
                    } else {
                        output("1100", rva);
                    }

                    break;
    
                    case "SUB": 

                    if(rva_arr.contains("01") ){
                        output("0001", "01");
                    } else {
                    output("0001", rva);
                    }
                    break;
    
                    case "OR": 

                    if(rva_arr.contains("01") ){
                        output("0011", "01");
                    } else {
                    output("0011", rva);
                    }
                    break;
    
                    case "JMP": 

                    if(rva_arr.contains("01") ){
                        output("0100", "01");
                    } else {
                    output("0100", rva);
                    }
                    break;
    
                    case "JGT": 

                    if(rva_arr.contains("01") ){
                        output("0101", "01");
                    } else {
                    output("0101", rva);
                    }
                    break;
    
                    case "JLT": 
                    if(rva_arr.contains("01") ){
                        output("0110", "01");
                    } else {
                    output("0110", rva);
                    }
                    break;
    
                    case "JEQ": 
                    if(rva_arr.contains("01") ){
                        output("0111", "01");
                    } else {
                    output("0111", rva);
                    }
                    break;
    
                    case "unused": 
                    if(rva_arr.contains("01") ){
                        output("1000", "01");
                    } else {
                    output("1000", rva);
                    }
                    break;
    
                    case "INC": 
                    if(rva_arr.contains("01") ){
                        output("1001", "01");
                    } else {
                    output("1001", rva);
                    }
                    break;
    
                    case "DEC": 
                    if(rva_arr.contains("01") ){
                        output("1010", "01");
                    } else {
                    output("1010", rva);
                    }
                    break;
    
                    case "NOT": 
                    if(rva_arr.contains("01") ){
                        output("1011", "01");
                    } else {
                    output("1011", rva);
                    }
                    break;
    
                    case "STORE": 
                    if(rva_arr.contains("01") ){
                        output("1101", "01");
                    } else {
                    output("1101", rva);
                    }
                    break;
    
                    case "ADD":
                    if(rva_arr.contains("01") ){
                        output("0000", "01");
                    } else{
                        output("0000", rva);
                    }
                    
                    break;
                }

        }
            
        }

        // outputTextFile(line, line, line, line, line);

        
        // C:/Users/joshu/Downloads/sal-examples/ex1.sal
    }
    

    public static void output(String instruction, String rva){

        //print the binary translation
        System.out.println(instruction + "000" + "0" + rva + "000000");

        

        //insert each sub section of the binary instruction to the 'outputTextFile' function as parameter
        // outputTextFile(instruction, "000", "0", "00", "000000");
        outputTextFile(instruction, "00", "0", rva, "000000");
        outputTxtItems.add(instruction + "000" + "0" + rva + "000000");
        
    }

    public static void outputTextFile(String instruction, String r, String size, String rva, String operand){
        try{
            FileWriter outputTextFile  = new FileWriter("C:/Users/joshu/Documents/" + "ex1" + ".bin");

            for(int i = 0; i < outputTxtItems.size(); i++){
                outputTextFile.write(instruction + "000" + "0" + rva + "000000" + "\n");
            }
           
            outputTextFile.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }
}