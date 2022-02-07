inherit image_types

#
# Create an image that can by written onto a SD card using dd.
# Originally written for rasberrypi adapt for the needs of allwinner sunxi based boards
#
# The disk layout used is:
#
#    0                      -> 8*1024                           - reserverd
#    8*1024                 ->                                  - arm combined spl/u-boot or aarch64 spl
#    40*1024                ->                                  - aarch64 u-boot
#    2048*1024              -> BOOT_SPACE                       - bootloader and kernel
#
#

# This image depends on the rootfs image
IMAGE_TYPEDEP_sunxi-spinor = "${SPINOR_ROOTFS_TYPE}"
SPINOR_ROOTFS_TYPE ?= "jffs2"

do_image_sunxi_spinor[depends] += " \
			parted-native:do_populate_sysroot \
			mtools-native:do_populate_sysroot \
			dosfstools-native:do_populate_sysroot \
			virtual/kernel:do_deploy \
			virtual/bootloader:do_deploy \
			"

# SD card image name
SPINOR = "${IMGDEPLOYDIR}/${IMAGE_NAME}.rootfs.sunxi-spinor"

IMAGE_CMD_sunxi-spinor () {

	# Initialize spi nor flash image file
	mkfs.jffs2 -s 0x100 -e 0x10000 --pad=0xa01000 -d ${WORKDIR}/rootfs -o ${DEPLOY_DIR_IMAGE}/jffs2.img
	dd if=/dev/zero of=${SPINOR} bs=1M count=16
	dd if=${DEPLOY_DIR_IMAGE}/${SPL_BINARY} of=${SPINOR} bs=1k conv=notrunc
	dd if=${DEPLOY_DIR_IMAGE}/${KERNEL_DEVICETREE} of=${SPINOR} bs=1k seek=448 conv=notrunc
	dd if=${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE} of=${SPINOR} bs=1k seek=512 conv=notrunc
	dd if=${DEPLOY_DIR_IMAGE}/jffs2.img of=${SPINOR} bs=1k seek=4864 conv=notrunc
	rm -f ${DEPLOY_DIR_IMAGE}/jffs2.img
}