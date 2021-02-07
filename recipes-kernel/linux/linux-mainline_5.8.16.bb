SECTION = "kernel"
DESCRIPTION = "Mainline Stable Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
COMPATIBLE_MACHINE = "(sun4i|sun5i|sun7i|sun8i)"

inherit kernel

require linux.inc

# Since we're not using git, this doesn't make a difference, but we need to fill
# in something or kernel-yocto.bbclass will fail.
KBRANCH ?= "master"

DEPENDS += "rsync-native"

# Pull in the devicetree files into the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree kernel-modules"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

BRANCH = "orange-pi-5.8"
S = "${WORKDIR}/git"

SRCREV = "c5464f4be19b78584713800a695dffdcf1761e27"
PV = "5.8.16"

SRC_URI[sha256sum] = "4622bf77bb58d316ad939296667802e4da5fee3eeb6c4885c79f2f85503d5852"

SRC_URI = "git://github.com/megous/linux.git;protocol=git;branch=${BRANCH} \
        file://defconfig \
        "
