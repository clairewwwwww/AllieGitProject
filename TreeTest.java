import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TreeTest 
{
    private static Tree tree;
    public TreeTest() throws IOException
    {
        tree = new Tree();
    }

    @Test
    void testRemoveTree() throws Throwable 
    {
        tree.addTree("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        tree.addTree("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");
        tree.addTree("blob : 01d82591292494afd1602d175e165f94992f6f5f : file2.txt");
        tree.addTree("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83 : file3.txt");
        tree.addTree("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");

        tree.removeTree("e7d79898d3342fd15daf6ec36f4cb095b52fd976");
        tree.removeTree("file2.txt");
        tree.removeTree("file1.txt");

        String expected = "blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83 : file3.txt" +
        "\n" + "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b";
        assertEquals(Tree.getCurrentFileName(), "52eae92bfc1db981246b6cb6acace3ec8b46e5ed");
        assertEquals(expected, readFile(Tree.getCurrentFileName()));
    }

    @Test
    void testAddTree() throws Throwable 
    {
        tree.addTree("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        tree.addTree("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");
        
        String expected = "blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt" +
        "\n" + "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b";


        File file = new File("objects", "80aaaaaea78ef9525bf854dcb1d60e2abe087221"); //SHA1 of the content
        Path filePath = Paths.get("objects", "80aaaaaea78ef9525bf854dcb1d60e2abe087221");
        List<String> temp = Files.readAllLines(filePath);
        String actual = "";
        for (String c : temp) 
        {
            actual += c;
        }
        assertTrue(file.exists());
        assertEquals(expected, actual);
    }

    private String readFile(String fileName) throws IOException 
    {
		BufferedReader br = new BufferedReader(new FileReader(fileName)); // the name of the file that want to read
		try {
			String string = "";
			while (br.ready()) {
				string += (char) br.read(); // read the char in the file, store to a string
			}
			br.close();
			return string; // return the string
		} catch (FileNotFoundException e) // if the file name is not found
		{
			return "File not found, whoops!";
		}
    }
}
