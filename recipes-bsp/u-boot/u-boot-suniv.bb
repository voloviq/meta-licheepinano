DESCRIPTION="Upstream's U-boot configured for sunxi devices"

require u-boot-suniv.inc
SRC_URI += "file://001-u-boot-mmc-support.patch"
