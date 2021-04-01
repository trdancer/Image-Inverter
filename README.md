# Image Inverter Project for Coding Assessment at BlockApps

## Troy Kelley

A java CLI tool that processes a 24-bit bitmap image and creates a new inverted-color version of it.

This repo includes the program and some sample images with which to test it.
## Usage

- Clone the repository onto your machine into the desired location.
- Navigate to the "bin/" directory
- Compile using >>javac ImageInverterMain.java 
- Run using >>java ImageInverterMain <input_image_name> <output_file_name>?
- The new iamge will be located in the "images/" directory

### Help

For more information on how to use the program, use java ImagerInverterMain --help

## CLI Arguments

- input_image_name:
  - Required
  - The name of a bitmap image file (.bmp) located in the images folder
  - Does not include the file extension
- output_file_name:
  - Optional
  - Default: <input_image_name>_inverted
  - If provided, the program will save the new image as <output_file_name>.bmp
