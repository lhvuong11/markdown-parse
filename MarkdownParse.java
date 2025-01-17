// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;

        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);

            int openParen2 = markdown.indexOf("(", openParen + 1);
            int closeParen2 = markdown.indexOf(")", openParen2 + 1);

            
            if (openParen == -1 || closeParen == -1) // test-file4.md
            {
                System.out.println("Invalid Input detected. Please include link in only one pair of parentheses.");
                return null;
            }

            if (nextCloseBracket == -1)
            {
                System.out.println("double brackets");
                return toReturn;
            }

            if (openParen2 != -1 || closeParen2 != -1) // for test-file2.md
            {
                closeParen = markdown.indexOf(")", closeParen2 + 1);
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                return toReturn;
            }



           // toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;

        }

        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}