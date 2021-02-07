DESCRIPTION="Upstream's U-boot configured for sunxi devices"

require u-boot-suniv.inc
SRC_URI += "file://licheepi_nano_spiflash_defconfig.patch \
           "
