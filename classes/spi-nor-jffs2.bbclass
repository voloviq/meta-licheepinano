inherit image_types

#
# Create an image that can by written into a SPI NOR flash technology using dd application.
# Written for LicheePi Nano to simplify programming process and to write only one image file.
#
# The image layout(layout is valid for 16MBytes Flashes) used is:
#
#    OFFSET                    PARTITION SIZE                PARTITION
#    0                      -> 458752(0x70000)             - SPL U-Boot
#    488*1024               -> 65536(0x10000)              - The dtb file
#    512*1024               -> 4980736(0x4C0000)           - Kernel
#    4864*1024              -> 10494976(0xA02400)          - JFFS2 rootfs
#
#    SUM of all partition should give all flash memory size, 
#    SUM = 0x70000+0x10000+0x4C0000+0xA02400 = 0xF42400(16000000)
#
#	 Before change partition offsets here do it first for U-Boot DTS and defconifg plus Kernel DTS

# This image depends on the rootfs image
IMAGE_TYPEDEP_sunxi-spinor = "${SPINOR_ROOTFS_TYPE}"
SPINOR_ROOTFS_TYPE ?= "jffs2"

do_image_sunxi_spinor[depends] += " \
			mtools-native:do_populate_sysroot \
			dosfstools-native:do_populate_sysroot \
			virtual/kernel:do_deploy \
			virtual/bootloader:do_deploy \
			"

# The NOR SPI Flash image name
SPINOR = "${IMGDEPLOYDIR}/${IMAGE_NAME}.rootfs.sunxi-spinor"

IMAGE_CMD_sunxi-spinor () {

	# Initialize spi nor flash image file
	mkfs.jffs2 -s 0x100 -e 0x10000 --pad=0xA02400 -d ${WORKDIR}/rootfs -o ${DEPLOY_DIR_IMAGE}/jffs2.img
	dd if=/dev/zero of=${SPINOR} bs=1M count=16
	dd if=${DEPLOY_DIR_IMAGE}/${SPL_BINARY} of=${SPINOR} bs=1k conv=notrunc
	dd if=${DEPLOY_DIR_IMAGE}/${KERNEL_DEVICETREE} of=${SPINOR} bs=1k seek=448 conv=notrunc
	dd if=${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE} of=${SPINOR} bs=1k seek=512 conv=notrunc
	dd if=${DEPLOY_DIR_IMAGE}/jffs2.img of=${SPINOR} bs=1k seek=4864 conv=notrunc
	rm -f ${DEPLOY_DIR_IMAGE}/jffs2.img
}