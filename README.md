# meta-licheepinano

## Instruction how to build an image for Lichee Pi Nano in Yocto

### Products:

![Schematic](licheepi-nano.png) <br>
Lichee Pi Nano Version <br>
<br>

Lichee Pi Nano Pinout <br>
![Pinout](licheepinano-pinout.png) <br>

Lichee Pi Nano dedicated LCD Display model sh050jgb30 <br>
![Display](licheepi-zero-nano-lcd-display.jpg) <br>

## General Note:
Assumed that Linux Ubuntu is installed

## List of tested elements
Example application for GPIO handling

## List of not tested elements
Lcd <br>
Touchscreen <br>

TBD <br>

## How to build an images

1. First make sure to following packages are installed in system

    ***sudo apt-get install gawk wget diffstat unzip texinfo gcc-multilib build-essential chrpath socat libsdl1.2-dev xterm emscripten libmpc-dev libgmp3-dev***

    **Note:**
    More informations can be found on Yocto reference manual.

2. Download necessary Yocto packaged listed below. Be sure to be in root of home folder.

	***mkdir yocto***<br>
	***cd yocto*** <br>
	***mkdir build*** <br>
	***git clone git://git.yoctoproject.org/poky --depth 1 -b dunfell*** <br>
        ***cd poky*** <br>
	***git clone git://git.openembedded.org/meta-openembedded --depth 1 -b dunfell*** <br>
	***git clone https://github.com/meta-qt5/meta-qt5.git --depth 1 -b dunfell*** <br>
	***git clone https://github.com/voloviq/meta-licheepinano --depth 1 -b dunfell*** <br>

3. Select directory to build Linux

    Nano version <br>
	***source oe-init-build-env ~/yocto/build/licheepinano*** <br>

4. Modify bblayers.conf(located in ~/yocto/build/licheepinano/conf)

    *BBLAYERS ?= " \\\
      ${HOME}/yocto/poky/meta \\\
      ${HOME}/yocto/poky/meta-poky \\\
      ${HOME}/yocto/poky/meta-openembedded/meta-oe \\\
      ${HOME}/yocto/poky/meta-openembedded/meta-networking \\\
      ${HOME}/yocto/poky/meta-openembedded/meta-python \\\
      ${HOME}/yocto/poky/meta-qt5 \\\
      ${HOME}/yocto/poky/meta-licheepinano \\\
      "*<br>

    **Note:** Please adapt PATH of conf/bblayers.conf if necessary. <br>

5. Modify local.conf(located in ~/yocto/build/licheepinano/conf) file

    - modify line with "MACHINE ??" to add "licheepinano-sdcard" or for SPI NOR Flash "licheepinano-spinor"

    - align *DL_DIR = "${HOME}/yocto/downloads"* <br>

    - align *SSTATE_DIR = "${HOME}/yocto/sstate-cache"* <br>
    
    - align *TMPDIR = "${HOME}/yocto/tmp"* <br>
    
    - add at the end following records <br> <br>
    	*RM_OLD_IMAGE = "1"* <br>
	    *INHERIT += "rm_work"* <br>
    - for spi flash change DISTRO ?= "poky" to DISTRO ?= "licheepinano-tiny" <br>

    **Note:** Please adapt rest of conf/local.conf parameters if necessary. <br>

6. Build objects

    - When using SPI NOR Flash use following image
    - core image minimal <br>
      ***bitbake core-image-minimal*** <br>

    - console image <br>
      ***bitbake console-image*** <br>

    - qt5 image <br>
      ***bitbake qt5-image*** <br>

    - qt5 toolchain sdk <br>
      ***bitbake meta-toolchain-qt5*** <br>

7. After compilation images appears in

    Nano version <br>
	*~/yocto/tmp/deploy/images/licheepinano* <br>

8. Insert SD CARD into dedicated CARD slot and issue following command to write an image

    **Note:** <br>
    Be 100% sure to provide a valid device name (**of=/dev/sde/mmcblk0**). Wrong name "/dev/sde/mmcblk0" dameage Your system file ! <br> <br>
        Nano version <br>
    	***sudo dd if=~/yocto/tmp/deploy/images/licheepinano-sdcard/core-image-minimal-licheepinano-sdcard.sunxi-sdimg of=/dev/mmcblk0 bs=1024*** <br>

9. SPI NOR Flash update tool compilation(if valid sunxi-tools installed go to point 10)<br>
    ***git clone https://github.com/Icenowy/sunxi-tools.git -b f1c100s-spiflash***<br>
    ***sudo apt-get install libz libusb-1.0-0-dev***<br>
    ***make***<br>
    ***sudo make install***<br>

10. Flash SPI NOR flash<br>
    ***sunxi-fel -p spiflash-write 0 ~/yocto/tmp/deploy/images/licheepinano-spinor/core-image-minimal-licheepinano-spinor.sunxi-spinor***<br>

11. How to handle GPIO from userfs - example (used PE3 as GPIO)<br>

    1. Take a GPIO for instance PE3<br>
    ***echo 131 > /sys/class/gpio/export***<br>
    2. Set as out or in<br>
    ***echo "out" > /sys/class/gpio/gpio131/direction***<br>
    3. Set GPIO state if configured as ouput<br>
    ***echo 1 > /sys/class/gpio/gpio131/value***<br>
    ***echo 0 > /sys/class/gpio/gpio131/value***<br>
    
# Limitation
	
