DESCRIPTION = "A minimal console image for Lichee Pi Nano"
LICENSE = "MIT"

IMAGE_INSTALL= "\
        base-files \
        base-passwd \
        busybox \
        sysvinit \
        initscripts \
        ${CORE_IMAGE_EXTRA_INSTALL} \
"

IMAGE_LINGUAS = " "

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"