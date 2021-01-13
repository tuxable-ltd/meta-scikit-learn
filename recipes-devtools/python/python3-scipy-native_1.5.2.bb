SUMMARY = "SciPy: Scientific Library for Python"
HOMEPAGE = "https://www.scipy.org"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8256119827cf2bbe63512d4868075867"

SRC_URI = "https://pypi.python.org/packages/source/s/scipy/scipy-${PV}.tar.gz"

SRC_URI[md5sum] = "620fc39f371e04a76af5d0290f8d3753"
SRC_URI[sha256sum] = "066c513d90eb3fd7567a9e150828d39111ebd88d3e924cdfc9f8ce19ab6f90c9"

S = "${WORKDIR}/scipy-${PV}"

DEPENDS = "${PYTHON_PN}-numpy-native openblas-native lapack-native ${PYTHON_PN}-pybind11-native"

CLEANBROKEN = "1"

inherit setuptools3 native
