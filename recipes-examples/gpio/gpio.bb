#
# Simple GPIO port handle example application
# Yocto Project Development Manual.
#

SUMMARY = "Simple GPIO handle example application"
SECTION = "example"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "	file://gpio-example.c \
			file://gpio_lib.c \
			file://gpio_lib.h \
			file://gpio-example.h "

S = "${WORKDIR}"

do_compile() {
		${CC} ${LDFLAGS} -c gpio-example.c -c gpio_lib.c
	    ${CC} ${LDFLAGS} gpio-example.o gpio_lib.o -o gpio-example
}

do_install() {
		install -d ${D}${bindir}
		install -m 0755 gpio-example ${D}${bindir}
		install -d ${D}${sysconfdir}/init.d
		install -m 755 gpio-example ${D}${sysconfdir}/init.d/gpio-example
}

inherit update-rc.d

INITSCRIPT_NAME="gpio-example"
