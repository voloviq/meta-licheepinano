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

Lcd <br>
Touchscreen <br>

## List of not tested elements

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

    - modify line with "MACHINE ??" to add "licheepinano"

    - align *DL_DIR = "${HOME}/yocto/downloads"* <br>

    - align *SSTATE_DIR = "${HOME}/yocto/sstate-cache"* <br>
    
    - align *TMPDIR = "${HOME}/yocto/tmp"* <br>
    
    - add at the end following records <br> <br>
    	*RM_OLD_IMAGE = "1"* <br>
	*INHERIT += "rm_work"* <br>

    **Note:** Please adapt rest of conf/local.conf parameters if necessary. <br>

6. Build objects

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
    	***sudo dd if=~/yocto/tmp/deploy/images/licheepinano/console-image-licheepinano.sunxi-sdimg of=/dev/mmcblk0 bs=1024*** <br>

# Limitation

	- rootfs-resize not working (SD CARD size can be resized manualy)
	- no wiringpi or similar library to driver GPIO in C code
	- wifi not working, missing in dts
	- spi flash not supported only mmc0 working
