SUMMARY = "SciPy: Scientific Library for Python"
HOMEPAGE = "https://www.scipy.org"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8256119827cf2bbe63512d4868075867"

SRC_URI = "https://pypi.python.org/packages/source/s/scipy/scipy-${PV}.tar.gz \
           file://0001-Allow-passing-flags-via-FARCH-for-mach.patch \
           "

SRC_URI[md5sum] = "620fc39f371e04a76af5d0290f8d3753"
SRC_URI[sha256sum] = "066c513d90eb3fd7567a9e150828d39111ebd88d3e924cdfc9f8ce19ab6f90c9"

S = "${WORKDIR}/scipy-${PV}"

DEPENDS = "${PYTHON_PN}-numpy ${PYTHON_PN}-numpy-native openblas lapack ${PYTHON_PN}-pybind11-native libgfortran"
RDEPENDS_${PN} = "${PYTHON_PN}-numpy ${PYTHON_PN}-multiprocessing"

CLEANBROKEN = "1"

inherit setuptools3

export F90 = "${TARGET_PREFIX}gfortran"
export FARCH = "${TUNE_CCARGS}"

# Numpy expects the LDSHARED env variable to point to a single
# executable, but OE sets it to include some flags as well. So we split
# the existing LDSHARED variable into the base executable and flags, and
# prepend the flags into LDFLAGS
LDFLAGS_prepend := "${@" ".join(d.getVar('LDSHARED', True).split()[1:])} "
export LDSHARED := "${@d.getVar('LDSHARED', True).split()[0]}"

# Tell Numpy to look in target sysroot site-packages directory for libraries
LDFLAGS_append = " -L${STAGING_LIBDIR}/${PYTHON_DIR}/site-packages/numpy/core/lib"

do_compile_prepend() {
	echo "[ALL]" > ${S}/site.cfg
	echo "library_dirs = ${STAGING_LIBDIR}" >> ${S}/site.cfg
	echo "include_dirs = ${STAGING_INCDIR}" >> ${S}/site.cfg

	echo "[openblas]" >> ${S}/site.cfg
	echo "libraries = openblas" >> ${S}/site.cfg
	echo "library_dirs = ${STAGING_LIBDIR}" >> ${S}/site.cfg
	echo "include_dirs = ${STAGING_INCDIR}" >> ${S}/site.cfg

	echo "[lapack]" >> ${S}/site.cfg
	echo "libraries = lapack" >> ${S}/site.cfg
	echo "library_dirs = ${STAGING_LIBDIR}" >> ${S}/site.cfg
	echo "include_dirs = ${STAGING_INCDIR}" >> ${S}/site.cfg

}
