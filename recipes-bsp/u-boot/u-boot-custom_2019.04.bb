#require u-boot-common.inc
#require u-boot.inc

# M.Wolowik
# Copyright (C) 2020 Embedded System Labs

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PV = "2019.04"
PR = "r0"

DEPENDS += " bc-native dtc-native swig-native python3-native flex-native bison-native "

COMPATIBLE_MACHINE = "(suniv)"

UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"

SRCREV = "c0ff302fa622ce5081c49ef573e0f4b125bb1e77"
SRC_URI = " \
    git://github.com/thirtythreeforty/u-boot.git;branch=f1c100s-v2019.04;protocol=git \
	file://boot.cmd \
 "

EXTRA_OEMAKE += ' HOSTLDSHARED="${BUILD_CC} -shared ${BUILD_LDFLAGS} ${BUILD_CFLAGS}" '

#do_compile_append() {
#	${B}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot.cmd ${WORKDIR}/${UBOOT_ENV_BINARY}
#}
