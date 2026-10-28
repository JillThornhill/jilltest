import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

// Program that opens lots of files and doesn't close them
// =======================================================
public class BuggyProg21 {
// Cause a large number of file descriptors by
// opening the same file x number of times
// ===========================================
    public static void main(String[] args) {
        int numFiles=50;
        try {
            numFiles=Integer.parseInt(args[0]);
            }
        catch (Exception e) {}
        System.out.println("Number of files to open: "+numFiles);
       
        // Create a file and write to it
        try (FileWriter writer = new FileWriter("hello.txt")) {
            writer.write("Hello world\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
     // Create a thread that reads the file
     // Repeat as per command line argument 
        for(int i=0;i<numFiles;i++) {
           try{Thread.sleep(20);} catch(Exception e) {}
           System.out.println("Thread: "+i);
           new Thread(() -> {
            readFile();  
            }).start();
        }  
     }

// Opens file for reading but never closes it
// ==========================================
public static void readFile() {
        // Open the file and read from it
            System.out.println("Opening file");
	    try {
                BufferedReader reader =
                     new BufferedReader(new FileReader("hello.txt")) ;
                String line="";
                while ((line = reader.readLine()) != null) {
//                    System.out.println("Read from file: " + line);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(15*60*1000);
            }
        catch(Exception e) {}
    }

}
