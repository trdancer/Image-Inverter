import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public abstract class ImageProcessor {
    final long mask = 255l;
    // abstract class so that in theory an image processor class could be extended to include more processing funcitonality
    public abstract BitmapImage process(BitmapImage image, String new_image_name);
    // converts an array of N bytes into an integer
    // bytes must be in little-endian format, so the least significant digits are at index 0
    protected int bytesToInt(byte[] bytes) {
        int num = 0;
        for (int i = 0; i < bytes.length; i++) {
            // Java reads bytes as signed values, so only keep first 8 chars of each byte-to-binary value using a mask
            // every add the shifted value of this byte by 8*i since the value is stored little-endian
            num += (int) (bytes[i] & mask) << (8*i);
        }
        return num;
    }
    // expects just a file name, no extensions or path
    // takes a file name as input and creates a bitmap image object with which to work with 
    public BitmapImage readImage(String file_name) {
        String path = "../images/" + file_name + ".bmp";
        try {
            FileInputStream fileReader = new FileInputStream(path);
            // bmp header is 14 bytes
            byte[] header_bytes = new byte[14];
            // bmp image info section is 40 bytes
            byte[] info_bytes = new byte[40];
            // read bytes into array from file
            fileReader.read(header_bytes);
            // read bytes into array from file
            fileReader.read(info_bytes);
            int n = fileReader.available();
            // create pixels array which is the size of the rest of the available bytes in the file
            byte[] pixels = new byte[n];
            fileReader.read(pixels);
            // close file for safety
            fileReader.close();
            int temp_index = 0;
            int val_index = 0;
            //used to temporalily store bytes to be processed
            byte[] temp = new byte[4];
            // vals stores {width, height, bits/pixel}
            int[] vals = new int[3];
            // iterate through the data in the image info to extract this image's width, height, and bits/pixel
            // width, height, and bits/pixel start at the 4th, 8th, and 14th byte positions repsectively
            // width: 4 bytes
            // height: 4 bytes
            // bits/pixel: 2 bytes
            for (int i = 4; i < 16; i ++) {
                // ignore indices 12, 13
                if (i >= 12 && i < 14) {
                    continue;
                }
                // if at index 14, set temp to a length-2 array
                if (i == 14) {
                    temp = new byte[2];
                }
                // add this pixel value to be processed
                temp[temp_index++] = info_bytes[i];
                // if gathered all pixels for this data, process data into a readable value
                if (temp_index == temp.length) {
                    // save value in vals array
                    vals[val_index++] = bytesToInt(temp);
                    temp_index = 0;
                }
            }
            // exit program if this image is not 24-bit
            if (vals[2] != 24) {
                System.out.println("You have not inputted a 24-bit bitmap file, please use a 24-bit bitmap image to work correctly");
                System.exit(1);
            }
            // create an image using the read values
            BitmapImage image = new BitmapImage(file_name, header_bytes, info_bytes, pixels, vals[0], vals[1]);
            return image;
        }
        catch (IOException e){
            // show error if one occurs
            System.out.println("Could not open that image");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }
    public void writeImage(BitmapImage image) {
        if (image == null) {
            return;
        }
        String path = String.format("../images/%s.bmp", image.getFileName());
        try {
            FileOutputStream fileWriter = new FileOutputStream(path);
            fileWriter.write(image.getFileInfo());
            fileWriter.write(image.getImageInfo());
            fileWriter.write(image.getPixels());
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("Could not write that image");
            System.out.println(e.getMessage());
            return;
        }
        return;
    }
}
