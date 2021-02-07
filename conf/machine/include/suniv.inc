include conf/machine/include/soc-family.inc
require conf/machine/include/tune-arm926ejs.inc

SOC_FAMILY = "suniv"

# Sub-architecture support
MACHINE_SOCARCH_SUFFIX ?= ""
MACHINE_SOCARCH_SUFFIX_suniv = "-suniv"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-suniv"
PREFERRED_VERSION_linux-suniv ?= "4.15%"

MACHINE_EXTRA_RRECOMMENDS = "kernel-modules"

UBOOT_LOCALVERSION = "-g${@d.getVar('SRCPV', True).partition('+')[2][0:7]}"

UBOOT_ENTRYPOINT ?= "0x80008000"

SERIAL_CONSOLE ?= "115200 ttyS0"
MACHINE_FEATURES ?= "alsa apm keyboard rtc serial usbgadget usbhost vfat"
