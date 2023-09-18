import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IndexTest 
{   
    private static Index index;
    private static Blob blob;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        /*
         * Utils.writeStringToFile("junit_example_file_data.txt", "test file contents");
         * Utils.deleteFile("index");
         * Utils.deleteDirectory("objects");
         */
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        /*
         * Utils.deleteFile("junit_example_file_data.txt");
         * Utils.deleteFile("index");
         * Utils.deleteDirectory("objects");
         */
    }
    public IndexTest() throws Throwable
    {
        index = new Index();
    }
    
    @Test
    void testAddBlobs() throws Throwable 
    {
        
    }

    @Test
    @DisplayName("Testing index File and 'object' Folder Creation")
    void testInitialize() throws IOException 
    {
        //Index index = new Index();
        index.initialize();

        File file = new File("index");
        Path path = Paths.get("objects");
        //assertTrue(file.isDirectory());

        assertTrue(file.exists());
        assertTrue(Files.exists(path));
    }

    @Test
    void testRemoveBlob() {

    }
}
