import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TreeTest {
    private static Tree tree;
    public TreeTest() throws FileNotFoundException
    {
        tree = new Tree();
    }

    @Test
    void testAddTree() throws Throwable 
    {
        // Run the code
        tree.addTree("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        tree.addTree("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");


    }

    @Test
    void testInitialize() 
    {
        // Run the code
        tree.initialize();

        // check if the file exists
        File file = new File("tree");

        assertTrue(file.exists());
    }

    @Test
    void testRemoveBlob() {

    }
}
