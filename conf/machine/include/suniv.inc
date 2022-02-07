require conf/machine/include/tune-arm926ejs.inc
include conf/machine/include/soc-family.inc

SOC_FAMILY = "suniv"

KERNEL_IMAGETYPE ?= "uImage"

IMAGE_CLASSES += "spi-nor-jffs2"
IMAGE_FSTYPES += "ext4 tar.gz sunxi-spinor"
