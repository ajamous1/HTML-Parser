// HTML Parser
// @author Ahmad Jamous

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
public class HTMLParser {


    public static void main(String[] args) throws IOException {


        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter a full web page address (URL) and press the Enter key: ");
            String webPageAddress = keyboard.nextLine();
            Scanner scan = new Scanner((new URL(webPageAddress)).openStream());

            //while there is another input from the scanner
            while (scan.hasNextLine()) {
                //the string of the entire stream
                String line = scan.nextLine();
                if (line.toLowerCase().indexOf("<title>") > 0 || line.toLowerCase().startsWith("<title>")) {
                    //whatever contains "<title" is declared as var
                    int var = line.indexOf("<title");
                    //assign a temp title to narrow down what the parser has to look for
                    String tempTitle = line.substring(var);
                    //indexOf: Returns the index within this string of the first occurrence of the specified substring.
                    int tagVar = tempTitle.indexOf(">");
                    tempTitle = tempTitle.substring(tagVar + 1);
                    int endVar = tempTitle.indexOf("</title>");
                    //the string of the title
                    String title = null;
                    //endVar MAY return a value of -1, so the following if statement accounts for that
                    if(endVar < 0) {
                        //trim out any excess that does returns a value of -1
                        line = scan.nextLine().trim();
                        if(line.contains("</title>")){

                            endVar = line.indexOf("</title>");
                            title = line.substring(0, endVar);



                        } else {
                            title = line;

                        }


                    }
                    else{
                        title = tempTitle.substring(0, endVar);

                    }

                    System.out.println("Title: " + title);
                    break;

                }


            }


        } catch (IOException ioe) {

            System.err.println(ioe);

        }


    }



}