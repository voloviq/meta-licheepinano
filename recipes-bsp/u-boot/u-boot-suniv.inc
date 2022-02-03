DESCRIPTION="Upstream's U-boot configured for sunxi devices"
require u-boot-common.inc
require u-boot.inc

DEPENDS += " bc-native dtc-native swig-native python3-native flex-native bison-native swig-native "

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

COMPATIBLE_MACHINE = "(suniv)"

DEFAULT_PREFERENCE_suniv="1"

SRC_URI = "git://github.com/henrycoding/uboot-f1c100s.git;branch=master \
		file://boot.cmd \
		"

SRCREV = "155caf14c9e998e3379adbd4db7b0bb82184806e"

PV = "v2020.07"

S = "${WORKDIR}/git"

UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"

EXTRA_OEMAKE += ' HOSTLDSHARED="${BUILD_CC} -shared ${BUILD_LDFLAGS} ${BUILD_CFLAGS}" '

do_compile_append() {
    ${B}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot.cmd ${WORKDIR}/${UBOOT_ENV_BINARY}
}
