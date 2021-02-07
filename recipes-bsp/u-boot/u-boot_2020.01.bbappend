FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DESCRIPTION="Upstream's U-boot configured for sunxi devices"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

COMPATIBLE_MACHINE = "(suniv)"

SRC_URI = "git://git.denx.de/u-boot.git;branch=master \
	file://boot.cmd \
	file://0001-u-boot-add-dts.patch \
	"

S = "${WORKDIR}/git"

UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"

do_compile_append() {
    ${B}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot.cmd ${WORKDIR}/${UBOOT_ENV_BINARY}
}
