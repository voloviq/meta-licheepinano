DESCRIPTION="Upstream's U-boot configured for sunxi devices"

require recipes-bsp/u-boot/u-boot.inc

#DEPENDS += " bc-native dtc-native swig-native python3-native flex-native bison-native "
#DEPENDS_append_sun50i = " atf-sunxi "

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

COMPATIBLE_MACHINE = "(suniv)"

DEFAULT_PREFERENCE_suniv="1"

SRC_URI = "git://github.com/Lichee-Pi/u-boot.git;branch=nano-v2018.01 \
           file://licheepi_nano_spiflash_defconfig.patch \
           "
#SRC_URI = "git:///home/fefr/Builds/suniv/u-boot;branch=nano-v2018.01;protocol=file \
#           file://boot_suniv.cmd \
#           "

SRCREV = "013ca457fd64b72444a0f5f480b1bbdd8b7481eb"

PV = "v2018.01+git${SRCPV}"
#PV = "v2018.01"

S = "${WORKDIR}/git"

UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"

EXTRA_OEMAKE += ' HOSTLDSHARED="${BUILD_CC} -shared ${BUILD_LDFLAGS} ${BUILD_CFLAGS}" '

do_compile_append() {
    ${B}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot_suniv.cmd ${WORKDIR}/${UBOOT_ENV_BINARY}
}
