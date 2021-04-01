public class ImageInverter extends ImageProcessor {
    public ImageInverter() {}
    public BitmapImage process(BitmapImage image, String new_image_name) {
        // check that the image object passed in is not null
        if (image == null) {
            System.out.println("No image to process");
            return null;
        }
        // pixels for the new image
        byte[] new_pixels = new byte[image.getPixels().length];
        // existing image properties
        int padding = image.getPadding();
        int height = image.getHeight();
        int width = image.getWidth();
        // the number of byte values per row of the image, inclding padding bytes
        int true_row_width = ((width*3) + padding);
        // existing pixel, is not actually a full pixel, but a single rgb value of a subpixel 
        byte pixel;
        // new subpixel value to be written to file
        byte new_pixel;
        //iterate through image's rows
        for (int i = 0; i < height; i++) {
            // iterate across image's columns
            for (int j = 0; j < true_row_width; j++) {
                // since pixels is a 1 dimensional array but iterating through i and j like a 2d array, use formula to convert 2d index into 1d index
                //get the value of the old image's pixel value at this index
                pixel = image.getPixel((i*true_row_width)+j);
                // determine if this is a padding byte
                if (true_row_width - j <= padding) {
                    // a padding byte is always 0 so set new pixel to this pixel
                    new_pixel = pixel;
                }
                // otherwise this is a value of an actual pixel 
                else {
                    // true pixel
                    // inversion = 255 - pixel_value = logical bitwise NOT of value
                    new_pixel = (byte) ~pixel;
                }
                // set new image's pixel value at the same index to the new value 
                new_pixels[(i*true_row_width)+j] = new_pixel;
            }
        }
        return new BitmapImage(new_image_name, image.fileHeader, image.imageInfo, new_pixels, image.width, image.height);
    }
}
