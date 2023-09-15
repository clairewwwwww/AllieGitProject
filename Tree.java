import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Tree {
    private HashMap <String, String> treeMap;
    private HashMap <String, TreeMap<String, String>> blobMap;
    private PrintWriter pw;

    public Tree() throws FileNotFoundException
    {
        initialize();
        treeMap = new HashMap <String, String> ();
        blobMap = new HashMap <String, TreeMap<String, String>> ();
        pw = new PrintWriter ("tree");
    }

    public void initialize ()
    {
        File file = new File ("tree");
        if (!file.exists())
        {
            File newFile = new File("tree");
        }
        //new File("objects").mkdirs();
    }
    /*Add another entry into the tree:
Entries are added as new lines of Strings
    tree.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b")
    tree.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt")
Entries are a newline String in format  typeOfFile : shaOfFile : optionalFileName
    typeOfFile: Can be either the String 'blob' or 'tree' 
    shaOfFile: Is a valid sha1 of a file in the objects folder
    optionalFileName: if it's a blob, please include the filename
    the two are separated by a " : "
Do NOT allow for duplicate 'trees' or duplicate 'filenames' in the file */

    public void addTree (String entry) throws Throwable
    {
        String typeOfFile = entry.substring(0, 4); 
        String shaOfFile = entry.substring(7, 47);
        if(typeOfFile.equals("tree"))
        {
            treeMap.put(typeOfFile, shaOfFile);
        }
        else
        {
            String optionalFileName = entry.substring(50);
            TreeMap<String, String> temp = new TreeMap<String, String>();
            temp.put(shaOfFile, optionalFileName);
            blobMap.put(typeOfFile, temp);
        }

        Set<String> treeSet = treeMap.keySet();
        Set<String> blobSet = blobMap.keySet();

        for(String key1: treeSet)
		{             
            String string1 = key1 + " : " + treeMap.get(key1);
            pw.println(string1);
		}
        
        for(String key2: blobSet)
		{
            TreeMap<String, String> temp = blobMap.get(key2);
            String string2 = key2 + " : " + temp.firstKey() + temp.firstEntry();
            pw.println(string2);
		}
        pw.close();
    }
/*Remove an entry from a tree by...
Remove a BLOB entry from the tree based on a filename
    tree.remove("file1.txt")
Remove a TREE entry based on SHA1
    tree.remove("bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b")
 */
    public void removeBlob (String entry) throws IOException
    {
        String shaOfFile = entry.substring(7, 47);
        String optionalFileName = entry.substring(50);
        Set<String> treeSet = treeMap.keySet();
        for(String key1: treeSet)
        {
            if(treeMap.get(key1).equals(shaOfFile))
            {
                treeMap.remove(key1);
            }
        }
        Set<String> blobSet = blobMap.keySet();
        for(String key2: blobSet)
        {
            TreeMap<String, String> temp = blobMap.get(key2);
            if(temp.get(temp.firstKey()).equals(optionalFileName))
            {
                blobMap.remove(key2);
            }
        }

        pw = new PrintWriter(new FileWriter("tree", false));

        Set<String> treeSetNew = treeMap.keySet();
        Set<String> blobSetNew = blobMap.keySet();

        for(String key1: treeSetNew)
		{             
            String string = key1 + " : " + treeMap.get(key1);
            pw.println(string);
		}
        
        for(String key2: blobSetNew)
		{
            TreeMap<String, String> temp = blobMap.get(key2);
            String string = key2 + " : " + temp.firstKey() + temp.firstEntry();
            pw.println(string);
		}
        pw.close();
    }
}
