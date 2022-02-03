SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "(suniv)"

inherit kernel

#require linux.inc
require recipes-kernel/linux/linux-yocto.inc

# Since we're not using git, this doesn't make a difference, but we need to fill
# in something or kernel-yocto.bbclass will fail.
KBRANCH ?= "nano-4.14-exp"

# Pull in the devicetree files into the rootfs
# RDEPENDS_${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree"

KCONFIG_MODE ?= "alldefconfig"
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

S = "${WORKDIR}/git"
SRCREV_pn-${PN} = "44874751dbc7e112de0c78cbfb0479574cdb2332"

SRC_URI += "git://github.com/Lichee-Pi/linux.git;branch=nano-4.14-exp;depth=1;protocol=git \
		file://licheepi-nano/001-lichee-pi-nano-dts-add-modules-patch.patch \
		file://licheepi-nano/002-add-original-lichee-pi-nano-lcd-touchscreen.dtsi.patch \
		file://licheepi-nano/003-suniv-dtsi-i2c-add.patch \
		file://licheepi-nano/004-licheepi-nano-i2c-mv64xx.patch \
		file://licheepi-nano/005-dts-add-nor-flash-support.patch \
        	file://licheepi-nano/defconfig \
        "
