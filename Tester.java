public class Tester {
    public static void main(String[] args) throws Throwable {
        Blob tester = new Blob ("tester.txt");
        Index index = new Index ();
        index.addBlobs("tester.txt");
        //index.addBlobs ("tester.txt");
        //index.removeBlob("tester.txt");
    }
}
