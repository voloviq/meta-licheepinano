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
# RDEPENDS_${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree"

KCONFIG_MODE ?= "alldefconfig"
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

S = "${WORKDIR}/git"
SRCREV_pn-${PN} = "05696d0119680f12afe74d70a26f8d35c208ba4a"

SRC_URI += "git://github.com/Lichee-Pi/linux.git;branch=nano-5.2-flash;depth=1;protocol=git \
		file://licheepi-nano/5.2-001-dts-add-nor-flash-support.patch \
		file://licheepi-nano/5.2-002-add-usb-support.patch \
        file://licheepi-nano/defconfig \
        "
