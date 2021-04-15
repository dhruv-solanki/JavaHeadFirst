import java.io.*;

// class WriteAFile {
//     public static void main(String[] args) {
//         try {
//             FileWriter wr = new FileWriter("test.txt");

//             wr.write("My First line to the text file!");

//             wr.close();
//         } catch(Exception e) {
//             e.printStackTrace();
//         }
//     }
// }

class WriteAFile {
    public static void main(String[] args) {
        try {
            File myFile = new File("MyText.txt");

            FileWriter fileWriter = new FileWriter(myFile);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write("What is 2+2/4");
            writer.write("What is 20+20/40");

            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}