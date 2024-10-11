import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;

public class BoundaryFill{
    public static void main(String args[]){
        if(args.length != 4){
            System.out.println("Error: Invalid Input. Please input in the follwing format: <fileName> <startX> <startY> <fill symbol>");
            return;
        }
        Scanner in;
        FileReader reader;

        // Process command line into variables
        try{
        
        String fileName = args[0];
        int startX = Integer.parseInt(args[1]);
        int startY = Integer.parseInt(args[2]);
        char fillSymbol = args[3].charAt(0);

         // Read in file
        reader = new FileReader(fileName);
        in = new Scanner(reader);
        
        // Get image name
        String imageName = in.nextLine();

        //Get image dimensions (rows and columns)
        int numRows = in.nextInt();
        int numColumns = in.nextInt();
        
        System.out.println(numRows);
        System.out.println((numColumns));

        // Create image matrix
        in.nextLine();
        char[][] image = new char[numRows][numColumns];
        for(int i = 0; i < numRows; i++){
            String row = in.nextLine();
            String newRow = row.replaceAll("\\s","");
            //System.out.println(row);
            for(int j = 0; j < numColumns; j++){
                image[i][j] = newRow.charAt(j);
            }
        }
       in.close();

       for(int i = 0; i < numRows; i++){
        for(int j = 0; j < numColumns; j++){
          //System.out.print(image[i][j]);
      }
      System.out.println();
    }

       // Fill in boundary
       char boundarySymbol = '1';
       System.out.println(boundarySymbol);
       boundaryFill(image, startX, startY, fillSymbol, boundarySymbol, numRows, numColumns);

       // Write to file
       FileWriter out = new FileWriter(imageName + ".txt");

       for(int i = 0; i < numRows; i++){
        
          for(int j = 0; j < numColumns; j++){
            out.write(image[i][j]);
        }
        out.write("\n");
       }
       out.close();

       System.out.println("Output written to  " + imageName);

       System.out.println("End of Processing");

        } catch (NumberFormatException e){
            System.out.println("Error: Expected a valid X and Y coordinate.");
        } catch (FileNotFoundException fnf){
            System.out.println("File not found: Input a valid file name.");
        } catch (IOException ioe){
            System.out.println(ioe);
        }
    }

    public static void boundaryFill(char image[][], int startX, int startY, char fillSymbol, char boundarySymbol, int numRows, int numColumns){
        // Base case: Checks if x and y are in bounds of image.
        
        if(startX < 0 || startY < 0 || startX >= image[0].length || startY >= image.length){
            return;
        }

        if(image[startY][startX] == boundarySymbol || image[startY][startX] == fillSymbol){
            return;
        }

        if(image[startY][startX] != boundarySymbol && image[startY][startX] != fillSymbol){
            image[startY][startX] = fillSymbol;
            //System.out.println("Fill: (" + startX + ", " + startY + ")");
        
            boundaryFill(image, startX+1, startY, fillSymbol, boundarySymbol,numRows,numColumns);
            boundaryFill(image, startX-1, startY, fillSymbol, boundarySymbol,numRows,numColumns);
            boundaryFill(image, startX, startY+1, fillSymbol, boundarySymbol,numRows,numColumns);
            boundaryFill(image, startX, startY-1, fillSymbol, boundarySymbol,numRows,numColumns);
        }
    }
}