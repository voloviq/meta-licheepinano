SECTION = "kernel"
DESCRIPTION = "Nano Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
COMPATIBLE_MACHINE = "(suniv)"

inherit kernel

#require linux.inc
require recipes-kernel/linux/linux-yocto.inc

# Since we're not using git, this doesn't make a difference, but we need to fill
# in something or kernel-yocto.bbclass will fail.
KBRANCH ?= "nano-5.2-flash"

# Pull in the devicetree files into the rootfs
# RDEPENDS_${KERNEL_PACKAGE_NAME}:base += "kernel-devicetree"

KCONFIG_MODE ?= "alldefconfig"
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

S = "${WORKDIR}/git"
SRCREV:pn-${PN} = "05696d0119680f12afe74d70a26f8d35c208ba4a"
SRC_URI[sha256sum] = "0a1a5ae2f30eb2b38215e59077f045aabd7f4e2857a881482f02ea48186105d8"

KERNEL_DANGLING_FEATURES_WARN_ONLY = "t"

SRC_URI += "git://github.com/Lichee-Pi/linux.git;branch=nano-5.2-flash;depth=1;protocol=https \
		file://licheepi-nano/dts-common-modifications.patch \
		file://licheepi-nano/dts-add-display-ili9340.patch \
		file://licheepi-nano/nor-flash-driver-update.patch \
		file://licheepi-nano/add-touchscreen-driver-ns2009.patch \
		file://licheepi-nano/dts-add-touchscreen-ns2009.patch \
		file://licheepi-nano/usb-driver-modifications.patch \
		file://licheepi-nano/fbtft-driver-modifications.patch \
		file://licheepi-nano/dtc-lexer-modification-to-lates-gcc.patch \
       		file://licheepi-nano/defconfig \
        "
