// simple class representing a bitmap image
// is definitely not a complete class since it does not properly extract all data fields from the file
// as well as the pixels array could be better represented by a 2- or 3-dimensional array for each row/column/sub-pixel
public class BitmapImage {
    String file_name;
    byte[] fileHeader;
    byte[] imageInfo;
    byte[] pixels;
    int height;
    int width;
    int padding;
    int bits_per_pixel;
    int bytes_per_pixel;
    int bytes_per_row;
    public BitmapImage(String file_name, byte[] fileHeader, byte[] imageInfo, byte[] pixels, int width, int height) {
        this.file_name = file_name;
        bits_per_pixel = 24;
        bytes_per_pixel = bits_per_pixel / 4;
        this.fileHeader = fileHeader;
        this.imageInfo = imageInfo;
        this.pixels = pixels;
        this.width = width;
        this.height = height;
        this.bytes_per_row = width * bytes_per_pixel;
        int x = (width * 3) % 4;
        if (x == 0) {
            this.padding = 0;
        }
        else {
            this.padding = 4 - x;
        }
    }
    public String getFileName() {
        return this.file_name;
    }
    public byte[] getPixels() {
        return this.pixels;
    }
    public byte[] getFileInfo() {
        return fileHeader;
    }
    public byte[] getImageInfo() {
        return imageInfo;
    }
    public byte getPixel(int i) {
        return this.pixels[i];
    }
    public int getPadding() {
        return this.padding;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public int getBytesPerPixel() {
        return bytes_per_pixel;
    }
    public int getBytesPerRow() {
        return bytes_per_row;

    }
    @Override
    public String toString() {
        return String.format("Image:\nname:%s\nwidth: %d\nheight: %d\npadding: %d", this.file_name, this.width, this.height, this.padding);
    }
}
