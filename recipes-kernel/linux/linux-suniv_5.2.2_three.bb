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
KBRANCH ?= "f1c100s-v5.2.2"

# Pull in the devicetree files into the rootfs
# RDEPENDS_${KERNEL_PACKAGE_NAME}:base += "kernel-devicetree"

KCONFIG_MODE ?= "alldefconfig"
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

S = "${WORKDIR}/git"
SRCREV:pn-${PN} = "5be728fc77f14ec41a20e0a3ab85dd6a53547cb1"

KERNEL_DANGLING_FEATURES_WARN_ONLY = "t"

SRC_URI += "git://github.com/thirtythreeforty/linux.git;branch=f1c100s-v5.2.2;protocol=https \
		file://licheepi-nano/dtc-lexer-modification-to-lates-gcc.patch \
       		file://licheepi-nano/defconfig \
        "
