public class ImageInverterMain {
    public static void main(String[] args) {
        // initialize file names to null so they are never uninitialized
        String output_file_name = null;
        String input_file_name = null;
        if (args.length < 1) {
            about();
            System.exit(1);
        }
        if (args.length > 2) {
            System.out.println("Please use no more than two arguments");
            about();
            System.exit(1);
        }
        //help option
        if (args[0].matches("-{1,2}h(elp)?")) {
            help();
            System.exit(0);
        }
        else {
            input_file_name = args[0];
            //two args given so set output file to second arg
            if (args.length == 2) {
                output_file_name = args[1];
            }
            //default output name
            else {
                output_file_name = args[0] + "_inverted";
            }
        }
        // create processor object
        ImageInverter processor = new ImageInverter();
        //read image by processor into a bitmap iamge object
        BitmapImage image = processor.readImage(input_file_name);
        // create new inverted image by processing existing iamge object
        BitmapImage new_image = processor.process(image, output_file_name);
        // write new image object to a file
        processor.writeImage(new_image);
    }
    // help instructions
    public static void help() {
        System.out.println("Bitmap Image Inverter Help:\nUse the command \"java ImageInverterMain <input_file_name> <output_file_name>?\"");
        System.out.println("<input_file_name> is a required argument. It is the name of a bitmap image located in the ../images directory\n\tDo not include the file type extension, for example, use \"image\" not \"image.bmp\"");
        System.out.println("<output_file_name> is an optional argument. This will be the name of the new inverted image that the program produces.\n\tWarning, if there is already a file of the same name and type, it will be overwritten.");
        System.out.println("\tDo not include the file name extension for this argument.");
        System.out.println("\tIf this argument is not provided, the default output file name will be \"<input_file_name>_inverted.bmp\"");
        about();
        return;
    }
    // about the program
    public static void about() {
        System.out.println("About: use the argument -help, --help, -h, --h for help using this program");
        System.out.println("ImageInverterMain is a java CLI program that inverts the colors of a 24-bit bitmap image and outputs the results into a new image");
        return;
    }
}