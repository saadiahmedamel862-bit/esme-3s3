# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "git://github.com/saadiahmedamel862-bit/esme-3s3.git;protocol=https;branch=main"
SRCREV = "7a4318a4a9c6b41058cdbd1a5984fae9eb9bea56"
S = "${WORKDIR}/git"
# NOTE: this is a Makefile-only piece of software, so we cannot generate much of the
# recipe automatically - you will need to examine the Makefile yourself and ensure
# that the appropriate arguments are passed in.

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
	# You will almost certainly need to add additional arguments here
	oe_runmake
}

do_install() {
    # binaire
    install -d ${D}${bindir}
    install -m 0755 esme-gpio-toggle ${D}${bindir}

    # script init.d
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 esme-gpio26-toggle.sh ${D}${sysconfdir}/init.d/
}
# Inherit pkg-config support
inherit pkgconfig

# Build dependency
DEPENDS += "libgpiod"

# Accept any libgpiod 1.x version
PREFERRED_VERSION_libgpiod = "1.%"

INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN}-dbg += "ldflags"

inherit pkgconfig update-rc.d

INITSCRIPT_NAME = "esme-gpio26-toggle.sh"
INITSCRIPT_PACKAGES = "${PN}"

DEPENDS += "libgpiod"
